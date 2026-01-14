package com.selenium.tests.ui.prism;

import com.selenium.tests.base.BaseTest;
import com.selenium.tests.pages.prism.HomePage;
import com.selenium.tests.pages.prism.AboutPage;
import com.selenium.tests.pages.prism.ContactPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class for Prism Software Solutions Home Page
 */
public class HomePageTest extends BaseTest {

    @Test
    public void testHomePageLoadsSuccessfully() {
        logger.info("Starting: Test Home Page Loads Successfully");
        
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHomePage();
        
        // Verify page title
        String pageTitle = driver.getTitle();
        logger.info("Page title: " + pageTitle);
        Assert.assertNotNull(pageTitle, "Page title should not be null");
        Assert.assertTrue(pageTitle.contains("Prism"), "Page title should contain 'Prism'");
        
        logger.info("Test passed: Home page loaded successfully");
    }

    @Test
    public void testHomePageMainHeading() {
        logger.info("Starting: Test Home Page Main Heading");
        
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHomePage();
        
        String mainHeading = homePage.getMainHeading();
        logger.info("Main heading: " + mainHeading);
        Assert.assertNotNull(mainHeading, "Main heading should not be null");
        Assert.assertTrue(mainHeading.contains("Building Cutting-Edge Software"), 
            "Main heading should contain expected text");
        
        logger.info("Test passed: Main heading verified");
    }

    @Test
    public void testWhyChoosePrismSectionVisible() {
        logger.info("Starting: Test Why Choose Prism Section Is Visible");
        
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHomePage();
        
        homePage.scrollToWhyChoosePrism();
        boolean isSectionVisible = homePage.isWhyChoosePrismSectionVisible();
        logger.info("Why Choose Prism section visible: " + isSectionVisible);
        Assert.assertTrue(isSectionVisible, "Why Choose Prism section should be visible");
        
        logger.info("Test passed: Why Choose Prism section is visible");
    }

    @Test
    public void testOurProductsSectionVisible() {
        logger.info("Starting: Test Our Products Section Is Visible");
        
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHomePage();
        
        homePage.scrollToProducts();
        boolean isSectionVisible = homePage.isOurProductsSectionVisible();
        logger.info("Our Products section visible: " + isSectionVisible);
        Assert.assertTrue(isSectionVisible, "Our Products section should be visible");
        
        logger.info("Test passed: Our Products section is visible");
    }

    @Test
    public void testNavigateToAboutPageViaLink() {
        logger.info("Starting: Test Navigate to About Page via Link");
        
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHomePage();
        
        homePage.clickAboutLink();
        
        // Wait for page to load
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(3));
        
        AboutPage aboutPage = new AboutPage(driver);
        String aboutPageTitle = aboutPage.getPageTitle();
        logger.info("About page title: " + aboutPageTitle);
        Assert.assertNotNull(aboutPageTitle, "About page title should not be null");
        
        logger.info("Test passed: Navigated to About page via link");
    }

    @Test
    public void testNavigateToContactPageViaLink() {
        logger.info("Starting: Test Navigate to Contact Page via Link");
        
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHomePage();
        
        homePage.clickContactUs();
        
        // Wait for page to load
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(3));
        
        ContactPage contactPage = new ContactPage(driver);
        boolean isConnectSectionVisible = contactPage.isConnectSectionVisible();
        logger.info("Contact page loaded: " + isConnectSectionVisible);
        Assert.assertTrue(isConnectSectionVisible, "Should navigate to Contact page");
        
        logger.info("Test passed: Navigated to Contact page via link");
    }
}
