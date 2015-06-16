package by.epam.shop.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class TechnicalException.
 */
public class TechnicalException extends Exception {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new technical exception.
	 */
	public TechnicalException() {
	}

	/**
	 * Instantiates a new technical exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public TechnicalException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new technical exception.
	 *
	 * @param message the message
	 */
	public TechnicalException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new technical exception.
	 *
	 * @param cause the cause
	 */
	public TechnicalException(Throwable cause) {
		super(cause);
	}
}
