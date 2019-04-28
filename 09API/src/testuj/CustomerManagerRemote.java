package testuj;

import entity.Customer;

import javax.ejb.Remote;

@Remote
public interface CustomerManagerRemote {

    public Customer logIn(String name, String password);
    public boolean registrate(String email, String fname, String lname, String name, String password);
}
