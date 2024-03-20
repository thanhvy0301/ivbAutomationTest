//package seatech.ibv.factoryPages;
//
//import org.openqa.selenium.WebDriver;
//import seatech.common.constant.IbvConstant;
//public class PageFactory {
//    public static BasePage createPage(String pageType, WebDriver driver) {
//
//        switch (pageType.toLowerCase()) {
//            case IbvConstant.LOGIN:
//                return new LoginPage(driver);
//            case IbvConstant.TRANSACTION247PAGE:
//                return new Transaction247Page(driver);
//            case IbvConstant.TRANSACTIONINTERNALPAGE:
//                return new TransactionInternalPage(driver);
//            case IbvConstant.ACCEPTTRANSACTIONINTERNALPAGE:
//                return new AcceptTransactionInternalPage(driver);
//            case IbvConstant.ACCEPTTRANSACTION247PAGE:
//                return new AcceptTransaction247Page(driver);
//            // Add more cases for other page types if needed
//            default:
//                throw new IllegalArgumentException("Invalid page type: " + pageType);
//        }
//    }
//}
