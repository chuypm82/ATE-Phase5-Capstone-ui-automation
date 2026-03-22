package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    WebDriver driver;

	// Locators - Page Factory
    private By viewProdsLnk = By.xpath("//a[text()='View Products']");
    private By allProdsLabel = By.xpath("//li[text()='All Products']");
    private By firstProduct = By.xpath("//table//tr[1]/td[6]/a[2]");
    private By secondProduct = By.xpath("//table//tr[5]/td[6]/a[2]");
    private By loaderItem = By.xpath("//div[@class='se-pre-con']");

    public HomePage(WebDriver driver) {
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
        // wait for loader to be invisible
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loaderItem));
    }

    public void waitForViewProductsPageToBeDisplayed() {
        waitForElementToBeVisible(allProdsLabel);
    }

    // Safe click wrapper
    private void safeClick(By locator) {
        waitForLoaderToDisappear();
        waitForElementToBeClickable(locator);
        driver.findElement(locator).click();
    }

    // Actions
    public void addFirstProductToCart() {
        safeClick(firstProduct);
    }

    public void addSecondProductToCart() {
        safeClick(secondProduct);
    }

    public void addOneItemAndGoToCart() {
        navigatToViewProducts();
        addFirstProductToCart();
    }

    public void navigatToViewProducts() {
        safeClick(viewProdsLnk);
        waitForViewProductsPageToBeDisplayed();
    }

}