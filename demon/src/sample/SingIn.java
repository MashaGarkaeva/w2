package sample;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.Random;

public class SingIn {
    @FXML
    private Label id;
    @FXML
    private ComboBox pol;
    @FXML
    void initialize(){
        String simCode = "0123456789";
        Random random = new Random();
        char sim;
        String codes = "";
        int index;
        for (int i = 0; i < 4; i++){
            index = random.nextInt(simCode.length());
            sim = simCode.charAt(index);
            codes += sim;}

        String finalCodes = codes;
        id.setText(finalCodes);



    }
}
