package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	WebDriver driver;

	// Locators - Page Factory
	private By loginLnk = By.xpath("//a[text()='Login']");
	private By username = By.xpath("//input[@id='username']");
	private By password = By.xpath("//input[@id='password']");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By errorMsg = By.xpath("//div[contains(@class,'alert-danger')]");

	// Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	// Actions
	public void clickOnLoginLink() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// Wait for loader to disappear
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.se-pre-con")));
		wait.until(ExpectedConditions.elementToBeClickable(loginLnk));
		driver.findElement(loginLnk).click();
	}

	public void login(String user, String pass) throws InterruptedException {
		clickOnLoginLink();
		enterUsername(user);
		enterPassword(pass);
		Thread.sleep(1000);
		clickLogin();

	}

	public void enterUsername(String user) {
		driver.findElement(username).sendKeys(user);
	}

	public void enterPassword(String pass) {
		driver.findElement(password).sendKeys(pass);
	}

	public void clickLogin() {
	    driver.findElement(loginBtn).click();
	}
	public String getErrorMessage() throws InterruptedException {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.presenceOfElementLocated(errorMsg));
	    String text = driver.findElement(errorMsg).getText();
	    System.out.println("Captured Error: " + text);
		Thread.sleep(1000);
	    return text;
	}
}
