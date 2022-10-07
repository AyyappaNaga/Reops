package com.tyss.trello.baseutil;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/***********************************************************************
 * Description : Implements Browser launching code through various ways
 * 
 * @author : Sajal
 */
public class WebDriverInstance {

	public static String userDataChromePath = System.getProperty("user.home") + "\\AppData\\Local\\Google\\Chrome\\UserData";
	public static String userDataEdgePath = System.getProperty("user.home") + "\\AppData\\Local\\Microsoft\\Edge\\UserData";
	public static String userDataOperaPath = System.getProperty("user.home")
			+ "\\AppData\\Local\\Opera Software\\Opera\\UserData";
	public static String operaProxy = "77.111.246.20";
	
	
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	private WebDriverInstance() {
	}
	/**
	 * Description: Method to retrieve active driver instance
	 * 
	 * @author Sajal,vikas
	 * @return instance
	 */
	public static WebDriver getDriver(String browserName) {

		if (driver.get() == null) {
			try {
				driver.set(createDriver(browserName));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return driver.get();
	}

	public static void cleanDriver() {

		driver.get().quit();
		driver.remove();

	}

	/**
	 * Description : This method download latest driver executables setup
	 *
	 * @author Sajal
	 * @param browserName
	 */
	public static void setDriverExecutables(String browserName) {
		try {
			killBrowserTask(browserName);
			if (browserName.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().forceDownload().cachePath(BaseTest.driverPath).avoidOutputTree()
						.setup();
			} else if (browserName.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().forceDownload().cachePath(BaseTest.driverPath).avoidOutputTree()
						.setup();
			} else if (browserName.equalsIgnoreCase("safari")) {
				WebDriverManager.safaridriver().clearDriverCache().forceDownload().cachePath(BaseTest.driverPath)
						.avoidOutputTree().setup();
			}
		} catch (Exception e) {
			BaseTest.logger.error("Unable to download " + browserName + " driver executable file");
		}
	}

	/**
	 * Description : This method set web driver capabilities for specified browser
	 *
	 * @author Sajal
	 * @param browser
	 * @param capabilities
	 */
	public DesiredCapabilities setWebDriverCapabilities(String browser, DesiredCapabilities capabilities) {
		browser = browser.toLowerCase();
		switch (browser) {
		case "ie":
			capabilities = new DesiredCapabilities().internetExplorer();
			break;
		case "firefox":
			capabilities = new DesiredCapabilities().firefox();
			break;
		case "chrome":
			capabilities = new DesiredCapabilities().chrome();
			break;
		case "edge":
			capabilities = new DesiredCapabilities().edge();
			break;
		case "safari":
			capabilities = new DesiredCapabilities().safari();
			break;
		default:
			capabilities = null;
		}
		return capabilities;
	}

	/**
	 * Description : Sets the web driver according to the browser specified
	 * 
	 * @author Sajal
	 * @param browser
	 * @param capabilities
	 * @param LOCAL_HUB_URL
	 */
	public WebDriver launchBrowserUsingGrid(String browser, String LOCAL_HUB_URL) {
		browser = browser.toLowerCase();
		DesiredCapabilities capabilities = null;
		switch (browser) {
		case "ie":
			capabilities = new DesiredCapabilities().internetExplorer();
			break;
		case "firefox":
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setHeadless(true);
			ProfilesIni profile = new ProfilesIni();
			FirefoxProfile testprofile = profile.getProfile("ShreyaU");
			testprofile.setPreference("dom.webnotifications.enabled", false);
			testprofile.setPreference("dom.push.enabled", false);
			capabilities = new DesiredCapabilities();
			capabilities.setBrowserName(BrowserType.FIREFOX);
			capabilities.setCapability(FirefoxDriver.PROFILE, testprofile);
			capabilities.setCapability(firefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
			firefoxOptions.merge(capabilities);
			break;
		case "chrome":
			ChromeOptions chromeOptions = new ChromeOptions();
			// chromeOptions.addArguments("--headless");
			chromeOptions.addArguments("--disable-popup-blocking");
			chromeOptions.addArguments("--disable-infobars");
			capabilities = new DesiredCapabilities();
			capabilities.setBrowserName(BrowserType.CHROME);
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			chromeOptions.merge(capabilities);
			break;
		case "edge":
			EdgeOptions edgeoptions = new EdgeOptions();
			capabilities = new DesiredCapabilities();
			capabilities.setBrowserName(BrowserType.EDGE);
			capabilities.setPlatform(Platform.WINDOWS);
			break;
		case "safari":
			capabilities = new DesiredCapabilities().safari();
			break;
		default:
			capabilities = null;
		}
		try {
			return new RemoteWebDriver(new URL(LOCAL_HUB_URL), capabilities);
		} catch (MalformedURLException e) {
			BaseTest.logger.info("The given HUB URL is Malformed");
		}
		return null;
	}

	/**
	 * Description :Sets the web driver according to the Browser
	 * 
	 * @author Sajal
	 * @param browser
	 */
	public WebDriver launchBrowser(String browser) {
		if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "/drivers/chromedriver.exe");
			ChromeOptions chromeOptions = new ChromeOptions();
			// Map<String, Object> prefs = new HashMap<String , Object>();
			// prefs.put("safebrowsing.enabled","true");
			// DesiredCapabilities capabilities = new DesiredCapabilities();
			// chromeOptions.addArguments("use-fake-device-for-media-stream");
			// chromeOptions.addArguments("use-fake-ui-for-media-stream");
			// chromeOptions.addArguments("--disable-popup-blocking");
			// chromeOptions.addArguments("--disable-infobars");
			// chromeOptions.addArguments("--disable-notifications");
			// capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			// capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
			// capabilities.setBrowserName(BrowserType.CHROME);
			// capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			// chromeOptions.merge(capabilities);
			// chromeOptions.setExperimentalOption("prefs", prefs);
			chromeOptions.addArguments("user-data-dir=" + userDataChromePath);
			return new ChromeDriver(chromeOptions);
		} else if (browser.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
			return new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("Edge")) {
			System.setProperty("webdriver.edge.driver", "./drivers/msedgedriver.exe");
			EdgeOptions edgeopt = new EdgeOptions();
			// edgeopt.addArguments("user-data-dir=" + userDataEdgePath);
			return new EdgeDriver(edgeopt);
		} else if (browser.equalsIgnoreCase("Opera")) {
			System.setProperty("webdriver.opera.driver", "./drivers/operadriver.exe");
			OperaOptions operaOptions = new OperaOptions();
			operaOptions.addArguments("user-data-dir", userDataOperaPath);
			operaOptions.addArguments("--proxy-server=%s", "%" + operaProxy);
			return new OperaDriver(operaOptions);
		} else {
			BaseTest.logger.error("Browser name not specified properly");
		}
		return null;
	}

	
	/**
	 * Description :Sets the web driver according to the Browser
	 * 
	 * @author Sajal
	 * @param browser
	 */
	public static WebDriver createDriver(String browser) {
		if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", BaseTest.sDirPath + "/drivers/chromedriver.exe");
			String userPath = System.getProperty("user.home") + "/AppData/Local/Google/Chrome/UserData";
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("safebrowsing.enabled", "true");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--disable-notifications");
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			chromeOptions.merge(capabilities);
			chromeOptions.addArguments("user-data-dir=" + userPath);
			chromeOptions.setExperimentalOption("prefs", prefs);
			return new ChromeDriver(capabilities);
		} else if (browser.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", BaseTest.sDirPath +"/drivers/geckodriver.exe");
			return new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("Edge")) {
			System.setProperty("webdriver.edge.driver", BaseTest.sDirPath +"/drivers/msedgedriver.exe");
			EdgeOptions edgeopt = new EdgeOptions();
			// edgeopt.addArguments("user-data-dir=" + userDataEdgePath);
			return new EdgeDriver(edgeopt);
		} else if (browser.equalsIgnoreCase("Opera")) {
			System.setProperty("webdriver.opera.driver", BaseTest.sDirPath +"/drivers/operadriver.exe");
			OperaOptions operaOptions = new OperaOptions();
			operaOptions.addArguments("user-data-dir", userDataOperaPath);
			operaOptions.addArguments("--proxy-server=%s", "%" + operaProxy);
			return new OperaDriver(operaOptions);
		} else {
			BaseTest.logger.error("Browser name not specified properly");
		}
		return null;
	}
	
	/**
	 * Description :Kills the driver in Task Manager as specified in the parameter
	 * 
	 * @author Sajal
	 * @param browserName
	 */
	public static void killBrowserTask(String browserName) {
		try {
			if (browserName.equalsIgnoreCase("chrome")) {
				Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
			} else if (browserName.equalsIgnoreCase("firefox")) {
				Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe");
			} else if (browserName.equalsIgnoreCase("edge")) {
				Runtime.getRuntime().exec("taskkill /F /IM msedgedriver.exe");
			} else {
				BaseTest.logger.error("Browser name not specified properly to kill the task");
			}
		} catch (Exception e) {
			BaseTest.logger.error("Unable to kill the " + browserName + " browser task");
		}
	}

	public static WebDriverInstance getInstance() {
		
		return null;
	}

}
