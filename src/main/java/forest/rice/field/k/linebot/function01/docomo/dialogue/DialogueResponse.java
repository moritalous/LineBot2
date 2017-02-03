package forest.rice.field.k.linebot.function01.docomo.dialogue;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DialogueResponse {

	public String utt = "";
	public String yomi = "";
	public String mode = "";
	public String da = "";
	public String context = "";

	public static DialogueResponse fromJson(String str) {
		System.out.println("fromJson:" + str);
		DialogueResponse response;
		ObjectMapper mapper = new ObjectMapper();
		try {
			response = mapper.readValue(str, DialogueResponse.class);
		} catch (IOException e) {
			e.printStackTrace();
			response = new DialogueResponse();
		}
		return response;
	}
}
