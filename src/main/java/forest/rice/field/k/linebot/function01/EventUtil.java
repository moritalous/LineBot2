package forest.rice.field.k.linebot.function01;

import com.linecorp.bot.model.event.BeaconEvent;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.JoinEvent;
import com.linecorp.bot.model.event.LeaveEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.event.UnfollowEvent;
import com.linecorp.bot.model.event.message.MessageContent;

public class EventUtil {

	public enum EVENT_TYPE {
		BEACON, FOLLOW, JOIN, LEAVE, MESSAGE, POSTBACK, UNFOLLOW, UNKNOWN
	}

	public static EVENT_TYPE getEventType(Event event) {
		if (event instanceof BeaconEvent) {
			return EVENT_TYPE.BEACON;
		}
		if (event instanceof FollowEvent) {
			return EVENT_TYPE.FOLLOW;
		}
		if (event instanceof JoinEvent) {
			return EVENT_TYPE.JOIN;
		}
		if (event instanceof LeaveEvent) {
			return EVENT_TYPE.LEAVE;
		}
		if (event instanceof MessageEvent) {
			return EVENT_TYPE.MESSAGE;
		}
		if (event instanceof PostbackEvent) {
			return EVENT_TYPE.POSTBACK;
		}
		if (event instanceof UnfollowEvent) {
			return EVENT_TYPE.UNFOLLOW;
		}

		return EVENT_TYPE.UNKNOWN;
	}

	@SuppressWarnings("unchecked")
	public static MessageEvent<MessageContent> getMessageEvent(Event event) throws Exception {
		if (!getEventType(event).equals(EVENT_TYPE.MESSAGE)) {
			throw new Exception("event is not MessageEvent");
		}

		return (MessageEvent<MessageContent>) event;
	}

	public static PostbackEvent getPostbackEvent(Event event) throws Exception {
		if (!getEventType(event).equals(EVENT_TYPE.POSTBACK)) {
			throw new Exception("event is not PostbackEvent");
		}

		return (PostbackEvent) event;
	}

	public static BeaconEvent getBeaconEvent(Event event) throws Exception {
		if (!getEventType(event).equals(EVENT_TYPE.BEACON)) {
			throw new Exception("event is not BeaconEvent");
		}

		return (BeaconEvent) event;
	}

}
