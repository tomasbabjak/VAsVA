package controller;

import entity.Customer;
import entity.Movie;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import testuj.dbtestRemote;
import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Locale;
import java.util.ResourceBundle;

public class FilmSceneView{

    private static final Logger LOG = Logger.getLogger(FilmSceneView.class.getName());

    private static final String JNDI = "ejb:AE09/09WAR/dbtest!testuj.dbtestRemote";

    public Button backButton;
    public GridPane grid;
    public ScrollPane scrollPane;
    @FXML
    ImageView pic;
    @FXML
    Image image;
    @FXML
    String id;
    @FXML
    Text allFilms;

    List<Movie> movies = null;
    HBox hb = new HBox();

    Customer c;

    private String lan;

    public void setUser(Customer c) {
        this.c = c;
    }

    public void backToPrevScene(ActionEvent actionEvent) {
        SceneCreator sc = new SceneCreator();
        try {
            if(c.isAdmin()){
                sc.launchAdminScene(c,lan);
            }
            else {
                sc.launchUserScene(c,lan);
            }
            ((javafx.scene.Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            LOG.log(Level.SEVERE,"openig back scene faild");
        }

    }


    public void initialize(String lan) {
        this.lan = lan;
        ResourceBundle rb =	ResourceBundle.getBundle("Label", Locale.forLanguageTag(lan));

        try {
            Context ctx = new InitialContext();
            dbtestRemote dbtestRemote = (dbtestRemote) ctx.lookup(JNDI);
            movies = dbtestRemote.getMovies();
        }catch (NamingException e){
           LOG.log(Level.SEVERE,"Initial context faild",e);
        }

        backButton.setText(rb.getString("backButton"));
        allFilms.setText(rb.getString("allFilms"));

        int listsize = -1;

            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

            grid.setPadding(new Insets(7,7,7,7));
            grid.setHgap(10);
            grid.setVgap(10);

            int rows = (movies.size() / 4) + 1;
            int columns = 4;
            int imageIndex = 0;

            for (int i = 0 ; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (imageIndex < movies.size()) {
                        addImage(imageIndex,j,i);
                        imageIndex++;

                    }
                }
            }
    }

    private void addImage(int index, int colIndex, int rowIndex) {

        String picId = String.valueOf(movies.get(index).getId());
        Image fximage = SwingFXUtils.toFXImage(convertImage(movies.get(index).getImage()), null);
        pic = new ImageView();
        pic.setFitWidth(160);
        pic.setFitHeight(220);
        pic.setImage(fximage);
        pic.setId(picId);
        hb.getChildren().add(pic);
        GridPane.setConstraints(pic, colIndex, rowIndex, 1, 1, HPos.CENTER, VPos.CENTER);
        grid.getChildren().addAll(pic);

        pic.setOnMouseClicked(e -> {
            ImageView selected = (ImageView) e.getTarget();
            long selectedId = Long.valueOf(selected.getId());
            try {
                Movie movie = movies.stream().filter(i -> i.getId() == selectedId).findFirst().orElse(null);
                if(movie == null){
                    LOG.log(Level.WARNING,"movie was not found by poster");
                }
                SceneCreator sc = new SceneCreator();
                sc.launchSceneSelectedFilm(lan, c, movie, SwingFXUtils.toFXImage(convertImage(movie.getImage()), null));
                ((javafx.scene.Node) (e.getSource())).getScene().getWindow().hide();
            } catch (IOException ex) {
                LOG.log(Level.WARNING,"IO Exception");
            }
        });

    }


    private BufferedImage convertImage(byte[] pole) {
        InputStream in = new ByteArrayInputStream(pole);
        try {
            return ImageIO.read(in);
        } catch (IOException e) {
            LOG.log(Level.WARNING,"image read faild");
        }
        return null;
    }






}
