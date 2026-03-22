package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupPage {

    WebDriver driver;

    // Locators
    private By signupLnk = By.xpath("//a[text()='Sign Up']");
    private By firstName = By.xpath("//input[@id='firstName']");
    private By lastName = By.xpath("//input[@id='lastName']");
    private By email = By.xpath("//input[@id='email']");
    private By phoneNumber = By.xpath("//input[@id='contactNumber']");
    private By password = By.xpath("//input[@id='password']");
    private By confPassword = By.xpath("//input[@id='confirmPassword']");
    private By nextBillingBtn = By.xpath("//button[@type='submit']");
    private By addLine1 = By.xpath("//input[@id='addressLineOne']");
    private By addLine2 = By.xpath("//input[@id='addressLineTwo']");
    private By city = By.xpath("//input[@id='city']");
    private By zipcode = By.xpath("//input[@id='postalCode']");
    private By state = By.xpath("//input[@id='state']");
    private By country = By.xpath("//input[@id='country']");
    private By nextConfirmBtn = By.xpath("//button[@name='_eventId_confirm']");
    private By confirmBtn = By.xpath("//a[text()='Confirm']");
    private By successMsg = By.xpath("//h1[text()='Welcome!']");
    private By loaderItem = By.xpath("//div[@class='se-pre-con']");

    public SignupPage(WebDriver driver) {
        this.driver = driver;
    }

    // Waits
    public void waitForElementToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForLoaderToDisappear() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loaderItem));
    }

    // Safe click wrapper

    private void safeClick(By locator) {
        waitForLoaderToDisappear();
        waitForElementToBeClickable(locator);
        driver.findElement(locator).click();
    }

    // Actions
    public void clickOnSignupLink() {
        safeClick(signupLnk);
    }

    public void enterFirstName(String fName) {
        driver.findElement(firstName).sendKeys(fName);
    }

    public void enterLastName(String lName) {
        driver.findElement(lastName).sendKeys(lName);
    }

    public void enterEmail(String userEmail) {
        driver.findElement(email).sendKeys(userEmail);
    }

    public void enterPhone(String userPhone) {
        driver.findElement(phoneNumber).sendKeys(userPhone);
    }

    public void enterPassword(String userPassword) {
        driver.findElement(password).sendKeys(userPassword);
    }

    public void enterConfPassword(String userConfPassword) {
        driver.findElement(confPassword).sendKeys(userConfPassword);
    }

    public void clickBillingButton() {
        safeClick(nextBillingBtn);
    }

    public void enterAddrLine1(String uAddressL1) {
        driver.findElement(addLine1).sendKeys(uAddressL1);
    }

    public void enterAddrLine2(String uAddressL2) {
        driver.findElement(addLine2).sendKeys(uAddressL2);
    }

    public void enterCity(String uCity) {
        driver.findElement(city).sendKeys(uCity);
    }

    public void enterZip(String uZip) {
        driver.findElement(zipcode).sendKeys(uZip);
    }

    public void enterState(String uState) {
        driver.findElement(state).sendKeys(uState);
    }

    public void enterCountry(String uCountry) {
        driver.findElement(country).sendKeys(uCountry);
    }

    public void clickNextConfirmButton() {
        safeClick(nextConfirmBtn);
    }

    public void clickConfirmButton() {
        safeClick(confirmBtn);
    }

    public String getSuccessMessage() {
        waitForElementToBeVisible(successMsg);
        return driver.findElement(successMsg).getText();
    }

    public void signupPage1(String fName, String lName, String userEmail, String userPhone, String userPassword, String userConfPassword) {
        clickOnSignupLink();
        enterFirstName(fName);
        enterLastName(lName);
        enterEmail(userEmail);
        enterPhone(userPhone);
        enterPassword(userPassword);
        enterConfPassword(userConfPassword);
        clickBillingButton();
    }

    public void signupPage2(String uAddressL1, String uAddressL2, String uCity, String uZip, String uState, String uCountry) {
        enterAddrLine1(uAddressL1);
        enterAddrLine2(uAddressL2);
        enterCity(uCity);
        enterZip(uZip);
        enterState(uState);
        enterCountry(uCountry);
        clickNextConfirmButton();
        clickConfirmButton();
    }
}