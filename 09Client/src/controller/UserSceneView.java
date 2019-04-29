package controller;

import entity.Customer;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.Locale;
import java.util.ResourceBundle;

public class UserSceneView {

    private static final Logger LOG = Logger.getLogger(UserSceneView.class.getName());

    Customer c;
    LoginView ancestor;
    private Stage stage;
    SceneCreator sceneCreator;

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

    private String lan;

    private double xOffset;
    private double yOffset;

    public void setAncestor(SceneCreator sceneCreator) {

    }

        public void setLanguage(String lan){
        this.lan = lan;
        ResourceBundle rb =	ResourceBundle.getBundle("Label", Locale.forLanguageTag(lan));
        windowTitleLabel.setText(rb.getString("userViewLabel"));
        you.setText(rb.getString("loginInfo"));
        manageFilmsButton.setText(rb.getString("viewFilms"));
        manageBookingsButton.setText(rb.getString("viewBookings"));
        logOutButton.setText(rb.getString("logOutButton"));

    }

    public void setStage(Stage stage) {
        setPane();
        login.setText(c.getUsername());
        lastNameLabel.setText(c.getLastName());
        firstNameLabel.setText(c.getFirstName());
        //language.setItems(FXCollections.observableArrayList("en","sk","sw"));
        //language.getSelectionModel().select(lan);
        this.stage = stage;
    }

    public void setUser(Customer c) {
        this.c = c;
    }

    public void editInfoClick(ActionEvent actionEvent) {
    }

    public void sendEmailClick(ActionEvent actionEvent) {
    }

    public void manageMoviesClick(ActionEvent actionEvent) {
        SceneCreator sc = new SceneCreator();
        try {
            sc.launchSceneMovies(c,lan);
            ((javafx.scene.Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            LOG.severe("opening movies scene");
        }
    }

    public void manageBookingsClick(ActionEvent actionEvent) {
    }

    public void logOutClick(ActionEvent actionEvent){
        SceneCreator sc = new SceneCreator();
        try {
            sc.launchLogInScene(lan);
            ((javafx.scene.Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            LOG.severe("opening logout scene");
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
    public void changeLan(MouseEvent mouseEvent) {
        if(mouseEvent.getSource().equals(sk)) lan = "sk";
        if(mouseEvent.getSource().equals(en)) lan = "en";
        if(mouseEvent.getSource().equals(sw)) lan = "sw";

        setLanguage(lan);

    }
}
