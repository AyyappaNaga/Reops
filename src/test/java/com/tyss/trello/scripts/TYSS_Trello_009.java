package com.tyss.trello.scripts;

import org.testng.annotations.Test;

import com.tyss.trello.baseutil.BaseTest;
import com.tyss.trello.dataproviders.DataProviderFactory;
import com.tyss.trello.dataproviders.DataProviderFileRowFilter;

/*
 * TestCaseId: Trello_009
 * TestScript Name: TYSS_Trello_009
 * Description: Verify that user is able to delete the workspace
 * 
 * Author: Sajal
 */
public class TYSS_Trello_009 extends BaseTest {
//	final String testCaseID="Trello_009";
	@DataProviderFileRowFilter(file =  "/src/test/resources/TestData/Trello_Data.xlsx", sql = "Select * from TrelloData where testCaseID ='Trello_001'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify that user is able to delete the workspace")
	public void TC_TYSS_ModuleName_009(String testCaseID, String boardName, String listName, String cardName,
			String list2Name, String checklistName, String item, String label, String description, String comment,
			String color) {

		/* Login to Application */
		pages.loginPage.loginToApplication(prop.getProperty("email"), prop.getProperty("password"));
	
		/* click on settings tab */
		pages.homePage.clkSettingsTab();

		/* click Delete This Workspace */
		pages.homePage.clkDeleteThisWorkspace();

		/* set Confirm Workspace Name */
		pages.homePage.setConfirmWorkspaceName(prop_constants.getProperty("workspaceName"));

		/* click Delete Workspace */
		pages.homePage.clkDeleteWorkspace();

		/*validate Workspace Is Deleted*/
		pages.homePage.validateWorkspaceIsDeleted(prop_constants.getProperty("workspaceName"));
		
		/* logout */
		pages.boardPage.clkLogOut();
	}

}
