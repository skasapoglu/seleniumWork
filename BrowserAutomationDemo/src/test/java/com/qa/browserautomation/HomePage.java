package com.qa.browserautomation;

import org.openqa.selenium.By;

/**
 * Page object for the home page
 * 
 */
public class HomePage {

	// test oracle for the assertions
	private static final String homePageTitle = "Campaign Manager";

	public static boolean isAt() {

		String pageText = Driver.getInstance().findElement(By.xpath("//h1[contains(text(),'Campaign Manager')]"))
				.getText();
		return pageText.equals(homePageTitle);
	}

	/**
	 * Navigates around the left menu bar on home page
	 * 
	 * @param conversionTrackers is a type of {@link CampaingManager}
	 */
	public static void GoTo(CampaingManager conversionTrackers) {

		switch (conversionTrackers) {
		case CONVERSION_TRACKERS:
			Driver.getInstance().findElement(By.xpath("//span[contains(text(),'Conversion Trackers')]")).click();
			break;
		// for other pages selections we can add in here but not required atm.
		default:
			System.out.println("Wrong page selected");
		}

	}
}
