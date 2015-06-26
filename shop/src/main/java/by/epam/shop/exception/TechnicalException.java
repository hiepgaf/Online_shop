package by.epam.shop.exception;

/**
 * The Class TechnicalException.
 */
public class TechnicalException extends Exception {

	private static final long serialVersionUID = 1L;

	public TechnicalException() {
	}

	/**
	 * Instantiates a new technical exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public TechnicalException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new technical exception.
	 *
	 * @param message
	 *            the message
	 */
	public TechnicalException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new technical exception.
	 *
	 * @param cause
	 *            the cause
	 */
	public TechnicalException(Throwable cause) {
		super(cause);
	}
}
