package assignment;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;


public class MyTest {
	public static void main(String[] args) {
        // declaration and instantiation of objects/variables
		//comment the above 2 lines and uncomment below 2 lines to use Chrome
		System.setProperty("webdriver.chrome.driver","resources/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String baseUrl = "https://codepen.io/CalendlyQA/full/KKPQLmV";
        
        // launch Chrome and direct it to the Base URL
        driver.get(baseUrl);
        
        
        driver.switchTo().frame("result");
        
        WebElement inputField = driver.findElement(By.xpath("//form/input[@id='number']"));
        inputField.clear();
        inputField.sendKeys("3");
        
        
        WebElement playButton = driver.findElement(By.tagName("button"));
        playButton.click();
        
//        WebDriverWait wait = new WebDriverWait(driver,30);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='0']")));
        
        
        WebElement firstSquare = driver.findElement(By.xpath("//*[@id='0']"));
        if(firstSquare.isDisplayed()) {
        	System.out.println("The table is displayed.");
        }
        
        //close Fire fox
        driver.close();
       
    }

}
