package com.tyss.trello.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.tyss.trello.baseutil.BaseTest;
import com.tyss.trello.util.WebActionUtil;

/**
 * Description: This class implements the methods for Initiator Dash board page
 * 
 * @author Sajal
 */
public class InitiatorDashboard_Page {

	public WebDriver driver;
	public WebActionUtil actionUtil;
	public long ETO;

	public InitiatorDashboard_Page(WebDriver driver, long ETO, WebActionUtil actionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.actionUtil = actionUtil;
		this.ETO = ETO;
	}

	/* Create Request button */
	@FindBy(xpath = "//button[text()='Create Request ']")
	private WebElement btnCreateRequest;

	/* Create request drop down */
	@FindBy(xpath = "//a[contains(text(),'Create/Approve Request')]")
	private WebElement ddCreateApproveRequest;

	/* New request option in drop down */
	@FindBy(xpath = "//a[text()='New Request']")
	private WebElement otnNewRequest;

	/* Drafts option in drop down */
	@FindBy(xpath = "//a[text()='Drafts']")
	private WebElement otnDrafts;

	/* Pending tab */
	@FindBy(xpath = "//span[@id='pending-requester-count']/parent::a")
	private WebElement tabPending;

	/* Approved tab */
	@FindBy(xpath = "//span[@id='pending-approved-count']/parent::a")
	private WebElement tabApproved;

	/* Revoked tab */
	@FindBy(xpath = "//span[@id='pending-revoked-count']/parent::a")
	private WebElement tabRevoked;

	/* Cancelled tab */
	@FindBy(xpath = "//span[@id='pending-cancelled-count']/parent::a")
	private WebElement tabCancelled;

	/* Rejected tab */
	@FindBy(xpath = "//span[@id='pending-rejected-count']/parent::a")
	private WebElement tabRejected;

	/* Pending Count text */
	@FindBy(id = "pending-requester-count")
	private WebElement txtPendingCount;

	/* Approved Count text */
	@FindBy(id = "pending-approved-count")
	private WebElement txtApprovedCount;

	/* Revoked Count text */
	@FindBy(id = "pending-revoked-count")
	private WebElement txtRevokedCount;

	/* Cancelled Count text */
	@FindBy(id = "pending-cancelled-count")
	private WebElement txtCancelledCount;

	/* Rejected Count text */
	@FindBy(id = "pending-rejected-count")
	private WebElement txtRejectedCount;

	/* Page size drop down */
	@FindBy(name = "requestermodule-pending-grid_length")
	private WebElement ddPageSize;

	/* All RequestID text */
	private List<WebElement> getAllRequestID(String applicationName, String role) {
		String xpath = "//span[contains(text(),'" + applicationName + "') and //span[contains(text(),'" + role
				+ "')]]/parent::td/preceding-sibling::td[1]";
		return driver.findElements(By.xpath(xpath));
	}

	/* Action icon */
	private WebElement actionIcn(String requestID) {
		String xpath = "//td[text()='" + requestID + "']/parent::tr/descendant::a[@class='aam-action acnPopAIcon']";
		return driver.findElement(By.xpath(xpath));
	}

	/* View link */
	private WebElement viewLink(String requestID) {
		String xpath = "(//a[contains(@id,'view') and @data-requestid='" + requestID + "'])[last()]";
		return driver.findElement(By.xpath(xpath));
	}

	/* Cancel link */
	private WebElement cancelLink(String requestID) {
		String xpath = "//a[@id='cancelPendingRequest' and @data-requestid='" + requestID + "']";
		return driver.findElement(By.xpath(xpath));
	}

	/* Revoke link */
	private WebElement revokeLink(String requestID) {
		String xpath = "(//a[@id='revokeApprovedRequestsReq' and @data-requestid='" + requestID + "'])[last()]";
		return driver.findElement(By.xpath(xpath));
	}

	/* Extend link */
	private WebElement extendLink(String requestID) {
		String xpath = "(//a[@id='extendApproveRequest' and @data-requestid='" + requestID + "'])[last()]";
		return driver.findElement(By.xpath(xpath));
	}

	/* Spinner */
	@FindBy(xpath = "//div[@class='go_spiner nolms']")
	private WebElement spinner;

	/* Spinner */
	@FindBy(xpath = "//div[@class='go_spiner-graph nolms']")
	private WebElement spinner1;

	/* Close button in revocation request pop up */
	@FindBy(xpath = "//div[@id='revocationRequesterModuleModal']/descendant::button")
	private WebElement btnCloseInRevocationRequestPopup;

	/* Remarks Text area in revocation request pop up */
	@FindBy(id = "remarksForRevocation")
	private WebElement taRemarksInRevocationRequestPopup;

	/* Submit button in revocation request pop up */
	@FindBy(id = "revokeRequesterDetails")
	private WebElement btnSubmitInRevocationRequestPopup;

	/* Revocation Request text */
	@FindBy(xpath = "//h4[text()='Revocation Request']")
	private WebElement txtRevocationRequest;

	/* OK button in Revocation request */
	@FindBy(xpath = "//p[contains(text(),'Are you sure you want to continue')]/parent::div/descendant::button[@id='modalOkbtn']")
	private WebElement btnOKInRevocationRequest;

	/* Cancel button in Revocation request */
	@FindBy(xpath = "//p[contains(text(),'Are you sure you want to continue')]/parent::div/descendant::button[@id='modalCancelbtn']")
	private WebElement btnCancelInRevocationRequest;

	/* Are you sure you want to continue text */
	@FindBy(xpath = "//p[contains(text(),'Are you sure you want to continue')]")
	private WebElement txtAreYouSureYouWantToContinue;

	/* Revocation request submitted successfully success message */
	@FindBy(id = "alert-msg-revoke")
	private WebElement txtRevocatioRequestSubmittedSuccessfully;

	/* Close button in cancel request pop up */
	@FindBy(xpath = "//div[@id='cancelRequestModal']/descendant::button")
	private WebElement btnCloseInCancelRequestPopup;

	/* Remarks Text area in cancel request pop up */
	@FindBy(id = "remarksForCancel")
	private WebElement taRemarksInCancelRequestPopup;

	/* Submit button in cancel request pop up */
	@FindBy(id = "cancelRequestModalsave")
	private WebElement btnSubmitInCancelRequestPopup;

	/* Cancel Request text */
	@FindBy(xpath = "//h4[text()='Cancel Request']")
	private WebElement txtCancelRequest;

	/* Are you sure you want to Cancel text */
	@FindBy(xpath = "//div[@id='CancelConfirm']/descendant::p[@id='response-message-confirm']")
	private WebElement txtAreYouSureYouWantToCancel;

	/* OK button in Cancel request */
	@FindBy(xpath = "//p[contains(text(),'Are you sure you want to Cancel')]/parent::div/descendant::button[@id='modalOkbtn']")
	private WebElement btnOKInCancelRequest;

	/* Cancel button in cancel request */
	@FindBy(xpath = "//p[contains(text(),'Are you sure you want to Cancel')]/parent::div/descendant::button[@id='modalCancelbtn']")
	private WebElement btnCancelInCancelRequest;

	/* The requestId is cancelled successfully success message */
	@FindBy(id = "alert-msg-revoke")
	private WebElement txtTheRequestIdIsCancelledSuccessfully;

	/* Close button in Extension request pop up */
	@FindBy(xpath = "//div[@id='extensionRequestModal']/descendant::button")
	private WebElement btnCloseInExtensionRequestPopup;

	/* Remarks Text area in Extension request pop up */
	@FindBy(id = "remarksForDuration")
	private WebElement taRemarksInExtensionRequestPopup;

	/* Submit button in Extension request pop up */
	@FindBy(id = "extendbtnRequester")
	private WebElement btnSubmitInExtensionRequestPopup;

	/* Extension Request text */
	@FindBy(xpath = "//h4[text()='Extension Request']")
	private WebElement txtExtensionRequest;

	/* OK button in Extension request */
	@FindBy(xpath = "//p[contains(text(),'Are you sure you want to extend')]/parent::div/descendant::button[@id='modalOkbtn']")
	private WebElement btnOKInExtensionRequest;

