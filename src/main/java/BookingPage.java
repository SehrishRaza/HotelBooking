import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class BookingPage {

    private final WebDriver driver;

    @FindBy(id = "firstname")
    WebElement firstNameElement;

    @FindBy(id = "lastname")
    WebElement lastNAmeElement;

    @FindBy(id = "totalprice")
    WebElement totalPriceElement;

    @FindBy(id = "depositpaid")
    WebElement depositPaidElement;

    @FindBy(id = "checkin")
    WebElement checkInCalendarElement;

    @FindBy(id = "checkout")
    WebElement checkOutCalendarElement;

    @FindBy(id = "bookings")
    WebElement bookingTableElement;

    public BookingPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigate(String url) throws InterruptedException {
        driver.navigate().to(url);
        WebDriverWait wait = new WebDriverWait(driver, 30);

        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = driver -> ((JavascriptExecutor)driver).executeScript("return jQuery.active").toString().equals("0");

        // wait for Javascript to load
//        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor)driver).executeScript("return document.readyState").toString().equals("complete");

        wait.until(jQueryLoad);

        PageFactory.initElements(driver, this);

    }

    public void setFirstName(String firstName){
        firstNameElement.sendKeys(firstName);
    }

    public void setLastName(String lastName){
        lastNAmeElement.sendKeys(lastName);
    }
//    public void setDepositPaid(String depositPaid){
//        depositPaidElement;
//    }
    public void setCheckIn(String checkIn){
        checkInCalendarElement.sendKeys(checkIn);
    }
    public void setCheckOut(String checkOut){
        checkOutCalendarElement.sendKeys(checkOut);
    }
    public void setTotalPrice(String totalPrice){
        totalPriceElement.sendKeys(totalPrice);
    }

    public void save() {
        WebElement saveElement = driver.findElement(By.xpath("//input[@value=' Save ']"));
        saveElement.click();
    }

    public Optional<WebElement> findBooking(String firstName, String lastName){
        List<WebElement> rows = bookingTableElement.findElements(By.className("row"));
        return rows.stream().filter(row -> {
            List<WebElement> columns = row.findElements(By.tagName("div"));
            return columns.get(0).getText().equals(firstName) && columns.get(1).getText().equals(lastName);

        }).findFirst();
    }

    public void deleteBooking(WebElement webElement) {
        WebElement deleteElement = webElement.findElement(By.tagName("input"));
        deleteElement.click();
    }
}
