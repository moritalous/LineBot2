package forest.rice.field.k.linebot.function01.urlshorter;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class UrlShorterManager {

	private static final String baseUrl = "https://www.googleapis.com/urlshortener/v1/url?key=%s";

	private static final String api_key = System.getenv("GOOGLE_API_SHORTER_KEY");

	private static final String request_body = "{\"longUrl\": \"%s/\"}";

	//
	public UrlShorter getUrlShorter(String longUrl) {

		try {
			String url = String.format(baseUrl, api_key);
			String requestBody = String.format(request_body, longUrl);

			String responsebody = getHttpResponseBody(url, requestBody);
			return createResult(responsebody);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}

	protected String getHttpResponseBody(String url, String postBody) throws IOException {
		OkHttpClient client = new OkHttpClient();
		RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), postBody);
		okhttp3.Request request = new okhttp3.Request.Builder().url(url).post(requestBody).build();
		okhttp3.Response response = client.newCall(request).execute();
		return response.body().string();
	}

	protected UrlShorter createResult(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.readValue(json, UrlShorter.class);
	}

}
