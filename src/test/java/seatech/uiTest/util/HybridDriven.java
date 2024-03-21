package seatech.uiTest.util;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import io.qameta.allure.Step;
import jdk.jfr.Description;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import seatech.common.baseBrowser.Base;
import seatech.common.config.PropertiesFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class HybridDriven {

     WebDriver driver;
    public Properties prop;

    public static Workbook book;
    public static Sheet sheet;

    public Base base;
    /*ReadProperties readProperties;*/
    public WebElement element;
    //public CommonFunctions cFunc;
    public HybridDriven(WebDriver driver){
        this.driver =driver;
    }


    String projectPath = System.getProperty("user.dir");
    public void startExecution(String sheetName) {
        Properties prop = new Properties();
        SoftAssert softAssert = new SoftAssert();
        PropertiesFile .setPropertiesFile();
        FileInputStream file = null;
        try {
            //đường dẫn đọc file excel từ file config với key là pathExcel
            file = new FileInputStream(projectPath+PropertiesFile.getPropValue("pathExcel"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            book = WorkbookFactory.create(file);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sheet = book.getSheet(sheetName);

        int k = 1;
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            try {
                //Duyệt tuần tự mỗi cột trong excel, bắt đầu từ dòng thứ 1, k tương ứng với thứ tự cột trong excel
                String locatorType = sheet.getRow(i + 1).getCell(k + 1).toString().trim();
                String locatorValue = sheet.getRow(i + 1).getCell(k + 2).toString().trim();
                String action = sheet.getRow(i + 1).getCell(k + 3).toString().trim();
                String data1 = sheet.getRow(i + 1).getCell(k + 4).toString().trim();
                String verify = sheet.getRow(i + 1).getCell(k + 5).toString().trim();
                //switch action: xử lí hành động tương ứng với case có giá trị giống với cột action trong excel
                switch (action) {
/*                    case "open browser":
                        base = new Base();
                        prop = base.init_properties();
                        if (data1.isEmpty() || data1.equals("NA")) {
                            driver = base.init_driver(prop.getProperty("browser"));
                        } else {
                            driver = base.init_driver(data1);
                        }
                        break;*/

                    case "enter url": //Nếu hành động được nhập trong excel là enter url thì driver sẽ mở url tương ứng
                        if (data1.isEmpty() || data1.equals("NA")) {
                            driver.get(PropertiesFile.getPropValue("url"));
                        } else {
                            driver.get(data1);
                        }
                        break;

                    case "quit":
                        Thread.sleep(3000);
                        driver.quit();
                        break;
                    default:
                        break;
                }
                //switch localType: xử lí hành động tuương ứng với giá trị trong excel
                switch (locatorType) {
                    case "id":
                        element = driver.findElement(By.id(locatorValue));
                        if (action.equalsIgnoreCase("sendkeys")) {
                            element.clear();
                            element.sendKeys(data1);
                            Log.info("SendKeys: "+data1);
                        } else if (action.equalsIgnoreCase("click")) {
                            element.click();
                            Thread.sleep(2000);
                            Log.info("Click: "+ locatorValue);
                        } else if (action.equalsIgnoreCase("isDisplayed")) {
                            element.isDisplayed();
                            Log.info("Display: "+ locatorValue);
                        }
                        else if (action.equalsIgnoreCase("getText")) {
                            String elementText = element.getText();
                            System.out.println("text from element : " + elementText);
                        } else if (action.equalsIgnoreCase("switchTo")) {
                            Thread.sleep(2000);
                            driver.switchTo().frame(driver.findElement(By.id(locatorValue)));
                            Log.info("Switch to: "+locatorValue);
                        }
                        else if (action.equalsIgnoreCase("switchToDefault")) {
                            driver.switchTo().defaultContent();
                            Log.info("Switch to default: "+locatorValue);
                        }
                        else if (action.equalsIgnoreCase("select")) {
                            // Khởi tạo đối tượng Select
                            Select selectDropdown = new Select(element);
                            Log.info("Select box: "+locatorValue);
                            selectDropdown.selectByValue(data1);
                            Log.info("Select option: "+data1);
                        }
                        locatorType = null;
                        break;
                    case "name":
                        element = driver.findElement(By.name(locatorValue));

                        if (action.equalsIgnoreCase("sendkeys")) {
                            element.clear();
                            element.sendKeys(data1);
//                            Thread.sleep(3000);
                        } else if (action.equalsIgnoreCase("click")) {
                            element.click();
//                            Thread.sleep(3000);
                        } else if (action.equalsIgnoreCase("isDisplayed")) {
                            element.isDisplayed();
                        } else if (action.equalsIgnoreCase("getText")) {
                            String elementText = element.getText();
                            System.out.println("text from element : " + elementText);
                        }
                        locatorType = null;
                        break;
                    case "xpath":
                        element = driver.findElement(By.xpath(locatorValue));
                        //Select selectElement  = new Select(element);
                        if (action.equalsIgnoreCase("sendkeys")) {
                            element.clear();
                            element.sendKeys(data1);
                            Log.info("SendKeys:" +data1);
                        } else if (action.equalsIgnoreCase("click")) {
                            //cFunc.waitVisible(element);
                            Thread.sleep(2000);
                            element.click();
                            Thread.sleep(2000);
                            Log.info("Click:" +locatorValue);
                        } else if (action.equalsIgnoreCase("isDisplayed")) {
                            //cFunc.waitVisible(element);
                            element.isDisplayed();
                            Log.info("Display: "+locatorValue);
                        } else if (action.equalsIgnoreCase("getText")) {
                            String elementText = element.getText();
                            System.out.println("text from element : " + elementText);
                        }
                        else if (action.equalsIgnoreCase("verifyText")) {
                            String actualText = driver.findElement(By.xpath(locatorValue)).getText();
                            softAssert.assertEquals(actualText, verify);
                            Log.info("Actual Text: "+actualText);
                        }
                        locatorType = null;
                        break;
                    case "cssSelector":
                        element = driver.findElement(By.cssSelector(locatorValue));
                        if (action.equalsIgnoreCase("sendkeys")) {
                            element.clear();
                            element.sendKeys(data1);
                            Thread.sleep(2000);
                        } else if (action.equalsIgnoreCase("click")) {
                            element.click();
                        } else if (action.equalsIgnoreCase("isDisplayed")) {
                            element.isDisplayed();
                        } else if (action.equalsIgnoreCase("getText")) {
                            String elementText = element.getText();
                            System.out.println("text from element : " + elementText);
                        }
                        locatorType = null;
                        break;
                    case "className":
                        element = driver.findElement(By.className(locatorValue));
                        if (action.equalsIgnoreCase("sendkeys")) {
                            element.clear();
                            element.sendKeys(data1);
                            Thread.sleep(3000);
                        } else if (action.equalsIgnoreCase("click")) {
                            element.click();
                            Thread.sleep(3000);
                        } else if (action.equalsIgnoreCase("isDisplayed")) {
                            element.isDisplayed();
                        } else if (action.equalsIgnoreCase("getText")) {
                            String elementText = element.getText();
                            System.out.println("text from element : " + elementText);
                        }
                        locatorType = null;
                        break;
                    case "linkText":
                        element = driver.findElement(By.linkText(locatorValue));
                        element.click();
                        Thread.sleep(3000);
                        locatorType = null;
                        break;
                    case "switchToDefault":
                        if (locatorValue.equals("NA")){
                            action.equalsIgnoreCase("switchToDefault");
                            driver.switchTo().defaultContent();
                            Log.info("Switch to default: "+locatorValue);
                            Thread.sleep(2000);
                        }
                        default:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
           // softAssert.assertAll();
        }
        softAssert.assertAll();// Sau khi thực thi toàn bộ testcase thì sẽ hiển thị too

    }
}