package http.request.template;

import okhttp3.Request;

public interface HttpMethodCallback {
	Request buildAndRequest() ;
}
