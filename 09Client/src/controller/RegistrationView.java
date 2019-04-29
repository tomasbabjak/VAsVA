package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import testuj.CustomerManagerRemote;
import entity.Customer;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Locale;
import java.util.ResourceBundle;

public class RegistrationView {

    private static final Logger LOG = Logger.getLogger(RegistrationView.class.getName());

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
    public Button backButton;
    public AnchorPane pane;

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
            LOG.entering(this.getClass().getName(),"enetering initialContext, registration");
            try {
                Context ctx = new InitialContext();
                CustomerManagerRemote cusRemote = (CustomerManagerRemote) ctx.lookup(JNDI);
                if(!cusRemote.registrate(emil.getText(), fname.getText(), lname.getText(), login.getText(), pass.getText())){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR Dialog");
                    alert.setHeaderText("User already exists!");
                    alert.setContentText("This username is already used. \nPlease choose new username.");
                    alert.showAndWait();
                }
                else{
                    user = cusRemote.logIn(login.getText(),pass.getText());
                    SceneCreator sc = new SceneCreator();
                    try {
                        sc.setAncestorRV(this);
                        sc.launchUserScene(user,lan);
                        ((javafx.scene.Node) (actionEvent.getSource())).getScene().getWindow().hide();
                    } catch (IOException e) {
                        LOG.log(Level.SEVERE,"IO Exception");
                    }
                }
                user = cusRemote.logIn(login.getText(),pass.getText());
            } catch (NamingException e) {
                LOG.log(Level.SEVERE,"Naming Exception registration",e);
            }
            LOG.exiting(this.getClass().getName(),"exiting registration");
        }
    }

    public void backToPrevScene(ActionEvent actionEvent) {
        SceneCreator sc = new SceneCreator();
        try {
            sc.launchLogInScene(lan);
            ((javafx.scene.Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            LOG.log(Level.SEVERE,"openig back scene faild");
        }

    }

}
