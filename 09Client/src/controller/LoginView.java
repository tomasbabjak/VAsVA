package controller;

import entity.Customer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import testuj.CustomerManagerRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;

public class LoginView {

    private static final String JNDI = "ejb:AE09/09WAR/CustomerManager!testuj.CustomerManagerRemote";

    public Label exitButt;
    public TextField usernameBox;
    public PasswordField passwordBox;
    public Button logInButton;
    public Button RegButton;
    public Text wrongCredentials;

    public void exitButton(MouseEvent mouseEvent) {
        Platform.exit();
    }

    public void loginClick(ActionEvent actionEvent) {

        Customer user = null;

        try {
            Context ctx = new InitialContext();
            CustomerManagerRemote cusRemote = (CustomerManagerRemote) ctx.lookup(JNDI);
            user = cusRemote.logIn(usernameBox.getText(),passwordBox.getText());
        }catch (NamingException e){
            e.printStackTrace();
        }

        if(user != null){
            SceneCreator sc = new SceneCreator();
            try {
                sc.launchUserScene(user);
                SceneCreator.setCurrentCustomer(user);
                if(user.isAdmin()) sc.launchAdminScene(user);
                else sc.launchUserScene(user);
                ((javafx.scene.Node) (actionEvent.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR Dialog");
            alert.setHeaderText("Wrong password or name");
            alert.setContentText("Please, try again");
            alert.showAndWait();
        }

    }

    public void registrateClick(ActionEvent actionEvent) {
        SceneCreator sc = new SceneCreator();
        try {
            sc.launchSceneRegistration();
            ((javafx.scene.Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
