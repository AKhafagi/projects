package exceptions;

public class NodeAddingException extends Exception {

	public NodeAddingException() {
		super();
	}

	public NodeAddingException(String message) {
		super(message);
	}

	public NodeAddingException(Throwable cause) {
		super(cause);
	}

	public NodeAddingException(String message, Throwable cause) {
		super(message, cause);
	}

	public NodeAddingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
