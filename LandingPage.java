package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage 
{
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		//Initialization
		this.driver = driver;
		PageFactory.initElements(driver, this); //Triggers @FindBy annotation
	}
	
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	//PageFactory -- same step on syntax in line #18
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement password01;
	
	@FindBy(id="login")
	WebElement submit;
	
	//Action method
	public void loginApplication(String email, String password)
	{
		userEmail.sendKeys(email);
		password01.sendKeys(password);
		submit.click();
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
		
	}
}
