module com.example.trspo_lab2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.trspo_lab2 to javafx.fxml;
    exports com.example.trspo_lab2;
}