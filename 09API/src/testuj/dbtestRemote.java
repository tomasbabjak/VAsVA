package testuj;

import javax.ejb.Remote;

@Remote
public interface dbtestRemote {
    public int otestuj(String input);
}
