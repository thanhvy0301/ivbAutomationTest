package seatech.uiTest.util;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import io.qameta.allure.Step;
import jdk.jfr.Description;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ReadExcelFile {

    WebDriver driver;
    public Properties prop;

    public static Workbook book;
    public static Sheet sheet;

    public Base base;
    /*ReadProperties readProperties;*/
    public WebElement element;
    PropertiesFile propertiesFile;
    //public CommonFunctions cFunc;
    public ReadExcelFile(WebDriver driver){
        this.driver =driver;
    }
    String projectPath = System.getProperty("user.dir");
    /*public void startExecution(String sheetName) {
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

        //driver.get(PropertiesFile.getPropValue("url"));
        sheet = book.getSheet(sheetName);

        int k = 1;
        for (Row row : sheet) {
            // Lấy dữ liệu từ cột 0 và cột 1 (index bắt đầu từ 0)
            String dataFromColumn0 = getStringCellValue(row.getCell(0));
            String dataFromColumn1 = getStringCellValue(row.getCell(1));

            // Sử dụng dữ liệu lấy từ Excel để thực hiện hành động mong muốn
            // Ví dụ:
            // driver.findElement(By.id("element_id")).sendKeys(dataFromColumn0);
            // driver.findElement(By.id("another_element_id")).sendKeys(dataFromColumn1);
        }

        // Đóng workbook và input stream
//        workbook.close();
//        inputStream.close();
    }*/
    public void startExecution(String sheetName) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        FileInputStream file = null;
        try {
            //đường dẫn đọc file excel từ file config với key là pathExcel
            file = new FileInputStream(projectPath+PropertiesFile.getPropValue("pathExcel"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // Load tệp Excel
        try {
            book = WorkbookFactory.create(file);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = book.getSheet(sheetName);
        for (int i = 1; i <sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            String action = row.getCell(1).getStringCellValue();
            String data = row.getCell(2).getStringCellValue();

            switch (action) {
                case "quit":
                    driver.quit();
                    break;
                case "username":
                    driver.findElement(By.id("t_userName")).sendKeys(data);
                    Log.info("Nhập username "+data);
                    break;
                case "password":
                    driver.findElement(By.id("t_password")).sendKeys(data);
                    Log.info("Nhập password "+data);
                    Thread.sleep(10000);
                    driver.findElement(By.tagName("button")).click();
                    Log.info("Nhấn vào button đăng nhập");
                    try {
                        // Wait for the element to be clickable
                        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@name,'button')]")));
                        // Check if the button is displayed
                        if (button.isDisplayed()) {
                            button.click(); // Click the button
                            Log.info("Nhấn vào button đồng ý");
                        }
                    } catch (Exception e) {
                        // Handle exception or log error message
                        e.printStackTrace();
                        Log.info("Không hiển thị trang session");
                    }
                    try{
                        WebElement btnActiveLater = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(.,'Kích hoạt sau')]")));
                        if(btnActiveLater.isDisplayed()){
                            driver.findElement(By.xpath("//span[contains(.,'Kích hoạt sau')]")).click();
                            Log.info("Nhấn vào button kích hoạt sau");
                        }else {
                            Log.info("Không hiển thị btn Kích hoạt");
                            break;
                        }
                    }catch (TimeoutException te){
                        te.printStackTrace();
                    }
                case "menu":
                    if(action.equalsIgnoreCase("menu")){
                        String[] menuItems = data.split(",\\s*"); // Split chuỗi dựa trên dấu phẩy và khoảng trắng sau dấu phẩy
                        ArrayList<String> menuList = new ArrayList<>();
                        for (String menuItem : menuItems) {
                            menuList.add(menuItem.trim()); // Thêm mục menu vào ArrayList và loại bỏ khoảng trắng ở hai đầu
                        }
                        if(menuItems.length<2){
                            String firstMenuItem = menuItems[0].trim();
                            // Tìm và click vào phần tử chỉ định trên trang web (ở đây dùng xpath làm ví dụ)
                            try {
                                WebElement menuItemElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='#'][contains(.,'" + firstMenuItem + "')]")));
                                Log.info("Click vào menu lv1");
                                menuItemElement.click();
                            } catch (NoSuchElementException e) {
                                System.out.println("Không tìm thấy phần tử menu: " + e.getMessage());
                            }
                        }
                        if (menuItems.length >= 2) {
                            // Lấy phần tử đầu tiên từ mảng menuItems để click vào phần tử chỉ định trên trang web
                            String firstMenuItem = menuItems[0].trim();
                            // Tìm và click vào phần tử chỉ định trên trang web (ở đây dùng xpath làm ví dụ)
                            try {
                                WebElement menuItemElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='#'][contains(.,'" + firstMenuItem + "')]")));
                                Log.info("Click vào menu lv1");
                                menuItemElement.click();
                            } catch (NoSuchElementException e) {
                                System.out.println("Không tìm thấy phần tử menu: " + e.getMessage());
                            }

                            // Lấy phần tử thứ hai từ mảng menuItems để click vào phần tử chỉ định trên trang web
                            String secondMenuItem = menuItems[1].trim();
                            try {
                                WebElement menuItemElementLv2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(.,'" + secondMenuItem + "')]")));
                                menuItemElementLv2.click();
                                Thread.sleep(3000);
                                Log.info("Click vào menu lv2");
                            } catch (NoSuchElementException e) {
                                System.out.println("Không tìm thấy phần tử menu lv2 : " + e.getMessage());
                            }
                            if (menuItems.length >= 3) {
                                String thirdMenuItem = menuItems[2].trim();
                                try {
                                    WebElement menuItemElementLv3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(thirdMenuItem)));
                                    menuItemElementLv3.click();
                                    Log.info("Click vào menu lv3");
                                    driver.switchTo().frame("tranFrame");
                                    WebElement titleDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nomall")));
                                    if (titleDisplayed.isDisplayed()) {
                                        String title = titleDisplayed.getText();
                                        Assert.assertEquals(title, thirdMenuItem);
                                        Log.info("Hiển thị : " + thirdMenuItem);
                                    } else {
                                        Log.error("Không hiển thị title");
                                    }
                                } catch (NoSuchElementException e) {
                                    System.out.println("Không tìm thấy phần tử menu lv3 : " + e.getMessage());
                                }
                            }
                        }
                    }
                    break;
                // Add more cases for other actions if needed
                default:
                    // Handle unknown action
                    break;
            }
        }
    }
}