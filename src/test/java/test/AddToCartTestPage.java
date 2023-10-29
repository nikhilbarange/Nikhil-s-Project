package test;

import java.util.Iterator;
import java.util.Set;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import pojo.BaseTest;
import pojo.Browser;
import pom.AddToCartPage;
import pom.NaaptolHomePage;
import pom.QuickViewProductDisplayPage;
import pom.SearchResultPage;
import utility.Reports;

public class AddToCartTestPage extends BaseTest {
	
	@BeforeClass
	public void addReports() {
		extentReports=Reports.configureReports();
	}
	
	@BeforeMethod
	public void openBrowser() {
		driver=Browser.openBrowser();
	}
	
	@Test(priority = 1)
	public void verifyingProductAddedToCart() throws InterruptedException {
		extentTest=extentReports.createTest("verifyingProductAddedToCart");
		NaaptolHomePage naaptolHomePage = new NaaptolHomePage(driver);
		naaptolHomePage.enterProductToSearch("cooker");
		naaptolHomePage.clickOnSearchButton();
		SearchResultPage searchResultPage = new SearchResultPage(driver);
		searchResultPage.moveToDesiredProduct(driver, 1);
		searchResultPage.clickOnQuickView(driver, 1);
		QuickViewProductDisplayPage quickViewProductDisplayPage = new QuickViewProductDisplayPage(driver);
    	String title=quickViewProductDisplayPage.getProductTitle(driver);
		System.out.println(title);
        int productPrice=quickViewProductDisplayPage.getProductPrice(driver);
		System.out.println(productPrice);
		int shippingPrice=quickViewProductDisplayPage.getShippingPrice();
		System.out.println(shippingPrice);
		try {
			quickViewProductDisplayPage.selectColour(driver,1);
		}
		catch (Exception e) {
			System.out.println("No such element");
		}
		quickViewProductDisplayPage.clickToBuyOnline(driver);
		Thread.sleep(2000);
		AddToCartPage addToCartPage = new AddToCartPage(driver);
		String cartproductDescription=addToCartPage.getProductDescription(driver, 0);
		System.out.println(cartproductDescription);
		int cartshippingPrice=addToCartPage.getShippingPriceFromCart(0);
		System.out.println(cartshippingPrice);
		int cartProductUnitPrice=addToCartPage.getUnitPriceFromAddToCart(0);
		System.out.println(cartProductUnitPrice);
		Assert.assertEquals(productPrice, cartProductUnitPrice);
		Assert.assertEquals(shippingPrice, cartshippingPrice);
		Assert.assertEquals(title, cartproductDescription);
		
	}
	
	@Test(priority = 2)
	public void productAddedToCartFromProductDisplayPage() throws InterruptedException {
		extentTest=extentReports.createTest("productAddedToCartFromProductDisplayPage");
		NaaptolHomePage naaptolHomePage = new NaaptolHomePage(driver);
		naaptolHomePage.enterProductToSearch("cooker");
		naaptolHomePage.clickOnSearchButton();
		SearchResultPage searchResultPage = new SearchResultPage(driver);
		searchResultPage.moveToDesiredProduct(driver, 1);
		String actualtitle=searchResultPage.getProductTitle(1);
		searchResultPage.clickOnDesiredProduct(driver, 1);
		QuickViewProductDisplayPage quickViewProductDisplayPage = new QuickViewProductDisplayPage(driver);
		Set<String> handles=driver.getWindowHandles();
		Iterator<String>i=handles.iterator();
		while(i.hasNext()) {
			String handle=i.next();
			driver.switchTo().window(handle);
			String currentTitle=driver.getTitle();
			if(currentTitle.contains(actualtitle)) {
				try {
					quickViewProductDisplayPage.selectColour(driver,1);
				}
				catch (Exception e) {
					System.out.println("No such element");
				}
				quickViewProductDisplayPage.clickToBuyOnline(driver);
				AddToCartPage addToCartPage = new AddToCartPage(driver);
				Thread.sleep(2000);
				Assert.assertEquals(quickViewProductDisplayPage.getProductTitle(driver), addToCartPage.getProductDescription(driver, 0));
				Assert.assertEquals(quickViewProductDisplayPage.getProductPrice(driver), addToCartPage.getUnitPriceFromAddToCart(0));
				Assert.assertEquals(quickViewProductDisplayPage.getShippingPrice(), addToCartPage.getShippingPriceFromCart(0));
			}
		}
		
		
			
		}
		
