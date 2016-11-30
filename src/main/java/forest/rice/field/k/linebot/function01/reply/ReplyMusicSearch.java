package forest.rice.field.k.linebot.function01.reply;

import java.util.ArrayList;
import java.util.List;

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
import forest.rice.field.k.linebot.function01.itunes.ItunesTrackSearchManager;
import forest.rice.field.k.linebot.function01.itunes.tracksearch.SearchResult;
import retrofit2.Response;

public class ReplyMusicSearch implements Reply {

	private MessageEvent<MessageContent> event;
	private TextMessageContent content;

	@Override
	public boolean match(Event event) {
		System.out.println("start match : ReplyRanking " + getClass().getName());

		if (!EventUtil.getEventType(event).equals(EVENT_TYPE.MESSAGE)) {
			System.out.println("start match : ReplyRanking 1" + getClass().getName());
			return false;
		}

		try {
			this.event = EventUtil.getMessageEvent(event);

			if (!MessageContentUtil.getMessageContentType(this.event.getMessage()).equals(MESSAGE_TYPE.TEXT)) {
				System.out.println("start match : ReplyRanking 1" + getClass().getName());
				return false;
			}
			content = MessageContentUtil.getTextMessageContent(this.event.getMessage());
			System.out.println("start match : ReplyRanking 2" + content.getText());
			if (content.getText().contains("が聴きたい")) {
				System.out.println("start match : ReplyRanking 5" + getClass().getName());
				return true;
			} else {
				System.out.println("start match : ReplyRanking 6" + getClass().getName());
				return false;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("start match : ReplyRanking 7" + getClass().getName());
		}

		System.out.println("start match : ReplyRanking 8" + getClass().getName());
		return false;
	}

	@Override
	public void execute() {
		try {
			ItunesTrackSearchManager manager = new ItunesTrackSearchManager();
			String keyword = content.getText().replaceAll("が聴きたい", "");
			SearchResult result = manager.getSearchResult(keyword);

			String replyToken = this.event.getReplyToken();
			LineMessagingService client = LineMessagingServiceBuilder
					.create(LineBotDynamoTriggerFunctionHandler.CHANNEL_ACCESS_TOKEN).build();

			Response<BotApiResponse> response;

			switch (result.getResultCount()) {
			case 0: {
				TextMessage text = new TextMessage("見つかりませんでした。。");
				response = client.replyMessage(new ReplyMessage(replyToken, text)).execute();
			}
				break;
			// case 1: {
			default: {
				List<Message> messages = new ArrayList<>();

				String previewUrl = result.getResults().get(0).getPreviewUrl();
				previewUrl = previewUrl.replaceAll("http://audio.itunes.apple.com/",
						"https://714oxhgy3c.execute-api.ap-northeast-1.amazonaws.com/prod/audio/");

				TextMessage text = new TextMessage(result.getResults().get(0).getTrackName());
				AudioMessage audio = new AudioMessage(previewUrl, result.getResults().get(0).getTrackTimeMillis() / 10);

				messages.add(text);
				messages.add(audio);

				response = client.replyMessage(new ReplyMessage(replyToken, messages)).execute();
			}
				break;
			// default: {
			// List<CarouselColumn> columns = new ArrayList<>();
			//
			// int loop = result.getResultCount() > 5 ? 5 :
			// result.getResultCount();
			// for (int i = 0; i < loop; i++) {
			// Result target = result.getResults().get(i);
			//
			// List<Action> actions = new ArrayList<>();
			// actions.add(new MessageAction("視聴する",
			// target.getTrackName() + " - " + target.getArtistName() +
			// "が聴きたい"));
			// actions.add(new URIAction("iTunesで開く",
			// target.getTrackViewUrl()));
			// //
			//
			// String image = target.getArtworkUrl100();
			// image = image.replace("http://is1.mzstatic.com/",
			// "https://714oxhgy3c.execute-api.ap-northeast-1.amazonaws.com/prod/is1/");
			// image = image.replace("http://is2.mzstatic.com/",
			// "https://714oxhgy3c.execute-api.ap-northeast-1.amazonaws.com/prod/is2/");
			// image = image.replace("http://is3.mzstatic.com/",
			// "https://714oxhgy3c.execute-api.ap-northeast-1.amazonaws.com/prod/is3/");
			// image = image.replace("http://is4.mzstatic.com/",
			// "https://714oxhgy3c.execute-api.ap-northeast-1.amazonaws.com/prod/is4/");
			// image = image.replace("http://is5.mzstatic.com/",
			// "https://714oxhgy3c.execute-api.ap-northeast-1.amazonaws.com/prod/is5/");
			//
			// CarouselColumn column = new CarouselColumn(image,
			// target.getTrackName() + " - " + target.getArtistName(),
			// target.getArtistName(), actions);
			// System.out.println(column.toString());
			// columns.add(column);
			// }
			// TemplateMessage messages = new TemplateMessage("altText", new
			// CarouselTemplate(columns));
			// response = client.replyMessage(new ReplyMessage(replyToken,
			// messages)).execute();
			// }
			// break;
			}

			if (response.isSuccessful()) {
				System.out.println(response.body().getMessage());
			} else {
				System.out.println(response.errorBody().string());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
