package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
public class Movie implements Serializable {

  @Id
  @GeneratedValue
  private long id;
  private String title;
  private String director;
  private String movieCast;
  private String description;
  private long durationMin;
  private byte[] image;
  private Timestamp premiere_date;

  public Movie() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getDirector() {
    return director;
  }

  public void setDirector(String director) {
    this.director = director;
  }

  public String getMovieCast() {
    return movieCast;
  }

  public void setMovieCast(String movieCast) {
    this.movieCast = movieCast;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public long getDurationMin() {
    return durationMin;
  }

  public void setDurationMin(long durationMin) {
    this.durationMin = durationMin;
  }

  public Timestamp getPremiere_date() {
    return premiere_date;
  }

  public void setPremiere_date(Timestamp premiere_date) {
    this.premiere_date = premiere_date;
  }

  public byte[] getImage() {
    return image;
  }

  public void setImage(byte[] image) {
    this.image = image;
  }
}
