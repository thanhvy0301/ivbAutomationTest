//package seatech.uiTest.ibv.test;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.ITestNGMethod;
//import org.testng.ITestResult;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.BeforeTest;
//import seatech.common.constant.IbvConstant;
//import seatech.ibv.factoryPages.BasePage;
//import seatech.ibv.factoryPages.PageFactory;
//
//import java.sql.SQLException;
//import java.util.Properties;
//
//import static java.sql.DriverManager.getDriver;
//
//
//public class BaseTest {
//    public WebDriver driver;
//    public BasePage currentPage;
////    ReadProperties readProperties;
//    @BeforeTest
//    public void setUp() {
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//       //currentPage = PageFactory.createPage(IbvConstant.LOGIN, driver);
//    }
//
//    @AfterMethod(alwaysRun = true)
//    public void captureScreenshotOnFailure(ITestResult result) {
//        ITestNGMethod method = result.getMethod();
//
//        if (ITestResult.FAILURE == result.getStatus()) {
//            currentPage.captureScreenshot(
//                    String.format(
//                            "%s_%s",
//                            method.getMethodName(),
//                            method.getParameterInvocationCount()), method.getRealClass().getSimpleName());
//        }
//    }
//    @AfterTest(alwaysRun = true)
//    public void tearDown() {
//        driver.quit();
//    }
//}
