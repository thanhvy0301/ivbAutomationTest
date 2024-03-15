//package seatech.common.baseBrowser;
//
//import org.openqa.selenium.PageLoadStrategy;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.chromium.ChromiumDriver;
//import org.openqa.selenium.chromium.ChromiumOptions;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.edge.EdgeOptions;
//import seatech.common.config.ConfigurationManager;
//
//public class BrowserDriver {
//
//    public static <T extends ChromiumDriver> T getDriver(String driverName){
//        if(driverName.equals("chrome")){
//            ChromeDriver driver = new ChromeDriver();
//            return (T) driver;
//        }
//        else if(driverName.equals("edge")){
//            System.out.println(EdgeOptions.class.getName());
//            EdgeDriver driver = new EdgeDriver();
//            return (T) driver;
//        }
//        return  null;
//    }
//    public static <T extends ChromiumOptions<T>> T getOption(String driverName){
//
//        if(driverName.equals("chrome")){
//            driverName
//
//        }
//        else if(driverName.equals("edge")) {
//            EdgeOptions options = new EdgeOptions();
//            return (T) options;
//        }
//
//
//        return null;
//
//    }
//
//}
