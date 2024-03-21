package seatech.common.functions;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class CaptureHelpers {
    static WebDriver driver;
    public static void captureScreenshot(ITestResult result) {

        if (ITestResult.FAILURE == result.getStatus()) {
            try {

                TakesScreenshot ts = (TakesScreenshot) driver;
                // Gọi hàm capture screenshot - getScreenshotAs
                File source = ts.getScreenshotAs(OutputType.FILE);
                //Kiểm tra folder tồn tại. Nêu không thì tạo mới folder
                File theDir = new File("./Screenshots/");
                if (!theDir.exists()) {
                    theDir.mkdirs();
                }
                // result.getName() lấy tên của test case xong gán cho tên File chụp màn hình
                FileHandler.copy(source, new File("./Screenshots/" + result.getName() + ".png"));
                System.out.println("Đã chụp màn hình: " + result.getName());
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        }
    }
}
