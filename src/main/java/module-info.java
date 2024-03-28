module isd.aims.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens isd.aims.main to javafx.fxml;
    opens isd.aims.main.views to javafx.fxml;
    opens isd.aims.main.views.home to javafx.fxml;
    exports isd.aims.main;
    exports isd.aims.main.views;
}
