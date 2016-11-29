package forest.rice.field.k.linebot.function01.reply;

import com.linecorp.bot.model.event.Event;

public interface Reply {

	public boolean match(Event event);

	public void execute();

}
