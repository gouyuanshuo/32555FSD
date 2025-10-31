package edu.uts.uniapp.view.controller;


import edu.uts.uniapp.model.Database;
import edu.uts.uniapp.model.Student;
import edu.uts.uniapp.model.Subject;
import edu.uts.uniapp.util.RegexConstants;
import edu.uts.uniapp.view.IOText;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class EnrolmentController {
    @FXML private Label userLabel, countLabel, avgLabel, passLabel;
    private Student current;

    public void init(Student student) {
        this.current = student;
        userLabel.setText(IOText.GUI_LOGIN + student.getEmail());
        refresh();
    }

    @FXML
    private void onEnrol() {
        if (!current.canEnrollMore()) {
            showException(IOText.GUI_ENR_FAIL);
            return;
        }
        Subject s = new Subject();
        boolean inValidID=true;
        List<Student> all = Database.readAll();
        while (inValidID) {
            final Subject fSubject=s;
            if (all.stream().anyMatch((st)->{return st.getSubjects().stream().anyMatch((subject)->{return subject.getId()==fSubject.getId();});})){
                s = new Subject();
            }else{
                inValidID=false;
            }
        }
        current.enrollSubject(s);
        updateDB();
        refresh();
        showInfo("Enrolled subject:\nID " + s.getId() +
                " | Mark " + s.getMark() + " | Grade " + s.getGrade());
    }

    @FXML
    private void onRemove() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Remove Subject");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter subject id:");
        dialog.showAndWait().ifPresent(id -> {
            try {
                int sid = Integer.parseInt(id.trim());
                boolean ok = current.removeSubjectById(sid);
                if (ok) {
                    updateDB();
                    refresh();
                    showInfo("Subject removed.");
                } else showException("Subject not found.");
            } catch (NumberFormatException e) {
                showException("Invalid subject id.");
            }
        });
    }

    @FXML
    private void onShow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/uts/uniapp/view/subjects.fxml"));
            Scene scene = new Scene(loader.load());
            SubjectController sc = loader.getController();
            sc.init(current);
            Stage stage = new Stage();
            stage.setTitle("Enrolled Subjects");
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onChangePassword() {
        TextInputDialog dlg = new TextInputDialog();
        dlg.setTitle("Change Password");
        dlg.setHeaderText(null);
        dlg.setContentText("Enter new password:");
        dlg.showAndWait().ifPresent(pwd -> {
            if (pwd.isEmpty()) {
                showException("Password cannot be empty.");
            }else if(!pwd.matches(RegexConstants.PASSWORD)){
                showException(IOText.GUI_PWD_INCORRECT);
            }else {
                current.setPassword(pwd);
                updateDB();
                showInfo("Password changed successfully.\nYou may reuse the same password.");
            }
        });
    }

    @FXML
    private void onLogout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/uts/uniapp/view/login.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) userLabel.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("GUIUniApp - Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void refresh() {
        countLabel.setText(String.format("Subjects: %d/4", current.getSubjects().size()));
        avgLabel.setText(String.format("Average: %.1f", current.averageMark()));
        passLabel.setText("PASS: " + (current.isPass() ? "YES" : "NO"));
    }

    private void updateDB() {
        List<Student> all = Database.readAll();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getEmail().equalsIgnoreCase(current.getEmail())) {
                all.set(i, current);
                break;
            }
        }
        Database.writeAll(all);
    }

    private void showException(String msg) {
        ExceptionController.show(msg);
    }

    private void showInfo(String msg) {
        ExceptionController.info(msg);
    }
}
