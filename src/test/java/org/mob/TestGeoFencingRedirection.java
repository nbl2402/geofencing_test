package org.mob;

import listeners.TestListener;
import org.pageObjects.DirectionPage;
import org.pageObjects.HomePage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.utilities.LogHelpers;
import org.enumObjects.MapEnums;

@Listeners(TestListener.class)
public class TestGeoFencingRedirection extends MobileBaseTest{

    /**
     * Test Case: Verify Google Map displays in home screen
     * Step 1: Launch geofencing app
     * Step 2: Select Access Type option - Only This Time
     * Step 3: Validate Google Maps is shown
     */
    @Test
    public void verifyGoogleMapDisplaysInHomeScreen()
    {
        LogHelpers.logStep("Launch Mobile app");
        HomePage homePage = new HomePage(driver);

        LogHelpers.logStep("Validate allow access screen is displayed");
        homePage.validateAllowAccessScreenDisplayed();

        LogHelpers.logStep("Tap on 'Only This Time' option");
        homePage.selectAccessType(MapEnums.AccessType.ONLY_THIS_TIME);

        LogHelpers.logStep("Validate Google Map is shown");
        homePage.validateGoogleMapDisplayed();
    }

    /**
     * Test Case: Verify user is redirected to Direction screen
     * Step 1: Launch geofencing app
     * Step 2: Select Access Type option - Only This Time
     * Step 3: Tap on marker point
     * Step 4: Tap on Direction button
     * Step 5: Validate location fields display correctly
     *          - Start location: Your location
     *          - Destination location: not null
     */
    @Test
    public void verifyDirectionDisplayedCorrectly()
    {
        LogHelpers.logStep("Launch Mobile app");
        HomePage homePage = new HomePage(driver);

        LogHelpers.logStep("Tap on 'Only This Time' option");
        homePage.selectAccessType(MapEnums.AccessType.ONLY_THIS_TIME);

        LogHelpers.logStep("Tap on direction button");
        homePage.waitForGoogleMapDisplayed();
        DirectionPage directionPage = homePage.tapOnDirectionButton();

        LogHelpers.logStep("Validate text displays correctly in start location field");
        directionPage.validateStartLocationFieldDisplayed("Your location");

        LogHelpers.logStep("Validate text displays correctly in destination location field");
        directionPage.validateDestinationLocationFieldDisplayed("Invalid Value");
    }
}