		@Test(priority = 3)
		public void addMultipleProductsFromQuickView() throws InterruptedException {
		extentTest=extentReports.createTest("addMultipleProductsFromQuickView");
		NaaptolHomePage naaptolHomePage = new NaaptolHomePage(driver);
		naaptolHomePage.enterProductToSearch("mixer");
		naaptolHomePage.clickOnSearchButton();
		SearchResultPage searchResultPage = new SearchResultPage(driver);
		searchResultPage.moveToDesiredProduct(driver, 1 );
		String firstproduct=searchResultPage.getProductTitle(1);
		System.out.println(firstproduct);
		searchResultPage.clickOnQuickView(driver, 1);
		Thread.sleep(2000);
		QuickViewProductDisplayPage quickViewProductDisplayPage = new QuickViewProductDisplayPage(driver);
		try {
			quickViewProductDisplayPage.selectColour(driver,1);
		}
		catch (Exception e) {
			System.out.println("No such element");
		}
		quickViewProductDisplayPage.clickToBuyOnline(driver);
		Thread.sleep(2000);
		AddToCartPage addToCartPage= new AddToCartPage(driver);
		addToCartPage.clickOnContinueShoppingTopButton(driver);
		searchResultPage.moveToDesiredProduct(driver, 2);
		String secondproduct=searchResultPage.getProductTitle(2);
		System.out.println(secondproduct);
		searchResultPage.clickOnQuickView(driver, 2);
		try {
			quickViewProductDisplayPage.selectColour(driver,1);
		}
		catch (Exception e) {
			System.out.println("No such element");
		}
		quickViewProductDisplayPage.clickToBuyOnline(driver);
		Thread.sleep(2000);
		System.out.println(addToCartPage.getProductDescription(driver, 1));
		System.out.println(addToCartPage.getProductDescription(driver, 0));
		Assert.assertTrue(addToCartPage.getProductDescription(driver, 1).contains(firstproduct));
		Assert.assertTrue(addToCartPage.getProductDescription(driver, 0).contains(secondproduct));
		
		
			
	}
		
