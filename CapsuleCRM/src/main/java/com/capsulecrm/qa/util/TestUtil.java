package com.capsulecrm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;



import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import org.testng.ITestResult;

import com.capsulecrm.qa.base.TestBase;

public class TestUtil {

	static WebDriver driver;

	public static String OS_NAME = System.getProperty("os.name");
	public static String CUR_DIR = System.getenv("user.dir");
	public TestUtil(WebDriver driver) {

		TestUtil.driver = driver;
	}

	public static String getDriverPath(String browserType) {

		String driverPath = "";
		if (OS_NAME.toUpperCase().contains("WINDOWS")){
			if(browserType.contains("chrome"))
				driverPath ="./src/main/resources/drivers/chromedriver.exe";
			else if (browserType.contains("ff"))
				driverPath ="./src/main/resources/drivers/geckodriver.exe";
			else if (browserType.contains("edge"))
				driverPath ="./src/main/resources/drivers/MicrosoftWebDriver.exe";
		}
		else if(OS_NAME.toUpperCase().contains("MAC")){
			if(browserType.contains("chrome"))
				driverPath ="./src/main/resources/drivers/chromedriver";
			else if (browserType.contains("ff"))
				driverPath ="./src/main/resources/drivers/geckodriver";
			else if (browserType.contains("edge"))
				driverPath ="./src/main/resources/drivers/MicrosoftWebDriver";
		}
		return driverPath;
	}




	//Reading data from Excel 
	public static Object[][] getData(String fileName,String sheetName) throws IOException{
		File file = new File("./src/main/resources/data/" + fileName);
		Object[][] obj = null;
		XSSFWorkbook wb;

		if(file.exists()==true){
			FileInputStream fis = new FileInputStream(file);
			wb = new XSSFWorkbook(fis);
			fis.close();
			XSSFSheet sheet;
			if(wb.getNumberOfSheets()!=0)
				for(int i=0; i<wb.getNumberOfSheets();i++)			
					if(wb.getSheetName(i).equals(sheetName)){
						sheet = wb.getSheet(sheetName);
						wb.close();
						XSSFRow row;
						XSSFCell cell;
						int lastrownum = sheet.getLastRowNum();
						int lastcolnum = sheet.getRow(1).getLastCellNum();
						obj = new Object[lastrownum][lastcolnum];

						if(sheet!=null)
							for (int j=0; j<=lastrownum; j++){
								row = sheet.getRow(j+1);
								for (int k=0; k<=lastcolnum; k++){
									if(row!=null){
										cell = row.getCell(k);
										if(cell!=null)
											obj[j][k] =cell.toString();

									}
								}

							}

					}
		}
		return obj;

	}

	public static void staticWait() {
		try {
			Thread.sleep(ElementsUtil.HARD_WAIT);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	public static String getScreenShot(WebDriver driver, String screenShotName ){

		String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts =(TakesScreenshot)driver; 
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = "./FailedScreenShots/" + screenShotName+ date +".png";
		File finalDestination = new File(destination);
		try{
			FileHandler.copy(source, finalDestination);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return destination;
	}

	public static void testResult(ITestResult result) {

		if(result.getStatus()==ITestResult.FAILURE){
			TestBase.TEST_LOG.error("?????Test case failed is "+ result.getName());
			
		}
		else if(result.getStatus()==ITestResult.SKIP){
			TestBase.TEST_LOG.info("?????Test case skipped is " + result.getName());
		}
		else if(result.getStatus()==ITestResult.SUCCESS){
			TestBase.TEST_LOG.info("******Test case is passed is  "+result.getName());
		}


	}


}
