package com.qa.browserautomation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

public class LoginPage {

	/**
	 * Navigates to the base url given in properties file.
	 */
	public static void goTo() {
		Driver.getInstance().get(Config.getUrl());
	}

	/**
	 * Logins as user to the application
	 */
	public static LoginCommand loginAs(String username) {
		return new LoginCommand(username);
	}

	/**
	 * this will close the pop-ups just after loging in
	 */
	public static void closePopUps() {
		Driver.getInstance().findElement(By.xpath("//*[@id='wm-shoutout-112607']/div[1]")).click();
		Driver.getInstance().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		Driver.getInstance().findElement(By.xpath("//*[@id='wm-shoutout-110740']/div[1]")).click();
		// I waited here till whole dom elements inside the page with 2secs
		// otherwise unknown error exception with implicit wait
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.out.println(e.getStackTrace());
		}

		Driver.getInstance().findElement(By.xpath("//*[@id='wm-shoutout-101297']/div[1]")).click();
		Driver.getInstance().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

}
