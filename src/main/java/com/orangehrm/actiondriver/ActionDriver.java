package com.orangehrm.actiondriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orangehrm.base.BaseClass;

public class ActionDriver {

	private WebDriver driver;
	private WebDriverWait wait;

	// Constructor
	/*public ActionDriver(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30)); //we can use config.properties file instead of hard coded value
	}*/
	
	/*public ActionDriver(WebDriver driver, int timeoutInSeconds) {
	this.driver = driver;
	this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
	} 
  */
	public ActionDriver(WebDriver driver) {
		this.driver = driver;
		int ExplicitWait = Integer.parseInt(BaseClass.getProp().getProperty("ExplicitWait"));
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(ExplicitWait));
	}
	

	// Method to click an element
	public void click(By by) {
		try {
			waitForElementToBeClickable(by);
			driver.findElement(by).click();
		} catch (Exception e) {

			System.out.println("unable to click element: " + e.getMessage());
		}
	}

	// Method to enter text into input field --Avoid Code Duplication - fix the multiple calling method
	public void enterText(By by, String value) {
		try {
			waitForElementToBeVisible(by);
			//driver.findElement(by).clear();
			//driver.findElement(by).sendKeys(value);
			WebElement element = driver.findElement(by);
			element.clear();
			element.sendKeys(value);
		} catch (Exception e) {
			System.out.println("unable to enter the value: " + e.getMessage());
		}
	}

	// Method to get text from input field
	public String getText(By by) {
		try {
			waitForElementToBeVisible(by);
			return driver.findElement(by).getText();
		} catch (Exception e) {
			System.out.println("unable to get the text: " + e.getMessage());
			return "";
		}
	}

	// Method to compare Two text
	public void comparetext(By by, String expectedText) {
		try {
			waitForElementToBeVisible(by);
			String actualText = driver.findElement(by).getText();

			if (expectedText.equals(actualText)) {
				System.out.println("Text are Matching: " + actualText + " equals " + expectedText);
			} else {
				System.out.println("Text are not Matching: " + actualText + " not equals " + expectedText);
			}
		} catch (Exception e) {
			System.out.println("unable to compare Texts: " + e.getMessage());
		}
	}

	// Method to check if an element is displayed
	/* public boolean isDisplayed(By by) {
		try {
			waitForElementToBeVisible(by);
			boolean isDisplayed = driver.findElement(by).isDisplayed();
			if (isDisplayed) {
				System.out.println("Element is visible");
				return isDisplayed;
			} else {
				return isDisplayed;
			}
		} catch (Exception e) {
			System.out.println("Element is not displayed: " + e.getMessage());
			return false;
		}
	} */
	
	//Simplified the method and remove redundant condition
	public boolean isDisplayed(By by) {
		try {
			waitForElementToBeVisible(by);
			return driver.findElement(by).isDisplayed();
		} catch (Exception e) {
			System.out.println("Element is not displayed: "+e.getMessage());
		}
		return false;
		
	}

	// Wait for the page to load
	public void waitForPageLoad(int timeOutInSec) {
		try {
			wait.withTimeout(Duration.ofSeconds(timeOutInSec)).until(WebDriver -> ((JavascriptExecutor) WebDriver)
					.executeScript("return document.readyState").equals("complete"));
			System.out.println("Page load successfully");
		} catch (Exception e) {
			System.out.println("Page did not load within " + timeOutInSec + "Seconds.Exception: " + e.getMessage());
		}
	}

	// scroll to element --Added a semicolon ; at the end of the script 
	public void scrollToElement(By by) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(by);
			js.executeScript("arguments[0],scrollIntoView(true); ", element);
		} catch (Exception e) {
			System.out.println("unable to locate element: " + e.getMessage());
		}
	}

	// Wait for element to be clickable
	private void waitForElementToBeClickable(By by) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			System.out.println("Element is not clickable: " + e.getMessage());

		}
	}

	// Wait for Element to be visible
	private void waitForElementToBeVisible(By by) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			System.out.println("Element is not visible: " + e.getMessage());

		}
	}
}
