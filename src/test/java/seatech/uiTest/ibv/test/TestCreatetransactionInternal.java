//package seatech.uiTest.ibv.test;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//import seatech.common.constant.IbvConstant;
//import seatech.ibv.data.CreateTransactionInternalData;
//import seatech.ibv.factoryPages.AcceptTransactionInternalPage;
//import seatech.ibv.factoryPages.LoginPage;
//import seatech.ibv.factoryPages.TransactionInternalPage;
//
//import java.lang.reflect.Method;
//import java.time.Duration;
//
//import static org.junit.Assert.*;
//import static seatech.uiTest.util.DataProvider.processCsv;
//
//public class TestCreatetransactionInternal extends BaseTest {
//
//	@DataProvider(name = "createTransactionInternal")
//	public Object[][] getCreateTransactionInternal(final Method testMethod) {
//		String testCaseId = testMethod.getAnnotation(Test.class).testName();
//		return processCsv(CreateTransactionInternalData.class, IbvConstant.PATHCSVTRANSACTIONINTERNAL, testCaseId);
//	}
//
//	@Test(
//			testName = "TC-1",
//			dataProvider = "createTransactionInternal"
//			)
//	public void TestCreateTransactionInternalSuccess(final CreateTransactionInternalData createTransactionInternalData) {
//		try {
//			TransactionInternalPage transactionInternalPage;
//			AcceptTransactionInternalPage acceptTransactionInternalPage;
//
//			transactionInternalPage = ((LoginPage) currentPage).loginMakerPageAndRedirectMakerPage(
//					createTransactionInternalData.getUsernameMaker(),
//					createTransactionInternalData.getPasswordMaker(),
//					createTransactionInternalData.getCapcha());
//
//			try {
//				((LoginPage) currentPage).ClickBtnOutOtherSession();
//			} catch (Exception e) {
//				;
//			}
//			transactionInternalPage.clickExpandMenu();
//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//			transactionInternalPage.clickTransactionMenu();
//			// vì trang có 2 frame khác nhau nên cần chuyển iframe
//			// sau khi chuyển iframe thì khi chuyển qua màn hình khác nó vẫn ở frame đó nên ko cần chuyển ifram lại
//			transactionInternalPage.createTransaction(
//					createTransactionInternalData.getType(),
//					createTransactionInternalData.getAccountNumber(),
//					createTransactionInternalData.getReceiveAccount(),
//					createTransactionInternalData.getAmount(),
//					createTransactionInternalData.getFeeTransaction(),
//					createTransactionInternalData.getContent()
//					);
//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//			transactionInternalPage.continuesCreateTransaction();
//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//			transactionInternalPage.endTransaction();
//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//			transactionInternalPage.enterLogout();
//
//
////			acceptTransactionInternalPage = ((LoginPage) currentPage).loginCheckerPageAndRedirectCheckerPage(
////					createTransactionInternalData.getUsernameChecker(),
////					createTransactionInternalData.getPasswordChecker());
////			try {
////				((LoginPage) currentPage).ClickBtnOutOtherSession();
////			} catch (Exception e) {
////				;
////			}
////			acceptTransactionInternalPage.acceptTransInternal(createTransactionInternalData.getTyperAuthen(), createTransactionInternalData.getAuthenDevice());
////			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
////			acceptTransactionInternalPage.continuesAcceptTransInternal(createTransactionInternalData.getOtp());
////			assertEquals(driver.findElement(By.name("dse_processorState")).getAttribute("value"), createTransactionInternalData.getCondition());
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//
//}
//
