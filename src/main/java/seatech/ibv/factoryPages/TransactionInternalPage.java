//package seatech.ibv.factoryPages;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.ui.Select;
//
//public class TransactionInternalPage extends  BasePage{
//    public TransactionInternalPage(WebDriver driver) {
//        super(driver);
//
//    }
//    @FindBy(css = "#nav li:nth-child(2)") // menu thứ 2( do lấy theo vị trí do tất cả menu ko có điểm nào khác biệt)
//    private WebElement navTransaction;
//
//    @FindBy(id="19_1")
//    private WebElement transactionInternalMenu;
//
//    @FindBy(id="transactionType")
//    private WebElement transactionType;
//
//    @FindBy(id="selectAccountNo")
//    private WebElement accountNo;
//
//    @FindBy(id="receiveAccount2")
//    private WebElement inputReceiveAccount2;
//
//    @FindBy(id="amount2")
//    private WebElement inputAmount2;
//
//    @FindBy(id="selectFeeTransaction")
//    private WebElement selectFeeTransaction;
//    @FindBy(id="textarea2")
//    private WebElement inputTextareaContent;
//
//    @FindBy(id="chkApp")
//    private WebElement chkApp;
//
//    @FindBy(id="submit2")
//    private WebElement btnSubmit;
//
//    @FindBy(id="submit1")
//    private WebElement btnSubmitContinues;
//
//    @FindBy(className = "button_bg")
//    private WebElement btnEndTransaction;
//    @FindBy(css = ".top-links li:nth-child(3)")
//    public WebElement btnLogout;
//
//    public void clickExpandMenu(){
//        navTransaction.click();
//    }
//    public void clickTransactionMenu(){
//        transactionInternalMenu.click();
//    }
//    public void choosenTransactionType(String valueTransactionType){
//        Select select = new Select(transactionType);
//        select.selectByValue(valueTransactionType);
//    }
//
//    public void chooseAccountNo(String valueAccountNo){
//        Select select = new Select(accountNo);
//        select.selectByValue(valueAccountNo);
//    }
//
//    public void enterReceiveAccount(String receiveAccount){
//        inputReceiveAccount2.sendKeys(receiveAccount);
//    }
//    public void enterAmount(String amount){
//        inputAmount2.sendKeys(amount);
//    }
//
//    public void chooseFeeTransaction(String valueFeeTransaction){
//        Select select = new Select(selectFeeTransaction);
//        select.selectByValue(valueFeeTransaction);
//    }
//
//    public void enterTextArea(String content){
//        inputTextareaContent.sendKeys(content);
//    }
//    public void checkCheckBox(){
//        chkApp.click();
//    }
//    public void enterBtnSubmit(){
//        btnSubmit.click();
//    }
//    public void changeIframe(){
////        WebElement iframe = driver.findElement(By.cssSelector("body div .container .panel-default #MainForm .row-st #Page_right #tranFrame"));
//        WebElement iframe = driver.findElement(By.id("tranFrame"));
//
//        driver.switchTo().frame(iframe);
//    }
//
//    public void createTransaction(
//            String valueTransactionType,
//            String valueAccountNo,
//            String receiveAccount,
//            String amount,
//            String valueFeeTransaction,
//            String content
//            ) throws InterruptedException{
//        clickTransactionMenu();
//        changeIframe();
//        choosenTransactionType(valueTransactionType);
//        chooseAccountNo(valueAccountNo);
//        enterReceiveAccount(receiveAccount);
//        enterAmount(amount);
//        chooseFeeTransaction(valueFeeTransaction);
//        enterTextArea(content);
//        Thread.sleep(3000);
//        checkCheckBox();
//        enterBtnSubmit();
//
//    }
//
//    public void continuesCreateTransaction(){
//        btnSubmitContinues.click();
//    }
//
//    public void endTransaction(){
//        btnEndTransaction.click();
//    }
//
//    public void enterLogout(){
//        driver.switchTo().parentFrame();
//        btnLogout.click();
//        driver.switchTo().alert().accept();
//    }
//
//
//
//
//}
