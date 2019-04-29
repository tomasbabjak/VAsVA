package executive;

import dao.ScreeningDao;
import entity.Auditorium;
import entity.City;
import entity.Movie;
import entity.Screening;
import javafx.beans.binding.StringBinding;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Executive bean to working with events associated with screenings
 */


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

    public boolean checkPos(Movie movie, Auditorium auditorium, Timestamp timestamp){
        String time = new SimpleDateFormat("yyyy/MM/dd").format(timestamp);
        List<Screening> screenings = dao.getScrenings(movie,auditorium,time);
        long zacS;
        long konS;
        long zacI;
        long konI;
        zacI = (timestamp.getHours() * 60) + timestamp.getMinutes();
        konI = (timestamp.getHours() * 60) + timestamp.getMinutes() + movie.getDurationMin() + 30;
        System.out.println("zacI " + zacI + " konI " +konI);
        for(Screening sc : screenings){
            zacS = (sc.getScreeningStart().getHours() * 60) + sc.getScreeningStart().getMinutes();
            konS = (sc.getScreeningStart().getHours() * 60) + sc.getScreeningStart().getMinutes() + sc.getMovie().getDurationMin() + 30;
            System.out.println("zacS " + zacS + " konS " +konS);
            if((zacI >= zacS && zacI <= konS) || (konI >= zacS && konI <= konS) || (zacI <= zacS && konI >= konS)){
                System.out.println("false to insert ");
                return false;
            }
        }
     return true;
    }



}
