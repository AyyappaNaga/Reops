package com.tyss.trello.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.fasterxml.jackson.databind.deser.Deserializers.Base;
import com.paulhammant.ngwebdriver.NgWebDriver;
import com.tyss.trello.baseutil.BaseTest;
import com.tyss.trello.commonutils.ExcelUtil;
import com.tyss.trello.reports.ExtentManager;

/**
 * Description: All the action utilities added in this class e.g click on
 * element, WaitforElement etc
 * 
 * @author : Sajal
 */
public class WebActionUtil {
	public WebDriver driver;
	public WebDriverWait wait;
	public long ETO;
	public JavascriptExecutor jsExecutor;
	public Actions action;
	public static TakesScreenshot screenshot;
	public String mainWindowID;

	public WebActionUtil(WebDriver driver, long ETO) {
		this.driver = driver;
		this.ETO = ETO;
		wait = new WebDriverWait(driver, ETO);
		jsExecutor = (JavascriptExecutor) driver;
		action = new Actions(driver);
		screenshot = (TakesScreenshot) driver;
	}

	/**
	 * Description Method to provide info in the log,testNg reports
	 * 
	 * @author Sajal
	 * @param message
	 */
	public static void info(String message) {
		BaseTest.logger.info(message);
		// ExtentHCLManager.getTestReport().info(message);
	}

	/**
	 * Description Method for the error updation in logs
	 * 
	 * @author Sajal
	 * @param message
	 */
	public static void error(String message) {
		BaseTest.logger.error(message);
		// ExtentHCLManager.getTestReport().error(message);
	}

	/**
	 * Description Method to provide info in the extent report
	 * 
	 * @author Sajal
	 * @param message
	 */
	public static void extentinfo(String message) {
		ExtentManager.getTestReport().info(message);
	}

	/**
	 * Description Method for the pass updation in extent Report
	 * 
	 * @author Sajal
	 * @param message
	 */
	public static void pass(String message) {
		ExtentManager.getTestReport().pass(MarkupHelper.createLabel(message, ExtentColor.GREEN));
	}

	/**
	 * Description Method for the info/error updation in extent Report
	 * 
	 * @author Sajal
	 * @param message
	 * @param color
	 */
	public static void validationinfo(String message, String color) {
		if (color.equalsIgnoreCase("blue"))
			ExtentManager.getTestReport().info(MarkupHelper.createLabel(message, ExtentColor.BLUE));
		else if (color.equalsIgnoreCase("green"))
			ExtentManager.getTestReport().pass(MarkupHelper.createLabel(message, ExtentColor.GREEN));
		else if (color.equalsIgnoreCase("red"))
			ExtentManager.getTestReport().fail(MarkupHelper.createLabel(message, ExtentColor.RED));
	}

	/**
	 * Description Method for the error updation in extent Report
	 * 
	 * @author Sajal
	 * @param message
	 */
	public static void fail(String message) {
		ExtentManager.getTestReport().fail(MarkupHelper.createLabel(message, ExtentColor.RED));
	}

	/**
	 * Description Method for fetching Current Date Time
	 * 
	 * @author Sajal
	 */
	public static String getCurrentDateTime() {
		DateFormat customFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		Date currentDate = new Date();
		return customFormat.format(currentDate);
	}

	/**
	 * Description Method to delete the directory
	 * 
	 * @author Sajal
	 * @param pathToDeleteFolder
	 */
	public static void deleteDir(String pathToDeleteFolder) {
		File extefolder = new File(pathToDeleteFolder);
		if ((extefolder.exists())) {
			deleteFolderDir(extefolder);
		}
	}

	/**
	 * Description Method to delete folder directory
	 * 
	 * @author Sajal
	 * @param folderToDelete
	 */
	public static void deleteFolderDir(File folderToDelete) {
		File[] folderContents = folderToDelete.listFiles();
		if (folderContents != null) {
			for (File folderfile : folderContents) {
				if (!Files.isSymbolicLink(folderfile.toPath())) {
					deleteFolderDir(folderfile);
				}
			}
		}
		folderToDelete.delete();
	}

