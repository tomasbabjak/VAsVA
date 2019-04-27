package executive;

import dao.CustomerDao;
import entity.Customer;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class LoginExe {

    @EJB
    CustomerDao dao;

    public void registrate(Customer customer){

        String salt = PasswordUtils.getSalt(30);
        String securedPassword = PasswordUtils.generateSecurePassword(customer.getPassword(),salt);
        customer.setPassword(securedPassword);
        customer.setSalt(salt);
        dao.registrate(customer);
    }

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
