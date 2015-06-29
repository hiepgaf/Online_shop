package by.epam.shop.exception;

/**
 * The Class TechnicalException.
 */
public class DAOException extends Exception {

	private static final long serialVersionUID = 1L;

	public DAOException() {
	}

	/**
	 * Instantiates a new technical exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new technical exception.
	 *
	 * @param message
	 *            the message
	 */
	public DAOException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new technical exception.
	 *
	 * @param cause
	 *            the cause
	 */
	public DAOException(Throwable cause) {
		super(cause);
	}
}
