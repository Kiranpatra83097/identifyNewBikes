package com.identifyNewBikes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


import io.github.bonigarcia.wdm.WebDriverManager;

public class baseUI {
	
	//Initializing web elements using page factory
	public static ExtentReports report = ExtentReportManager.getReportInstance();
	public static ExtentTest logger;
	public static SoftAssert softassert = new SoftAssert();
	public static WebDriver driver;
	
	@FindBy(xpath = "//header/div[1]/div[2]/div[1]/nav[1]/div[1]/ul[1]/li[6]/a[1]")
	public static WebElement usedCarsLink;
	@FindBy(xpath = "//span[contains(text(),'Find Used Cars')]")
	public static WebElement usedCarsButton;
	@FindBy(xpath = "//*[@id=\"popularCityList\"]/li[7]//*[contains(text(),'Chennai ')]")
	public static WebElement location;
	@FindBy(xpath = "//body/div[11]/div[1]/div[1]/div[1]/div[1]/div[2]/ul[1]/li[2]/div[2]/div[5]/ul[1]")
	public static List<WebElement> popularbrandslist;
	@FindBy(xpath = "//a[contains(text(),'New Bikes')]")
	public static WebElement newbikesLink;
	@FindBy(xpath = "//span[contains(text(),'Upcoming Bikes')]")
	public static WebElement upcomingbikesLink;
	@FindBy(xpath = "//*[@id='makeId']")
	public static WebElement manufacturerListbox;
	@FindBy(xpath = "//span[contains(text(),'View More Bikes')]")
	public static WebElement viewmoreButton;
	@FindBy(xpath = "/html[1]/body[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[2]/ul[1]")
	public static WebElement bikesList;
	@FindBy(xpath = "//*[@id=\"des_lIcon\"]")
	public static WebElement login;
	@FindBy(xpath ="//*[text()=('Continue with Google')]")
	//@FindBy(xpath = "//*[@id=\"googleSignIn\"]")
	public static WebElement continueWithGoogleButton;
	@FindBy(xpath = "//*[@id=\"identifierId\"]")
	public static WebElement emailTextbox;
	@FindBy(xpath = "//*[@id=\"identifierNext\"]/div/button/span")
	public static WebElement nextButton;
	@FindBy(xpath = "//div[@class ='o6cuMc']")
	public static WebElement alertMessage;
	
	/***********************************************************************************
     * This method is used for setting up driver
     ***********************************************************************************/

	public static WebDriver browserSetUp() {
		System.out.println(
				"\n-------------------------------------------------------------------------------------------------\n");
		System.out.println("\n1)To choose chrome brower for execution ,type-'chrome'");
		System.out.println("\n2)To choose Mozila FireFox brower for execution ,type-'firefox'");
		System.out.println("\n3)To choose edge brower for execution ,type-'edge'");
		System.out.println("\n4)To exit the execution ,type-'exit'");
		System.out.println(
				"\n-------------------------------------------------------------------------------------------------");
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		String browser = scan.next();
		System.out.println("-------------------------------------------------------------------------------------------------\n");
		driver = baseUI.initializeBrowser(browser);
		System.out.println("Browser Initialized");
		driver.get(baseUI.getProperties("url"));
		softassert.assertEquals(driver.getTitle(),
				"New Cars & Bikes, Prices, News, Reviews, Buy & Sell Used Cars - ZigWheels.com");
		driver.manage().window().maximize();
		baseUI.closeAd(driver);
		//closeLogin.click();
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(120, TimeUnit.SECONDS);
		softassert.assertAll();
		return driver;
	}
	
	 /***********************************************************************************
     * This method is used for initializing browser
     ***********************************************************************************/

	public static WebDriver initializeBrowser(String browser)// initializing browser
	{

		if (browser.equalsIgnoreCase("chrome")) {
			//System.setProperty("webdriver.chrome.driver", "Drivers\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);
			
		}
		else if (browser.equalsIgnoreCase("firefox")) {
			//System.setProperty("webdriver.gecko.driver", "Drivers\\geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
		else if (browser.equalsIgnoreCase("edge")) {
			//System.setProperty("webdriver.edge.driver", "Drivers\\msedgedriver.exe");
			WebDriverManager.edgedriver().setup();
			driver= new EdgeDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
		else
		{
			System.out.println("No browser value is given");
		}

		return driver;
	}
	
	/**********************************************************************************************
	 *This method is used to get the value from properties file
	 *********************************************************************************************/

	public static String getProperties(String key) {
		Properties props = new Properties();
		FileInputStream input;
		try {
			input = new FileInputStream("src\\test\\resources\\Properties\\config.properties");
			// load from input stream
			props.load(input);
		} catch (Exception e) {

			e.printStackTrace();
		}

		String locator = props.getProperty(key);
		return locator;

	}
	
	/**********************************************************************************************
	 *This method is used to close the ad if appears
	 *********************************************************************************************/

	public static void closeAd(WebDriver driver) {
		boolean eleSelected = existsElement(getProperties("ad_Property"));
		if (eleSelected == true) {
			driver.findElement(By.xpath(getProperties("ad_Property"))).click();
		}
	}

	public static boolean existsElement(String xpath) {

		try {
			driver.findElement(By.xpath(xpath));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**********************************************************************************************
	 * Capturing ScreenShots
	 *********************************************************************************************/
	
	public static void takeScreenShots(WebDriver driver) {
		try {
			//Convert WebDriver object to TakesScreenshot
			TakesScreenshot scrShot = ((TakesScreenshot) driver);
			//call getScreenshot as method to create image file
			File source = scrShot.getScreenshotAs(OutputType.FILE);
			//Move image file to new destination
			File dest = new File("src\\test\\resources\\Screenshots\\" + DateUtil.getTimeStamp() + ".png");
			//Copy file at destination
			FileUtils.copyFile(source, dest);
			System.out.println("Screenshot is taken");

		} catch (Exception e) {

			System.out.println(e);
		}
	}
	
	/**
	 * Reporting Functions
	 */

	// Taking screenshots if the test got failed
	public static void reportFail(String reportString) {
		logger.log(Status.FAIL, reportString);
		takeScreenShots(driver);
		try {
			logger.addScreenCaptureFromPath("src\\test\\resources\\Screenshots\\" + DateUtil.getTimeStamp() + ".png");
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

	public static void reportPass(String reportString) {
		logger.log(Status.PASS, reportString);
	}

	 /*************************************************************************************
     * This method is used to close the 'driver
     *************************************************************************************/
    
	public static void closeDriver(WebDriver driver) {
		driver.close();
		report.flush();
	}

	 /*************************************************************************************
     * This method is used to quit the 'Browser'
     *************************************************************************************/
    
	public static void quitDriver(WebDriver driver) {
		driver.quit();
	}

}
