package dao;

import entity.City;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
public class BookingDao {

    @PersistenceContext(unitName = "09")
    private EntityManager entityManager;

    public List getSeats(int screaning){
        String hql = "SELECT S2.number from SeatReserved S " +
                "join Seat S2 on S.id=S2.id " +
                "where S.screening.id = :id";
        Query query = entityManager.createQuery(hql);
        query.setParameter("id", (long) screaning);

        return query.getResultList();
    }


    public List<City> getCities() {
        List<City> cities = null;
        String hql = "SELECT C from City C ";
        Query query = entityManager.createQuery(hql);
        cities = query.getResultList();
        System.out.println(cities.toString());
        return cities;
    }

    public List<Timestamp> getDates(long movie_id,long cityId) {
        List<Timestamp> dates = null;
        String hql = "SELECT S.screeningStart from Screening S " +
                "join Auditorium A on A.id = S.auditorium.id " +
                "where S.movie.id = :movie_id AND A.city.id = :city_id";
        Query query = entityManager.createQuery(hql);
        query.setParameter("movie_id",movie_id);
        query.setParameter("city_id",cityId);
        dates = query.getResultList();
        return dates;
    }
}
