module isd.aims.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.web;

    opens isd.aims.main to javafx.fxml;
    opens isd.aims.main.views to javafx.fxml;
    opens isd.aims.main.views.home to javafx.fxml;
    opens isd.aims.main.views.popup to javafx.fxml;
    opens isd.aims.main.views.cart to javafx.fxml;
    opens isd.aims.main.views.shipping to javafx.fxml;
    opens isd.aims.main.views.invoice to javafx.fxml;
    opens isd.aims.main.views.payment to javafx.fxml;

    exports isd.aims.main;
    exports isd.aims.main.views;
}
