package com.selenium.tests.pages.prism;

import com.selenium.tests.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object for Prism Software Solutions About Page
 */
public class AboutPage extends BasePage {

    @FindBy(xpath = "//h1[contains(text(), 'About Prism')]")
    private WebElement pageTitle;

    @FindBy(xpath = "//h3[contains(text(), 'Our Mission')]")
    private WebElement missionSection;

    @FindBy(xpath = "//h3[contains(text(), 'Our Vision')]")
    private WebElement visionSection;

    @FindBy(xpath = "//h2[contains(text(), 'Smart Operations')]")
    private WebElement smartOperationsSection;

    @FindBy(xpath = "//a[contains(text(), 'Home')]")
    private WebElement homeLink;

    @FindBy(xpath = "//a[contains(text(), 'Contact')]")
    private WebElement contactLink;

    public AboutPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToAboutPage() {
        navigateTo("https://prismsoftwaresolutions.com/about");
        logger.info("Navigated to About page");
    }

    public String getPageTitle() {
        return getText(pageTitle);
    }

    public String getMissionText() {
        return getText(missionSection);
    }

    public String getVisionText() {
        return getText(visionSection);
    }

    public boolean isMissionSectionVisible() {
        return missionSection.isDisplayed();
    }

    public boolean isVisionSectionVisible() {
        return visionSection.isDisplayed();
    }

    public boolean isSmartOperationsSectionVisible() {
        return smartOperationsSection.isDisplayed();
    }

    public void clickHomeLink() {
        click(homeLink);
        logger.info("Clicked Home link from About page");
    }

    public void clickContactLink() {
        click(contactLink);
        logger.info("Clicked Contact link from About page");
    }

    public void scrollToMission() {
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", missionSection);
        logger.info("Scrolled to Mission section");
    }

    public void scrollToVision() {
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", visionSection);
        logger.info("Scrolled to Vision section");
    }
}
