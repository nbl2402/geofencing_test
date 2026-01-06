package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.utilities.JiraHelpers;
import org.utilities.LogHelpers;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result)
    {
        JiraHelpers.reportIfFailed(result);
    }

    @Override
    public void onTestStart(ITestResult result)
    {
        LogHelpers.logStep("====== Test Listener started ======");
        LogHelpers.logStep("TEST STARTED: " + result.getMethod().getMethodName());
    }
}
