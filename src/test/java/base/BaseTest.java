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

        ChromeOptions options = new ChromeOptions();

        // Recommended for Docker / CI environments
        options.addArguments("--headless=new"); // Use new headless mode
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        // Selenium Manager automatically downloads and uses the correct ChromeDriver
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.get("http://172.19.144.1:8080/medicare2/");
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	}

	@AfterMethod
	public void teardown() {
		 driver.quit();
	}
}
