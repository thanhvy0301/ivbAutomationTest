package seatech.ibv.factoryPages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import seatech.common.constant.IbvConstant;
import seatech.common.functions.CommonFunctions;

public class LoginPage extends BasePage{
    CommonFunctions commonFunctions = new CommonFunctions(driver);
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(name = "t_userName")
    private WebElement inputUsername;
    @FindBy(name = "t_password")
    private WebElement inputPassword;
    @FindBy(className = "btn-default")
    private WebElement loginButton;
    @FindBy(name="_verifyCode")
    private WebElement inputCapcha;
    
//    @FindBy(name="button")
//    public WebElement btnNoitice;

    @FindBy(xpath = "//input[@value='Đồng ý']")
    private WebElement acceptOutOtherSession;
    @FindBy(linkText = "Đăng nhập tài khoản khác")
    private WebElement logigOtherAccount;
    
    public void changeToParentFrame(){
        driver.switchTo().parentFrame();
    }
    public void enterUsername(String username) {
        inputUsername.sendKeys(username);
    }
    public void enterPassword(String password) {
        inputPassword.sendKeys(password);
    }
    public void enterCapcha(String capcha) {
        inputCapcha.sendKeys(capcha);
    }
    public void loginClick() {
        loginButton.click();
    }
    public void removeChar(){
        inputUsername.clear();
        inputPassword.clear();
        inputCapcha.clear();

    }
    public <T extends BasePage> T loginMakerPageAndRedirectMakerPage(String username, String password, String capcha) throws InterruptedException{
        removeChar();
        enterUsername(username);
        enterPassword(password);
        enterCapcha(capcha);
        loginClick();
        commonFunctions.waitVisible(acceptOutOtherSession);
//        acceptOutOtherSession.click();
        return (T) PageFactory.createPage(IbvConstant.TRANSACTION247PAGE, driver);
    }
        public void loginPageSuccess(String username, String password, String capcha) throws InterruptedException{
        removeChar();
        System.out.println(username + " " + password+" " + capcha);
        enterUsername(username);
        enterPassword(password);
        enterCapcha(capcha);
        loginClick();
    }
    public void ClickBtnOutOtherSession(){
        acceptOutOtherSession.click();
    }
    public void enterLoginOtherAccount(){
        changeToParentFrame();
        logigOtherAccount.click();
    }
    /*public <T extends BasePage> T loginCheckerPageAndRedirectCheckerPage(String username, String password) throws InterruptedException{
        try {
            enterLoginOtherAccount();
        }catch (Exception e){
            ;
        }
        enterUsername(username);
        enterPassword(password);
        loginClick();
        Thread.sleep(3000);
        ClickBtnOutOtherSession();
        return (T) PageFactory.createPage(IbvConstant.ACCEPTTRANSACTION247PAGE, driver);
    }*/



}
