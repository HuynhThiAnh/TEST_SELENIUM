package PageObjects.Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Random;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static Common.Constant.Constant.WEBDRIVER;

public class BookTicketPage extends GeneralPage {
    private final By selectDepartFrom = By.xpath("//select[@name='DepartStation']");
    private final By selectArriveAt = By.xpath("//select[@name='ArriveStation']");
    private final By selectSeatType = By.xpath("//select[@name='SeatType']");
    private final By selectTicketAmount = By.xpath("//select[@name='TicketAmount']");
    private final By btnBookTicket = By.xpath("//input[@value='Book ticket']");
    private final By selectDepartDate = By.xpath("//select[@name='Date']");
    private final By departDateField = By.xpath("//div[@id='content']//td[4]");
    private final By departStationField = By.xpath("//div[@id='content']//td[1]");
    private final By arriveStationField = By.xpath("//div[@id='content']//td[2]");
    private final By seatTypeField = By.xpath("//div[@id='content']//td[3]");
    private final By amountField = By.xpath("//div[@id='content']//td[7]");
    private final By successMessage = By.xpath("//div[@id='content']//h1[contains(text(),'Ticket booked successfully!')]");

    public String getDepartDate() {
        WebElement departDateElement = WEBDRIVER.findElement(departDateField);
        return departDateElement.getText();
    }

    public String getDepartStation() {
        WebElement departStationElement = WEBDRIVER.findElement(departStationField);
        return departStationElement.getText();
    }

    public String getArriveStation() {
        WebElement arriveStationElement = WEBDRIVER.findElement(arriveStationField);
        return arriveStationElement.getText();
    }

    public String getSeatType() {
        WebElement seatTypeElement = WEBDRIVER.findElement(seatTypeField);
        return seatTypeElement.getText();
    }

    public String getAmount() {
        WebElement amountElement = WEBDRIVER.findElement(amountField);
        return amountElement.getText();
    }

    public boolean isTicketBookedSuccessfullyDisplayed() {
        try {
            WebElement successMessageElement = WEBDRIVER.findElement(successMessage);
            return successMessageElement.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException e) {
            return false;
        }
    }

    public void selectRandomDepartDate() {
        WebElement dropdown = WEBDRIVER.findElement(selectDepartDate);
        scrollToElement(dropdown);
        Select select = new Select(dropdown);
        List<WebElement> options = select.getOptions();
        int randomIndex = new Random().nextInt(options.size());
        select.selectByIndex(randomIndex);
    }

    private void selectOptionInDropdown(By dropdownLocator, String option) {
        WebElement dropdown = WEBDRIVER.findElement(dropdownLocator);
        scrollToElement(dropdown);
        Select select = new Select(dropdown);
        select.selectByVisibleText(option);
    }

    public void selectDepartFrom(String station) {
        selectOptionInDropdown(selectDepartFrom, station);
    }

    public void selectArriveAt(String station) {
        selectOptionInDropdown(selectArriveAt, station);
    }

    public void selectSeatType(String seatType) {
        selectOptionInDropdown(selectSeatType, seatType);
    }

    public void selectTicketAmount(String amount) {
        selectOptionInDropdown(selectTicketAmount, amount);
    }

    public void clickBookTicketButton() {
        WebElement button = WEBDRIVER.findElement(btnBookTicket);
        scrollToElement(button);
        button.click();
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) WEBDRIVER).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void selectDepartDate(int daysToAdd) {
        System.out.println("Calling selectDepartDate method...");

        // Lấy ngày hiện tại
        LocalDate today = LocalDate.now();

        // Thêm số ngày cần thiết
        LocalDate desiredDate = today.plusDays(daysToAdd);

        if (daysToAdd >= 3 && daysToAdd <= 30) {
            // Định dạng ngày mong muốn thành chuỗi
            String formattedDate = desiredDate.format(DateTimeFormatter.ofPattern("M/d/yyyy"));

            // Gọi hàm selectOptionInDropdown với ngày đã được định dạng
            selectOptionInDropdown(selectDepartDate, formattedDate);
            System.out.println("selectDepartDate method executed successfully.");
        } else {
            System.out.println("We only have tickets for 3-30 days ahead.");
            System.out.println("Please come to the station to buy a ticket if you need to depart within 2 days.");
        }
    }

    public boolean verifyTicketDetails(String departStation, String arriveStation, String seatType, String departDate, String amount) {
        WebElement table = WEBDRIVER.findElement(By.xpath("//table[@class='MyTable WideTable']"));
        WebElement row = table.findElement(By.xpath("//tr[@class='OddRow']")); // Assuming there's only one row for simplicity

        String actualDepartStation = row.findElement(By.xpath("//td[1]")).getText();
        String actualArriveStation = row.findElement(By.xpath("//td[2]")).getText();
        String actualSeatType = row.findElement(By.xpath("//td[3]")).getText();
        String actualDepartDate = row.findElement(By.xpath("//td[4]")).getText();
        String actualAmount = row.findElement(By.xpath("//td[7]")).getText();

        return actualDepartStation.equals(departStation)
                && actualArriveStation.equals(arriveStation)
                && actualSeatType.equals(seatType)
                && actualDepartDate.equals(departDate)
                && actualAmount.equals(amount);
    }
}