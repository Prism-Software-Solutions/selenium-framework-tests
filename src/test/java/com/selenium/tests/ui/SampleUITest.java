package com.selenium.tests.ui;

import com.selenium.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Sample UI Test class demonstrating test structure
 */
public class SampleUITest extends BaseTest {

    @Test
    public void sampleNavigationTest() {
        logger.info("Starting sample navigation test");
        
        // Navigate to a website
        driver.navigate().to("https://www.example.com");
        
        // Verify page title
        String title = driver.getTitle();
        logger.info("Page title: " + title);
        Assert.assertNotNull(title, "Page title should not be null");
        
        logger.info("Sample navigation test completed successfully");
    }
}
