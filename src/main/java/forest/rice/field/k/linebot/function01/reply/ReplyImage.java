package forest.rice.field.k.linebot.function01.reply;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.linecorp.bot.client.LineMessagingService;
import com.linecorp.bot.client.LineMessagingServiceBuilder;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.action.Action;
import com.linecorp.bot.model.action.MessageAction;
import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.event.message.MessageContent;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.template.CarouselColumn;
import com.linecorp.bot.model.message.template.CarouselTemplate;
import com.linecorp.bot.model.response.BotApiResponse;

import forest.rice.field.k.linebot.function01.EventUtil;
import forest.rice.field.k.linebot.function01.EventUtil.EVENT_TYPE;
import forest.rice.field.k.linebot.function01.LineBotDynamoTriggerFunctionHandler;
import forest.rice.field.k.linebot.function01.MessageContentUtil;
import forest.rice.field.k.linebot.function01.MessageContentUtil.MESSAGE_TYPE;
import forest.rice.field.k.linebot.function01.urlshorter.UrlShorter;
import forest.rice.field.k.linebot.function01.urlshorter.UrlShorterManager;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class ReplyImage implements Reply {

	private static final String S3_ACCESS_KEY = System.getenv("S3_ACCESS_KEY");
	private static final String S3_SECRET_KEY = System.getenv("S3_SECRET_KEY");

	private static final String S3_BUCKET_NAME = System.getenv("S3_BUCKET_NAME");

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
			putS3(body.byteStream(), content.getId(), contentType);

			String objectPath = String.format("https://s3-ap-northeast-1.amazonaws.com/moritalous-001/%s",
					content.getId());

			// QRコードを返す
			UrlShorterManager urlShorterManager = new UrlShorterManager();
			UrlShorter urlShorter = urlShorterManager.getUrlShorter(objectPath);

			{

				List<CarouselColumn> columns = new ArrayList<>();

				String image = String.format("%s%s", urlShorter.getId(), ".qr");

				List<Action> actions = new ArrayList<>();
				actions.add(new URIAction("画像を表示", objectPath));
				actions.add(new URIAction("QRコードを表示", image));
				actions.add(new MessageAction("短縮URLを表示", urlShorter.getId()));

				CarouselColumn column = new CarouselColumn(null, "画像を保管しました。", "ファイルは約一日後に削除されます。", actions);
				columns.add(column);

				TemplateMessage messages = new TemplateMessage("画像を保管しました。", new CarouselTemplate(columns));

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

	private void putS3(InputStream inputStream, String keyName, MediaType contentType) {
		String bucketName = S3_BUCKET_NAME;
		AmazonS3 s3client = new AmazonS3Client(new BasicAWSCredentials(S3_ACCESS_KEY, S3_SECRET_KEY));

		AccessControlList acl = new AccessControlList();
		acl.grantPermission(GroupGrantee.AllUsers, Permission.Read);

		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(contentType.toString());

		PutObjectResult result = s3client
				.putObject(new PutObjectRequest(bucketName, keyName, inputStream, metadata).withAccessControlList(acl));
		System.out.println(result.toString());
	}

}
