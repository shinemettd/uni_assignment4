module com.example.uni_assignment4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.uni_assignment4 to javafx.fxml;
    exports com.example.uni_assignment4;
}