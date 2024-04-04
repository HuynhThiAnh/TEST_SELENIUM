package PageObjects.Railway;
import  org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import Common.Constant.Constant;
public class GeneralPage{
    //Locators
    private final By tabLogin = By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']");
    private final By tabLogout = By.xpath("//div[@id='menu']//a[@href='/Account/Logout']");
    private final By IblWelcomeMessage = By.xpath("//div[@class='account']/strong");
    private final By tabBookTicket = By.xpath("//div[@id='menu']//a[@href='/Page/BookTicketPage.cshtml']");
    private final By tabRegister = By.xpath("//div[@id='menu']//a[@href='/Account/Register.cshtml']");
    public final By tabChangePassword = By.xpath("//div[@id='menu']//a[@href='/Account/ChangePassword.cshtml']");
    public  final By tabForgotPassword = By.xpath("//div[@id='content']/ul/li[3]/a");
    public final By tabMyTicket = By.xpath("//div[@id='menu']//a[@href='/Page/ManageTicket.cshtml']");

    public void clickBookTicket() {
        Constant.WEBDRIVER.findElement(tabBookTicket).click();
    }
    private final By _MyTicket = By.xpath("//div[@id='menu']//a[@href='/Page/ManageTicket.cshtml']");
    public void clickMyTicket() {
        Constant.WEBDRIVER.findElement(_MyTicket).click();
    }
    private final By _ChangePassword = By.xpath("//div[@id='menu']//a[@href='/Account/ChangePassword.cshtml']");
    private final By _ForgotPassword = By.xpath("//div[@id='menu']//a[@href='/Account/ForgotPassword.cshtml']");
    public void clickChangePassword() {
        Constant.WEBDRIVER.findElement(_ChangePassword).click();
    }
    //Elements
    protected WebElement getTabLogin(){
        return Constant.WEBDRIVER.findElement(tabLogin);

    }
    protected WebElement getTabRegister(){
        return Constant.WEBDRIVER.findElement(tabRegister);
    }
    protected WebElement getTabChangePassword(){return Constant.WEBDRIVER.findElement(tabChangePassword);}
    protected WebElement getTabForgotPassword(){return Constant.WEBDRIVER.findElement(tabForgotPassword);}
    public WebElement getMyTicket(){
        return Constant.WEBDRIVER.findElement(_MyTicket);
    }
    public WebElement getChangePassword(){
        return Constant.WEBDRIVER.findElement(_ChangePassword);
    }
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
    public ForgotPasswordPage gotoForgotPasswordPage(){
        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        this.getTabForgotPassword().click();
        return new ForgotPasswordPage();
    }
    // TC15
    public final By tabTrainTimetable = By.xpath("//*[@id='menu']/ul/li[4]/a");

    protected WebElement getTabTrainTimetable(){
        return Constant.WEBDRIVER.findElement(tabTrainTimetable);
    }
    public TimeTablePage gotoTrainTimetablePage(){
        this.getTabTrainTimetable().click();
        return new TimeTablePage();
    }
    protected WebElement getTabBookTicket(){
        return Constant.WEBDRIVER.findElement(tabBookTicket);
    }
    public BookTicketPage gotoBookTicketPage(){
        this.getTabBookTicket().click();
        return new BookTicketPage();
    }
    protected WebElement getTabMyTicket(){
        return Constant.WEBDRIVER.findElement(tabMyTicket);
    }
    public MyTicketPage gotoMyTicketPage(){
        this.getTabMyTicket().click();
        return new MyTicketPage();
    }
}


