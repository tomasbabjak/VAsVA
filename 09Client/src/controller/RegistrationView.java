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
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistrationView {

    private static final Logger LOG = Logger.getLogger(RegistrationView.class.getName());

    private static final String JNDI = "ejb:AE09/09WAR/CustomerManager!testuj.CustomerManagerRemote";

    public TextField emil;
    public TextField fname;
    public TextField lname;
    public TextField login;
    public TextField pass;
    public TextField repass;

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
            LOG.entering(this.getClass().getName(),"enetering initialContext, registration");
            try {
                Context ctx = new InitialContext();
                CustomerManagerRemote cusRemote = (CustomerManagerRemote) ctx.lookup(JNDI);
                cusRemote.registrate(emil.getText(), fname.getText(), lname.getText(), login.getText(), pass.getText());
                user = cusRemote.logIn(login.getText(),pass.getText());
            } catch (NamingException e) {
                LOG.log(Level.SEVERE,"Naming Exception registration",e);
            }
            LOG.exiting(this.getClass().getName(),"exiting registration");

            SceneCreator sc = new SceneCreator();
            try {
                sc.setAncestorRV(this);
                sc.launchUserScene(user);
                ((javafx.scene.Node) (actionEvent.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                LOG.log(Level.SEVERE,"IO Exception");
            }
        }
    }

    public void exit(javafx.event.ActionEvent actionEvent)  {
        Platform.exit();
    }
}
