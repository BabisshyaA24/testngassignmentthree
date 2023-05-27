package ai.iamneo.assignmentthree.Testing_Selenium_TestngThree;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.List;

public class AppTest {
	private WebDriver driver;

	@BeforeMethod
	@Parameters("browser")
	public void setup(@Optional("chrome") String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.port", "8080");
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		} else if (browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		} else {
			System.out.println("Invalid browser name");
		}

		driver.get("https://www.flipkart.com");

	}

	@Test
	public void countLinksOnHomePage() {

		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("Number of links on homepage: " + links.size());
	}

	@AfterMethod
	public void printLinksOnHomePage() {
		driver.get("https://www.flipkart.com/");
		List<WebElement> links = driver.findElements(By.tagName("a"));
		for (WebElement link : links) {
			System.out.println(link.getText() + " - " + link.getAttribute("href"));
		}
	}

}
