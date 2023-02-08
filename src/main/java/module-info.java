module com.example.analogur3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.analogur3 to javafx.fxml;
    exports com.example.analogur3;
}