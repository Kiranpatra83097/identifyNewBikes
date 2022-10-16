package com.identifyNewBikes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.Status;

public class popularUsedCars extends baseUI {
	public static void popularUsedCarsModel(WebDriver driver) throws Exception {
		try {
			closeAd(driver);// closing ad if occurs
			logger = report.createTest("Used cars in chennai");
			findUsedCarsLinkText(driver);
			String pageurl = driver.getCurrentUrl();
			softassert.assertTrue(pageurl.contains("Upcoming Honda Bikes in India 2022/23, See Price, Launch Date, Specs @ ZigWheels"));// asserting the title of the page
			logger.log(Status.INFO, "Used cars is selected");
			SelectLocation(driver);
			logger.log(Status.INFO, "Chennai location is selected");
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			List<String> models = printModels(driver);// writing values to properties file
			writingProperties.writingfile(models, "Used-Cars");
			softassert.assertAll();
			reportPass("Popular used cars models are written in properties file");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	/*****************************************************************************
     * Following method is used to click on usedCars link text
     *****************************************************************************/

	public static void findUsedCarsLinkText(WebDriver driver) {
		usedCarsLink.click();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	}
	
	
	/*****************************************************************************
     * Following method is used to select the location
     *****************************************************************************/

	
	public static void SelectLocation(WebDriver driver) {
		
		location.click();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	}
	
	/*******************************************************************************
	 * Following method code is to extract all the 'Popular Car Models' in the Chennai 
	 *******************************************************************************/
	
	// printing in console and writing values to properties file
	public static List<String> printModels(WebDriver driver) throws Exception {
		System.out.println("*****************************************************************************");
		System.out.println("Popular used cars model is displayed");
		System.out.println("*****************************************************************************");
		List<WebElement> models_list = popularbrandslist;
		boolean usedcars = !(models_list.isEmpty());
		softassert.assertTrue(usedcars);
		List<String> models = new ArrayList<String>();
		for (WebElement carname : models_list) {
			models.add(carname.getText());
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		}
		System.out.println("Popular Models of Used Cars in Chennai are:-");
		for (int i = 0; i < models.size(); i++) {
			System.out.println((i + 1) + " " + models.get(i));
			
		}
		return models;
	}

}
