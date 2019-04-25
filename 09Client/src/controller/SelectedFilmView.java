package controller;

import entity.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SelectedFilmView {
    public Button backButton;
    public ImageView selectedFilmPoster;
    public Text description;
    public Text startDate;
    public Text time;
    public Button bookButton;
    public Text title;
    Movie movie;

    public void backToPrevScene(ActionEvent actionEvent) {
    }

    public void goToBookingScene(ActionEvent actionEvent) throws IOException {
        BookingView controller;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ManageBookingsScene.fxml"));
        Parent parent = fxmlLoader.load();
        controller = fxmlLoader.getController();
        controller.setMovie(movie);
        controller.setScreening_id(1);
        controller.init();

        Stage primaryStage = new Stage();
        primaryStage.setTitle("Title");
        primaryStage.setScene(new Scene(parent));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }


    public void buildScene(Movie movie, Image image){
        this.movie = movie;
        title.setText(movie.getTitle());
        description.setText(movie.getDescription());
//        Date date = new Date();
//        date.setTime(movie.getPremiere_date().getTime());
//        startDate.setText(new SimpleDateFormat("yyyyMMdd").format(date));
        time.setText(String.valueOf(movie.getDurationMin()));
        selectedFilmPoster.setImage(image);


    }

}
