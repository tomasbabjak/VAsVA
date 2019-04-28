package controller;

import entity.Customer;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminView {

    private static final Logger LOG = Logger.getLogger(AdminView.class.getName());

    public Label lastNameLabel;
    public Label firstNameLabel;
    public Label login;
    Customer c;
    private Stage stage;

    public void setStage(Stage stage) {
        login.setText(c.getUsername());
        lastNameLabel.setText(c.getLastName());
        firstNameLabel.setText(c.getFirstName());
        this.stage = stage;
    }

    public void setUser(Customer c) {
        this.c = c;
    }

    public void sendEmailClick(ActionEvent actionEvent) {
    }

    public void editInfoClick(ActionEvent actionEvent) {
    }

    public void manageMoviesClick(ActionEvent actionEvent) {
        SceneCreator sc = new SceneCreator();
        try {
            sc.launchManageFilmScene(c);
            ((javafx.scene.Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            LOG.log(Level.SEVERE,"cannot open back scene",e);
        }
    }

    public void manageBookingsClick(ActionEvent actionEvent) {
        SceneCreator sc = new SceneCreator();
        try {
            sc.launchSceneMovies(c);
            ((javafx.scene.Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            LOG.log(Level.SEVERE,"cannot open booking scene",e);
        }
    }

    public void logOutClick(ActionEvent actionEvent) {
        SceneCreator sc = new SceneCreator();
        try {
            sc.launchLogInScene();
            ((javafx.scene.Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            LOG.log(Level.SEVERE,"cannot open back scene",e);
        }
    }
}
