package isd.aims.main.views.payment;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import isd.aims.main.controller.PaymentController;
import isd.aims.main.entity.invoice.Invoice;
import isd.aims.main.entity.response.Response;
import isd.aims.main.subsystem.vnPay.VnPayConfig;
import isd.aims.main.utils.Configs;
import isd.aims.main.views.BaseScreenHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class PaymentScreenHandler extends BaseScreenHandler {

	// @FXML
	// private Button btnConfirmPayment;

	// @FXML
	// private ImageView loadingImage;

	private Invoice invoice;

	// public PaymentScreenHandler(Stage stage, String screenPath, int amount, String contents) throws IOException {
	// 	super(stage, screenPath);
	// }

	@FXML
	private VBox vBox;

	public PaymentScreenHandler(Stage stage, String screenPath, Invoice invoice) throws IOException {
		super(stage, screenPath);
		this.invoice = invoice;

		displayWebView();
	}

	private void displayWebView() {
		var paymentController = new PaymentController();
		var paymentUrl = paymentController.getUrlPay(invoice.getAmount(), "Thanh toan hoa don AIMS");
		WebView paymentView = new WebView();
		WebEngine webEngine = paymentView.getEngine();
		webEngine.load(paymentUrl);
		webEngine.locationProperty().addListener((observable, oldValue, newValue) -> {
			// Xử lý khi URL thay đổi
			handleUrlChanged(newValue);
		});
		vBox.getChildren().clear();
		vBox.getChildren().add(paymentView);
	}


    private void handleUrlChanged(String newValue) {
        if (newValue.contains(VnPayConfig.vnp_ReturnUrl)) {
            try {
                URI uri = new URI(newValue);
                String query = uri.getQuery();
                Response response = new Response(query);

                payOrder(response);

            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void payOrder(Response response) throws IOException {

        var ctrl = (PaymentController) super.getBController();
        Map<String, String> res = ctrl.makePayment(response, 1);

        BaseScreenHandler resultScreen = new ResultScreenHandler(this.stage, Configs.RESULT_SCREEN_PATH,
                res.get("RESULT"), res.get("MESSAGE"));
        ctrl.emptyCart();
        resultScreen.setPreviousScreen(this);
        resultScreen.setHomeScreenHandler(homeScreenHandler);
        resultScreen.setScreenTitle("Result Screen");
        resultScreen.show();
    }
}
