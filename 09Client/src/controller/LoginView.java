package controller;

import entity.Customer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import testuj.CustomerManagerRemote;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.logging.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginView {

    private static final Logger LOG = Logger.getLogger(LoginView.class.getName());

    private static final String JNDI = "ejb:AE09/09WAR/CustomerManager!testuj.CustomerManagerRemote";

    CustomerManagerRemote cusRemote = null;

    public Label exitButt;
    public TextField usernameBox;
    public PasswordField passwordBox;
    public Button logInButton;
    public Button RegButton;
    public Text wrongCredentials;
    public Label notMemeber;
    public Label signIn;
    public ComboBox language;

    public String lan;

    public void exitButton(MouseEvent mouseEvent) {
        Platform.exit();
    }

    public void loginClick(ActionEvent actionEvent) {

        Customer user = null;
            LOG.entering(this.getClass().getName(),"entering logIn user");
            user = cusRemote.logIn(usernameBox.getText(),passwordBox.getText());

        if(user != null){
            SceneCreator sc = new SceneCreator();
            try {
                SceneCreator.setCurrentCustomer(user);
                if(user.isAdmin()) sc.launchAdminScene(user,lan);
                else sc.launchUserScene(user,lan);
                ((javafx.scene.Node) (actionEvent.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                LOG.log(Level.SEVERE,"IO Exeption",e);
                e.printStackTrace();
            }
        }
        else {
            LOG.log(Level.SEVERE,"user not found");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR Dialog");
            alert.setHeaderText("Wrong password or name");
            alert.setContentText("Please, try again");
            alert.showAndWait();
        }
        LOG.exiting(this.getClass().getName(),"exiting logIn");
    }

    public void registrateClick(ActionEvent actionEvent) {
        SceneCreator sc = new SceneCreator();
        try {
            sc.launchSceneRegistration(lan);
            ((javafx.scene.Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeLanguage(){
        lan = language.getSelectionModel().getSelectedItem().toString();
        ResourceBundle rb =	ResourceBundle.getBundle("Label", Locale.forLanguageTag(lan));
        logInButton.setText(rb.getString("logInButton"));
        RegButton.setText(rb.getString("regButton"));
        notMemeber.setText(rb.getString("notMember"));
        signIn.setText(rb.getString("logIn"));
    }

    public void initialize(){

        language.setItems(FXCollections.observableArrayList("en","sk","sw"));
        language.getSelectionModel().selectFirst();
        this.lan = language.getSelectionModel().getSelectedItem().toString();

        ResourceBundle rb =	ResourceBundle.getBundle("Label", Locale.forLanguageTag(language.getSelectionModel().getSelectedItem().toString()));
        try {
            Context ctx = new InitialContext();
            cusRemote = (CustomerManagerRemote) ctx.lookup(JNDI);
        }catch (NamingException e){
            LOG.log(Level.SEVERE,"Naming exeption, initialContext",e);
        }
        logInButton.setText(rb.getString("logInButton"));
        RegButton.setText(rb.getString("regButton"));
        notMemeber.setText(rb.getString("notMember"));
        signIn.setText(rb.getString("logIn"));
    }

}
