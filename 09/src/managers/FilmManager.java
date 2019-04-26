package managers;

import executive.FilmExe;
import testuj.FilmManagerRemote;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.sql.Timestamp;
import java.time.chrono.Chronology;

@Stateless
public class FilmManager implements FilmManagerRemote {

    @EJB
    FilmExe filmExe;

    @Override
    public void addFilm(String filmTitle, String filmDirector, String filmCast, Timestamp timestamp, String filmDescription, long duration, byte[] img) {
        filmExe.addFilm(filmTitle, filmDirector, filmCast, timestamp, filmDescription, duration, img);
    }
}
