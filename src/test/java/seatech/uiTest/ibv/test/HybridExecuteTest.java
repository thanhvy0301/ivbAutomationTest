package seatech.uiTest.ibv.test;


import io.qameta.allure.*;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import seatech.common.baseBrowser.Base;
import seatech.common.functions.AllureListeners;
import seatech.common.functions.CaptureHelpers;
import seatech.common.functions.CommonFunctions;
import seatech.ibv.data.BaseData;
import seatech.uiTest.util.HybridDriven;
import seatech.uiTest.util.Log;
import seatech.uiTest.util.ReadExcelFile;

import java.io.File;
import java.sql.DriverManager;
import java.util.Properties;

import static org.testng.AssertJUnit.assertEquals;


public class HybridExecuteTest extends  AllureListeners{
    static WebDriver driver;
    Properties prop;
    Base base;
    CommonFunctions commonFunctions = new CommonFunctions(driver);
    @Step("Data_1")
    @Test( testName = "TC-1", priority = 1)
    @Feature("Chuyển khoản trong hệ thống")
    public void InternalTrans() throws InterruptedException {
        Log.info("Run Testcase");
        HybridDriven hybridDriven= new HybridDriven();
        hybridDriven.startExecution("Sheet3");
    }
    @Step("Data_2")
    @Test( testName = "TC-2", priority = 2)
    @Feature("Chuyển khoản trong hệ thống")
    public void InternalTrans_1() throws InterruptedException {
        Log.info("Run Testcase");
        HybridDriven hybridDriven= new HybridDriven();
        hybridDriven.startExecution("ck trong");
    }
    @Attachment(value = "Screenshot on failure", type = "image/png")
    public byte[] captureScreenshot() {
    try {
        TakesScreenshot ts = (TakesScreenshot) driver;
        return ts.getScreenshotAs(OutputType.BYTES);
    } catch (Exception e) {
        System.out.println("Exception while taking screenshot: " + e.getMessage());
        return null;
    }
}
    @AfterMethod
    public void takeScreenshot(ITestResult result) throws InterruptedException {
        if (ITestResult.FAILURE == result.getStatus()) {
            Thread.sleep(2000);
            byte[] screenshot = captureScreenshot();
        }
    }
    @Attachment(value = "Screenshot on failure", type = "image/png")
    public byte[] attachScreenshotToAllure(byte[] screenshotBytes, String screenshotName) {
        return screenshotBytes;
    }
}