package TestAutomation;

import TestAutomation.helpers.Browser;
import TestAutomation.helpers.Config;
import TestAutomation.helpers.TestBase;
import TestAutomation.helpers.WaitHelper;
import TestAutomation.pageObjects.MidtransHomePage;
import TestAutomation.pageObjects.PaymentDetailPopUp;
import TestAutomation.pageObjects.ShoppingCartSideBar;
import org.openqa.selenium.By;
import org.testng.annotations.Test;


public class TestCheckout extends TestBase {
    private String totalAmount;

    @Test(dataProvider = "getTestConfig", description = "This testcase is verifying successful checkout click to Midtrans demo site")
    public void testCheckout(Config testConfig) {
        Browser.openBrowserAndNavigateToUrl(testConfig, "https://demo.midtrans.com/");

        MidtransHomePage midtransHomePage = new MidtransHomePage(testConfig);
        ShoppingCartSideBar shoppingCartSideBar = new ShoppingCartSideBar(testConfig);
        PaymentDetailPopUp paymentDetailPopUp = new PaymentDetailPopUp(testConfig);

        midtransHomePage.clickBuyButton();
        shoppingCartSideBar.verifyCartSideBarShow();
//        Browser.takeScreenshot(testConfig);

        totalAmount = shoppingCartSideBar.getTotalAmountText();
        shoppingCartSideBar.clickCheckoutButton();

        WaitHelper.waitForElementToBeVisible(testConfig, By.id("snap-midtrans"), 15);
        paymentDetailPopUp.switchToSnapIframe();

        paymentDetailPopUp.clickCreditCardList();
        paymentDetailPopUp.inputCreditCardNumber("4811111111111114");
        paymentDetailPopUp.inputCreditCardExpiry("11/25");
        paymentDetailPopUp.inputCreditCardCvv("123");


        Browser.takeScreenshot(testConfig);
    }
}
