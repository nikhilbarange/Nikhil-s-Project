package test;

import java.util.Iterator;
import java.util.Set;

import org.testng.Assert;
import org.testng.ITestListener;
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

public class QuickViewTestPage extends BaseTest{
	
	@BeforeMethod
	public void openBrowser() {
		driver=Browser.openBrowser();
	}
	
	@Test
	public void verifyIfProductDisplayedInQuickView() throws InterruptedException {
		extentTest=extentReports.createTest("verifyIfProductDisplayedInQuickView");
		NaaptolHomePage naaptolHomePage = new NaaptolHomePage(driver);
		naaptolHomePage.enterProductToSearch("cooker");
		naaptolHomePage.clickOnSearchButton();
	    SearchResultPage searchResultPage = new SearchResultPage(driver);
	    searchResultPage.moveToDesiredProduct(driver, 1);
	    searchResultPage.clickOnQuickView(driver, 1);;
	    Thread.sleep(3000);
	    QuickViewProductDisplayPage quickViewProductDisplayPage = new QuickViewProductDisplayPage(driver);
	    System.out.println(quickViewProductDisplayPage.getProductTitle(driver));
	    Assert.assertTrue(quickViewProductDisplayPage.getProductTitle(driver).contains("Pressure Cooker"));
	    
				
	}
	
	@Test
	public void verifyIfProductIsDisplayedInCartIsSameAsSelectedFromPDP() throws InterruptedException {
		extentTest=extentReports.createTest("verifyIfProductIsDisplayedInCartIsSameAsSelectedFromPDP");
		NaaptolHomePage naaptolHomePage = new NaaptolHomePage(driver);
		naaptolHomePage.enterProductToSearch("cooker");
		naaptolHomePage.clickOnSearchButton();
		SearchResultPage searchResultPage = new SearchResultPage(driver);
		searchResultPage.productImageSize();
		searchResultPage.clickOnDesiredProduct(driver, 2);
		Set<String>handles=driver.getWindowHandles();
		Iterator<String>i=handles.iterator();
		QuickViewProductDisplayPage quickViewProductDisplayPage = new QuickViewProductDisplayPage(driver);
		while(i.hasNext()) {
			String handle=i.next();
			driver.switchTo().window(handle);
			Thread.sleep(4000);
			String currentTitle=driver.getTitle();
			if(currentTitle.contains("Cookware Set")) {
				Assert.assertTrue(quickViewProductDisplayPage.getProductTitle(driver).contains("Cookware Set"));
				quickViewProductDisplayPage.clickToBuyOnline(driver);
			}
		}
		
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