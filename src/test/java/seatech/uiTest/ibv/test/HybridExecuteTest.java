package seatech.uiTest.ibv.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import seatech.common.baseBrowser.Base;

import seatech.common.functions.CommonFunctions;
import seatech.ibv.data.BaseData;
import seatech.uiTest.util.HybridDriven;
import seatech.uiTest.util.ReadExcelFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static seatech.uiTest.util.HybridDriven.book;


public class HybridExecuteTest  {

    private WebDriver driver;
    CommonFunctions commonFunctions = new CommonFunctions(driver);
    Base base;
    @FindBy(xpath = "//span[contains(text(),'Xin lỗi')]")
    public WebElement showNotice;
    @FindBy(name = "button")
    public WebElement btnAgree;
    @Test( testName = "TC-1", priority = 1)
    public void loginTest() throws InterruptedException {
        HybridDriven hybridDriven= new HybridDriven();
        hybridDriven.startExecution("login");
    }
}
