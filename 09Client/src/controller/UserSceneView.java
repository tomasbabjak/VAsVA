package controller;

import entity.Customer;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import javafx.scene.control.Label;
import java.io.IOException;
import java.util.logging.Logger;

public class UserSceneView {

    private static final Logger LOG = Logger.getLogger(UserSceneView.class.getName());

    Customer c;
    LoginView ancestor;
    private Stage stage;
    SceneCreator sceneCreator;

    public Label lastNameLabel;
    public Label firstNameLabel;
    public Label login;

    public void setAncestor(SceneCreator sceneCreator) {

    }

    public void setStage(Stage stage) {
        login.setText(c.getUsername());
        lastNameLabel.setText(c.getLastName());
        firstNameLabel.setText(c.getFirstName());
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
            sc.launchSceneMovies(c);
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
            sc.launchLogInScene();
            ((javafx.scene.Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            LOG.severe("opening logout scene");
        }
    }
}
