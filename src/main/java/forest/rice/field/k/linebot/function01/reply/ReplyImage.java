package forest.rice.field.k.linebot.function01.reply;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.linecorp.bot.client.LineMessagingService;
import com.linecorp.bot.client.LineMessagingServiceBuilder;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.event.message.MessageContent;
import com.linecorp.bot.model.message.ImageMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;

import forest.rice.field.k.linebot.function01.EventUtil;
import forest.rice.field.k.linebot.function01.EventUtil.EVENT_TYPE;
import forest.rice.field.k.linebot.function01.LineBotDynamoTriggerFunctionHandler;
import forest.rice.field.k.linebot.function01.MessageContentUtil;
import forest.rice.field.k.linebot.function01.MessageContentUtil.MESSAGE_TYPE;
import forest.rice.field.k.linebot.function01.aws.AwsUtil;
import forest.rice.field.k.linebot.function01.urlshorter.UrlShorter;
import forest.rice.field.k.linebot.function01.urlshorter.UrlShorterManager;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class ReplyImage implements Reply {

	private MessageEvent<MessageContent> event;
	private ImageMessageContent content;

	@Override
	public boolean match(Event event) {
		if (!EventUtil.getEventType(event).equals(EVENT_TYPE.MESSAGE)) {
			return false;
		}

		try {
			this.event = EventUtil.getMessageEvent(event);

			if (!MessageContentUtil.getMessageContentType(this.event.getMessage()).equals(MESSAGE_TYPE.IMAGE)) {
				return false;
			}
			content = MessageContentUtil.getImageMessageContent(this.event.getMessage());

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

			Call<ResponseBody> messageContent = client.getMessageContent(content.getId());
			Response<ResponseBody> responseBody = messageContent.execute();
			ResponseBody body = responseBody.body();
			MediaType contentType = responseBody.body().contentType();

			// S3に上げる
			AwsUtil.putS3(body.byteStream(), content.getId(), contentType);

			String objectPath = AwsUtil.s3Filepath(content.getId());

			// QRコードを返す
			UrlShorterManager urlShorterManager = new UrlShorterManager();
			UrlShorter urlShorter = urlShorterManager.getUrlShorter(objectPath);

			{
				String image = String.format("%s%s", urlShorter.getId(), ".qr");

				List<Message> messages = new ArrayList<>();
				messages.add(new TextMessage("画像を保管しました。\r\nファイルは約一日後に削除されます。"));
				messages.add(new ImageMessage(image, image));
				messages.add(new TextMessage(urlShorter.getId()));

				Response<BotApiResponse> response = client.replyMessage(new ReplyMessage(replyToken, messages))
						.execute();

				if (response.isSuccessful()) {
					System.out.println(response.body().getMessage());
				} else {
					System.out.println(response.errorBody().string());
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String help() {
		return "画像を送信→QRコードで共有";
	}

}
