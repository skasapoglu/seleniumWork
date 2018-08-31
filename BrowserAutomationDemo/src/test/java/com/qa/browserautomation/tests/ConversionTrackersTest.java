package com.qa.browserautomation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.browserautomation.AccountType;
import com.qa.browserautomation.CampaingManager;
import com.qa.browserautomation.ConversionTrackerPage;
import com.qa.browserautomation.HomePage;

public class ConversionTrackersTest extends TestBase {

	@Test
	public void userCanReachConversionTracker() {
		HomePage.GoTo(CampaingManager.CONVERSION_TRACKERS);
		Assert.assertTrue(ConversionTrackerPage.isAtConversionTrackers(), "Conversion Tracker cannot be loaded");
	}

	@Test
	public void userWithOutPrivilagesCannotCreateConversionTracker() {

		HomePage.GoTo(CampaingManager.CONVERSION_TRACKERS);

		ConversionTrackerPage.createConversionTracker().withAdvertiser("testAutomationAdvertiser")
				.withTracker("testTrackerName").withType("Image Pixel").withAttributionWindow("30 Days").create();

		Assert.assertTrue(ConversionTrackerPage.errorOnCreation(),
				"The creation is successful, but the user has no rights");

	}

	@Test
	public void userCanSeeConversionTrackersForAllAccounts() {
		// for this test only 1 tracker can be displayed
		int expectedTrackerCount = 1;

		HomePage.GoTo(CampaingManager.CONVERSION_TRACKERS);

		ConversionTrackerPage.clickTrackersFor(AccountType.All_ACCOUNTS);

		Assert.assertEquals(ConversionTrackerPage.tableEntryCount(), expectedTrackerCount,
				"The table entry count did not match");
	}

	@Test
	public void userCanSeeConversionTrackersForSharedAccounts() {
		// for this test no tracker exists
		int expectedTrackerCount = 0;

		HomePage.GoTo(CampaingManager.CONVERSION_TRACKERS);

		ConversionTrackerPage.clickTrackersFor(AccountType.SELECTED_ACCOUNTS);

		Assert.assertEquals(ConversionTrackerPage.tableEntryCount(), expectedTrackerCount,
				"The table entry count did not match");

		Assert.assertTrue(ConversionTrackerPage.noFilterResultMessage(),
				"The message did not match with the requirement");

	}

	@Test
	public void userCanSearchCreatedConversionTracker() {

		HomePage.GoTo(CampaingManager.CONVERSION_TRACKERS);
		ConversionTrackerPage.clickTrackersFor(AccountType.All_ACCOUNTS);
		// for simplicity case insensitive test not included
		ConversionTrackerPage.search("tesTracker");

		Assert.assertTrue(ConversionTrackerPage.rowsContains("testTracker"),
				"Some rows don't include given search item");

	}

	@Test
	void userCanPartiallySearchCreatedConversionTracker() {

		HomePage.GoTo(CampaingManager.CONVERSION_TRACKERS);
		ConversionTrackerPage.clickTrackersFor(AccountType.All_ACCOUNTS);

		ConversionTrackerPage.search("testAut");

		Assert.assertTrue(ConversionTrackerPage.rowsContains("testAut"), "Some rows don't include given search item");
	}

	@Test
	public void givenShowResultGreaterThanTotalRowsMoveButtonsDisabled() {
		// given only 1 conversion tracker name
		HomePage.GoTo(CampaingManager.CONVERSION_TRACKERS);
		ConversionTrackerPage.clickTrackersFor(AccountType.All_ACCOUNTS);

		ConversionTrackerPage.changeShowCount(5);

		Assert.assertTrue(ConversionTrackerPage.firstDisabled());
		Assert.assertTrue(ConversionTrackerPage.previousDisabled());
		Assert.assertTrue(ConversionTrackerPage.nextDisabled());
		Assert.assertTrue(ConversionTrackerPage.lastDisabled());

	}

	@Test
	public void userCanViewDetailsOfSelectedConversionTracker() {

		HomePage.GoTo(CampaingManager.CONVERSION_TRACKERS);
		ConversionTrackerPage.clickTrackersFor(AccountType.All_ACCOUNTS);

		ConversionTrackerPage.showDetailsOfTracker("testTracker");

		Assert.assertTrue(ConversionTrackerPage.isAtDetails("testTracker"),
				"Detail is not match with selected tracker");

	}

	@Test
	public void userCanSortWithTrackerName() {
		// for this test I cannot add tracker thus so I cannot assert automatically now.
		// if I could write down the test it would be as follows

		// add 11 number of trackers first

		// then sort according to Tracker Name (for other fields Advertiser Type Created
		// as well)

		// assert that the list of elements you grab is sorted according to Tracker
	}

	@Test
	public void userCanShowDesiredAmountOfRows() {
		// for this test I cannot add tracker thus so I cannot assert automatically now.
		// if I could write down the test it would be as follows

		// given 11 number of trackers

		// select show button with 5 Rows

		// assert that the table list size of items is 5 not more

		// next last buttons available
	}

}
