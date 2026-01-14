# Selenium Java Test Framework

This is a Selenium UI Test Automation Framework built with Java.

## Project Structure

```
selenium-framework-tests/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/selenium/tests/
│   │   │       ├── base/          (Base classes for tests and pages)
│   │   │       └── pages/         (Page Object Models)
│   │   └── resources/             (Configuration files)
│   └── test/
│       └── java/
│           └── com/selenium/tests/
│               └── ui/            (Test cases)
├── pom.xml                        (Maven configuration)
├── testng.xml                     (TestNG configuration)
└── README.md                      (This file)
```

## Key Components

### Base Classes
- **BaseTest**: Handles WebDriver initialization and teardown
- **BasePage**: Base class for Page Object Models with common operations

### Test Framework
- **Selenium WebDriver 4.15.0**: Web automation framework
- **TestNG 7.8.1**: Testing framework
- **WebDriverManager 5.6.3**: Automatic driver management
- **Log4j 2.21.1**: Logging framework

## Prerequisites

- Java 11+
- Maven 3.6+
- Chrome browser (for the sample test)

## Build & Run

### Build the project
```bash
mvn clean compile
```

### Run all tests
```bash
mvn test
```

### Run a specific test
```bash
mvn test -Dtest=SampleUITest
```

## File Descriptions

### Core Configuration Files

#### `pom.xml`
Maven Project Object Model configuration file. Contains:
- Project metadata (groupId, artifactId, version)
- Java version settings (Java 11)
- Dependency management for Selenium, TestNG, WebDriverManager, and Log4j
- Maven plugin configurations for compilation and test execution
- Defines how the project is built and tested

#### `testng.xml`
TestNG test suite configuration file. Contains:
- Test suite definition named "Selenium Test Suite"
- Parallel execution settings
- Test classes to be executed
- Controls which tests run and in what order/manner

#### `src/main/resources/log4j.properties`
Logging configuration file. Contains:
- Log output settings (console and file)
- Log level configuration (INFO level by default)
- Log format pattern with timestamp, level, class name, and message
- Defines how and where logs are written during test execution

### Base Classes (Main Source)

#### `src/main/java/com/selenium/tests/base/BaseTest.java`
Abstract base class for all test classes. Purpose:
- Manages WebDriver lifecycle (`setUp()` and `tearDown()`)
- Initializes Chrome WebDriver before each test
- Maximizes browser window for consistent test execution
- Closes WebDriver and frees resources after each test
- Provides logger instance for all test classes
- All test classes should extend this class to inherit WebDriver management

#### `src/main/java/com/selenium/tests/pages/BasePage.java`
Abstract base class for Page Object Models (POM). Purpose:
- Implements Page Object Model design pattern
- Provides common methods: `click()`, `sendKeys()`, `getText()`, `navigateTo()`
- Initializes PageFactory for @FindBy annotations support
- Provides logger for page operations
- Base class for all page-specific classes that represent web pages
- Centralizes reusable web element interactions

### Test Classes

#### `src/test/java/com/selenium/tests/ui/SampleUITest.java`
Sample test class demonstrating the framework usage. Purpose:
- Shows how to extend `BaseTest` for writing test cases
- Includes a sample test method `sampleNavigationTest()`
- Demonstrates navigation, verification, and assertion patterns
- Serves as a template for creating new test cases
- Tests page title retrieval from example.com

### Documentation

#### `README.md`
This file. Provides:
- Project overview and description
- Directory structure explanation
- Technology stack details
- Setup and execution instructions
- Configuration file descriptions
- Guidelines for adding new tests

## Adding New Tests

1. Create a new test class in `src/test/java/com/selenium/tests/ui/`
2. Extend `BaseTest` class to inherit WebDriver management
3. Annotate test methods with `@Test`
4. Update `testng.xml` to include the new test class in the suite

## Adding New Page Objects

1. Create a new page class in `src/main/java/com/selenium/tests/pages/`
2. Extend `BasePage` class
3. Use `@FindBy` annotations for WebElements (requires PageFactory)
4. Implement page-specific methods using inherited helper methods

## Prism Software Solutions Test Suite

### Test Coverage

This test suite contains comprehensive tests for **prismsoftwaresolutions.com** website covering:

#### Page Objects
- **HomePage**: Home page with main heading, sections, and navigation links
- **AboutPage**: About page with mission, vision, and product information
- **ContactPage**: Contact page with form fields and submission functionality

#### Test Classes

**HomePageTest** (7 tests)
- Home page loads successfully
- Main heading verification
- Prism logo display
- Why Choose Prism section visibility
- Our Products section visibility
- Navigation to About page via link
- Navigation to Contact page via link
- Learn More link functionality

**AboutPageTest** (8 tests)
- About page loads successfully
- Page title verification
- Mission section visibility and content
- Vision section visibility and content
- Smart Operations section visibility
- Navigation to Home page via link
- Navigation to Contact page via link

**ContactPageTest** (9 tests)
- Contact page loads successfully
- Page title verification
- Contact form elements display
- Connect section visibility
- Fill contact form with data
- Submit contact form
- Navigation to Home page from Contact
- Contact page navigation path (Home -> About -> Contact)
- Email input validation

**PrismNavigationTest** (6 tests)
- Complete navigation flow between all pages
- Direct navigation to all pages
- Home page all links accessibility
- Page title consistency across pages
- Browser back button functionality
- Browser forward button functionality

### Running Tests

Run all tests:
```bash
mvn test
```

Run only Prism tests:
```bash
mvn test -Dtest=prism/*
```

Run specific test class:
```bash
mvn test -Dtest=HomePageTest
```

Run specific test method:
```bash
mvn test -Dtest=HomePageTest#testHomePageLoadsSuccessfully
```

### Test Results

Test execution logs are written to: `logs/test-execution.log`

Console output includes:
- Test start/end information
- Page navigation logs
- Element interaction details
- Assertion results
