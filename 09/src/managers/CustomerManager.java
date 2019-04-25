package managers;

import entity.Customer;
import testuj.CustomerManagerRemote;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class CustomerManager implements CustomerManagerRemote {

    @PersistenceContext(unitName = "09")
    private EntityManager entityManager;

    public Customer logIn(String name, String password) {
        String hql = "SELECT C from Customer C where C.username = :name AND C.password = :password";
        Query query = entityManager.createQuery(hql);
        query.setParameter("name", name);
        query.setParameter("password", password);
        try {
            Customer user = (Customer) query.getSingleResult();
            return user;
        } catch (NoResultException e) {
            return null;
        }
    }

    public void registrate(String email, String fname, String lname, String name, String password) {

        //entityManager.getTransaction().begin();
        Customer customer = new Customer();
        customer.setEmail(email);
        customer.setFirstName(fname);
        customer.setLastName(lname);
        customer.setPassword(password);
        customer.setUsername(name);
        //customer.setId(2);
        entityManager.persist(customer);
       // entityManager.getTransaction().commit();

        /*String hql = "INSERT INTO Customer (id, email, firstname, lastname, password, username) VALUES (2, :email, :fname, :lname, :name, :password)";
        Query query = entityManager.createQuery(hql);
        query.setParameter("email",email);
        query.setParameter("fname",fname);
        query.setParameter("lname",lname);
        query.setParameter("name", name);
        query.setParameter("password",password);*/
    }
}

