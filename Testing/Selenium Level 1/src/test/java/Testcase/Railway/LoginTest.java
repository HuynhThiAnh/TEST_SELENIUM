package Testcase.Railway;

import PageObjects.Railway.*;
import org.openqa.selenium.chrome.ChromeDriver;

import Common.Common.Utilities;
import Common.Constant.Constant;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("Pre-condition");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }
//    @AfterMethod
//    public void afterMethod(){
//        System.out.println("Post-condition");
//        Constant.WEBDRIVER.quit();
//    }
    @Test
    public void TC01(){
        System.out.println("TC01 - User can log into Railway with valid username and password");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        String actualMsg = loginPage.login(Constant.USERNAME, Constant.PASSWORD).getWelcomeMessage();
        String expectedMsg = "Welcome " + Constant.USERNAME;
        Assert.assertEquals(actualMsg, expectedMsg, "Welcome message is not displayed as expected");
    }
    @Test
    public void TC02() {
        System.out.println("TC02-User can't login with blank 'Username' textbox");
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
    public void TC07()throws InterruptedException{
        System.out.println("User can create new account");
        HomePage homePage = new HomePage();
        homePage.open();
        RegisterPage registerPage = homePage.gotoRegister();
        String actualMsg = registerPage.register(Constant.EMAIL, Constant.PASSWORD, Constant.CONFIRMPASSWORD, Constant.ID).getWelcomeMessage();
        String expectedMsg = "Thank you for registering your account";
        Assert.assertEquals(actualMsg, expectedMsg, "");
        Thread.sleep(10000);

    }
    @Test
    public void TC08(){
        System.out.println("TC08 - User can't login with an account hasn't been activated");
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
        System.out.println("TC09 - User can change password");
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
//        Thread.sleep(10000);
    }
    @Test
    public void TC10() throws InterruptedException{
        System.out.println("User can't create account with 'Confirm password' is not the same with 'Password'");
        HomePage homePage = new HomePage();
        homePage.open();
        RegisterPage registerPage = homePage.gotoRegister();
        registerPage.getTxtEmail().sendKeys("huynanh270603@gmail.com");
        registerPage.getTxtPassword().sendKeys("huynhthianh");
        registerPage.getTxtConfirmPassword().sendKeys("huynhanh");
        registerPage.getTxtPid().sendKeys("123456900876");
        registerPage.getBtnRegister().submit();
        String actualMsg = registerPage.getLblRegisterErrorMsg().getText();
        String expectedMsg = "There're errors in the form. Please correct the errors and try again.";
        Assert.assertEquals(actualMsg, expectedMsg, "Success message is not displayed as expected");
        Thread.sleep(20000);
    }
    @Test
    public void TC11(){
        System.out.println("User can't create account while password and PID fields are empty");
        HomePage homePage = new HomePage();
        homePage.open();
        RegisterPage registerPage = homePage.gotoRegister();
        registerPage.getTxtEmail().sendKeys("huynhanh@gmail.com");
        registerPage.getBtnRegister().submit();
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





    }



}
