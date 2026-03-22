package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;

	@BeforeMethod
	public void setup() {

		WebDriverManager.chromedriver().setup();
		// Add steps to open the Chrome browser and maximize the window
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless=new");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--disable-gpu");
		options.addArguments("--window-size=1920,1080");
		driver = new ChromeDriver(options);
		// maximize the window
		driver.manage().window().maximize();
		// Add steps to open the application URL in the browser
		driver.get("http://localhost:8080/medicare2/");
		// Add steps to code to delete cookies
		driver.manage().deleteAllCookies();
		// Add Selenium commands in the PageLoadTimeout code
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
	}

	@AfterMethod
	public void teardown() {
		 driver.quit();
	}
}
