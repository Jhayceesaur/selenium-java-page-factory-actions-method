package rahulshettyacademy.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductCatalog 
{
	WebDriver driver;
	
	public ProductCatalog(WebDriver driver)
	{
		//Initialization
		this.driver = driver;
		PageFactory.initElements(driver, this); //Triggers @FindBy annotation
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	
	
}
