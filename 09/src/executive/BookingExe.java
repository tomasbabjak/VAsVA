package executive;

import dao.BookingDao;
import dao.CustomerDao;
import entity.*;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

/**
 * Stateless executive bean to manage the events associated with the booking seats
 */
@Stateless
@LocalBean
public class BookingExe {

    @EJB
    BookingDao dao;

    @EJB
    CustomerDao customerDao;

    /**
     * Method to obtain seats that are booked for specific screening
     * @param screaning screening
     * @return List of seats numbers
     */
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

    /**
     * Method to add a new record of reservation to database
     * Method also creates qr code, that represents id of reservation and then it is used in ticket in pdfCreator.
     * This method also sending this ticket to mail
     * @param customerId Id of custommer
     * @param screeningId Id of screening
     * @param paid booloan value representing whether the reservation was paid
     * @param seats list of booked seats
     * @return pdf file, ticket in pdf format
     */
    public byte[] setReservation(long customerId, long screeningId, boolean paid, List<Integer> seats) {
        byte[] imageB = null;
        byte[] pdf = null;
        long resId;
        Customer customer;

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

        customer = customerDao.getCastumerByID(customerId);
        MailSender mailSender = new MailSender();
        mailSender.send("dannyel.minarik@gmail.com","CINEMA TICKET","Thank you for visiting",pdf,imageB);
        //mailSender.send(customer.getEmail(),"CINEMA TICKET","Thank you for visiting",pdf,imageB);

        return pdf;

        //return null;
    }
}