		@Test(priority = 4)
		public void addMultipleProductsFromProductDisplayPage() throws InterruptedException {
			extentTest=extentReports.createTest("addMultipleProductsFromProductDisplayPage");
			NaaptolHomePage naaptolHomePage = new NaaptolHomePage(driver);
			naaptolHomePage.enterProductToSearch("cooker");
			naaptolHomePage.clickOnSearchButton();
			SearchResultPage searchResultPage = new SearchResultPage(driver);
			searchResultPage.clickOnDesiredProduct(driver, 1);
			String firstproduct = searchResultPage.getProductTitle(1);
			String secondproduct = searchResultPage.getProductTitle(2);
			System.out.println(firstproduct);
			String mainPageHandle=driver.getWindowHandle();
			Set<String>handles=driver.getWindowHandles();
			Iterator<String>i=handles.iterator();
			QuickViewProductDisplayPage quickViewProductDisplayPage = new QuickViewProductDisplayPage(driver);
			AddToCartPage addToCartPage = new AddToCartPage(driver);
			while(i.hasNext()) {
				String handle=i.next();
				driver.switchTo().window(handle);
				String currentTitle=driver.getTitle();
				if(currentTitle.contains(firstproduct)) {
					quickViewProductDisplayPage.clickToBuyOnline(driver);
					addToCartPage.clickOnContinueShoppingBottomButton(driver);
				
				}
		}
//			Iterator<String> j =handles.iterator();
//			while(j.hasNext()) {
//				String handle=j.next();
//				driver.switchTo().window(handle);
//				String currentTitle=driver.getTitle();
//				if(currentTitle.contains("Search Result for cooker")) {
//					searchResultPage.clickOnDesiredProduct(driver, 2);
//				}
//			}
			driver.switchTo().window(mainPageHandle);
			String currentTitle=driver.getTitle();
			if(currentTitle.contains("Search Result for cooker")) {
				searchResultPage.clickOnDesiredProduct(driver, 2);
			}
			Set<String>updatedhandles=driver.getWindowHandles();
			Iterator<String> k=updatedhandles.iterator();
			while(k.hasNext())
			{
				String handle=k.next();
				driver.switchTo().window(handle);
				String mainTitle=driver.getTitle();
				if(mainTitle.contains(secondproduct)) {
					quickViewProductDisplayPage.clickToBuyOnline(driver);
				}
			}
			Thread.sleep(2000);
			Assert.assertTrue(addToCartPage.getProductDescription(driver, 1).contains(firstproduct));
			Assert.assertTrue(addToCartPage.getProductDescription(driver, 0).contains(secondproduct));
			
		}
		
		@Test(priority = 5)
		public void verifyingArithmeticCalculationsOfAddToCart() throws InterruptedException {
			extentTest=extentReports.createTest("verifyingArithmeticCalculationsOfAddToCart");
			NaaptolHomePage naaptolHomePage = new NaaptolHomePage(driver);
			naaptolHomePage.enterProductToSearch("mixer");
			naaptolHomePage.clickOnSearchButton();
			SearchResultPage searchResultPage = new SearchResultPage(driver);
			searchResultPage.moveToDesiredProduct(driver, 0);
			searchResultPage.clickOnQuickView(driver, 0);
			QuickViewProductDisplayPage quickViewProductDisplayPage = new QuickViewProductDisplayPage(driver);
			try {
				quickViewProductDisplayPage.selectColour(driver,1);
			}
			catch (Exception e) {
				System.out.println("No such element");
			}
			quickViewProductDisplayPage.clickToBuyOnline(driver);
			AddToCartPage addToCartPage= new AddToCartPage(driver);
			addToCartPage.clickOnContinueShoppingTopButton(driver);
			searchResultPage.moveToDesiredProduct(driver, 2);
			searchResultPage.clickOnQuickView(driver, 2);
			Thread.sleep(2000);
			try {
				quickViewProductDisplayPage.selectColour(driver,0);
			}
			catch (Exception e) {
				System.out.println("No such element");
			}
			quickViewProductDisplayPage.clickToBuyOnline(driver);
			Thread.sleep(2000);			
			int expectedOrderAmountForFirstProduct=(addToCartPage.getProductQuantity(0)*addToCartPage.getUnitPriceFromAddToCart(0))+addToCartPage.getShippingPriceFromCart(0);
			int expectedOrderAmountForSecontProduct=(addToCartPage.getProductQuantity(1)*addToCartPage.getUnitPriceFromAddToCart(1))+addToCartPage.getShippingPriceFromCart(1);
			int ExpectedTotalAmount=(addToCartPage.getOrderAmount(0)+addToCartPage.getOrderAmount(1))-addToCartPage.getDiscountAmount();
			Assert.assertEquals(addToCartPage.getOrderAmount(0), expectedOrderAmountForFirstProduct);
			Assert.assertEquals(addToCartPage.getOrderAmount(1), expectedOrderAmountForSecontProduct);
			Assert.assertEquals(addToCartPage.getTotalAmount(), ExpectedTotalAmount);
			//addToCartPage.changeQuantity();
					
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