	/* Cancel button in Extension request */
	@FindBy(xpath = "//p[contains(text(),'Are you sure you want to extend')]/parent::div/descendant::button[@id='modalCancelbtn']")
	private WebElement btnCancelInExtensionRequest;

	/* Are you sure you want to continue text */
	@FindBy(xpath = "//p[contains(text(),'Are you sure you want to extend')]")
	private WebElement txtAreYouSureYouWantToExtend;

	/* The requestId is extended successfully success message */
	@FindBy(id = "alert-msg-revoke")
	private WebElement txtTheRequestIdIsExtendedSuccessfully;

	/* Profile Icon */
	@FindBy(xpath = "//div[@class='glb-pnl-informationDropIcon']")
	private WebElement icnProfile;

	/* Change Role option link */
	private WebElement lnkChangeRole(String changeRole) {
		String xpath = "//a[contains(@class,'clsMoveTo') and text()='" + changeRole + "']";
		return driver.findElement(By.xpath(xpath));
	}

	/* Logout link */
	@FindBy(xpath = "//a[@class='logout_alert']")
	private WebElement lnkLogout;

	/* Are you sure you want to logout text */
	@FindBy(xpath = "//p[contains(text(),'Are you sure you want to logout')]")
	private WebElement txtAreYouSureYouWantToLogout;

	/* Yes button */
	@FindBy(xpath = "//button[text()='YES']")
	private WebElement btnYesInLogout;

	/* Cancel button */
	@FindBy(xpath = "//p[contains(text(),'Are you sure you want to logout')]/following-sibling::div/descendant::button[text()='Cancel']")
	private WebElement btnCancelInLogout;

	/* Cancel button */
	@FindBy(id = "cancelbtnclick")
	private WebElement btnCancel;

	/* Revoke button */
	@FindBy(id = "revokebtnclickRequester")
	private WebElement btnRevoke;

	/* Extend button */
	@FindBy(id = "extendbtnclick")
	private WebElement btnExtend;

	/* Select all check box */
	@FindBy(xpath = "//input[@class='check-all-role-req  cstmChekBox check2 check-parent-requester']")
	private WebElement cbSelectAll;

	/* Request id check box */
	private WebElement cbRequestID(String requestID) {
		String xpath = "//td[text()='" + requestID + "']/preceding-sibling::td/descendant::input";
		return driver.findElement(By.xpath(xpath));
	}

	/* Request id text */
	private WebElement txtRequestID(String requestID) {
		String xpath = "//td[text()='" + requestID + "']";
		return driver.findElement(By.xpath(xpath));
	}

	/* New Request Page */
	@FindBy(xpath = "//ol//a[text()='New Request']")
	private WebElement lnkNewReqPage;

	/* Create Approve Request drop down option */
	@FindBy(xpath = "//ul[@class='dropdown-menu']/li/a")
	private List<WebElement> createApproveRequestDropdownOpt;

	/* Log in your Account header text */
	@FindBy(xpath = "//span[text()='Log in your Account']")
	private WebElement txtLoginYourAccountHeader;

	/**
	 * Description Method to fetch the Request ID
	 * 
	 * @author Sajal
	 * @param applicationName
	 * @param role
	 */
	public synchronized String getRequestID(String applicationName, String role) {
		List<String> lst = new ArrayList();
		try {
			actionUtil.waitForElements(getAllRequestID(applicationName, role), "Request Id List");
			List<WebElement> requestID = getAllRequestID(applicationName, role);
			for (WebElement webElement : requestID) {
				lst.add(webElement.getText());
			}
			Collections.sort(lst);
			System.out.println("lst" + lst);
			actionUtil.info("Request ID : " + lst.get(lst.size() - 1) + " is generated");
			actionUtil.validationinfo("Request ID : " + lst.get(lst.size() - 1) + " is generated", "blue");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to fetch Request ID");
			Assert.fail("Unable to fetch Request ID");
		}
		return lst.get(lst.size() - 1);
	}

	/**
	 * Description Method to select an option in Page size drop down
	 * 
	 * @author Sajal
	 * @param pageSize
	 */
	public synchronized void selectPageSizeDd(String pageSize) {
		try {
			actionUtil.waitForElement(ddPageSize, "Page size Drop down");
			actionUtil.selectByText(ddPageSize, pageSize,"Page size Drop down");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to select Page size Option");
			Assert.fail("Unable to select Page size Option");
		}
	}

	/**
	 * Description Method to fetch Pending count Text
	 * 
	 * @author Sajal
	 */
	public synchronized String getPendingCountTxt() {
		try {
			actionUtil.waitForElement(txtPendingCount, "Pending count Text");
			String pendingCount = actionUtil.getText(txtPendingCount, "Pending count Text");
			return pendingCount;
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to fetch Pending count Text");
			Assert.fail("Unable to fetch Pending count Text");
		}
		return null;
	}

	/**
	 * Description Method to fetch Approved count Text
	 * 
	 * @author Sajal
	 */
	public synchronized String getApprovedCountTxt() {
		try {
			actionUtil.waitForElement(txtApprovedCount, "Approved count Text");
			String approvedCount = actionUtil.getText(txtApprovedCount, "Approved count Text");
			return approvedCount;
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to fetch Approved count Text");
			Assert.fail("Unable to fetch Approved count Text");
		}
		return null;
	}

	/**
	 * Description Method to fetch Revoked count Text
	 * 
	 * @author Sajal
	 */
	public synchronized String getRevokedCountTxt() {
		try {
			actionUtil.waitForElement(txtRevokedCount, "Revoked count Text");
			String revokedCount = actionUtil.getText(txtRevokedCount, "Revoked count Text");
			return revokedCount;
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to fetch Revoked count Text");
			Assert.fail("Unable to fetch Revoked count Text");
		}
		return null;
	}

	/**
	 * Description Method to fetch Cancelled count Text
	 * 
	 * @author Sajal
	 */
	public synchronized String getCancelledCountTxt() {
		try {
			actionUtil.poll(2000);
			actionUtil.waitForElement(txtCancelledCount, "Cancelled count Text");
			String cancelledCount = actionUtil.getText(txtCancelledCount, "Cancelled count Text");
			return cancelledCount;
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to fetch Cancelled count Text");
			Assert.fail("Unable to fetch Cancelled count Text");
		}
		return null;
	}

	/**
	 * Description Method to fetch Rejected count Text
	 * 
	 * @author Sajal
	 */
	public synchronized String getRejectedCountTxt() {
		try {
			actionUtil.waitForElement(txtRejectedCount, "Rejected count Text");
			String rejectedCount = actionUtil.getText(txtRejectedCount, "Rejected count Text");
			return rejectedCount;
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to fetch Rejected count Text");
			Assert.fail("Unable to fetch Rejected count Text");
		}
		return null;
	}

	/**
	 * Description Method to click on Pending tab
	 * 
	 * @author Sajal
	 */
	public synchronized void clkPendingTab() {
		try {
			actionUtil.waitForElement(tabPending, "Pending Tab");
			actionUtil.clickOnElement(tabPending, "Pending Tab");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Pending Tab");
			Assert.fail("Unable to perform action on Pending Tab");
		}
	}

	/**
	 * Description Method to click on Approved tab
	 * 
	 * @author Sajal
	 */
	public synchronized void clkApprovedTab() {
		try {
			actionUtil.waitForElement(tabApproved, "Approved Tab");
			actionUtil.clickOnElement(tabApproved, "Approved Tab");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Approved Tab");
			Assert.fail("Unable to perform action on Approved Tab");
		}
	}

	/**
	 * Description Method to click on Revoked tab
	 * 
	 * @author Sajal
	 */
	public synchronized void clkRevokedTab() {
		try {
			actionUtil.waitForElement(tabRevoked, "Revoked Tab");
			actionUtil.clickOnElement(tabRevoked, "Revoked Tab");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Revoked Tab");
			Assert.fail("Unable to perform action on Revoked Tab");
		}
	}

