package PageObjects.Railway;
import  org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import Common.Constant.Constant;
public class GeneralPage{
    //Locators
    private final By tabLogin = By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']");
    private final By tabLogout = By.xpath("//div[@id='menu']//a[@href='/Account/Logout']");
    private final By IblWelcomeMessage = By.xpath("//div[@class='account']/strong");
    private final By tabRegister = By.xpath("//div[@id='menu']//a[@href='/Account/Register.cshtml']");
    public final By tabChangePassword = By.xpath("//div[@id='menu']//a[@href='/Account/ChangePassword.cshtml']");
    public  final By tabForgotPassword = By.xpath("//div[@id='menu']//a[@href='/Account/ForgotPassword.cshtml']");
    //Elements
    protected WebElement getTabLogin(){
        return Constant.WEBDRIVER.findElement(tabLogin);

    }
    protected WebElement getTabRegister(){
        return Constant.WEBDRIVER.findElement(tabRegister);
    }
    protected WebElement getTabChangePassword(){return Constant.WEBDRIVER.findElement(tabChangePassword);}
    protected WebElement getTabForgotPassword(){return Constant.WEBDRIVER.findElement(tabForgotPassword);}
    protected WebElement getTabLogout(){
        return Constant.WEBDRIVER.findElement(tabLogout);
    }
    protected WebElement getLblWelcomeMessage(){
        return Constant.WEBDRIVER.findElement(IblWelcomeMessage);
    }
    //Methods
    public String getWelcomeMessage()
    {
        return this.getLblWelcomeMessage().getText();
    }
    public LoginPage gotoLoginPage()
    {
        this.getTabLogin().click();
        return new LoginPage();
    }
    public  RegisterPage gotoRegister(){
        this.getTabRegister().click();
        return new RegisterPage();
    }
    public ChangePasswordPage gotoChangePasswordPage(){
        this.getTabChangePassword().click();
        return new ChangePasswordPage();
    }

}

