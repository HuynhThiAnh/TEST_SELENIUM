
package PageObjects.Railway;
import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import static Common.Constant.Constant.WEBDRIVER;

public class HomePage extends GeneralPage {
    //Locators
    public HomePage open()
    {
        WEBDRIVER.navigate().to(Constant.RAILWAY_URL);
        return this;
    }
    public BookTicketPage clickBookTicketTab() {
        WebElement bookTicketTab = WEBDRIVER.findElement(By.xpath("//a[@href='/Page/BookTicketPage.cshtml']"));
        bookTicketTab.click();
        return new BookTicketPage();
    }
}
