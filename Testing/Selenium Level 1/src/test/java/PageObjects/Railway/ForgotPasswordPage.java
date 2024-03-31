package PageObjects.Railway;
import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
public class ForgotPasswordPage extends GeneralPage {
    private final By _txtEmail = By.xpath("//input[@id='username']");
    private final By _btnForgotPassword = By.xpath("//input[@value='ForgotPassword']");
    private final By _IblForgotPasswordErrorMsg = By.xpath("//p[@class='message error LoginForm']");
    public WebElement getTxtEmail()
    {
        return Constant.WEBDRIVER.findElement(_txtEmail);
    }
    public WebElement getBtnForgotPassword(){
        return Constant.WEBDRIVER.findElement(_btnForgotPassword);
    }
    public LoginPage forgotPasswordPage(String email)
    {
        this.getTxtEmail().sendKeys(email);
        this.getBtnForgotPassword().click();
        getTabForgotPassword().submit();
        return new LoginPage();
    }

}
