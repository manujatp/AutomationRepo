# AutomationRepo
###########Selenium Coding Challenge 3 :CapsuleCRM.com############

*******Project Covergae********
First Scenario:
--------------
In TestBase constructor reading from config.properties file 
@BeforeMethod
Launching browser based on the OS version and navigate to url.

First Test Case in LoginTest in class LoginCapsuleCRMTest
1. Validating title of loginPage using hard assertion.
2. Login to CapsuleCRM (username and password are reading from config.properties file) and validaitng title using hard assertion.
3. Adding Person and validating it using soft validation.
4. Adding case for the same person and validating it using soft validation.
     ***While adding case for same person, person name is not auto completing, giving error.
        Note: It was possible when I run it days before.
@AfterMethod 
Closing the browesr

Second Scenario:
@BeforeMethod
Navigate to url and login to CapsuleCRM
 
Second Test AccountSettingValidTest in class AccountSettingTest
---------------------------------------------------------------
1. Going to Account Settings and validating header using hard assertion.
2. Clicking on Account,Export,Mail Drop Box, Custom Field and validating header using hard assertion.

   All the values are from Excel for this case.
3. Clicking on Appearance and validating header using hard assertion and adding new logo using sendKeys
   (also added the code with autoit tool based on OS) and validating it using soft validation
4. Clicking on User and doing header validation using hard assertion and adding new user (Only 2 users can add 
    so checking it before adding) and validating it using soft validation.
5. Clicking on Task Categories and doing  header validation and adding new task and validating it.
    ***Duplication of Task Category(in AccountSetting-TaskCategories link) values are not allowed,
       so please change the value of TS:TaskCategry in the Excel(src/main/resources/data/TestData.xlsx)
6. Clicking on Tags and doing header validation and adding new Tag and validating it.

Third Test AccSetIntegrationTest in class AccountSettingTest
------------------------------------------------------------

1. Clicking on Integration and header validation using hard assertion and taking the count of Configure button.
2. Clicking on Invoices and Trash and validating its header using hard assertion.

Fouth Test AccSetOpportunityTestin class AccountSettingTest
------------------------------------------------------------

1. Clicking on Opprotunities link and doing header validation using hard assertion and 
   adding new milestone and validating it(Data from DataProvider : having numeric value from excel its taking with '.0')
     ***Duplication of Milestone(in AccountSetting-Opportunities link) values are not allowed, so please change the value
        of milestone in the dataProvider name : testDataForOPnTR (method name AccSetOpportunityTest
           (in src/main/java/com/capsulecrm/qa/util/DataProviderUtil.java)

Fifth Test AccSetTrackTestclass AccountSettingTest
--------------------------------------------------
1. Clicking on Track link and validating its header using hard assertion. Adding new track and validating it.
    (Data from DataProvider : having numeric value from excel its taking with '.0')

@AfterMethod
Closing the browser.


Project Structure:
Project folder
pom.xml
testng.xml
Source Folder
src/main/java:
Package 
com/capsulecrm/qa
/base   : TestBase class
/pages  : LoginPage
        : HomePage
        : PeoplenOrgPage
	: CasesPage
	: AccountSettingPage
	: AppearancePage
	: UsersPAge
	: OpportunitiesPage
	: TracksPage
	: TaskCategoriesPage
	: TagsPage
	: IntegrationPage
	
/listenrs : WebEventListener class
/utils  : TestUtils
	: ElementsUtil
	: DataProviderUtil
/config :config.properties

Source Folder
Package
src/test/java
com/capsulecrm/qa
/tests  : LoginCapsuleCRMTest
	: AccountSettingTest
src/main/resources
/drivers : browser exes
/data    : Excel file
         : FileUploader.exes for logo uploding for both Windows and Mac Os
