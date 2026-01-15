# Using the Standalone MCP Server

The MCP server has been extracted to its own standalone repository to be used across multiple projects.

## Using with This Project

### Quick Start

1. Clone the MCP server:

```bash
git clone https://github.com/Prism-Software-Solutions/mcp-server.git ~/Code/mcp-server
cd ~/Code/mcp-server
npm install
echo "ANTHROPIC_API_KEY=sk-..." > .env
```

2. Use it with the Selenium framework:

```bash
PROJECT_ROOT=$(pwd) ~/Code/mcp-server/node_modules/.bin/node ~/Code/mcp-server/index.js "What tests are available?"
```

Or create an alias:

```bash
alias mcp="PROJECT_ROOT=$(pwd) node ~/Code/mcp-server/index.js"
```

Then:

```bash
cd /path/to/selenium-framework-tests
mcp "Run all tests"
```

### With npm Script

Add to your `package.json`:

```json
{
  "scripts": {
    "mcp": "PROJECT_ROOT=$(pwd) node ~/Code/mcp-server/index.js"
  }
}
```

Then:

```bash
npm run mcp -- "What tests are available?"
npm run mcp -- "Run all tests"
npm run mcp -- "Generate a test report"
```

## Resources

- **Repository**: https://github.com/Prism-Software-Solutions/mcp-server
- **Setup Guide**: See the repo's SETUP.md file
- **Full Documentation**: See the repo's README.md file

## Why Separate?

The MCP server is now standalone so you can:
- Use it across multiple projects (Selenium, FastAPI, Next.js, etc.)
- Update it independently
- Share it with team members
- Eventually publish to npm as a package
