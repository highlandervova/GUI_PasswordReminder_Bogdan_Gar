package main;

import bean.Record;
import dao.RecordDAO;
import iostream.Serialize_DeserializeRecord;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.scene.Scene;
import javafx.stage.StageStyle;
import random.RandomService;
import static dao.RecordDAO.arrPR;

public class Controller implements Initializable {

    @FXML
    private Button exitButton;
    @FXML
    private Label messageLabel;
    @FXML
    private Label messageLabel2;
    @FXML
    private TextField addressField;
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField1;
    @FXML
    private TextField passwordField2;

    public void listButtonOnAction(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("showcontroller.fxml"));
        primaryStage.setTitle("Database");
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void exitButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void saveButtonOnAction(ActionEvent event) {
        if (addressField.getText().isBlank() &&
                loginField.getText().isBlank() &&
                passwordField1.getText().isBlank() ||
                passwordField2.getText().isBlank()) {
            messageLabel.setText("Некорректный ввод!");
        }
            String address = addressField.getText();
            String login = loginField.getText();
            String password;
            if (!passwordField1.getText().isEmpty()) {
                password = passwordField1.getText();
            } else {
                int tmp = Integer.parseInt(passwordField2.getText());
                StringBuffer sb = new StringBuffer(tmp);
                for (int i = 0; i < tmp; i++) {
                    sb.append(RandomService.getRandomSymbol());
                }
                password = String.valueOf(sb);
            }
            Record r = new Record(address, login, password, new Date());
            RecordDAO.add(r);
            arrPR.add(r);
            Serialize_DeserializeRecord.writeRecord(arrPR);
            messageLabel.setText("Данные сохранены");
    }

    public void passwordField2KeyTyped(KeyEvent event) {
        passwordField2.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                passwordField2.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        messageLabel2.setText("Вводите только цифры!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
