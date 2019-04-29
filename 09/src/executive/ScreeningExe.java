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
        long tmp;
        long tmp2;
        tmp2 = (timestamp.getHours() * 60) + timestamp.getMinutes();
        System.out.println("tmp 2 je" + tmp2);
        for(Screening sc : screenings){
            tmp = (sc.getScreeningStart().getHours() * 60) + sc.getScreeningStart().getMinutes() + sc.getMovie().getDurationMin();
            System.out.println("tmp je" + tmp);
            if(tmp + 30 > tmp2){
                System.out.println(tmp2 + "  " + tmp + " false");
                return false;
            }
        }
     return true;
    }



}
