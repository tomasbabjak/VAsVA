package testuj;

import entity.Customer;
import entity.Reservation;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface CustomerManagerRemote {

    public Customer logIn(String name, String password);
    public boolean registrate(String email, String fname, String lname, String name, String password);

    public List<Reservation> getReservations(Customer c);

}
