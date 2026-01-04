package org.utilities;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Supplier;

public class WaitHelpers {

    private static AppiumDriver driver;
    private static WebDriverWait wait;

    public static void setDriver(AppiumDriver appiumDriver)
    {
        driver = appiumDriver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public static void waitForVisible(WebElement webElement)
    {
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void waitForTextDisplayed(WebElement webElement, String expected)
    {
        wait.until(ExpectedConditions.textToBePresentInElement(webElement, expected));
    }

    public static void waitForSeconds(int seconds)
    {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
