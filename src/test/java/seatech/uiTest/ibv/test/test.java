package seatech.uiTest.ibv.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class test {
    WebDriver driver;
    @BeforeTest
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://192.168.1.232:9999/IBSCorporateIDVN/Request");
        driver.findElement(By.id("t_userName")).sendKeys("1001470_maker10");
        driver.findElement(By.id("t_password")).sendKeys("111111");
        driver.findElement(By.id("vcode")).sendKeys("12345");
        driver.findElement(By.xpath("//button[contains(.,'Đăng nhập')]")).click();
    }
    @Test
    public void test() throws InterruptedException {
        driver.findElement(By.xpath("//input[contains(@name,'button')]")).click();
        WebElement frameElement = driver.findElement(By.id("infoFrame"));
        driver.switchTo().frame(frameElement);

        driver.findElement(By.xpath("//span[contains(.,'Chuyển Khoản Trong Hệ Thống')]")).click();
        Thread.sleep(2000);
        driver.switchTo().defaultContent();

        WebElement frameElement_2 = driver.findElement(By.id("tranFrame"));
        driver.switchTo().frame(frameElement_2);

        driver.findElement(By.xpath("//label[contains(.,'Cùng chủ tài khoản')]")).click();

        //table[3]/tbody[1]/tr[2]/td[2]/span[1][@hidden='true']
        Select sl = new Select(driver.findElement(By.id("selectAccountNo")));
        sl.selectByValue("1001470-001");
        driver.findElement(By.id("submit1")).click();
        List<WebElement> hiddenRows = driver.findElements(By.xpath("//table[1]/tbody[1]/tr[1]/td[1]/table[3]"));
        for (WebElement row : hiddenRows) {
            String textRow = row.getText();
            System.out.println(textRow);
            //Assert.assertEquals(textRow,"abc");
        }
    }
}
