package dao;

import entity.Customer;
import entity.Reservation;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Dao bean that working with database specifically with events related to customer
 */
@Stateless
public class CustomerDao {

    @PersistenceContext(unitName = "09")
    private EntityManager entityManager;

    /**
     * Checking if there is user with same username in database.
     * @param username Username of new customer which is being checked.
     * @return True if user with this username already exists, else false
     */
    public boolean checkUser(String username){
        String hql = "SELECT c.id from Customer c where c.username = :username";
        Query query = entityManager.createQuery(hql);
        query.setParameter("username", username);
        try {
            query.getSingleResult();
        }catch(NoResultException e){
            return true;
        }
        return false;
    }

    /**
     * Register customer
     * @param customer customer
     */
    public void registrate(Customer customer) {
        entityManager.persist(customer);
    }

    /**
     * Customer by name
     * @param name name of customer
     * @return instance of customer or null
     */
    public Customer getCastumerByName(String name) {
        String hql = "SELECT C from Customer C where C.username = :name ";
        Query query = entityManager.createQuery(hql);
        query.setParameter("name", name);
        try {
            return (Customer) query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public Customer getCastumerByID(long id) {
        String hql = "SELECT C from Customer C where C.id = :id ";
        Query query = entityManager.createQuery(hql);
        query.setParameter("id", id);
        try {
            return (Customer) query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }


    public List<Reservation> getReservations(Customer customer) {
        String hql = "SELECT R from Reservation R " +
                "where R.customer.id = :customerId ";
        Query query = entityManager.createQuery(hql);
        query.setParameter("customerId",customer.getId());
        try {
            return query.getResultList();
        }catch (NoResultException e){
            return null;
        }
    }
}
