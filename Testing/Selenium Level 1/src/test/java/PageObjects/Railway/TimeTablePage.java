package PageObjects.Railway;

import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import java.util.List;

import static Common.Constant.Constant.WEBDRIVER;

public class TimeTablePage{
    public final By allRows = By.xpath("//table[@class='MyTable WideTable']//tr[@class='OddRow' or @class='EvenRow']");
    private final By bookTicketClick = By.xpath("//*[@id='content']/div/div/table/tbody/tr[4]/td[7]/a");
    private final By btnBookTicket = By.xpath("//input[@value='Book ticket']");

    public void SelectbookTicketClick() {
        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        WebElement bookTicketButtonElement = Constant.WEBDRIVER.findElement(bookTicketClick);
        bookTicketButtonElement.click();
    }
    public void clickBookTicketButton() {
        WebElement button = WEBDRIVER.findElement(btnBookTicket);
        scrollToElement(button);
        button.click();
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) WEBDRIVER).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public  List<WebElement> getAllRows() {
        return Constant.WEBDRIVER.findElements(allRows);
    }
}
