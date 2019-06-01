package com.capsulecrm.qa.tests;


import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.capsulecrm.qa.base.TestBase;
import com.capsulecrm.qa.pages.AccountSettingPage;
import com.capsulecrm.qa.pages.AppearancePage;
import com.capsulecrm.qa.pages.HomePage;
import com.capsulecrm.qa.pages.IntegrationsPage;
import com.capsulecrm.qa.pages.LoginPage;
import com.capsulecrm.qa.pages.OpportunitiesPage;
import com.capsulecrm.qa.pages.TagsPage;
import com.capsulecrm.qa.pages.TaskCategoriesPage;
import com.capsulecrm.qa.pages.TracksPage;
import com.capsulecrm.qa.pages.UsersPage;
import com.capsulecrm.qa.util.DataProviderUtil;
import com.capsulecrm.qa.util.ElementsUtil;
import com.capsulecrm.qa.util.TestUtil;

public class AccountSettingTest extends TestBase {
	LoginPage login;
	HomePage home;
	AccountSettingPage accountset;
	AppearancePage appearance;
	UsersPage user;
	OpportunitiesPage opportunity;
	TracksPage tracks;
	TaskCategoriesPage taskcat;
	TagsPage tags;
	IntegrationsPage integration;
	String actualheader;
	String msg = "";


	public AccountSettingTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		initialization();

		login = new LoginPage(driver);
		home = new HomePage(driver);
		accountset = new AccountSettingPage(driver);
		appearance = new AppearancePage(driver);
		user = new UsersPage(driver);
		opportunity = new OpportunitiesPage(driver);
		tracks = new TracksPage(driver);
		taskcat = new TaskCategoriesPage(driver);
		tags = new TagsPage(driver);
		integration = new IntegrationsPage(driver);
		home = login.Login(prop.getProperty("username"), prop.getProperty("password"));

	}

	@Test(priority=2, dataProvider = "testdata", dataProviderClass = DataProviderUtil.class)
	public void AccountSettingValidTest(String fname, String lname, String email, String uname, String tskcatname, String tagname) {

		
		//Going to Account Setting and validating header
		accountset = home.clickAccountSettingLink();
		actualheader = accountset.getAccountSettingHeader();
		msg = "header is not same as expected";
		ElementsUtil.Assert(actualheader, "Account Settings","AccountSettingPage "+ msg);

		//Account Header Validation
		actualheader = accountset.getAccountHeader();
		ElementsUtil.Assert(actualheader, "Account", "AccountPage"+msg);

		//Export Header Validation
		actualheader = accountset.getExportHeader();
		ElementsUtil.Assert(actualheader, "Export","ExportPage "+ msg);

		//Validating header and Adding new logo to AppearancePage
		appearance = accountset.AppearanceLink();
		actualheader = appearance.getAppearanceHeader();
		ElementsUtil.Assert(actualheader, "Appearance", "AppearancePage "+msg);
		appearance.addLogo(System.getProperty("user.dir")+"/src/main/resources/data/logo.jpg");
		//Validating logo
		String logoname = null;
		logoname = appearance.getCurrentLogo();
		if(!logoname.isEmpty()) {
			TEST_LOG.info("*****Logo is Added****");
		}
		else {
			TEST_LOG.info("*****Logo is not Added****");
		}
		//Back to AccountSettingPage
		accountset = appearance.backToAccountSetting();

		//Mail Drop Box header validation
		actualheader= accountset.getMailDropBoxHeader();
		ElementsUtil.Assert(actualheader, "Mail Drop Box","Mail Drop BoxPage "+ msg);

		//Validating header and Adding new users to UsersPage
		user = accountset.UsersLink();
		actualheader = user.getUserPageHeader();
		ElementsUtil.Assert(actualheader, "Users", "UsersPage "+msg);
		try {
			user.AddNewUser(fname, lname, email, uname);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		//validating added user
		user.UserValidation(uname);
		//Back To AccountSettingPage
		accountset = user.backToAccountSetting();


		//Validating header and adding tasks
		taskcat = accountset.TaskCategoriesLink();
		actualheader = taskcat.getTaskPageHeader();
		ElementsUtil.Assert(actualheader, "Task Categories", "TaskPage "+msg);
		taskcat.AddTasks(tskcatname);

		//Validating added Task
		taskcat.ValidationTaskCategory(tskcatname);
		//Back to AccountSetting
		accountset = taskcat.backToAccountSetting();

		//Validating Custom Field header
		actualheader = accountset.getCustomFieldsHeader();
		ElementsUtil.Assert(actualheader, "Custom Fields", "Custom FieldsPage "+msg);

		//Validating header and add new tags
		tags = accountset.TagsLink();
		actualheader = tags.getTagPageHeader();
		ElementsUtil.Assert(actualheader, "Tags and DataTags", "TagPage "+msg);
		tags.AddTag(tagname);

		//Validating added Tag
		tags.TagValidation(tagname);
		//Back to AccountSetting
		accountset = tags.backToAccountSetting();
	}
	
	

	@Test(priority=3)
	public void AccSetIntegrationTest() {

		//Going to Account Setting and validating header
		accountset = home.clickAccountSettingLink();

		//Validating header and getting the count of 'Configure'
		integration = accountset.IntegrationsLink();
		actualheader = integration.getIntegrationPageHeader();
		ElementsUtil.Assert(actualheader, "Integrations", "IntegrationPage "+msg);
		integration.CountOfConfigure();
		//Back to AccountSetting
		accountset = integration.backToAccountSetting();

		//Validating Trash page header
		actualheader = accountset.getTrashHeader();
		ElementsUtil.Assert(actualheader, "Trash", "TrashPage "+msg);

		//Invoice Header Validation
		actualheader = accountset.getInvoicesHeader();
		ElementsUtil.Assert(actualheader, "Invoices","InvoicePage "+ msg);
	}



	@Test(priority=4, dataProvider="testDataForOPnTR", dataProviderClass = DataProviderUtil.class)
	public void AccSetOpportunityTest(String msname, String msdesc, String probabmsdays, String saledays) {

		//Going to Account Setting and validating header
		accountset = home.clickAccountSettingLink();

		//Validating header and Adding new milestone 
		opportunity = accountset.OpportunitiesLink();
		actualheader = opportunity.getOpportunitiesPageHeader();
		ElementsUtil.Assert(actualheader, "Opportunities", "OpportunitiesPage "+msg);
		opportunity.AddMilestone(msname, msdesc, probabmsdays, saledays);

		//Validating added  milestone
		opportunity.MilestoneValidation(msname);
		//back to AccountSetting
		accountset = opportunity.backToAccountSetting();
	}



	@Test(priority=5, dataProvider="testDataForOPnTR", dataProviderClass = DataProviderUtil.class)
	public void AccSetTrackTest(String trkname, String trktag, String desctsk, String tskdays) {

		//Going to Account Setting and validating header
		accountset = home.clickAccountSettingLink();

		//Validating Tracks header and adding new tracks
		tracks = accountset.TracksLink();
		actualheader = tracks.getTracksPageHeader();
		ElementsUtil.Assert(actualheader, "Tracks", "TracksPage "+msg);
		tracks.AddTracks(trkname, trktag, desctsk, tskdays);

		//Validating added Tracks
		tracks.TrackValidation(trkname);
		//Back to AccountSetting
		accountset = tracks.backToAccountSetting();

	}


	@AfterMethod
	public void tearDown(ITestResult result) {


		TestUtil.testResult(result);

		driver.quit();

	}


}
