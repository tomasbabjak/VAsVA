package executive;

import dao.BookingDao;
import entity.City;
import entity.Screening;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
@LocalBean
public class BookingExe {

    @EJB
    BookingDao dao;

    public List<Integer> getSeats(int screaning) {

        List<Integer> result;

        result = dao.getSeats(screaning);
        System.out.println(result.toString());
        return result;
    }

    public List<City> getCities() {
        return dao.getCities();
    }

    public List<Screening> getDates(long movie_id, long cityId) {

        List<Screening> db = dao.getDates(movie_id,cityId);
        List<Screening> result = new ArrayList<>();
        for(Screening ele : db){
           Screening nn = new Screening();
           nn.setId(ele.getId());
           nn.setScreeningStart(ele.getScreeningStart());
           result.add(nn);
        }
        return result;
    }
}
