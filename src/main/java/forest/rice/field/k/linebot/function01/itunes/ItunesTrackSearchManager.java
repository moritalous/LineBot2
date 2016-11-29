package forest.rice.field.k.linebot.function01.itunes;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import forest.rice.field.k.linebot.function01.itunes.tracksearch.SearchResult;
import okhttp3.OkHttpClient;

public class ItunesTrackSearchManager {

	public SearchResult getSearchResult(String keyword) {
		SearchResult result = null;

		try {
			result = createResult(getHttpResponseBody(getRequestUrl(keyword)));
		} catch (Exception e) {
			e.printStackTrace();
			result = new SearchResult();
		}

		return result;
	}

	protected String getRequestUrl(String keyword) {
		String format = "https://itunes.apple.com/search?term=%s&country=JP&media=music&entity=musicTrack&limit=5&language= ja_jp";
		String urlKeyword = null;
		try {
			urlKeyword = URLEncoder.encode(keyword, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			urlKeyword = keyword;
		}
		return String.format(format, urlKeyword);
	}

	protected SearchResult createResult(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.readValue(json, SearchResult.class);
	}

	protected String getHttpResponseBody(String url) throws IOException {
		OkHttpClient client = new OkHttpClient();

		okhttp3.Request request = new okhttp3.Request.Builder().url(url).build();

		okhttp3.Response response = client.newCall(request).execute();
		return response.body().string();
	}

}
