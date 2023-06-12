package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalTime;

public class OrganizatorWindow {

    @FXML
    public Label text;
    @FXML
    public Button jury;

    public static final LocalTime DAY_TIME = LocalTime.of(11,0);
    public static final LocalTime EVENING_TIME = LocalTime.of(18,0);
    public static final LocalTime NIGHT_TIME = LocalTime.of(23,0);

    @FXML
    public void initialize() {
        jury.setOnAction(event -> {
            jury.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("SingIn.fxml"));

            try {
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }
    public void getHelloString (LocalTime curTime){
        Label text = new Label();
        if (curTime.isBefore(DAY_TIME)) {
            text.setText("Доброе утро");
        } else if (curTime.isBefore(EVENING_TIME)) {
            text.setText("Добрый день");
        } else if (curTime.isBefore(NIGHT_TIME)) {
            text.setText("Добрый вечер");
        } else {
            text.setText("Доброй ночи");
        }
    }
}
