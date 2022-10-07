package com.tyss.trello.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.tyss.trello.baseutil.BaseTest;
import com.tyss.trello.util.WebActionUtil;

/**
 * Description This class has the implementations of the Login page
 * 
 * @author Tanu katiyar
 */
public class Trello_LoginPage {

	public WebDriver driver;
	public WebActionUtil actionUtil;
	public long ETO;

	public Trello_LoginPage(WebDriver driver, long ETO, WebActionUtil actionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.actionUtil = actionUtil;
		this.ETO = ETO;
	}

	/* Login In Button */
	@FindBy(linkText = "Log in")
	private WebElement lnkLoginIn;

	/* User Name Text Box */
	@FindBy(id = "user")
	private WebElement txtEmail;

	/* Login in with Atlassian */
	@FindBy(id = "login")
	private WebElement btnLoginAtlassian;

	/* Password Text Box */
	@FindBy(name = "password")
	private WebElement txtPassword;

	/* Login Button */
	@FindBy(xpath = "//span[text()='Log in']")
	private WebElement btnLogin;

	/* validate home page */
	@FindBy(xpath = "//p[text()='Create']")
	private WebElement btnCreate;

	/* Error Login Message */
	@FindBy(xpath = "//div[@id='login-error']/span")
	private WebElement txtErrorMsg;

	/**
	 * Description Method to Login to the application
	 * 
	 * @author Tanu Katiyar
	 * @param username
	 * @param password
	 */
	public synchronized void loginToApplication(String email, String password) {
		try {
			actionUtil.info("Login To the App");
			actionUtil.clickOnElement(lnkLoginIn, "Login In link");
			actionUtil.waitForElement(txtEmail, "Email Text Box");
			actionUtil.typeText(txtEmail, email, "Email Text Box");
			actionUtil.clickOnElement(btnLoginAtlassian, "Login with Atlassian Button");
			actionUtil.poll(3000);
			actionUtil.typeText(txtPassword, password, "Password Text Box");
			actionUtil.clickOnElement(btnLogin, "Login Button");
			if (BaseTest.prop_constants.getProperty("loginpage_url") == driver.getCurrentUrl()) {
				actionUtil.info("Invalid Email Address and Password, so unable to Login");
				actionUtil.validationinfo("Invalid Email Address and Password, so unable to Login", "blue");
				// actionUtil.validateisElementDisplayed(txtErrorMsg, "Error Login Message",
				// "Invalid Email Address and Password, so unable to Login",
				// "Able to Login with Invalid Email Address and Password", "blue");
			} else if (BaseTest.prop_constants.getProperty("homepage_url") == driver.getCurrentUrl()) {
				actionUtil.validateisElementDisplayed(btnCreate, "Create Button",
						"Login is Successful and Home Page is displayed",
						"Unable to Login and Home Page is not displayed", "green");
			}
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to Login in the Application");
			Assert.fail("Unable to Login in the Application");
		}
	}

}
