package executive;

import dao.CustomerDao;
import entity.Customer;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Stateless executive bean use to verify or registrate user
 */

@Stateless
@LocalBean
public class LoginExe {

    @EJB
    CustomerDao dao;

    /**
     * Method used on register new user and add record to database
     * @param customer user representing a new user
     */
    public void registrate(Customer customer){

        String salt = PasswordUtils.getSalt(30);
        String securedPassword = PasswordUtils.generateSecurePassword(customer.getPassword(),salt);
        customer.setPassword(securedPassword);
        customer.setSalt(salt);
        dao.registrate(customer);
    }

    /**
     * Method to logIn user respectively to verify name and password with database record
     * @param name input userName from user
     * @param password input password from user
     * @return instance of user database or null
     */
    public Customer login(String name, String password) {
        Customer customer = dao.getCastumerByName(name);

        if(customer == null){
            return null;
        }
        boolean passwordMatch = PasswordUtils.verifyUserPassword(password,customer.getPassword(),customer.getSalt());

        if(passwordMatch){
            return customer;
        }
        else return null;
    }



}
