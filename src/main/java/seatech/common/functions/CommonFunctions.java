package seatech.common.functions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonFunctions {
    private WebDriver driver;
    WebElement webElement;
    public CommonFunctions(WebDriver driver) {
        this.driver = driver; // Gán giá trị cho biến driver
        PageFactory.initElements(driver, this); // Khởi tạo các trường @FindBy
    }
    public void iframe(String xpathFrame){
        webElement.findElement(By.xpath(xpathFrame));
        driver.switchTo().frame(xpathFrame);
    }

    public void waitVisible(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }
    //span[contains(text(),'Xin lỗi')]

}
