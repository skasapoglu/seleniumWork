package com.qa.browserautomation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {

	private static WebDriver driver = null;

	/**
	 * Instantiate the driver object to ensure there will only be used one driver
	 * throughout each test.
	 * 
	 * @return instance of {@link WebDriver} element
	 */
	public static WebDriver getInstance() {

		if (driver == null) {
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}

		return driver;
	}

	/**
	 * Closes all the pages opened up during the test period
	 */
	public static void cleanUp() {
		driver.close();
		driver=null;
	}

}
