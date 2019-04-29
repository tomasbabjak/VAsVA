package managers;

import entity.Customer;
import entity.Reservation;
import executive.CheckExe;
import executive.LoginExe;
import testuj.CustomerManagerRemote;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Facade bean implements remote
 */
@Stateless
public class CustomerManager implements CustomerManagerRemote {

    //@PersistenceContext(unitName = "09")
    //private EntityManager entityManager;

    @EJB
    LoginExe exe;
    @EJB
    CheckExe cexe;


    public Customer logIn(String name, String password) {
        return exe.login(name, password);
    }

    public boolean registrate(String email, String fname, String lname, String name, String password) {

        Customer customer = new Customer();
        customer.setEmail(email);
        customer.setFirstName(fname);
        customer.setLastName(lname);
        customer.setPassword(password);
        customer.setUsername(name);

        if(cexe.checkUser(name)) {
            exe.registrate(customer);
            return true;
        }
        else return false;

    }

    @Override
    public List<Reservation> getReservations(Customer customer) {
        return exe.getReservations(customer);
    }


}

