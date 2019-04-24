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

    public Customer logIn(String name, String password){
        String hql = "SELECT C from Customer C where C.username = :name AND C.password = :password";
        Query query = entityManager.createQuery(hql);
        query.setParameter("name",name);
        query.setParameter("password",password);
        try{
           Customer user = (Customer) query.getSingleResult();
           return user;
        }catch (NoResultException e){
            return null;
        }
    }


}
