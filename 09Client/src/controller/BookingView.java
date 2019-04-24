package controller;

import entity.City;
import entity.Movie;
import entity.Screening;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import testuj.BookingManagerRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.File;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BookingView {

    int screening_id;
    Movie movie;
    long movie_id = 2;
    boolean City = false;
    int selected_day = -1;
    BookingManagerRemote bmr = null;


    static List<Integer> seats = new ArrayList<>();
    static List<Integer> obsadene = null;
    List<City> cities = null;
    List<Date> dates = null;
    List<String> times = new ArrayList<>();
    List<Screening> screenings = new ArrayList<>();

    public BorderPane border;
    public ImageView Image;
    public Pane seatPane;
    // String theater1 = "20x__16x\n.20x__16x\n__18x__14x\n.20x__16x\n20x__16x\n.20x__16x\n20x__16x\n";
    String theater1 = "...16x\n...16x\n.18x\n20x\n20x\n";
  //  String theater2 = "11x_10x_11x\n11x_10x_11x\n11x_10x_11x\n\n11x_10x_11x\n11x_10x_11x\n11x_10x_11x\n";

    private static final String JNDI = "ejb:AE09/09WAR/BookingManager!testuj.BookingManagerRemote";


    public ComboBox cityBox;
    public TextField movie_title;
    public Button backButton;
    public DatePicker datePicker;
    public ComboBox timeDropDownList;

    public void backToPrevScene(ActionEvent actionEvent) {
    }

    public void bookSeat(MouseEvent mouseEvent) {
        System.out.println(seats.toString());
    }


    public void picked_date(ActionEvent actionEvent) {
        if(City){
            fillTimes();
        }
    }

    public void pickedTime(ActionEvent actionEvent) {
    }

    public void selectedCity(ActionEvent actionEvent) {
       City city = (entity.City) cityBox.getSelectionModel().getSelectedItem();
       screenings = bmr.getDates(movie_id,city.getCityId());
       City = true;
     //  dates = bmr.getDates(movie_id,city.getCityId());
       fillTimes();
    }

    public void setScreening_id(int screening_id) {
        this.screening_id = screening_id;
    }


    private void fillTimes(){
       // Date date = new Date(datePicker.getValue().format("dd-mm-yyyy"));
        LocalDate ld = datePicker.getValue();
        Calendar c =  Calendar.getInstance();
        c.set(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
        Date date = c.getTime();
        selected_day = date.getDate();
        times = new ArrayList<>();
        for(Screening ele : screenings){
            if(ele.getScreeningStart().getDate() == date.getDate()){
                times.add(ele.getScreeningStart().getHours() + ":" + ele.getScreeningStart().getMinutes());
            }
        }
        timeDropDownList.setItems(FXCollections.observableArrayList(times));


    }


    public void init(){
        datePicker.setValue(LocalDate.now());
        try {
            Context ctx = new InitialContext();
            bmr = (BookingManagerRemote) ctx.lookup(JNDI);
        }catch (NamingException e){
            e.printStackTrace();
        }

        cities = bmr.getCities();
        cityBox.setItems(FXCollections.observableArrayList(cities));

        /*
        obsadene = bmr.getReservedSeats(screening_id);

        addTab("1",theater1(seatPane,theater1));
        File file = new File("C:\\Users\\minar\\Desktop\\VAVA_intellij\\09Client\\res\\platno.png");
        Image image = new Image(file.toURI().toString());
        Image.setCache(true);
        Image.setImage(image);
        Image.setPreserveRatio(true);
*/

    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void search(ActionEvent actionEvent) {

        seats.clear();
        Screening scree = screenings.stream().filter(e -> e.getScreeningStart().getDate() == selected_day)
                .filter(t -> ((String)timeDropDownList.getSelectionModel().getSelectedItem()).equals(t.getScreeningStart().getHours() + ":" + t.getScreeningStart().getMinutes()))
                .findFirst()
                .orElse(null);
        if(scree == null){
            System.out.println("nieco je yle");
            return;
        }
        obsadene = bmr.getReservedSeats((int) scree.getId());
        addTab("1",theater1(seatPane,theater1));
        File file = new File("C:\\Users\\minar\\Desktop\\VAVA_intellij\\09Client\\res\\platno.png");
        Image image = new Image(file.toURI().toString());
        Image.setCache(true);
        Image.setImage(image);
        Image.setPreserveRatio(true);
    }

    static class Seat extends Group {
        Color freeColor = Color.rgb(215, 215, 215);
        Color reservedColor = Color.rgb(142, 31,  27);
        Color choosen = Color.rgb(255,115,70);

        BooleanProperty iamReserved = new SimpleBooleanProperty(false);
        int myNo;
        public Seat(int no) {
            myNo = no;
            // Circle pillow = new Circle(15);
            Rectangle pillow = new Rectangle(30,40);
            if(obsadene.contains((long) no)) {
                pillow.setFill(reservedColor);
                pillow.setId(String.valueOf(-no));
            }
            else{
                pillow.setFill(freeColor);
                pillow.setId(String.valueOf(no));
            }
            pillow.setStrokeWidth(1);
            pillow.setStroke(Color.rgb(30, 40, 40));
            getChildren().add(pillow);

            Text lable = new Text(""+no);
            lable.setFont(lable.getFont().font(15));
            lable.setTextAlignment(TextAlignment.CENTER);
            //    lable.setBoundsType(TextBoundsType.VISUAL);
            lable.setTextOrigin(VPos.CENTER);
            lable.setLayoutX((-lable.getLayoutBounds().getWidth() / 2) + 15);
            lable.setLayoutY((-lable.getLayoutBounds().getHeight() / 2) + 20);
            getChildren().add(lable);

            iamReserved.addListener((e, o, n) -> {
                if(n){
                    pillow.setFill(choosen);
                    seats.add(Integer.valueOf(pillow.getId()));
                }
                else{
                    pillow.setFill(freeColor);
                    seats.remove(Integer.valueOf(pillow.getId()));
                }
                // pillow.setFill(n ? choosen : freeColor);
            });
            setOnMouseClicked(m -> {
                int selected_id = Integer.valueOf(((Rectangle) m.getTarget()).getId());
                if(selected_id >= 0) {
                    iamReserved.set(!iamReserved.get());

                }
            });
        }


        static double width() { return 32; }
        static double height() { return 45; }
    }

    Pane theater1(Pane pane, String theater) {
        double x = 20;
        double y = 40;
        int no = 1;
        for (String row : theater.split("\n")) {
            int count = 0;
            for (int c : row.toCharArray()) {
                switch (c) {
                    case 'x':
                        while (count-- > 0) {
                            Seat seat = new Seat(no++);
                            seat.setLayoutX(x);
                            x+= Seat.width();
                            seat.setLayoutY(y);
                            pane.getChildren().add(seat);
                        }
                        count = 0;
                        break;
                    case '0': case '1': case '2': case '3': case '4': case '5': case'6': case '7': case '8': case '9':
                        count = 10*count + (c-'0');
                        break;
                    case '_':
                        x+= Seat.width();
                        break;
                    case '.':
                        x+= Seat.width()/2;
                        break;
                    default: System.out.println("Unknown char: '"+(char)c+"'");
                }
            }
            y += Seat.height();
            count = 0;
            x = 20;
        }
        return pane;
    }

    void addTab(String label, Region node) {
    }

}