package org.example.testComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listener extends BaseTest implements ITestListener {
    ExtentReports extentReports = ExtentReportsNG.getExtentReportObject();
    ThreadLocal<ExtentTest> threadLocal = new ThreadLocal<>();

    ExtentTest test;
    @Override
    public void onTestStart(ITestResult result) {
        test = extentReports.createTest(result.getMethod().getMethodName());
        threadLocal.set(test); //for this object will assign a unique thread id
        //it knows which test (thread) pushed that object in threadLocal
        //it creates a map,for each thread id will map a test object
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        threadLocal.get().log(Status.PASS,"Test passed!");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        threadLocal.get().fail(result.getThrowable());
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        }
        catch (Exception e){
            e.printStackTrace();
        }

        String filePath = null;
        try {
            filePath = getScreenshot(result.getMethod().getMethodName(),driver);
        } catch (IOException e) {
            e.printStackTrace();
        }
        test.addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());


    }

    @Override
    public void onTestSkipped(ITestResult result) {
        threadLocal.get().log(Status.SKIP,"Test skipped!");
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }
}

