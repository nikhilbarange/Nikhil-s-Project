package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v115.browser.Browser;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import pojo.BaseTest;
import pom.NaaptolHomePage;

public class NaaptolHomePageTest extends BaseTest {
	
	@BeforeMethod
	public void openBrowser() {
		driver=pojo.Browser.openBrowser();
	}
	
	@Test
	public void NaaptolLoginTest() throws InterruptedException {
		extentTest=extentReports.createTest("NaaptolLoginTest");
		NaaptolHomePage naaptolHomePage = new NaaptolHomePage(driver);
		naaptolHomePage.clickOnLoginOrRegister();
		naaptolHomePage.enterMobileNumber("7972708189");
		naaptolHomePage.clickOnContinueButton();
		Thread.sleep(50000);
		Assert.assertTrue(naaptolHomePage.islogoutDisplayed());
		Assert.assertEquals(naaptolHomePage.getUserName(), "Nikhil");
	}
	
	@AfterMethod
	public void closeBrowser(ITestResult result) {
		if(result.getStatus()==result.SUCCESS) {
			extentTest.log(Status.PASS,result.getName());
		}
		else if(result.getStatus()==result.FAILURE){
			extentTest.log(Status.FAIL, result.getName());
		}
		else if(result.getStatus()==result.SKIP) {
			extentTest.log(Status.SKIP, result.getName());
		}
		driver.close();
	}
	
	@AfterClass
	public void flushData() {
		extentReports.flush();
	}
	
	

}