	/**
	 * Description Method to click on Cancelled tab
	 * 
	 * @author Sajal
	 */
	public synchronized void clkCancelledTab() {
		try {
			actionUtil.waitForElement(tabCancelled, "Cancelled Tab");
			// actionUtil.clickOnElement(tabCancelled, "Cancelled Tab");
			actionUtil.clickOnElementUsingJS(tabCancelled, "Cancelled Tab");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Cancelled Tab");
			Assert.fail("Unable to perform action on Cancelled Tab");
		}
	}

	/**
	 * Description Method to click on Rejected tab
	 * 
	 * @author Sajal
	 */
	public synchronized void clkRejectedTab() {
		try {
			actionUtil.waitForElement(tabRejected, "Rejected Tab");
			actionUtil.clickOnElement(tabRejected, "Rejected Tab");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Rejected Tab");
			Assert.fail("Unable to perform action on Rejected Tab");
		}
	}

	/**
	 * Description Method to select Drafts Option in Create Request Drop down
	 * 
	 * @author Sajal
	 */
	public synchronized void selectDrafts() {
		try {
			try {
				actionUtil.waitForElement(ddCreateApproveRequest, "Create Request Drop down");
				actionUtil.clickOnElement(ddCreateApproveRequest, "Create Request Drop down");
			} catch (Exception e) {
				actionUtil.error(e.getMessage());
				actionUtil.fail("Unable to perform action on Create Request Drop down");
				Assert.fail("Unable to perform action on Create Request Drop down");
			}
			actionUtil.waitForElement(otnDrafts, "Drafts Option in Create Request Drop down");
			actionUtil.clickOnElement(otnDrafts, "Drafts Option in Create Request Drop down");
			actionUtil.waitForInvisibilityOfElement(spinner, "Spinner", (long) 60);
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Drafts Option in Create Request Drop down");
			Assert.fail("Unable to perform action on Drafts Option in Create Request Drop down");
		}
	}

	/**
	 * Description Method to click on Submit button in cancel request pop up
	 * 
	 * @author Sajal
	 */
	public synchronized void clkSubmitBtnInCancelRequest(String remarks) {
		try {
			actionUtil.validateisElementDisplayed(txtCancelRequest, "Cancel Request Text in pop up",
					"Cancel Request pop up is displayed", "Cancel Request pop up is not displayed", "blue");
			setRemarksTaInCancelRequestPopup(remarks);
			actionUtil.waitForElement(btnSubmitInCancelRequestPopup, "Submit Button in cancel request pop up");
			actionUtil.clickOnElement(btnSubmitInCancelRequestPopup, "Submit Button in cancel request pop up");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Submit Button in cancel request pop up");
			Assert.fail("Unable to perform action on Submit Button in cancel request pop up");
		}
	}

	/**
	 * Description Method to click on OK button
	 * 
	 * @author Sajal
	 */
	public synchronized void clkOkBtnInCancelRequest() {
		try {
			actionUtil.validateisElementDisplayed(txtAreYouSureYouWantToCancel,
					"Are you sure you want to Cancel Text in pop up",
					"Are you sure you want to Cancel pop up is displayed",
					"Are you sure you want to Cancel pop up is not displayed", "blue");
			actionUtil.waitForAngularPageToLoad();
			actionUtil.waitForElement(btnOKInCancelRequest, "OK Button");
			// actionUtil.clickOnElement(btnOKInCancelRequest, "OK Button");
			actionUtil.clickOnElementUsingJS(btnOKInCancelRequest, "OK Button");
			actionUtil.validateisElementDisplayed(txtTheRequestIdIsCancelledSuccessfully,
					"The requestId is cancelled successfully message",
					"The requestId is cancelled successfully message is displayed",
					"The requestId is cancelled successfully message is not displayed", "green");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on OK Button");
			Assert.fail("Unable to perform action on OK Button");
		}
	}

	/**
	 * Description Method to click on Cancel button
	 * 
	 * @author Sajal
	 */
	public synchronized void clkCancelBtnInCancelRequest() {
		try {
			actionUtil.validateisElementDisplayed(txtAreYouSureYouWantToCancel,
					"Are you sure you want to Cancel Text in pop up",
					"Are you sure you want to Cancel pop up is displayed",
					"Are you sure you want to Cancel pop up is not displayed", "blue");
			actionUtil.waitForAngularPageToLoad();
			actionUtil.waitForElement(btnCancelInCancelRequest, "Cancel Button");
			actionUtil.clickOnElement(btnCancelInCancelRequest, "Cancel Button");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Cancel Button");
			Assert.fail("Unable to perform action on Cancel Button");
		}
	}

	/**
	 * Description Method to click on Close button in cancel request pop up
	 * 
	 * @author Sajal
	 */
	public synchronized void clkCloseBtnInCancelRequestPopup() {
		try {
			actionUtil.waitForElement(btnCloseInCancelRequestPopup, "Close Button in cancel request pop up");
			actionUtil.clickOnElement(btnCloseInCancelRequestPopup, "Close Button in cancel request pop up");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Close Button in cancel request pop up");
			Assert.fail("Unable to perform action on Close Button in cancel request pop up");
		}
	}

	/**
	 * Description Method to click on Submit button in Revocation request pop up
	 * 
	 * @author Sajal
	 * @param remarks
	 */
	public synchronized void clkSubmitBtnInRevocationRequest(String remarks) {
		try {
			actionUtil.validateisElementDisplayed(txtRevocationRequest, "Revocation Request Text in pop up",
					"Revocation Request pop up is displayed", "Revocation Request pop up is not displayed", "blue");
			setRemarksTaInRevocationRequestPopup(remarks);
			actionUtil.waitForElement(btnSubmitInRevocationRequestPopup, "Submit Button in revocation request pop up");
			actionUtil.clickOnElement(btnSubmitInRevocationRequestPopup, "Submit Button in revocation request pop up");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Submit Button in revocation request pop up");
			Assert.fail("Unable to perform action on Submit Button in revocation request pop up");
		}
	}

	/**
	 * Description Method to click on Close button in Revocation request pop up
	 * 
	 * @author Sajal
	 */
	public synchronized void clkCloseBtnInRevocationRequestPopup() {
		try {
			actionUtil.waitForElement(btnCloseInRevocationRequestPopup, "Close Button in Revocation request pop up");
			actionUtil.clickOnElement(btnCloseInRevocationRequestPopup, "Close Button in Revocation request pop up");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Close Button in Revocation request pop up");
			Assert.fail("Unable to perform action on Close Button in Revocation request pop up");
		}
	}

	/**
	 * Description Method to click on OK button in Revocation request
	 * 
	 * @author Sajal
	 */
	public synchronized void clkOkBtnInRevocationRequest() {
		try {
			actionUtil.validateisElementDisplayed(txtAreYouSureYouWantToContinue,
					"Are you sure you want to continue Text in pop up",
					"Are you sure you want to continue pop up is displayed",
					"Are you sure you want to continue pop up is not displayed", "blue");
			actionUtil.waitForAngularPageToLoad();
			actionUtil.waitForElement(btnOKInRevocationRequest, "OK Button");
			actionUtil.clickOnElement(btnOKInRevocationRequest, "OK Button");
			actionUtil.validateisElementDisplayed(txtRevocatioRequestSubmittedSuccessfully,
					"Revocation request submitted successfully message",
					"Revocation request submitted successfully message is displayed",
					"Revocation request submitted successfully message is not displayed", "green");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on OK Button");
			Assert.fail("Unable to perform action on OK Button");
		}
	}

	/**
	 * Description Method to click on Cancel button in Revocation request
	 * 
	 * @author Sajal
	 */
	public synchronized void clkCancelBtnInRevocationRequest() {
		try {
			actionUtil.validateisElementDisplayed(txtAreYouSureYouWantToContinue,
					"Are you sure you want to continue Text in pop up",
					"Are you sure you want to continue pop up is displayed",
					"Are you sure you want to continue pop up is not displayed", "blue");
			actionUtil.waitForAngularPageToLoad();
			actionUtil.waitForElement(btnCancelInRevocationRequest, "Cancel Button");
			actionUtil.clickOnElement(btnCancelInRevocationRequest, "Cancel Button");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Cancel Button");
			Assert.fail("Unable to perform action on Cancel Button");
		}
	}

