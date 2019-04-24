package dao;

import entity.City;
import entity.Screening;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.List;

@Stateless
public class BookingDao {

    @PersistenceContext(unitName = "09")
    private EntityManager entityManager;

    public List<Integer> getSeats(int screaning){
        System.out.println("Screnenig je " + screaning);
        String hql = "SELECT S2.number from SeatReserved S " +
                "join Seat S2 on S.seat.id = S2.id " +
                "where S.screening.id = :id";
        Query query = entityManager.createQuery(hql);
        query.setParameter("id", (long) screaning);
        List<Integer> result = query.getResultList();
        System.out.println(result.toString());
        return result;
    }


    public List<City> getCities() {
        List<City> cities = null;
        String hql = "SELECT C from City C ";
        Query query = entityManager.createQuery(hql);
        cities = query.getResultList();
        return cities;
    }

    public List<Screening> getDates(long movie_id, long cityId) {
        List<Screening> dates = null;
        String hql = "SELECT S from Screening S " +
                "join Auditorium A on A.id = S.auditorium.id " +
                "where S.movie.id = :movie_id AND A.city.id = :city_id";
        Query query = entityManager.createQuery(hql);
        query.setParameter("movie_id",movie_id);
        query.setParameter("city_id",cityId);
        dates = query.getResultList();
        System.out.println(dates);
        return dates;
    }
}