	/**
	 * Description Capture the screenshot of the complete screen
	 * 
	 * @author Sajal
	 * @param path
	 * @return destinationPath
	 */
	public static String getScreenShot(String path) {
		File src = (screenshot.getScreenshotAs(OutputType.FILE));
		String currentDateTime = getCurrentDateTime();
		String destinationPath = path + BaseTest.className + "-" + currentDateTime + ".png";
		File destination = new File(destinationPath);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "./Screenshots/" + BaseTest.className + "-" + currentDateTime + ".png";
	}

	/**
	 * Description Clear the Text
	 * 
	 * @author Sajal
	 * @param element
	 * @param elementName
	 */
	public void clearText(WebElement element, String elementName) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.clear();
			info("Cleared the text present in" + elementName);
		} catch (Exception e) {
			error("Unable to clear the text in " + elementName);
		}
	}

	/**
	 * Description To Enter the Text to the Text filed
	 * 
	 * @author Sajal
	 * @param element
	 * @param value
	 * @param elementName
	 */
	public synchronized void typeText(WebElement element, String value, String elementName) {
		try {
			info("Enter the value into " + elementName);
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.sendKeys(value);
			info(value + " typed into " + elementName);
		} catch (AssertionError error) {
			error(" Unable to type " + value + " into " + elementName);
			Assert.fail("Unable to type in " + elementName);
		} catch (Exception e) {
			error(" Unable to type " + value + "into " + elementName);
			Assert.fail("Unable to type in " + elementName);
		}
	}

	/**
	 * 
	 * Description : This method selects the options by value using Select Class.
	 * 
	 * @author Manish Kumar C D.
	 * @param element
	 * @param value
	 * @param elementName
	 */
	public synchronized void selectByValue(WebElement element, String value, String elementName) {
		try {
			waitForElement(element, elementName);
			Select sc = new Select(element);
			sc.selectByValue(value);
			info(value + " is selected from " + elementName);
			extentinfo(value + " is selected from " + elementName);
		} catch (Exception e) {
			error("Unable to select " + value + " from " + elementName);
			fail("Unable to select " + value + " from " + elementName);
			Assert.fail("Unable to select " + value + " from " + elementName);
		}
	}

	/**
	 * Description To Select the value by the Visible Text
	 * 
	 * @author Sajal
	 * @param element
	 * @param value
	 * @param elementName
	 */
	public synchronized void selectByText(WebElement element, String value, String elementName) {
		try {
			info("Select the value " + value + " from " + elementName);
			Select selecvalue = new Select(element);
			selecvalue.selectByVisibleText(value);
			info("Able to select the value : " + value + " from " + elementName);
			extentinfo("Able to select the value  : " + value + " from " + elementName);
		} catch (Exception e) {
			error("Unable to select the value : " + value + " from " + elementName);
			fail("Unable to select the value : " + value + " from " + elementName);
			Assert.fail("Unable to select the value : " + value + " from " + elementName);
		}
	}

	/**
	 * Description This method will deselect all the value from drop down
	 * 
	 * @author Tanu
	 * @param element
	 */
	public synchronized void deSelectAll(WebElement element) {
		try {
			info("Deselect all value from dropdown");
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			Select selecvalue = new Select(element);
			selecvalue.deselectAll();
			info("Able to deselect all value from dropdown");
			extentinfo("Able to deselect all value from dropdown");
		} catch (Exception e) {
			error("Unable to deselect all value from dropdown");
			fail("Unable to deselect all value from dropdown");
			Assert.fail("Unable to deselect all value from dropdown");
		}
	}

	/**
	 * Description :This method will perform mouse over action context click
	 * 
	 * @author Tanu
	 * @param element
	 */
	public synchronized void mouseHoverToContextClick(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			action.contextClick(element).perform();
			info("Able to perform mouse hover context click");
			extentinfo("Able to perform mouse hover context click");
		} catch (Exception e) {
			error("Unable to perform mouse hover context click");
			fail("Unable to perform mouse hover context click");
			Assert.fail("Unable to perform mouse hover context click");
		}
	}

	/**
	 * Description : Scroll to the Element
	 * 
	 * @author Sajal
	 * @param element
	 * @param elementName
	 */
	public void scrollToElement(WebElement element, String elementName) {
		info("Scroll till the " + elementName);
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
			info("Scroll till the " + elementName + " completed");
		} catch (Exception e) {
			error("Scroll till the " + elementName + " failed");
		}
	}

	/**
	 * Description Scroll up
	 * 
	 * @author Sajal
	 */
	public synchronized void scrollUp() {
		try {
			jsExecutor.executeScript("window.scrollTo(document.body.scrollHeight , 0)");
			info("Scroll up");
		} catch (Exception e) {
			error("Scroll up failed");
		}
	}

	/**
	 * Description : Wait for given time
	 * 
	 * @author Sajal
	 * @param millis
	 */
	public synchronized static void poll(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Description : Wait for the angular page load
	 * 
	 * @author Sajal
	 */
	public synchronized void waitForAngularPageload() {
		NgWebDriver ngDriver = new NgWebDriver(jsExecutor);
		ngDriver.waitForAngularRequestsToFinish();
	}

	/**
	 * Description Wait for the angular page to load
	 * 
	 * @author Sajal
	 */
	public synchronized void waitForAngularPageToLoad() {
		try {
			ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					return Boolean.valueOf(((JavascriptExecutor) driver)
							.executeScript("return (window.angular !== undefined) "
									+ "&& (angular.element(document).injector() !== undefined) "
									+ "&& (angular.element(document).injector().get('$http').pendingRequests.length === 0)")
							.toString());
				}
			};
			WebDriverWait wait = new WebDriverWait(driver, 2);
			wait.until(pageLoadCondition);
		} catch (Exception e) {
			// error("Unable to load the page correctly");
		}
	}

	/**
	 * Description Wait for the Element is displayed with expected conditions
	 * 
	 * @author Sajal
	 * @param element
	 * @param elementName
	 */
	public void waitForElement(WebElement element, String elementName) {
		try {
			info("Wait for " + elementName);
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(element)) != null);

		} catch (Exception e) {
			error(elementName + " is not visible");

		}
	}

	/**
	 * Description Wait for the load by checking readystate using JavascriptExecutor
	 * 
	 * @author Sajal
	 * @param timeInSecs
	 */
	public synchronized void waitForPageLoadJS(int timeInSecs) {
		new WebDriverWait(driver, timeInSecs).until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
				.executeScript("return document.readyState").equals("complete"));
	}

	/**
	 * Description Wait for invisibility of element
	 * 
	 * @author Sajal jain
	 * @param element
	 * @param elementName
	 * @param timeInSecs
	 */
	public synchronized void waitForInvisibilityOfElement(WebElement element, String elementName, Long timeoutInSecs) {
		info("Wait for invisiblity of " + elementName);
		WebDriverWait wait1 = new WebDriverWait(driver, timeoutInSecs);
		try {
			wait1.until(ExpectedConditions.invisibilityOf(element));
		} catch (Exception e) {
			error(elementName + " is still visible");
			fail(elementName + " is still visible");
			Assert.fail(elementName + " is still visible");

		}
	}

	/**
	 * Description Wait for the Element is displayed with expected conditions
	 * 
	 * @author Sajal, Ashish
	 * @param element
	 * @param elementName
	 */
	public void waitForElements(List<WebElement> elements, String elementName) {
		try {
			info("Wait for " + elementName);
			wait.until(ExpectedConditions.visibilityOfAllElements(elements));
			Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfAllElements(elements)) != null);
		} catch (Exception e) {
			error(elementName + " is not visible");
		}
	}

	/**
	 * Description Wait for Visibility of element
	 * 
	 * @author Sajal jain
	 * @param element
	 * @param elementName
	 */
	public synchronized void waitForVisibilityOfElement(WebElement element, String elementName) {// , Long
																									// timeoutInSecs) {
		info("Wait for Visiblity of " + elementName);
		// WebDriverWait wait1 = new WebDriverWait(driver, timeoutInSecs);
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			error(elementName + " is not visible");
		}
	}

	/**
	 * Description :Checks whether an element is visible
	 * 
	 * @author Sajal
	 * @param element
	 * @param elementName
	 */
	public synchronized boolean isElementVisible(WebElement element, String elemantName) {

		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			info(elemantName + " is visible");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Description Check whether the Element is displayed with expected conditions
	 * 
	 * @author Sajal
	 * @param element
	 * @param elementName
	 */
	public synchronized boolean isElementClickable(WebElement element, String elementName) {

		info("Validate " + elementName + " is clickable");
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return true;
		} catch (Exception e) {
			error(elementName + " is not clickable");
			return false;
		}
	}

	/**
	 * Description :File upload by using the image path
	 * 
	 * @author Sajal
	 * @param imagePath
	 */
	public synchronized void upload(String imagePath) {
		StringSelection stringSelection = new StringSelection(imagePath);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Description : This method validates the current url of the application
	 * 
	 * @author Manish Kumar C D.
	 * @param expectedUrl
	 * @param elementName
	 */
	public synchronized void validateUrl(String expectedUrl, String elementName) {
		try {
			String actualUrl = driver.getCurrentUrl();
			Assert.assertEquals(expectedUrl, actualUrl);
			info(elementName + " page is displayed");
			validationinfo(elementName + " page is displayed", "green");
		} catch (Exception e) {
			error(elementName + " page is not displayed");
			fail(elementName + " page is not displayed");
			Assert.fail(elementName + " page is not displayed");
		}
	}

	/**
	 * Description Validate the Text partially
	 * 
	 * @author Sajal jain
	 * @param expected
	 * @param element
	 * @param elementname
	 * @param validationPassMessage
	 * @param validationFailMessage
	 * @param color
	 */
	public synchronized void validatePartialText(String expected, WebElement element, String elementname,
			String validationPassMessage, String validationFailMessage, String color) {
		String actual = null;
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			actual = element.getText();
			Assert.assertTrue(actual.contains(expected));
			info("Expected text : " + expected + " is present in " + actual + " text in the application");
			validationinfo(validationPassMessage, color);
			info(validationPassMessage);
		} catch (Exception e) {
			error("Expected text : " + expected + " is not present in " + actual + " text in the application ");
			error(validationFailMessage);
			fail(validationFailMessage);
		}
	}

	/**
	 * Description Validate the Text
	 * 
	 * @author Sajal
	 * @param expected
	 * @param element
	 * @param elementname
	 * @param validationPassMessage
	 * @param validationFailMessage
	 * @param color
	 */
	public synchronized void validatetext(String expected, WebElement element, String elementname,
			String validationPassMessage, String validationFailMessage, String color) {
		wait.until(ExpectedConditions.visibilityOf(element));
		String actual = element.getText();
		try {
			Assert.assertEquals(actual, expected);
			info("Expected text : " + expected + " is matching with the : " + actual + " text in the application ");
			validationinfo(validationPassMessage, color);
			info(validationPassMessage);
		} catch (Exception e) {
			error("Expected text : " + expected + " is not  matching with the : " + actual
					+ " text in the application ");
			error(validationFailMessage);
			fail(validationFailMessage);
		}
	}

	/**
	 * Description Check whether the Element is displayed with expected conditions
	 * 
	 * @author Sajal
	 * @param element
	 * @param elementName
	 * @param validationPassMessage
	 * @param validationFailMessage
	 * @param color
	 */
	public synchronized void validateisElementDisplayed(WebElement element, String elementName,
			String validationPassMessage, String validationFailMessage, String color) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			Assert.assertTrue(element.isDisplayed());
			validationinfo(validationPassMessage, color);
			info(validationPassMessage);
		} catch (AssertionError e) {
			fail(validationFailMessage);
			error(validationFailMessage);
		}
	}

	/**
	 * Description : Verify the attribute value of an element
	 * 
	 * @author Sajal
	 * @param element
	 * @param attribute
	 * @param valuetobecompared
	 * @param elementName
	 * @param validationPassMessage
	 * @param validationFailMessage
	 * @param color
	 */
	public synchronized void validateAttribute(WebElement element, String attribute, String valuetobecompared,
			String elementName, String validationPassMessage, String validationFailMessage, String color) {
		String actualvalue = element.getAttribute(attribute);
		try {
			wait.until(ExpectedConditions.attributeContains(element, attribute, valuetobecompared));
			Assert.assertEquals(element.getAttribute(attribute), valuetobecompared);
			info("Expected attribute value : " + valuetobecompared + " is matching with the actual attribute value : "
					+ actualvalue + " of " + elementName);
			info(validationPassMessage);
			validationinfo(validationPassMessage, color);
		} catch (Exception e) {
			error("Expected attribute value : " + valuetobecompared
					+ " is not matching with the actual attribute value : " + actualvalue + " of " + elementName);
			error(validationFailMessage);
			fail(validationFailMessage);
		}
	}

	/**
	 * Description : Validate the value entered in an element
	 * 
	 * @author Sajal
	 * @param expectedValue
	 * @param actualvalue
	 * @param elementName
	 * @param validationPassMessage
	 * @param validationFailMessage
	 * @param color
	 */
	public synchronized void validateEnteredValue(String expectedValue, String actualvalue, String elementName,
			String validationPassMessage, String validationFailMessage, String color) {
		try {
			Assert.assertEquals(expectedValue, actualvalue);
			info("Valid value is entered in " + elementName);
			info(validationPassMessage);
			validationinfo(validationPassMessage, color);
		} catch (Exception e) {
			error("Valid value is not entered in " + elementName);
			error(validationFailMessage);
			fail(validationFailMessage);
		}
	}

	/**
	 * Description Check whether the Element is Enabled
	 * 
	 * @author Sajal
	 * @param element
	 * @param elementName
	 * @param validationPassMessage
	 * @param validationFailMessage
	 * @param color
	 */
	public synchronized void validateisElementEnabled(WebElement element, String elementName,
			String validationPassMessage, String validationFailMessage, String color) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			Assert.assertTrue(element.isEnabled());
			validationinfo(validationPassMessage, color);
			info(validationPassMessage);
		} catch (AssertionError e) {
			fail(validationFailMessage);
			error(validationFailMessage);
		}
	}

	/**
	 * Description :Retrieves text of the web element
	 * 
	 * @author Sajal
	 * @param element
	 * @param attribute
	 * @param elementName
	 * @param validationPassMessage
	 * @param validationFailMessage
	 * @param color
	 */
	public synchronized void validateAttributeIsEmpty(WebElement element, String attribute, String elementName,
			String validationPassMessage, String validationFailMessage, String color) {
		String actualvalue = element.getAttribute(attribute);
		try {
			if (actualvalue.isEmpty()) {
				validationinfo(validationPassMessage, color);
				info(validationPassMessage);
			} else {
				error(validationFailMessage);
				fail(validationFailMessage);
			}
		} catch (Exception e) {
			error(validationFailMessage);
			fail(validationFailMessage);
		}
	}

	/**
	 * Description Check whether the Element is selected with expected conditions
	 * 
	 * @author Sajal
	 * @param element
	 * @param elementName
	 * @param validationPassMessage
	 * @param validationFailMessage
	 * @param color
	 */
	public synchronized void validateisElementSelected(WebElement element, String elementName,
			String validationPassMessage, String validationFailMessage, String color) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			Assert.assertTrue(element.isSelected());
			validationinfo(validationPassMessage, color);
			info(validationPassMessage);
		} catch (AssertionError e) {
			fail(validationFailMessage);
			error(validationFailMessage);
		}
	}

	/**
	 * Description : Get file path
	 * 
	 * @author Sajal
	 * @param fileFormat
	 * @return filePath + fileName
	 */

	public synchronized String getSampleFilePath(String fileFormat) {
		String filePath = System.getProperty("user.dir") + "\\data\\SampleFiles\\";
		String fileName = "Sample" + fileFormat.toUpperCase() + "." + fileFormat;
		return filePath + fileName;
	}

	/**
	 * Description : Retrives the value entered in an element
	 * 
	 * @author Sajal
	 * @param elementId
	 * @return entereddValue
	 */
	public synchronized String getTextUsingJS(String elementId) {
		String entereddValue = null;
		try {
			poll(1000);
			String script = "return document.getElementById('" + elementId + "').value";
			entereddValue = (String) (jsExecutor.executeScript(script));
			// info("The value entered in element is " + entereddValue);
		} catch (Exception e) {
			error("Unable to get entered value from element with id " + elementId);
		}
		return entereddValue;
	}

	/**
	 * Description :Retrieves text of the Webelement
	 * 
	 * @author Sajal
	 * @param element
	 * @param elementName
	 */
	public synchronized String getText(WebElement element, String elementName) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			info(elementName + " is visible");
			String text = element.getText();
			info("Retrieved the text of the element " + elementName);
			return text;
		} catch (Exception e) {
			error("Failed to retrieve the text of the element " + elementName);
			return null;
		}
	}

	/**
	 * Description :Compares text of the webelement
	 * 
	 * @author Sajal
	 * @param actual
	 * @param expected
	 * @param color
	 */
	public synchronized void comparetText(String actual, String expected, String color) {
		try {
			Assert.assertEquals(actual, expected);
			info(actual + " is matching with " + expected);
			validationinfo(actual + " is matching with " + expected, color);
		} catch (Exception e) {
			error(actual + " is not  matching with " + expected);
			fail(actual + " is not  matching with " + expected);
		}
	}

	/**
	 * Description :Retrieves text of the Web element
	 * 
	 * @author Sajal
	 * @param element
	 */
	public synchronized String getTextusingJSWithXpath(WebElement element) {
		String entereddValue = null;
		try {
			poll(5000);
			entereddValue = (String) (jsExecutor.executeScript("return arguments[0].value", element));
			info("The value entered in element is" + entereddValue);
		} catch (Exception e) {
			error("Unable to entered value using JS with xpath");
		}
		return entereddValue;
	}

	/**
	 * Description : Get file Name
	 * 
	 * @author Sajal
	 * @param fileFormat
	 * @return fileName
	 */
	public synchronized String getSampleFileName(String fileFormat) {
		String fileName = "Sample" + fileFormat.toUpperCase() + "." + fileFormat;
		return fileName;
	}

	/**
	 * Description Method to return data for excel upload
	 * 
	 * @author Sajal
	 * @param data
	 * @param format
	 */
	public synchronized Map<String, String> getData_FormatMap(String[] data, String[] format) {
		Map<String, String> mapDataAndFormat = new LinkedHashMap<String, String>();
		for (int i = 0; i < data.length; i++) {
			mapDataAndFormat.put(data[i], format[i]);
		}
		return mapDataAndFormat;
	}

	/**
	 * Description Method to switch driver focus to New Tab/window
	 * 
	 * @author Sajal jain
	 */
	public synchronized void switchToNewTab() {
		try {
			mainWindowID = driver.getWindowHandle();
			Set<String> allWindowID = driver.getWindowHandles();
			for (String id : allWindowID) {
				if (!id.equals(mainWindowID)) {
					driver.switchTo().window(id);
				}
			}
			info("Switched to New Tab");
			extentinfo("Switched to New Tab");
		} catch (Exception e) {
			error("Unable to switch to New Tab");
			fail("Unable to switch to New Tab");
			Assert.fail("Unable to switch to New Tab");
		}
	}

	/**
	 * 
	 * Description Method to switch driver focus to Main Tab/window
	 * 
	 * @author Sajal jain
	 */
	public synchronized void switchToMainTab() {
		try {
			driver.switchTo().window(mainWindowID);
			info("Switched to Main Tab");
			extentinfo("Switched to Main Tab");
		} catch (Exception e) {
			error("Unable to switch to Main Tab");
			fail("Unable to switch to Main Tab");
			Assert.fail("Unable to switch to Main Tab");
		}
	}

	/**
	 * Description Method to select calendar date
	 * 
	 * @param elementId
	 * @param date
	 * @param elementName
	 * @author vikas
	 */
	public synchronized void select_CalendarDate(String elementId, String date, String elementName) {
		try {
			WebActionUtil.poll(1000);
			waitForElement(driver.findElement(By.id(elementId)), elementName);
			String script = "document.getElementById('" + elementId + "').value=" + "\"" + date + "\" ";
			jsExecutor.executeScript(script);
			info(date + " is selected");
		} catch (Exception e) {
			error("Unable to select " + date);
			Assert.fail("Unable to select " + date);
		}
	}

	/**
	 * Description Click on the check box
	 * 
	 * @author Sajal
	 * @param element
	 * @param elementName
	 */
	public void clickCheckBox(WebElement element, String elementname) {

		wait.until(ExpectedConditions.visibilityOf(element));
		if (element.isSelected()) {
			info(elementname + " selected by default");
			extentinfo(elementname + " selected by default");
		} else {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			info(elementname + " is selected");
			extentinfo(elementname + " is selected");
		}
	}

	/**
	 * Description To create the duration of the Test Run
	 * 
	 * @author Sajal
	 * @param millis
	 */
	public static String formatDuration(long millis) {
		long seconds = (millis / 1000) % 60;
		long minutes = (millis / (1000 * 60)) % 60;
		long hours = millis / (1000 * 60 * 60);

		StringBuilder b = new StringBuilder();
		b.append(hours == 0 ? "00" : hours < 10 ? String.valueOf("0" + hours) : String.valueOf(hours));
		b.append(":");
		b.append(minutes == 0 ? "00" : minutes < 10 ? String.valueOf("0" + minutes) : String.valueOf(minutes));
		b.append(":");
		b.append(seconds == 0 ? "00" : seconds < 10 ? String.valueOf("0" + seconds) : String.valueOf(seconds));
		return b.toString();
	}

	/**
	 * Description fetches all the login details from AAM Test Data
	 * 
	 * @author Sajal Jain, Manish Kumar C D
	 * @param map
	 * @param esaEmpCode
	 */
	public synchronized static Map<String, String[]> getLoginDetailsForRoles(Map<String, String> map) {
		// String xlpath = System.getProperty("user.dir") + "/data/Data.xlsx";
		String xlpath = BaseTest.TESTDATAEXCELPATH;
		String[] loginData = new String[1];
		Set<String> setRoles = map.keySet();
		String strQuery = null;
		String empCode = null;
		String empRole = null;
		Map<String, String[]> mapLoginDetails = new LinkedHashMap<>();
		for (String role : setRoles) {
			empCode = map.get(role);
			if (role.contains("Initiator")) {
				role = "Initiator";
				empRole = role;
			} else if (role.contains("Approver1")) {
				role = "Approver1";
				empRole = role;
			} else if (role.contains("Approver2")) {
				role = "Approver2";
				empRole = role;
			}
			strQuery = "SELECT * from LoginCredentials WHERE EmpCode='" + empCode + "' and Role = '" + empRole + "'";
			String employeedata[][] = null;
			// System.out.println(strQuery);
			employeedata = ExcelUtil.getRowDataFromExcelUsingFillo(xlpath, strQuery);

			for (String[] singleEmpData : employeedata) {

				mapLoginDetails.put(singleEmpData[0], new String[] { singleEmpData[1], singleEmpData[2] });
			}
		}
		return mapLoginDetails;
	}

	/**
	 * Description Mouse over on an element
	 * 
	 * @author Sajal
	 * @param element
	 * @param elementName
	 */
	public synchronized void actionMouseOver(WebElement element, String elementName) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			action.moveToElement(element).build().perform();
			info("Able to mouse over on " + elementName);
			extentinfo("Able to mouse over on " + elementName);
		} catch (Exception e) {
			error("Unable to mouse over on " + elementName);
			fail("Unable to mouse over on " + elementName);
			Assert.fail("Unable to mouse over on " + elementName);
		}
	}

	/**
	 * Description Method clicks on web element
	 * 
	 * @author Sajal
	 * @param element
	 * @param elementName
	 */
	public synchronized void clickOnElement(WebElement element, String elementName) {
		if (isElementClickable(element, elementName)) {
			element.click();
			info("Clicked on : " + elementName);
			extentinfo("Clicked on : " + elementName);
		} else {
			error("Unable to click on : " + elementName);
			fail("Unable to click on : " + elementName);
			Assert.assertTrue(wait.until(ExpectedConditions.elementToBeClickable(element)) == null);
		}
	}

	/**
	 * Description Click on the Element using JavaSCript
	 * 
	 * @author Sajal
	 * @param element
	 * @param elementName
	 */
	public void clickOnElementUsingJS(WebElement element, String elementName) {
		try {
			if (isElementClickable(element, elementName)) {
				jsExecutor.executeScript("arguments[0].click();", element);
				info("Clicked on : " + elementName);
				extentinfo("Clicked on : " + elementName);
			}
		} catch (NoSuchElementException e) {
			error("Unable to click on " + elementName);
			fail("Unable to click on " + elementName);
			Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(element)) == null);
		}
	}

	/**
	 * Description Click on element using action class
	 * 
	 * @author Sajal
	 * @param element
	 * @param elementName
	 */
	public synchronized void actionClick(WebElement element, String elementName) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			action.click(element).build().perform();
			info("Clicked on " + elementName);
			extentinfo("Clicked on " + elementName);
		} catch (Exception e) {
			error("Unable to click on " + elementName);
			fail("Unable to click on " + elementName);
			Assert.fail("Unable to click on " + elementName);
		}
	}

	/**
	 * Description Click on the check box using JavaScript
	 * 
	 * @author Sajal jain
	 * @param element
	 * @param elementName
	 */
	public void clickCheckBoxUsingJS(WebElement element, String elementName) {
		try {
			if (element.isSelected()) {
				info(elementName + " selected by default");
				extentinfo(elementName + " selected by default");
			} else {
				jsExecutor.executeScript("arguments[0].click();", element);
				info(elementName + " is selected");
				extentinfo(elementName + " is selected");
			}
		} catch (NoSuchElementException e) {
			error("Unable to click on " + elementName);
			fail("Unable to click on " + elementName);
			Assert.fail("Unable to click on " + elementName);
		}
	}

	/**
	 * Description Double Click On Element
	 * 
	 * @author Sajal
	 * @param element
	 * @param elementName
	 */
	public void doubleClickOnElement(WebElement element, String elementName) {
		try {
			action.doubleClick(element).perform();
			info("Double clicked on : " + elementName);
			extentinfo("Double clicked on : " + elementName);
		} catch (Exception e) {
			error("Unable to double click on " + elementName);
			fail("Unable to double click on " + elementName);
			Assert.fail("Unable to double click on " + elementName);
		}
	}

	/**
	 * Description Method to press Enter key
	 * 
	 * @author Sajal
	 */
	public synchronized void actionEnter() {
		try {
			action.sendKeys(Keys.ENTER).build().perform();
			info("Enter Key pressed");
			extentinfo("Enter Key  is pressed");
		} catch (Exception e) {
			error("Unable to press Enter Key");
			fail("Unable to press Enter Key");
			Assert.fail("Unable to press Enter Key");
		}
	}

	/**
	 * Description :This method move the mouse from current position to given method
	 *
	 * @author Tanu
	 * @param xOffset
	 * @param yOffset
	 */
	public void moveByOffset(int xOffset, int yOffset) {
		try {
			info("move mouse from current position to given method ");
			Actions action = new Actions(driver);
			action.moveByOffset(xOffset, yOffset).click().perform();
			pass("move mouse from current position to given method ");

		} catch (Exception e) {
			fail("Unable to move mouse from current position to given method ");

		}
	}

	/**
	 * Description Copy file from source directory to destination directory
	 * 
	 * @author Sajal, Vikas
	 * @param destinationPath
	 * @param imageName
	 */
	public static void copyFile(String destinationPath, String imageName) {
		String srcPath = System.getProperty("user.dir") + "./company_logo/" + imageName;
		File src = new File(srcPath);
		File destination = new File(destinationPath);
		try {
			FileUtils.copyFileToDirectory(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