	/**
	 * Description Method to click on Submit button in Extension request pop up
	 * 
	 * @author Sajal
	 * @param remarks
	 */
	public synchronized void clkSubmitBtnInExtensionRequest(String remarks, String numberOfDaysOrYearOrDate) {
		try {
			actionUtil.validateisElementDisplayed(txtExtensionRequest, "Extension Request Text in pop up",
					"Extension Request pop up is displayed", "Extension Request pop up is not displayed", "blue");
			selectNewDurationRb(numberOfDaysOrYearOrDate);
			setRemarksTaInExtensionRequestPopup(remarks);
			actionUtil.waitForElement(btnSubmitInExtensionRequestPopup, "Submit Button in extension request pop up");
			actionUtil.clickOnElement(btnSubmitInExtensionRequestPopup, "Submit Button in extension request pop up");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Submit Button in extension request pop up");
			Assert.fail("Unable to perform action on Submit Button in extension request pop up");
		}
	}

	/**
	 * Description Method to click on Close button in Extension request pop up
	 * 
	 * @author Sajal
	 */
	public synchronized void clkCloseBtnInExtensionRequestPopup() {
		try {
			actionUtil.waitForElement(btnCloseInExtensionRequestPopup, "Close Button in Extension request pop up");
			actionUtil.clickOnElement(btnCloseInExtensionRequestPopup, "Close Button in Extension request pop up");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Close Button in Extension request pop up");
			Assert.fail("Unable to perform action on Close Button in Extension request pop up");
		}
	}

	/**
	 * Description Method to click on OK button in Extension request
	 * 
	 * @author Sajal
	 */
	public synchronized void clkOkBtnInExtensionRequest() {
		try {
			actionUtil.validateisElementDisplayed(txtAreYouSureYouWantToExtend,
					"Are you sure you want to extend Text in pop up",
					"Are you sure you want to extend pop up is displayed",
					"Are you sure you want to extend pop up is not displayed", "blue");
			actionUtil.waitForAngularPageToLoad();
			actionUtil.waitForElement(btnOKInExtensionRequest, "OK Button");
			actionUtil.clickOnElement(btnOKInExtensionRequest, "OK Button");
			actionUtil.validateisElementDisplayed(txtTheRequestIdIsExtendedSuccessfully,
					"The requestId is extended successfully message",
					"The requestId is extended successfully message is displayed",
					"The requestId is extended successfully message is not displayed", "green");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on OK Button");
			Assert.fail("Unable to perform action on OK Button");
		}
	}

	/**
	 * Description Method to click on Cancel button in Extension request
	 * 
	 * @author Sajal
	 */
	public synchronized void clkCancelBtnInExtensionRequest() {
		try {
			actionUtil.validateisElementDisplayed(txtAreYouSureYouWantToExtend,
					"Are you sure you want to extend Text in pop up",
					"Are you sure you want to extend pop up is displayed",
					"Are you sure you want to extend pop up is not displayed", "blue");
			actionUtil.waitForAngularPageToLoad();
			actionUtil.waitForElement(btnCancelInExtensionRequest, "Cancel Button");
			actionUtil.clickOnElement(btnCancelInExtensionRequest, "Cancel Button");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Cancel Button");
			Assert.fail("Unable to perform action on Cancel Button");
		}
	}

	/* Select New Duration radio button */
	private WebElement selectNewDuration(String numberOfDaysOrYear) {
		String xpath = "//ul[@id='selectDaysId']/descendant::a[text()='" + numberOfDaysOrYear + " Days' or text()='"
				+ numberOfDaysOrYear + " Year']";
		return driver.findElement(By.xpath(xpath));
	}

	/**
	 * Description Method to click on Select New Duration radio button
	 * 
	 * @author Sajal
	 * @param numberOfDaysOrYearOrDate
	 */
	public synchronized void selectNewDurationRb(String numberOfDaysOrYearOrDate) {
		try {
			if (numberOfDaysOrYearOrDate.length() <= 3) {
				actionUtil.waitForElement(selectNewDuration(numberOfDaysOrYearOrDate),
						"Select New Duration Radio Button");
				actionUtil.clickOnElement(selectNewDuration(numberOfDaysOrYearOrDate),
						"Select New Duration Radio Button");
			} else {
				actionUtil.select_CalendarDate("extensionCalenderDate", numberOfDaysOrYearOrDate,
						"Select New Duration Calender");
			}
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Select New Duration Radio Button");
			Assert.fail("Unable to perform action on Select New Duration Radio Button");
		}
	}

	/**
	 * Description Method to click on Change role link in Profile drop down
	 * 
	 * @author Sajal
	 * @param changeRole
	 */
	public synchronized void clkChangeRoleLnk(String changeRole) {
		try {
			actionUtil.actionMouseOver(icnProfile, "Profile Icon");
			actionUtil.waitForAngularPageToLoad();
			actionUtil.waitForElement(lnkChangeRole(changeRole), "Change Role option Link");
			actionUtil.clickOnElement(lnkChangeRole(changeRole), "Change Role option Link");
			actionUtil.waitForAngularPageToLoad();
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Change Role option Link");
			Assert.fail("Unable to perform action on Change Role option Link");
		}
	}

	/**
	 * Description Method to click on Logout link
	 * 
	 * @author Sajal
	 */
	public synchronized void clkLogoutlnk() {
		try {
			actionUtil.actionMouseOver(icnProfile, "Profile Icon");
			actionUtil.waitForElement(lnkLogout, "Logout Link");
			actionUtil.clickOnElement(lnkLogout, "Logout Link");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Logout Link");
			Assert.fail("Unable to perform action on Logout Link");
		}
	}

	/**
	 * Description Method to click on Yes button in Are you sure you want to logout
	 * pop up
	 * 
	 * @author Sajal
	 */
	public synchronized void clkYesBtnInLogout() {
		try {
			actionUtil.validateisElementDisplayed(txtAreYouSureYouWantToLogout, "Are you sure you want to logout Text",
					"Are you sure you want to logout pop up is displayed",
					"Are you sure you want to logout pop up is not displayed", "blue");
			actionUtil.waitForAngularPageToLoad();
			actionUtil.waitForElement(btnYesInLogout, "Yes Button");
			actionUtil.clickOnElement(btnYesInLogout, "Yes Button");

			actionUtil.validateisElementDisplayed(txtLoginYourAccountHeader, "Log in Your Account",
					"User Logged Out Successfully", "User Logged Out Successfully", "blue");

			actionUtil.waitForAngularPageToLoad();
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Yes Button");
			Assert.fail("Unable to perform action on Yes Button");
		}
	}

	/**
	 * Description Method to click on Cancel button in Are you sure you want to
	 * logout pop up
	 * 
	 * @author Sajal
	 */
	public synchronized void clkCancelBtnInLogout() {
		try {
			actionUtil.validateisElementDisplayed(txtAreYouSureYouWantToLogout, "Are you sure you want to logout Text",
					"Are you sure you want to logout pop up is displayed",
					"Are you sure you want to logout pop up is not displayed", "blue");
			actionUtil.waitForAngularPageToLoad();
			actionUtil.waitForElement(btnCancelInLogout, "Cancel Button");
			actionUtil.clickOnElement(btnCancelInLogout, "Cancel Button");
			actionUtil.waitForAngularPageToLoad();
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Cancel Button");
			Assert.fail("Unable to perform action on Cancel Button");
		}
	}

	/**
	 * Description Method to click on Select all check box
	 * 
	 * @author Sajal
	 */
	public synchronized void clkSelectAllCb() {
		try {
			actionUtil.waitForElement(cbSelectAll, "Select All Check box");
			// actionUtil.scrollToElement(cbSelectAll, "Select All Check box");
			actionUtil.clickCheckBox(cbSelectAll, "Select All Check box");
			actionUtil.waitForAngularPageToLoad();
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Select All Check box");
			Assert.fail("Unable to perform action on Select All Check box");
		}
	}

