package forest.rice.field.k.linebot.function01.reply;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.linecorp.bot.client.LineMessagingService;
import com.linecorp.bot.client.LineMessagingServiceBuilder;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.MessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.AudioMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;

import forest.rice.field.k.linebot.function01.EventUtil;
import forest.rice.field.k.linebot.function01.EventUtil.EVENT_TYPE;
import forest.rice.field.k.linebot.function01.LineBotDynamoTriggerFunctionHandler;
import forest.rice.field.k.linebot.function01.MessageContentUtil;
import forest.rice.field.k.linebot.function01.MessageContentUtil.MESSAGE_TYPE;
import forest.rice.field.k.linebot.function01.aws.AwsUtil;
import forest.rice.field.k.linebot.function01.docomo.dialogue.DialogueManager;
import forest.rice.field.k.linebot.function01.docomo.dialogue.DialogueResponse;
import retrofit2.Response;

public class ReplyEcho implements Reply {

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

			DialogueManager dialogueManager = new DialogueManager();
			DialogueResponse dialogueResponse = dialogueManager.getResponse(this.content.getText());

			String filename = "Polly-" + UUID.randomUUID();
			String audioUrl = AwsUtil.createAudioAndGetS3FilePath(dialogueResponse.utt, filename);

			List<Message> messages = new ArrayList<>();
			// messages.add(new TextMessage(this.content.getText()));
			messages.add(new AudioMessage(audioUrl, 10000));
			messages.add(new TextMessage(dialogueResponse.utt));

			Response<BotApiResponse> response = client.replyMessage(new ReplyMessage(replyToken, messages)).execute();

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

	@Override
	public String help() {
		return "その他のテキスト→オウム返しします。";
	}

}
