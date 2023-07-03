package com.ExtentReports;

import com.aventstack.extentreports.ExtentReports;

public class ExtentFactory {
    public static ExtentReports getInstance(){
        ExtentReports extent = new ExtentReports();
        extent.setSystemInfo("Selenium version", "4.9.1");
        extent.setSystemInfo("OS", "Windows");
        return extent;
    }
}
