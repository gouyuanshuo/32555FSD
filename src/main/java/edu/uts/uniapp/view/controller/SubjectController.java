package edu.uts.uniapp.view.controller;


import edu.uts.uniapp.model.Student;
import edu.uts.uniapp.model.Subject;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class SubjectController {
    @FXML private TableView<Subject> table;
    @FXML private TableColumn<Subject, String> idCol;
    @FXML private TableColumn<Subject, Number> markCol;
    @FXML private TableColumn<Subject, String> gradeCol;
    @FXML private Label summaryLabel;

    private Student current;

    public void init(Student student) {
        this.current = student;
        idCol.setCellValueFactory(c -> new SimpleStringProperty(String.format("%03d", c.getValue().getId())));
        markCol.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getMark()));
        gradeCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getGrade()));
        table.getItems().setAll(student.getSubjects());
        summaryLabel.setText(String.format("Average=%.1f, PASS=%s",
                student.averageMark(), student.isPass() ? "YES" : "NO"));
    }

    @FXML
    private void onClose() {
        Stage stage = (Stage) table.getScene().getWindow();
        stage.close();
    }
}
