package gridTest;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChromeGridTest {

	WebDriver driver;
	DesiredCapabilities cap;
	ChromeOptions options;
	String hubUrl = "http://192.168.2.181:4444/wd/hub";
	
	
	//To start hub - java -jar selenium-server-standalone-3.141.59.jar -role hub
	//To start node - java -Dwebdriver.chrome.driver="C:\SeleniumGrid\chromedriver.exe" -jar selenium-server-standalone-3.141.59.jar -role node -hub http://192.168.2.181:4444/grid/register
	@BeforeMethod
	public void setUp(){
		//WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver", "C:\\SeleniumGrid\\chromedriver.exe");
		//Define desire cap
		cap = new DesiredCapabilities();
		cap.setBrowserName("chrome");
		cap.setPlatform(Platform.WINDOWS);
		
		//Chome Options defination
		options = new ChromeOptions();
		options.merge(cap);
	}
	
	@Test
	public void gridTest() throws MalformedURLException, InterruptedException{
		driver = new RemoteWebDriver(new URL(hubUrl), options);
		driver.get("https://www.github.com");
		Thread.sleep(5000);
		System.out.println(driver.getTitle());
	}
	
	@AfterMethod
	public void cleanUp(){
		driver.quit();
	}
}
