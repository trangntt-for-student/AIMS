module isd.aims.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens isd.aims.main to javafx.fxml;
    exports isd.aims.main;
}