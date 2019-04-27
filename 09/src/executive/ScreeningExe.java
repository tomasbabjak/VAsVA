package executive;

import dao.ScreeningDao;
import entity.Auditorium;
import entity.City;
import entity.Movie;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.sql.Timestamp;
import java.util.List;

@Stateless
@LocalBean
public class ScreeningExe {

    @EJB
    ScreeningDao dao;

    public List<City> getCities() {
        return dao.getCities();
    }


    public List<Auditorium> getAuditoriums(long id) {
        return dao.getAuditoriums(id);
    }

    public void setScreening(Movie movie, Auditorium auditorium, Timestamp screeningStart) {
        dao.setScreening(movie,auditorium,screeningStart);
    }

}
