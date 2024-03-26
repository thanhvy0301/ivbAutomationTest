package seatech.uiTest.util;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import io.qameta.allure.Step;
import jdk.jfr.Description;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.*;
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
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        SoftAssert softAssert = new SoftAssert();

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
                    case "enter url": //Nếu hành động được nhập trong excel là enter url thì driver sẽ mở url tương ứng
                        if (data1.isEmpty() || data1.equals("NA")) {
                            driver.get(PropertiesFile.getPropValue("url"));
                        } else {
                            driver.get(data1);
                        }
                        break;

                    case "quit":
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
                            if(locatorValue!=null){
                                element.clear();
                                element.sendKeys(data1);
                                Thread.sleep(2000);
                                Log.info("SendKeys: "+data1);
                            }else {
                                break;
                            }
                        } else if (action.equalsIgnoreCase("click")) {
                            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
                            element.click();
//                            Thread.sleep(3000);
                            Log.info("Click: "+ locatorValue);
                        } else if (action.equalsIgnoreCase("isDisplayed")) {
                            try {
                                element.isDisplayed();
                                Log.info("Display: "+ locatorValue);
                            }catch (NoSuchElementException ex){
                                Log.error("Không tìm thấy element: "+ex);
                            }
                            break;
                        }
                        else if (action.equalsIgnoreCase("getText")) {
                            String elementText = element.getText();
                            System.out.println("text from element : " + elementText);
                        } else if (action.equalsIgnoreCase("switchTo")) {
//                            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(locatorValue)));
                            Thread.sleep(1000);
                            driver.switchTo().frame(driver.findElement(By.id(locatorValue)));
                            Log.info("Switch to: "+locatorValue);
                        }
                        /*else if (action.equalsIgnoreCase("switchToDefault")) {
                            driver.switchTo().defaultContent();
                            Thread.sleep(1000);
                            Log.info("Switch to default: "+locatorValue);
                        }*/
                        else if (action.equalsIgnoreCase("select")) {
                            // Khởi tạo đối tượng Select
                            Select selectDropdown = new Select(element);
                            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(locatorValue)));
                            if(data1!=null){
                                Log.info("Select box: "+locatorValue);
                                selectDropdown.selectByValue(data1);
                                Thread.sleep(2000);
                                Log.info("Select option: "+data1);
                            }
                            else {
                                break;
                            }
                        }
                        else if (action.equalsIgnoreCase("verifyText")) {
                            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
                            String actualText = element.getText();
                            softAssert.assertEquals(actualText, verify);
                            Log.info("Actual Text: "+actualText);
                        }
                        locatorType = null;
                        break;
                    case "name":
                        element = driver.findElement(By.name(locatorValue));
                        if (action.equalsIgnoreCase("sendkeys")) {
                            element.clear();
                            element.sendKeys(data1);
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
                    case "xpath":
                        element = driver.findElement(By.xpath(locatorValue));
                        //Select selectElement  = new Select(element);
                        if (action.equalsIgnoreCase("sendkeys")) {
                            if(!data1.equals("NA")){
                                element.clear();
                                element.sendKeys(data1);
                                Log.info("SendKeys:" +data1);
                            }else {
                                break;
                            }
                        } else if (action.equalsIgnoreCase("click")) {
                            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
                            element.click();
                            Thread.sleep(2000);
                            Log.info("Click:" +locatorValue);
                        } else if (action.equalsIgnoreCase("isDisplayed")) {
                            WebElement visibleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
                            Assert.assertTrue(visibleElement.isDisplayed());
                            Log.info("Display: "+visibleElement);
                        } else if (action.equalsIgnoreCase("getText")) {
                            String elementText = element.getText();
                            System.out.println("text from element : " + elementText);
                        }
                        else if (action.equalsIgnoreCase("verifyText")) {
                            String actualText = driver.findElement(By.xpath(locatorValue)).getText();
                            softAssert.assertEquals(actualText, verify);
                            Log.info("Actual Text: "+actualText);
                        }
                        else if (action.equalsIgnoreCase("select")) {
                            // Khởi tạo đối tượng Select
                            Select selectDropdown = new Select(element);
                            Log.info("Select box: "+locatorValue);
                            selectDropdown.selectByValue(data1);
                            Thread.sleep(2000);
                            Log.info("Select option: "+data1);
                        }
                       /* else if (action.equalsIgnoreCase("verifyNull")) {
                            List<WebElement> hiddenRows = driver.findElements(By.xpath(locatorValue));
                            for (WebElement row : hiddenRows) {
                                String textRow = row.getText();
                                System.out.println(textRow);
                                softAssert.assertEquals(textRow,verify);
                            }
                        }*/
                        locatorType = null;
                        break;
                    case "cssSelector":
                        element = driver.findElement(By.cssSelector(locatorValue));
                        //Select selectElement  = new Select(element);
                        if (action.equalsIgnoreCase("sendkeys")) {
                            element.clear();
                            element.sendKeys(data1);
                            Log.info("SendKeys:" +data1);
                        } else if (action.equalsIgnoreCase("click")) {
                            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locatorValue)));
                            element.click();
                            Log.info("Click:" +locatorValue);
                        } else if (action.equalsIgnoreCase("isDisplayed")) {
                            try{
                                WebElement visibleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
                                Log.info("Display: "+visibleElement);
                            }catch (NoSuchElementException noElement){
                                Log.error("Không hiển thị element: "+noElement);
                            }
                        } else if (action.equalsIgnoreCase("getText")) {
                            String elementText = element.getText();
                            System.out.println("text from element : " + elementText);
                        }
                        else if (action.equalsIgnoreCase("verifyText")) {
                            String actualText = driver.findElement(By.cssSelector(locatorValue)).getText();
                            softAssert.assertEquals(actualText, verify);
                            Log.info("Actual Text: "+actualText);
                        }
                        else if (action.equalsIgnoreCase("select")) {
                            // Khởi tạo đối tượng Select
                            Select selectDropdown = new Select(element);
                            Log.info("Select box: "+locatorValue);
                            selectDropdown.selectByValue(data1);
                            Thread.sleep(2000);
                            Log.info("Select option: "+data1);
                        }
                        else if (action.equalsIgnoreCase("verifyNull")) {
                            List<WebElement> hiddenRows = driver.findElements(By.cssSelector(locatorValue));
                            for (WebElement row : hiddenRows) {
                                String textRow = row.getText();
                                System.out.println(textRow);
                                softAssert.assertEquals(textRow,verify);
                            }
                        }
                        locatorType = null;
                        break;
                    case "className":
                        element = driver.findElement(By.className(locatorValue));
                        if (action.equalsIgnoreCase("sendkeys")) {
                            element.clear();
                            element.sendKeys(data1);
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
                    case "linkText":
                        element = driver.findElement(By.linkText(locatorValue));
                        element.click();
                        Thread.sleep(3000);
                        locatorType = null;
                        break;
                    case "switchToDefault":
                        action.equalsIgnoreCase("switchToDefault");
                        driver.switchTo().defaultContent();
                        Log.info("Switch to default: "+locatorValue);
                   /* case "alert":
                        WebDriverWait waitAlert = new WebDriverWait(driver, Duration.ofSeconds(10));
                        try {
                            Alert alert = waitAlert.until(ExpectedConditions.alertIsPresent());
                            if (action.equalsIgnoreCase("alertIsPresent")) {
                                Log.info("Display alert: " + alert.getText());
                            } else if (action.equalsIgnoreCase("acceptAlert")) {
                                alert.accept();
                                Log.info("Accept alert");
                            } else if (action.equalsIgnoreCase("cancelAlert")) {
                                alert.dismiss();
                                Log.info("Dismiss alert");
                            } else if (action.equalsIgnoreCase("verifyTextAlert")) {
                                String textAlert = alert.getText();
                                softAssert.assertEquals(textAlert, verify);
                                Log.info("Verify text alert: " + textAlert + "/" + verify);
                            }
                        } catch (TimeoutException e) {
                            Log.error("Alert không xuất hiện sau khi chờ đợi.");
                        }
                        break;*/
                    default:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        softAssert.assertAll();// Sau khi thực thi toàn bộ testcase thì sẽ hiển thị too

    }
}