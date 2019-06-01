package com.capsulecrm.qa.util;

import java.io.IOException;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.capsulecrm.qa.base.TestBase;

public class ElementsUtil {

	static WebDriver driver;
	public static int PAGE_LOAD_TIME = 20;
	public static int EXPLICIT_WAIT = 20;
	public static int IMPLICIT_WAIT = 20;
	public static int HARD_WAIT = 20;

	//Element by Dynamic xpath
	public static WebElement ElementByDynamicXpath(WebDriver driver, String linkText) {
		int i;
		List<WebElement> _linkListElement = driver.findElements(By.xpath("//ul[@class = 'nav-panel']//li//a"));
		for(i=0; i<_linkListElement.size(); ++i)
			if(_linkListElement.get(i).getText().contains(linkText)) 
				break;
		return _linkListElement.get(i);

	}
	//Getting header
	public static String getHeader(WebElement el) {
		return el.getText();
	}

	//click() with explicit wait
	public static void clickByExplicitWait(WebElement el, WebDriver driver){
		wait(driver,el);
		el.click();
	}


	//Set() with explicit wait
	public static void setByExplicitWait(WebElement el,WebDriver driver,String str){
		wait(driver,el);
		el.click();
		el.clear();
		el.sendKeys(str);
		el.sendKeys(Keys.TAB);
	}

	//Set() for logo with explicit wait
	public static void setLogoByExplicitWait(WebElement el,WebDriver driver,String str){
		wait(driver,el);
		el.sendKeys(str);

	}

	//set for logo by exe
	public static void setLogoByExe(WebElement el,WebDriver driver,String fileName){
		wait(driver,el);
		el.click();
		try {
			Runtime.getRuntime().exec(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//click by Action
	public static void performClick( WebElement el, WebDriver driver){
		wait(driver,el);
		Actions actions = new Actions(driver);
		actions.moveToElement(el).click().perform();

	}
	//wait()
	private static void wait(WebDriver driver, WebElement el) {
		WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT);
		wait.until(ExpectedConditions.elementToBeClickable(el));

	}


	//click function by JavascriptExecutor
	public static void clickElementByJs(WebElement el,WebDriver driver){

		JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", el);

	}
	//HardWait()
	public static void hardWait() throws InterruptedException {
		Thread.sleep(HARD_WAIT);
	}
	//Assert()
	public static void Assert(String actualvalue, String expvalue,String msg) {

		Assert.assertEquals(actualvalue, expvalue,msg);

	}

	//Validating Item from list
	public static void validateItemInList(List<WebElement> list, String itemname) {
		boolean flag = false;

		for(int i=0; i<list.size(); ++i) {
			if (list.get(i).getText().contains(itemname)) {
				flag = true;
				break;
			}
		}

		if(flag) {
			TestBase.TEST_LOG.info("********"+itemname +" is successfully added"+"*********");
		}
		else {

			TestBase.TEST_LOG.error("????????"+itemname+" is not added"+"????????????");
		}


	}

	//Deleting added Milestone
	public static void deleteMileStoneFromList(List<WebElement> mileStonelist, List<WebElement> deleteMileStonelist,WebElement deleteBtnElement,String itemname) {


		for(int i=0; i<mileStonelist.size(); ++i) {
			if (mileStonelist.get(i).getText().contains(itemname)) {
				deleteMileStonelist.get(i).click();
				deleteBtnElement.click();
				System.out.println("\n\nDeleting the added "+itemname+ " milestone for multiple execution with same milestone ");
				break;
			}
		}
	}
	//Deleting added tracks
	public static void deleteTracksFromList(List<WebElement> tracklist, List<WebElement> deleteTrackslist,WebElement deleteBtnElement,String tskcatname) {

		for(int i=0; i<tracklist.size(); ++i) {
			if (tracklist.get(i).getText().contains(tskcatname)) {
				deleteTrackslist.get(i).click();
				deleteBtnElement.click();
				System.out.println("\n\nDeleting the added "+ tskcatname+" for multiple execution with same task ");
				break;
			}
		}
	}
}

