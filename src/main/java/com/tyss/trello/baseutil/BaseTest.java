package com.tyss.trello.baseutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.zeroturnaround.zip.ZipUtil;

import com.aventstack.extentreports.ExtentTest;
import com.tyss.trello.commonutils.FileOperation;
import com.tyss.trello.commonutils.FileVariables;
import com.tyss.trello.reports.Extent;
import com.tyss.trello.reports.ExtentManager;
import com.tyss.trello.util.WebActionUtil;

/***********************************************************************
 * Description : Implements Application Precondition and Postcondition.
 * 
 * @author : Sajal
 * @BeforeSuite: Creates all the folder structure for Extent Reports
 * @BeforeClass : Launches the browser according to the browser name specified.
 * @BeforeMethod : Creates nodes for the test methods in Extent report and
 *               Launch the application
 * @AfterClass : Closes the browser after completion of execution
 * @AfterSuite: Kills the driver (example chromedriver.exe) according to browser
 *              specified and generate zip file of the report folder
 */

public class BaseTest {

	public WebDriver driver;
	public static Properties prop;
	public static Properties prop_constants;
	public static final int ITO = 10;
	public static final int ETO = 15;
	public static final String sDirPath = System.getProperty("user.dir");
	public static Logger logger = LoggerFactory.getLogger(BaseTest.class);
	public static WebActionUtil actionUtil;
	public String testCaseName;
	public static final String LOCAL_HUB_URL = "http://localhost:4444/wd/hub";
	public static final String CONFIGPATH = sDirPath + "./config/config.properties";
	public static final String JENKINS_MAILER = sDirPath + "./jenkins_mailer/AutomationReport.zip";
	public static final String JENKINS_MAILER_FOLDER = sDirPath + "./jenkins_mailer";
	public static final String VALIDATION_CONSTANTS = sDirPath
			+ "./src/test/resources/TestData/validation_constants.properties";
	public static final String UPLOADFILESPATH = sDirPath + "./src/test/resources/UploadFiles";
	public static final String TESTDATAEXCELPATH = "./src/test/resources/TestData/Trello_Data.xlsx";
	public static InitializePages pages;
	public static String className;
	public static final String driverPath = sDirPath + "/drivers";
	public static final int pageLoadTimeout = 15;
	static {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(CONFIGPATH);
			prop.load(fis);
			prop_constants = new Properties();
			FileInputStream fis1 = new FileInputStream(VALIDATION_CONSTANTS);
			prop_constants.load(fis1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Description : Creates folder structure for Extent reports.
	 * 
	 * @author:Sajal
	 */
	@BeforeSuite(alwaysRun = true)
	public synchronized void createFiles() {
		try {
			logger.info("Folder creation for Extent");
			FileOperation fileOperation = new FileOperation();
			fileOperation.CreateFiles();
			// Runtime.getRuntime().exec("wscript "+
			// System.getProperty("user.dir")+"/PreventScreenLock.vbs");
		} catch (Exception e) {
			logger.error("Exception while report inititation");
		}
	}

	/**
	 * Description : Download the current driver executable in drivers folder
	 * 
	 * @author:Sajal
	 * @param browserName
	 */
	@Parameters({ "browserName" })
	@BeforeTest(alwaysRun = true)
	public synchronized void downloadDriverExecutables(String browserName) {
		WebDriverInstance.setDriverExecutables(browserName);
	}

	/**
	 * Description: Launches the browser as specified in the parameter
	 * 
	 * @author Sajal
	 * @param browserName
	 */
	@Parameters({ "browserName" })
	@BeforeClass
	public synchronized void launchBrowser(String browserName) {
		className = getClass().getSimpleName();
		logger = LoggerFactory.getLogger(getClass().getName());
		ExtentTest parentExtentTest = Extent.createTest(getClass().getSimpleName());
		ExtentManager.setParentReport(parentExtentTest);
		driver = WebDriverInstance.getDriver(browserName);
		// driver = CreateDriver.getInstance().launchBrowserUsingGrid(browserName,
		// LOCAL_HUB_URL);
		driver.manage().timeouts().implicitlyWait(ITO, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(pageLoadTimeout, TimeUnit.SECONDS);
		actionUtil = new WebActionUtil(driver, ETO);
		driver.manage().window().maximize();
//		String appurl = prop.getProperty("app_URL");
//		driver.manage().deleteAllCookies();
//		driver.get(appurl);
//		new WebDriverWait(driver, 30).until(ExpectedConditions.urlToBe(appurl));
//		pages = new InitializePages(driver, ETO, actionUtil);
	}

	/**
	 * Description: Creates nodes for the test methods in Extent report and launch
	 * the application
	 * 
	 * @author Sajal
	 * @param browserName
	 * @param methodName
	 */
	@BeforeMethod(alwaysRun = true)
	@Parameters({ "browserName" })
	public synchronized void setExtentReport(String browserName, Method methodName) {
		this.testCaseName = methodName.getName();
		String description = methodName.getAnnotation(Test.class).description();
		try {
			String appurl = prop.getProperty("app_URL");
			driver.manage().deleteAllCookies();
			driver.get(appurl);
			new WebDriverWait(driver, 30).until(ExpectedConditions.urlToBe(appurl));
			pages = new InitializePages(driver, ETO, actionUtil);
			ExtentTest testReport = ExtentManager.getParentReport().createNode(testCaseName, description);
			ExtentManager.setTestReport(testReport);
			if (driver.getWindowHandles().size() > 0) {
				actionUtil.validationinfo(browserName + " Browser is launched", "blue");
				actionUtil.info(browserName + " Browser is launched");
			} else {
				actionUtil.fail("Failed to launch the Browser");
				actionUtil.error("Failed to launch the Browser");
			}
		} catch (Exception e) {
			throw new SkipException("Failed to launch the application");
		}
	}

	/**
	 * Description: Closes the browser
	 * 
	 * @author:Sajal
	 */
	@AfterClass(alwaysRun = true)
	public synchronized void closeBrowser() {
		try {
			WebDriverInstance.cleanDriver();
//			if (driver != null) {
//				driver.quit();
//			} else {
//				logger.error("Unable to close the driver");
//			}
		} catch (Exception e) {
		}
	}

	/**
	 * Description: Kills the driver in Task Manager as specified in the parameter
	 * and generate ZIP file for report folder
	 * 
	 * @author Sajal
	 * @param browserName
	 */
	@AfterSuite(alwaysRun = true)
	@Parameters({ "browserName" })
	public synchronized void killTask(String browserName) {
		WebDriverInstance.killBrowserTask(browserName);
		try {
			/* Converting report folder in ZIP file */
			logger.info("Compressing report for email....");
			Files.createDirectories(Paths.get(JENKINS_MAILER_FOLDER));
			ZipUtil.pack(new File(FileVariables.getExtentDir()), new File(JENKINS_MAILER));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Description: Logging the Bug to Jira
	 * 
	 * @author: Manikandan A
	 * @param :result
	 */
//	@AfterMethod
//	public synchronized void bug(ITestResult result) throws JiraException {
//
//		BasicCredentials creds = new BasicCredentials("manikandantyss", "Mani@9120");
//	JiraClient client = new JiraClient("http://localhost:8081/", creds);
//	Issue issue;
//	
//		if (result.getStatus() == ITestResult.FAILURE) {
//			logger.info("Issue");
//
//			issue = client.createIssue("TRELISSUE", "Bug")
//					.field(Field.SUMMARY,
//							result.getMethod().getMethodName() + " is failed due to : "
//									+ result.getThrowable().toString())
//					.field(Field.DESCRIPTION, "get the description").execute();
//			logger.info("Issue is created with key:" + issue.getKey());
//			
//		}
//
//	}

}
