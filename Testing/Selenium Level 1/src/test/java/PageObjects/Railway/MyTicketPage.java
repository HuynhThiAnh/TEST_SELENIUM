package PageObjects.Railway;

import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MyTicketPage extends GeneralPage {
    private final By _lblManageTicket = By.xpath("//*[@id='content']/h1[text()='Manage ticket']");

    public WebElement getLblManageTicket(){
        return Constant.WEBDRIVER.findElement(_lblManageTicket);
    }
    private final By _btnCancel = By.xpath("//*[@id='content']/form/div/div/table/tbody/tr[2]/td[11]/input");
    public void clickCancelButton(String xp) {
        WebElement CancelButtonElement = Constant.WEBDRIVER.findElement(_btnCancel);
        CancelButtonElement.click();
    }
}
