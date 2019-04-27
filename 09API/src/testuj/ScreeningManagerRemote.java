package testuj;

import entity.Auditorium;
import entity.City;
import entity.Movie;

import javax.ejb.Remote;
import java.sql.Timestamp;
import java.util.List;

@Remote
public interface ScreeningManagerRemote {

    List<City> getCities();
    List<Auditorium> getAuditoriums(long id);
    void setScreening(Movie movie, Auditorium auditorium, Timestamp screeningStart);

}
