package isd.aims.main.controller;

import isd.aims.main.common.exception.InvalidCardException;
import isd.aims.main.common.exception.PaymentException;
import isd.aims.main.common.exception.UnrecognizedException;
import isd.aims.main.entity.cart.Cart;
import isd.aims.main.entity.payment.CreditCard;
import isd.aims.main.entity.payment.PaymentTransaction;
import isd.aims.main.subsystem.InterbankInterface;
import isd.aims.main.subsystem.InterbankSubsystem;
import isd.aims.main.subsystem.VnPayInterface;
import isd.aims.main.subsystem.VnPaySubsystem;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;



/**
 * This {@code PaymentController} class control the flow of the payment process
 * in our AIMS Software.
 *
 */
public class PaymentController extends BaseController {

	private VnPayInterface vnPayService;

	public PaymentController() {
		vnPayService = new VnPaySubsystem();
	}

	public Map<String, String> makePayment(Map<String, String> response, int orderId) {
		Map<String, String> result = new Hashtable<String, String>();

		try {
			// this.vnPayService = new VnPaySubsystem();
			var trans = vnPayService.makePaymentTransaction(response);
			trans.save(orderId);
			result.put("RESULT", "PAYMENT SUCCESSFUL!");
			result.put("MESSAGE", "You have succesffully paid the order!");
		} catch (PaymentException | UnrecognizedException | SQLException ex) {
			result.put("MESSAGE", ex.getMessage());
			result.put("RESULT", "PAYMENT FAILED!");

		} catch (ParseException ex) {
			result.put("MESSAGE", ex.getMessage());
			result.put("RESULT", "PAYMENT FAILED!");
		}

		return result;
	}

	/**
	 * Generate VNPay payment URL
	 */
	public String getUrlPay(int amount, String content) {
		var url = vnPayService.generatePayUrl(amount, content);
		return url;
	}

	public void emptyCart(){
        Cart.getCart().emptyCart();
    }
}
