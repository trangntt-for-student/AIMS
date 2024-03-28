package isd.aims.main.views.invoice;

import java.io.IOException;
import java.sql.SQLException;

import isd.aims.main.entity.order.OrderMedia;
import isd.aims.main.utils.Utils;
import isd.aims.main.views.FXMLScreenHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MediaInvoiceScreenHandler extends FXMLScreenHandler {

    @FXML
    private HBox hboxMedia;

    @FXML
    private VBox imageLogoVbox;

    @FXML
    private ImageView image;

    @FXML
    private VBox description;

    @FXML
    private Label title;

    @FXML
    private Label numOfProd;

    @FXML
    private Label labelOutOfStock;

    @FXML
    private Label price;

    private OrderMedia orderMedia;

    public MediaInvoiceScreenHandler(String screenPath) throws IOException{
        super(screenPath);
    }

    public void setOrderMedia(OrderMedia orderMedia) throws SQLException{
        this.orderMedia = orderMedia;
        setMediaInfo();
    }

    public void setMediaInfo() throws SQLException{
        title.setText(orderMedia.getMedia().getTitle());
        price.setText(Utils.getCurrencyFormat(orderMedia.getPrice()));
        numOfProd.setText(String.valueOf(orderMedia.getQuantity()));
        setImage(image, orderMedia.getMedia().getImageURL());
		image.setPreserveRatio(false);
		image.setFitHeight(90);
		image.setFitWidth(83);
    }

}
