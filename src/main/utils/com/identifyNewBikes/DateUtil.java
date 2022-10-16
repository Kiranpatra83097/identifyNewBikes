package com.identifyNewBikes;


import java.util.Date;

public class DateUtil {
	
	/**********************************************************************************
	 * Following method is used to print 'Date' on the extend report
	 **********************************************************************************/
	
	//Method getting the Timestamp
     public static String getTimeStamp(){
    	 
    	// Instantiate a Date object
		Date date = new Date();
		
		// return time and date using toString()
		return date.toString().replaceAll(":", "_").replaceAll(" ", "_");
			}
	}