	/**
	 * Description Method to click on RequestID Check box
	 * 
	 * @author Sajal
	 * @param requestID
	 */
	public synchronized void clkRequestIDCb(String requestID) {
		try {
			actionUtil.waitForElement(cbRequestID(requestID), requestID + " Check box");
			// actionUtil.scrollToElement(cbRequestID(requestID), requestID + " Check box");
			actionUtil.clickCheckBox(cbRequestID(requestID), requestID + " Check box");
			actionUtil.waitForAngularPageToLoad();
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on " + requestID + " Check box");
			Assert.fail("Unable to perform action on " + requestID + " Check box");
		}
	}

	/**
	 * Description Method to click on Cancel button
	 * 
	 * @author Sajal
	 */
	public synchronized void clkCancelBtn(String remarks) {
		try {
			actionUtil.waitForElement(btnCancel, "Cancel Button");
			actionUtil.scrollToElement(btnCancel, "Cancel Button");
			actionUtil.clickOnElementUsingJS(btnCancel, "Cancel Button");
			actionUtil.waitForAngularPageToLoad();
			clkSubmitBtnInCancelRequest(remarks);
			try {
				actionUtil.validateisElementDisplayed(txtAreYouSureYouWantToCancel,
						"Are you sure you want to Cancel Text in pop up",
						"Are you sure you want to Cancel pop up is displayed",
						"Are you sure you want to Cancel pop up is not displayed", "blue");
				actionUtil.waitForAngularPageToLoad();
				actionUtil.waitForElement(btnOKInCancelRequest, "OK Button");
				actionUtil.clickOnElement(btnOKInCancelRequest, "OK Button");
				new WebDriverWait(driver, 30).until(ExpectedConditions.alertIsPresent());
				driver.switchTo().alert().accept();
				actionUtil.validateisElementDisplayed(txtTheRequestIdIsCancelledSuccessfully,
						"The requestId is cancelled successfully message",
						"The requestId is cancelled successfully message is displayed",
						"The requestId is cancelled successfully message is not displayed", "green");
			} catch (Exception e) {
				actionUtil.error(e.getMessage());
				actionUtil.fail("Unable to perform action on OK Button");
				Assert.fail("Unable to perform action on OK Button");
			}
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Cancel Button");
			Assert.fail("Unable to perform action on Cancel Button");
		}
	}

	/**
	 * Description Method to click on Revoke button
	 * 
	 * @author Sajal
	 * @param remarks
	 */
	public synchronized void clkRevokeBtn(String remarks) {
		try {
			actionUtil.waitForElement(btnRevoke, "Revoke Button");
			actionUtil.scrollToElement(btnRevoke, "Revoke Button");
			actionUtil.clickOnElementUsingJS(btnRevoke, "Revoke Button");
			actionUtil.waitForAngularPageToLoad();
			clkSubmitBtnInRevocationRequest(remarks);
			clkOkBtnInRevocationRequest();
			Thread.sleep(5000);
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Revoke Button");
			Assert.fail("Unable to perform action on Revoke Button");
		}
	}

	/**
	 * Description Method to click on Extend button
	 * 
	 * @author Sajal
	 * @param remarks
	 */
	public synchronized void clkExtendBtn(String remarks, String numberOfDaysOrYearOrDate) {
		try {
			actionUtil.waitForElement(btnExtend, "Extend Button");
			actionUtil.scrollToElement(btnExtend, "Extend Button");
			actionUtil.clickOnElementUsingJS(btnExtend, "Extend Button");
			actionUtil.waitForAngularPageToLoad();
			clkSubmitBtnInExtensionRequest(remarks, numberOfDaysOrYearOrDate);
			clkOkBtnInExtensionRequest();
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Extend Button");
			Assert.fail("Unable to perform action on Extend Button");
		}
	}

	/**
	 * Description Method to validate Request ID is removed from the table
	 * 
	 * @author Sajal
	 * @param requestID
	 */
	public synchronized void validateRequestID(String requestID) {
		try {
			txtRequestID(requestID);
			actionUtil.error(requestID + " is not removed");
			actionUtil.fail(requestID + " is not removed");
			Assert.fail(requestID + " is not removed");
		} catch (NoSuchElementException e) {
			actionUtil.info(requestID + " is removed from the page");
			actionUtil.validationinfo(requestID + " is removed from the page", "green");
		} catch (Exception e) {
			actionUtil.fail("Unable to validate " + requestID + " is removed");
			actionUtil.error("Unable to validate " + requestID + " is removed");
			Assert.fail("Unable to validate " + requestID + " is removed");
		}
	}

	// -------------------------------------------------

	/* Application Access Management Text */
	@FindBy(xpath = "//a[text()='Application Access Management']")
	private WebElement validateText;

	/**
	 * Description Method to validate decreased Pending count
	 * 
	 * @author Sajal
	 * @param expectedPendingCount
	 * @param count1
	 */
	public synchronized void validatePendingCountDecreased(String expectedPendingCount, String count) {
		try {
			int value = Integer.parseInt(expectedPendingCount);
			int count1 = Integer.parseInt(count);
			int expectedCount = value - count1;
			String pendingCountTxt = getPendingCountTxt();
			int actualCount = Integer.parseInt(pendingCountTxt);
			if (expectedCount == actualCount) {
				actionUtil.info("Pending Count is decreased by " + count1);
				actionUtil.validationinfo("Pending Count is decreased by " + count1, "blue");
			} else {
				actionUtil.error("Pending Count is not decreased by " + count1);
				actionUtil.fail("Pending Count is not decreased by " + count1);
			}
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to validate decreased Pending Count");
			Assert.fail("Unable to validate decreased Pending Count");
		}
	}

	/**
	 * Description Method to validate decreased Approved count
	 * 
	 * @author Sajal
	 * @param expectedApprovedCount
	 * @param count1
	 */
	public synchronized void validateApprovedCountDecreased(String expectedApprovedCount, String count) {
		try {
			int value = Integer.parseInt(expectedApprovedCount);
			int count1 = Integer.parseInt(count);
			int expectedCount = value - count1;
			String approvedCountTxt = getApprovedCountTxt();
			int actualCount = Integer.parseInt(approvedCountTxt);
			if (expectedCount == actualCount) {
				actionUtil.info("Approved Count is decreased by " + count1);
				actionUtil.validationinfo("Approved Count is decreased by " + count1, "blue");
			} else {
				actionUtil.error("Approved Count is not decreased by " + count1);
				actionUtil.fail("Approved Count is not decreased by " + count1);
			}
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to validate decreased Approved Count");
			Assert.fail("Unable to validate decreased Approved Count");
		}
	}

	/**
	 * Description Method to validate decreased Revoked count
	 * 
	 * @author Sajal
	 * @param expectedRevokedCount
	 * @param count1
	 */
	public synchronized void validateRevokedCountDecreased(String expectedRevokedCount, String count) {
		try {
			int value = Integer.parseInt(expectedRevokedCount);
			int count1 = Integer.parseInt(count);
			int expectedCount = value - count1;
			String revokedCountTxt = getRevokedCountTxt();
			int actualCount = Integer.parseInt(revokedCountTxt);
			if (expectedCount == actualCount) {
				actionUtil.info("Revoked Count is decreased by " + count1);
				actionUtil.validationinfo("Revoked Count is decreased by " + count1, "blue");
			} else {
				actionUtil.error("Revoked Count is not decreased by " + count1);
				actionUtil.fail("Revoked Count is not decreased by " + count1);
			}
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to validate decreased Revoked Count");
			Assert.fail("Unable to validate decreased Revoked Count");
		}
	}

	/**
	 * Description Method to validate decreased Cancelled count
	 * 
	 * @author Sajal
	 * @param expectedCancelledCount
	 * @param count1
	 */
	public synchronized void validateCancelledCountDecreased(String expectedCancelledCount, String count) {
		try {
			int value = Integer.parseInt(expectedCancelledCount);
			int count1 = Integer.parseInt(count);
			int expectedCount = value - count1;
			String cancelledCountTxt = getCancelledCountTxt();
			int actualCount = Integer.parseInt(cancelledCountTxt);
			if (expectedCount == actualCount) {
				actionUtil.info("Cancelled Count is decreased by " + count1);
				actionUtil.validationinfo("Cancelled Count is decreased by " + count1, "blue");
			} else {
				actionUtil.error("Cancelled Count is not decreased by " + count1);
				actionUtil.fail("Cancelled Count is not decreased by " + count1);
			}
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to validate decreased Cancelled Count");
			Assert.fail("Unable to validate decreased Cancelled Count");
		}
	}

