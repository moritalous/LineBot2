package forest.rice.field.k.linebot.function01.itunes;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import forest.rice.field.k.linebot.function01.itunes.topchart.Music;
import okhttp3.OkHttpClient;

public class ItunesMusicManager {

	public Music getMusic() {
		Music music = null;

		try {
			music = createMusic(getHttpResponseBody());
		} catch (Exception e) {
			e.printStackTrace();
			music = new Music();
		}

		return music;
	}

	protected String getRequestUrl() {
		return "https://itunes.apple.com/jp/rss/topsongs/limit=5/genre=27/json";
	}

	protected Music createMusic(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.readValue(json, Music.class);
	}

	protected String getHttpResponseBody() throws IOException {
		OkHttpClient client = new OkHttpClient();

		okhttp3.Request request = new okhttp3.Request.Builder().url(getRequestUrl()).build();

		okhttp3.Response response = client.newCall(request).execute();
		return response.body().string();
	}

}
