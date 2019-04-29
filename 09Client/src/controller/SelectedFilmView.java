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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Locale;
import java.util.ResourceBundle;

public class SelectedFilmView {

    private static final Logger LOG = Logger.getLogger(SelectedFilmView.class.getName());

    public Button backButton;
    public ImageView selectedFilmPoster;
    public Text description;
    public Text startDate;
    public Button  bookButton;
    public Text title;
    public Text director;
    public Text cast;
    public Text windowTitleLabel;
    public Text premiereDate;
    public Text directorLabel;
    public Text castLabel;
    public AnchorPane pane;

    private double xOffset;
    private double yOffset;

    private String lan;

    Movie movie;
    Customer c;

    public void setCustomer(Customer c){
        this.c = c;
    }

    public void backToPrevScene(ActionEvent actionEvent) {
        SceneCreator sc = new SceneCreator();
        try {
            sc.launchSceneMovies(c,lan);
            ((javafx.scene.Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
           LOG.log(Level.SEVERE,"Back Scene opening",e);
        }
    }

    public void goToBookingScene(ActionEvent actionEvent) throws IOException {
        if(c.isAdmin()) {
            ManageScreeningView controller;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ManageScreeningScene.fxml"));
            Parent parent = fxmlLoader.load();
            controller = fxmlLoader.getController();
            controller.setMovie(movie);
            controller.setUser(c);
            controller.init(lan);

            Stage primaryStage = new Stage();
            primaryStage.setTitle("Title");
            primaryStage.setScene(new Scene(parent));
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.show();
            ((javafx.scene.Node) (actionEvent.getSource())).getScene().getWindow().hide();

        }

        else {
            BookingView controller;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ManageBookingsScene.fxml"));
            Parent parent = fxmlLoader.load();
            controller = fxmlLoader.getController();
            controller.setMovie(movie);
            controller.setScreening_id(1);
            controller.init(lan);

            Stage primaryStage = new Stage();
            primaryStage.setTitle("Title");
            primaryStage.setScene(new Scene(parent));
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.show();
            ((javafx.scene.Node) (actionEvent.getSource())).getScene().getWindow().hide();
        }
    }


    public void buildScene(Movie movie, Image image, String lan){
        setPane();
        this.lan = lan;
        ResourceBundle rb =	ResourceBundle.getBundle("Label", Locale.forLanguageTag(lan));
        windowTitleLabel.setText(rb.getString("userViewLabel"));
        directorLabel.setText(rb.getString("directorLabel"));
        premiereDate.setText(rb.getString("premiereDate"));
        castLabel.setText(rb.getString("castLabel"));
        backButton.setText(rb.getString("backButton"));

        this.movie = movie;
//        Integer.toString(movie.getPremiere_date().getDate());
        if(c.isAdmin())bookButton.setText(rb.getString("addScreeningButton"));
        else bookButton.setText(rb.getString("bookButton"));
        title.setText(movie.getTitle());
        description.setText(movie.getDescription());
        selectedFilmPoster.setImage(image);
        startDate.setText((movie.getPremiere_date().getDate()) + "." + (movie.getPremiere_date().getMonth()) + "." + (movie.getPremiere_date().getYear() + 1900));
        director.setText(movie.getDirector());
        cast.setText(movie.getMovieCast());
    }

    public void setPane(){
        pane.setOnMousePressed(e->{
                    xOffset = e.getSceneX();
                    yOffset = e.getSceneY();
                }
        );
        pane.setOnMouseDragged(e->{
                    pane.getScene().getWindow().setX(e.getScreenX() - xOffset);
                    pane.getScene().getWindow().setY(e.getScreenY() - yOffset);
                }
        );
    }
}
