package seatech.uiTest.ibv.test;


import io.qameta.allure.*;
import org.apache.commons.io.FileUtils;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import seatech.common.baseBrowser.Base;
import seatech.common.config.PropertiesFile;
import seatech.common.functions.CommonFunctions;
import seatech.uiTest.util.HybridDriven;
import seatech.uiTest.util.Log;

import java.io.File;
import java.sql.DriverManager;
import java.util.Properties;

import static org.testng.AssertJUnit.assertEquals;


public class HybridExecuteTest extends Base{
    Properties prop = new Properties();
    WebDriver driver ;
    @BeforeMethod //Hàm setup sẽ được khởi tạo sau mỗi lần thực thi 1 tc
    public void setutp(){
        PropertiesFile .setPropertiesFile();
        Properties prop = new Properties();
        driver= init_driver(PropertiesFile.getPropValue("browser")); //driver nào sẽ được sử dụng khi được lấy trong file config.properties với key là: browser
    }
    @Step("Data_1")
    @Test( testName = "TC-1", priority = 1) //testName: Tên testcase, priority: thứ thự thi testcase
    @Feature("Chuyển khoản trong hệ thống")
    //Hàm thực thi testcase - Mỗi testcase tương ứng với 1 sheet trong file excel
    public void InternalTrans() throws InterruptedException {
        Log.info("Run TC-1");
        HybridDriven hybridDriven= new HybridDriven(driver);
        hybridDriven.startExecution("Sheet3"); //Tên sheet cần test
    }
    @Step("Data_2")
    @Test( testName = "TC-2", priority = 2)
    @Feature("Chuyển khoản trong hệ thống")
    public void InternalTrans_1() throws InterruptedException {
        Log.info("Run TC-2");
        HybridDriven hybridDriven= new HybridDriven(driver);
        hybridDriven.startExecution("ck trong");
    }
    @AfterMethod //Hàm takeScreenshot sẽ được thực thi sau khi mỗi tc kết thúc (Với điều kiện testcase đó Fail)
    public void takeScreenshot(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                if (driver != null) { // Kiểm tra nếu driver không null
                    TakesScreenshot ts = (TakesScreenshot) driver;
                    File source = ts.getScreenshotAs(OutputType.FILE);
                    // Kiểm tra folder tồn tại. Nếu không, tạo mới folder
                    File theDir = new File("./Screenshots/");
                    if (!theDir.exists()) {
                        theDir.mkdirs();
                    }
                    File screenshotFile = new File("./Screenshots/" + result.getName() + ".png");
                    FileHandler.copy(source, screenshotFile);
                    System.out.println("Đã chụp màn hình: " + result.getName());
                    // Đính kèm ảnh chụp màn hình vào báo cáo Allure
                    byte[] screenshotBytes = FileUtils.readFileToByteArray(screenshotFile);
                    attachScreenshotToAllure(screenshotBytes, result.getName());
                }
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
            } finally {
                if (driver != null) { // Đảm bảo rằng driver không null trước khi gọi quit()
                    driver.quit();
                }
            }
        }
    }
    @Attachment(value = "Screenshot on failure", type = "image/png")
    public byte[] attachScreenshotToAllure(byte[] screenshotBytes, String screenshotName) {
        return screenshotBytes;
    }
}