package seatech.uiTest.ibv.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;
import seatech.common.baseBrowser.Base;

import seatech.common.functions.CommonFunctions;
import seatech.ibv.data.BaseData;
import seatech.uiTest.util.HybridDriven;
import seatech.uiTest.util.ReadExcelFile;


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
        hybridDriven.startExecution("ck trong");
    }
}
