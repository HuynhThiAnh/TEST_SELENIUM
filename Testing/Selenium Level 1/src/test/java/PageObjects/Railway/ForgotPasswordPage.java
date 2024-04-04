
package PageObjects.Railway;
import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
public class ForgotPasswordPage extends GeneralPage {
    private final By _txtEmail = By.xpath("//input[@id='email']");
    private final By _btnForgotPassword = By.xpath("//*[@id='content']/ul/li[3]/a]");
    private final By _btnSendInstructors = By.xpath("//input[@value='Send Instructions']");
    public WebElement getTxtEmail()
    {
        return Constant.WEBDRIVER.findElement(_txtEmail);
    }
    public WebElement getBtnForgotPassword(){
        return Constant.WEBDRIVER.findElement(_btnForgotPassword);
    }
    public WebElement getBtnSendInstructors(){
        return Constant.WEBDRIVER.findElement(_btnSendInstructors);
    }
    public LoginPage forgotPasswordPage(String email)
    {
        this.getTxtEmail().sendKeys(email);
        this.getBtnForgotPassword().click();
        getTabForgotPassword().submit();
        return new LoginPage();
    }

}
