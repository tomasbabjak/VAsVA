package testuj;

import entity.Customer;

import javax.ejb.Remote;

@Remote
public interface CustomerManagerRemote {

    public Customer logIn(String name, String password);
}
