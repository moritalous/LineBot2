package forest.rice.field.k.linebot.function01.docomo.dialogue;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DialogueManager {

	private final String DOCOMO_API_KEY = System.getenv("DOCOMO_API_KEY");

	private final String REQUEST_URL = "https://api.apigw.smt.docomo.ne.jp/dialogue/v1/dialogue";

	public DialogueResponse getResponse(String message) throws IOException {
		System.out.print("getResponse:" + message);

		DialogueRequest dialogueRequest = new DialogueRequest();
		dialogueRequest.utt = message;
		System.out.print("utt:" + dialogueRequest.utt);

		Request httpRequest = createRequest(createUrl(), createPostBody(dialogueRequest));
		OkHttpClient client = new OkHttpClient();
		Response httpResponse = client.newCall(httpRequest).execute();

		return DialogueResponse.fromJson(httpResponse.body().string());

	}

	private String createUrl() {
		return String.join("?", REQUEST_URL, String.join("=", "APIKEY", DOCOMO_API_KEY));
	}

	private RequestBody createPostBody(DialogueRequest request) {
		return RequestBody.create(MediaType.parse("application/json"), DialogueRequest.toJson(request));
	}

	private Request createRequest(String url, RequestBody body) {
		return new Request.Builder().url(url).post(body).build();
	}

}
