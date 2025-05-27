package TestAutomation.pageObjects;

import TestAutomation.helpers.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartSideBar extends BasePage {

    @FindBy(css = ".cart-content.buying")
    private WebElement cartSideBar;

    @FindBy(css = "td.amount")
    private WebElement totalAmountText;

    @FindBy(css = ".cart-checkout")
    private WebElement checkoutButton;

    Config testConfig;

    public ShoppingCartSideBar(Config testConfig) {
        PageFactory.initElements(testConfig.driver, this);
        this.testConfig = testConfig;
    }

    public void verifyCartSideBarShow() {
        AssertHelper.compareTrue(testConfig, "Side Bar Show", IsElementDisplayed(testConfig, cartSideBar));
    }

    public String getTotalAmountText() {
        return CommonUtilities.removeSpecialCharacter(getText(testConfig, totalAmountText, "total amount text is being collected"));
    }

    public void clickCheckoutButton() {
        WaitHelper.waitForElementToBeClickable(testConfig, checkoutButton, "waiting for checkout button");
        click(testConfig, checkoutButton, "checkoutButton is being clicked here");
    }



}
