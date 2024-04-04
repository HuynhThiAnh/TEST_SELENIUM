package Testcase.Railway;

import PageObjects.Railway.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import Common.Common.Utilities;
import Common.Constant.Constant;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginTest {
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("Pre-condition");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }
//        @AfterMethod
//    public void afterMethod(){
//        System.out.println("Post-condition");
//        Constant.WEBDRIVER.quit();
//    }
    @Test
    public void TC01(){
        System.out.println("User can log into Railway with valid username and password");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        String actualMsg = loginPage.login(Constant.USERNAME, Constant.PASSWORD).getWelcomeMessage();
        String expectedMsg = "Welcome " + Constant.USERNAME;
        Assert.assertEquals(actualMsg, expectedMsg, "Welcome message is not displayed as expected");
    }
    @Test
    public void TC02() {
        System.out.println("User can't login with blank 'Username' textbox");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login("", Constant.PASSWORD);
        String actualErrorMsg = loginPage.getLblLoginErrorMsg().getText();
        String expectedErrorMsg = "There was a problem with your login and/or errors exist in your form.";
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected");
    }
    @Test
    public void TC03(){
        System.out.println("User cannot log into Railway with invalid password ");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        String actualMsg = loginPage.login(Constant.USERNAME, Constant.PASSWORD).getWelcomeMessage();
        String expectedErrorMsg = "There was a problem with your login and/or errors exist in your form.";
        Assert.assertEquals(actualMsg, expectedErrorMsg,"Error message is not displayed as expected");

    }
    @Test
    public void TC04() {
        System.out.println("Login page displays when un-logged User clicks on 'Book ticket' tab");
        HomePage homePage = new HomePage();
        homePage.open();
        homePage.clickBookTicket();
        LoginPage loginPage = new LoginPage();
        Assert.assertTrue(loginPage.getBtnLogin().isDisplayed(), "Login page did not display when un-logged User clicked on 'Book ticket' tab");
    }
    @Test
    public void TC05() {
        System.out.println("System shows message when user enters wrong password several times");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        for (int i = 0; i < 4; i++) {
            loginPage.login(Constant.USERNAME, "invalidPassword");
        }
        loginPage.login(Constant.USERNAME, "invalidPassword");
        String actualErrorMsg = loginPage.getLblLoginErrorMsg().getText();
        String expectedErrorMsg = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected");
    }
    @Test
    public void TC06() {
        System.out.println("TC06-Additional pages display once user logged in");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);
        loginPage.clickMyTicket();
        loginPage.clickChangePassword();
        Assert.assertTrue(loginPage.getMyTicket().isDisplayed(), "My ticket tab is not displayed");
        Assert.assertTrue(loginPage.getChangePassword().isDisplayed(), "Change password tab is not displayed");
    }

    @Test
    public void TC07(){
        System.out.println("User can create new account");
        HomePage homePage = new HomePage();
        homePage.open();
        RegisterPage registerPage = homePage.gotoRegister();
        String actualMsg = registerPage.register(Constant.EMAIL, Constant.PASSWORD, Constant.CONFIRMPASSWORD, Constant.ID).getWelcomeMessage();
        String expectedMsg = "Thank you for registering your account";
        Assert.assertEquals(actualMsg, expectedMsg, "");
    }
    @Test
    public void TC08(){
        System.out.println("User can't login with an account hasn't been activated");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        loginPage.getTxtUsername().sendKeys("invaliduser@gmail.com");
        loginPage.getTxtPassword().sendKeys("invalid password");
        loginPage.getBtnLogin().click();

        String actualErrorMsg = loginPage.getLblLoginErrorMsg().getText();
        String expectedErrorMsg = "Invalid username or password. Please try again.";

        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected");
    }
    @Test
    public void TC09() {
        System.out.println("User can change password");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.getTxtUsername().sendKeys(Constant.USERNAME);
        loginPage.getTxtPassword().sendKeys(Constant.PASSWORD);
        loginPage.getBtnLogin().click();
        ChangePasswordPage changePasswordPage = homePage.gotoChangePasswordPage();
        changePasswordPage.getTxtCurrentPassword().sendKeys(Constant.PASSWORD);
        changePasswordPage.getTxtNewPassword().sendKeys("huynhanh");
        changePasswordPage.getTxtConfirmPassword().sendKeys("huynhanh");
        changePasswordPage.getBtnChangePassword().click();
        String actualSuccessMsg = changePasswordPage.getLblChangePasswordErrorMsg().getText();
        String expectedSuccessMsg = "Your password has been updated!";
        Assert.assertEquals(actualSuccessMsg, expectedSuccessMsg, "Success message is not displayed as expected");
    }
    @Test
    public void TC10(){
        System.out.println("User can't create account with 'Confirm password' is not the same with 'Password'");
        HomePage homePage = new HomePage();
        homePage.open();
        RegisterPage registerPage = homePage.gotoRegister();
        registerPage.getTxtEmail().sendKeys("huynanh270603@gmail.com");
        registerPage.getTxtPassword().sendKeys("huynhthianh");
        registerPage.getTxtConfirmPassword().sendKeys("huynhanh");
        registerPage.getTxtPid().sendKeys("123456900876");
        registerPage.getbtnRegister().submit();
        String actualMsg = registerPage.getLblRegisterErrorMsg().getText();
        String expectedMsg = "There're errors in the form. Please correct the errors and try again.";
        Assert.assertEquals(actualMsg, expectedMsg, "Success message is not displayed as expected");
    }
    @Test
    public void TC11(){
        System.out.println("User can't create account while password and PID fields are empty");
        HomePage homePage = new HomePage();
        homePage.open();
        RegisterPage registerPage = homePage.gotoRegister();
        registerPage.getTxtEmail().sendKeys("huynhanh@gmail.com");
        registerPage.getbtnRegister().submit();
        String actualMsg = registerPage.getLblRegisterErrorMsg().getText();
        String expectedMsg = "There're errors in the form. Please correct the errors and try again.";
        Assert.assertEquals(actualMsg, expectedMsg, "");
    }
    @Test
    public void TC12(){
        System.out.println("Errors display when password reset token is blank");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        ForgotPasswordPage forgotPasswordPage = homePage.gotoForgotPasswordPage();
        forgotPasswordPage.getTxtEmail().sendKeys("huynhthianh@gmail.com");
        forgotPasswordPage.getBtnSendInstructors().submit();
    }
    @Test
    public void TC13() {
        System.out.println("Errors display if password and confirm password don't match when resetting password");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        ForgotPasswordPage forgotPasswordPage = homePage.gotoForgotPasswordPage();
        forgotPasswordPage.getTxtEmail().sendKeys("huynhthianh@gmail.com");
        forgotPasswordPage.getBtnSendInstructors().submit();
    }
    @Test
    public void TC14() {
        System.out.println("User can book 1 ticket at a time");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.getTxtUsername().sendKeys("anh2763@gmail.com");
        loginPage.getTxtPassword().sendKeys("123456789");
        loginPage.getBtnLogin().submit();
        BookTicketPage bookTicketPage = homePage.clickBookTicketTab();
        bookTicketPage.selectDepartDate(5);
        bookTicketPage.selectDepartFrom("Quảng Ngãi");
        bookTicketPage.selectArriveAt("Đà Nẵng");

        bookTicketPage.selectSeatType("Soft bed with air conditioner");
        bookTicketPage.selectTicketAmount("1");

        bookTicketPage.clickBookTicketButton();
        Assert.assertTrue(bookTicketPage.isTicketBookedSuccessfullyDisplayed(), "Ticket booked successfully message is not displayed");

        boolean ticketDetailsMatch = bookTicketPage.verifyTicketDetails("Sài Gòn", "Nha Trang", "Soft bed with air conditioner", bookTicketPage.getDepartDate(), "1");
        System.out.println("Depart Station: " + bookTicketPage.getDepartStation());
        System.out.println("Arrive Station: " + bookTicketPage.getArriveStation());
        System.out.println("Seat Type: " + bookTicketPage.getSeatType());
        System.out.println("Depart Date: " + bookTicketPage.getDepartDate());
        System.out.println("Amount: " + bookTicketPage.getAmount());
        Assert.assertTrue(ticketDetailsMatch, "Ticket details do not match.");
    }
