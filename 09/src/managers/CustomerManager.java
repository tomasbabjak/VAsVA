package managers;

import entity.Customer;
import executive.LoginExe;
import testuj.CustomerManagerRemote;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Facade bean implements remote
 */
@Stateless
public class CustomerManager implements CustomerManagerRemote {

    //@PersistenceContext(unitName = "09")
    //private EntityManager entityManager;

    @EJB
    LoginExe exe;

    public Customer logIn(String name, String password) {
        return exe.login(name, password);
    }

    public void registrate(String email, String fname, String lname, String name, String password) {

        Customer customer = new Customer();
        customer.setEmail(email);
        customer.setFirstName(fname);
        customer.setLastName(lname);
        customer.setPassword(password);
        customer.setUsername(name);
        exe.registrate(customer);

    }





}

