package com.selenium.tests.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Base test class for all Selenium tests
 * Handles WebDriver setup and teardown
 */
public class BaseTest {
    protected WebDriver driver;
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeMethod
    public void setUp() {
        logger.info("Setting up WebDriver...");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        logger.info("WebDriver setup complete");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            logger.info("Closing WebDriver...");
            driver.quit();
            logger.info("WebDriver closed");
        }
    }
}
