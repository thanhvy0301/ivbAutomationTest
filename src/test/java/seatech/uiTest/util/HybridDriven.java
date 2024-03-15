package seatech.uiTest.util;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import seatech.common.baseBrowser.Base;
import seatech.common.functions.CommonFunctions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class HybridDriven {

    public WebDriver driver;
    public Properties prop;

    public static Workbook book;
    public static Sheet sheet;

    public Base base;
    /*ReadProperties readProperties;*/
    public WebElement element;
    public CommonFunctions cFunc;

    public final String SCENARIO_SHEET_PATH = "C:\\Users\\Admin\\Desktop\\automation-test-master\\src\\test\\java\\seatech\\uiTest\\ibv\\testcase\\hubspot_scenarios.xlsx";
    public void startExecution(String sheetName) {

        FileInputStream file = null;
        try {
            file = new FileInputStream(SCENARIO_SHEET_PATH);
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
                String locatorType = sheet.getRow(i + 1).getCell(k + 1).toString().trim();
                String locatorValue = sheet.getRow(i + 1).getCell(k + 2).toString().trim();
                String action = sheet.getRow(i + 1).getCell(k + 3).toString().trim();
                String value1 = sheet.getRow(i + 1).getCell(k + 4).toString().trim();
                //String value2 = sheet.getRow(i + 1).getCell(k + 5).toString().trim();
                switch (action) {
                    case "open browser":
                        base = new Base();
                        prop = base.init_properties();
                        if (value1.isEmpty() || value1.equals("NA")) {
                            driver = base.init_driver(prop.getProperty("browser"));
                        } else {
                            driver = base.init_driver(value1);
                        }
                        break;

                    case "enter url":
                        if (value1.isEmpty() || value1.equals("NA")) {
                            driver.get(prop.getProperty("url"));
                        } else {
                            driver.get(value1);
                        }
                        break;

                    case "quit":
                        driver.quit();
                        break;
                    default:
                        break;
                }
                switch (locatorType) {
                    case "id":
                        element = driver.findElement(By.id(locatorValue));
                        if (action.equalsIgnoreCase("sendkeys")) {
                            element.clear();
                            element.sendKeys(value1);
                            Thread.sleep(3000);
                        } else if (action.equalsIgnoreCase("click")) {
                            element.click();
                        } else if (action.equalsIgnoreCase("isDisplayed")) {
                            Thread.sleep(3000);
                            element.isDisplayed();
                        } else if (action.equalsIgnoreCase("getText")) {
                            String elementText = element.getText();
                            System.out.println("text from element : " + elementText);
                        }
                        locatorType = null;
                        break;
                    case "name":
                        element = driver.findElement(By.name(locatorValue));
                        if (action.equalsIgnoreCase("sendkeys")) {
                            element.clear();
                            element.sendKeys(value1);
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
                    case "xpath":
                        element = driver.findElement(By.xpath(locatorValue));
                        if (action.equalsIgnoreCase("sendkeys")) {
                            element.clear();
                            element.sendKeys(value1);
                        } else if (action.equalsIgnoreCase("click")) {
                            element.click();
                        } else if (action.equalsIgnoreCase("isDisplayed")) {
                            cFunc.waitVisible(element);
                            element.isDisplayed();
                        } else if (action.equalsIgnoreCase("getText")) {
                            String elementText = element.getText();
                            System.out.println("text from element : " + elementText);
                        }
                        locatorType = null;
                        break;
                    case "cssSelector":
                        element = driver.findElement(By.cssSelector(locatorValue));
                        if (action.equalsIgnoreCase("sendkeys")) {
                            element.clear();
                            element.sendKeys(value1);
                            Thread.sleep(3000);
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
                            element.sendKeys(value1);
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
                    case "partialLinkText":
                        element = driver.findElement(By.partialLinkText(locatorValue));
                        element.click();
                        Thread.sleep(3000);
                        locatorType = null;
                        break;
                    default:
                        break;
                    }
                } catch (Exception e) {
            }
        }
    }
}