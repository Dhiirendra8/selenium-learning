package Listener;

import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomListener implements ITestListener{
    //This belongs to ITestListener and execute before starting of Test set/batch
    public void onStart(ITestContext arg)
    {
        System.out.println("==> Starts test execution.."+arg.getName());
    }

    //This belongs to ITestListener and execute after starting of Test set/batch
    public void onFinish(ITestContext arg)
    {
        System.out.println("=> Finish test execution.."+arg.getName());
    }

    //This belongs to ITestListener and will execute before the main test starts i.e. @Test
    public void onTestStart(ITestContext arg0)
    {
        System.out.println("=> Starts test.."+arg0.getName());
    }

    //This belongs to ITestListener and will execute when a test is passed
    public void onTestSuccess(ITestResult arg0)
    {
        System.out.println("Passed test.."+arg0.getName());
    }

    //This belongs to ITestListener and will execute when a test is failed
    public void onTestFailure(ITestResult arg0)
    {
        System.out.println("Failed test.."+arg0.getName());
    }

    //This belongs to ITestListener and will execute when a test is skipped
    public void onTestSkipped(ITestResult arg0)
    {
        System.out.println("Skipped test.."+arg0.getName());
    }
}
