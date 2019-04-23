package test;

import entity.Movie;
import testuj.dbtestRemote;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

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

    public List<byte[]> getImage() {

        List<Movie> movieList = null;
        String sql = "SELECT m from Movie m";

        Query query = entityManager.createQuery(sql);
        movieList = query.getResultList();

        List<byte[]> pole = new ArrayList<>();
        for(Movie movie : movieList){
            pole.add(movie.getImage());
        }

       // byte[] pole = movieList.get(0).getImage();
        return pole;
    }

    public List<Movie> getMovies(){
        List<Movie> movieList = null;
        String sql = "SELECT m from Movie m";

        Query query = entityManager.createQuery(sql);
        movieList = query.getResultList();

        return movieList;
    }

}
