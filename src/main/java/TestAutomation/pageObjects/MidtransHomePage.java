package TestAutomation.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import TestAutomation.helpers.BasePage;
import TestAutomation.helpers.Config;
import TestAutomation.helpers.WaitHelper;

public class MidtransHomePage extends BasePage {

    @FindBy(css = ".btn.buy")
    private WebElement buyButton;

    @FindBy(css = ".btn.btn-signup")
    private WebElement signUpButton;

    @FindBy(css = ".glyphicon.glyphicon-menu-left")
    private WebElement glyphiconLeftButton;

    @FindBy(css = ".glyphicon.glyphicon-menu-right")
    private WebElement glyphiconRightButton;

    Config testConfig;

    public MidtransHomePage(Config testConfig) {
        PageFactory.initElements(testConfig.driver, this);
        this.testConfig = testConfig;
    }

    public void clickBuyButton() {
        WaitHelper.waitForElementToBeClickable(testConfig, buyButton, "waiting for buy button");
        click(testConfig, buyButton, "buyButton is being clicked here");
    }

    public void clickSignUpButton() {
        WaitHelper.waitForElementToBeClickable(testConfig, signUpButton, "waiting for sign up button");
        click(testConfig, signUpButton, "signUpButton is being clicked here");
    }

    public void clickGlyphiconRightButton() {
        WaitHelper.waitForElementToBeClickable(testConfig, glyphiconRightButton, "waiting for glyphicon right button");
        click(testConfig, glyphiconRightButton, "glyphiconRightButton is being clicked here");
    }

    public void clickGlyphiconLeftButton() {
        WaitHelper.waitForElementToBeClickable(testConfig, glyphiconLeftButton, "waiting for glyphicon left button");
        click(testConfig, glyphiconLeftButton, "glyphiconLeftButton is being clicked here");
    }

}