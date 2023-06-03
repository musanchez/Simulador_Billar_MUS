module com.example.physicsenginev_0_1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;


    opens com.example.physicsenginev_0_1 to javafx.fxml;
    exports com.example.physicsenginev_0_1;
    exports com.example.physicsenginev_0_1.implement;
    opens com.example.physicsenginev_0_1.implement to javafx.fxml;
}