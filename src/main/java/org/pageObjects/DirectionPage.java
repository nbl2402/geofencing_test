package org.pageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.utilities.AssertHelpers;
import org.utilities.WaitHelpers;

import java.time.Duration;

public class DirectionPage {
    AndroidDriver driver;

    private final String startPointXpath = "//*[contains(@content-desc,'Start location')]";
    private final String destinationPointXpath = "//*[contains(@content-desc,'Destination')]";

    public DirectionPage(AndroidDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(
                new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public void validateStartLocationFieldDisplayed(String expect)
    {
        WaitHelpers.waitForSeconds(3);
        WebElement start = driver.findElement(By.xpath(startPointXpath));
        AssertHelpers.assertElementText(start, expect);
    }

    public void validateDestinationLocationFieldDisplayed(String expect)
    {
        WaitHelpers.waitForSeconds(3);
        WebElement end = driver.findElement(By.xpath(destinationPointXpath));
        AssertHelpers.assertElementText(end, expect);
    }
}
