package com.qa.browserautomation.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.qa.browserautomation.Config;
import com.qa.browserautomation.Driver;
import com.qa.browserautomation.LoginPage;

public class TestBase {

	@BeforeMethod()
	public void setup() {
		Config.initilize();
		//since those are common for all tests I have just separated into before method
		LoginPage.goTo();
		LoginPage.loginAs(Config.getUsername()).withPassword(Config.getPassword()).login();
		LoginPage.closePopUps();
	}

	@AfterMethod(alwaysRun=true)
	public void cleanup() {
		Driver.cleanUp();
	}

}
