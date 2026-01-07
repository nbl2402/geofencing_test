package org.mob;

import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.utilities.DriverFactory;
import org.utilities.LogHelpers;
import org.utilities.WaitHelpers;

public class MobileBaseTest
{
    protected AndroidDriver driver;

    @BeforeMethod
    public void setUp()
    {
        driver = DriverFactory.createAndroidDriver();
        WaitHelpers.setDriver(driver);
    }

    @AfterMethod
    public void tearDown()
    {
        LogHelpers.logStep("Close Mobile app");
        LogHelpers.clear();
        if (driver != null)
            driver.quit();
    }
}
