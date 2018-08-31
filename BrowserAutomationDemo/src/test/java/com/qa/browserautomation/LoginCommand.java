package com.qa.browserautomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Command pattern to build up the login operation with parameters.
 */
public class LoginCommand {

	private String username;
	private String password;

	public LoginCommand(String username) {
		this.username = username;
	}

	/**
	 * Adds password to Login inputs.
	 * 
	 * @param password
	 * @return instance of {@link LoginCommand} with password
	 * 
	 */
	public LoginCommand withPassword(String password) {
		this.password = password;
		return this;
	}

	/**
	 * Logins to the homepage with given fields username and password
	 * S
	 */
	public void login() {

		WebElement userField = Driver.getInstance().findElement(By.xpath("//input[@id='email']"));
		userField.sendKeys(username);

		WebElement passField = Driver.getInstance().findElement(By.xpath("//input[@id='password']"));
		passField.sendKeys(password);

		Driver.getInstance().findElement(By.xpath("//button[@type='submit']")).click();

	}

}
