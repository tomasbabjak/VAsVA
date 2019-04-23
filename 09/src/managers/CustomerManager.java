package managers;

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

    public boolean logIn(String name, String password){
        String sql = "SELECT C.id from Customer C where C.username = :name AND C.password = :password";
        Query query = entityManager.createQuery(sql);
        query.setParameter("name",name);
        query.setParameter("password",password);
        try{
           query.getSingleResult();
           return true;
        }catch (NoResultException e){
            return false;
        }
    }


}
