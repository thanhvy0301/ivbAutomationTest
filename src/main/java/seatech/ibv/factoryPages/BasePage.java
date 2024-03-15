package seatech.ibv.factoryPages;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void captureScreenshot(String fileName, String foldername) {
        Shutterbug.shootPage(driver).withName(fileName).save("src/test/java/seatech/uiTest/ibv/ErrorScreen/" + foldername);
    }
}
