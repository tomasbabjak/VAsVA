package controller;

import entity.Customer;
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
    public Text director;
    public Text cast;

    Movie movie;
    Customer c;

    public void setCustomer(Customer c){
        this.c = c;
    }

    public void backToPrevScene(ActionEvent actionEvent) {
        SceneCreator sc = new SceneCreator();
        try {
            sc.launchSceneMovies(c);
            ((javafx.scene.Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        ((javafx.scene.Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }


    public void buildScene(Movie movie, Image image){
        this.movie = movie;
//        Integer.toString(movie.getPremiere_date().getDate());
        title.setText(movie.getTitle());
        description.setText(movie.getDescription());
//        Date date = new Date();
//        date.setTime(movie.getPremiere_date().getTime());
//        startDate.setText(new SimpleDateFormat("yyyyMMdd").format(date));
        //time.setText(String.valueOf(movie.getDurationMin()));
        selectedFilmPoster.setImage(image);
        //startDate.setText(((movie.getPremiere_date()).toString()));
        startDate.setText((movie.getPremiere_date().getDate()) + "." + (movie.getPremiere_date().getMonth()) + "." + (movie.getPremiere_date().getYear() + 1900));
        director.setText(movie.getDirector());
        cast.setText(movie.getMovieCast());
    }

}
