package dao;

import entity.Customer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class CustomerDao {

    @PersistenceContext(unitName = "09")
    private EntityManager entityManager;

    public void registrate(Customer customer) {
        entityManager.persist(customer);
    }

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


}
