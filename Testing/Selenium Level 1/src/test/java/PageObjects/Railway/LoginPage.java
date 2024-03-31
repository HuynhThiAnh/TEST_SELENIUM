package PageObjects.Railway;
import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends GeneralPage {
    //Locators
    private final By _txtUsername = By.xpath("//input[@id='username']");
    private final By _txtPassword = By.xpath("//input[@id='password']");
    private final By _btnLogin = By.xpath("//input[@value='login']");
    private final By _IblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");

    //Elements
    public WebElement getTxtUsername()
    {
        return Constant.WEBDRIVER.findElement(_txtUsername);
    }
    public WebElement getTxtPassword()
    {
        return Constant.WEBDRIVER.findElement(_txtPassword);
    }
    public WebElement getBtnLogin()
    {
        return Constant.WEBDRIVER.findElement(_btnLogin);
    }
    public WebElement getLblLoginErrorMsg()
    {
        return Constant.WEBDRIVER.findElement(_IblLoginErrorMsg);
    }

    //methods
    public HomePage login(String username, String password)
    {
        //submit
        this.getTxtUsername().sendKeys(username);
        this.getTxtPassword().sendKeys(password);
        this.getBtnLogin().click();
        //Land on Home page
        return new HomePage();
    }

}
