package http.request.template;

import java.io.IOException;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Request.Builder;
import okhttp3.Response;

public class HttpRequest {
	static final OkHttpClient client = new OkHttpClient();
	
	private final String basicUrl;
	
	public HttpRequest(String url) {
		this.basicUrl = url;
	}
	
	private HttpResponse template(HttpMethodCallback callback) throws IOException {
		Request request = callback.buildAndRequest();
		
		Response response;
		try {
			response = client.newCall(request).execute();
			
			HttpResponse httpResponse = new HttpResponse();
			httpResponse.setCode(response.code());
			if(response.isSuccessful()) {
				if(response.body()!=null)
					httpResponse.setResult(response.body().string());
				else
					httpResponse.setResult("응답 결과가 없음");
			}

			return httpResponse;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public HttpResponse get(final String path, final Map<String, String> headers, final Map<String, String> params) throws IOException {
		try {
			return template(new HttpMethodCallback() {
				
				public Request buildAndRequest() {
					String extendUrl = appendParams(basicUrl+path, params);
					Request.Builder builder = new Request.Builder().url(extendUrl).get();
					addHeaders(headers, builder);
					return builder.build();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	public HttpResponse post(final String path, final Map<String, String> headers, final String mediaType, final String body) throws IOException{
		
		try {
			return template(new HttpMethodCallback() {
				
				public Request buildAndRequest() {
				  RequestBody requestBody = RequestBody.create(MediaType.parse(mediaType), body);
					Request.Builder builder = new Request.Builder()
							.url(basicUrl+path)
							.post(requestBody);
					addHeaders(headers, builder);
					return builder.build();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	protected String appendParams(String url, Map<String, String> params) {
		url+="?";
		for(Map.Entry<String, String> entry : params.entrySet()) {
			url+=entry.getKey()+"=" +entry.getValue()+"/";
		}
		return url;
	}

	protected void addHeaders(Map<String, String> headers, Builder builder) {
		for(Map.Entry<String, String> entry : headers.entrySet()) {
			builder.addHeader(entry.getKey(), entry.getValue());
		}
	}
}
