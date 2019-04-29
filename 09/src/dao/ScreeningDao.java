package dao;

import entity.Auditorium;
import entity.City;
import entity.Movie;
import entity.Screening;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Dao bean that working with database specifically with events related to screening
 */
@Stateless
public class ScreeningDao {

    @PersistenceContext(unitName = "09")
    private EntityManager entityManager;


    public List<City> getCities() {
        List<City> cities = null;
        String hql = "SELECT C from City C ";
        Query query = entityManager.createQuery(hql);
        cities = query.getResultList();
        return cities;
    }

    public List<Auditorium> getAuditoriums(long id) {
        List<Auditorium> auditoriums = null;

        String hql = "SELECT a from Auditorium a join City c on c.cityId = a.city.cityId where c.cityId = :id";

        Query query = entityManager.createQuery(hql);
        query.setParameter("id", id);
        auditoriums = query.getResultList();
        return auditoriums;
    }

    public void setScreening(Movie movie, Auditorium auditorium, Timestamp screeningStart) {
        Screening screening = new Screening();
        screening.setAuditorium(auditorium);
        screening.setMovie(movie);
        screening.setScreeningStart(screeningStart);
        entityManager.persist(screening);
    }

    public List<Screening> getScrenings(Movie movie, Auditorium auditorium, String time) {
        String hql = "SELECT S from Screening S WHERE S.auditorium.id = :auditoriumid AND to_char(S.screeningStart,'YYYY/MM/DD') = :time";
        Query query = entityManager.createQuery(hql);
        query.setParameter("auditoriumid",auditorium.getId());
        query.setParameter("time",time);
        List<Screening> result = new ArrayList<>();
        result = query.getResultList();
        return result;
    }
}
