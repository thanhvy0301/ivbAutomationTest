package seatech.ibv.factoryPages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AcceptTransactionInternalPage extends BasePage {
    public AcceptTransactionInternalPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "moduleTree_1_switch")
    private WebElement enterExpandButtonTransaction;
    @FindBy(id = "moduleTree_2_a")
    private WebElement expandTransInternal;

    @FindBy(css = "#data_content tbody tr:nth-child(1) [name=\"no\"]")
    private WebElement radioButton;
    @FindBy(name="allCode")
    private WebElement selectAuthenMethod;
    @FindBy(name="autheticationDevice")
    private WebElement selectAuthenDevice;

    @FindBy(css="#currAca span:nth-child(1) input")
    private WebElement enterAcceptBtn;

    @FindBy(name="_otp")
    private WebElement inputOTP;
    @FindBy(id="submit1")
    private WebElement btnContinues;
    public void changeIframeLeft(){
        WebElement iframe = driver.findElement(By.id("menuApproveFrame"));
        driver.switchTo().frame(iframe);
    }
    public void changeIframeRight(){
        WebElement iframe = driver.findElement(By.id("tranFrame"));
        driver.switchTo().frame(iframe);
    }

    public void chooseAuthenMethod(String methodAuthen){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", selectAuthenMethod);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.xpath("//select[@id='allCode']/option[@value='" + methodAuthen + "']"), methodAuthen));
    }

    public void chooseAuthenDevice(String valueDeviceAuthen){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", selectAuthenDevice);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.xpath("//select[@id='autheticationDevice']/option[@value='" + valueDeviceAuthen + "']"), valueDeviceAuthen));
    }

    public void chooseTransForAccept(){
        radioButton.click();
    }
    public void changeToParentFrame(){
        driver.switchTo().parentFrame();
    }
    public void enterOtp(String otp){
        inputOTP.sendKeys(otp);
    }
    public void clickExpandInternal(){
        // action: sử dụng khi có 1 lớp khác chồng lên lớp muốn bấm ví dụ thẻ li chặn bên ngoài
        // => selenium ko thể đi vào bên trong đưuọc ( element click intercepted)
        // dùng Action để đi vào element muốn bam rồi mới bấm
        Actions actions = new Actions(driver);
        actions.moveToElement(expandTransInternal).click().perform();
    }
    public void clickBtnSubmit(){
        // di chuyển viewport xuống đến khi thấy nút bấm
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", enterAcceptBtn);
        // viewport của nút duyệt nằm ngoài màn hình nên cần đợi cho đến khi nhìn thấy rồi mới bấm
        WebElement myElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(enterAcceptBtn));
        myElement.click();
    }
    public void acceptTransInternal(String methodAuthen, String valueDeviceAuthen){

        changeIframeLeft();
        enterExpandButtonTransaction.click();
        clickExpandInternal();
        changeToParentFrame();
        changeIframeRight();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        chooseTransForAccept();
        chooseAuthenMethod(methodAuthen);
        chooseAuthenDevice(valueDeviceAuthen);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        clickBtnSubmit();
        
    }
    public void continuesAcceptTransInternal(String otp){
        enterOtp(otp);
        btnContinues.click();
    }

}