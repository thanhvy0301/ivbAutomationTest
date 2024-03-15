package seatech.ibv.factoryPages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Transaction247Page extends  BasePage{
	public Transaction247Page(WebDriver driver) {
		super(driver);
	}
	@FindBy(css = "#nav li:nth-child(2)") // menu thứ 2( do lấy theo vị trí do tất cả menu ko có điểm nào khác biệt)
	private WebElement navTransaction;

	@FindBy(id="19_3")
	private WebElement transaction247Menu;

	@FindBy(id="source_account_list")
	private WebElement accountNo;
	
	@FindBy(xpath="//span[@id='select2-bankNo-container']")
	private WebElement selectReceiveBank;

	@FindBy(id="accSML")
	private WebElement inputReceiveAccount;

	@FindBy(id="amount")
	private WebElement inputAmount;
	
	@FindBy(id="comment")
	private WebElement inputContent;

	@FindBy(id="chkApp")
	private WebElement chkApp;

	@FindBy(id="submit1")
	private WebElement btnSubmit;

	@FindBy(xpath = "//input[@class='buttonYellowL24 awesome']")
	private WebElement btnSubmitContinues;

	//@FindBy(className = "button_bg")
	@FindBy(xpath = "(//input[contains(@type,'button')])[1]")
	private WebElement btnEndTransaction;
	
	@FindBy(css = ".top-links li:nth-child(3)")
	//@FindBy(xpath = "//a[@onclick='return lout();']")
	public WebElement btnLogout;
	
	@FindBy(xpath = "//input[contains(@class,'select2-search__field')]")
	public WebElement txbSearchBank;
	
	@FindBy(id = "OTP")
	public WebElement txbOtp;
	

	
	//Ngan hang TMCP Dong A HCM (DAB)
	public void clickExpandMenu(){
		navTransaction.click();
	}
	public void clickTransactionMenu(){
		transaction247Menu.click();        ;
	}
	public void choosenAccountList(String valueAccountList) {
		Select select = new Select(accountNo);
		select.selectByValue(valueAccountList);
	}
	public void chooseReceiveBank(String valueReceiveBank) throws InterruptedException{
		selectReceiveBank.click();
		Thread.sleep(2000);
		txbSearchBank.sendKeys(valueReceiveBank);
		keyEnter();
	}
	public void keyEnter() {
		Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER);
        action.sendKeys(Keys.TAB);
        action.perform();
	}
	public void enterReceiveAccount(String receiveAccount){
		inputReceiveAccount.sendKeys(receiveAccount);
	}
	public void enterAmount(String amount){
		inputAmount.sendKeys(amount);
	}
	public void enterContent(String content){
		inputContent.sendKeys(content);
	}
	public void checkCheckBox(){
		chkApp.click();
	}
	public void enterBtnSubmit(){
		btnSubmit.click();
	}
	public void enterOTP(String otp) {
		txbOtp.sendKeys(otp);
	}
	public void changeIframe(){
		//        WebElement iframe = driver.findElement(By.cssSelector("body div .container .panel-default #MainForm .row-st #Page_right #tranFrame"));
		WebElement iframe = driver.findElement(By.id("tranFrame"));

		driver.switchTo().frame(iframe);
	}
	public void createTransaction247(
			String valueAccountList,
			String valueReceiveBank,
			String receiveAccount,
			String amount,
			String content
//			String otp
			) throws InterruptedException{
		clickTransactionMenu();
		Thread.sleep(3000);
		changeIframe();
		choosenAccountList(valueAccountList);
		chooseReceiveBank(valueReceiveBank);
		enterReceiveAccount(receiveAccount);
		enterAmount(amount);
		enterContent(content);
		checkCheckBox();
		Thread.sleep(2000);
		enterBtnSubmit();		
	}

	public void continuesCreateTransaction(){
		btnSubmitContinues.click();
	}

	public void endTransaction(){
		btnEndTransaction.click();
	}

	public void enterLogout(){
		driver.switchTo().parentFrame();
		btnLogout.click();
		driver.switchTo().alert().accept();
	}
	
}
