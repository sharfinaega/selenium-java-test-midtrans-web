package TestAutomation.pageObjects;

import TestAutomation.helpers.*;
import com.beust.jcommander.Parameterized;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.openqa.selenium.JavascriptExecutor;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PaymentDetailPopUp extends BasePage {

    private WebElement totalAmountText;

    private WebElement creditCardList;

    private WebElement virtualAccountList;

    private WebElement virtualAccountPayment;

    private WebElement validateExpectedVaNumber;

    private WebElement validateActualVaNumber;

    private WebElement copyVaNumber;

    private WebElement inputVirtualAccountNumber;

    private WebElement inquireButton;

    private WebElement payButton;

    private WebElement validateSuccessPayment;

    private WebElement creditCardInput;
    private WebElement creditCardExpiryInput;
    private WebElement creditCardCvvInput;

    Config testConfig;
    private WebDriver driver;
    private Parameterized tabs;


    public PaymentDetailPopUp(Config testConfig) {
        PageFactory.initElements(testConfig.driver, this);
        this.testConfig = testConfig;
    }

    public void verifyTotalAmount(String input) {
        this.totalAmountText = getIframeElement(testConfig, How.css, ".header-amount");
        WaitHelper.waitForElementToBeDisplayed(testConfig, totalAmountText, "waiting for total amount text");
        AssertHelper.compareContains(testConfig, "Total Amount on Payment Detail is correct", input, CommonUtilities.removeSpecialCharacter(getText(testConfig, totalAmountText, "total amount text is being collected")));
    }

    public void clickCreditCardList() {
        // Switch ke iframe snap-midtrans dulu (sesuai kode kamu ada method untuk ini)
        switchToSnapIframe();

        // Setelah switch, cari element
        this.creditCardList = getPageElement(testConfig, How.xPath, "//a[@href='#/credit-card']");
        WaitHelper.waitForElementToBeClickable(testConfig, creditCardList, "waiting for credit card list");
        click(testConfig, creditCardList, "creditCardList is being clicked here");

    }

    public void switchToSnapIframe() {
        try {
            testConfig.driver.switchTo().defaultContent();
            WebDriverWait wait = new WebDriverWait(testConfig.driver,15);
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("snap-midtrans")));
            testConfig.logComment("✅ Berhasil switch ke iframe snap-midtrans");
        } catch (Exception e) {
            testConfig.logComment("❌ Gagal switch ke iframe snap-midtrans: " + e.getMessage());
            throw e;
        }
    }

    public void inputCreditCardNumber(String input) {
        WebElement element = getIframeElement(testConfig, How.id, "snap-midtrans");
        testConfig.driver.switchTo().frame(element);
        WebElement otherElement = getPageElement(testConfig,How.xPath,"//input[@autocomplete= 'cc-number']");
        WaitHelper.waitForElementToBeClickable(testConfig, otherElement, "waiting for credit card input");
        enterData(testConfig, otherElement, input, "creditCardInput is being input");
    }

    public void inputCreditCardExpiry(String input) {
        this.creditCardExpiryInput = getIframeElement(testConfig, How.id, "card-expiry");
        WaitHelper.waitForElementToBeDisplayed(testConfig, creditCardExpiryInput, "waiting for credit card expiry input");
        enterData(testConfig, creditCardExpiryInput, input, "creditCardInputExpiry is being input");
    }

    public void inputCreditCardCvv(String input) {
        this.creditCardCvvInput = getIframeElement(testConfig, How.id, "card-cvv");
        WaitHelper.waitForElementToBeClickable(testConfig, creditCardCvvInput, "waiting for credit card expiry input");
        enterData(testConfig, creditCardCvvInput, input, "creditCardInputExpiry is being input");
    }

    public void clickVirtualAccountList(){
        switchToSnapIframe();

        this.virtualAccountList = getPageElement(testConfig, How.id, "bank_transfer");
        WaitHelper.waitForElementToBeClickable(testConfig, virtualAccountList, "waiting for virtual account list");
        click(testConfig, virtualAccountList, "virtualAccountList is being clicked here");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String paymentUsingVirtualAccount() {
        switchToSnapIframe();
        this.virtualAccountPayment = getPageElement(testConfig, How.xPath, "//a[@href='#/bank-transfer/bca-va']");
        if (virtualAccountPayment == null) {
            System.out.println("❌ Element for virtualAccountPayment not found");
            throw new RuntimeException("virtualAccountPayment is null");
        }

        WaitHelper.waitForElementToBeClickable(testConfig, virtualAccountPayment, "waiting for virtual account");
        click(testConfig, virtualAccountPayment, "virtualAccountPayment is being clicked here");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        this.validateActualVaNumber = getPageElement(testConfig, How.id, "vaField");
        String actualVaNumber = validateActualVaNumber.getText();

        String actualVa = actualVaNumber.substring(5);

        this.copyVaNumber = getPageElement(testConfig, How.css, "div.float-right.clickable.copy");
        WaitHelper.waitForElementToBeClickable(testConfig, copyVaNumber, "waiting for Copy button");
        click(testConfig, copyVaNumber, "Copy button clicked");

        return actualVa;
    }

    public void openUrlPayment(WebDriver driver, String url) {
        ((JavascriptExecutor) driver).executeScript("window.open('" + url + "', '_blank');");

        // Switch to the new tab
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1)); // Switch to the last opened tab
    }
    public void paymentVaInSandboxWeb(String vaNumberFromWeb) {

        this.inputVirtualAccountNumber = getPageElement(testConfig, How.id, "inputMerchantId");
        WaitHelper.waitForElementToBeClickable(testConfig, inputVirtualAccountNumber, "waiting for va number input");
        click(testConfig, inputVirtualAccountNumber, "inputVirtualAccountNumber is being clicked here");

        /*Actions actions = new Actions(testConfig.driver);
        actions.moveToElement(inputVirtualAccountNumber).click().sendKeys(Keys.CONTROL,"v").build().perform();*/
        inputVirtualAccountNumber.sendKeys(Keys.COMMAND,"v");

        this.inquireButton = getPageElement(testConfig, How.css, "input[type='submit'][value='Inquire']");
        WaitHelper.waitForElementToBeClickable(testConfig, inquireButton, "waiting for Inquire button");
        click(testConfig, inquireButton, "Inquire button is being clicked");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        this.validateExpectedVaNumber = getPageElement(testConfig, How.css, "p.markdown-p.inputed-form");

        String expectedVaNumber = validateExpectedVaNumber != null ? validateExpectedVaNumber.getText() : null; // Example: "07743446933629139524143"
        System.out.println("ini nomer va expected: " + expectedVaNumber);

        Assert.assertEquals(vaNumberFromWeb, expectedVaNumber);

        this.payButton = getPageElement(testConfig, How.css, "input[type='submit'][value='Pay']");
        WaitHelper.waitForElementToBeClickable(testConfig, payButton, "waiting for pay button");
        click(testConfig, payButton, "Pay button is being clicked");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void backToWebUrl() {

        Object[] windowHandles = testConfig.driver.getWindowHandles().toArray();
        testConfig.driver.switchTo().window((String) windowHandles[0]);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void checkStatusPayment() {
        switchToSnapIframe();

        this.validateSuccessPayment = getPageElement(testConfig, How.css, "button.btn.full.primary.btn-theme");
        WaitHelper.waitForElementToBeClickable(testConfig, validateSuccessPayment, "waiting for Check Status button");
        click(testConfig, validateSuccessPayment, "Check Status button clicked");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
