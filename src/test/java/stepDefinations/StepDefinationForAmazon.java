package stepDefinations;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.asserts.SoftAssert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationForAmazon {
	WebDriver driver;
	String Browser="chrome";
	SoftAssert st;
	@Before
	public void OpenBrowser(){
		 st=new SoftAssert();
		if(Browser.equalsIgnoreCase("chrome")){
			driver=new ChromeDriver();
		}else if(Browser.equalsIgnoreCase("firefox")){
			driver=new FirefoxDriver();
		}else if(Browser.equalsIgnoreCase("edge")){
			driver=new EdgeDriver();
		}
		driver.get("https://www.amazon.com/");// open url
		driver.manage().window().maximize(); //maximize browser
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	@After
	public void CloseBrowser(){
		driver.quit();
		st.assertAll();
	}
	
	@Given("user is in the department page")
	public void user_is_in_the_department_page() throws InterruptedException {
		((JavascriptExecutor)driver).executeScript("document.querySelector(\"#nav-xshop > a:nth-child(2)\").click()");
		Thread.sleep(5);
	}
	@When("user select {string} department and select item to add")
	public void user_select_department_and_select_item_to_add(String checkbox) {
		if(checkbox.equalsIgnoreCase("furniture")){
			driver.findElement(By.xpath("//*[@id=\"grid-main-container\"]/div[2]/span[3]/ul/li[18]/label/input")).click();
		}else if(checkbox.equalsIgnoreCase("menswatches")){
			driver.findElement(By.xpath("//*[@id=\"grid-main-container\"]/div[2]/span[3]/ul/li[36]/label/input")).click();
		}else if(checkbox.equalsIgnoreCase("MovieAndTv")){
			driver.findElement(By.xpath("//*[@id=\"grid-main-container\"]/div[2]/span[3]/ul/li[37]/label/input")).click();
		}
		//click on first item
				driver.findElement(By.xpath("//*[@id=\"grid-main-container\"]/div[3]/div/div[1]/div/div/a/div/div/img")).click();
				//click on first item in sublist
				driver.findElement(By.xpath("//*[@id=\"octopus-dlp-asin-stream\"]/ul/li[1]/span/div/div[1]/a/div")).click();
	}
	@And("clicks on add2cart button")
	public void clicks_on_add2cart_button() {
		//click on add2cart 
				driver.findElement(By.id("add-to-cart-button")).click();
	}
	@Then("sucessfully an item to be added to the cart")
	public void sucessfully_an_item_to_be_added_to_the_cart() {
		try{
		String text = driver.findElement(By.xpath("//*[@id=\"NATC_SMART_WAGON_CONF_MSG_SUCCESS\"]/span")).getText();
		System.out.println("Actual text ="+text);
		st.assertEquals(text, "Added to Cart");  
		}catch(Throwable t){
			String text = driver.findElement(By.xpath("//*[@id=\"attachDisplayAddBaseAlert\"]/div/h4")).getText();
			System.out.println("Actual text ="+text);
			st.assertEquals(text, "Added to Cart");  
		}
	}
}
