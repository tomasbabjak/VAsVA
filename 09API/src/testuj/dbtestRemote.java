package testuj;

import entity.Movie;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface dbtestRemote {
    public int otestuj(String input);

    public List<byte[]> getImage();

    public List<Movie> getMovies();
}
