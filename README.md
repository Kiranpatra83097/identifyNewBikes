# identifyNewBikes



WEBSITE:
https://www.zigwheels.com/






TOOLS & TECHNOLOGY USED:
	Selenium with Java in Eclipse: Selenium is a portable framework for testing web applications. Selenium provides a playback tool for authoring functional tests.Developed using Java, the Eclipse platform can be used to develop rich client applications, integrated development environments and other tools. Eclipse can be used as an IDE for any programming language for which a plug-in is available.

TestNG: TestNG (Test Next Generation) is the testing framework. TestNG is inspired from JUnit and NUnit, but it has a new functionality that makes this framework more powerful and easier. TestNG is designed in such a way that it covers all the categories of tests comprising unit, functional and integration.



Maven: Maven is a build automation tool used primarily for Java projects. Maven can also be used to build and manage projects written in C#, Ruby, Scala, and other languages. Maven is built using a plugin- based architecture that allows it to make use of any application controllable through standard input.

Page Factory with POI:Page Factory is a class provided by Selenium WebDriver to support Page Object Design patterns.The advantage of the makes your framework more structured, robust and maintainable. Changes in the DOM Tree of any individual/multiple page can be accommodated with quite ease. Through Page Factory , the different calls between @Test class, BrowserFactory , Page Objects and Assertions becomes more cleaner and efficient. Apache POI, a project run by the Apache Software Foundation, and previously a sub-project of the Jakarta Project, provides pure Java libraries for reading and writing files in Microsoft Office formats, such as Word, PowerPoint and Excel.


WORD DONE:
The Code is written in JAVA and is automated in different browsers with the help of Selenium. The project and dependencies are managed with the help of Maven.
Page Factory is used to create classes for each page therefore reducing usage of same code for various objects.
Input data is obtained from properties file with the help of Apache POI.




REQUIREMENTS AND EXECUTION STEPS:



Display "Upcoming" bikes details like bike name, price and expected launch date in India, for manufacturer 'Honda' & Bike price should be less than 4Lac.

Click on new bikes.
Click on upcoming bikes.
Choose ‘Honda’ from the drop down box.
Display upcoming bikes under 4lacs and also under 2 lacs by storing them in array and writing in excel sheet.

For Used cars in Chennai, extract all the popular models in a List; Display the same.

Go to Used cars drop down and select the following cities: Chennai.
Extract the displayed options and write them in excel sheet and update it in the popularCars.properties file.


Try to 'Login' with google, give invalid account details & capture the error message
Click on Login button in Homepage
Login to google with invalid credentials and capture screenshot write the error message in extend report








PREREQUISITES

Steps to automate:
Initially we need to create a Maven Project.
In that project we have to create test scenarios and test classes 
According to this project we need to create methods in that pages 
We have to use Base Class for driver setup and others utilities
Data driven concept is used for the pages to read data and write data 
This are the basic requirements of this project.
