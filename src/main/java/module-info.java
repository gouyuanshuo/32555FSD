module edu.uts.uniapp {
    requires javafx.controls;
    requires javafx.fxml;

    // 给 FXML 反射访问
    opens edu.uts.uniapp.view.controller to javafx.fxml;
    opens edu.uts.uniapp.view to javafx.fxml;

    exports edu.uts.uniapp.view;
    exports edu.uts.uniapp.model;

}