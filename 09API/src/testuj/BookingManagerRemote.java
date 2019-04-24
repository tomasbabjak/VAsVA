package testuj;

import entity.City;
import entity.Screening;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface BookingManagerRemote {


     List<Integer> getReservedSeats(int screaning);

    List<City> getCities();

    List<Screening> getDates(long movie_id, long cityId);
}
