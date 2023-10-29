package pom;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QuickViewProductDisplayPage {
	
	@FindBy (xpath="//div[@id='square_Details']//h1")private WebElement productTitle;
	
	@FindBy (xpath="//a[@class='red_button icon chat']")private WebElement buyButton;
	
	@FindBy (xpath="//input[@class='inputField_new checkPin']")private WebElement pincode;
	
	@FindBy (xpath="(//a[@class='button_1'])[3]")private WebElement pincodeCheckButton;
	
	@FindBy (xpath="//a[@class='sml-link tooltip']")private WebElement addToWishlist;
	
	@FindBy (xpath="(//li[@id='productPriceDisplay']//span[2])[1]")private WebElement productPrice;
	
	@FindBy (xpath="//span[@class='ship-price']")private WebElement shippingPrice;
	
	@FindBy (xpath="//a[@onclick=\"javascript:productObj.setColor('0',this)\"]")private List<WebElement> colour;
	
	
	
	public QuickViewProductDisplayPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String getProductTitle(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000));
		wait.until(ExpectedConditions.visibilityOf(productTitle));
		return productTitle.getText();
	}
	
	public void clickToBuyOnline(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
		wait.until(ExpectedConditions.visibilityOf(buyButton));
		buyButton.click();
	}
	
	public void enterPincode(String code) {
		pincode.sendKeys(code);
	}
	
	public void checkProductAvailabilityByPincode() {
		pincodeCheckButton.click();
	}
	
	public void clickOnAddToList() {
		addToWishlist.click();
	}
	
	public int getProductPrice(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
		wait.until(ExpectedConditions.visibilityOfAllElements(productPrice));
	    String pp= productPrice.getText().substring(0, 6).replaceAll("[^0-9]","");
	    int priceProduct=Integer.parseInt(pp);
	    return priceProduct;
	    
	}
	
	public int getShippingPrice() {
		String sp= shippingPrice.getText().replaceAll("[^0-9]","");
		int priceShipping=Integer.parseInt(sp);
		return priceShipping;
	}
	
	public void selectColour(WebDriver driver,int index) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
		colour.get(index).click();
	}
	
	
	

}
