package com.selenium.tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Base page object class
 * All page objects should extend this class
 */
public class BasePage {
    protected WebDriver driver;
    protected static final Logger logger = LogManager.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected void click(WebElement element) {
        logger.info("Clicking element: " + element);
        element.click();
    }

    protected void sendKeys(WebElement element, String text) {
        logger.info("Typing in element: " + text);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(WebElement element) {
        logger.info("Getting text from element");
        return element.getText();
    }

    protected void navigateTo(String url) {
        logger.info("Navigating to: " + url);
        driver.navigate().to(url);
    }
}
