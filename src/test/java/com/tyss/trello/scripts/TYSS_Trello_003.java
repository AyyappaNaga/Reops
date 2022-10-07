package com.tyss.trello.scripts;

import org.testng.annotations.Test;

import com.tyss.trello.baseutil.BaseTest;
import com.tyss.trello.dataproviders.DataProviderFactory;
import com.tyss.trello.dataproviders.DataProviderFileRowFilter;
/*
 * TestCaseId: Trello_003
 * TestScript Name: TYSS_Trello_003
 * Description: Verify that whether user is able to Create Description for a Card
 * 
 * Author: Sajal
 */
public class TYSS_Trello_003 extends BaseTest {
//	final String testCaseID="Trello_003";
	@DataProviderFileRowFilter(file =  "/src/test/resources/TestData/Trello_Data.xlsx", sql = "Select * from TrelloData where testCaseID ='Trello_003'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify that whether user is able to Create Description for a Card")
	public void TC_TYSS_Trello_003(String testCaseID, String boardName, String listName, String cardName, String list2Name,
			String checklistName, String item, String label, String description, String comment, String color) {

		/* Login to Application */
		pages.loginPage.loginToApplication(prop.getProperty("email"), prop.getProperty("password"));

		/* Click on Create New Board */
		pages.homePage.clkCreateNewBoard();

		/* Enter Board Name */
		pages.homePage.setBoardTitle(boardName);

		/* Click on Create Board */
		pages.homePage.clkCreateBoardBtn();

		/* Enter List Name */
		pages.boardPage.setListName(listName, cardName);

		/* Add Description for a Card */
		pages.cardPage.addDescription(description);

		/* Click on Save button */
		pages.cardPage.clkDescriptionSaveBtn();

		/* Close Card PopUp */
		pages.cardPage.clkCloseCardPopUp();

		/* Validate Description */
		pages.boardPage.validateDescription(cardName);

		/* Close Board */
		pages.boardPage.clkOnCloseBoard();

		/* Validate Close Board */
		pages.boardPage.validateCloseBoard(boardName);

		/* logout */
//		pages.boardPage.clkLogOut();
	}
}
