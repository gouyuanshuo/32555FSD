package edu.uts.uniapp.view.controller;



import edu.uts.uniapp.model.Database;
import edu.uts.uniapp.model.Student;
import edu.uts.uniapp.util.RegexConstants;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class LoginController {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    @FXML
    public void onLogin(ActionEvent event) {
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        if (email.isEmpty() || password.isEmpty()) {
            showError("Email and password cannot be empty.");
            return;
        }
        if (!email.matches(RegexConstants.EMAIL)) {
            showError("Invalid email format.\nEmails should end with @university.com");
            return;
        }
        if (!password.matches(RegexConstants.PASSWORD)) {
            showError("Invalid password format:\n" +
                    "Starts with uppercase, ≥5 letters, then ≥3 digits.");
            return;
        }

        List<Student> all = Database.readAll();
        Student found = all.stream()
                .filter(s -> s.getEmail().equalsIgnoreCase(email) && s.getPassword().equals(password))
                .findFirst()
                .orElse(null);

        if (found == null) {
            showError("Incorrect credentials.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/uts/cliuniapp/view/enrolment.fxml"));
            Scene scene = new Scene(loader.load());
            EnrolmentController controller = loader.getController();
            controller.init(found);
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.setTitle("GUIUniApp - Enrolment");
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onExit(ActionEvent e) {
        ((Stage) emailField.getScene().getWindow()).close();
    }

    private void showError(String msg) {
        errorLabel.setText(msg);
        errorLabel.setVisible(true);
    }
}
