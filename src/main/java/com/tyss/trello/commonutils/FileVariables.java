package com.tyss.trello.commonutils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description : Implements creation of file variables required to perform
 * various file operations.
 * 
 * @author: Sajal, Vikas
 */
public class FileVariables {
	private static String extentDir;
	private static String screenShotPath;
	private String sStartTime;
	private String extentReportFolderPath;
	private Date date;
	private SimpleDateFormat sdf;
	private String sDate;
	private String hash;

	/**
	 * Description:Gets the screenshot path
	 * 
	 * @author:Sajal, Vikas
	 * @param screenShotPath
	 */
	public static String getScreenShotPath() {
		return screenShotPath;
	}

	/**
	 * Description:Sets the screenshot path
	 * 
	 * @author:Sajal, Vikas
	 * @param screenShotPath
	 */
	public void setScreenShotPath(String screenShotPath) {
		FileVariables.screenShotPath = screenShotPath;
	}

	/**
	 * Description:Gets start time
	 * 
	 * @author:Sajal, Vikas
	 * @return sStartTime
	 */

	public String getsStartTime() {
		return sStartTime;
	}

	/**
	 * Description:Sets the start time
	 * 
	 * @author:Sajal, Vikas
	 * @param sStartTime
	 */
	public void setsStartTime(String sStartTime) {
		this.sStartTime = sStartTime;
	}

	/**
	 * Description:Gets Extent report folder path
	 * 
	 * @author:Sajal, Vikas
	 * @return extentReportFolderPath
	 */
	public String getExtentReportFolderPath() {
		return extentReportFolderPath;
	}

	/**
	 * Description:Sets the Extent report folder path
	 * 
	 * @author:Sajal, Vikas
	 * @param extentReportFolderPath
	 */
	public void setExtentReportFolderPath(String extentReportFolderPath) {
		this.extentReportFolderPath = extentReportFolderPath;
	}

	/**
	 * Description:Gets Date
	 * 
	 * @author:Sajal, Vikas
	 * @return date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Description:Sets the date
	 * 
	 * @author:Sajal, Vikas
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Description:Gets simple date format
	 * 
	 * @author:Sajal, Vikas
	 * @return sdf
	 */
	public SimpleDateFormat getSdf() {
		return sdf;
	}

	/**
	 * Description:Sets the simple date format
	 * 
	 * @author:Sajal, Vikas
	 * @param sdf
	 */
	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}

	/**
	 * Description:Gets Date
	 * 
	 * @author:Sajal, Vikas
	 * @return sDate
	 */
	public String getsDate() {
		return sDate;
	}

	/**
	 * Description:Sets the date
	 * 
	 * @author:Sajal, Vikas
	 * @param sDate
	 */
	public void setsDate(String sDate) {
		this.sDate = sDate;
	}

	/**
	 * Description:Gets Hash
	 * 
	 * @author:Sajal, Vikas
	 * @return hash
	 */
	public String getHash() {
		return hash;
	}

	/**
	 * Description Sets the hash
	 * 
	 * @author:Sajal, Vikas
	 * @param hash
	 */
	public void setHash(String hash) {
		this.hash = hash;
	}

	/**
	 * Description:Gets extent directory
	 * 
	 * @author:Sajal, Vikas
	 * @return extentDir
	 */
	public static String getExtentDir() {
		return extentDir;
	}

	/**
	 * Description:Sets extent dir
	 * 
	 * @author:Sajal, Vikas
	 * @param extentDir
	 */
	public void setExtentDir(String extentDir) {
		this.extentDir = extentDir;
	}
}
