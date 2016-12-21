package forest.rice.field.k.linebot.function01.reply;

import java.io.IOException;

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

			Response<BotApiResponse> response = client
					.replyMessage(new ReplyMessage(replyToken, new TextMessage(this.content.getText()))).execute();

			if (response.isSuccessful()) {
				System.out.println(response.body().getMessage());
			} else {
				System.out.println(response.errorBody().string());
			}

			// try {
			// AmazonPolly pollyClient =
			// AmazonPollyClientBuilder.defaultClient();
			//
			// SynthesizeSpeechRequest request = new SynthesizeSpeechRequest();
			// request.setOutputFormat(OutputFormat.Mp3);
			// request.setSampleRate("22050");
			// request.setText(this.content.getText());
			// request.setVoiceId(VoiceId.Mizuki);
			//
			// SynthesizeSpeechResult result =
			// pollyClient.synthesizeSpeech(request);
			// InputStream stream = result.getAudioStream();
			//
			// System.out.println(result.toString());
			//
			// AmazonS3 s3client = new AmazonS3Client(new
			// ProfileCredentialsProvider());
			// PutObjectResult putResult = s3client.putObject("moritalous-001",
			// "audio.mp3", stream,
			// new ObjectMetadata());
			//
			// System.out.println(putResult.toString());
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
