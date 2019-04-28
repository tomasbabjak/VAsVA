package dao;

import entity.Movie;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;

/**
 * Dao bean that working with database specifically with events related to films
 */
@Stateless
public class FilmDao {

    @PersistenceContext(unitName = "09")
    private EntityManager entityManager;

    public void addFilm(String filmTitle, String filmDirector, String filmCast, Timestamp timestamp, String filmDescription, long duration, byte[] img) {
        Movie movie = new Movie();
        movie.setTitle(filmTitle);
        movie.setDirector(filmDirector);
        movie.setMovieCast(filmCast);
        movie.setPremiere_date(timestamp);
        movie.setDescription(filmDescription);
        movie.setDurationMin(duration);
        movie.setImage(img);
        entityManager.persist(movie);

    }
}
