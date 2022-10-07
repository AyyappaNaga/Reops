package com.tyss.trello.scripts;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.tyss.trello.baseutil.BaseTest;
import com.tyss.trello.dataproviders.DataProviderFactory;
import com.tyss.trello.dataproviders.DataProviderFileRowFilter;
/*
 * TestCaseId: Trello_002
 * TestScript Name: TYSS_Trello_002
 * Description: Verify that whether user is able to Move a Card
 * 
 * Author: Sajal
 */
public class TYSS_Trello_002 extends BaseTest {
//	final String testCaseID="Trello_002";
	@DataProviderFileRowFilter(file = "/src/test/resources/TestData/Trello_Data.xlsx", sql = "Select * from TrelloData where testCaseID ='Trello_002'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify that whether user is able to Move a Card")
	public void TC_TYSS_Trello_002(String testCaseID, String boardName, String listName, String cardName, String list2Name,
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

		/* Enter List Name */
		pages.boardPage.setListName(list2Name);

		/* click on move card */
		pages.boardPage.clkOnMove(list2Name);
		List<String> cards = new ArrayList();
		cards.add(cardName);

		pages.boardPage.validateMoveCard(listName, list2Name, cards);

		/* Close Board */
		pages.boardPage.clkOnCloseBoard();

		/* Validate Close Board */
		pages.boardPage.validateCloseBoard(boardName);

		/* logout */
		pages.boardPage.clkLogOut();

	}
}
