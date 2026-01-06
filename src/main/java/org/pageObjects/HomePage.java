package org.pageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.utilities.AssertHelpers;
import org.enumObjects.MapEnums;
import org.utilities.WaitHelpers;

import java.time.Duration;

public class HomePage {
    AndroidDriver driver;

    @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_message")
    private WebElement allowAccessingDialog;
    @AndroidFindBy(accessibility = "Google Map")
    private WebElement googleMap;
    @AndroidFindBy(uiAutomator = "new UiSelector().descriptionContains(\"Marker\")")
    private WebElement markerPoint;
    @AndroidFindBy(accessibility = "Get directions")
    private WebElement directionButton;


    public HomePage(AndroidDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(
                new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public void selectAccessType(MapEnums.AccessType accessType)
    {
        String accessTypeItem = "com.android.permissioncontroller:id/%s";

        WebElement item = this.driver.findElement(By.id(String.format(accessTypeItem, accessType.getType())));
        item.click();
    }

    public DirectionPage tapOnDirectionButton()
    {
        markerPoint.click();
        WaitHelpers.waitForVisible(directionButton);
        directionButton.click();
        return new DirectionPage(driver);
    }

    public void waitForGoogleMapDisplayed()
    {
        WaitHelpers.waitForVisible(markerPoint);
    }

    public void validateAllowAccessScreenDisplayed()
    {
        AssertHelpers.isDisplayed(allowAccessingDialog);
    }

    public void validateGoogleMapDisplayed()
    {
        AssertHelpers.isDisplayed(googleMap);
        AssertHelpers.isDisplayed(markerPoint);
    }
}
