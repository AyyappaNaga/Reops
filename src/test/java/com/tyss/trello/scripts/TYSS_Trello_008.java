package com.tyss.trello.scripts;

import org.testng.annotations.Test;

import com.tyss.trello.baseutil.BaseTest;
import com.tyss.trello.dataproviders.DataProviderFactory;
import com.tyss.trello.dataproviders.DataProviderFileRowFilter;

/*
 * TestCaseId: Trello_008
 * TestScript Name: TYSS_Trello_008
 * Description: Verify that user is unable to sent invitation with wrong credentials
 * 
 * Author: Sajal
 */
public class TYSS_Trello_008 extends BaseTest {
//	final String testCaseID = "Trello_008";

	@DataProviderFileRowFilter(file =  "/src/test/resources/TestData/Trello_Data.xlsx", sql = "Select * from TrelloData where testCaseID ='Trello_001'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify that user is unable to sent invitation with wrong credentials")
	public void TC_TYSS_Trello_008(String testCaseID, String boardName, String listName, String cardName,
			String list2Name, String checklistName, String item, String label, String description, String comment,
			String color) {

		/* Login to Application */
		pages.loginPage.loginToApplication(prop.getProperty("email"), prop.getProperty("password"));

		/* Click on Create New Board */
		pages.homePage.clkCreateNewBoard();

		/* Enter Board Name */
		pages.homePage.setBoardTitle(boardName);

		/* Click on Create Board */
		pages.homePage.clkCreateBoardBtn();

		/* Send Invitation */
		pages.boardPage.sendInvitation(prop_constants.getProperty("emailid"), prop_constants.getProperty("invite"));

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
