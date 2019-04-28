package controller;

import entity.Customer;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import testuj.CustomerManagerRemote;
import testuj.FilmManagerRemote;

import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManageFilmsView {

    private static final Logger LOG = Logger.getLogger(ManageFilmsView.class.getName());

    private static final String JNDI = "ejb:AE09/09WAR/FilmManager!testuj.FilmManagerRemote";

    private File selectedImage;
    @FXML
    DatePicker filmPremiereDate;
    @FXML
    TextField filmTrailer;
    @FXML
    TextArea filmDescription;
    @FXML
    Button backButton;
    @FXML
    TextField filmTitle;
    @FXML
    TextArea filmCast;
    @FXML
    TextField filmDirector;
    @FXML
    TextField filmDuration;

    Customer c;

    public void setUser(Customer c){
        this.c = c;
    }

    public void updateFilmText(KeyEvent keyEvent) {
    }

    public void storeFilmInfo(ActionEvent actionEvent) {

        try{
            if (filmTitle.getText().equals("") || filmDescription.getText().equals("") || filmTrailer.getText().equals(""))
                throw new InputMismatchException("Please complete all fields!");
            else if (selectedImage == null) {
                LOG.warning("not uploaded image");
                throw new InputMismatchException("Please add the film poster!");
            }
        } catch (NullPointerException e) {
            LOG.warning("not complete fields");
        throw new InputMismatchException("Please complete all fields!");
        }

        try {
            LocalDate ld = filmPremiereDate.getValue();
            Calendar c =  Calendar.getInstance();
            c.set(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
            Timestamp timestamp = new Timestamp(c.getTimeInMillis());
            Context ctx = new InitialContext();
            FilmManagerRemote filmManagerRemote = (FilmManagerRemote) ctx.lookup(JNDI);
            filmManagerRemote.addFilm(filmTitle.getText(), filmDirector.getText(), filmCast.getText(), timestamp, filmDescription.getText(), Long.parseLong(filmDuration.getText()), Files.readAllBytes(selectedImage.toPath()));

        } catch (NamingException | IOException e) {
            LOG.log(Level.SEVERE,"Naming Exeption, Initial Context Film manager",e);
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "New Film has been added!", ButtonType.OK);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            SceneCreator sc = new SceneCreator();
            try {
                sc.launchAdminScene(c);
                ((javafx.scene.Node) (actionEvent.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                LOG.log(Level.SEVERE,"opening admin scene",e);
            }
        }
    }

    public void updateDateAndTime(ActionEvent actionEvent) {
    }

    public void backToPrevScene(ActionEvent actionEvent) {
        SceneCreator sc = new SceneCreator();
        try {
            sc.launchAdminScene(c);
            ((javafx.scene.Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            LOG.log(Level.SEVERE,"opening previous scene",e);
        }
    }

    @FXML
    public void uploadImageClick(ActionEvent event) throws IOException {

        try {
            FileChooser fc = new FileChooser();
            selectedImage = fc.showOpenDialog(null);
            // checking that input file is not null and handling the exception
            if (selectedImage == null)
                return;
            else if (ImageIO.read(selectedImage) == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please upload an image in JPG or PNG format!",
                        ButtonType.OK);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) {
                    return;
                }
            } else {
                Image img = SwingFXUtils.toFXImage(ImageIO.read(selectedImage), null);
            }
        } catch (FileNotFoundException ex) {
            LOG.log(Level.SEVERE,"opening poster image faild",ex);
        }
    }

}
