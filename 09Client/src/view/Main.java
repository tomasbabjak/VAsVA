package view;

import controller.LoginView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.logging.Logger;

public class Main extends Application {

    private static final Logger LOG = Logger.getLogger(Main.class.getName());

  //  private FilmSceneView controller;
    private LoginView controller;
  //  private BookingView controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
      //  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ViewFilmsScene.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/LoginScene.fxml"));
       // FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ManageBookingsScene.fxml"));
        Parent parent = fxmlLoader.load();
        controller = fxmlLoader.getController();
      //  controller.setScreening_id(1);
      //  controller.init();

        primaryStage.setTitle("Title");
        primaryStage.setScene(new Scene(parent));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

    static String getPath() {
        String path = ClassLoader.getSystemClassLoader().getResource(".").getPath();
        return path;
    }


    public static void main(String[] args) throws IOException {
        launch(args);
        Platform.exit();
    }


}
