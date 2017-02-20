package forest.rice.field.k.linebot.function01.reply;

import java.util.ArrayList;
import java.util.List;

import com.linecorp.bot.client.LineMessagingService;
import com.linecorp.bot.client.LineMessagingServiceBuilder;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.BeaconEvent;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;

import forest.rice.field.k.linebot.function01.EventUtil;
import forest.rice.field.k.linebot.function01.EventUtil.EVENT_TYPE;
import forest.rice.field.k.linebot.function01.LineBotDynamoTriggerFunctionHandler;
import retrofit2.Response;

public class ReplyBeacon implements Reply {

	private BeaconEvent event;

	@Override
	public boolean match(Event event) {
		if (!EventUtil.getEventType(event).equals(EVENT_TYPE.BEACON)) {
			return false;
		}

		try {
			this.event = EventUtil.getBeaconEvent(event);
			return true;
		} catch (Exception e) {
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

			List<Message> messages = new ArrayList<>();
			switch (event.getBeacon().getType()) {
			case "enter": {
				messages.add(new TextMessage("おかえりなさい"));
			}
				break;
			case "leave": {
				messages.add(new TextMessage("行ってらっしゃい"));
			}
				break;
			case "banner":
			default: {
				messages.add(new TextMessage("ビーコンイベント"));
			}
				break;

			}

			Response<BotApiResponse> response = client.replyMessage(new ReplyMessage(replyToken, messages)).execute();

			if (response.isSuccessful()) {
				System.out.println(response.body().getMessage());
			} else {
				System.out.println(response.errorBody().string());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public String help() {
		return "ビーコンにも反応します。";
	}

}
