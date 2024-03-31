package PageObjects.Railway;
import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
public class RegisterPage extends GeneralPage{

    private final By _txtEmail =By.xpath("//input[@id='email']");
    private final By _txtPassword = By.xpath("//input[@id='password']");

    private final By _txtConfirmPassword =By.xpath("//input[@id='confirmPassword']");
    private final By _TxtPid =By.xpath("//input[@id='pid']");
    private final By _btnRegister = By.xpath("//input[@value='Register']");
    private final By _IblRegisterErrorMsg = By.xpath("//*[@id=\"content\"]/p[2]");
    public WebElement getTxtPassword()
    {
        return Constant.WEBDRIVER.findElement(_txtPassword);
    }
    public WebElement getTxtEmail() {return Constant.WEBDRIVER.findElement(_txtEmail);}
    public WebElement getTxtConfirmPassword(){
        return Constant.WEBDRIVER.findElement(_txtConfirmPassword);
    }
    public WebElement getTxtPid(){return Constant.WEBDRIVER.findElement(_TxtPid);}
    public WebElement getBtnRegister()
    {
        return Constant.WEBDRIVER.findElement(_btnRegister);
    }
    public WebElement getLblRegisterErrorMsg()
    {
        return Constant.WEBDRIVER.findElement(_IblRegisterErrorMsg);
    }
    public HomePage register(String email, String password, String ConfirmPassword, String Pid){
//        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
//        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        this.getTxtEmail().sendKeys(email);
        this.getTxtPassword().sendKeys(password);
        this.getTxtConfirmPassword().sendKeys(ConfirmPassword);
        this.getTxtPid().sendKeys(Pid);
        getBtnRegister().submit();
        return new HomePage();
    }
}
