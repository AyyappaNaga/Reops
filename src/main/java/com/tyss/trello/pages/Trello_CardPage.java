package com.tyss.trello.pages;

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
 * Description This class has the implementations of the Card page
 * 
 * @author Tanu katiyar
 */
public class Trello_CardPage {
	public WebDriver driver;
	public WebActionUtil actionUtil;
	public long ETO;

	public Trello_CardPage(WebDriver driver, Long ETO, WebActionUtil actionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.actionUtil = actionUtil;
		this.ETO = ETO;
	}

	/* Description Text Area */
	@FindBy(xpath = "//a[contains(@class,'description-fake-text-area hide-on-edit js-edit-desc  js-hide-with-draft')]")
	private WebElement taDescription;

	/* Description Text Area */
	@FindBy(xpath = "//a[contains(text(),'Add a more detailed description')]")
	private WebElement tbDescription;

	/* Save Button */
	@FindBy(xpath = "//input[contains(@class,'confirm mod-submit-edit js-save-edit')]")
	private WebElement btnSaveDescription;

	/* Comment Text Area */
	@FindBy(xpath = "//textarea[@class='comment-box-input js-new-comment-input']")
	private WebElement taComment;

	/* Comment Save Button */
	@FindBy(xpath = "//input[contains(@class,'primary confirm mod-no-top-bottom-margin js-add-comment')]")
	private WebElement btnSaveComment;

	/* CheckList link */
	@FindBy(xpath = "//a[@title='Checklist']")
	private WebElement lnkCheckList;

	/* CheckList Title text field */
	@FindBy(id = "id-checklist")
	private WebElement tbCheckListTitle;

	/* Add CheckList Button */
	@FindBy(xpath = "//input[contains(@class,'primary wide confirm js-add-checklist')]")
	private WebElement btnAddCheckList;

	/* Add Item text field */
	@FindBy(xpath = "//textarea[contains(@class,'new-item-text js-new-checklist-item-input')]")
	private WebElement tbAddItem;

	/* Add Item Button */
	@FindBy(xpath = "//input[contains(@class,'primary confirm mod-submit-edit js-add-checklist-item')]")
	private WebElement btnAddItem;

	/* Item CheckBox */
	@FindBy(xpath = "//div[@class='checklist-item-checkbox enabled js-toggle-checklist-item']")
	private WebElement checkBoxItem;

	/* Labels link */
	@FindBy(xpath = "//a[@title='Labels']")
	private WebElement lnkLabels;

	/* Label pencil icon */
	private WebElement selectLabel(String labelIcon) {
		String xpath = "//span[contains(@class,'card-label-" + labelIcon + "')]/preceding-sibling::a";
		return driver.findElement(By.xpath(xpath));
	}

	/* Pencil label icon */
	@FindBy(xpath = "//span[contains(@class,' card-label-green  js-select-label selected')]/preceding-sibling::a")
	private WebElement icnPencilLabel;

	/* Label Name text field */
	@FindBy(id = "labelName")
	private WebElement tbLabelName;

	/* Label Save Button */
	@FindBy(xpath = "//input[@class='nch-Button nch-Button--primary wide js-submit']")
	private WebElement btnLabelSave;

	/* Label Button */
	private WebElement selectLabelColor(String labelName) {
		String xpath = "//a/following-sibling::span[contains(text(),'" + labelName + "')]";
		return driver.findElement(By.xpath(xpath));
	}

	/* Description link */
	@FindBy(xpath = "//h3[text()='Description']")
	private WebElement lnkDescription;

	/* Save Button */
	@FindBy(xpath = "//input[@class='nch-Button nch-Button--primary wide js-save-edits']")
	private WebElement btnSave;

	/* Labels Options */
	@FindBy(xpath = "//a/following-sibling::span[contains(@class,'card-label mod-selectable card-label')]")
	private List<WebElement> btnlabelsOptions;

	/* Label Button */
	@FindBy(xpath = "//span[contains(@class,'label-green active js-select-label selected')]")
	private WebElement btnlabel;

	/* Label close Button */
	@FindBy(xpath = "//a[@class='pop-over-header-close-btn icon-sm icon-close']")
	private WebElement btnlabelClose;

	/* Template Button */
	@FindBy(xpath = "//span[text()='Make template']")
	private WebElement btntemplate;

