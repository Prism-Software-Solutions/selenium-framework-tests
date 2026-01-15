#!/usr/bin/env node

/**
 * Selenium Test Framework MCP Server
 * Provides tools for managing and running Selenium tests
 */

// Load environment variables from .env (if present)
import 'dotenv/config';

import Anthropic from "@anthropic-ai/sdk";
import { spawn } from "child_process";
import fs from "fs";
import path from "path";
import { fileURLToPath } from "url";

const __dirname = path.dirname(fileURLToPath(import.meta.url));
const PROJECT_ROOT = path.join(__dirname, "..");

// Initialize Anthropic client
const client = new Anthropic({
  apiKey: process.env.ANTHROPIC_API_KEY,
});

// Tool definitions
const tools = [
  {
    name: "list_tests",
    description: "List all available Selenium tests in the framework",
    input_schema: {
      type: "object",
      properties: {
        test_class: {
          type: "string",
          description:
            "Optional: specific test class to filter (HomePageTest, AboutPageTest, etc.)",
        },
      },
      required: [],
    },
  },
  {
    name: "run_tests",
    description: "Run Selenium tests using Maven",
    input_schema: {
      type: "object",
      properties: {
        test_name: {
          type: "string",
          description:
            "Optional: specific test to run (e.g., 'HomePageTest' or 'HomePageTest#testHomePageLoadsSuccessfully')",
        },
        verbose: {
          type: "boolean",
          description: "Show verbose output (default: false)",
          default: false,
        },
      },
      required: [],
    },
  },
  {
    name: "get_test_results",
    description: "Get results from the last test run",
    input_schema: {
      type: "object",
      properties: {},
      required: [],
    },
  },
  {
    name: "generate_report",
    description: "Generate an HTML test report from recent test runs",
    input_schema: {
      type: "object",
      properties: {
        output_format: {
          type: "string",
          enum: ["html", "json", "summary"],
          description: "Format for the report (default: summary)",
          default: "summary",
        },
      },
      required: [],
    },
  },
  {
    name: "get_framework_info",
    description: "Get information about the Selenium test framework",
    input_schema: {
      type: "object",
      properties: {},
      required: [],
    },
  },
];

/**
 * List all available tests
 */
function listTests(testClass) {
  const testsDir = path.join(PROJECT_ROOT, "src/test/java/com/selenium/tests/ui/prism");

  if (!fs.existsSync(testsDir)) {
    return { error: "Tests directory not found" };
  }

  const files = fs.readdirSync(testsDir).filter((f) => f.endsWith(".java"));
  const tests = {};

  files.forEach((file) => {
    const className = file.replace(".java", "");
    if (!testClass || className === testClass) {
      const filePath = path.join(testsDir, file);
      const content = fs.readFileSync(filePath, "utf-8");
      const testMethods = (content.match(/@Test\s+public\s+void\s+(\w+)/g) || []).map((m) =>
        m.replace(/@Test\s+public\s+void\s+/, "")
      );

      tests[className] = testMethods;
    }
  });

  return tests;
}

/**
 * Run Maven tests
 */
function runTests(testName, verbose) {
  return new Promise((resolve) => {
    let command = `cd ${PROJECT_ROOT} && /opt/homebrew/bin/mvn test`;

    if (testName) {
      command += ` -Dtest=${testName}`;
    }

    if (!verbose) {
      command += " -q";
    }

    console.log(`Executing: ${command}`);

    const process = spawn("bash", ["-c", command]);
    let output = "";
    let error = "";

    process.stdout.on("data", (data) => {
      output += data.toString();
    });

    process.stderr.on("data", (data) => {
      error += data.toString();
    });

    process.on("close", (code) => {
      resolve({
        success: code === 0,
        exit_code: code,
        output: output.slice(-1000), // Last 1000 chars
        error: error.slice(-500),
      });
    });
  });
}

/**
 * Parse test results from surefire reports
 */
function getTestResults() {
  const reportsDir = path.join(PROJECT_ROOT, "target/surefire-reports");

  if (!fs.existsSync(reportsDir)) {
    return { error: "No test results found. Run tests first." };
  }

  try {
    const testSuiteFile = path.join(reportsDir, "TEST-TestSuite.xml");
    if (!fs.existsSync(testSuiteFile)) {
      return { error: "Test suite file not found" };
    }

    const content = fs.readFileSync(testSuiteFile, "utf-8");

    // Extract summary from XML
    const testsMatch = content.match(/tests="(\d+)"/);
    const failuresMatch = content.match(/failures="(\d+)"/);
    const errorsMatch = content.match(/errors="(\d+)"/);
    const timeMatch = content.match(/time="([\d.]+)"/);

    return {
      total_tests: testsMatch ? parseInt(testsMatch[1]) : 0,
      failures: failuresMatch ? parseInt(failuresMatch[1]) : 0,
      errors: errorsMatch ? parseInt(errorsMatch[1]) : 0,
      time_seconds: timeMatch ? parseFloat(timeMatch[1]) : 0,
      passed: testsMatch
        ? parseInt(testsMatch[1]) - (parseInt(failuresMatch?.[1] || 0) + parseInt(errorsMatch?.[1] || 0))
        : 0,
    };
  } catch (e) {
    return { error: `Failed to parse results: ${e.message}` };
  }
}

