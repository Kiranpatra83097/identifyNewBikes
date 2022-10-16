package com.identifyNewBikes;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class Testbase extends baseUI {
	public static ExtentReports report = ExtentReportManager.getReportInstance();// creating report instance
	public static ExtentTest logger;

	/*************************************************************************************
     * This method is used to call the 'baseUI' class and browserSetUp method
     *************************************************************************************/
	
	@BeforeClass(groups = { "smoke" , "regression" })
	// initializing browser and page factory elements
	public static void init() {
		baseUI.browserSetUp();
		baseUI base = new baseUI();
		PageFactory.initElements(driver, base);

	}

	/*************************************************************************************
     * This method is used to call the 'UpcomingBikes' class and findingUpcomingBikes method
     *************************************************************************************/
	
	@Test(priority = 1, groups = "smoke")
	// UpcomingBikes test method
	public static void upcomingBikesTest() throws Exception {
		UpcomingBikes.findingUpcomingBikes(driver);
	}
	
	/*************************************************************************************
     * This method is used to call the 'popularUsedCars' class and popularUsedCarsModel method
     *************************************************************************************/
	
	@Test(priority = 2, groups = { "regression" })
	// popularUsedCarsTestMethod
	public static void popularUsedCarsTest() throws Exception {
		//driver.navigate().to("https://www.zigwheels.com");
		popularUsedCars.popularUsedCarsModel(driver);

	}
	
	/*************************************************************************************
     * This method is used to call the 'ZigWheelsSignIn' class and googleSignIn method
     * while giving invalid email we are getting error so we are putting this method under smoke and regression  
     *************************************************************************************/

	@Test(priority = 3, groups = { "smoke", "regression" })
	// SignIn test method
	public static void ZigwheelsSignInTest() throws Exception {
		driver.navigate().to("https://www.zigwheels.com");
		ZigWheelsSignIn.googleSignIn(driver);
	}

	/*************************************************************************************
     * This method is used to close the 'Browser' and quit the 'Browser'
     *************************************************************************************/
	@AfterClass(groups ={ "smoke", "regression" })
	// closing and quitting driver
	public static void closeDriver() {
		baseUI.closeDriver(driver);
		baseUI.quitDriver(driver);

	}

}
