package com.tyss.trello.scripts;

import org.testng.annotations.Test;

import com.tyss.trello.baseutil.BaseTest;
import com.tyss.trello.dataproviders.DataProviderFactory;
import com.tyss.trello.dataproviders.DataProviderFileRowFilter;

/*
 * TestCaseId: Trello_010
 * TestScript Name: TYSS_Trello_010
 * Description: Verify that whether user is able to close a Board
 * 
 * Author: Sajal
 */
public class TYSS_Trello_010 extends BaseTest {
//	final String testCaseID = "Trello_010";

	@DataProviderFileRowFilter(file =  "/src/test/resources/TestData/Trello_Data.xlsx", sql = "Select * from TrelloData where testCaseID ='Trello_001'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify that whether user is able to close a Board")
	public void TC_TYSS_Trello_010(String testCaseID, String boardName, String listName, String cardName,
			String list2Name, String checklistName, String item, String label, String description, String comment,
			String color) {

		/* Login to Application */
		pages.loginPage.loginToApplication(prop.getProperty("email"), prop.getProperty("password"));

		/* Click on Create New Board */
		pages.homePage.clkCreateNewBoard1();

		/* Enter Board Name */
		pages.homePage.setBoardTitle(boardName);

		/* Click on Create Board */
		pages.homePage.clkCreateBoardBtn();

		/* Send Invitation */
		pages.boardPage.sendInvitation(prop_constants.getProperty("emailid"), prop_constants.getProperty("invited"));

		/* click on Send Invitation Button */
		pages.boardPage.clkSendInvitationBtn();

		/* Close Board */
		pages.boardPage.clkOnCloseBoard();

		/* Validate Close Board */
		pages.boardPage.validateCloseBoard(boardName);

		/* logout */
		pages.boardPage.clkLogOut();

	}
}
