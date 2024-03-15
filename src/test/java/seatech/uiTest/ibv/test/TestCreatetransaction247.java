//package seatech.uiTest.ibv.test;
//
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//import seatech.common.constant.IbvConstant;
//import seatech.ibv.data.BaseData;
//import seatech.ibv.data.CreateTransaction247Data;
//import seatech.ibv.data.CreateTransactionInternalData;
//import seatech.ibv.data.LoginData;
//import seatech.ibv.factoryPages.AcceptTransaction247Page;
//import seatech.ibv.factoryPages.LoginPage;
//import seatech.ibv.factoryPages.Transaction247Page;
//import seatech.uiTest.util.HybridDriven;
//import seatech.uiTest.util.ReadExcelFile;
//import seatech.uiTest.util.ReadProperties;
//
//import java.io.IOException;
//import java.lang.reflect.Method;
//import java.time.Duration;
//import java.util.Properties;
////
////import static seatech.uiTest.util.DataProvider.processCsv;
//
//public class TestCreatetransaction247 {
//
///*	@DataProvider(name = "login")
//	public Object[][] getLogin(final Method testMethod) {
//		String testCaseId = testMethod.getAnnotation(Test.class).testName();
//		return processCsv(LoginData.class, IbvConstant.LOGINPATH, testCaseId);
//	}*/
//
//@Test
//public void testLogin() throws Exception {
//	// TODO Auto-generated method stub
//	WebDriver webdriver = new ChromeDriver();
//	ReadExcelFile file = new ReadExcelFile();
//	ReadProperties readProperties = new ReadProperties();
//	Properties allObjects = readProperties.getObjectRepository();
//	HybridDriven operation = new HybridDriven(webdriver);
////Read keyword sheet
//	Sheet sheet = file.readExcel("C:\\Users\\Admin\\Desktop\\automation-test-master\\src\\test\\java\\seatech\\uiTest\\ibv\\testcase\\TestCase.xlsx" , "KeywordFramework");
////Find number of rows in excel file
//	int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
//	//Create a loop over all the rows of excel file to read it
//	for (int i = 1; i < rowCount+1; i++) {
//		//Loop over all the rows
//		Row row = sheet.getRow(i);
//		//Check if the first cell contain a value, if yes, That means it is the new testcase name
//		if(row.getCell(0).toString().length()==0){
//			//Print testcase detail on console
//			System.out.println(row.getCell(1).toString()+"----"+ row.getCell(2).toString()+"----"+
//					row.getCell(3).toString()+"----"+ row.getCell(4).toString());
//			//Call perform function to perform operation on UI
//			operation.perform(allObjects, row.getCell(1).toString(), row.getCell(2).toString(),
//					row.getCell(3).toString(), row.getCell(4).toString());
//		}
//		else{
//			//Print the new testcase name when it started
//			System.out.println("New Testcase->"+row.getCell(0).toString() +" Started");
//		}
//	}
//}
///*	@Test(
//			dataProvider = "createTransaction247",
//			priority=0, enabled = false
//
//	)*/
//	/*public void TestCreateTransaction247Success_TC1(final CreateTransaction247Data createTransaction247Data) {
//		try {
//			Transaction247Page transaction247Page;
//			AcceptTransaction247Page acceptTransaction247Page;
//
//			transaction247Page = ((LoginPage) currentPage).loginMakerPageAndRedirectMakerPage(
//					createTransaction247Data.getUsernameMaker(),
//					createTransaction247Data.getPasswordMaker(),
//					createTransaction247Data.getCapcha());
//
//			try {
//				((LoginPage) currentPage).ClickBtnOutOtherSession();
//			} catch (Exception e) {
//				;
//			}
//			transaction247Page.clickExpandMenu();
//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//			transaction247Page.clickTransactionMenu();
//			// vì trang có 2 frame khác nhau nên cần chuyển iframe
//			// sau khi chuyển iframe thì khi chuyển qua màn hình khác nó vẫn ở frame đó nên ko cần chuyển ifram lại
//			transaction247Page.createTransaction247(
//					createTransaction247Data.getAccountNumber(),
//					createTransaction247Data.getReceiveBank(),
//					createTransaction247Data.getReceiveAccount(),
//					createTransaction247Data.getAmount(),
//					createTransaction247Data.getContent()
//			);
//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//			transaction247Page.continuesCreateTransaction();
//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//			transaction247Page.endTransaction();
//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//			transaction247Page.enterLogout();
//
//			*//*acceptTransaction247Page = ((LoginPage) currentPage).loginCheckerPageAndRedirectCheckerPage(
//					createTransaction247Data.getUsernameChecker(),
//					createTransaction247Data.getPasswordChecker(),
//					createTransaction247Data.getCapcha());
//			try {
//				((LoginPage) currentPage).ClickBtnOutOtherSession();
//			} catch (Exception e) {
//				;
//			}
//			acceptTransaction247Page.acceptTrans247(createTransaction247Data.getTyperAuthen(), createTransaction247Data.getAuthenDevice());
//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//			acceptTransaction247Page.continuesAcceptTrans247(createTransaction247Data.getOtp());
//			assertEquals(driver.findElement(By.name("dse_processorState")).getAttribute("value"), createTransaction247Data.getCondition());*//*
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}*/
//	/*@Test(
//			testName = "TC-2",
//			dataProvider = "createTransaction247",
//			priority=1,
//			enabled = false
//	)
//	public void TestCreateTransaction247Success_TC2(final CreateTransaction247Data createTransaction247Data) {
//		try {
//			Transaction247Page transaction247Page;
//			AcceptTransaction247Page acceptTransaction247Page;
//
//			transaction247Page = ((LoginPage) currentPage).loginMakerPageAndRedirectMakerPage(
//					createTransaction247Data.getUsernameMaker(),
//					createTransaction247Data.getPasswordMaker(),
//					createTransaction247Data.getCapcha());
//
//			try {
//				((LoginPage) currentPage).ClickBtnOutOtherSession();
//			} catch (Exception e) {
//				;
//			}
//			transaction247Page.clickExpandMenu();
//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//			transaction247Page.clickTransactionMenu();
//			// vì trang có 2 frame khác nhau nên cần chuyển iframe
//			// sau khi chuyển iframe thì khi chuyển qua màn hình khác nó vẫn ở frame đó nên ko cần chuyển ifram lại
//			transaction247Page.createTransaction247(
//					createTransaction247Data.getAccountNumber(),
//					createTransaction247Data.getReceiveBank(),
//					createTransaction247Data.getReceiveAccount(),
//					createTransaction247Data.getAmount(),
//					createTransaction247Data.getContent()
//			);
//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//			transaction247Page.continuesCreateTransaction();
//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//			transaction247Page.endTransaction();
//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//			transaction247Page.enterLogout();
//
//			*//*acceptTransaction247Page = ((LoginPage) currentPage).loginCheckerPageAndRedirectCheckerPage(
//					createTransaction247Data.getUsernameChecker(),
//					createTransaction247Data.getPasswordChecker(),
//					createTransaction247Data.getCapcha());
//			try {
//				((LoginPage) currentPage).ClickBtnOutOtherSession();
//			} catch (Exception e) {
//				;
//			}
//			acceptTransaction247Page.acceptTrans247(createTransaction247Data.getTyperAuthen(), createTransaction247Data.getAuthenDevice());
//			acceptTransaction247Page.warning();
//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//			//			acceptTransaction247Page.continuesAcceptTrans247(createTransaction247Data.getOtp());
//			//assertEquals(driver.findElement(By.xpath("//form[1]/div[2]/div[1]/strong[1]")).getText(), createTransaction247Data.getCondition());
//			assertEquals(driver.findElement(By.name("dse_processorState")).getAttribute("value"), createTransaction247Data.getCondition());*//*
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//	@Test(
//			testName = "TC-3",
//			dataProvider = "createTransaction247",
//			priority=2, enabled = false
//	)
//	public void TestCreateTransaction247Success_TC3(final CreateTransaction247Data createTransaction247Data) {
//		try {
//			Transaction247Page transaction247Page;
//			AcceptTransaction247Page acceptTransaction247Page;
//
//			transaction247Page = ((LoginPage) currentPage).loginMakerPageAndRedirectMakerPage(
//					createTransaction247Data.getUsernameMaker(),
//					createTransaction247Data.getPasswordMaker(),
//					createTransaction247Data.getCapcha());
//
//			try {
//				((LoginPage) currentPage).ClickBtnOutOtherSession();
//			} catch (Exception e) {
//				;
//			}
//			transaction247Page.clickExpandMenu();
//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//			transaction247Page.clickTransactionMenu();
//			// vì trang có 2 frame khác nhau nên cần chuyển iframe
//			// sau khi chuyển iframe thì khi chuyển qua màn hình khác nó vẫn ở frame đó nên ko cần chuyển ifram lại
//			transaction247Page.createTransaction247(
//					createTransaction247Data.getAccountNumber(),
//					createTransaction247Data.getReceiveBank(),
//					createTransaction247Data.getReceiveAccount(),
//					createTransaction247Data.getAmount(),
//					createTransaction247Data.getContent()
//			);
//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//			transaction247Page.continuesCreateTransaction();
//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//			transaction247Page.endTransaction();
//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//			transaction247Page.enterLogout();
//
//			*//*acceptTransaction247Page = ((LoginPage) currentPage).loginCheckerPageAndRedirectCheckerPage(
//					createTransaction247Data.getUsernameChecker(),
//					createTransaction247Data.getPasswordChecker(),
//					createTransaction247Data.getCapcha());
//			try {
//				((LoginPage) currentPage).ClickBtnOutOtherSession();
//			} catch (Exception e) {
//				;
//			}
//			acceptTransaction247Page.acceptTrans247(createTransaction247Data.getTyperAuthen(), createTransaction247Data.getAuthenDevice());
//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//			acceptTransaction247Page.btnContinued();
//			//span[@id='errorNullotp']
//			assertEquals(driver.findElement(By.xpath("//span[@id='errorNullotp']")).getText(), "Vui lòng nhập mã OTP");
//			Thread.sleep(3000);*//*
//			//			acceptTransaction247Page.continuesAcceptTrans247(createTransaction247Data.getOtp());////div[@class='center']
//			//assertEquals(driver.findElement(By.xpath("//form[1]/div[2]/div[1]/strong[1]")).getText(), createTransaction247Data.getCondition());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}*/
//}
//
