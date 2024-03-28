package isd.aims.main.common.exception;

public class InvalidVersionException extends PaymentException{
	public InvalidVersionException() {
		super("ERROR: Invalid Version Information!");
	}
}
