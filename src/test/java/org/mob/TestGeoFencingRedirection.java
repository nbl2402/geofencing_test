package org.mob;

import org.pageObjects.DirectionPage;
import org.pageObjects.HomePage;
import org.testng.annotations.Test;
import org.utilities.MapEnums;

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
        HomePage homePage = new HomePage(driver);

        logger.info("Validate allow access screen is displayed");
        homePage.validateAllowAccessScreenDisplayed();

        logger.info("Tap on 'Only This Time' option");
        homePage.selectAccessType(MapEnums.AccessType.ONLY_THIS_TIME);

        logger.info("Validate Google Map is shown");
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
        HomePage homePage = new HomePage(driver);

        logger.info("Tap on 'Only This Time' option");
        homePage.selectAccessType(MapEnums.AccessType.ONLY_THIS_TIME);

        logger.info("Tap on direction button");
        homePage.waitForGoogleMapDisplayed();
        DirectionPage directionPage = homePage.tapOnDirectionButton();

        logger.info("Validate text displays correctly in start location field");
        directionPage.validateStartLocationFieldDisplayed("Your location");

        logger.info("Validate text displays correctly in destination location field");
        directionPage.validateDestinationLocationFieldDisplayed("Mensa am Hofgarten");
    }
}
