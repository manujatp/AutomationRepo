package com.capsulecrm.qa.tests;


import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.capsulecrm.qa.base.TestBase;
import com.capsulecrm.qa.pages.CasesPage;
import com.capsulecrm.qa.pages.HomePage;
import com.capsulecrm.qa.pages.LoginPage;
import com.capsulecrm.qa.pages.PeoplenOrgPage;
import com.capsulecrm.qa.util.DataProviderUtil;
import com.capsulecrm.qa.util.ElementsUtil;
import com.capsulecrm.qa.util.TestUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


public class LoginCapsuleCRMTest extends TestBase {

	LoginPage login;
	HomePage home;
	PeoplenOrgPage person;
	CasesPage cases;

	public LoginCapsuleCRMTest() {
		super();
	}

	@BeforeMethod
	public void setup() {


		initialization();
		login = new LoginPage(driver);
		home = new HomePage(driver);
		person = new PeoplenOrgPage(driver);
		cases = new CasesPage(driver);
	}

	@Test(priority=1,dataProvider="testdata", dataProviderClass = DataProviderUtil.class,description = "Verifying login and adding persons and cases")
//	@Severity(SeverityLevel.BLOCKER)
//	@Description("Test Case Description: Login and adding persons and cases ")
//	@Story("Story Name : Login")

	public void LoginTest(String title,String firstname,String lastname,String jobtitle,String org,String tagp,String phno,String email, String personnamec , String casename, String description, String tagc) {

		//Validating LoginPage Title
		String actualtilte = login.getTitle();
		String exptitle = "ManujaNikiitha CRM";
		String msg = "Actual Tilte is not same as expected";
	//	ElementsUtil.Assert(actualtilte, exptitle, msg);

		//Login into CapsuleCRM
		home = login.Login(prop.getProperty("username"), prop.getProperty("password"));


		//Validating HomePage Title	
		actualtilte = login.getTitle();
		exptitle = "Dashboard | ManujaNikiitha CRM";
//		ElementsUtil.Assert(actualtilte, exptitle, msg);

		//Adding persons
		person = home.clickPeopleLink();
		person.AddPerson(title, firstname, lastname, jobtitle, org, tagp, phno, email);
		person = home.clickPeopleLink();
		//Validating the person added
		String personnamep = firstname+" "+ lastname;
		person.validatePersonName(personnamep);

		//Back to homepage
		home = person.navigateToHome();

		//Adding Case
		cases = home.clickCasesLink();
		cases.AddCase(personnamec, casename, description, tagc);
		home = cases.navigateToHome();
		cases = home.clickCasesLink();
		//validating added case for the person and its status
		cases.ValidateCase(personnamep, casename);
	}




	@AfterMethod
	public void tearDown(ITestResult result) {


		TestUtil.testResult(result);
		driver.quit();

	}

}
