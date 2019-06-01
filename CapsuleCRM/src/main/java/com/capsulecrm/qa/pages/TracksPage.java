package com.capsulecrm.qa.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.capsulecrm.qa.base.TestBase;
import com.capsulecrm.qa.util.ElementsUtil;


public class TracksPage extends TestBase {

	WebDriver driver;


	@FindBy(xpath ="//h2[contains(@class,'settings-page-header')]")
	private WebElement _h2LblElement;

	@FindBy(xpath = "//a[text()='Add new Track']")
	private WebElement _addNewTrackBtnElement;


	@FindBy(id = "j_id123:trackDescriptionDecorate:trackDescription")
	private WebElement _nameTrackTxtElement;

	@FindBy(id = "j_id123:trackTagDecorate:trackTag")
	private WebElement _tagTrackTxtElement;


	@FindBy(id = "j_id123:taskLines:0:taskDescriptionDecorate:taskDescription")
	private WebElement _descTaskTxtElement;

	@FindBy(id = "j_id123:taskLines:0:taskDaysAfterDecorate:taskDaysAfter")
	private WebElement _daysTaskTxtElement;

	@FindBy(xpath = "//a[text()='Save']")
	private WebElement _saveBtnElement;


	@FindBy(xpath = "//table[@id='taskgroups:searchresults']//tbody//tr//td[1]//a")
	private List<WebElement> _tracksList;

	//Constructor
	public TracksPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Getting header
	public String getTracksPageHeader() {
		return _h2LblElement.getText();
	}

	// Adding new Tracks
	public void AddTracks(String trkname, String trktag, String desctsk, String tskdays) {
		ElementsUtil.clickByExplicitWait(_addNewTrackBtnElement, driver);

		ElementsUtil.setByExplicitWait(_nameTrackTxtElement, driver, trkname);
		ElementsUtil.setByExplicitWait(_tagTrackTxtElement, driver, trktag);
		ElementsUtil.setByExplicitWait(_descTaskTxtElement, driver, desctsk);
		ElementsUtil.setByExplicitWait(_daysTaskTxtElement, driver, tskdays);

		ElementsUtil.clickByExplicitWait(_saveBtnElement, driver);
	}

	//Validating added track
	public void TrackValidation(String trkname) {

		ElementsUtil.validateItemInList(_tracksList, trkname);
	}

	public AccountSettingPage backToAccountSetting() {
		return new AccountSettingPage(driver);
	}
}

