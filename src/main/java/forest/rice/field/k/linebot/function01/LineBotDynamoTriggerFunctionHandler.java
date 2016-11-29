package forest.rice.field.k.linebot.function01;

import java.io.IOException;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent.DynamodbStreamRecord;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.linecorp.bot.model.event.CallbackRequest;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.MessageContent;

import forest.rice.field.k.linebot.function01.reply.ReplyManager;

public class LineBotDynamoTriggerFunctionHandler implements RequestHandler<DynamodbEvent, String> {

	public static final String CHANNEL_SECRET = System.getenv("CHANNEL_SECRET");
	public static final String CHANNEL_ACCESS_TOKEN = System.getenv("CHANNEL_ACCESS_TOKEN");

	@Override
	public String handleRequest(DynamodbEvent input, Context context) {

		try {
			mainFunction(input, context);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private void mainFunction(DynamodbEvent input, Context context) throws Exception {

		for (DynamodbStreamRecord record : input.getRecords()) {
			Map<String, AttributeValue> newImage = record.getDynamodb().getNewImage();

			String eventString = newImage.get("event").getS();
			String lineSignature = newImage.get("line_signature").getS();

			CallbackRequest callbackRequest = handle(CHANNEL_SECRET, lineSignature, eventString);

			for (Event event : callbackRequest.getEvents()) {

				System.out.println(event.toString());

				ReplyManager.getReply(event).execute();

				// MessageEvent<MessageContent> messageEvent =
				// castEvent2MessageEvent(event);
				//
				// String replyToken = messageEvent.getReplyToken();
				// MessageContent messageContext = messageEvent.getMessage();
				// LineMessagingService client =
				// LineMessagingServiceBuilder.create(CHANNEL_ACCESS_TOKEN).build();
				//
				// if (messageContext instanceof TextMessageContent) {
				// TextMessageContent textContext = (TextMessageContent)
				// messageContext;
				//
				// Response<BotApiResponse> response = client
				// .replyMessage(new ReplyMessage(replyToken, new
				// TextMessage(textContext.getText())))
				// .execute();
				//
				// if (response.isSuccessful()) {
				// System.out.println(response.body().getMessage());
				// } else {
				// System.out.println(response.errorBody().string());
				// }
				//
				// }
			}
		}
	}

	private MessageEvent<MessageContent> castEvent2MessageEvent(Event event) throws Exception {
		if (event instanceof MessageEvent) {
			return (MessageEvent<MessageContent>) event;
		} else {
			throw new Exception("Can not cast Event to MessageEvent");
		}

	}

	private static CallbackRequest handle(String channelSecret, String signature, String json)
			throws Exception, IOException {

		// System.out.println("channelSecret : " + channelSecret);
		// System.out.println("signature : " + signature);
		// System.out.println("json : " + json);

		// validate signature

		// LineSignatureValidator lineSignatureValidator = new
		// LineSignatureValidator(channelSecret.getBytes());

		// if (!lineSignatureValidator.validateSignature(json.getBytes(),
		// signature)) {
		// throw new Exception("Invalid API signature");
		// }

		final CallbackRequest callbackRequest = buildObjectMapper().readValue(json, CallbackRequest.class);
		if (callbackRequest == null || callbackRequest.getEvents() == null) {
			throw new Exception("Invalid content");
		}

		System.out.println(callbackRequest.toString());

		return callbackRequest;
	}

	private static ObjectMapper buildObjectMapper() {
		final ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		// Register JSR-310(java.time.temporal.*) module and read number as
		// millsec.
		objectMapper.registerModule(new JavaTimeModule())
				.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
		return objectMapper;
	}

}