	/**
	 * Description Method to validate decreased Rejected count
	 * 
	 * @author Sajal
	 * @param expectedRejectedCount
	 * @param count1
	 */
	public synchronized void validateRejectedCountDecreased(String expectedRejectedCount, String count) {
		try {
			int value = Integer.parseInt(expectedRejectedCount);
			int count1 = Integer.parseInt(count);
			int expectedCount = value - count1;
			String rejectedCountTxt = getRejectedCountTxt();
			int actualCount = Integer.parseInt(rejectedCountTxt);
			if (expectedCount == actualCount) {
				actionUtil.info("Rejected Count is decreased by " + count1);
				actionUtil.validationinfo("Rejected Count is decreased by " + count1, "blue");
			} else {
				actionUtil.error("Rejected Count is not decreased by " + count1);
				actionUtil.fail("Rejected Count is not decreased by " + count1);
			}
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to validate decreased Rejected Count");
			Assert.fail("Unable to validate decreased Rejected Count");
		}
	}

	/**
	 * Description This Method implements to enter data in Remarks text area in
	 * cancel request pop up
	 * 
	 * @author Sajal
	 * @param remarks
	 */
	private synchronized void setRemarksTaInCancelRequestPopup(String remarks) {
		try {
			actionUtil.waitForElement(taRemarksInCancelRequestPopup, "Remarks Text area in cancel request pop up");
			actionUtil.clearText(taRemarksInCancelRequestPopup, "Remarks Text area in cancel request pop up");
			actionUtil.typeText(taRemarksInCancelRequestPopup, remarks, "Remarks Text area in cancel request pop up");
			actionUtil.validatetext(remarks, taRemarksInCancelRequestPopup,
					"Remarks Text area in cancel request pop up",
					remarks + " is displayed in Remarks Text area in cancel request pop up",
					remarks + " is not displayed in Remarks Text area in cancel request pop up", "blue");
		} catch (Exception e) {
			actionUtil.fail("Unable to enter data in Remarks Text area in cancel request pop up");
			actionUtil.error("Unable to enter data in Remarks Text area in cancel request pop up");
			Assert.fail("Unable to enter data in Remarks Text area in cancel request pop up");
		}
	}

	// -----------------------------------------------------------------------------------------------------------------------

	/* Cancel link */
	@FindBy(id = "cancelPendingRequest")
	private WebElement lnkCancel;

	/* View link on Extend Table */
	@FindBy(id = "viewPendingRequest")
	private WebElement lnkViewPending;

	/* Revoke link */
	@FindBy(xpath = "//div[@class='popover-content']//a[@id='revokeApprovedRequestsReq']")
	private WebElement lnkRevoke;

	/* Extend link */
	@FindBy(xpath = "//div[@class='popover-content']//a[@id='extendApproveRequest']")
	private WebElement lnkExtend;

	/* view link on Approve Table */
	@FindBy(xpath = "//div[@class='popover-content']//a[@id='viewApproveRequest']")
	private WebElement lnkViewApprove;

	/* view link on Revoked Table */
	@FindBy(xpath = "//div[@class='popover-content']//a[@id='viewRevokedRequest']")
	private WebElement lnkViewRevoke;

	/* view link on Cancelled Table */
	@FindBy(xpath = "//div[@class='popover-content']//a[@id='viewCancelledRequest']")
	private WebElement lnkViewCancelled;

	/* view link on Reject Table */
	@FindBy(xpath = "//div[@class='popover-content']//a[@id='viewDeleteRequest']")
	private WebElement lnkViewRejected;

	/* Pending with icon */
	private WebElement pendingWithIcn(String requestID) {
		String xpath = "//td[text()='" + requestID + "']/parent::tr/descendant::a[@id='approverNamesId']";
		return driver.findElement(By.xpath(xpath));
	}

	/* List of Approver Name text */
	@FindBy(xpath = "//div[@id='approversIdssection']/descendant::li")
	private List<WebElement> lstApproverIdTxt;

	/* Close button in pending with pop up */
	@FindBy(xpath = "//div[@id='approversNamesModal']/descendant::button[@class='close']")
	private WebElement btnCloseInPendingWithPopup;

	/**
	 * Description Method to click on Extend Link in Action icon
	 * 
	 * @author Sajal
	 * @param requestID
	 */
	public synchronized void clkExtendLnk(String requestID, String tab) {
		try {
			clkActionIconOptionAndValidate(requestID, tab);
			actionUtil.waitForElement(extendLink(requestID), "Extend Link in Action Icon");
			actionUtil.clickOnElement(extendLink(requestID), "Extend Link in Action Icon");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Extend Link in Action Icon");
			Assert.fail("Unable to perform action on Extend Link in Action Icon");
		}
	}

	/**
	 * Description Method to click on Revoke Link in Action icon
	 * 
	 * @author Sajal
	 * @param requestID
	 */
	public synchronized void clkRevokeLnk(String requestID, String tab) {
		try {
			clkActionIconOptionAndValidate(requestID, tab);
			actionUtil.waitForElement(revokeLink(requestID), "Revoke Link in Action Icon");
			actionUtil.clickOnElement(revokeLink(requestID), "Revoke Link in Action Icon");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Revoke Link in Action Icon");
			Assert.fail("Unable to perform action on Revoke Link in Action Icon");
		}
	}

	/**
	 * Description Method to click on Cancel Link in Action icon
	 * 
	 * @author Sajal
	 * @param requestID
	 */
	public synchronized void clkCancelLnk(String requestID, String tab) {
		try {
			clkActionIconOptionAndValidate(requestID, tab);
			actionUtil.waitForElement(cancelLink(requestID), "Cancel Link in Action Icon");
			actionUtil.clickOnElement(cancelLink(requestID), "Cancel Link in Action Icon");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Cancel Link in Action Icon");
			Assert.fail("Unable to perform action on Cancel Link in Action Icon");
		}
	}

	/**
	 * Description Method to click on View Link in Action icon
	 * 
	 * @author Sajal
	 * @param requestID
	 */
	public synchronized void clkViewLnk(String requestID, String tab) {
		try {
			clkActionIconOptionAndValidate(requestID, tab);
			actionUtil.waitForElement(viewLink(requestID), "View Link in Action Icon");
			actionUtil.clickOnElement(viewLink(requestID), "View Link in Action Icon");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on View Link in Action Icon");
			Assert.fail("Unable to perform action on View Link in Action Icon");
		}
	}

	/**
	 * Description Method to click on Action icon
	 * 
	 * @author Sajal
	 * @param requestID
	 */
	private synchronized void clkActionIconOptionAndValidate(String requestID, String tab) {
		try {
			actionUtil.waitForElement(actionIcn(requestID), "Action Icon");
			actionUtil.doubleClickOnElement(actionIcn(requestID), "Action Icon");
			actionUtil.waitForAngularPageToLoad();
			if (tab.equalsIgnoreCase("pending")) {
				actionUtil.validateisElementDisplayed(lnkCancel, "Cancel Link",
						"Cancel Link is displayed on Pending Table ", "Cancel Link is not displayed on Pending Table ",
						"blue");
				actionUtil.validateisElementDisplayed(lnkViewPending, "View Link",
						"View Link is displayed on Pending Table ", "View Link is not displayed on Pending Table ",
						"blue");

			} else if (tab.equalsIgnoreCase("approved")) {
				actionUtil.validateisElementDisplayed(lnkRevoke, "Revoke Link",
						"Revoke Link is displayed on Approved Table", "Revoke Link is not displayed on Approved Table ",
						"blue");
				actionUtil.validateisElementDisplayed(lnkExtend, "Extend Link",
						"Extend Link is displayed on Approved Table ",
						"Extend Link is not displayed on Approved Table ", "blue");
				actionUtil.validateisElementDisplayed(lnkViewApprove, "View Link",
						"View Link is displayed on Approved Table ", "View Link is not displayed on Approved Table ",
						"blue");

			} else if (tab.equalsIgnoreCase("revoked")) {
				actionUtil.validateisElementDisplayed(lnkViewRevoke, "View Link",
						"View Link is displayed on Revoked Table ", "View Link is not displayed on Revoked Table ",
						"blue");

			} else if (tab.equalsIgnoreCase("cancelled")) {
				actionUtil.validateisElementDisplayed(lnkViewCancelled, "View Link",
						"View Link is displayed on Cancelled Table", "View Link is not displayed on Cancelled Table",
						"blue");

			} else {
				actionUtil.validateisElementDisplayed(lnkViewRejected, "View Link",
						"View Link is displayed on Rejected Table", "View Link is not displayed on Rejected Table",
						"blue");

			}
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Action Icon");
			Assert.fail("Unable to perform action on Action Icon");
		}
	}

