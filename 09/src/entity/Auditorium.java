package entity;

import javax.persistence.*;

@Entity
public class Auditorium {

  @Id
  @GeneratedValue
  private long id;
  private String name;
  private long seatsNo;
  @ManyToOne
  private City city;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public long getSeatsNo() {
    return seatsNo;
  }

  public void setSeatsNo(long seatsNo) {
    this.seatsNo = seatsNo;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }
}
