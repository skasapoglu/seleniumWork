package com.qa.browserautomation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.browserautomation.HomePage;

public class LoginPageTest extends TestBase {

	// this test is a smoke test if the login is successful.
	@Test
	public void userCanLoginSuccesfuly() {

		Assert.assertTrue(HomePage.isAt(), "Login is not succesfull");
	}

}
