package com.capsulecrm.qa.util;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class DataProviderUtil {
	//From Excel
	@DataProvider(name="testdata")
	public Object[][] getDataFromAccSetExcel(Method m) throws IOException {
		if(m.getName().contains("AccountSettingValidTest"))
			return TestUtil.getData("TestData.xlsx", "AccSet");
		else
			return TestUtil.getData("TestData.xlsx","PersonnCase");
	}

	//From DataProvider
	@DataProvider(name="testDataForOPnTR")
	public Object[][] getDataFromDataProvider(Method m) {
		if(m.getName().contains("AccSetOpportunityTest"))
			return new Object[][] {
			{"Design", "Details", "50", "2"}
		};
		else 
			return new Object[][] {
			{"Queue", "FIFO", "FirstInFirstOut", "3"}
		};

	}

}
