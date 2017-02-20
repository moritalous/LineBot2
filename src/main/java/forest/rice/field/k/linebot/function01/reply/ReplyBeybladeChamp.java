package forest.rice.field.k.linebot.function01.reply;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.linecorp.bot.client.LineMessagingService;
import com.linecorp.bot.client.LineMessagingServiceBuilder;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.action.Action;
import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.MessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.template.CarouselColumn;
import com.linecorp.bot.model.message.template.CarouselTemplate;
import com.linecorp.bot.model.response.BotApiResponse;

import forest.rice.field.k.linebot.function01.EventUtil;
import forest.rice.field.k.linebot.function01.EventUtil.EVENT_TYPE;
import forest.rice.field.k.linebot.function01.LineBotDynamoTriggerFunctionHandler;
import forest.rice.field.k.linebot.function01.MessageContentUtil;
import forest.rice.field.k.linebot.function01.MessageContentUtil.MESSAGE_TYPE;
import forest.rice.field.k.linebot.function01.beyblade.ChampManager;
import forest.rice.field.k.linebot.function01.beyblade.champ.Champ;
import retrofit2.Response;

public class ReplyBeybladeChamp implements Reply {

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
			if (content.getText().startsWith("#ベイブレード #大会")) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public void execute() {
		try {
			ChampManager manager = new ChampManager();
			Champ champ = manager.getChamp();

			String replyToken = this.event.getReplyToken();
			LineMessagingService client = LineMessagingServiceBuilder
					.create(LineBotDynamoTriggerFunctionHandler.CHANNEL_ACCESS_TOKEN).build();

			Response<BotApiResponse> response;

			{
				List<Message> messages = new ArrayList<>();
				List<List<String>> targetList = champ.getAaData().stream().filter(p -> p.get(1).matches("奈良県"))
						.collect(Collectors.toList());
				System.out.println("targetList Count: " + targetList.size());

				{
					List<CarouselColumn> columns = new ArrayList<>();

					for (List<String> aaData : targetList) {
						String title = escape(aaData.get(0));
						String text = String.format("%s\r\n%s\r\n%s", escape(aaData.get(6)), escape(aaData.get(5)),
								escape(aaData.get(4)));
						text = text.length() > 58 ? text.substring(0, 55) + "…" : text;

						System.out.println(String.format("%s %s", title, text));

						List<Action> actions = new ArrayList<>();
						actions.add(new URIAction("公式サイト", "https://beyblade.takaratomy.co.jp/champ_g4"));

						CarouselColumn column = new CarouselColumn(null, title, text, actions);
						columns.add(column);
						if (columns.size() == 5) {
							TemplateMessage message = new TemplateMessage("ベイブレード大会情報", new CarouselTemplate(columns));
							messages.add(message);
							columns = new ArrayList<>();
						}
					}

					TemplateMessage message = new TemplateMessage("ベイブレード大会情報", new CarouselTemplate(columns));
					messages.add(message);
				}

				response = client.replyMessage(new ReplyMessage(replyToken, messages)).execute();

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

	@Override
	public String help() {
		return null;
	}

	public String escape(String str) {
		return str.replaceAll("&#039;", "'").replaceAll("\r", "").replaceAll("\n", "");
	}

}
