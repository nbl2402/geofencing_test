package org.mob;

import io.appium.java_client.android.AndroidDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.utilities.DriverFactory;
import org.utilities.WaitHelpers;

import java.io.IOException;

public class MobileBaseTest
{
    protected static final Logger logger = LoggerFactory.getLogger(MobileBaseTest.class);
    protected AndroidDriver driver;

    @BeforeMethod
    public void setUp()
    {
        logger.info("Launch Mobile app");
        driver = DriverFactory.createAndroidDriver();
        WaitHelpers.setDriver(driver);
    }

    @AfterMethod
    public void tearDown()
    {
        logger.info("Close Mobile app");
        if (driver != null)
            driver.quit();
    }
}
