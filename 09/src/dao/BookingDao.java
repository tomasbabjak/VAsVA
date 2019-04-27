package dao;

import entity.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class BookingDao {

    @PersistenceContext(unitName = "09")
    private EntityManager entityManager;

    public List<Integer> getSeats(int screaning){
        System.out.println("Screnenig je " + screaning);
        String hql = "SELECT S.seatNumber from SeatReserved S " +
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

    public long setReservation(long customerId, long screeningId, boolean paid) {
        Reservation reservation = new Reservation();
        reservation.setActive(true);
        Customer customer = new Customer();
        customer.setId(customerId);
        reservation.setCustomer(customer);
        Screening screening = new Screening();
        screening.setId(screeningId);
        reservation.setScreening(screening);
        reservation.setPaid(paid);
        entityManager.persist(reservation);
        System.out.println("toto je id:" + reservation.getId());
        return reservation.getId();
    }

    public void setResSeats(Reservation res, Screening scr, int seat) {
            SeatReserved reserved = new SeatReserved();
            reserved.setReservation(res);
            reserved.setScreening(scr);
            reserved.setSeatNumber(seat);
            entityManager.persist(reserved);
    }

    public Movie getMovieByScreening(long screeningId) {
        String hql = "SELECT M from Screening S " +
                "join Movie M on M.id = S.movie.id " +
                "where S.id = :screeningId";
        Query query = entityManager.createQuery(hql);
        query.setParameter("screeningId",screeningId);
        return (Movie) query.getSingleResult();
    }

    public Screening getScreening(long screeningId) {
        String hql = "SELECT S from Screening S " +
                "where S.id = :screeningId";
        Query query = entityManager.createQuery(hql);
        query.setParameter("screeningId",screeningId);
        return (Screening) query.getSingleResult();
    }
}
