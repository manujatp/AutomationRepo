package com.capsulecrm.qa.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.capsulecrm.qa.base.TestBase;

public class IntegrationsPage extends TestBase {

	WebDriver driver;

	@FindBy(xpath ="//h2[contains(@class,'settings-page-header')]")
	private WebElement _h2LblElement;


	@FindBy(xpath = "//table[@id ='j_id124:searchresults']//tbody//tr//td//a[text()='Configure']")
	private List<WebElement> _configEvnList;

	// Constructor
	public IntegrationsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Getting header
	public String getIntegrationPageHeader() {
		return _h2LblElement.getText();
	}

	//Count ofConfigure
	public void CountOfConfigure() {

		System.out.println("Total number of \"Configure\" is :"+_configEvnList.size());
	}

	public AccountSettingPage backToAccountSetting() {
		return new AccountSettingPage(driver);
	}
}
