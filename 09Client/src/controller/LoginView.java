package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
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
    public Text wrongCredentials;

    public void exitButton(MouseEvent mouseEvent) {
        Platform.exit();
    }

    public void loginClick(ActionEvent actionEvent) {

        boolean checked = false;

        try {
            Context ctx = new InitialContext();
            CustomerManagerRemote cusRemote = (CustomerManagerRemote) ctx.lookup(JNDI);
            checked = cusRemote.logIn(usernameBox.getText(),passwordBox.getText());
        }catch (NamingException e){
            e.printStackTrace();
        }

        if(checked){
            SceneCreator sc = new SceneCreator();
            try {
                sc.launchSceneMovies();
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
}
