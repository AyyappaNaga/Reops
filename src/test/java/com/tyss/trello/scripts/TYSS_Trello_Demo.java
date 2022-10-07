package com.tyss.trello.scripts;

import org.testng.annotations.Test;

import com.tyss.trello.baseutil.BaseTest;
import com.tyss.trello.dataproviders.DataProviderFactory;
import com.tyss.trello.dataproviders.DataProviderFileRowFilter;

/*
 * TestCaseId: Trello_001
 * TestScript Name: TYSS_Trello_001
 * Description: Verify that user is able to create a card
 * 
 * Author: Sajal
 */
public class TYSS_Trello_Demo extends BaseTest {
//	final String testCaseID="Trello_001";
	@DataProviderFileRowFilter(file = "./src/test/resources/TestData/Trello_Data.xlsx", sql = "Select * from TrelloData where testCaseID ='Trello_001'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify that user is able to create a card")
	public void TC_TYSS_Trello_001(String testCaseID, String boardName, String listName, String cardName,
			String list2Name, String checklistName, String item, String label, String description, String comment,
			String color) {

		/* Login to Application */
		pages.loginPage.loginToApplication(prop.getProperty("email"), prop.getProperty("password"));

		/* Click on Create New Board */
		pages.homePage.clkCreateNewBoard();

		/* logout */
		pages.boardPage.clkLogOut();
	}
}
