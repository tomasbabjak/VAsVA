package managers;

import entity.City;
import entity.Screening;
import executive.BookingExe;
import testuj.BookingManagerRemote;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Facade bean implements remote
 */
@Stateless
public class BookingManager implements BookingManagerRemote {

    @EJB
    BookingExe bookingExe;

    @Override
    public List<Integer> getReservedSeats(int screaning) {

        List<Integer> result = bookingExe.getSeats(screaning);
        System.out.println(result.toString());

        return result;
    }

    @Override
    public List<City> getCities() {

        List<City> result = bookingExe.getCities();

        return result;
    }

    @Override
    public List<Screening> getDates(long movie_id, long cityId) {

        List<Screening> dates = bookingExe.getDates(movie_id,cityId);

        return dates;
    }

    @Override
    public byte[] setReservation(long customerId, long screeningId, boolean paid, List<Integer> seats) {
        return bookingExe.setReservation(customerId, screeningId, paid, seats);
    }
}
