package Testcase.Railway;

import PageObjects.Railway.GeneralPage;
import PageObjects.Railway.HomePage;
import PageObjects.Railway.LoginPage;
import PageObjects.Railway.RegisterPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;


import Common.Common.Utilities;
import Common.Constant.Constant;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class RegisterTest {
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Pre-condition");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

//    @AfterMethod
//    public void afterMethod() {
//        System.out.println("Post-condition");
//        Constant.WEBDRIVER.quit();
//    }
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

}
