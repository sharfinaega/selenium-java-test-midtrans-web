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

public class TestCheckoutPaymentVA extends TestBase {
    private String vaNumber;
    private String totalAmount;

    @Test(dataProvider = "getTestConfig", description = "This testcase is verifying successful checkout click to Midtrans demo site")
    public void testCheckout(Config testConfig) {
        Browser.openBrowserAndNavigateToUrl(testConfig, "https://demo.midtrans.com/");
//        testConfig.driver.manage().window().maximize();

        MidtransHomePage midtransHomePage = new MidtransHomePage(testConfig);
        ShoppingCartSideBar shoppingCartSideBar = new ShoppingCartSideBar(testConfig);
        PaymentDetailPopUp paymentDetailPopUp = new PaymentDetailPopUp(testConfig);

        midtransHomePage.clickBuyButton();
        shoppingCartSideBar.verifyCartSideBarShow();
        Browser.takeScreenshot(testConfig);

        totalAmount = shoppingCartSideBar.getTotalAmountText();
        shoppingCartSideBar.clickCheckoutButton();

        WaitHelper.waitForElementToBeVisible(testConfig, By.id("snap-midtrans"), 15);
//        paymentDetailPopUp.switchToSnapIframe();

        paymentDetailPopUp.clickVirtualAccountList();
        vaNumber = paymentDetailPopUp.paymentUsingVirtualAccount();
        paymentDetailPopUp.openUrlPayment(testConfig.driver, "https://simulator.sandbox.midtrans.com/bca/va/index");
        paymentDetailPopUp.paymentVaInSandboxWeb(vaNumber);
        paymentDetailPopUp.backToWebUrl();
        paymentDetailPopUp.checkStatusPayment();
        Browser.takeScreenshot(testConfig);

    }
}