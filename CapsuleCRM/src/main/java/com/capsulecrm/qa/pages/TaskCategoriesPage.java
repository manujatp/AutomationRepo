package com.capsulecrm.qa.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.capsulecrm.qa.base.TestBase;
import com.capsulecrm.qa.util.ElementsUtil;


public class TaskCategoriesPage extends TestBase {

	WebDriver driver;


	@FindBy(xpath ="//h2[contains(@class,'settings-page-header')]")
	private WebElement _h2LblElement;

	@FindBy(xpath = "//a[text()='Add new Category']")
	private WebElement _addNewCategoryBtnElement;


	@FindBy(id = "editCategoryForm:taskCategoryNameDecorate:taskCategoryName")
	private WebElement _taskCatNameTxtElement;

	@FindBy(xpath = "//input[contains(@id,'editCategoryForm:')]")
	private WebElement _saveBtnElement;


	@FindBy(xpath = "//table[contains(@id, ':stages')]//tbody//tr//td[2]//a")
	private List<WebElement> _tasksList;

	@FindBy(xpath = "//table[contains(@id, ':stages')]//tbody//tr//td[3]//a")
	private List<WebElement> _deleteTasksList;


	@FindBy(id = "//form[contains(@id,'j_id')]//input[contains(@class, 'btn-danger')]")
	private WebElement _deleteBtnElement;

	//Constructor
	public TaskCategoriesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Getting header
	public String getTaskPageHeader() {
		return _h2LblElement.getText();
	}

	//Adding new task
	public void AddTasks(String tskcatname) {
		ElementsUtil.clickByExplicitWait(_addNewCategoryBtnElement, driver);
		ElementsUtil.setByExplicitWait(_taskCatNameTxtElement, driver, tskcatname);
		ElementsUtil.clickByExplicitWait(_saveBtnElement, driver);

	}

	//Validating added task
	public void ValidationTaskCategory(String tskcatname) {

		ElementsUtil.validateItemInList(_tasksList, tskcatname);

		//		ElementsUtil.deleteTracksFromList(_tasksList, _deleteTasksList, _deleteBtnElement, tskcatname);
	}

	public AccountSettingPage backToAccountSetting() {
		return new AccountSettingPage(driver);
	}
}
