package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NaaptolHomePage {
	
	@FindBy (xpath="//a[@id='login-panel-link']")private WebElement loginOrRegister ;
	@FindBy (xpath="//a[text()='Track Order']")private WebElement trackOrder;
	@FindBy (xpath="//div[@onmouseover='javascript:menu.showMainMenu(true)']")private WebElement shoppingCategories;
	@FindBy (xpath="//input[@id='header_search_text']")private WebElement searchBox;
	@FindBy (xpath="(//a[@href=\"javascript:autoSuggestion.headerSearch()\"])[2]")private WebElement searchButton;
	@FindBy (xpath="(//a[@id='cart-panel-link'])[2]")private WebElement cartButton;
	@FindBy (xpath="//input[@id='registration-basic-panel-mobile']")private WebElement mobileno;
	@FindBy (xpath="//input[@id='registration-basic-panel-submit']")private WebElement continueButton;
	@FindBy (xpath="//a[text()='Logout']")private WebElement logout;
	@FindBy (xpath="//div[@class='small_links']//b")private WebElement username;
	
	public NaaptolHomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnLoginOrRegister() {
		loginOrRegister.click();
	}
	public void clickOnTrackOrder() {
		trackOrder.click();
	}
	public void moveToShoppingCategories(WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.moveToElement(shoppingCategories);
		actions.perform();
	}
	public void enterProductToSearch(String product) {
		searchBox.sendKeys(product);
	}
    public void clickOnSearchButton() {
    	searchButton.click();
    }
    public void clickOnCart() {
    	cartButton.click();
    }
    public void enterMobileNumber(String number) {
    	mobileno.sendKeys(number);
    }
    public void clickOnContinueButton() {
    	continueButton.click();
    }
    public boolean islogoutDisplayed() {
    	return logout.isDisplayed();
    }
    public String getUserName() {
    	return username.getText();
    }
    public void clickOnLogout() {
    	logout.click();
    }
   
}
