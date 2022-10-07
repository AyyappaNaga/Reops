package com.tyss.trello.scripts;

import org.testng.annotations.Test;

import com.tyss.trello.baseutil.BaseTest;
import com.tyss.trello.dataproviders.DataProviderFactory;
import com.tyss.trello.dataproviders.DataProviderFileRowFilter;
/*
 * TestCaseId: Trello_005
 * TestScript Name: TYSS_Trello_005
 * Description: Verify that whether user is able to create checklist for a Card
 * 
 * Author: Sajal
 */
public class TYSS_Trello_005 extends BaseTest {
//	final String testCaseID="Trello_005";
	@DataProviderFileRowFilter(file =  "/src/test/resources/TestData/Trello_Data.xlsx", sql = "Select * from TrelloData where testCaseID ='Trello_005'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify that whether user is able to create checklist for a Card")
	public void TC_TYSS_Trello_005(String testCaseID, String boardName, String listName, String cardName, String list2Name,
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

		/* Add CheckList for a Card */
		pages.cardPage.addCheckList(checklistName, item);

		/* Close Card PopUp */
		pages.cardPage.clkCloseCardPopUp();

		/* Validate checklist */
		pages.boardPage.validateChecklist(cardName);

		/* Close Board */
		pages.boardPage.clkOnCloseBoard();

		/* Validate Close Board */
		pages.boardPage.validateCloseBoard(boardName);

		/* logout */
		pages.boardPage.clkLogOut();
	}
}