@Test
public void TC15() {
    System.out.println("User can open 'Book ticket' page by clicking on 'Book ticket' link in 'Train timetable' page");

    HomePage homePage = new HomePage();
    homePage.open();

    LoginPage loginPage = homePage.gotoLoginPage();
    loginPage.getTxtUsername().sendKeys("anh2763@gmail.com");
    loginPage.getTxtPassword().sendKeys("123456789");
    loginPage.getBtnLogin().submit();
    TimeTablePage trainTimetablePage = homePage.gotoTrainTimetablePage();
    trainTimetablePage.SelectbookTicketClick();
    Select DepartFrom= new Select(Constant.WEBDRIVER.findElement(By.xpath("//*[@id='content']/div[1]/form/fieldset/ol/li[2]/select")));
    Select ArriveStation=new Select(Constant.WEBDRIVER.findElement(By.xpath("//*[@id='ArriveStation']/select")));
    ArrayList<String> compare = new ArrayList<String>();
    compare.add(DepartFrom.getFirstSelectedOption().getText());
    compare.add(ArriveStation.getFirstSelectedOption().getText());
    List<String> expectedInfo = Arrays.asList("Sài Gòn", "Huế");
    trainTimetablePage.clickBookTicketButton();
    Assert.assertEquals(compare, expectedInfo, "Booking information doesn't match");
}
    @Test
    public void TC16()
    {
        System.out.println("TC16 -  User can cancel a ticket");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.getTxtUsername().sendKeys("anh2763@gmail.com");
        loginPage.getTxtPassword().sendKeys("123456789");
        loginPage.getBtnLogin().submit();
        BookTicketPage bookTicketPage = homePage.gotoBookTicketPage();
        LocalDate currentDate = LocalDate.now().plusDays(4);
        String departDate = currentDate.format(DateTimeFormatter.ofPattern("d"));
        bookTicketPage.selectDepartDate(Integer.parseInt(departDate));
        bookTicketPage.selectDepartFrom("Sài Gòn");
        bookTicketPage.selectArriveAt("Nha Trang");
        bookTicketPage.selectSeatType("Soft bed with air conditioner");
        bookTicketPage.selectTicketAmount("1");
        bookTicketPage.clickBookTicketButton();
        String url = Constant.WEBDRIVER.getCurrentUrl();
        String[] urls = url.split("id=");
        String delete= urls[1];
        String xp ="//input[@onclick='DeleteTicket("+delete+");']";
        MyTicketPage ticket = homePage.gotoMyTicketPage();
        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        ticket.clickCancelButton(xp);
        Constant.WEBDRIVER.switchTo().alert().accept();
        boolean isTicketDisplayed = true;
        try {
            WebElement CancelButtonElement = Constant.WEBDRIVER.findElement(By.xpath(xp));
            isTicketDisplayed = CancelButtonElement.isDisplayed();
        }
        catch (NoSuchElementException e) {
            isTicketDisplayed = false;
        }
        Assert.assertFalse(isTicketDisplayed, "The ticket does not disappear");
    }
}
