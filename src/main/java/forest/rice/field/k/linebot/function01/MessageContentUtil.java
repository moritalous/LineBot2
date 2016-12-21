package forest.rice.field.k.linebot.function01;

import com.linecorp.bot.model.event.message.AudioMessageContent;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.event.message.LocationMessageContent;
import com.linecorp.bot.model.event.message.MessageContent;
import com.linecorp.bot.model.event.message.StickerMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.message.VideoMessageContent;

public class MessageContentUtil {

	public enum MESSAGE_TYPE {
		AUDIO, IMAGE, LOCATION, STICKER, TEXT, UNKNOWN, VIDEO
	}

	public static MESSAGE_TYPE getMessageContentType(MessageContent content) {
		if (content instanceof AudioMessageContent) {
			return MESSAGE_TYPE.AUDIO;
		}
		if (content instanceof ImageMessageContent) {
			return MESSAGE_TYPE.IMAGE;
		}
		if (content instanceof LocationMessageContent) {
			return MESSAGE_TYPE.LOCATION;
		}
		if (content instanceof StickerMessageContent) {
			return MESSAGE_TYPE.STICKER;
		}
		if (content instanceof TextMessageContent) {
			return MESSAGE_TYPE.TEXT;
		}
		if (content instanceof VideoMessageContent) {
			return MESSAGE_TYPE.VIDEO;
		}
		return MESSAGE_TYPE.UNKNOWN;
	}

	public static TextMessageContent getTextMessageContent(MessageContent content) throws Exception {
		if (!getMessageContentType(content).equals(MESSAGE_TYPE.TEXT)) {
			throw new Exception("content is not TextMessageContent");
		}
		return (TextMessageContent) content;
	}

	public static LocationMessageContent getLocationMessageContent(MessageContent content) throws Exception {
		if (!getMessageContentType(content).equals(MESSAGE_TYPE.LOCATION)) {
			throw new Exception("content is not LocationMessageContent");
		}
		return (LocationMessageContent) content;
	}

	public static ImageMessageContent getImageMessageContent(MessageContent content) throws Exception {
		if (!getMessageContentType(content).equals(MESSAGE_TYPE.IMAGE)) {
			throw new Exception("content is not ImageMessageContent");
		}
		return (ImageMessageContent) content;
	}

}