	/* Close card Button */
	@FindBy(xpath = "//a[@class='icon-md icon-close dialog-close-button js-close-window']")
	private WebElement btnCloseCard;

	/* Open Card Button */
	@FindBy(xpath = "//span[@class='list-card-title js-card-name']")
	private WebElement btnCard;

	/* label heading text */
	@FindBy(xpath = "//div[contains(@class,'card-detail-item-labels')]/h3[@class='card-detail-item-header']")
	private WebElement txtLabelsHeader;

	/* Auto labelName color */
	private WebElement checkLabelColor(String color) {
		String xpath = "//h3/following-sibling::div/span[contains(@class,'card-label card-label-" + color + "')]";
		return driver.findElement(By.xpath(xpath));
	}

	/* Open card Button */
	@FindBy(xpath = "//a[@class='quick-card-editor-buttons-item']/span[text()='Open card']")
	private WebElement btnOpenCard;

	/* Auto label title text */
	private WebElement checkLabelTitle(String color) {
		String xpath = "//h3/following-sibling::div/span[contains(@class,'card-label card-label-" + color + "')]/span";
		return driver.findElement(By.xpath(xpath));
	}

	/* Created Card Name link */
	@FindBy(xpath = "//span[@class='list-card-title js-card-name']")
	private WebElement lnkCreatedCardName;

	/* Pencil icon */
	@FindBy(xpath = "//span[@class='icon-sm icon-edit list-card-operation dark-hover js-open-quick-card-editor js-card-menu']")
	private WebElement imgPencilIcon;

	/**
	 * Description: Method implements to Add Description for a Card
	 * 
	 * @author sushmita p h
	 * @param description
	 */
	public synchronized void addDescription(String description) {
		try {
			actionUtil.waitForElement(lnkCreatedCardName, "Created Card name");
			actionUtil.poll(2000);
			actionUtil.actionMouseOver(lnkCreatedCardName, "Created Card name");
			actionUtil.poll(2000);
			actionUtil.clickOnElementUsingJS(imgPencilIcon, "click to Pencil Icon");
			actionUtil.poll(2000);
			actionUtil.clickOnElement(btnOpenCard, "click to Open Card Button");
			actionUtil.waitForElement(tbDescription, "Description Area");
			actionUtil.typeText(tbDescription, description, "Description Text Area");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to add Description for a Card");
			Assert.fail("Unable to add Description for a Card");
		}
	}

	/**
	 * Description: Method implements to Add Comment for a Card
	 * 
	 * @author sushmita p h
	 * @param comment
	 */
	public synchronized void addComment(String comment) {
		try {
			actionUtil.waitForElement(lnkCreatedCardName, "Created Card name Link");
			actionUtil.actionMouseOver(lnkCreatedCardName, "Created Card name Link");

			actionUtil.clickOnElementUsingJS(imgPencilIcon, "Pencil Icon");
			actionUtil.poll(2000);
			actionUtil.clickOnElement(btnOpenCard, "Open Card Button");

			actionUtil.waitForElement(taComment, "Comment Text Area");
			actionUtil.typeText(taComment, comment, "Comment Text Area");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to Add Comment for a Card");
			Assert.fail("Unable to Add Comment for a Card");
		}
	}

	/**
	 * Description: Method implements to Click on Description Save Button
	 * 
	 * @author sushmita p h
	 */
	public synchronized void clkDescriptionSaveBtn() {
		try {
			actionUtil.clickOnElement(btnSaveDescription, "Description Save Button");
			actionUtil.info("Description is added");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Description Save Button");
			Assert.fail("Unable to perform action on Description Save Button");
		}
	}

	/**
	 * Description: Method implements to Click on Comment Save Button
	 * 
	 * @author sushmita p h
	 */
	public synchronized void clkCommentSaveBtn() {
		try {
			actionUtil.waitForElement(btnSaveComment, "Comment Save Button");
			actionUtil.clickOnElement(btnSaveComment, "Comment Save Button");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Comment Save Button");
			Assert.fail("Unable to perform action on Comment Save Button");
		}
	}

