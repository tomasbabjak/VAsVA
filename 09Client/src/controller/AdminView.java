package controller;

import entity.Customer;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Locale;
import java.util.ResourceBundle;

public class AdminView {

    private static final Logger LOG = Logger.getLogger(AdminView.class.getName());

    public Label lastNameLabel;
    public Label firstNameLabel;
    public Label login;
    public Label windowTitleLabel;
    public Label you;
    public Button manageFilmsButton;
    public Button manageBookingsButton;
    public Button logOutButton;
    //public ComboBox language;
    public AnchorPane pane;
    public ImageView sk;
    public ImageView en;
    public ImageView sw;
    public ImageView logo;
    public Label welcome;

    private double xOffset;
    private double yOffset;

    Customer c;
    private Stage stage;
    private String lan;

    public void setStage(Stage stage) {
        setPane();
        login.setText(c.getUsername());
        lastNameLabel.setText(c.getLastName());
        firstNameLabel.setText(c.getFirstName());
        //language.setItems(FXCollections.observableArrayList("en","sk","sw"));
        //language.getSelectionModel().select(lan);
        this.stage = stage;
    }

        public void setLanguage(String lan){
        this.lan = lan;
        ResourceBundle rb =	ResourceBundle.getBundle("Label", Locale.forLanguageTag(lan));
        windowTitleLabel.setText(rb.getString("adminViewLabel"));
        you.setText(rb.getString("loginInfo"));
        manageFilmsButton.setText(rb.getString("addFilm"));
        manageBookingsButton.setText(rb.getString("addScreening"));
        logOutButton.setText(rb.getString("logOutButton"));
        welcome.setText(rb.getString("welcome"));
        }

    public void setUser(Customer c) {
        this.c = c;
    }

    public void manageMoviesClick(ActionEvent actionEvent) {
        SceneCreator sc = new SceneCreator();
        try {
            sc.launchManageFilmScene(c,lan);
            ((javafx.scene.Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            LOG.log(Level.SEVERE,"cannot open back scene",e);
        }
    }

    public void manageBookingsClick(ActionEvent actionEvent) {
        SceneCreator sc = new SceneCreator();
        try {
            sc.launchSceneMovies(c,lan);
            ((javafx.scene.Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            LOG.log(Level.SEVERE,"cannot open booking scene",e);
        }
    }

    public void logOutClick(ActionEvent actionEvent) {
        SceneCreator sc = new SceneCreator();
        try {
            sc.launchLogInScene(lan);
            ((javafx.scene.Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            LOG.log(Level.SEVERE,"cannot open back scene",e);
        }
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

    public void initialize() {
        try {
            String projdir = System.getProperty("user.dir");
            File file = new File(projdir + "\\09Client\\res\\images\\sk.png");
            sk.setImage(new Image(String.valueOf(file.toURL())));
            file = new File(projdir + "\\09Client\\res\\images\\en.png");
            en.setImage(new Image(String.valueOf(file.toURL())));
            file = new File(projdir + "\\09Client\\res\\images\\sw.png");
            sw.setImage(new Image(String.valueOf(file.toURL())));
            file = new File(projdir + "\\09Client\\res\\images\\unicinema1.png");
            logo.setImage(new Image(String.valueOf(file.toURL())));

        } catch (MalformedURLException e) {
            LOG.severe("cannot load flags image");
        }

    }

        public void changeLan(MouseEvent mouseEvent) {
        if(mouseEvent.getSource().equals(sk)) lan = "sk";
        if(mouseEvent.getSource().equals(en)) lan = "en";
        if(mouseEvent.getSource().equals(sw)) lan = "sw";

        setLanguage(lan);

    }
}
