module com.example.trspo_lab1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.trspo_lab1 to javafx.fxml;
    exports com.example.trspo_lab1;
}