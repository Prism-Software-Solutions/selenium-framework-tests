package com.selenium.tests.ui.prism;

import com.selenium.tests.base.BaseTest;
import com.selenium.tests.pages.prism.AboutPage;
import com.selenium.tests.pages.prism.HomePage;
import com.selenium.tests.pages.prism.ContactPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class for Prism Software Solutions About Page
 */
public class AboutPageTest extends BaseTest {

    @Test
    public void testAboutPageLoadsSuccessfully() {
        logger.info("Starting: Test About Page Loads Successfully");
        
        AboutPage aboutPage = new AboutPage(driver);
        aboutPage.navigateToAboutPage();
        
        // Verify page title
        String pageTitle = driver.getTitle();
        logger.info("Page title: " + pageTitle);
        Assert.assertNotNull(pageTitle, "Page title should not be null");
        Assert.assertTrue(pageTitle.contains("About"), "Page title should contain 'About'");
        
        logger.info("Test passed: About page loaded successfully");
    }

    @Test
    public void testAboutPageTitle() {
        logger.info("Starting: Test About Page Title");
        
        AboutPage aboutPage = new AboutPage(driver);
        aboutPage.navigateToAboutPage();
        
        String pageTitle = aboutPage.getPageTitle();
        logger.info("About page title: " + pageTitle);
        Assert.assertNotNull(pageTitle, "About page title should not be null");
        Assert.assertTrue(pageTitle.contains("About Prism"), 
            "Page title should contain 'About Prism'");
        
        logger.info("Test passed: About page title verified");
    }

    @Test
    public void testMissionSectionVisible() {
        logger.info("Starting: Test Mission Section Is Visible");
        
        AboutPage aboutPage = new AboutPage(driver);
        aboutPage.navigateToAboutPage();
        
        aboutPage.scrollToMission();
        boolean isMissionVisible = aboutPage.isMissionSectionVisible();
        logger.info("Mission section visible: " + isMissionVisible);
        Assert.assertTrue(isMissionVisible, "Mission section should be visible");
        
        logger.info("Test passed: Mission section is visible");
    }

    @Test
    public void testVisionSectionVisible() {
        logger.info("Starting: Test Vision Section Is Visible");
        
        AboutPage aboutPage = new AboutPage(driver);
        aboutPage.navigateToAboutPage();
        
        aboutPage.scrollToVision();
        boolean isVisionVisible = aboutPage.isVisionSectionVisible();
        logger.info("Vision section visible: " + isVisionVisible);
        Assert.assertTrue(isVisionVisible, "Vision section should be visible");
        
        logger.info("Test passed: Vision section is visible");
    }

    @Test
    public void testSmartOperationsSectionVisible() {
        logger.info("Starting: Test Smart Operations Section Is Visible");
        
        AboutPage aboutPage = new AboutPage(driver);
        aboutPage.navigateToAboutPage();
        
        boolean isSmartOpsVisible = aboutPage.isSmartOperationsSectionVisible();
        logger.info("Smart Operations section visible: " + isSmartOpsVisible);
        Assert.assertTrue(isSmartOpsVisible, "Smart Operations section should be visible");
        
        logger.info("Test passed: Smart Operations section is visible");
    }

    @Test
    public void testNavigateToHomePageFromAbout() {
        logger.info("Starting: Test Navigate to Home Page from About");
        
        AboutPage aboutPage = new AboutPage(driver);
        aboutPage.navigateToAboutPage();
        
        aboutPage.clickHomeLink();
        
        // Wait for page to load
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(3));
        
        HomePage homePage = new HomePage(driver);
        String mainHeading = homePage.getMainHeading();
        logger.info("Home page heading: " + mainHeading);
        Assert.assertNotNull(mainHeading, "Should navigate to Home page");
        Assert.assertTrue(mainHeading.contains("Building"), "Should be on home page");
        
        logger.info("Test passed: Navigated to Home page from About");
    }

    @Test
    public void testNavigateToContactPageFromAbout() {
        logger.info("Starting: Test Navigate to Contact Page from About");
        
        AboutPage aboutPage = new AboutPage(driver);
        aboutPage.navigateToAboutPage();
        
        aboutPage.clickContactLink();
        
        // Wait for page to load
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(3));
        
        ContactPage contactPage = new ContactPage(driver);
        boolean isFormVisible = contactPage.isNameInputDisplayed();
        logger.info("Contact form visible: " + isFormVisible);
        Assert.assertTrue(isFormVisible, "Should navigate to Contact page");
        
        logger.info("Test passed: Navigated to Contact page from About");
    }

    @Test
    public void testMissionContent() {
        logger.info("Starting: Test Mission Content");
        
        AboutPage aboutPage = new AboutPage(driver);
        aboutPage.navigateToAboutPage();
        
        aboutPage.scrollToMission();
        String missionText = aboutPage.getMissionText();
        logger.info("Mission text: " + missionText);
        Assert.assertNotNull(missionText, "Mission text should not be null");
        Assert.assertFalse(missionText.isEmpty(), "Mission text should not be empty");
        
        logger.info("Test passed: Mission content verified");
    }

    @Test
    public void testVisionContent() {
        logger.info("Starting: Test Vision Content");
        
        AboutPage aboutPage = new AboutPage(driver);
        aboutPage.navigateToAboutPage();
        
        aboutPage.scrollToVision();
        String visionText = aboutPage.getVisionText();
        logger.info("Vision text: " + visionText);
        Assert.assertNotNull(visionText, "Vision text should not be null");
        Assert.assertFalse(visionText.isEmpty(), "Vision text should not be empty");
        
        logger.info("Test passed: Vision content verified");
    }
}
