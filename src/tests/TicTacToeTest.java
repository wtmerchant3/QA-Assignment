package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
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
	public void verifyTableGeneratedWithCorrectInput() throws InterruptedException {
		String inputValue = "3";
		
		System.out.println("Inputting value into input field - " + inputValue);
		WebElement inputField = driver.findElement(By.xpath("//form/input[@id='number']"));
        inputField.clear();
        inputField.sendKeys(inputValue);
        
        System.out.println("Clicking play button");
        WebElement playButton = driver.findElement(By.tagName("button"));
        playButton.click();
        
        System.out.println("Checking to see if tic tac toe board generated");
        WebElement firstSquare = driver.findElement(By.xpath("//*[@id='0']"));
        System.out.println("Is board generated: " + firstSquare.isDisplayed());
        
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Assert.assertTrue(firstSquare.isDisplayed(), "Assert that the table is successfully generated.");
	}
	
	
	@Test(testName = "Scenario 2")
	public void verifyTableNotGeneratedWithInputValueLessThanEqualToZero() {
		int expectedNumberOfFirstSquares = 0;
		String inputValue = "0";
		
		System.out.println("Inputting value into input field - " + inputValue);
		WebElement inputField = driver.findElement(By.xpath("//form/input[@id='number']"));
        inputField.clear();
        inputField.sendKeys(inputValue);
        
        System.out.println("Clicking play button");
        WebElement playButton = driver.findElement(By.tagName("button"));
        playButton.click();
        
        System.out.println("Checking for the number of tic tac toe tables on the page.");
        List<WebElement> firstSquares = driver.findElements(By.xpath("//*[@id='0']"));
        System.out.println("Number of tables: " + firstSquares.size());
        
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Assert.assertEquals(firstSquares.size(), expectedNumberOfFirstSquares, "Assert that the table is not generated if user inputs a number less than or equal to zero.");
	}
	
	@Test(testName = "Scenario 3")
	public void verifyTableNotGeneratedWithNonNumericInputValue() {
		int expectedNumberOfFirstSquares = 0;
		String inputValue = "Q";
		
		System.out.println("Inputting value into input field - " + inputValue);
		WebElement inputField = driver.findElement(By.xpath("//form/input[@id='number']"));
        inputField.clear();
        inputField.sendKeys(inputValue);
        
        System.out.println("Clicking play button");
        WebElement playButton = driver.findElement(By.tagName("button"));
        playButton.click();
        
        System.out.println("Checking for the number of tic tac toe tables on the page.");
        List<WebElement> firstSquares = driver.findElements(By.xpath("//*[@id='0']"));
        System.out.println("Number of tables: " + firstSquares.size());
        
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Assert.assertEquals(firstSquares.size(), expectedNumberOfFirstSquares, "Assert that the table is not generated if user inputs a number less than or equal to zero.");
	}
	
	@Test(testName = "Scenario 4")
	public void verifyNewTableNotGeneratedIfGameAlreadyStarted() {
		int expectedNumberOfFirstSquares = 1;
		String inputValue = "3";
		
		System.out.println("Inputting value into input field - " + inputValue);
		WebElement inputField = driver.findElement(By.xpath("//form/input[@id='number']"));
        inputField.clear();
        inputField.sendKeys(inputValue);
        
        System.out.println("Clicking play button");
        WebElement playButton = driver.findElement(By.tagName("button"));
        playButton.click();
        
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.out.println("Inputting value into input field - " + inputValue);
        inputField.clear();
        inputField.sendKeys(inputValue);
        
        System.out.println("Clicking play button");
        playButton.click();
        
        System.out.println("Checking for the number of tic tac toe tables on the page.");
        List<WebElement> firstSquares = driver.findElements(By.xpath("//*[@id='0']"));
        System.out.println("Number of tables: " + firstSquares.size());
        
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Assert.assertEquals(firstSquares.size(), expectedNumberOfFirstSquares, "Assert that another table is not generated if a game is already in session.");
	}
	
	
	@Test(testName = "Scenario 5")
	public void verifyGameCanEndWithAWinner() {
		String expectedBannerText = "Congratulations player X! You've won. Refresh to play again!";
		String inputValue = "1";
		
		System.out.println("Inputting value into input field - " + inputValue);
		WebElement inputField = driver.findElement(By.xpath("//form/input[@id='number']"));
        inputField.clear();
        inputField.sendKeys(inputValue);
        
        System.out.println("Clicking play button");
        WebElement playButton = driver.findElement(By.tagName("button"));
        playButton.click();
        
        System.out.println("Board Generated!");
        List<WebElement> boardSquares = driver.findElements(By.xpath("//td"));
        
        int[] clickOrder = {0};
        System.out.println("Clicking Squares on Tic Tac Toe board...");
        for(int i : clickOrder) {
            System.out.println("Clicking space on row: " + boardSquares.get(i).getAttribute("data-row") + " column: " + boardSquares.get(i).getAttribute("data-column") +"." );
            boardSquares.get(i).click();
            System.out.println("Space value: " + boardSquares.get(i).getText());
        }
        
        System.out.println("Reading text from end game banner....");
        WebElement endGameDiv = driver.findElement(By.id("endgame"));
        String actualText = endGameDiv.getText();
        System.out.println("Banner text: "+ actualText);
        
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Assert.assertEquals(actualText, expectedBannerText, "Assert that game ends with a winner and correct winner is displayed on banner");
	}
	
	@Test(testName = "Scenario 6")
	public void verifyGameCanEndInADraw() {
		List<Boolean> expected = Arrays.asList(true, true, true, true, true, true, true, true, true);
		List<Boolean> actual = new ArrayList();
		String inputValue = "3";
		
		System.out.println("Inputting value into input field - " + inputValue);
		WebElement inputField = driver.findElement(By.xpath("//form/input[@id='number']"));
        inputField.clear();
        inputField.sendKeys(inputValue);
        
        System.out.println("Clicking play button");
        WebElement playButton = driver.findElement(By.tagName("button"));
        playButton.click();
		
        System.out.println("Board Generated!");
        
        List<WebElement> boardSquares = driver.findElements(By.xpath("//td"));
        int[] clickOrder = {0,4,1,3,5,2,6,7,8};
        
        System.out.println("Clicking Squares on Tic Tac Toe board...");
        for(int i : clickOrder) {
            System.out.println("Clicking space on row: " + boardSquares.get(i).getAttribute("data-row") + " column: " + boardSquares.get(i).getAttribute("data-column") +"." );
            boardSquares.get(i).click();
            System.out.println("Space value: " + boardSquares.get(i).getText());
        }
       
        System.out.println("Checking if all squares on game board are filled");
        boardSquares = driver.findElements(By.xpath("//td"));
        for(WebElement square: boardSquares) {
        	System.out.println("Is space on row: " + square.getAttribute("data-row") + " column: " + square.getAttribute("data-column") +" filled?");
        	String squareValue = square.getText();
        	System.out.println("Square value: " + squareValue);
        	boolean isFilled = !squareValue.isEmpty();
        	System.out.println("Result: " + isFilled);
        	actual.add(isFilled);
        }
        
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Assert.assertEquals(actual, expected, "Assert game ends in draw when all spots on board are filled");
	}
	
	@Test(testName = "Scenario 7")
	public void verifySpacesCannotBeClickedAfterGameEndsInWinWithRemainingEmptySpaces() {
		
		String inputValue = "3";
		int lastSpace = 7;
		
		System.out.println("Inputting value into input field - " + inputValue);
		WebElement inputField = driver.findElement(By.xpath("//form/input[@id='number']"));
        inputField.clear();
        inputField.sendKeys(inputValue);
        
        System.out.println("Clicking play button");
        WebElement playButton = driver.findElement(By.tagName("button"));
        playButton.click();
		
        System.out.println("Board Generated!");
        
        List<WebElement> boardSquares = driver.findElements(By.xpath("//td"));
        int[] clickOrder = {0,1,3,4,6};
        
        System.out.println("Clicking Squares on Tic Tac Toe board...");
        for(int i : clickOrder) {
            System.out.println("Clicking space on row: " + boardSquares.get(i).getAttribute("data-row") + " column: " + boardSquares.get(i).getAttribute("data-column") +"." );
            boardSquares.get(i).click();
            System.out.println("Space value: " + boardSquares.get(i).getText());
        }
        
        System.out.println("Is end game banner displayed?");
        WebElement endGameDiv = driver.findElement(By.id("endgame"));
        System.out.println("Result: "+ endGameDiv.isDisplayed());
        
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.out.println("Clicking space on row: " + boardSquares.get(lastSpace).getAttribute("data-row") + " column: " + boardSquares.get(lastSpace).getAttribute("data-column") +"." );
        boardSquares.get(lastSpace).click();
        String spaceValue = boardSquares.get(lastSpace).getText();
        System.out.println("Space Value: " + spaceValue);
        
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		Assert.assertTrue(spaceValue.isEmpty(), "Assert that clicking an empty space after a game has ended in a win with empty spaces remaining does not fill the space.");
	}
	
	@Test(testName = "Scenario 8")
	public void verifySpacesCannotBeClickedAfterGameEndsInDraw() {
		
		String inputValue = "3";
		int lastSpace = 8;
		String expectedValue = "X";
		
		System.out.println("Inputting value into input field - " + inputValue);
		WebElement inputField = driver.findElement(By.xpath("//form/input[@id='number']"));
        inputField.clear();
        inputField.sendKeys(inputValue);
        
        System.out.println("Clicking play button");
        WebElement playButton = driver.findElement(By.tagName("button"));
        playButton.click();
		
        System.out.println("Board Generated!");
        
        List<WebElement> boardSquares = driver.findElements(By.xpath("//td"));
        int[] clickOrder = {0,4,1,3,5,2,6,7,8};
        
        System.out.println("Clicking Squares on Tic Tac Toe board...");
        for(int i : clickOrder) {
            System.out.println("Clicking space on row: " + boardSquares.get(i).getAttribute("data-row") + " column: " + boardSquares.get(i).getAttribute("data-column") +"." );
            boardSquares.get(i).click();
            System.out.println("Space value: " + boardSquares.get(i).getText());
        }
        
        System.out.println("Checking if all squares on game board are filled");
        boardSquares = driver.findElements(By.xpath("//td"));
        for(WebElement square: boardSquares) {
        	System.out.println("Is space on row: " + square.getAttribute("data-row") + " column: " + square.getAttribute("data-column") +" filled?");
        	String squareValue = square.getText();
        	System.out.println("Square value: " + squareValue);
        	boolean isFilled = !squareValue.isEmpty();
        	System.out.println("Result: " + isFilled);
        }
        
        
        System.out.println("Clicking space on row: " + boardSquares.get(lastSpace).getAttribute("data-row") + " column: " + boardSquares.get(lastSpace).getAttribute("data-column") +"." );
        boardSquares.get(lastSpace).click();
        String spaceValue = boardSquares.get(lastSpace).getText();
        System.out.println("Space Value: " + spaceValue);
        
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		Assert.assertTrue(spaceValue.equals(expectedValue), "Assert that clicking on any space after a game has ended in a draw does not change the space value.");
	}
	
	@Test(testName = "Scenario 9")
	public void verifyPlayerOCanWinGame() {
		String expectedBannerText = "Congratulations player O! You've won. Refresh to play again!";
		String inputValue = "3";
		
		System.out.println("Inputting value into input field - " + inputValue);
		WebElement inputField = driver.findElement(By.xpath("//form/input[@id='number']"));
        inputField.clear();
        inputField.sendKeys(inputValue);
        
        System.out.println("Clicking play button");
        WebElement playButton = driver.findElement(By.tagName("button"));
        playButton.click();
		
        System.out.println("Board Generated!");
        
        List<WebElement> boardSquares = driver.findElements(By.xpath("//td"));
        int[] clickOrder = {0,2,3,4,7,6};
        
        System.out.println("Clicking Squares on Tic Tac Toe board...");
        for(int i : clickOrder) {
            System.out.println("Clicking space on row: " + boardSquares.get(i).getAttribute("data-row") + " column: " + boardSquares.get(i).getAttribute("data-column") +"." );
            boardSquares.get(i).click();
            System.out.println("Space value: " + boardSquares.get(i).getText());
        }
        
        System.out.println("Reading text from end game banner....");
        WebElement endGameDiv = driver.findElement(By.id("endgame"));
        String actualText = endGameDiv.getText();
        System.out.println("Banner text: "+ actualText);
        
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Assert.assertEquals(actualText, expectedBannerText, "Assert that Player O can win the game and that the endgame banner declares them as the winner.");
	}
	
	@Test(testName = "Scenario 10")
	public void verifyPlayerCantClickAlreadySelectedSquareDuringUnfinishedGame() {
		String inputValue = "2";
		int lastSpace = 0;
		String expectedValue = "X";
		
		System.out.println("Inputting value into input field - " + inputValue);
		WebElement inputField = driver.findElement(By.xpath("//form/input[@id='number']"));
        inputField.clear();
        inputField.sendKeys(inputValue);
        
        System.out.println("Clicking play button");
        WebElement playButton = driver.findElement(By.tagName("button"));
        playButton.click();
		
        System.out.println("Board Generated!");
        
        List<WebElement> boardSquares = driver.findElements(By.xpath("//td"));
        int[] clickOrder = {0};
        
        System.out.println("Clicking Squares on Tic Tac Toe board...");
        for(int i : clickOrder) {
            System.out.println("Clicking space on row: " + boardSquares.get(i).getAttribute("data-row") + " column: " + boardSquares.get(i).getAttribute("data-column") +"." );
            boardSquares.get(i).click();
            System.out.println("Space value: " + boardSquares.get(i).getText());
        }
        
        
        System.out.println("Clicking space on row: " + boardSquares.get(lastSpace).getAttribute("data-row") + " column: " + boardSquares.get(lastSpace).getAttribute("data-column") +"." );
        boardSquares.get(lastSpace).click();
        String spaceValue = boardSquares.get(lastSpace).getText();
        System.out.println("Space Value: " + spaceValue);
        
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		Assert.assertTrue(spaceValue.equals(expectedValue), "Assert that clicking on a space after it has already been clicked on by another user doesn't change its value during a currently running game.");
		
	}
	
	@AfterMethod()
	public void closeBrowser() {
		System.out.println("Closing Browser...");
        driver.quit();
	}

}
