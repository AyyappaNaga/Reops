package com.tyss.trello.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.tyss.trello.baseutil.BaseTest;
import com.tyss.trello.commonutils.FileVariables;

/**
 * Description : Implements the creation of the Extent HTML report with
 * specified name after loading the Extent config file from specified location
 * 
 * @author Sajal, Vikas
 */
public class Extent {
	private static ExtentTest extentTest;
	private static ExtentReports extentReports;
	FileVariables fileVariables = new FileVariables();

	/**
	 * Description : Creates the HTML report based on the name specified
	 * 
	 * @author Sajal, Vikas
	 * @param name
	 */
	public static ExtentTest createTest(String name) {
		extentTest = extentReports.createTest(name);
		return extentTest;
	}

	public static ExtentReports getExtent() {
		return extentReports;
	}

	/**
	 * Description : Initializes report specified in the file path
	 * 
	 * @author Sajal, Vikas
	 * @param
	 */
	public void initReport(String filepath) {
		extentReports = getExtent(filepath);
		BaseTest.logger.info("Report Initiated");
	}

	/**
	 * Description: Creates HTML report in specified path
	 * 
	 * @author Sajal, Vikas
	 * @param filepath
	 */
	public ExtentReports getExtent(String filepath) {
		if (extentReports == null) {
			try {
				extentReports = new ExtentReports();
				extentReports.attachReporter(getHtmlReporter(filepath + "/AutomationReport.html"));
				return extentReports;
			} catch (Exception e) {
				BaseTest.logger.info("Exception while creating report html file");
			}
		}
		return extentReports;
	}

	/**
	 * Description : Loads the Extent-config file from the specified location
	 * 
	 * @author Sajal, Vikas
	 * @param filePath
	 */
	private static ExtentHtmlReporter getHtmlReporter(String filePath) {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filePath);
		htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "./config\\extent-config.xml");
		return htmlReporter;
	}

}
