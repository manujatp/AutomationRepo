package com.capsulecrm.qa.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.capsulecrm.qa.base.TestBase;
import com.capsulecrm.qa.util.ElementsUtil;


public class OpportunitiesPage extends TestBase {

	WebDriver driver;


	@FindBy(xpath ="//header[contains(@class,'page-box-header')]")
	private WebElement _headerLblElement;

	@FindBy(xpath ="//button[text()='Add new Milestone']")
	private WebElement _addNewMilestoneBtnElement;



	@FindBy(xpath = "//input[contains(@class,'form-input-text milestone-modal-name')]")
	private WebElement _nameMstoneTxtElement;

	@FindBy(xpath = "//textarea[contains(@class,'form-input-text milestone-modal-description')]")
	private WebElement _descMstoneTxtElement;

	@FindBy(xpath = "//input[contains(@class,'form-input-text milestone-modal-probability')]")
	private WebElement _probabMstoneTxtElement;


	@FindBy(xpath = "//input[contains(@class,'form-input-text milestone-modal-days-until-stale')]")
	private WebElement _saleDayMstoneTxtElement;

	@FindBy(xpath = "//button[text()='Save']")
	private WebElement _saveBtnElement;


	@FindBy(xpath = "//table[@class='record-list']//tbody//tr//td[1]//button")
	private List<WebElement> _mileStoneList;

	@FindBy(xpath = "//table[@class='record-list']//tbody//tr//td[4]//button")
	private List<WebElement> _deleteMileStoneList;

	@FindBy(xpath = "//button[contains(@class, 'async-button ember-view btn-danger')]")
	private WebElement _deleteBtnElement;

	// Constructor
	public OpportunitiesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Getting the header
	public String getOpportunitiesPageHeader() {
		return _headerLblElement.getText();
	}

	// Adding new MikleStone
	public void AddMilestone(String msname, String msdesc, String probabmsdays, String saledays) {

		ElementsUtil.clickByExplicitWait(_addNewMilestoneBtnElement, driver);

		ElementsUtil.setByExplicitWait(_nameMstoneTxtElement, driver, msname);
		ElementsUtil.setByExplicitWait(_descMstoneTxtElement, driver, msdesc);
		ElementsUtil.setByExplicitWait(_probabMstoneTxtElement, driver, probabmsdays);
		ElementsUtil.setByExplicitWait(_saleDayMstoneTxtElement, driver, saledays);
		ElementsUtil.clickByExplicitWait(_saveBtnElement, driver);
	}

	//Validating added Milestone
	public void MilestoneValidation(String msname) {
		ElementsUtil.validateItemInList(_mileStoneList, msname);
		//		ElementsUtil.deleteMileStoneFromList(_mileStoneList, _deleteMileStoneList, _deleteBtnElement, msname);
	}

	public AccountSettingPage backToAccountSetting() {
		return new AccountSettingPage(driver);
	}


}
