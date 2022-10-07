package com.tyss.trello.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.tyss.trello.util.WebActionUtil;

/**
 * Description This class has the implementations of the Board page
 * 
 * @author Tanu katiyar
 */
public class Trello_BoardPage {

	public WebDriver driver;
	public WebActionUtil actionUtil;
	public long ETO;

	public Trello_BoardPage(WebDriver driver, long ETO, WebActionUtil actionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.actionUtil = actionUtil;
		this.ETO = ETO;
	}

	/* List Name Plus Icon */
	@FindBy(xpath = "//span[@class='icon-sm icon-add']")
	private WebElement imgPlusList;

	/* List Name Text */
	@FindBy(name = "name")
	private WebElement txtListName;

	/* List Name */
	private WebElement listName(String listName) {
		String xpath = "//textarea[text()='" + listName + "']";
		return driver.findElement(By.xpath(xpath));
	}

	/* Add List */
	@FindBy(xpath = "//div/input[@class='nch-button nch-button--primary mod-list-add-button js-save-edit']")
	private WebElement btnAddList;

	/* Add Card */
	@FindBy(xpath = "//span[text()='Add a card']")
	private WebElement txtAddCard;

	/* Invite Button */
	private WebElement btnInvite(String value) {
		String xpath = "//span[contains(text(),'" + value + "')] ";
		return driver.findElement(By.xpath(xpath));
	}

	/* Error Invite Message */
	@FindBy(xpath = "//div[@class='is-empty-text']")
	private WebElement txtInviteMsg;

	/* Send Invitation */
	@FindBy(xpath = "//button[text()='Send invitation']")
	private WebElement btnSendInvitation;

	/* Invite email address */
	@FindBy(xpath = "//input[contains(@placeholder,'Email address or name')]")
	private WebElement txtEmail;

	/* Add Another List text */
	@FindBy(xpath = "//span[text()='Add another list']")
	private WebElement txtAddAnotherList;

	/* To Do Text area */
	@FindBy(xpath = "//div[@class='list-header-target js-editing-target']/following-sibling::textarea[text()='To Do']")
	private WebElement taToDo;

	/* Card name text */
	@FindBy(xpath = "//div/textarea[@class='list-card-composer-textarea js-card-title']")
	private WebElement txtCardName;

	/* Card Text */
	@FindBy(xpath = "//textarea[@class='list-card-composer-textarea js-card-title']")
	private WebElement txtCard;

	/* Add Card button */
	@FindBy(xpath = "//div/input[@class='nch-button nch-button--primary confirm mod-compact js-add-card']")
	private WebElement btnAddCard;

	/* Add List */
	@FindBy(className = "nch-button nch-button--primary confirm mod-compact js-add-card")
	private WebElement lnkCreatedCard;

	/* Add List */
	@FindBy(xpath = "//span[@class='list-card-title js-card-name']")
	private WebElement lnkCreatedCardName;

	/* Pencil icon */
	@FindBy(xpath = "//span[contains(@class,'icon-sm icon-edit list-card-operation dark-hover js')]")
	private WebElement imgPencilIcon;

	/* Move button */
	@FindBy(xpath = "//a[@class='quick-card-editor-buttons-item js-move-card']/span[text()='Move']")
	private WebElement btnMove;

	/* Copy button */
	@FindBy(xpath = "//a[@class='quick-card-editor-buttons-item js-copy-card']/span[text()='Copy']")
	private WebElement btnCopy;

	/* list Option */
	@FindBy(className = "js-select-list")
	private WebElement ddList;

	/* Submit button Move/Copy */
	@FindBy(xpath = "//input[@class='nch-button nch-button--primary wide js-submit']")
	private WebElement btnSubmitCardAction;

	/* Archive button */
	@FindBy(xpath = "//a[@class='quick-card-editor-buttons-item js-archive']/span[text()='Archive']")
	private WebElement btnArchive;

	/* Show menu button */
	@FindBy(xpath = "//button[@aria-label='Show menu']")
	private WebElement btnShowMenu;

	/* More option */
	@FindBy(xpath = "//a[@class='board-menu-navigation-item-link js-open-more']")
	private WebElement optionMore;

	/* close Board option */
	@FindBy(xpath = "//a[@class='board-menu-navigation-item-link js-close-board']")
	private WebElement optionCloseBoard;

	/* close option */
	@FindBy(xpath = "//input[@class='js-confirm full nch-button nch-button--danger']")
	private WebElement optionClose;

	/* permanently delete board */
	@FindBy(xpath = "//button[text()='Permanently delete board']")
	private WebElement lnkPermanentlyDelete;

