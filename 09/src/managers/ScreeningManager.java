package managers;

import entity.Auditorium;
import entity.City;
import entity.Movie;
import executive.ScreeningExe;
import testuj.ScreeningManagerRemote;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.sql.Timestamp;
import java.util.List;

/**
 * Facade bean implements remote
 */
@Stateless
public class ScreeningManager implements ScreeningManagerRemote {

    @EJB
    ScreeningExe screeningExe;

    @Override
    public List<City> getCities() {

        List<City> result = screeningExe.getCities();

        return result;
    }

    @Override
    public List<Auditorium> getAuditoriums(long id) {

        List<Auditorium> result = screeningExe.getAuditoriums(id);

        return result;
    }

    @Override
    public void setScreening(Movie movie, Auditorium auditorium, Timestamp screeningStart) {
        screeningExe.setScreening(movie,auditorium,screeningStart);
    }

    @Override
    public boolean checkPos(Movie movie, Auditorium auditorium, Timestamp screeningStart){
       return screeningExe.checkPos(movie, auditorium, screeningStart);
    }

}
