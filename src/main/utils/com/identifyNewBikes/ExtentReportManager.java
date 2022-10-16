package com.identifyNewBikes;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;

//Extent Report
public class ExtentReportManager {

			public static ExtentHtmlReporter htmlReporter;
			public static ExtentReports report;
			
			/*********************************************************
			 * This class is used to generate HTML Reports
			 **********************************************************/
			
			//Method for Extent Report
			public static ExtentReports getReportInstance(){
				
				if(report == null){
					String reportName = DateUtil.getTimeStamp() + ".html";
					 
		            /*************************************************************************
		             * We are storing the name of the reports using the Date Stamp to 
		             * distinguish the reports generated while testing the program multiple times.
		             ********************************************************************************/
					
					ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("src\\test\\resources\\HTML_Reports\\" + reportName);
					report =  new ExtentReports();
					report.attachReporter(htmlReporter);
					
					//Stating some of the system info in the report in which the project has run  
					report.setSystemInfo("OS", "Windows 10");
					report.setSystemInfo("Environment", "UAT");
					report.setSystemInfo("Build Number", "10.8.1");
					report.setSystemInfo("Browser", "Chrome");
					
					//Stating some of the project info in the report
					htmlReporter.config().setDocumentTitle("Automation Results");
					htmlReporter.config().setReportName("Test Report");
					htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
					htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
				}
				//Returning the report.
				return report;
			}
}

