package isd.aims.main.views.payment;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import isd.aims.main.controller.PaymentController;
import isd.aims.main.entity.invoice.Invoice;
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

	// Hàm chuyển đổi query string thành Map
    private static Map<String, String> parseQueryString(String query) {
        Map<String, String> params = new HashMap<>();
        if (query != null && !query.isEmpty()) {
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                if (keyValue.length == 2) {
                    params.put(keyValue[0], keyValue[1]);
                }
            }
        }
        return params;
    }

    private void handleUrlChanged(String newValue) {
        if (newValue.contains(VnPayConfig.vnp_ReturnUrl)) {
            try {
                URI uri = new URI(newValue);
                String query = uri.getQuery();

                // Chuyển đổi query thành Map
                Map<String, String> params = parseQueryString(query);

                payOrder(params);

            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void payOrder(Map<String, String> res) throws IOException {

        var ctrl = (PaymentController) super.getBController();
        Map<String, String> response = ctrl.makePayment(res, 1);

        BaseScreenHandler resultScreen = new ResultScreenHandler(this.stage, Configs.RESULT_SCREEN_PATH,
                response.get("RESULT"), response.get("MESSAGE"));
        ctrl.emptyCart();
        resultScreen.setPreviousScreen(this);
        resultScreen.setHomeScreenHandler(homeScreenHandler);
        resultScreen.setScreenTitle("Result Screen");
        resultScreen.show();
    }

	// @FXML
	// private Label pageTitle;

	// @FXML
	// private TextField cardNumber;

	// @FXML
	// private TextField holderName;

	// @FXML
	// private TextField expirationDate;

	// @FXML
	// private TextField securityCode;

	// void confirmToPayOrder() throws IOException{
	// 	String contents = "pay order";
	// 	PaymentController ctrl = (PaymentController) getBController();
	// 	Map<String, String> response = ctrl.payOrder(invoice.getAmount(), contents, cardNumber.getText(), holderName.getText(),
	// 			expirationDate.getText(), securityCode.getText());

	// 	BaseScreenHandler resultScreen = new ResultScreenHandler(this.stage, Configs.RESULT_SCREEN_PATH, response.get("RESULT"), response.get("MESSAGE") );
	// 	resultScreen.setPreviousScreen(this);
	// 	resultScreen.setHomeScreenHandler(homeScreenHandler);
	// 	resultScreen.setScreenTitle("Result Screen");
	// 	resultScreen.show();
	// }

}
