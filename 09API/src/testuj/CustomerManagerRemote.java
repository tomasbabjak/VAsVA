package testuj;

import javax.ejb.Remote;

@Remote
public interface CustomerManagerRemote {

    public boolean logIn(String name, String password);
}
