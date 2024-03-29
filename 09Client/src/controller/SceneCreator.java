package controller;

import entity.Customer;
import entity.Movie;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.logging.Logger;

public class SceneCreator {

    private static final Logger LOG = Logger.getLogger(SceneCreator.class.getName());

    RegistrationView rv;
    LoginView lv;
    UserSceneView usv;
    static Customer currentCustomer;

    public void setAncestorRV(RegistrationView rv){this.rv = rv;}
    public void setAncestorLV(LoginView lv){this.lv = lv;}

    public void launchSceneSelectedFilm(String language,Customer c, Movie movie, Image image) throws IOException {

        SelectedFilmView controller;
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ViewSelectedFilmScene.fxml"));
        Parent parent = fxmlLoader.load();
        controller = fxmlLoader.getController();
        controller.setCustomer(c);
        controller.buildScene(movie,image,language);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public void launchSceneMovies(Customer c, String language) throws IOException {
        FilmSceneView controller;
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ViewFilmsScene.fxml"));
        Parent parent = fxmlLoader.load();
        controller = fxmlLoader.getController();
        controller.setUser(c);
        controller.initialize(language);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public void launchUserScene(Customer c, String language) throws IOException {
        UserSceneView controller;
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/UserScene.fxml"));
        Parent parent = fxmlLoader.load();
        controller = fxmlLoader.getController();
        controller.setLanguage(language);
        controller.setUser(c);
        controller.setStage(stage);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public void launchAdminScene(Customer c, String language) throws IOException {
        AdminView controller;
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AdminScene.fxml"));
        Parent parent = fxmlLoader.load();
        controller = fxmlLoader.getController();
        controller.setUser(c);
        controller.setLanguage(language);
        controller.setStage(stage);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(parent));
        stage.show();
    }


    public void launchSceneRegistration(String language) throws IOException {
        RegistrationView controller;
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/RegistrationScene.fxml"));
        Parent parent = fxmlLoader.load();
        controller = fxmlLoader.getController();
        controller.initialize(language);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public static Customer getCurrentCustomer() {
        return currentCustomer;
    }
    public static void setCurrentCustomer(Customer currentCustomer) {
        SceneCreator.currentCustomer = currentCustomer;
    }

    public void launchLogInScene(String language) throws IOException {
        LoginView controller;
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/LoginScene.fxml"));
        Parent parent = fxmlLoader.load();
        controller = fxmlLoader.getController();
        controller.setLanguage(language);
        controller.initialize();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(parent));
        stage.show();
    }


    public void launchManageFilmScene(Customer c, String language) throws IOException {
        ManageFilmsView controller;
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ManageFilmScene.fxml"));
        Parent parent = fxmlLoader.load();
        controller = fxmlLoader.getController();
        controller.setUser(c, language);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public void launchManageScreeningScene(Customer c, Movie movie) throws IOException {
        ManageScreeningView controller;
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ManageScreeningScene.fxml"));
        Parent parent = fxmlLoader.load();
        controller = fxmlLoader.getController();
        controller.setUser(c);
        controller.setMovie(movie);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(parent));
        stage.show();
    }

}
