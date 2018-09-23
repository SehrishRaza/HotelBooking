import org.junit.Test;

public class AddBookingTest extends BaseClass{

    @Test
    public void createBooking() throws InterruptedException {
        BookingPage bookingPage = new BookingPage(getDriver());
        bookingPage.navigate(getProperties("url"));
        bookingPage.setFirstName(getProperties("firstName"));
        bookingPage.setLastName(getProperties("lastName"));
        bookingPage.setTotalPrice(getProperties("totalPrice"));
        bookingPage.setCheckIn(getProperties("checkIn"));
        bookingPage.setCheckOut(getProperties("checkOut"));
        bookingPage.save();
    }
}
