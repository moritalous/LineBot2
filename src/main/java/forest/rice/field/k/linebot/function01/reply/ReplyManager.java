package forest.rice.field.k.linebot.function01.reply;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.linecorp.bot.model.event.Event;

public class ReplyManager {

	@SuppressWarnings("unchecked")
	private static Class<? extends Reply>[] classArray = new Class[] { ReplyBeacon.class, ReplyRanking.class,
			ReplyMusicSearch.class, ReplyGo.class, ReplyGoPostback.class, ReplyImage.class, ReplyHelp.class,
			ReplyBeybladeChamp.class, ReplyEcho.class };

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

	public static List<String> help() {
		List<String> help = Arrays.asList(classArray).stream().map(clazz -> {
			try {
				return clazz.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			return new ReplyEcho();
		}).distinct().map(e -> {
			return e.help();
		}).collect(Collectors.toList());

		help.removeAll(Collections.singleton(null));

		return help;
	}
}
