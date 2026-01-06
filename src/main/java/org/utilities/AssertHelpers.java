package org.utilities;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class AssertHelpers {

    public static void isDisplayed(WebElement webElement)
    {
        Assert.assertTrue(webElement.isDisplayed());
    }

    public static void assertElementText(WebElement webElement, String expect)
    {
        Assert.assertEquals(webElement.getText(), expect);
    }
}
