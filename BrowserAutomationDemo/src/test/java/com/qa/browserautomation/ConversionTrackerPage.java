package com.qa.browserautomation;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

/**
 * Page object for Conversion Tracker Page.
 * 
 */
public class ConversionTrackerPage {

	private static final String conversionTrackersTitle = "Conversion Trackers";
	private static final String noResultForFilters = "There are no results for the selected filters.";

	/**
	 * Initializes conversion creation command.
	 * 
	 * @return
	 */
	public static CreateConversionCommand createConversionTracker() {
		return new CreateConversionCommand();
	}

	/**
	 * Checks the notification alert on page in case a conversion tracker creation
	 * fails
	 * 
	 * @return {@link True} if error pop ups during conversion tracker creation,
	 *         else {@link False}
	 */
	public static boolean errorOnCreation() {

		WebElement notification = Driver.getInstance().findElement(By.xpath("//div[@class='alert']"));
		return !notification.getText().isEmpty();
	}

	/**
	 * Click operation for the View Conversion Trackers for field with given option
	 * 
	 * @param accountType type of {@link AccountType}
	 */
	public static void clickTrackersFor(AccountType accountType) {

		WebElement selectButton = Driver.getInstance().findElement(By.xpath(
				"//select-dropdown[@class='inline-block ng-untouched ng-pristine ng-valid']//button[@type='button']"));
		selectButton.click();

		WebElement unorderedList = Driver.getInstance()
				.findElement(By.xpath("//div[@class='dropdown--container ng-star-inserted']//ul"));
		List<WebElement> typeList = unorderedList.findElements(By.tagName("li"));

		for (WebElement webElement : typeList) {
			if (webElement.getText().equals(accountType.type())) {
				webElement.click();
				break;
			}
		}

	}

	/**
	 * 
	 * @return {@link True} if the conversion tracker page displays, else
	 *         {@link False}
	 */
	public static boolean isAtConversionTrackers() {
		String pageText = Driver.getInstance().findElement(By.xpath("//h1[contains(text(),'Conversion Trackers')]"))
				.getText();

		return pageText.equals(conversionTrackersTitle);
	}

	/**
	 * Puts the given search string to the search field on Conversion Tracker Page
	 * 
	 * @param string
	 */
	public static void search(String searchInput) {

		WebElement searchField = Driver.getInstance().findElement(By.xpath("//input[@placeholder='Search']"));
		searchField.sendKeys(searchInput);
		Driver.getInstance().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	/**
	 * Retrieves the table row count for the particular option selected (All
	 * Accounts and Show Rows)
	 * 
	 * @return number of items inside the table
	 */
	public static int tableEntryCount() {

		int countOfItems;

		WebElement tableElement = Driver.getInstance().findElement(By.xpath("//div[@class='async-table']"));

		// check now if body of table exists
		boolean bodyExists = tableElement.findElements(By.tagName("tbody")).size() > 0;

		if (bodyExists) {
			WebElement bodyElement = tableElement.findElement(By.tagName("tbody"));
			countOfItems = bodyElement.findElements(By.tagName("tr")).size();
		} else {
			countOfItems = 0;
		}

		return countOfItems;
	}

	/**
	 * Checks if there is no entry at the table the no filter result message is
	 * displayed
	 * 
	 * @return {@link True} if the message is correctly displayed, {@link False}
	 *         otherwise
	 */
	public static boolean noFilterResultMessage() {

		String textMessage = Driver.getInstance().findElement(By.xpath("//h3[@class='ng-star-inserted']")).getText();

		return textMessage.equals(noResultForFilters);
	}

	/**
	 * Checks if all resulting rows include the given search parameter.
	 * 
	 * @param searchEntry
	 * @return {@link True} if all the resultant table rows contains search
	 *         parameter, {@link False} otherwise
	 */
	public static boolean rowsContains(String searchEntry) {

		boolean result = true;

		WebElement tableElement = Driver.getInstance().findElement(By.xpath("//div[@class='async-table']"));

		// check now if body of table exists
		boolean bodyExists = tableElement.findElements(By.tagName("tbody")).size() > 0;

		if (!bodyExists) {
			result = false;
		} else {

			WebElement tableBody = tableElement.findElement(By.tagName("tbody"));

			List<WebElement> rowElements = tableBody.findElements(By.tagName("tr"));

			// for each row at least Tracker Name or Advertiser should include the searched
			// field
			for (WebElement rowElement : rowElements) {

				String trackerNameFieldText = rowElement.findElement(By.xpath("//td[@class='row-actions']//span"))
						.getText();
				String advertiserFieldText = rowElement.findElement(By.xpath("//td[@class='ellipsis']//a")).getText();

				if (trackerNameFieldText.contains(searchEntry) || advertiserFieldText.contains(searchEntry)) {
					result = result && true;
				} else {
					result = result && false;
				}
			}
		}

		return result;
	}

	/**
	 * Changes the row show count for the table filter
	 * 
	 * @param showCount number of the rows displayed per page
	 */
	public static void changeShowCount(int showCount) {

		WebElement showButton = Driver.getInstance().findElement(By.xpath("//span[contains(text(),'10 Rows')]"));

		showButton.click();

		WebElement unorderedList = Driver.getInstance()
				.findElement(By.xpath("//div[@class='dropdown--container ng-star-inserted']//ul"));
		List<WebElement> typeList = unorderedList.findElements(By.tagName("li"));

		for (WebElement webElement : typeList) {
			if (webElement.getText().equals(showCount + " Rows")) {
				webElement.click();
				break;
			}
		}
	}

	public static boolean firstDisabled() {
		return !isButtonEnabled("First");
	}

	public static boolean previousDisabled() {
		return !isButtonEnabled("Previous");
	}

	public static boolean nextDisabled() {
		return !isButtonEnabled("Next");
	}

	public static boolean lastDisabled() {
		return !isButtonEnabled("Last");
	}

	private static boolean isButtonEnabled(String buttonName) {
		return Driver.getInstance().findElement(By.xpath("//button[contains(text(),'" + buttonName + "')]"))
				.isEnabled();
	}

	/**
	 * Shows the detail page for the selected Conversion Tracker
	 * 
	 * @param trackerName
	 */
	public static void showDetailsOfTracker(String trackerName) {
		// first find the tracker name
		WebElement tableBody = Driver.getInstance().findElement(By.xpath("//table[@class='ng-star-inserted']//tbody"));

		List<WebElement> listOfRows = tableBody.findElements(By.tagName("tr"));

		// search for the tracker name given on row items
		// if found click the view details
		for (WebElement rowElement : listOfRows) {
			if (rowElement.findElement(By.xpath("//span[@class='overflow']")).getText().equals(trackerName)) {

				WebElement viewDetails = rowElement.findElement(By.xpath("//a[contains(text(),'View Details')]"));
				// js executor to click on invisible element
				JavascriptExecutor js = (JavascriptExecutor) Driver.getInstance();
				js.executeScript("arguments[0].click();", viewDetails);

			}
		}

		Driver.getInstance().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	/**
	 * Method to check if details page for a tracker record is open and name of
	 * tracker is correct.
	 * 
	 * @param trackerName
	 * @return
	 */
	public static boolean isAtDetails(String trackerName) {

		// to get the already input field use js executor to grab the value of the field
		WebElement trackerNameField = Driver.getInstance()
				.findElement(By.xpath("//div[@class='row ng-star-inserted']//input[@name='name']"));
		JavascriptExecutor js = (JavascriptExecutor) Driver.getInstance();

		return js.executeScript("return arguments[0].value", trackerNameField).equals(trackerName);
	}

}