	/* permanently delete board */
	@FindBy(xpath = "//button[text()='Delete']")
	private WebElement btnDelete;

	/* list name */
	@FindBy(xpath = "//textarea[contains(@class,'list-header-name')]")
	private List<WebElement> txtCreatedListName;

	/* Validate list card */
	private List<WebElement> listCard(String listName) {
		String xpath = "//h2[text()='" + listName
				+ "']/parent::div/following-sibling::div//span[contains(@class,'list-card-title')]";
		return driver.findElements(By.xpath(xpath));
	}

	/* Checklist Created */
	private WebElement actualChecklist(String cardName) {
		String xpath = "//span[text()='" + cardName
				+ "']/parent::div//div[@class='badge js-checkitems-badge is-complete']";
		return driver.findElement(By.xpath(xpath));
	}

	/* Title Link */
	@FindBy(xpath = "//div[@class='yRPuNUIoZpQWwj']")
	private WebElement lnkTitle;

	/* Logout Button */
	@FindBy(xpath = "//span[contains(text(),'Log out')]")
	private WebElement btnLogOut;

	/* Logout Button in Logout page */
	@FindBy(id = "logout-submit")
	private WebElement btnLogOutInLogoutPage;

	/* Sign up Button */
	@FindBy(xpath = "//a[contains(text(),'Sign up')]")
	private WebElement btnSignup;

	/* Log out of your Atlassian account Text */
	@FindBy(xpath = "//h5[contains(text(),'Log out of your Atlassian account')]")
	private WebElement txtLogoutOfYourAtlassianAccount;

	/* Template */
	private WebElement template(String cardName) {
		String xpath = "//span[text()='" + cardName + "']/parent::div//div[@class='badge is-template']";
		return driver.findElement(By.xpath(xpath));
	}
	
	/* Description */
	private WebElement description(String cardName) {
		String xpath = "//span[text()='" + cardName + "']/parent::div//div[@class='badge is-icon-only']/span";
		return driver.findElement(By.xpath(xpath));
	}

	/* Comment */
	private WebElement checkComment(String cardName) {
		String xpath = "//span[text()='" + cardName
				+ "']/parent::div//div[@class='badge']/span[@class='badge-icon icon-sm icon-comment']";
		return driver.findElement(By.xpath(xpath));
	}
	/**
	 * Description : This method implements click on Send Invitation Button
	 * 
	 * @author Tanu and Sushmita P H
	 */
	public synchronized void clkSendInvitationBtn() {
		try {
			if (!btnSendInvitation.isEnabled()) {
				actionUtil.info("Send Invitation Button is Disabled");
				actionUtil.validationinfo("Send Invitation Button is Disabled", "blue");
				actionUtil.validateisElementDisplayed(txtInviteMsg, "Error Invite Message",
						"Invalid Email Address, so unable to send Invitation",
						"Able to send Invitation with Invalid Email Address", "blue");
			} else {
				actionUtil.waitForElement(btnSendInvitation, "Send Invitation Button");
				actionUtil.clickOnElement(btnSendInvitation, "Send Invitation Button");
			}

		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Send Invitation Button ");
			Assert.fail("Unable to perform action on Send Invitation Button ");
		}
	}

	/**
	 * Description : This method implements for sending invitation
	 * 
	 * @author Tanu and Sushmita P H
	 * @param emailId
	 * @param value
	 */
	public synchronized void sendInvitation(String emailId, String value) {
		try {
			Thread.sleep(10000);
			actionUtil.clickOnElement(btnInvite(value), "Invite Button");
			actionUtil.waitForElement(txtEmail, "Email Id");
			actionUtil.typeText(txtEmail, emailId, "Enter Email Id");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Invite Button");
			Assert.fail("Unable to perform action on Invite Button");
		}
	}

	/**
	 * Description : This method Enter List Name in List Text Area.
	 * 
	 * @author Tanu
	 * @param listName
	 */
	public synchronized void setListName(String listName) {
		try {
			actionUtil.waitForElement(txtListName, "Enter List name");
			actionUtil.poll(3000);
			actionUtil.typeText(txtListName, listName, "Enter List name");
			actionUtil.clickOnElement(btnAddList, "click to Add List Button");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to create List Name");
			Assert.fail("Unable to create List Name");
		}
	}

	/**
	 * Description : This method Enter List Name in List Text Area.
	 * 
	 * @author Tanu
	 * @param listName
	 * @param cardName
	 */
	public synchronized void setListName(String listName, String cardName) {
		try {
			try {
				if (taToDo.isDisplayed()) {
					setCard(cardName);
				}
			} catch (Exception e) {
				setListName(listName);
				setCardName(cardName);
			}
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to create List Name");
			Assert.fail("Unable to create List Name");
		}
	}

