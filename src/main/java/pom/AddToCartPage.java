package pom;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddToCartPage {
	
	@FindBy (xpath="//div[@class='cart_info']//h2//a")private List<WebElement> productDescription;
	
	@FindBy (xpath="//p[@class='chintu']")private List<WebElement> productRemove;
	
	@FindBy (xpath="//li[@class='head_UPrice']")private List<WebElement> unitPrice;
	
	@FindBy (xpath="//li[@class='head_ship']")private List<WebElement> shippingPrice;
	
	@FindBy (xpath="//li[@class='head_Amount']")private List<WebElement> orderAmount;
	
	@FindBy (xpath="(//ul[@id='cartTotal']//li//label)[1]")private WebElement cartAmount;
	
	@FindBy (xpath="(//ul[@id='cartTotal']//li//label)[2]")private WebElement discount;
	
	@FindBy (xpath="(//ul[@id='cartTotal']//li//label)[3]")private WebElement totalAmount;
	
	@FindBy (xpath="//input[@class='input_Special_2']")private List<WebElement> quantity;
		
    @FindBy (xpath="(//a[@onclick='cart.continueShopping()'])[1]")private WebElement continueShoppingTopButton;
	
	@FindBy (xpath="(//a[@onclick='cart.continueShopping()'])[2]")private WebElement continueShoppingBottomButton;
	
	
	public AddToCartPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	

	public String getProductDescription(WebDriver driver,int index) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
		wait.until(ExpectedConditions.visibilityOf(productDescription.get(index)));
		return productDescription.get(index).getText();
	}
	
	public void removeProductFromCart(int index) {
		productRemove.get(index).click();
	}
	
	public int getUnitPriceFromAddToCart(int index) {
		String up= unitPrice.get(index+1).getText().replaceAll("[^0-9]","");
		int priceUnit=Integer.parseInt(up);
		return priceUnit;
	}
	
	public int getShippingPriceFromCart(int index) {
		String sp=shippingPrice.get(index+1).getText().replaceAll("[^0-9]","");
		int priceshipping=Integer.parseInt(sp);
		return priceshipping;
	}
	public int getOrderAmount(int index) {
		String oa=orderAmount.get(index+1).getText().replaceAll("[^0-9]","");
		int amountOrder=Integer.parseInt(oa);
		return amountOrder;
	}
	
	public void clickOnContinueShoppingTopButton(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
		wait.until(ExpectedConditions.visibilityOf(continueShoppingTopButton));
		continueShoppingTopButton.click();
	}
	
	public void clickOnContinueShoppingBottomButton(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
		wait.until(ExpectedConditions.visibilityOf(continueShoppingBottomButton));
		continueShoppingBottomButton.click();
	}
	
	public int getCartAmount(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
		wait.until(ExpectedConditions.visibilityOf(cartAmount));
		String ca= cartAmount.getText().replaceAll("[^0-9]", "");
		int amountCart = Integer.parseInt(ca);
		return amountCart;
	}
	
	public int getProductQuantity(int index) {
		String qt=quantity.get(index).getAttribute("qty");
		int productQuantity=Integer.parseInt(qt);
		return productQuantity;
		
	}
	
	public int getDiscountAmount() {
		String d=discount.getText().replaceAll("[^0-9]", "");
		int discountamount=Integer.parseInt(d);
		return discountamount;
	}
	
	public int getTotalAmount() {
		String ta=totalAmount.getText().replaceAll("[^0-9]","");
		int amountTotal=Integer.parseInt(ta);
		return amountTotal;
	}
	
	public void changeQuantity() {
		quantity.get(0);
		
	}
	
	
	
	

}
