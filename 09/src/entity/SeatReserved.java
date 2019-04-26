package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SeatReserved {

  @Id
  @GeneratedValue
  private long id;
  private int seatNumber;
  @ManyToOne
  private Reservation reservation;
  @ManyToOne
  private Screening screening;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getSeatNumber() {
    return seatNumber;
  }

  public void setSeatNumber(int seatNumber) {
    this.seatNumber = seatNumber;
  }

  public Reservation getReservation() {
    return reservation;
  }

  public void setReservation(Reservation reservation) {
    this.reservation = reservation;
  }

  public Screening getScreening() {
    return screening;
  }

  public void setScreening(Screening screening) {
    this.screening = screening;
  }
}
