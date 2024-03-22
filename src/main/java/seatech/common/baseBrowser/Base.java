package seatech.common.baseBrowser;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {

        public WebDriver driver;
        public Properties prop;
        //Hàm lựa chọn driver để run theo key browser trong file config
        public WebDriver init_driver(String browserName){
            if(browserName.equals("chrome")){//
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            } else if(browserName.equals("firefox")){
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            }
            return driver;
        }
        public Properties init_properties(){
            prop = new Properties();
            try {
                String projectPath = System.getProperty("user.dir");
                FileInputStream ip = new FileInputStream(projectPath+"\\src\\main\\resources\\config.properties");
                prop.load(ip);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return prop;
        }
    }
