import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.Optional;

public class DeleteBookingTest extends BaseClass {

    @Test
    public void deleteBooking() throws InterruptedException{
        BookingPage bookingPage = new BookingPage(getDriver());
        bookingPage.navigate();
        Optional<WebElement> booking = bookingPage.findBooking(getProperties("firstName"), getProperties("lastName"));
        if(booking.isPresent()){
            bookingPage.deleteBooking(booking.get());
        }

    }
}