/**
 * Generate test report
 */
function generateReport(outputFormat) {
  const results = getTestResults();

  if (results.error) {
    return results;
  }

  const summary = `
Test Execution Report
====================
Total Tests: ${results.total_tests}
Passed: ${results.passed}
Failed: ${results.failures}
Errors: ${results.errors}
Execution Time: ${results.time_seconds}s
Success Rate: ${((results.passed / results.total_tests) * 100).toFixed(2)}%
  `;

  if (outputFormat === "summary") {
    return { report: summary };
  } else if (outputFormat === "json") {
    return { report: results };
  } else if (outputFormat === "html") {
    return {
      report: `
<html>
  <head><title>Test Report</title></head>
  <body>
    <h1>Selenium Test Framework Report</h1>
    <table border="1">
      <tr><td>Total Tests</td><td>${results.total_tests}</td></tr>
      <tr><td>Passed</td><td>${results.passed}</td></tr>
      <tr><td>Failed</td><td>${results.failures}</td></tr>
      <tr><td>Errors</td><td>${results.errors}</td></tr>
      <tr><td>Execution Time</td><td>${results.time_seconds}s</td></tr>
    </table>
  </body>
</html>
      `,
    };
  }

  return results;
}

/**
 * Get framework information
 */
function getFrameworkInfo() {
  const pomPath = path.join(PROJECT_ROOT, "pom.xml");
  const readmePath = path.join(PROJECT_ROOT, "README.md");

  let pomContent = "";
  let readmeContent = "";

  if (fs.existsSync(pomPath)) {
    pomContent = fs.readFileSync(pomPath, "utf-8");
    const versionMatch = pomContent.match(/<version>(.*?)<\/version>/);
    const seleniumMatch = pomContent.match(/<selenium\.version>(.*?)<\/selenium\.version>/);
    const testngMatch = pomContent.match(/<testng\.version>(.*?)<\/testng\.version>/);

    var frameworkInfo = {
      project_version: versionMatch ? versionMatch[1] : "unknown",
      selenium_version: seleniumMatch ? seleniumMatch[1] : "unknown",
      testng_version: testngMatch ? testngMatch[1] : "unknown",
    };
  }

  if (fs.existsSync(readmePath)) {
    readmeContent = fs.readFileSync(readmePath, "utf-8").slice(0, 500);
  }

  return {
    ...frameworkInfo,
    project_root: PROJECT_ROOT,
    has_tests: fs.existsSync(path.join(PROJECT_ROOT, "src/test")),
    test_count: Object.values(listTests("")).reduce((a, b) => a + b.length, 0),
  };
}

/**
 * Process tool calls
 */
async function processToolCall(toolName, toolInput) {
  switch (toolName) {
    case "list_tests":
      return JSON.stringify(listTests(toolInput.test_class || ""));
    case "run_tests":
      return JSON.stringify(await runTests(toolInput.test_name || "", toolInput.verbose || false));
    case "get_test_results":
      return JSON.stringify(getTestResults());
    case "generate_report":
      return JSON.stringify(generateReport(toolInput.output_format || "summary"));
    case "get_framework_info":
      return JSON.stringify(getFrameworkInfo());
    default:
      return JSON.stringify({ error: `Unknown tool: ${toolName}` });
  }
}

/**
 * Main MCP server loop
 */
async function main() {
  console.error("Selenium Test Framework MCP Server started");
  console.error("Tools available:", tools.map((t) => t.name));

  const userMessage =
    process.argv[2] || "What tests are available in the framework and what was the last test run result?";

  const messages = [{ role: "user", content: userMessage }];

  let continueLoop = true;
  while (continueLoop) {
    const response = await client.messages.create({
      model: "claude-3-sonnet-20240229",
      max_tokens: 4096,
      tools: tools,
      messages: messages,
    });

    // Add assistant response to messages
    messages.push({ role: "assistant", content: response.content });

    // Check if we should continue
    if (response.stop_reason === "end_turn") {
      continueLoop = false;

      // Print final response
      response.content.forEach((block) => {
        if (block.type === "text") {
          console.log(block.text);
        }
      });
      break;
    }

    // Process tool calls
    const toolResults = [];
    for (const block of response.content) {
      if (block.type === "tool_use") {
        console.error(`Executing tool: ${block.name}`);
        const result = await processToolCall(block.name, block.input);
        toolResults.push({
          type: "tool_result",
          tool_use_id: block.id,
          content: result,
        });
      }
    }

    // Add tool results to messages
    if (toolResults.length > 0) {
      messages.push({ role: "user", content: toolResults });
    } else {
      continueLoop = false;
    }
  }
}

main().catch(console.error);
