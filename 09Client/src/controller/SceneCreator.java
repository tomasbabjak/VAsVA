package controller;

import entity.Movie;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import view.Main;

import java.awt.*;
import java.io.IOException;

public class SceneCreator {

    public void launchSceneSelectedFilm(Movie movie, Image image) throws IOException {

        SelectedFilmView controller;
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ViewSelectedFilmScene.fxml"));
        Parent parent = fxmlLoader.load();
        controller = fxmlLoader.getController();
        controller.buildScene(movie,image);
        stage.setTitle("Title");
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public void launchSceneMovies() throws IOException {
        FilmSceneView controller;
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ViewFilmsScene.fxml"));
        Parent parent = fxmlLoader.load();
        controller = fxmlLoader.getController();
        stage.setTitle("Title");
        stage.setScene(new Scene(parent));
        stage.show();
    }
}
