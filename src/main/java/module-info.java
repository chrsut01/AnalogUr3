module com.example.analogur3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.analogtur to javafx.fxml;
    exports com.example.analogtur;
}