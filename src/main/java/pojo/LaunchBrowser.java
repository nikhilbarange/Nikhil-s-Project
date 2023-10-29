package pojo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LaunchBrowser {
	
	public static WebDriver openBrowser(String url) {
		System.setProperty("webdriver.chrome.driver", "E:\\JAVA\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.naaptol.com/");
		return driver;
		}


}