	/**
	 * Description : This method Enter List Name in List Text Area.
	 * 
	 * @author Tanu
	 * @param listName
	 */
	public synchronized void setAnotherListName(String listName) {
		try {
			actionUtil.clickOnElement(txtAddAnotherList, "Add Another List Button");
			actionUtil.typeText(txtListName, listName, "Enter List name");
			actionUtil.clickOnElement(btnAddList, "Add List Button");
			actionUtil.moveByOffset(10, 10);
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to create List Name");
			Assert.fail("Unable to create List Name");
		}
	}

	/**
	 * Description : This method Enter Card Name in card Text Area.
	 * 
	 * @author Tanu
	 * @param cardName
	 */
	public synchronized void setCard(String cardName) {
		try {
			actionUtil.waitForElement(txtCard, "Enter Card name");
			actionUtil.typeText(txtCard, cardName, "Enter List name");
			actionUtil.poll(4000);
			actionUtil.clickOnElement(btnAddCard, "Add Card Button");
			actionUtil.poll(3000);
			actionUtil.validatetext(cardName, lnkCreatedCardName, "Card Name Text", "Card is Created",
					"Card is not Created", "green");

		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to create Card");
			Assert.fail("Unable to create Card");
		}
	}

	/**
	 * Description : This method Enter Card Name in card Text Area.
	 * 
	 * @author Tanu
	 * @param cardName
	 */
	public synchronized void setCardName(String cardName) {
		try {
			actionUtil.clickOnElement(txtAddCard, "Card name Button");
			actionUtil.poll(2000);
			actionUtil.typeText(txtCardName, cardName, "Enter List name");
			actionUtil.clickOnElement(btnAddCard, "Add Card Button");
			actionUtil.poll(2000);
			actionUtil.validatetext(cardName, lnkCreatedCardName, "Card Name Text", "Card is Created",
					"Card is not Created", "green");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to set Card Name");
			Assert.fail("Unable to set Card Name");
		}
	}

	/**
	 * Description : This method click on card Action.
	 * 
	 * @author Tanu
	 * @param listName
	 */
	public synchronized void clkOnMove(String listName) {
		try {
			actionUtil.waitForElement(lnkCreatedCardName, "Created Card name");
			actionUtil.actionMouseOver(lnkCreatedCardName, "Created Card name");
			actionUtil.clickOnElementUsingJS(imgPencilIcon, "Pencil Icon");
			actionUtil.waitForElement(btnMove, "Move Button");
			actionUtil.poll(3000l);
			actionUtil.clickOnElementUsingJS(btnMove, "Move Button");
			actionUtil.selectByText(ddList, listName,"List Dropdown");
			actionUtil.clickOnElementUsingJS(btnSubmitCardAction, "Move Button");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Move Button");
			Assert.fail("Unable to perform action on Move Button");
		}
	}

	/**
	 * Description : This method Enter List Name in List Text Area.
	 * 
	 * @author Tanu
	 * @param listName
	 */
	public synchronized void clkOnCopy(String listName) {
		try {
			actionUtil.waitForElement(lnkCreatedCardName, "Created Card name");
			actionUtil.actionMouseOver(lnkCreatedCardName, "Created Card name");
			actionUtil.clickOnElementUsingJS(imgPencilIcon, "Pencil Icon");
			actionUtil.waitForElement(btnCopy, "Copy Button");
			actionUtil.clickOnElementUsingJS(btnCopy, "Copy Button");
			actionUtil.selectByText(ddList, listName,"List Dropdown");
			actionUtil.clickOnElement(btnSubmitCardAction, "Copy Button");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Copy Button");
			Assert.fail("Unable to perform action on Copy Button");
		}
	}

	/**
	 * Description : This method click on Archive.
	 * 
	 * @author Tanu
	 */
	public synchronized void clkOnArchive() {
		try {
			actionUtil.waitForElement(lnkCreatedCardName, "Created Card name");
			actionUtil.poll(3000);
			actionUtil.actionMouseOver(lnkCreatedCardName, "Created Card name");
			actionUtil.clickOnElementUsingJS(imgPencilIcon, "Pencil Icon");
			actionUtil.waitForElement(btnArchive, "Archive Card");
			actionUtil.clickOnElement(btnArchive, "Archive Button");

		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Archive Button");
			Assert.fail("Unable to perform action on Archive Button");
		}
	}

