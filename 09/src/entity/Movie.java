package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Movie {

  @Id
  @GeneratedValue
  private long id;
  private String title;
  private String director;
  private String movieCast;
  private String description;
  private long durationMin;


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

}
