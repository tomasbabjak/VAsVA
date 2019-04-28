package controller;

import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.application.Platform;
import testuj.CustomerManagerRemote;
import entity.Customer;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;


public class RegistrationView {

    private static final String JNDI = "ejb:AE09/09WAR/CustomerManager!testuj.CustomerManagerRemote";

    public TextField emil;
    public TextField fname;
    public TextField lname;
    public TextField login;
    public TextField pass;
    public TextField repass;
    public Label fn;
    public Label ln;
    public Label reg;
    public Label passs;
    public Label rpasss;
    public Label un;
    public Label message;
    public Button regButton;

    private String lan;

    public void initialize(String language){
        this.lan = language;
        ResourceBundle rb =	ResourceBundle.getBundle("Label", Locale.forLanguageTag(language));
        fn.setText(rb.getString("fNameLabel"));
        ln.setText(rb.getString("lNameLabel"));
        reg.setText(rb.getString("registrationLabel"));
        passs.setText(rb.getString("passwordLabel"));
        rpasss.setText(rb.getString("rpasswordLabel"));
        un.setText(rb.getString("nameLabel"));
        message.setText(rb.getString("messageLabel"));
        regButton.setText(rb.getString("regButton"));
    }
    public void register(javafx.event.ActionEvent actionEvent) {

        Customer user = null;

        if(emil.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR Dialog MAIL");
            alert.setHeaderText("Please write your mail.");
            alert.setContentText("Please write your mail.");
            alert.showAndWait();
        }else if(login.getText().isEmpty() || pass.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR Dialog");
            alert.setHeaderText("Invalid mail or password.");
            alert.setContentText("Invalid mail or password.");
            alert.showAndWait();
        } else if(!(pass.getText().equals(repass.getText()))){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR Dialog");
            alert.setHeaderText("Passwords mismatch.");
            alert.setContentText("Passwords are different.");
            alert.showAndWait();
        }else {
            try {
                Context ctx = new InitialContext();
                CustomerManagerRemote cusRemote = (CustomerManagerRemote) ctx.lookup(JNDI);
                cusRemote.registrate(emil.getText(), fname.getText(), lname.getText(), login.getText(), pass.getText());
                user = cusRemote.logIn(login.getText(),pass.getText());
            } catch (NamingException e) {
                e.printStackTrace();
            }

            SceneCreator sc = new SceneCreator();
            try {
                sc.setAncestorRV(this);
                sc.launchUserScene(user,lan);
                ((javafx.scene.Node) (actionEvent.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void exit(javafx.event.ActionEvent actionEvent)  {
        Platform.exit();
    }
}
