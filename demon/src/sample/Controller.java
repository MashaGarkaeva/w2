package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Controller {

    Connection conn = new DbConnection().getConnection();

    private ObservableList<Event> events = FXCollections.observableArrayList();

    @FXML
    private Button authButton;

    @FXML
    private TableView<Event> tableEvent;

    @FXML
    private TableColumn<Event, String> nameEvent;

    @FXML
    private TableColumn<Event, String> dataEvent;

    public Controller() throws SQLException, ClassNotFoundException {
    }

    @FXML
    void initialize() throws SQLException {
        authButton.setOnAction(event -> {
            authButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Avtorization.fxml"));

            try {
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            });

        showEvents();
        nameEvent.setCellValueFactory(new PropertyValueFactory<Event, String>("NameEventColum"));
        dataEvent.setCellValueFactory(new PropertyValueFactory<Event, String>("DataEventColum"));
        // заполняем таблицу данными
        tableEvent.setItems(events);

    }

    private void showEvents() throws SQLException {
        events.clear();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Events");
        while (resultSet.next()){
            events.add(new Event(resultSet.getString("Событие"), resultSet.getString("DATE")));
        }

        tableEvent.setItems(events);

}}
