package forest.rice.field.k.linebot.function01.reply;

import java.util.Arrays;

import com.linecorp.bot.model.event.Event;

public class ReplyManager {

	@SuppressWarnings("unchecked")
	private static Class<? extends Reply>[] classArray = new Class[] { ReplyMusicSearch.class, ReplyRanking.class,
			ReplyGo.class, ReplyGoPostback.class, ReplyEcho.class };

	public static Reply getReply(Event event) {
		return Arrays.asList(classArray).stream().map(clazz -> {
			try {
				return clazz.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			return new ReplyEcho();
		}).distinct().filter(bot -> bot.match(event)).findFirst().get();
	}
}
