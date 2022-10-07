package com.tyss.trello.pages;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.tyss.trello.util.WebActionUtil;

/**
 * Description This class has the implementations of the Home page
 * 
 * @author Tanu katiyar
 */
public class Trello_HomePage {

	public WebDriver driver;
	public WebActionUtil actionUtil;
	public long ETO;

	public Trello_HomePage(WebDriver driver, Long ETO, WebActionUtil actionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.actionUtil = actionUtil;
		this.ETO = ETO;
	}

	/* Create New Board */
	@FindBy(xpath = "//*[contains(text(),'Create new board') or contains(text(),'Create your first board')]")
	private WebElement btnCreateNewBoard;

	/* Add Board Title text field */
	@FindBy(xpath = "//div[text()='Board title']/parent::label/input")
	private WebElement tbAddBoardTitle;

	/* Create Board Button */
	@FindBy(xpath = "//button[contains(text(),'Create')]")
	private WebElement btnCreateBoard;

	/* Create Board Button */
	@FindBy(xpath = "//p[text()='Create']")
	private WebElement btnCreate;

	/* Create Board Button */
	@FindBy(xpath = "//p[contains(text(),'A board is made up of cards')]")
	private WebElement optCreateBorad;

	/* Settings Tab */
	@FindBy(xpath = "//span[contains(text(),'Settings')]")
	private WebElement tabSettings;

	/* Delete This Workspace Button */
	@FindBy(xpath = "//button[contains(text(),'Delete this Workspace?')]")
	private WebElement btnDeleteThisWorkspace;

	/* Delete Workspace Button */
	@FindBy(xpath = "//button[contains(text(),'Delete Workspace')]")
	private WebElement btnDeleteWorkspace;

	/* Create a workspace Button */
	@FindBy(xpath = "//button[contains(text(),'Create a workspace')]")
	private WebElement btnCreateAWorkspace;

	/* Confirm Workspace Name Text box */
	@FindBy(id = "confirmWorkspaceName")
	private WebElement tbConfirmWorkspaceName;

	/* Workspace name text box */
	@FindBy(xpath = "//label[text()='Workspace name']/following-sibling::input")
	private WebElement tbWorkspaceName;

	/* Continue Button */
	@FindBy(xpath = "//button[contains(text(),'Continue')]")
	private WebElement btnContinue;

	/* Workspace Type drop down */
	@FindBy(xpath = "//div[contains(text(),'Choose…')]")
	private WebElement ddWorkspaceType;

	/* Workspace Name text */
	private WebElement txtWorkspaceName(String workspaceName) {
		String xpath = "//span[contains(text(),'" + workspaceName + "')]";
		return driver.findElement(By.xpath(xpath));
	}

	/* Workspace Type drop down */
	@FindBy(xpath = "//span[contains(text(),'has been deleted')]")
	private WebElement txtWorkspaceHasBeenDeleted;

	/**
	 * Description: Method implements to click on Create New Board Button
	 * 
	 * @author Sushmita P H
	 */
	public synchronized void clkCreateNewBoard() {
		try {
			actionUtil.clickOnElementUsingJS(btnCreateNewBoard, "Create New Board Button");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Create New Board Button");
			Assert.fail("Unable to perform action on Create New Board Button");
		}
	}

	/**
	 * Description: Method implements to click on Create Board Button
	 * 
	 * @author Tanu
	 */
	public synchronized void clkCreateBoard() {
		try {
			actionUtil.clickOnElementUsingJS(btnCreate, "Create New Board Button");
			actionUtil.poll(2000);
			actionUtil.clickOnElementUsingJS(optCreateBorad, "Create New Board Button");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Create New Board Button");
			Assert.fail("Unable to perform action on Create New Board Button");

		}
	}

	/**
	 * Description: Method implements to Add Board Title
	 * 
	 * @author Sushmita P H
	 * @param boardTitle
	 */
	public synchronized void setBoardTitle(String boardTitle) {
		try {
			actionUtil.poll(2000);
			actionUtil.typeText(tbAddBoardTitle, boardTitle, "Add Board Title Text field");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to Set Board Title Text field");
			Assert.fail("Unable to Set Board Title Text field");
		}
	}

	/**
	 * Description: Method implements to click on Create New Board Button
	 * 
	 * @author Sushmita P H
	 */
	public synchronized void clkCreateBoardBtn() {
		try {
			actionUtil.clickOnElement(btnCreateBoard, "Create Board Button");
			actionUtil.validationinfo("Successfully created Board", "blue");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Create Board Button");
			Assert.fail("Unable to perform action on Create Board Button");

		}
	}

	/**
	 * Description: Method to validate workspace is deleted
	 * 
	 * @author Sajal
	 * @param workspaceName
	 */
	public synchronized void validateWorkspaceIsDeleted(String workspaceName) {
		try {
			actionUtil.validateisElementDisplayed(txtWorkspaceHasBeenDeleted,
					"The Workspace " + workspaceName + " has been deleted Text",
					workspaceName + " workspace is deleted successfully", workspaceName + " workspace is not deleted",
					"green");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to validate " + workspaceName + " workspace is deleted");
			Assert.fail("Unable to validate " + workspaceName + " workspace is deleted");
		}
	}

