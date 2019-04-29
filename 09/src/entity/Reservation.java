package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Reservation implements Serializable {

  @Id
  @GeneratedValue
  private long id;
  @ManyToOne
  private Screening screening;
  @ManyToOne
  private Customer customer;
  @ManyToOne
  private ReservationType reservationType;
  private String reservationContact;
  private boolean reserved;
  private boolean paid;
  private boolean active;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


    public Screening getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ReservationType getReservationType() {
        return reservationType;
    }

    public void setReservationType(ReservationType reservationType) {
        this.reservationType = reservationType;
    }

    public String getReservationContact() {
        return reservationContact;
    }

    public void setReservationContact(String reservationContact) {
        this.reservationContact = reservationContact;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
