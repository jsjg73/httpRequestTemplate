package http.request.exception;

public class RequestFailException extends RuntimeException {
	public RequestFailException(Throwable cause) {
		super(cause);
	}
	public RequestFailException() {
		super();
	}
}
