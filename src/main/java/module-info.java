module isd.aims.main {
    requires javafx.controls;
    requires javafx.fxml;


    opens isd.aims.main to javafx.fxml;
    exports isd.aims.main;
}