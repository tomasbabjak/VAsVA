package managers;

import entity.City;
import executive.BookingExe;
import testuj.BookingManagerRemote;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.List;

@Stateless
public class BookingManager implements BookingManagerRemote {

    @EJB
    BookingExe bookingExe;

    @Override
    public List<Integer> getReservedSeats(int screaning) {

        List<Integer> result = bookingExe.getSeats(screaning);

        return result;
    }

    @Override
    public List<City> getCities() {

        List<City> result = bookingExe.getCities();

        return result;
    }

    @Override
    public List<Date> getDates(long movie_id,long cityId) {

        List<Date> dates = bookingExe.getDates(movie_id,cityId);

        return dates;
    }
}
