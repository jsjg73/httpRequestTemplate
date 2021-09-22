package http.request.exception;

public class EmptyResponseBodyException extends RuntimeException {
	public EmptyResponseBodyException(Throwable cause) {
		super(cause);
	}
	public EmptyResponseBodyException() {
		super();
	}
}
