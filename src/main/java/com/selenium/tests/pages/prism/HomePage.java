package com.selenium.tests.pages.prism;

import com.selenium.tests.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object for Prism Software Solutions Home Page
 */
public class HomePage extends BasePage {

    @FindBy(xpath = "//h1[contains(text(), 'Building Cutting-Edge Software')]")
    private WebElement mainHeading;

    @FindBy(xpath = "//h2[contains(text(), 'Why Choose Prism')]")
    private WebElement whyChoosePrismSection;

    @FindBy(xpath = "//h2[contains(text(), 'Our Latest Products')]")
    private WebElement ourProductsSection;

    @FindBy(xpath = "//a[contains(text(), 'Learn More')]")
    private WebElement learnMoreLink;

    @FindBy(xpath = "//a[contains(text(), 'Contact Us')]")
    private WebElement contactUsLink;

    @FindBy(linkText = "About")
    private WebElement aboutLink;

    @FindBy(xpath = "//img[@alt]")
    private WebElement prismLogo;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToHomePage() {
        navigateTo("https://prismsoftwaresolutions.com");
        logger.info("Navigated to Prism home page");
    }

    public String getMainHeading() {
        return getText(mainHeading);
    }

    public boolean isWhyChoosePrismSectionVisible() {
        return whyChoosePrismSection.isDisplayed();
    }

    public boolean isOurProductsSectionVisible() {
        return ourProductsSection.isDisplayed();
    }

    public void clickLearnMore() {
        click(learnMoreLink);
        logger.info("Clicked Learn More link");
    }

    public void clickContactUs() {
        click(contactUsLink);
        logger.info("Clicked Contact Us link");
    }

    public void clickAboutLink() {
        click(aboutLink);
        logger.info("Clicked About link");
    }

    public boolean isPrismLogoDisplayed() {
        return prismLogo.isDisplayed();
    }

    public void scrollToWhyChoosePrism() {
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", whyChoosePrismSection);
        logger.info("Scrolled to Why Choose Prism section");
    }

    public void scrollToProducts() {
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ourProductsSection);
        logger.info("Scrolled to Our Products section");
    }
}
