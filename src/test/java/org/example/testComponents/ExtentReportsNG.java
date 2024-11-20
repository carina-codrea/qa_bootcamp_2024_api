package org.example.testComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG{

    public static ExtentReports getExtentReportObject(){
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("index.html");
        sparkReporter.config().setDocumentTitle("Selenium Dummy Web App");
        sparkReporter.config().setReportName("Rahul Shetty Academy Selenium Test Case");

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Tester","Codrea Carina");

        return extentReports;
    }
}
