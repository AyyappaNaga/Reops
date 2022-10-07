package com.tyss.trello.scripts;

import org.testng.annotations.Test;

import com.tyss.trello.baseutil.BaseTest;
import com.tyss.trello.dataproviders.DataProviderFactory;
import com.tyss.trello.dataproviders.DataProviderFileRowFilter;
/*
 * TestCaseId: Trello_007
 * TestScript Name: TYSS_Trello_007
 * Description: Verify that user is not able to login with wrong credentials
 * 
 * Author: Sajal
 */
public class TYSS_Trello_007 extends BaseTest {
//	final String testCaseID="Trello_007";
	@DataProviderFileRowFilter(file =  "/src/test/resources/TestData/Trello_Data.xlsx", sql = "Select * from TrelloData where testCaseID ='Trello_001'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify that user is not able to login with wrong credentials")
	public void TC_TYSS_Trello_007(String testCaseID, String boardName, String listName, String cardName,
			String list2Name, String checklistName, String item, String label, String description, String comment,
			String color) {

		/* Login to Application */
		pages.loginPage.loginToApplication(prop.getProperty("email"), prop.getProperty("pwdInvalid"));

	}

}
