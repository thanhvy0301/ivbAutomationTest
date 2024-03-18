package seatech.uiTest.ibv.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

import seatech.common.functions.CommonFunctions;
import seatech.ibv.data.BaseData;
import seatech.uiTest.util.HybridDriven;
import seatech.uiTest.util.Log;
import seatech.uiTest.util.ReadExcelFile;


public class HybridExecuteTest  {

    private WebDriver driver;
    CommonFunctions commonFunctions = new CommonFunctions(driver);
    @Test( testName = "TC-1", priority = 1)
    public void loginTest() throws InterruptedException {
        Log.info("Run Testcase");
        HybridDriven hybridDriven= new HybridDriven();
        hybridDriven.startExecution("ck trong");
    }
}
