package isd.aims.main.subsystem;

import isd.aims.main.common.exception.PaymentException;
import isd.aims.main.common.exception.UnrecognizedException;
import isd.aims.main.entity.payment.PaymentTransaction;

import java.text.ParseException;
import java.util.Map;

/**
 * The {@code InterbankInterface} class is used to communicate with the
 * {@link VnPaySubsystem InterbankSubsystem} to make transaction.
 *
 * @author hieud
 */
public interface VnPayInterface {

    /**
     * Pay order, and then return the payment transaction.
     */
    public abstract String generatePayUrl(int amount, String contents)
            throws PaymentException, UnrecognizedException;


    public PaymentTransaction
    makePaymentTransaction(Map<String, String> response) throws ParseException;
}
