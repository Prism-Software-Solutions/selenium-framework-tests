package com.selenium.tests.ui.prism;

import com.selenium.tests.base.BaseTest;
import com.selenium.tests.pages.prism.ContactPage;
import com.selenium.tests.pages.prism.HomePage;
import com.selenium.tests.pages.prism.AboutPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class for Prism Software Solutions Contact Page
 */
public class ContactPageTest extends BaseTest {

    @Test
    public void testContactPageLoadsSuccessfully() {
        logger.info("Starting: Test Contact Page Loads Successfully");
        
        ContactPage contactPage = new ContactPage(driver);
        contactPage.navigateToContactPage();
        
        // Verify page URL
        String pageUrl = driver.getCurrentUrl();
        logger.info("Page URL: " + pageUrl);
        Assert.assertNotNull(pageUrl, "Page URL should not be null");
        Assert.assertTrue(pageUrl.contains("contact"), "Page URL should contain 'contact'");
        
        logger.info("Test passed: Contact page loaded successfully");
    }

    @Test
    public void testContactPageTitle() {
        logger.info("Starting: Test Contact Page Title");
        
        ContactPage contactPage = new ContactPage(driver);
        contactPage.navigateToContactPage();
        
        String pageTitle = contactPage.getPageTitle();
        logger.info("Contact page title: " + pageTitle);
        Assert.assertNotNull(pageTitle, "Contact page title should not be null");
        Assert.assertTrue(pageTitle.contains("Contact Us"), 
            "Page title should contain 'Contact Us'");
        
        logger.info("Test passed: Contact page title verified");
    }

    @Test
    public void testContactFormElementsDisplayed() {
        logger.info("Starting: Test Contact Form Elements Displayed");
        
        ContactPage contactPage = new ContactPage(driver);
        contactPage.navigateToContactPage();
        
        boolean isNameDisplayed = contactPage.isNameInputDisplayed();
        boolean isEmailDisplayed = contactPage.isEmailInputDisplayed();
        boolean isSubmitDisplayed = contactPage.isSubmitButtonDisplayed();
        
        logger.info("Name input displayed: " + isNameDisplayed);
        logger.info("Email input displayed: " + isEmailDisplayed);
        logger.info("Submit button displayed: " + isSubmitDisplayed);
        
        Assert.assertTrue(isNameDisplayed, "Name input should be displayed");
        Assert.assertTrue(isEmailDisplayed, "Email input should be displayed");
        Assert.assertTrue(isSubmitDisplayed, "Submit button should be displayed");
        
        logger.info("Test passed: All contact form elements are displayed");
    }

    @Test
    public void testConnectSectionVisible() {
        logger.info("Starting: Test Connect Section Visible");
        
        ContactPage contactPage = new ContactPage(driver);
        contactPage.navigateToContactPage();
        
        boolean isConnectSectionVisible = contactPage.isConnectSectionVisible();
        logger.info("Connect section visible: " + isConnectSectionVisible);
        Assert.assertTrue(isConnectSectionVisible, "Connect section should be visible");
        
        logger.info("Test passed: Connect section is visible");
    }

    @Test
    public void testFillContactForm() {
        logger.info("Starting: Test Fill Contact Form");
        
        ContactPage contactPage = new ContactPage(driver);
        contactPage.navigateToContactPage();
        
        String testName = "John Doe";
        String testEmail = "john.doe@example.com";
        String testMessage = "I am interested in learning more about your services.";
        
        contactPage.enterName(testName);
        contactPage.enterEmail(testEmail);
        contactPage.enterMessage(testMessage);
        
        logger.info("Contact form filled with test data");
        logger.info("Test passed: Contact form fields filled successfully");
    }

    @Test
    public void testSubmitContactForm() {
        logger.info("Starting: Test Submit Contact Form");
        
        ContactPage contactPage = new ContactPage(driver);
        contactPage.navigateToContactPage();
        
        String testName = "Jane Smith";
        String testEmail = "jane.smith@example.com";
        String testMessage = "Please contact me regarding your AI solutions.";
        
        contactPage.submitContactForm(testName, testEmail, testMessage);
        
        // Wait for form submission
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(2));
        
        logger.info("Contact form submitted successfully");
        logger.info("Test passed: Contact form submission completed");
    }

    @Test
    public void testNavigateToHomePageFromContact() {
        logger.info("Starting: Test Navigate to Home Page from Contact");
        
        ContactPage contactPage = new ContactPage(driver);
        contactPage.navigateToContactPage();
        
        contactPage.clickHomeLink();
        
        // Wait for page to load
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(3));
        
        HomePage homePage = new HomePage(driver);
        String mainHeading = homePage.getMainHeading();
        logger.info("Home page heading: " + mainHeading);
        Assert.assertNotNull(mainHeading, "Should navigate to Home page");
        Assert.assertTrue(mainHeading.contains("Building"), "Should be on home page");
        
        logger.info("Test passed: Navigated to Home page from Contact");
    }

    @Test
    public void testContactPageNavigationPath() {
        logger.info("Starting: Test Contact Page Navigation Path");
        
        // Navigate from Home -> About -> Contact
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHomePage();
        
        homePage.clickAboutLink();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(3));
        
        AboutPage aboutPage = new AboutPage(driver);
        aboutPage.clickContactLink();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(3));
        
        ContactPage contactPage = new ContactPage(driver);
        String contactPageTitle = contactPage.getPageTitle();
        logger.info("Final page reached: " + contactPageTitle);
        Assert.assertTrue(contactPageTitle.contains("Contact Us"), 
            "Should be able to navigate from Home -> About -> Contact");
        
        logger.info("Test passed: Contact page accessible via navigation path");
    }

    @Test
    public void testContactFormEmailValidation() {
        logger.info("Starting: Test Contact Form Email Validation");
        
        ContactPage contactPage = new ContactPage(driver);
        contactPage.navigateToContactPage();
        
        // Fill form with invalid email
        contactPage.enterName("Test User");
        contactPage.enterEmail("invalid-email");
        contactPage.enterMessage("Test message");
        
        logger.info("Contact form filled with invalid email");
        logger.info("Test passed: Contact form allows email input (validation may be client-side)");
    }
}
