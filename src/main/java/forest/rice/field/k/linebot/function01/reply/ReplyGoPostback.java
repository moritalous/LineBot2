package forest.rice.field.k.linebot.function01.reply;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.linecorp.bot.client.LineMessagingService;
import com.linecorp.bot.client.LineMessagingServiceBuilder;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.event.postback.PostbackContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;

import forest.rice.field.k.linebot.function01.EventUtil;
import forest.rice.field.k.linebot.function01.EventUtil.EVENT_TYPE;
import forest.rice.field.k.linebot.function01.LineBotDynamoTriggerFunctionHandler;
import retrofit2.Response;

public class ReplyGoPostback implements Reply {

	private PostbackEvent event;
	private PostbackContent content;

	@Override
	public boolean match(Event event) {
		if (!EventUtil.getEventType(event).equals(EVENT_TYPE.POSTBACK)) {
			return false;
		}

		try {
			this.event = EventUtil.getPostbackEvent(event);

			content = this.event.getPostbackContent();
			System.out.println("data : " + content.getData());

			if (content.getData().contains("type=pokemon")) {
				return true;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public void execute() {
		try {
			String replyToken = this.event.getReplyToken();
			LineMessagingService client = LineMessagingServiceBuilder
					.create(LineBotDynamoTriggerFunctionHandler.CHANNEL_ACCESS_TOKEN).build();

			Map<String, String> map = convertData();

			int ramdom = (int) (Math.random() * 100) % 2;
			String text = (ramdom == 0) ? "ゲットだぜ" : "に逃げられた。。";

			Response<BotApiResponse> response = client.replyMessage(new ReplyMessage(replyToken,
					new TextMessage(String.format("%s%s", convertData().get("name"), text)))).execute();

			if (response.isSuccessful()) {
				System.out.println(response.body().getMessage());
			} else {
				System.out.println(response.errorBody().string());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private Map<String, String> convertData() {
		Map<String, String> map = new HashMap<>();

		String data = content.getData();

		String[] d = data.split("&");
		for (String d1 : d) {
			String[] d2 = d1.split("=");
			map.put(d2[0], d2[1]);
		}

		return map;
	}

	@Override
	public String help() {
		return null;
	}

}