	/**
	 * Description: Method implements to Click on CheckList Link
	 * 
	 * @author sushmita p h
	 */
	public synchronized void clkCheckListLnk() {
		try {
			actionUtil.poll(3000);
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOf(btnCard));
			actionUtil.clickOnElement(btnCard, "Created Card Button");

			actionUtil.waitForElement(lnkCheckList, "CheckList Link");
			actionUtil.clickOnElement(lnkCheckList, "CheckList Link");

		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on CheckList Link");
			Assert.fail("Unable to perform action on CheckList Link");
		}
	}

	/**
	 * Description: Method implements to Add Checklist Title
	 * 
	 * @author sushmita p h
	 * @param checkListTitle
	 */
	public synchronized void addChecklistTitle(String checkListTitle) {
		try {
			actionUtil.poll(2000);
			actionUtil.waitForElement(tbCheckListTitle, "CheckList Title");

			System.out.println("Checklist label");
			actionUtil.clearText(tbCheckListTitle, "CheckList Title");
			actionUtil.typeText(tbCheckListTitle, checkListTitle, "CheckList Title");
			actionUtil.clickOnElement(btnAddCheckList, "CheckList Add Button");

		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to Add CheckList title");
			Assert.fail("Unable to Add CheckList title");
		}
	}

	/**
	 * Description: Method implements to Add Checklist Item
	 * 
	 * @author sushmita p h
	 * @param checkListItem
	 */
	public synchronized void addChecklistItem(String checkListItem) {
		try {
			actionUtil.waitForElement(tbAddItem, "CheckList Item");
			actionUtil.typeText(tbAddItem, checkListItem, "checkList Item");
			actionUtil.clickOnElement(btnAddItem, "CheckList Item Add Button");
			actionUtil.waitForElement(checkBoxItem, "Item Checkbox");
			actionUtil.clickCheckBox(checkBoxItem, "Item Checkbox");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to Add CheckList Item");
			Assert.fail("Unable to Add CheckList Item");
		}
	}

	/**
	 * Description: Method implements to Click on Labels Link
	 * 
	 * @author sushmita p h
	 */
	public synchronized void clkLabelsLnk() {
		try {
			actionUtil.waitForElement(lnkCreatedCardName, "Created Card name");
			actionUtil.poll(2000);
			actionUtil.clickOnElementUsingJS(lnkCreatedCardName, "click on card name");
			actionUtil.poll(2000);
			actionUtil.waitForElement(lnkLabels, "Labels Link");
			actionUtil.clickOnElement(lnkLabels, "Labels Link");

		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Labels Link");
			Assert.fail("Unable to perform action on Labels Link");
		}
	}

	/**
	 * Description: Method implements to Add Label for a card
	 * 
	 * @author sushmita p h
	 * @param labelName
	 * @param labelColor
	 */
	public synchronized void setLabelName(String labelName, String labelColor) {
		try {
			System.out.println("label color");
			actionUtil.waitForElement(selectLabel(labelColor), "Label Pencil Icon");
			actionUtil.clickOnElement(selectLabel(labelColor), "Label Pencil Icon");

			try {
				actionUtil.waitForElement(tbLabelName, "label Name text field");
				actionUtil.typeText(tbLabelName, labelName, "label Name text field");

				actionUtil.clickOnElement(btnLabelSave, "Label Save Button");
			} catch (Exception e) {
				actionUtil.error(e.getMessage());
				actionUtil.fail("Unable to set Label name");
				Assert.fail("Unable to set Label name");
			}

			setLabel(labelName);
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to Add Label for a card");
			Assert.fail("Unable to Add Label for a card");
		}
	}

	/**
	 * Description: Method implements to select Created Label
	 * 
	 * @author sushmita p h
	 * @param labelName
	 */
	public synchronized void setLabel(String labelName) {
		try {
			System.out.println("clicked on label");

			actionUtil.waitForElement(selectLabelColor(labelName), "Labels Name");

			actionUtil.poll(3000);

			actionUtil.clickOnElementUsingJS(selectLabelColor(labelName), "Label Button");
			System.out.println("clicked on label");
			actionUtil.clickOnElement(btnlabelClose, "Label  Close Button");

		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to select Created Label");
			Assert.fail("Unable to select Created Label");
		}
	}

	/**
	 * Description: Method implements to click on close card Button
	 * 
	 * @author sushmita p h
	 */
	public synchronized void clkCloseCardPopUp() {
		try {
			actionUtil.waitForElement(btnCloseCard, "Card Close Button");
			actionUtil.clickOnElement(btnCloseCard, "Card Close Button");

		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Card Close Button");
			Assert.fail("Unable to perform action on Card Close Button");
		}
	}

	/**
	 * Description: Method implements to click on Make Template
	 * 
	 * @author sushmita p h
	 */
	public synchronized void clkOnMakeTemplate() {
		try {
			actionUtil.waitForElement(lnkCreatedCardName, "Created Card name");
			actionUtil.clickOnElement(lnkCreatedCardName, "click to Created Card name");
			actionUtil.waitForElement(btntemplate, "Make Template Button");
			actionUtil.clickOnElement(btntemplate, "Make Template Button");

		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Make Template Button");
			Assert.fail("Unable to perform action on Make Template Button");
		}
	}

	/**
	 * Description: Method implements to Create CheckList for a Card
	 * 
	 * @author sushmita p h
	 * @param checkListTitle
	 * @param checkListItem
	 */
	public synchronized void addCheckList(String checkListTitle, String checkListItem) {
		try {
			clkCheckListLnk();
			addChecklistTitle(checkListTitle);
			addChecklistItem(checkListItem);
			actionUtil.info("Checklist created");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to Create CheckList for a Card");
			Assert.fail("Unable to Create CheckList for a Card");
		}
	}

	/**
	 * Description: Method implements to Create CheckList for a Card
	 * 
	 * @author sushmita p h
	 * @param labelName
	 * @param color
	 */
	public synchronized void addLabel(String labelName, String color) {
		try {
			clkLabelsLnk();
			setLabelName(labelName, color);
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to set Add Label");
			Assert.fail("Unable to set Add Label");
		}
	}

	/**
	 * Description: Method implements to validate Comment Created for a Card
	 * 
	 * @author sushmita p h
	 * @param expComment
	 */
	public synchronized void validateComment(String expComment) {
		try {
			WebElement actualComment = driver.findElement(By.xpath("//p[text()='" + expComment + "']"));
			Assert.assertEquals(actualComment, expComment);
			actionUtil.info("Comment is created for a card");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Comment is not created for a Card");
			Assert.fail("Comment is not created for a Card");
		}
	}

	/**
	 * Description: Method implements to validate Comment Created for a Card
	 * 
	 * @author sushmita p h
	 * @param color
	 * @param expText
	 */
	public synchronized void validateLabel(String color, String expText) {
		try {
			boolean flag = false;
			if (txtLabelsHeader.isDisplayed()) {
				actionUtil.info("Label Header is displayed");
				actionUtil.validationinfo("Label Header is displayed", "blue");
				if (checkLabelColor(color).isDisplayed()) {
					actionUtil.info("Successfully created Label with color " + color);
					actionUtil.validationinfo("Successfully created Label with color " + color, "blue");
					if (checkLabelTitle(color).getText().equalsIgnoreCase(expText)) {
						actionUtil.info("Successfully created Label with text " + expText);
						actionUtil.validationinfo("Successfully created Label with text " + expText, "blue");
						flag = true;
					}
				}
			}
			if (flag == true) {
				actionUtil.info("Successfully created Label with color " + color + " and with Text " + expText);
				actionUtil.validationinfo("Successfully created Label with color " + color + " and with Text" + expText,
						"blue");
			}else {
				actionUtil.info("Failed to create Label with color " + color + " and with Text " + expText);
				actionUtil.fail("Failed to create Label with color " + color + " and with Text" + expText);
				Assert.fail("Failed to create Label with color " + color + " and with Text" + expText);
			}
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Label is not created for a Card");
			Assert.fail("Label is not created for a Card");
		}
	}

	/**
	 * Description: Method implements to validate Template Created for a Card
	 * 
	 * @author sushmita p h
	 * @param expTemplate
	 */
	public synchronized void validateTemplate(String expTemplate) {
		try {
			WebElement actualTemplate = driver.findElement(By.xpath("//p[text()='" + expTemplate + "']"));
			Assert.assertEquals(actualTemplate, expTemplate);
			actionUtil.info("Template is created for a card");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Template is not created for a Card");
			Assert.fail("Template is not created for a Card");
		}
	}

}
