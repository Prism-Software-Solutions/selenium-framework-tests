package com.selenium.tests.pages.prism;

import com.selenium.tests.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object for Prism Software Solutions Contact Page
 */
public class ContactPage extends BasePage {

    @FindBy(xpath = "//h1[contains(text(), 'Contact Us')]")
    private WebElement pageTitle;

    @FindBy(xpath = "//h2[contains(text(), \"Let's Connect\")]")
    private WebElement connectSection;

    @FindBy(xpath = "//input[@placeholder]")
    private WebElement nameInput;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//textarea")
    private WebElement messageInput;

    @FindBy(xpath = "//button[contains(., 'Submit')]")
    private WebElement submitButton;

    @FindBy(xpath = "//a[contains(text(), 'Home')]")
    private WebElement homeLink;

    public ContactPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToContactPage() {
        navigateTo("https://prismsoftwaresolutions.com/contact");
        logger.info("Navigated to Contact page");
    }

    public String getPageTitle() {
        return getText(pageTitle);
    }

    public boolean isConnectSectionVisible() {
        return connectSection.isDisplayed();
    }

    public void enterName(String name) {
        sendKeys(nameInput, name);
        logger.info("Entered name: " + name);
    }

    public void enterEmail(String email) {
        sendKeys(emailInput, email);
        logger.info("Entered email: " + email);
    }

    public void enterMessage(String message) {
        sendKeys(messageInput, message);
        logger.info("Entered message: " + message);
    }

    public void clickSubmitButton() {
        click(submitButton);
        logger.info("Clicked Submit button");
    }

    public void submitContactForm(String name, String email, String message) {
        enterName(name);
        enterEmail(email);
        enterMessage(message);
        clickSubmitButton();
        logger.info("Contact form submitted with name: " + name);
    }

    public void clickHomeLink() {
        click(homeLink);
        logger.info("Clicked Home link from Contact page");
    }

    public boolean isNameInputDisplayed() {
        return nameInput.isDisplayed();
    }

    public boolean isEmailInputDisplayed() {
        return emailInput.isDisplayed();
    }

    public boolean isSubmitButtonDisplayed() {
        return submitButton.isDisplayed();
    }
}
