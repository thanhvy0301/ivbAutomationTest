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

public class Base {

        public WebDriver driver;
        public Properties prop;
        public WebDriver init_driver(String browserName){
            if(browserName.equals("chrome")){//
                driver = new ChromeDriver();
                driver.manage().window().maximize();
            } else if(browserName.equals("firefox")){
//                System.setProperty("webdriver.gecko.driver", "/Users/NaveenKhunteta/Downloads/geckodriver");
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
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
