package utils;

import org.testng.*;

import java.io.File;
import java.io.IOException;

import static utils.DeleteDirectoryFiles.deleteFiles;

public class TestNGListners implements ITestListener , IInvokedMethodListener , ISuiteListener {
    String propertiesFilePath = "src/main/resources/Configurations.properties";

    public void onTestStart(ITestResult result) {
        // not implemented
    }

    public void onTestSuccess(ITestResult result) {
    }

    public void onTestFailure(ITestResult result) {
    }

    public void onTestSkipped(ITestResult result) {
        // not implemented
    }

    public void onStart(ITestContext context) {
        //Load Properties File
        PropertiesManager.filePath = propertiesFilePath;
        try {
            PropertiesManager.loadPropertiesIntoSystem();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Clear Old Files from Screenshots & Allure Results
        File file1 = new File("src/test/resources/Screenshots");
        File file2 = new File("allure-results");
        deleteFiles(file1);
        deleteFiles(file2);
    }

    public void onFinish(ITestContext context) {
        // not implemented
    }

    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        // not implemented
    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
    }
}