	/**
	 * Description Method to validate Revoked count
	 * 
	 * @author Sajal
	 * @param expectedRevokedCount
	 * @param count1
	 */
	public synchronized void validateRevokedCountIncreased(String expectedRevokedCount, String count) {
		try {
			int value = Integer.parseInt(expectedRevokedCount);
			int count1 = Integer.parseInt(count);
			int expectedCount = value + count1;
			String revokedCountTxt = getRevokedCountTxt();
			int actualCount = Integer.parseInt(revokedCountTxt);
			if (expectedCount == actualCount) {
				actionUtil.info("Revoked Count is increased by " + count1);
				actionUtil.validationinfo("Revoked Count is increased by " + count1, "blue");
			} else {
				actionUtil.error("Revoked Count is not increased by " + count1);
				actionUtil.fail("Revoked Count is not increased by " + count1);
			}

		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to validate Revoked Count");
			Assert.fail("Unable to validate Revoked Count");
		}
	}

	/**
	 * Description Method to validate Pending count
	 * 
	 * @author Sajal
	 * @param expectedPendingCount
	 * @param count1
	 */
	public synchronized void validatePendingCountIncreased(String expectedPendingCount, String count) {
		try {
			int value = Integer.parseInt(expectedPendingCount);
			int count1 = Integer.parseInt(count);
			int expectedCount = value + count1;
			String pendingCountTxt = getPendingCountTxt();
			int actualCount = Integer.parseInt(pendingCountTxt);
			if (expectedCount == actualCount) {
				actionUtil.info("Pending Count is increased by " + count1);
				actionUtil.validationinfo("Pending Count is increased by " + count1, "blue");
			} else {
				actionUtil.error("Pending Count is not increased by " + count1);
				actionUtil.fail("Pending Count is not increased by " + count1);
			}
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to validate Pending Count");
			Assert.fail("Unable to validate Pending Count");
		}
	}

	/**
	 * Description Method to validate Approved count
	 * 
	 * @author Sajal
	 * @param expectedApprovedCount
	 * @param count1
	 */
	public synchronized void validateApprovedCountIncreased(String expectedApprovedCount, String count) {
		try {
			int value = Integer.parseInt(expectedApprovedCount);
			int count1 = Integer.parseInt(count);
			int expectedCount = value + count1;
			String approvedCountTxt = getApprovedCountTxt();
			int actualCount = Integer.parseInt(approvedCountTxt);
			if (expectedCount == actualCount) {
				actionUtil.info("Approved Count is increased by " + count1);
				actionUtil.validationinfo("Approved Count is increased by " + count1, "blue");
			} else {
				actionUtil.error("Approved Count is not increased by " + count1);
				actionUtil.fail("Approved Count is not increased by " + count1);
			}
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to validate Approved Count");
			Assert.fail("Unable to validate Approved Count");
		}
	}

	/**
	 * Description Method to validate Cancelled count
	 * 
	 * @author Sajal
	 * @param expectedCancelledCount
	 * @param count1
	 */
	public synchronized void validateCancelledCountIncreased(String expectedCancelledCount, String count) {
		try {
			int value = Integer.parseInt(expectedCancelledCount);
			int count1 = Integer.parseInt(count);
			int expectedCount = value + count1;
			String cancelledCountTxt = getCancelledCountTxt();
			int actualCount = Integer.parseInt(cancelledCountTxt);
			if (expectedCount == actualCount) {
				actionUtil.info("Cancelled Count is increased by " + count1);
				actionUtil.validationinfo("Cancelled Count is increased by " + count1, "blue");
			} else {
				actionUtil.error("Cancelled Count is not increased by " + count1);
				actionUtil.fail("Cancelled Count is not increased by " + count1);
			}
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to validate Cancelled Count");
			Assert.fail("Unable to validate Cancelled Count");
		}
	}

	/**
	 * Description Method to validate Rejected count
	 * 
	 * @author Sajal
	 * @param expectedRejectedCount
	 * @param count1
	 */
	public synchronized void validateRejectedCountIncreased(String expectedRejectedCount, String count) {
		try {
			int value = Integer.parseInt(expectedRejectedCount);
			int count1 = Integer.parseInt(count);
			int expectedCount = value + count1;
			String rejectedCountTxt = getRejectedCountTxt();
			int actualCount = Integer.parseInt(rejectedCountTxt);
			if (expectedCount == actualCount) {
				actionUtil.info("Rejected Count is increased by " + count1);
				actionUtil.validationinfo("Rejected Count is increased by " + count1, "blue");
			} else {
				actionUtil.error("Rejected Count is not increased by " + count1);
				actionUtil.fail("Rejected Count is not increased by " + count1);
			}
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to validate Rejected Count");
			Assert.fail("Unable to validate Rejected Count");
		}
	}

	/**
	 * Description This Method implements to enter data in Remarks text area in
	 * Extension request pop up
	 * 
	 * @author Sajal
	 * @param remarks
	 */
	private synchronized void setRemarksTaInExtensionRequestPopup(String remarks) {
		try {
			actionUtil.waitForElement(taRemarksInExtensionRequestPopup,
					"Remarks Text area in extension request pop up");
			actionUtil.clearText(taRemarksInExtensionRequestPopup, "Remarks Text area in extension request pop up");
			actionUtil.typeText(taRemarksInExtensionRequestPopup, remarks,
					"Remarks Text area in extension request pop up");
			actionUtil.validateAttribute(taRemarksInExtensionRequestPopup, "value", remarks, "Remaks Text Area",
					remarks + " is displayed in Remark Text Area", remarks + " is displayed in Remark Text Area",
					"blue");
		} catch (Exception e) {
			actionUtil.fail("Unable to enter data in Remarks Text area in extension request pop up");
			actionUtil.error("Unable to enter data in Remarks Text area in extension request pop up");
			Assert.fail("Unable to enter data in Remarks Text area in extension request pop up");
		}
	}

	/**
	 * Description This Method implements to enter data in Remarks text area in
	 * revocation request pop up
	 * 
	 * @author Sajal
	 * @param remarks
	 */
	private synchronized void setRemarksTaInRevocationRequestPopup(String remarks) {
		try {
			actionUtil.waitForElement(taRemarksInRevocationRequestPopup,
					"Remarks Text area in revocation request pop up");
			actionUtil.clearText(taRemarksInRevocationRequestPopup, "Remarks Text area in revocation request pop up");
			actionUtil.typeText(taRemarksInRevocationRequestPopup, remarks,
					"Remarks Text area in revocation request pop up");
			actionUtil.validateAttribute(taRemarksInRevocationRequestPopup, "value", remarks, "Remaks Text Area",
					remarks + " is displayed in Remark Text Area", remarks + " is displayed in Remark Text Area",
					"blue");

			//// actionUtil.validatetext(remarks, taRemarksInRevocationRequestPopup, "Remaks
			//// Text Area",
			// remarks + " is displayed in Remark Text Area", remarks + " is displayed in
			//// Remark Text Area",
			// "blue");

		} catch (Exception e) {
			actionUtil.fail("Unable to enter data in Remarks Text area in revocation request pop up");
			actionUtil.error("Unable to enter data in Remarks Text area in revocation request pop up");
			Assert.fail("Unable to enter data in Remarks Text area in revocation request pop up");
		}
	}

