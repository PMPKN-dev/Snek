module com.snek {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.snek to javafx.fxml;
    exports com.snek;
}