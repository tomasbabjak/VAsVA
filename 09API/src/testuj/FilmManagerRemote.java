package testuj;

import javax.ejb.Remote;
import java.sql.Timestamp;
import java.time.chrono.Chronology;

@Remote
public interface FilmManagerRemote {

    void addFilm(String filmTitle, String filmDirector, String filmCast, Timestamp timestamp, String filmDescription, long duration, byte[] img);

}