	/**
	 * Description : This method click on Close Board.
	 * 
	 * @author Tanu
	 */
	public synchronized void clkOnCloseBoard() {
		try {
			actionUtil.waitForElement(btnShowMenu, "Show Menu Option");
			actionUtil.poll(3000);
			actionUtil.clickOnElement(btnShowMenu, "Show Menu Option");
			actionUtil.poll(3000);
			actionUtil.clickOnElement(optionMore, "More Option");
			actionUtil.clickOnElement(optionCloseBoard, "Close Board Option");
			actionUtil.clickOnElement(optionClose, "Close Option");
			actionUtil.poll(2000);
			actionUtil.clickOnElement(lnkPermanentlyDelete, "Permanently Delete Option");
			actionUtil.clickOnElement(btnDelete, "Delete Option");
			actionUtil.poll(2000);
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Close Board Button");
			Assert.fail("Unable to perform action on Close Board Button");
		}
	}

	/**
	 * Description : This method to validate check list
	 * 
	 * @author Tanu
	 * @param cardName
	 */
	public synchronized void validateChecklist(String cardName) {
		try {
			Assert.assertTrue(actualChecklist(cardName).isDisplayed());
			actionUtil.info("Checklist is created for a card");
			actionUtil.validationinfo("Checklist is created for a card", "green");
		} catch (AssertionError e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Checklist is not created for a Card");
			Assert.fail("Checklist is not created for a Card");
		}catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to validate checklist");
			Assert.fail("Unable to validate checklist");
		}
	}

	/**
	 * Description: Method implements to validate Template Created for a Card
	 * 
	 * @author sushmita p h
	 * @param cardName
	 */
	public synchronized void validateTemplate(String cardName) {
		try {
			Assert.assertTrue(template(cardName).isDisplayed());
			actionUtil.info("Template is created for a card");
			actionUtil.validationinfo("Template is created for a card", "blue");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Template is not created for a Card");
			Assert.fail("Template is not created for a Card");
		}
	}

	/**
	 * Description: Method implements to validate Description Created for a Card
	 * 
	 * @author sushmita p h
	 * @param cardName
	 */
	public synchronized void validateDescription(String cardName) {
		try {
			Assert.assertTrue(description(cardName).isDisplayed());
			actionUtil.info("Description is created for a card");
			actionUtil.validationinfo("Description is created for a card", "blue");
		} catch (AssertionError e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Description is not created for a card");
			Assert.fail("Description is not created for a card");
		}catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to validate Description is created for a card");
			Assert.fail("Unable to validate Description is created for a card");
		}
	}
	
	/**
	 * Description: Method implements to validate Comment Created for a Card
	 * 
	 * @author sushmita p h
	 * @param cardName
	 */
	public synchronized void validateComment(String cardName) {
		try {
			Assert.assertTrue(checkComment(cardName).isDisplayed());
			actionUtil.info("Comment is created for a card");
			actionUtil.validationinfo("Comment is created for a card", "blue");
		} catch (AssertionError e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Comment is not created for a card");
			Assert.fail("Comment is not created for a card");
		}catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to validate Comment created for a card");
			Assert.fail("Unable to validate Comment created for a card");
		}
	}

	/**
	 * Description: Method implements to click on Logout Button
	 * 
	 * @author Ramya R
	 */
	public synchronized void clkLogOut() {
		try {
			actionUtil.poll(5000);
			actionUtil.waitForElement(lnkTitle, "Title Link");
			actionUtil.clickOnElementUsingJS(lnkTitle, "Title Link");
			actionUtil.waitForElement(btnLogOut, "Logout Button");
			actionUtil.clickOnElementUsingJS(btnLogOut, "Logout Button");
			actionUtil.isElementVisible(txtLogoutOfYourAtlassianAccount, "Log out of your Atlassian account Text");
			actionUtil.clickOnElementUsingJS(btnLogOutInLogoutPage, "Logout Button");
			actionUtil.isElementVisible(btnSignup, "Sign up Button");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform Logout action");
			Assert.fail("Unable to perform Logout action");
		}
	}

	/**
	 * Description: Method implements to validate Created Card
	 * 
	 * @author Tanu
	 * @param expected
	 */
	public synchronized void validateCard(String expected) {
		try {
			String actual = lnkCreatedCardName.getText();
			Assert.assertEquals(actual, expected);
			actionUtil.info("Card is created");
			actionUtil.validationinfo("Card is created", "green");
		} catch (AssertionError e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Card is not created");
			Assert.fail("Card is not created");
		}catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to validate Card is created");
			Assert.fail("Unable to validate Card is created");
		}
	}

	/**
	 * Description: Method implements to validate Move Card
	 * 
	 * @author Tanu
	 * @param actualList
	 * @param movedList
	 * @param cards
	 */
	public synchronized void validateMoveCard(String actualList, String movedList, List<String> cards) {
		try {
			List<String> actualCards = new ArrayList();
			List<String> movedCards = new ArrayList();
			for (WebElement ele : txtCreatedListName) {
				System.out.println(ele.getText());
				if (ele.getText().equalsIgnoreCase(actualList)) {
					for (WebElement ele1 : listCard(actualList)) {
						actualCards.add(ele1.getText());
					}
				}
				if (ele.getText().equalsIgnoreCase(movedList)) {
					for (WebElement ele1 : listCard(movedList)) {
						movedCards.add(ele1.getText());
					}
				}
			}
			System.out.println(actualCards);
			System.out.println(movedCards);

			if (actualCards.isEmpty() && movedCards.equals(cards)) {
				actionUtil.info("Card is moved from " + actualList + " to " + movedList);
				actionUtil.pass("Card is moved from " + actualList + " to " + movedList);
			} else {
				actionUtil.error("Failed to move card from " + actualList + " to " + movedList);
				actionUtil.fail("Failed to move card from " + actualList + " to " + movedList);
				Assert.fail("Failed to move card from " + actualList + " to " + movedList);
			}
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to validate move card action");
			Assert.fail("Unable to validate move card action");
		}
	}

	/**
	 * Description: Method implements to validate CopyCard
	 * 
	 * @author Tanu
	 * @param actualList
	 * @param copyedList
	 */
	public synchronized void validateCopyCard(String actualList, String copyedList) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			List<String> actualCards = new ArrayList();
			List<String> movedCards = new ArrayList();
			for (WebElement ele : txtCreatedListName) {
				Thread.sleep(2000);
				if (ele.getText().equalsIgnoreCase(actualList)) {
					for (WebElement ele1 : listCard(actualList)) {
						Thread.sleep(2000);
						actualCards.add(ele1.getText());
					}
				}
				if (ele.getText().equalsIgnoreCase(copyedList)) {

					for (WebElement ele1 : listCard(copyedList)) {
						Thread.sleep(2000);
						movedCards.add(ele1.getText());
					}
				}
			}
			if (actualCards.equals(movedCards)) {
				actionUtil.info("Card is copy from " + actualList + " to " + copyedList);
				actionUtil.pass("Card is copy from " + actualList + " to " + copyedList);
			} else {
				actionUtil.error("Failed to copy card from " + actualList + " to " + copyedList);
				actionUtil.fail("Failed to copy card from " + actualList + " to " + copyedList);
				Assert.fail("Failed to copy card from " + actualList + " to " + copyedList);
			}
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to validate copy card action");
			Assert.fail("Unable to validate copy card action");
		}
	}

	/**
	 * Description: Method implements to validate Archive Card
	 * 
	 * @author Tanu
	 * @param deletedElements
	 * @param listName
	 */
	public synchronized void validateArchive(List<String> deletedElements, String listName) {
		try {
			List<String> actualCards = new ArrayList();
			for (WebElement card : listCard(listName)) {
				actualCards.add(card.getText());
			}
			for (int i = 0; i < deletedElements.size(); i++) {
				int count = 0;
				for (String cards : actualCards) {
					if (deletedElements.get(i).equalsIgnoreCase(cards)) {
						count++;
					}
				}
				if (count == 0) {
					actionUtil.info(deletedElements.get(i) + " deleted from the Board ");
					actionUtil.validationinfo(deletedElements.get(i) + " deleted from the Board ", "blue");
				}
			}
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to validate board is Archive");
			Assert.fail("Unable to validate board is Archive");
		}
	}

	/**
	 * Description: Method implements to validate Board is closed/deleted
	 * 
	 * @author Tanu
	 * @param boardName
	 */
	public synchronized void validateCloseBoard(String boardName) {
		List<WebElement> list = driver.findElements(By.xpath("//div[@class='board-tile-details-name']"));
		ArrayList<String> actual = new ArrayList<String>();
		boolean value = false;
		try {
			for (WebElement webelement : list) {
				System.out.println(webelement.getText());
				if (webelement.getText().contains(boardName)) {
					
					value = true;
					break;
				}
			}
			if (value == true) {
				actionUtil.error(boardName + " board is not deleted");
				actionUtil.fail(boardName + " board is not deleted ");
			} else {
				actionUtil.info(boardName + " board is deleted");
				actionUtil.validationinfo(boardName + " board is deleted ", "blue");
			}
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to validate " + boardName + " board is deleted");
			Assert.fail("Unable to validate " + boardName + " board is deleted");
		}
	}
}
