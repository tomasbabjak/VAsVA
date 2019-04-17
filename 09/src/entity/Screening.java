package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Screening {

  @Id
  @GeneratedValue
  private long id;
  @ManyToOne
  private Movie movie;
  @ManyToOne
  private Auditorium auditorium;
  private java.sql.Timestamp screeningStart;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public Movie getMovie() {
    return movie;
  }

  public void setMovie(Movie movie) {
    this.movie = movie;
  }

  public Auditorium getAuditorium() {
    return auditorium;
  }

  public void setAuditorium(Auditorium auditorium) {
    this.auditorium = auditorium;
  }

  public java.sql.Timestamp getScreeningStart() {
    return screeningStart;
  }

  public void setScreeningStart(java.sql.Timestamp screeningStart) {
    this.screeningStart = screeningStart;
  }

}
