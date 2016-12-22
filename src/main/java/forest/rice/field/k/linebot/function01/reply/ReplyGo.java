package forest.rice.field.k.linebot.function01.reply;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.linecorp.bot.client.LineMessagingService;
import com.linecorp.bot.client.LineMessagingServiceBuilder;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.action.Action;
import com.linecorp.bot.model.action.PostbackAction;
import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.LocationMessageContent;
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
import forest.rice.field.k.linebot.function01.pokemon.Pokemon;
import forest.rice.field.k.linebot.function01.pokemon.PokemonManager;
import retrofit2.Response;

public class ReplyGo implements Reply {

	private MessageEvent<MessageContent> event;
	private LocationMessageContent content;

	@Override
	public boolean match(Event event) {

		if (!EventUtil.getEventType(event).equals(EVENT_TYPE.MESSAGE)) {
			return false;
		}

		try {
			this.event = EventUtil.getMessageEvent(event);

			if (!MessageContentUtil.getMessageContentType(this.event.getMessage()).equals(MESSAGE_TYPE.LOCATION)) {
				return false;
			}
			content = MessageContentUtil.getLocationMessageContent(this.event.getMessage());
			return true;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public void execute() {
		try {
			double latitude = content.getLatitude();
			double longitude = content.getLongitude();
			List<Integer> pokemonList = getPokemonLost(latitude, longitude);

			String replyToken = this.event.getReplyToken();
			LineMessagingService client = LineMessagingServiceBuilder
					.create(LineBotDynamoTriggerFunctionHandler.CHANNEL_ACCESS_TOKEN).build();

			List<CarouselColumn> columns = new ArrayList<>();

			PokemonManager manager = new PokemonManager();
			for (Integer i : pokemonList) {
				Pokemon pokemon = manager.requestDetail(i);

				List<Action> actions = new ArrayList<>();
				actions.add(new PostbackAction("たたかう", createData(pokemon, "none")));
				actions.add(new PostbackAction("にがす", createData(pokemon, "none")));
				actions.add(new URIAction("ずかんをみる", pokemon.getUrl()));

				String image = pokemon.getLargeImageUrl();

				CarouselColumn column = new CarouselColumn(image, null, pokemon.getName(), actions);
				// CarouselColumn column = new CarouselColumn(image,
				// pokemon.getName(), pokemon.getName(), actions);

				columns.add(column);
			}
			TemplateMessage messages = new TemplateMessage("altText", new CarouselTemplate(columns));

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

	private List<Integer> getPokemonLost(double latitude, double longitude) {

		List<Integer> list = new ArrayList<>();
		int l1 = (int) (latitude * 1000000);
		int l2 = (int) (longitude * 1000000);

		System.out.println("l1 : " + l1 + ", l2 : " + l2);

		list.add(Math.abs((l1 % 721) + 1));
		list.add(Math.abs((l2 % 721) + 1));
		list.add(Math.abs(((l1 + l2) % 721) + 1));
		list.add(Math.abs(((l1 * l2) % 721) + 1));
		list.add(Math.abs(((l1 - l2) % 721) + 1));

		return list;
	}

	private String createData(Pokemon pokemon, String ball) {
		Map<String, String> map = new HashMap<>();
		map.put("type", "pokemon");
		map.put("no", Integer.toString(pokemon.getNo()));
		map.put("name", pokemon.getName());
		map.put("ball", ball);
		map.put("hp", "10");

		StringBuilder sb = new StringBuilder();

		for (String key : map.keySet()) {
			sb.append(key).append("=").append(map.get(key)).append("&");
		}

		System.out.println("data : " + sb.toString());

		return sb.toString();
	}

	@Override
	public String help() {
		return "位置情報を送信→Go!";
	}

}
