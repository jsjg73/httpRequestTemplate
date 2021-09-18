package http_request_template;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import http.request.template.HttpRequest;
import http.request.template.HttpResponse;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HttpRequestTest {
	private HttpRequest httpRequest;
	@Before
	public void setUp() {
		httpRequest = new HttpRequest(/*BASE_URL*/);
	}
	@Test
	public void startSuccess() throws IOException {

		String postBody = /*JSON 형식의 문자열*/;
		String mediaType="application/json";
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("X-Auth-Token", /*토큰*/);
		
		HttpResponse httpResponse = httpRequest.post("/start", headers, mediaType, postBody );
		
		assertThat(httpResponse.getCode(), is(200));
		assertThat(httpResponse.getResult(), notNullValue());
	 
	}
	
	@Test
	public void	locations()throws IOException {
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", /*토큰*/);
		headers.put("Content-Type", "application/json");

		Map<String, String> params = new HashMap<String, String>();
		
		HttpResponse httpResponse = httpRequest.get("/locations", headers, params );
		
		assertThat(httpResponse.getCode(), is(200));
		assertThat(httpResponse.getResult(), is(not("응답 결과가 없음")));
		
	}
	@Test
	public void startFail() throws IOException {
        String postBody = /*JSON 형식의 문자열*/;
        String mediaType="application/json";
        
        Map<String, String> headers = new HashMap<String, String>();
		headers.put("X-Auth-Token", "wrong token");

		HttpResponse httpResponse = httpRequest.post("/start", headers, mediaType, postBody );
		
		assertThat(httpResponse.getCode(), is(401));
		assertThat(httpResponse.getResult(), nullValue());
	}
}
