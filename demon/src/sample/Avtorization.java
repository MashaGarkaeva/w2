package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Avtorization {

    Connection conn = new DbConnection().getConnection();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button enterButton;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button caphaButton;

    @FXML
    private TextField caphaField;

    public Avtorization() throws SQLException, ClassNotFoundException {
    }

    @FXML
    void initialize() {
        String simCode = "qwertyuiopasdfghjkzxcvbnmQWERTYUOASDFGHJKLZXCVBNM1234567890!@#$%^&*>?";
        Random random = new Random();
        char sim;
        String codes = "";
        int index;
        for (int i = 0; i < 4; i++){
            index = random.nextInt(simCode.length());
            sim = simCode.charAt(index);
            codes += sim;}

        String finalCodes = codes;
        caphaButton.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Сообщение");
            alert.setHeaderText(finalCodes);
            alert.showAndWait();

        });
        enterButton.setOnAction(event -> {
            String textLogin = loginField.getText().trim();
            String textPassword = passwordField.getText().trim();
            String textCapha = caphaField.getText().trim();
            if (!textLogin.equals("") & !textPassword.equals("") & !textCapha.equals("")){
                if (textCapha.equals(finalCodes)){
                    try {
                        loginUser(textLogin,textPassword);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Неправилный код!");
                    alert.setContentText("Повторите попытку позже!");
                    alert.showAndWait();
                }
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Поля не заполнены!");
                alert.setContentText("Заполните все поля!");
                alert.showAndWait();
            }

        });


    }

    private void loginUser(String textLogin, String textPassword) throws SQLException {
        try {
            conn = getDbConnection();
            ResultSet resSet1 = conn.createStatement().executeQuery("SELECT * FROM Jury");
            while (resSet1.next()) {
                if (resSet1.getString("телефон").equals(textLogin) &&
                        resSet1.getString("пароль").equals(textPassword)){
                    enterButton.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("JuryWindow.fxml"));

                    try {
                        Parent root = loader.load();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }}
                break;


            }
            ResultSet resSet2 = conn.createStatement().executeQuery("SELECT * FROM Moder");
            while (resSet2.next()) {
                if (resSet2.getString("телефон").equals(textLogin) &&
                        resSet2.getString("пароль").equals(textPassword)){
                    enterButton.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("ModerWindow.fxml"));

                    try {
                        Parent root = loader.load();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }}
                break;


            }

            ResultSet resSet3 = conn.createStatement().executeQuery("SELECT * FROM Organizers");
            while (resSet3.next()) {
                if (resSet3.getString("телефон").equals(textLogin) &&
                        resSet3.getString("пароль").equals(textPassword)){
                    enterButton.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("OrganizatorWindow.fxml"));

                    try {
                        Parent root = loader.load();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    };}
                break;


            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionUrl =
                "jdbc:sqlserver://127.0.0.1:1433;"
                        + "database = w1;"
                        + "user = a1;"
                        + "password = a1;"
                        + "encrypt = false;";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn = DriverManager.getConnection(connectionUrl);
        return conn;
    }


}