	/**
	 * Description: Method implements to click on Settings Tab
	 * 
	 * @author Sajal
	 */
	public synchronized void clkSettingsTab() {
		try {
			actionUtil.waitForElement(tabSettings, "Settings Tab");
			actionUtil.clickOnElement(tabSettings, "Settings Tab");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Settings Tab");
			Assert.fail("Unable to perform action on Settings Tab");
		}
	}

	/**
	 * Description: Method implements to click on Delete This Workspace Button
	 * 
	 * @author Sajal
	 */
	public synchronized void clkDeleteThisWorkspace() {
		try {
			actionUtil.waitForElement(btnDeleteThisWorkspace, "Delete This Workspace Button");
			actionUtil.clickOnElement(btnDeleteThisWorkspace, "Delete This Workspace Button");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Delete This Workspace Button");
			Assert.fail("Unable to perform action on Delete This Workspace Button");
		}
	}

	/**
	 * Description: Method implements to click on Delete Workspace Button
	 * 
	 * @author Sajal
	 */
	public synchronized void clkDeleteWorkspace() {
		try {
			actionUtil.waitForElement(btnDeleteWorkspace, "Delete Workspace Button");
			actionUtil.clickOnElement(btnDeleteWorkspace, "Delete Workspace Button");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Delete Workspace Button");
			Assert.fail("Unable to perform action on Delete Workspace Button");
		}
	}

	/**
	 * Description: Method implements to click on Create a workspace Button
	 * 
	 * @author Sajal
	 */
	public synchronized void clkCreateAWorkspace() {
		try {
			actionUtil.waitForElement(btnCreateAWorkspace, "Create a workspace Button");
			actionUtil.clickOnElement(btnCreateAWorkspace, "Create a workspace Button");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Create a workspace Button");
			Assert.fail("Unable to perform action on Create a workspace Button");
		}
	}

	/**
	 * Description: Method implements to enter data in Confirm Workspace Name Text
	 * box
	 * 
	 * @author Sajal
	 * @param workspaceName
	 */
	public synchronized void setConfirmWorkspaceName(String workspaceName) {
		try {
			actionUtil.typeText(tbConfirmWorkspaceName, workspaceName, "Confirm Workspace Name Text box");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to set Confirm Workspace Name Text box");
			Assert.fail("Unable to set Confirm Workspace Name Text box");
		}
	}

	/**
	 * Description: Method implements to enter data in Workspace Name Text box
	 * 
	 * @author Sajal
	 * @param workspaceName
	 */
	public synchronized void setWorkspaceName(String workspaceName) {
		try {
			actionUtil.typeText(tbWorkspaceName, workspaceName, "Workspace Name Text box");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to set Workspace Name Text box");
			Assert.fail("Unable to set Workspace Name Text box");
		}
	}

	/**
	 * Description: Method implements to click on Continue Button
	 * 
	 * @author Sajal
	 */
	public synchronized void clkContinueBtn() {
		try {
			actionUtil.waitForElement(btnContinue, "Continue Button");
			actionUtil.clickOnElement(btnContinue, "Continue Button");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Continue Button");
			Assert.fail("Unable to perform action on Continue Button");
		}
	}

	/**
	 * Description: Method implements to select option in Workspace Type drop down*
	 * 
	 * @author Sajal
	 */
	public synchronized void clkWorkspaceTypeDD() {
		try {
			// actionUtil.waitForElement(ddWorkspaceType, "Workspace Type drop down*");

			actionUtil.poll(3000);

			// ddWorkspaceType.sendKeys(Keys.TAB);
			Robot r = new Robot();
			// for (int i = 1; i <= 3; i++) {
			// r.keyPress(KeyEvent.VK_SHIFT);
			// r.keyPress(KeyEvent.VK_TAB);
			// r.keyRelease(KeyEvent.VK_TAB);
			// r.keyRelease(KeyEvent.VK_SHIFT);
			// }
			actionUtil.clickOnElement(ddWorkspaceType, "Workspace Type drop down*");
			actionUtil.poll(3000);
			driver.findElement(By.xpath("//span[@aria-live='polite']/child::span[contains(text(),'Other')]")).click();

			System.out.println("bye");
			r.keyPress(KeyEvent.VK_DOWN);
			r.keyRelease(KeyEvent.VK_DOWN);
			actionUtil.poll(3000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			actionUtil.poll(3000);
			System.out.println("hiii");
			ddWorkspaceType.sendKeys(Keys.ENTER);
			actionUtil.poll(3000);
			System.out.println("pass");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Workspace Type drop down*");
			// Assert.fail("Unable to perform action on Workspace Type drop down*");
		}
	}
	
	/**
	 * Description: Method implements to click on Create New Board Button
	 * 
	 * @author Sushmita P H
	 */
	public synchronized void clkCreateNewBoard1() {
		try {
			btnCreateNewBoard.click();
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Create New Board Button");
			Assert.fail("Unable to perform action on Create New Board Button");
		}
	}

}
