package forest.rice.field.k.linebot.function01.docomo.dialogue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DialogueRequest {

	public String utt = "";
	public String context = "";
	public String nickname = "星野源";
	public String nickname_y = "ホシノゲン";
	public String sex = "男";
	public String bloodtype = "AB";
	public String birthdateY = "1981";
	public String birthdateM = "1";
	public String birthdateD = "28";
	public String age = "36";
	public String constellations = "水瓶座";
	public String place = "奈良";
	public String mode = "dialog";
	public String t = "20";

	public static String toJson(DialogueRequest request) {
		ObjectMapper mapper = new ObjectMapper();
		String str = "{}";
		try {
			str = mapper.writeValueAsString(request);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println("toJson:" + str);
		return str;
	}
}
