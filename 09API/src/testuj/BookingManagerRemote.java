package testuj;

import entity.City;

import javax.ejb.Remote;
import java.util.Date;
import java.util.List;

@Remote
public interface BookingManagerRemote {


     List<Integer> getReservedSeats(int screaning);

    List<City> getCities();

    List<Date> getDates(long movie_id,long cityId);
}
