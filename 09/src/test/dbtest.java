package test;

import testuj.dbtestRemote;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class dbtest implements dbtestRemote {

    @PersistenceContext(unitName = "09")
    private EntityManager entityManager;

    public int otestuj(String input){
        String sql = "SELECT M.id from Movie M where M.title = :title";

        Query query = entityManager.createQuery(sql);
        query.setParameter("title",input);
        long result = 0;

        result = (Long) query.getSingleResult();

        return Long.valueOf(result).intValue();
    }

}
