package seatech.common.baseBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

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
                FileInputStream ip = new FileInputStream("C:\\Users\\Admin\\Desktop\\automation-test-master\\src\\main\\resources\\config.properties");
                prop.load(ip);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return prop;
        }
    }
