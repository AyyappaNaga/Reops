package com.tyss.trello.baseutil;

import org.openqa.selenium.WebDriver;

import com.tyss.trello.pages.InitiatorDashboard_Page;
import com.tyss.trello.pages.Trello_BoardPage;
import com.tyss.trello.pages.Trello_CardPage;
import com.tyss.trello.pages.Trello_HomePage;
import com.tyss.trello.pages.Trello_LoginPage;
import com.tyss.trello.util.WebActionUtil;

/**
 * Description: Initializes all pages with driver instance ,Explicit Time out,
 * WebAactionUtility using variables driver,ETO,actionUtil
 * 
 * @author : Sajal
 */
public class InitializePages {

	public InitiatorDashboard_Page initiatorDashboardPage;
	public Trello_LoginPage loginPage;
	public Trello_HomePage homePage;
	public Trello_BoardPage boardPage;
	public Trello_CardPage cardPage;

	public InitializePages(WebDriver driver, long ETO, WebActionUtil actionUtil) {

		initiatorDashboardPage = new InitiatorDashboard_Page(driver, ETO, actionUtil);
		loginPage = new Trello_LoginPage(driver, ETO, actionUtil);
		homePage = new Trello_HomePage(driver, ETO, actionUtil);
		boardPage = new Trello_BoardPage(driver, ETO, actionUtil);
		cardPage = new Trello_CardPage(driver, ETO, actionUtil);

	}
}
