package controller;

import entity.Movie;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SelectedFilmView {
    public Button backButton;
    public ImageView selectedFilmPoster;
    public Text description;
    public Text startDate;
    public Text time;
    public Button bookButton;
    public Text title;

    public void backToPrevScene(ActionEvent actionEvent) {
    }

    public void goToBookingScene(ActionEvent actionEvent) {
    }


    public void buildScene(Movie movie, Image image){
        title.setText(movie.getTitle());
        description.setText(movie.getDescription());
//        Date date = new Date();
//        date.setTime(movie.getPremiere_date().getTime());
//        startDate.setText(new SimpleDateFormat("yyyyMMdd").format(date));
        time.setText(String.valueOf(movie.getDurationMin()));
        selectedFilmPoster.setImage(image);


    }

}
