package executive;

import dao.FilmDao;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.sql.Timestamp;

@Stateless
@LocalBean
public class FilmExe {

    @EJB
    FilmDao dao;

    public void addFilm(String filmTitle, String filmDirector, String filmCast, Timestamp timestamp, String filmDescription, long duration, byte[] img) {
        dao.addFilm(filmTitle, filmDirector, filmCast, timestamp, filmDescription, duration, img);
    }
}
