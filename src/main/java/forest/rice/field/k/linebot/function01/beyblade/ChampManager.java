package forest.rice.field.k.linebot.function01.beyblade;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import forest.rice.field.k.linebot.function01.beyblade.champ.Champ;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class ChampManager {

	private static String URL = "https://beyblade.takaratomy.co.jp/_champ_g4";

	public Champ getChamp() {
		try {

			OkHttpClient client = new OkHttpClient();
			RequestBody body = new FormBody.Builder().add("sEcho", "1").add("iColumns", "3").add("sColumns", "")
					.add("iDisplayStart", "0").add("iDisplayLength", "-1").add("mDataProp_0", "0")
					.add("mDataProp_1", "1").add("mDataProp_2", "2").add("iSortCol_0", "1").add("sSortDir_0", "asc")
					.add("iSortingCols", "1").add("bSortable_0", "true").add("bSortable_1", "true")
					.add("bSortable_2", "false").add("prefecture", "all").build();

			okhttp3.Request request = new okhttp3.Request.Builder().url(URL).post(body).build();
			okhttp3.Response response = client.newCall(request).execute();

			String bodyString = response.body().string();

			return createResult(bodyString);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	protected static Champ createResult(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.readValue(json, Champ.class);
	}

}
