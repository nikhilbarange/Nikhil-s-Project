package pom;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage {
	
	@FindBy (xpath="//div[@class='grid_Square ']")private List<WebElement> products;
	
	@FindBy (xpath="//img[@class='square']")private List<WebElement> productimage;
	
	@FindBy (xpath="//a[@href='#QuickView']")private List<WebElement> quickView;
	
	@FindBy (xpath="//div[@class='item_title']//a")private List<WebElement> productTitle;
	
	
	public SearchResultPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public int getNumberOfProductDisplayedAfterSearch(int index) {
		return products.size();
	}
	
	public void moveToDesiredProduct(WebDriver driver,int index) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
		wait.until(ExpectedConditions.visibilityOfAllElements(products));
		Actions actions = new Actions(driver);
		actions.moveToElement(products.get( index));
		actions.perform();
	}
	public void clickOnDesiredProduct(WebDriver driver,int index) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
		wait.until(ExpectedConditions.visibilityOfAllElements(productimage));
		productimage.get(index).click();
	}
	public String getProductTitle(int index) {
		return productTitle.get(index).getText();
	}
	public void clickOnQuickView(WebDriver driver,int index) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
	    wait.until(ExpectedConditions.visibilityOf(quickView.get(index)));
		quickView.get(index).click();
	}
	public int productImageSize() {
		return productimage.size();
	}
	
	

}
