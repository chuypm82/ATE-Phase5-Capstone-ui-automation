package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;

	@BeforeMethod
	public void setup() {

		WebDriverManager.chromedriver().setup();
		// Add steps to open the Chrome browser and maximize the window
		driver = new ChromeDriver();
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
