package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class TicTacToeTest {
  
	public String driverPath = "resources/chromedriver";
	public String baseUrl = "https://codepen.io/CalendlyQA/full/KKPQLmV";
	public WebDriver driver;
	
	@BeforeMethod()
	public void openBrowser() {
		System.out.println("Opening Browser to Tic Tac Toe app...");
		System.setProperty("webdriver.chrome.driver",driverPath);
		driver = new ChromeDriver();
        // launch Chrome and direct it to the Base URL
        driver.get(baseUrl);
        driver.switchTo().frame("result");
	}
	
	@Test(testName = "Scenario 1")
	public void verifyTableGeneratedWithCorrectInput() {
        WebElement inputField = driver.findElement(By.xpath("//form/input[@id='number']"));
        inputField.clear();
        inputField.sendKeys("3");
        
        WebElement playButton = driver.findElement(By.tagName("button"));
        playButton.click();
        
        WebElement firstSquare = driver.findElement(By.xpath("//*[@id='0']"));
        
        Assert.assertTrue(firstSquare.isDisplayed(), "Assert that the table is successfully generated.");
	}
	
	
	@Test(testName = "Scenario 2")
	public void verifyTableNotGeneratedWithInputValueLessThanEqualToZero() {
		int expectedNumberOfFirstSquares = 0;
		String inputValue = "0";
		
		WebElement inputField = driver.findElement(By.xpath("//form/input[@id='number']"));
        inputField.clear();
        inputField.sendKeys(inputValue);
        
        WebElement playButton = driver.findElement(By.tagName("button"));
        playButton.click();
        
        List<WebElement> firstSquares = driver.findElements(By.xpath("//*[@id='0']"));
        
        Assert.assertEquals(firstSquares.size(), expectedNumberOfFirstSquares, "Assert that the table is not generated if user inputs a number less than or equal to zero.");
	}
	
	@Test(testName = "Scenario 3")
	public void verifyTableNotGeneratedWithNonNumericInputValue() {
		int expectedNumberOfFirstSquares = 0;
		String inputValue = "Q";
		
		WebElement inputField = driver.findElement(By.xpath("//form/input[@id='number']"));
        inputField.clear();
        inputField.sendKeys(inputValue);
        
        WebElement playButton = driver.findElement(By.tagName("button"));
        playButton.click();
        
        List<WebElement> firstSquares = driver.findElements(By.xpath("//*[@id='0']"));
        
        Assert.assertEquals(firstSquares.size(), expectedNumberOfFirstSquares, "Assert that the table is not generated if user inputs a number less than or equal to zero.");
	}
	
	@Test(testName = "Scenario 4")
	public void verifyNewTableNotGeneratedIfGameAlreadyStarted() {
		int expectedNumberOfFirstSquares = 1;
		String inputValue = "3";
		
		WebElement inputField = driver.findElement(By.xpath("//form/input[@id='number']"));
        inputField.clear();
        inputField.sendKeys(inputValue);
        
        WebElement playButton = driver.findElement(By.tagName("button"));
        playButton.click();
        
        inputField.clear();
        inputField.sendKeys(inputValue);
        
        playButton.click();
        
        List<WebElement> firstSquares = driver.findElements(By.xpath("//*[@id='0']"));
        
        Assert.assertEquals(firstSquares.size(), expectedNumberOfFirstSquares, "Assert that another table is not generated if a game is already in session.");
	}
	
	
	@Test(testName = "Scenario 5")
	public void verifyGameCanEndWithAWinner() {
		String expectedBannerText = "Congratulations player X! You've won. Refresh to play again!";
		String inputValue = "3";
		
		WebElement inputField = driver.findElement(By.xpath("//form/input[@id='number']"));
        inputField.clear();
        inputField.sendKeys(inputValue);
        
        WebElement playButton = driver.findElement(By.tagName("button"));
        playButton.click();
        
        List<WebElement> boardSquares = driver.findElements(By.xpath("//td"));
        boardSquares.get(0).click();
        boardSquares.get(1).click();
        boardSquares.get(3).click();
        boardSquares.get(4).click();
        boardSquares.get(6).click();
        
        WebElement endGameDiv = driver.findElement(By.id("endgame"));
        String actualText = endGameDiv.getText();
        
        Assert.assertEquals(actualText, expectedBannerText, "Assert that game ends with a winner and correct winner is displayed on banner");
	}
	
	@AfterMethod()
	public void closeBrowser() {
		System.out.println("Closing Browser...");
        driver.quit();
	}

}