	/**
	 * Description Method to fetch the Approver Login ID
	 * 
	 * @author Sajal
	 * @param requestID
	 */
	public synchronized Map<String, String> getApproverLoginID(String requestID) {
		Map<String, String> approverIDs = new LinkedHashMap<String, String>();
		try {
			actionUtil.waitForElement(pendingWithIcn(requestID), "Pending With Icon");
			actionUtil.clickOnElement(pendingWithIcn(requestID), "Pending With Icon");
			actionUtil.waitForElement(lstApproverIdTxt.get(0), "Approver Name Text");
			actionUtil.validateisElementDisplayed(lstApproverIdTxt.get(0), "Approver Name Text",
					"Approver Name is displayed in popup", "Approver Name is not displayed in popup ", "Blue");
			for (int i = 0; i < lstApproverIdTxt.size(); i++) {
				String text = lstApproverIdTxt.get(i).getText();
				int i1 = i + 1;
				String trim = text.replaceAll("[^0-9]", "").trim();
				approverIDs.put("Approver" + i1, trim);
			}
			actionUtil.info("Approver IDs " + approverIDs);
			actionUtil.extentinfo("Approver IDs " + approverIDs);
			actionUtil.clickOnElement(btnCloseInPendingWithPopup, "Close Button");
			actionUtil.validateisElementDisplayed(validateText, "Application Access Manangement Text",
					"Pop is closed and Initiator dashboard is displayed",
					"Pop is closed and Initiator dashboard is not displayed", "blue");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to fetch Approver Login ID");
			Assert.fail("Unable to fetch Approver Login ID");
		}
		return approverIDs;
	}

	/**
	 * Description Method to click on Create Request button
	 * 
	 * @author Sajal
	 */
	public synchronized void clkCreateRequestBtn() {
		try {
			actionUtil.waitForElement(btnCreateRequest, "Create Request Button");
			actionUtil.scrollToElement(btnCreateRequest, "Create Request Button");
			actionUtil.clickOnElement(btnCreateRequest, "Create Request Button");
			actionUtil.waitForInvisibilityOfElement(spinner, "Spinner", (long) 60);
			actionUtil.validateisElementDisplayed(lnkNewReqPage, "New Request Page", "New Request page is Display",
					"New Request page is not Display", "blue");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on Create Request Button");
			Assert.fail("Unable to perform action on Create Request Button");
		}
	}

	/**
	 * Description Method to select New Request Option in Create Request Drop down
	 * 
	 * @author Sajal
	 */
	public synchronized void selectNewRequest() {
		try {
			try {
				actionUtil.waitForElement(ddCreateApproveRequest, "Create Request Drop down");
				actionUtil.clickOnElement(ddCreateApproveRequest, "Create Request Drop down");

				validateCreateApproveRequestDropdownOpt();

			} catch (Exception e) {
				actionUtil.error(e.getMessage());
				actionUtil.fail("Unable to perform action on Create Request Drop down");
				Assert.fail("Unable to perform action on Create Request Drop down");
			}
			actionUtil.waitForElement(otnNewRequest, "New Request Option in Create Request Drop down");
			actionUtil.clickOnElement(otnNewRequest, "New Request Option in Create Request Drop down");
			actionUtil.waitForInvisibilityOfElement(spinner, "Spinner", (long) 60);
			actionUtil.validateisElementDisplayed(lnkNewReqPage, "New Request Page", "New Request page is displayed",
					"New Request page is not displayed", "blue");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to perform action on New Request Option in Create Request Drop down");
			Assert.fail("Unable to perform action on New Request Option in Create Request Drop down");
		}
	}

	/**
	 * Description Method to validate Request ID is present from the table
	 * 
	 * @author Sajal
	 * @param requestId
	 */
	public synchronized void validateRequestIdPresent(String requestId) {
		try {
			actionUtil.waitForElement(txtRequestID(requestId), "Request Id");
			// txtRequestID(requestId);
			actionUtil.validateisElementDisplayed(txtRequestID(requestId), "Request Id Text",
					requestId + " is present in the list", requestId + " is not present in the list", "blue");

		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to validate " + requestId + " is present");
			Assert.fail("Unable to validate " + requestId + " is present");
		}
	}

	/* Initiator Dash board header link */
	@FindBy(xpath = "//ol[@class='role-pagination new-pagination']/descendant::a[text()='Initiator Dashboard']")
	private WebElement lnkInitiatorDashboardHeader;

	/* Dash board header link */
	@FindBy(xpath = "//ol[@class='role-pagination new-pagination']/descendant::a[text()='Dashboard']")
	private WebElement lnkDashboardHeader;

	/**
	 * Description Method to validate Page is displayed
	 * 
	 * @author Sajal
	 * @param page
	 */
	public synchronized void validatePage(String page) {
		try {
			if (page.equalsIgnoreCase("Initiator Dashboard")) {
				actionUtil.validateisElementDisplayed(lnkInitiatorDashboardHeader, "Initiator Dashboard header Link",
						"Initiator Dashboard page is displayed", "Initiator Dashboard page is not displayed", "blue");

			} else if (page.equalsIgnoreCase("Approve Request")) {
				try {
					if (spinner1.isDisplayed()) {
						actionUtil.waitForInvisibilityOfElement(spinner1, "Spinner", 80L);
					}
				} catch (StaleElementReferenceException r) {
					if (spinner1.isDisplayed())
						actionUtil.waitForInvisibilityOfElement(spinner1, "Spinner", 80L);
				}
				actionUtil.validateisElementDisplayed(lnkDashboardHeader, "Approver Dashboard header Link",
						"Approver Dashboard page is displayed", "Approver Dashboard page is not displayed", "blue");
			} else {
				actionUtil.error("Page Header text mismatched");
			}

		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to validate " + page + " page");
			Assert.fail("Unable to validate " + page + " page");
		}
	}

	/* Black Dot Icon */
	private WebElement icnBlackDot(String requestID) {
		String xpath = "//td[text()='" + requestID + "']//span[@class='dotL grayBG']";
		return driver.findElement(By.xpath(xpath));
	}

	/**
	 * Description Method to validate Black Dot
	 * 
	 * @author Manikandan A
	 * 
	 * @param page
	 * @param requestID
	 */
	public synchronized void validateBlackDot(String requestID, String page) {
		try {
			validatePage(page);
			actionUtil.waitForElement(icnBlackDot(requestID), "Black Dot");
			actionUtil.validateisElementDisplayed(icnBlackDot(requestID), "Black Dot",
					requestID + " request is displayed with Black Dot",
					requestID + " request is not displayed with Black Dot", "green");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail(requestID + " request is not present in List");
			Assert.fail(requestID + " request is not present in List");
		}
	}

	/**
	 * Description Method to validate Options under create/approve request drop down
	 * 
	 * @author Ashish Piplodiya
	 */
	public synchronized void validateCreateApproveRequestDropdownOpt() {
		try {
			List<String> expectedDropdownOpt = new ArrayList();
			expectedDropdownOpt.add(BaseTest.prop_constants.getProperty("new_request"));
			expectedDropdownOpt.add(BaseTest.prop_constants.getProperty("drafts"));
			expectedDropdownOpt.add(BaseTest.prop_constants.getProperty("approve_request"));
			expectedDropdownOpt.add(BaseTest.prop_constants.getProperty("copy_clone"));

			ArrayList<String> actualDropdownOpt = new ArrayList<String>();
			actionUtil.waitForElements(createApproveRequestDropdownOpt, "Create/Approve Request dropdown Option");
			for (int i = 0; i < createApproveRequestDropdownOpt.size(); i++) {
				actualDropdownOpt.add(createApproveRequestDropdownOpt.get(i).getText());
			}
			Assert.assertEquals(actualDropdownOpt, expectedDropdownOpt);
			actionUtil.validationinfo("All options are displayed in Create Request dropdown", "blue");
			actionUtil.info("All options are displayed in Create Request dropdown");
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("All options are not displayed in Create Request dropdown");
			Assert.fail("All options are not displayed in Create Request dropdown");

		}
	}

}
