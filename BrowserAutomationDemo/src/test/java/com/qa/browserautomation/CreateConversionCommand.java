package com.qa.browserautomation;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

/**
 * Command to create a new Conversion Tracker
 *
 */
public class CreateConversionCommand {

	private String advertiser;
	private String trackerName;
	private String type;
	private String attributionWindow;

	/**
	 * Adds advertiser to the command
	 * 
	 * @param advertiser
	 * @return
	 */
	public CreateConversionCommand withAdvertiser(String advertiser) {
		this.advertiser = advertiser;
		return this;
	}

	/**
	 * Adds tracker name to the command
	 * 
	 * @param trackerName
	 * @return
	 */
	public CreateConversionCommand withTracker(String trackerName) {
		this.trackerName = trackerName;
		return this;
	}

	/**
	 * Adds type to the command
	 * 
	 * @param type
	 * @return
	 */
	public CreateConversionCommand withType(String type) {
		this.type = type;
		return this;
	}

	/**
	 * Adds attributionWindow to the command
	 * 
	 * @param trackerName
	 * @return
	 */
	public CreateConversionCommand withAttributionWindow(String attributionWindow) {
		this.attributionWindow = attributionWindow;
		return this;
	}

	/**
	 * Creates a new conversion tracker
	 */
	public void create() {

		Driver.getInstance().findElement(By.xpath("//button[contains(text(),'Create Conversion Tracker')]")).click();
		Driver.getInstance().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		try {

			selectSearchableAdvitiser();

			Driver.getInstance().findElement(By.xpath("//input[@name='name']")).sendKeys(trackerName);

			selectTypeField();

			selectAttributeWindowField();

			Driver.getInstance().findElement(By.xpath("//button[contains(text(),'Create Tracker')]")).click();

		} catch (InterruptedException e) {
			System.out.println(e.getStackTrace().toString());
		}

	}

	private void selectSearchableAdvitiser() throws InterruptedException {

		WebElement searchableText = Driver.getInstance()
				.findElement(By.xpath("//input[@placeholder='Type an advertiser name']"));
		searchableText.sendKeys(advertiser);

		// work around to populate the dropdown and select the element containing test
		// not a good solution
		Thread.sleep(5000);
		searchableText.sendKeys(Keys.BACK_SPACE);
		Thread.sleep(5000);
		searchableText.submit();

		WebElement dropdown = Driver.getInstance()
				.findElement(By.xpath("//dropdown[@class='full-width']//div[@class='dropdown']"));
		WebElement unorderedList = dropdown
				.findElement(By.xpath("//div[@class='dropdown--container ng-star-inserted']//ul"));
		List<WebElement> selectList = unorderedList.findElements(By.tagName("li"));

		// find the dropdown item in list to click
		for (WebElement webElement : selectList) {
			if (webElement.getText().contains(advertiser)) {
				webElement.click();
				break;
			}
		}

	}

	private void selectTypeField() {
		WebElement dropdown = Driver.getInstance()
				.findElement(By.xpath("//select-dropdown[@name='type']//button[@type='button']"));

		dropdown.click();

		WebElement unorderedList = dropdown
				.findElement(By.xpath("//div[@class='dropdown--container ng-star-inserted']//ul"));
		List<WebElement> typeList = unorderedList.findElements(By.tagName("li"));

		for (WebElement webElement : typeList) {
			if (webElement.getText().equals(type)) {
				webElement.click();
				break;
			}
		}
	}

	private void selectAttributeWindowField() {
		WebElement dropdown = Driver.getInstance()
				.findElement(By.xpath("//select-dropdown[@name='expiration']//button[@type='button']"));
		dropdown.click();

		WebElement unorderedList = dropdown
				.findElement(By.xpath("//div[@class='dropdown--container ng-star-inserted']//ul"));
		List<WebElement> typeList = unorderedList.findElements(By.tagName("li"));

		for (WebElement webElement : typeList) {
			if (webElement.getText().equals(attributionWindow)) {
				webElement.click();
				break;
			}
		}
	}

}
