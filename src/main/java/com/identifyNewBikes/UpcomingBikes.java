package com.identifyNewBikes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UpcomingBikes extends baseUI {
	public static void findingUpcomingBikes(WebDriver driver) throws Exception {// Invoking the below methods
		try {
			driver.navigate().refresh();
			logger = report.createTest("Upcoming Honda Bikes");
			clickOnUpcomingBikes(driver);
			softassert.assertEquals(driver.getTitle(),
					"Upcoming Bikes in India 2022/23, See Price, Launch Date, Specs @ ZigWheels");// asserting the page
																									// title
			logger.log(Status.INFO, "Upcoming Bikes is clicked");
			selectHondaModels(driver);
			softassert.assertEquals(driver.getTitle(),
					"Upcoming Honda Bikes in India 2022/23, See Price, Launch Date, Specs @ ZigWheels");// asserting the
																										// page title
			logger.log(Status.INFO, "Honda models is selected");
			ArrayList<String> upcomingBikes = getHondaBikesUnder4Lacs(driver);
			WritingExcel.writeExcel(upcomingBikes);
			softassert.assertAll();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			reportPass("Values are written in excel file");
		} catch (Exception e) {
			System.out.println(e);
			reportFail(e.getMessage());
		}
	}

	/*************************************************************************************
	 * This method is used to click on the 'Upcoming Bikes' link text
	 *************************************************************************************/
	
	public static void clickOnUpcomingBikes(WebDriver driver) throws Exception {
		
		//Create object 'action' of an Actions class
		Actions action = new Actions(driver);
		
		//Mouseover on an element
		action.moveToElement(newbikesLink).build().perform();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(upcomingbikesLink));
		upcomingbikesLink.click();
	}

	/*************************************************************************
	 * Here we are selecting model name from dropdown and clicking on viewmore button
	 *************************************************************************/
	
	public static void selectHondaModels(WebDriver driver) {

		// Navigating to the Upcoming Honda Bikes page
		Select manufacturerSelect = new Select(manufacturerListbox);
		manufacturerSelect.selectByVisibleText(baseUI.getProperties("manufacturer"));
		// Clicking View More bikes
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("window.scrollBy(0,1600)", "");
		WebElement viewmore = viewmoreButton;
		executor.executeScript("arguments[0].click();", viewmore);
		System.out.println("*****************************************************************************");
		System.out.println("Upcoming Honda models are displayed");
		System.out.println("*****************************************************************************");
	}

	
	/*************************************************************************
	 * Here we are  sorting the price of bikes which are below 4 lakhs
	 *************************************************************************/
	
	public static ArrayList<String> getHondaBikesUnder4Lacs(WebDriver driver) throws Exception {
		// Storing the information of all the Upcoming Honda Bikes
		String bikeModels = bikesList.getText();

		// Storing the info in an ArrayList
		ArrayList<String> bikeModelsElements = new ArrayList<String>();
		Collections.addAll(bikeModelsElements, bikeModels.split("\n"));

		// Sorting the information according to names,dates and prices
		ArrayList<String> NameList = new ArrayList<String>();
		ArrayList<String> DateList = new ArrayList<String>();
		ArrayList<String> PriceList = new ArrayList<String>();
		String[] arr = null;
		for (int i = 0; i < bikeModelsElements.size(); i++) {
			String s = bikeModelsElements.get(i);
			if (s.contains("Honda")) {
				NameList.add(s);
			}
			if (s.contains("Rs. ")) {
				arr = s.split(" ");
				PriceList.add(arr[1]);
			}
			if (s.contains("Exp. Launch")) {
				DateList.add(s);
			}
		}
		// Creating an Arraylist which will add only the upcoming bikes under 4 Lakhs
		ArrayList<String> upcomingBikes = new ArrayList<String>();
		for (int i = 0; i < NameList.size(); i++) {
			String temp = NameList.get(i);
			double price = Double.parseDouble(PriceList.get(i));
			String info = temp + "  " + PriceList.get(i) + " Lakh  " + DateList.get(i);
			if (info.contains(temp)) {
				if (Double.compare(price, 4d) < 0) {
					upcomingBikes.add(info);
				}
			}
		}
		// Reading the input from properties file
		String upcomingBikesManufacturer = baseUI.getProperties("manufacturer");
		// Printing them
		System.out.println("Upcoming " + upcomingBikesManufacturer + " Bikes Below 4 Lakhs are as follows:");
		for (int i = 0; i < upcomingBikes.size(); i++) {
			System.out.println(upcomingBikes.get(i));
		}
		
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("window.scrollBy(0,-1600)", "");

		return upcomingBikes;

	}
}
