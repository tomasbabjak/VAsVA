package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ReservationType {

  @Id
  @GeneratedValue
  private long id;
  private String reservationType;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getReservationType() {
    return reservationType;
  }

  public void setReservationType(String reservationType) {
    this.reservationType = reservationType;
  }

}
