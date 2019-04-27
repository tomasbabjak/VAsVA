package executive;

import dao.BookingDao;
import entity.City;
import entity.Movie;
import entity.Reservation;
import entity.Screening;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
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

    public byte[] setReservation(long customerId, long screeningId, boolean paid, List<Integer> seats) {
        byte[] imageB = null;
        byte[] pdf = null;
        long resId;

        try {
            resId = dao.setReservation(customerId, screeningId, paid);

            Reservation res = new Reservation();
            res.setId(resId);
            Screening scr = new Screening();
            scr.setId(screeningId);
            for(Integer element : seats){
               dao.setResSeats(res,scr,element);
            }

        }catch (PersistenceException exe){
            //loger
            return null;
        }

        Movie movie = dao.getMovieByScreening(screeningId);
        Screening screening = dao.getScreening(screeningId);

        imageB = QrGenerator.getQRCodeImage(String.valueOf(resId));
        pdf = PdfCreator.create(imageB,movie,screening,seats);


        MailSender mailSender = new MailSender();
        mailSender.send("dannyel.minarik@gmail.com","CINEMA TICKET","Thank you for visiting",pdf,imageB);

        return pdf;

        //return null;
    }
}
