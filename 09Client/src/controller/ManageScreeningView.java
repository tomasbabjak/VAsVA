package controller;

import entity.Auditorium;
import entity.City;
import entity.Customer;
import entity.Movie;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import testuj.ScreeningManagerRemote;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManageScreeningView {

    private static final Logger LOG = Logger.getLogger(ManageScreeningView.class.getName());

    private static final String JNDI1= "ejb:AE09/09WAR/ScreeningManager!testuj.ScreeningManagerRemote";

    public Button backButton;
    public ComboBox cityBox;
    public ComboBox auditoriumBox;
    public TextField movie_title;
    public DatePicker datePicker;
    public ComboBox timeDropDownListHours;
    public ComboBox timeDropDownListMinutes;
    public Text timeLabel;
    public Label cityLabel;
    public Text dateLabel;
    public Button addScreening;

    private String lan;

    Customer c;
    Movie movie;
    City selectedCity;
    Auditorium selectedAuditorium;
    List<City> cities = null;
    List<Auditorium> auditoriums = null;
    ScreeningManagerRemote smr = null;

    public void setUser(Customer c){
        this.c = c;
    }
    public void setMovie(Movie movie){this.movie = movie;}

    public void backToPrevScene(ActionEvent actionEvent) {
        SceneCreator sc = new SceneCreator();
        try {
            sc.launchSceneMovies(c,lan);
            ((javafx.scene.Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
           LOG.log(Level.SEVERE,"backScene opening",e);
        }
    }

    public void init(String lan){
        this.lan = lan;
        ResourceBundle rb =	ResourceBundle.getBundle("Label", Locale.forLanguageTag(lan));
        backButton.setText(rb.getString("backButton"));
        cityLabel.setText(rb.getString("cityLabel"));
        timeLabel.setText(rb.getString("timeLabel"));
        dateLabel.setText(rb.getString("dateLabel"));
        addScreening.setText(rb.getString("addScreening"));

        movie_title.setText(movie.getTitle());
        datePicker.setValue(LocalDate.now());

        try {
            Context ctx = new InitialContext();
            smr = (ScreeningManagerRemote) ctx.lookup(JNDI1);
        }catch (NamingException e){
            LOG.log(Level.SEVERE,"InitialContext",e);
        }

        cities = smr.getCities();
        cityBox.setItems(FXCollections.observableArrayList(cities));
        timeDropDownListHours.setItems(FXCollections.observableArrayList(10,11,12,13,14, 15, 16, 17, 18, 19, 20, 21, 22, 23)); //sprav z toho list
        timeDropDownListMinutes.setItems(FXCollections.observableArrayList(00, 10, 20, 30, 40, 50)); //aj tu

        timeDropDownListHours.getSelectionModel().selectFirst();
        timeDropDownListMinutes.getSelectionModel().selectFirst();
    }


    public void selectedCity(ActionEvent actionEvent) {
        selectedCity = (City)(cityBox.getSelectionModel().getSelectedItem());
        try {
            Context ctx = new InitialContext();
            smr = (ScreeningManagerRemote) ctx.lookup(JNDI1);
        }catch (NamingException e){
            LOG.log(Level.SEVERE,"InitialContext",e);
        }
        System.out.println(selectedCity.getCityName());
        auditoriums = smr.getAuditoriums(selectedCity.getCityId());
        auditoriumBox.setItems(FXCollections.observableArrayList(auditoriums));
        System.out.println(selectedCity.getCityName());
        System.out.println(auditoriums.toString());

    }

    public void selectedAuditorium(ActionEvent actionEvent) {
        selectedAuditorium = (Auditorium)(auditoriumBox.getSelectionModel().getSelectedItem());
    }

    public void createScreening(MouseEvent mouseEvent) {
        try{
            if (selectedCity.equals("") || selectedAuditorium.equals(""))
                throw new NullPointerException("Please complete all fields!");
        } catch (NullPointerException e) {
            LOG.log(Level.FINEST,"not complete fields screating screening");
            throw new NullPointerException("Please complete all fields!");
        }

        try {
            LocalDate ld = datePicker.getValue();
            Calendar c =  Calendar.getInstance();
            int hour = (int)timeDropDownListHours.getSelectionModel().getSelectedItem();
            int minute = (int) timeDropDownListMinutes.getSelectionModel().getSelectedItem();
            c.set(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth(), hour, minute, 0);

            Timestamp timestamp = new Timestamp(c.getTimeInMillis());
            Context ctx = new InitialContext();
            ScreeningManagerRemote screeningManagerRemote = (ScreeningManagerRemote) ctx.lookup(JNDI1);
            screeningManagerRemote.setScreening(movie,selectedAuditorium,timestamp);

        } catch (NamingException e) {
            e.printStackTrace();
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "New Screening has been added!", ButtonType.OK);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            SceneCreator sc = new SceneCreator();
            try {
                sc.launchSceneMovies(c,lan);
                ((javafx.scene.Node) (mouseEvent.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                LOG.severe("opening movies scene");
            }
        }
    }



    public void picked_date(ActionEvent actionEvent) {
    }
}
