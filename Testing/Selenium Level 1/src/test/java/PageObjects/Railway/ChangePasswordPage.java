
package PageObjects.Railway;

import Common.Constant.Constant;
import PageObjects.Railway.GeneralPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ChangePasswordPage extends GeneralPage {
    private final By _txtCurrentPassword = By.xpath("//input[@id='currentPassword']");
    private final By _txtNewPassword = By.xpath("//input[@id='newPassword']");
    private final By _txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
    private final By _btnChangePassword = By.xpath("//input[@value='Change Password']");
    private final By _lblChangePasswordErrorMsg = By.xpath("//*[@id='ChangePW']/fieldset/p[1]");


    //Elements
    public WebElement getTxtCurrentPassword(){
        return Constant.WEBDRIVER.findElement(_txtCurrentPassword);
    }

    public WebElement getTxtNewPassword(){
        return Constant.WEBDRIVER.findElement(_txtNewPassword);
    }
    public WebElement getTxtConfirmPassword(){
        return Constant.WEBDRIVER.findElement(_txtConfirmPassword);
    }
    public WebElement getBtnChangePassword(){
        return Constant.WEBDRIVER.findElement(_btnChangePassword);
    }
    public WebElement getLblChangePasswordErrorMsg(){
        return Constant.WEBDRIVER.findElement(_lblChangePasswordErrorMsg);
    }

    public HomePage changePassword(String currentPassword, String newPassword, String confirmPassword){

        this.getTxtCurrentPassword().sendKeys(currentPassword);
        this.getTxtNewPassword().sendKeys(newPassword);
        this.getTxtConfirmPassword().sendKeys(confirmPassword);
        this.getBtnChangePassword().click();

        //Land on Home page
        return new HomePage();
    }
}
