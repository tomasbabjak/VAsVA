package executive;

import dao.CustomerDao;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class CheckExe {

    @EJB
    CustomerDao customerDao;

    public boolean checkUser(String username){
        return(customerDao.checkUser(username));
    }
}
