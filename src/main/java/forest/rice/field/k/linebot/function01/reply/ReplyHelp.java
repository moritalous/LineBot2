package forest.rice.field.k.linebot.function01.reply;

import java.util.List;

import com.linecorp.bot.client.LineMessagingService;
import com.linecorp.bot.client.LineMessagingServiceBuilder;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.MessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;

import forest.rice.field.k.linebot.function01.EventUtil;
import forest.rice.field.k.linebot.function01.EventUtil.EVENT_TYPE;
import forest.rice.field.k.linebot.function01.LineBotDynamoTriggerFunctionHandler;
import forest.rice.field.k.linebot.function01.MessageContentUtil;
import forest.rice.field.k.linebot.function01.MessageContentUtil.MESSAGE_TYPE;
import retrofit2.Response;

public class ReplyHelp implements Reply {

	private MessageEvent<MessageContent> event;
	private TextMessageContent content;

	@Override
	public boolean match(Event event) {
		if (!EventUtil.getEventType(event).equals(EVENT_TYPE.MESSAGE)) {
			return false;
		}

		try {
			this.event = EventUtil.getMessageEvent(event);

			if (!MessageContentUtil.getMessageContentType(this.event.getMessage()).equals(MESSAGE_TYPE.TEXT)) {
				return false;
			}
			content = MessageContentUtil.getTextMessageContent(this.event.getMessage());
			if (content.getText().contains("#ヘルプ")) {
				return true;
			}

			return false;

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

			List<String> help = ReplyManager.help();
			StringBuilder sb = new StringBuilder();
			help.stream().forEach(s -> sb.append(s).append("\r\n"));

			Response<BotApiResponse> response = client
					.replyMessage(new ReplyMessage(replyToken, new TextMessage(sb.toString()))).execute();

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
		return null;
	}

}
