package executive;

import dao.BookingDao;
import entity.City;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
@LocalBean
public class BookingExe {

    @EJB
    BookingDao dao;

    public List<Integer> getSeats(int screaning) {

        List<Integer> result = new ArrayList<>();

        result = dao.getSeats(screaning);

        return result;
    }

    public List<City> getCities() {
        return dao.getCities();
    }

    public List<Date> getDates(long movie_id,long cityId) {

        List<Timestamp> timestamps = dao.getDates(movie_id,cityId);
        List<Date> dates = new ArrayList<>();

        for(Timestamp time : timestamps){
            dates.add(new Date(time.getTime()));
        }

        return dates;
    }
}
