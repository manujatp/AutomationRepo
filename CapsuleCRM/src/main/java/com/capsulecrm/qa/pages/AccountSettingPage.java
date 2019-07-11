package com.capsulecrm.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.capsulecrm.qa.base.TestBase;
import com.capsulecrm.qa.util.ElementsUtil;


public class AccountSettingPage extends TestBase {
	WebDriver driver;


	@FindBy(xpath ="//span[contains(@class,'settings-content-menu-title')]")
	private WebElement _accountSetHeadLblElement;

	//Account Export Appearance header
	@FindBy(xpath ="//h1[contains(@class,'settings-page-header')]")
	private WebElement _h1LblElement;

	//Mail drop Users Tracks Task cat Custom Tags Integration Trash header
	@FindBy(xpath ="//h2[contains(@class,'settings-page-header')]")
	private WebElement _h2LblElement;

	//invoice Opportunities header
	@FindBy(xpath ="//header[contains(@class,'page-box-header')]")
	private WebElement _headerLblElement;

	// Constructor
	public AccountSettingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Getting header of Account Setting Page
	public String getAccountSettingHeader() {

		return _accountSetHeadLblElement.getText();
	}

	public void clickAccountLink() {
		ElementsUtil.clickElementByJs(ElementsUtil.ElementByDynamicXpath(driver, "Account"), driver);
	}

	// Getting header of Account Page
	public String getAccountHeader() {

		ElementsUtil.clickElementByJs(ElementsUtil.ElementByDynamicXpath(driver, "Account"), driver);
		return _h1LblElement.getText();
	}

	// Getting header of Invoice Page
	public String getInvoicesHeader() {

		ElementsUtil.clickByExplicitWait(ElementsUtil.ElementByDynamicXpath(driver, "Invoices"), driver);
		return _headerLblElement.getText();
	}

	// Getting header of Export Page
	public String getExportHeader() {

		ElementsUtil.clickByExplicitWait(ElementsUtil.ElementByDynamicXpath(driver, "Export"), driver);
		return _h1LblElement.getText();
	}


	public AppearancePage AppearanceLink() {

		ElementsUtil.clickByExplicitWait(ElementsUtil.ElementByDynamicXpath(driver, "Appearance"), driver);
		return new AppearancePage(driver);

	}

	// Getting header of Mail Drop Box Page
	public String getMailDropBoxHeader() {

		ElementsUtil.clickByExplicitWait(ElementsUtil.ElementByDynamicXpath(driver, "Mail Drop Box"), driver);
		return _h2LblElement.getText();
	}

	public UsersPage UsersLink() {

		ElementsUtil.clickByExplicitWait(ElementsUtil.ElementByDynamicXpath(driver, "Users"), driver);
		return new UsersPage(driver);
	}


	public OpportunitiesPage OpportunitiesLink() {

		ElementsUtil.clickByExplicitWait(ElementsUtil.ElementByDynamicXpath(driver, "Opportunities"), driver);
		return new OpportunitiesPage(driver);
	}


	public TracksPage TracksLink() {

		ElementsUtil.clickByExplicitWait(ElementsUtil.ElementByDynamicXpath(driver, "Tracks"), driver);
		return new TracksPage(driver);
	}

	public TaskCategoriesPage TaskCategoriesLink() {

		ElementsUtil.clickByExplicitWait(ElementsUtil.ElementByDynamicXpath(driver, "Task Categories"), driver);
		return new TaskCategoriesPage(driver);
	}

	public String getCustomFieldsHeader() {

		ElementsUtil.clickByExplicitWait(ElementsUtil.ElementByDynamicXpath(driver, "Custom Fields"), driver);
		return _h2LblElement.getText();
	}

	public TagsPage TagsLink() {

		ElementsUtil.clickByExplicitWait(ElementsUtil.ElementByDynamicXpath(driver, "Tags"), driver);
		return new TagsPage(driver);
	}

	public IntegrationsPage IntegrationsLink() {

		ElementsUtil.clickByExplicitWait(ElementsUtil.ElementByDynamicXpath(driver, "Integrations"), driver);
		return new IntegrationsPage(driver);
	}

	// Getting header of Trash Page
	public String getTrashHeader() {

		ElementsUtil.clickByExplicitWait(ElementsUtil.ElementByDynamicXpath(driver, "Trash"), driver);
		return ElementsUtil.getHeader(_h2LblElement);
	}
}
