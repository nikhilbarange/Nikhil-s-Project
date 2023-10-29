package test;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import pojo.BaseTest;
import pojo.Browser;
import pom.NaaptolHomePage;
import pom.QuickViewProductDisplayPage;
import pom.SearchResultPage;

public class NaaptolProductSearchTest extends BaseTest {
	
	@BeforeMethod
	public void openBrowser() {
		driver=Browser.openBrowser();
	}
	
	@Test
	public void productSearchTest() {
		extentTest=extentReports.createTest("productSearchTest");
		NaaptolHomePage naaptolHomePage = new NaaptolHomePage(driver);
		naaptolHomePage.enterProductToSearch("mobile");
		naaptolHomePage.clickOnSearchButton();
	    SearchResultPage searchResultPage = new SearchResultPage(driver);
	   int products= searchResultPage.getNumberOfProductDisplayedAfterSearch(0);
	   Assert.assertTrue(products>0);
	   Assert.assertTrue(driver.getTitle().contains("mobile"));
	}
	
	@Test
public void verifyIfProductIsDisplayedInQuickView() throws InterruptedException {
		extentTest=extentReports.createTest("verifyIfProductIsDisplayedInQuickView");
		NaaptolHomePage naaptolHomePage = new NaaptolHomePage(driver);
		Thread.sleep(10000);
		naaptolHomePage.enterProductToSearch("cooker");
		naaptolHomePage.clickOnSearchButton();
	    SearchResultPage searchResultPage = new SearchResultPage(driver);
	    searchResultPage.moveToDesiredProduct(driver, 1);
	    searchResultPage.getProductTitle(1);
	    searchResultPage.clickOnQuickView(driver, 1);
	    Thread.sleep(3000);
	    QuickViewProductDisplayPage quickViewProductDisplayPage = new QuickViewProductDisplayPage(driver);
	    quickViewProductDisplayPage.getProductTitle(driver);
	    System.out.println(quickViewProductDisplayPage.getProductTitle(driver));
	    Assert.assertTrue(quickViewProductDisplayPage.getProductTitle(driver).contains("Pressure Cooker"));
	    
	       
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
