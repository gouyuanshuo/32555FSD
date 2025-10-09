package edu.uts.uniapp.view.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ExceptionController {
    @FXML private Label titleLabel;
    @FXML private Label messageLabel;

    @FXML
    public void onOk() {
        ((Stage) messageLabel.getScene().getWindow()).close();
    }

    public void init(String title, String msg) {
        titleLabel.setText(title);
        messageLabel.setText(msg);
    }

    public static void show(String msg) {
        open("Exception", msg);
    }

    public static void info(String msg) {
        open("Information", msg);
    }

    private static void open(String title, String msg) {
        try {
            FXMLLoader loader = new FXMLLoader(ExceptionController.class.getResource("/edu/uts/cliuniapp/view/exception.fxml"));
            Scene scene = new Scene(loader.load());
            ExceptionController ctrl = loader.getController();
            ctrl.init(title, msg);

            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
