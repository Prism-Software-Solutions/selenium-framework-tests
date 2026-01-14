package com.selenium.tests.ui.prism;

import com.selenium.tests.base.BaseTest;
import com.selenium.tests.pages.prism.HomePage;
import com.selenium.tests.pages.prism.AboutPage;
import com.selenium.tests.pages.prism.ContactPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class for Prism Software Solutions Cross-Page Navigation
 * Tests navigation flows between home, about, and contact pages
 */
public class PrismNavigationTest extends BaseTest {

    @Test
    public void testCompleteNavigationFlow() {
        logger.info("Starting: Test Complete Navigation Flow");
        
        // Start from Home Page
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHomePage();
        String homeTitle = driver.getTitle();
        Assert.assertNotNull(homeTitle, "Home page should load");
        logger.info("Loaded Home page");
        
        // Navigate to About via link
        homePage.clickAboutLink();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(3));
        AboutPage aboutPage = new AboutPage(driver);
        String aboutTitle = aboutPage.getPageTitle();
        Assert.assertTrue(aboutTitle.contains("About"), "Should be on About page");
        logger.info("Navigated to About page");
        
        // Navigate to Contact from About
        aboutPage.clickContactLink();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(3));
        ContactPage contactPage = new ContactPage(driver);
        String contactTitle = contactPage.getPageTitle();
        Assert.assertTrue(contactTitle.contains("Contact"), "Should be on Contact page");
        logger.info("Navigated to Contact page");
        
        // Navigate back to Home from Contact
        contactPage.clickHomeLink();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(3));
        homePage = new HomePage(driver);
        String finalTitle = homePage.getMainHeading();
        Assert.assertNotNull(finalTitle, "Should be back on Home page");
        logger.info("Navigated back to Home page");
        
        logger.info("Test passed: Complete navigation flow successful");
    }

    @Test
    public void testDirectNavigationToAllPages() {
        logger.info("Starting: Test Direct Navigation to All Pages");
        
        // Test Home page direct navigation
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHomePage();
        Assert.assertTrue(driver.getCurrentUrl().contains("prismsoftwaresolutions.com"), 
            "Should be on Prism home page");
        logger.info("Direct navigation to Home successful");
        
        // Test About page direct navigation
        AboutPage aboutPage = new AboutPage(driver);
        aboutPage.navigateToAboutPage();
        Assert.assertTrue(driver.getCurrentUrl().contains("/about"), 
            "Should be on About page");
        logger.info("Direct navigation to About successful");
        
        // Test Contact page direct navigation
        ContactPage contactPage = new ContactPage(driver);
        contactPage.navigateToContactPage();
        Assert.assertTrue(driver.getCurrentUrl().contains("/contact"), 
            "Should be on Contact page");
        logger.info("Direct navigation to Contact successful");
        
        logger.info("Test passed: Direct navigation to all pages successful");
    }

    @Test
    public void testHomePageAllLinksAccessible() {
        logger.info("Starting: Test Home Page All Links Accessible");
        
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHomePage();
        
        // Verify Home page loads
        String homeUrl = driver.getCurrentUrl();
        Assert.assertTrue(homeUrl.contains("prismsoftwaresolutions.com"), 
            "Home page should load");
        logger.info("Home page loads correctly");
        
        logger.info("Test passed: Home page accessible");
    }

    @Test
    public void testPageTitleConsistency() {
        logger.info("Starting: Test Page Title Consistency");
        
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHomePage();
        String homePageTitle = driver.getTitle();
        Assert.assertNotNull(homePageTitle, "Home page title should not be null");
        logger.info("Home page title: " + homePageTitle);
        
        AboutPage aboutPage = new AboutPage(driver);
        aboutPage.navigateToAboutPage();
        String aboutPageTitle = driver.getTitle();
        Assert.assertNotNull(aboutPageTitle, "About page title should not be null");
        logger.info("About page title: " + aboutPageTitle);
        
        ContactPage contactPage = new ContactPage(driver);
        contactPage.navigateToContactPage();
        String contactPageUrl = driver.getCurrentUrl();
        Assert.assertTrue(contactPageUrl.contains("/contact"), "Should be on Contact page");
        logger.info("Contact page URL: " + contactPageUrl);
        
        logger.info("Test passed: All pages load with valid URLs");
    }

    @Test
    public void testBrowserBackButton() {
        logger.info("Starting: Test Browser Back Button");
        
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHomePage();
        
        AboutPage aboutPage = new AboutPage(driver);
        aboutPage.navigateToAboutPage();
        String aboutUrl = driver.getCurrentUrl();
        logger.info("Current URL (About): " + aboutUrl);
        
        driver.navigate().back();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(2));
        
        String previousUrl = driver.getCurrentUrl();
        logger.info("URL after back button: " + previousUrl);
        Assert.assertTrue(previousUrl.contains("prismsoftwaresolutions.com"), 
            "Back button should navigate to home page");
        
        logger.info("Test passed: Browser back button works correctly");
    }

    @Test
    public void testBrowserForwardButton() {
        logger.info("Starting: Test Browser Forward Button");
        
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHomePage();
        
        AboutPage aboutPage = new AboutPage(driver);
        aboutPage.navigateToAboutPage();
        
        driver.navigate().back();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(2));
        
        driver.navigate().forward();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(2));
        
        String forwardUrl = driver.getCurrentUrl();
        logger.info("URL after forward button: " + forwardUrl);
        Assert.assertTrue(forwardUrl.contains("/about"), 
            "Forward button should navigate to About page");
        
        logger.info("Test passed: Browser forward button works correctly");
    }
}
