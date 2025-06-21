package rahulshettyacademy.SeleniumFrameworkDesign;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class StandAloneTestECommerce 
{
	public static void main(String[] args) throws InterruptedException 
	{
		ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo(); //Actions method from LandingPage class
		landingPage.loginApplication("anshika@gmail.com", "Iamking@000"); //Actions method from LandingPage class
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3")); //Store all web elements (products) in a list
		
		WebElement prod =	products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null); //Locate all Add cart button of the items in the stored list
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click(); //Click Add Cart button for ZARA COAT 3 product
		
		Thread.sleep(3000);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		WebElement cartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[routerlink*='cart']")));
		cartBtn.click();
		//driver.findElement(By.cssSelector("[routerlink*='cart']")).click(); //Click cart button
		
		List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3")); //Store list of products added in a cart in to a list	
		Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName)); //Compare text value of the string (ZARA COAT 3) in the products added in the list
		Assert.assertTrue(match); //Verrify if string value and text captured in the list does match
		driver.findElement(By.cssSelector(".totalRow button")).click(); //Click proceed to checkout button
	
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform(); //Input value on the country field
	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results"))); //Wait to display auto-suggestion values after typing a value on the field
	
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click(); //Selecting the 2nd option on the list
		driver.findElement(By.cssSelector(".action__submit")).click(); //Click submit button
	
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText(); //Get the value of web element text to be use for assertion
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER.")); //Verify if the value of web element text matched in the assertion
		driver.close();
	}
}
