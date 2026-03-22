package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

    WebDriver driver;
 
	// Locators - Page Factory
	private By prodTable = By.xpath("//table//th[1][text()='Product']");
	private By cartItems = By.xpath("//table//h4");
	private By checkoutBtn = By.xpath("//a[text()='Checkout ']");
	private By selectAddressBtn = By.xpath("//a[text()='Select']");
	private By payBtn = By.xpath("//a[text()='Pay']");
	private By successMsg = By.xpath("//h3[text()='Your Order is Confirmed!!']");
	private By loaderItem = By.xpath("//div[@class='se-pre-con']");
	private By continueShoppingBtn = By.xpath("//a[text()=' Continue Shopping']");

    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void waitForElementToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    
    public void waitForElementToBeClicable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    public void waitForLoaderToDisappear() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loaderItem));
    }
       
    public void waitForCartPageToBeDisplayed() {
    	waitForLoaderToDisappear();
    	waitForElementToBeVisible(prodTable);
    }
    
    
    public int getCartItemCount() {    	
    	waitForCartPageToBeDisplayed();
        return driver.findElements(cartItems).size();
    }

    public void clickCheckout() throws InterruptedException {
    	waitForElementToBeVisible(prodTable);
        Thread.sleep(1000);
    	driver.findElement(checkoutBtn).click();
        waitForElementToBeVisible(selectAddressBtn);
        Thread.sleep(1000);
        driver.findElement(selectAddressBtn).click();
        waitForElementToBeVisible(payBtn);
        Thread.sleep(1000);
        driver.findElement(payBtn).click();
    }

    public String getOrderSuccessMessage() throws InterruptedException {     
    	waitForElementToBeVisible(successMsg);
        Thread.sleep(1000);
	    String text = driver.findElement(successMsg).getText();
	    System.out.println("Success Message: " + text);
	    return text; 
    }
    
    public void continueShoping() {
        waitForLoaderToDisappear();
        waitForElementToBeClicable(continueShoppingBtn);
        driver.findElement(continueShoppingBtn).click();
    }
}