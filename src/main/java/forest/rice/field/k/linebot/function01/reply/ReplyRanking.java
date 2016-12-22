package forest.rice.field.k.linebot.function01.reply;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.linecorp.bot.client.LineMessagingService;
import com.linecorp.bot.client.LineMessagingServiceBuilder;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.action.Action;
import com.linecorp.bot.model.action.MessageAction;
import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.MessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.template.CarouselColumn;
import com.linecorp.bot.model.message.template.CarouselTemplate;
import com.linecorp.bot.model.response.BotApiResponse;

import forest.rice.field.k.linebot.function01.EventUtil;
import forest.rice.field.k.linebot.function01.EventUtil.EVENT_TYPE;
import forest.rice.field.k.linebot.function01.LineBotDynamoTriggerFunctionHandler;
import forest.rice.field.k.linebot.function01.MessageContentUtil;
import forest.rice.field.k.linebot.function01.MessageContentUtil.MESSAGE_TYPE;
import forest.rice.field.k.linebot.function01.itunes.ItunesMusicManager;
import forest.rice.field.k.linebot.function01.itunes.topchart.Entry;
import forest.rice.field.k.linebot.function01.itunes.topchart.Music;
import forest.rice.field.k.linebot.function01.urlshorter.UrlShorter;
import forest.rice.field.k.linebot.function01.urlshorter.UrlShorterManager;
import retrofit2.Response;

public class ReplyRanking implements Reply {

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
			if (content.getText().contains("ランキング")) {
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
			ItunesMusicManager manager = new ItunesMusicManager();
			Music music = manager.getMusic();

			String replyToken = this.event.getReplyToken();
			LineMessagingService client = LineMessagingServiceBuilder
					.create(LineBotDynamoTriggerFunctionHandler.CHANNEL_ACCESS_TOKEN).build();

			List<CarouselColumn> columns = new ArrayList<>();

			for (Entry entry : music.getFeed().getEntry()) {

				List<Action> actions = new ArrayList<>();
				actions.add(new MessageAction("視聴する", entry.getTitle().getLabel() + "が聴きたい"));
				actions.add(new URIAction("iTunesで開く", entry.getLink().get(0).getAttributes().getHref()));

				String image = entry.getImImage().get(2).getLabel();
				UrlShorterManager shorterManager = new UrlShorterManager();
				UrlShorter imageUrlShorter = shorterManager.getUrlShorter(image);

				CarouselColumn column = new CarouselColumn(imageUrlShorter.getId(), entry.getTitle().getLabel(),
						entry.getImArtist().getLabel(), actions);
				System.out.println(column.toString());
				columns.add(column);
			}

			TemplateMessage messages = new TemplateMessage("ランキング", new CarouselTemplate(columns));

			Response<BotApiResponse> response = client.replyMessage(new ReplyMessage(replyToken, messages)).execute();

			if (response.isSuccessful()) {
				System.out.println(response.body().getMessage());
			} else {
				System.out.println(response.errorBody().string());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String help() {
		return "ランキング→ランキングを表示";
	}

}
