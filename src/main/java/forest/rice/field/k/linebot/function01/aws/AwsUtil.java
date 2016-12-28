package forest.rice.field.k.linebot.function01.aws;

import java.io.InputStream;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.polly.AmazonPolly;
import com.amazonaws.services.polly.AmazonPollyClientBuilder;
import com.amazonaws.services.polly.model.OutputFormat;
import com.amazonaws.services.polly.model.SynthesizeSpeechRequest;
import com.amazonaws.services.polly.model.SynthesizeSpeechResult;
import com.amazonaws.services.polly.model.VoiceId;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

import okhttp3.MediaType;

public class AwsUtil {

	private static final String S3_ACCESS_KEY = System.getenv("S3_ACCESS_KEY");
	private static final String S3_SECRET_KEY = System.getenv("S3_SECRET_KEY");

	private static final String S3_BUCKET_NAME = System.getenv("S3_BUCKET_NAME");
	private static final String S3_RESION = System.getenv("S3_RESION");

	private static final String POLLY_ACCESS_KEY = System.getenv("POLLY_ACCESS_KEY");
	private static final String POLLY_SECRET_KEY = System.getenv("POLLY_SECRET_KEY");

	public static void putS3(InputStream inputStream, String keyName, MediaType contentType) {
		String bucketName = S3_BUCKET_NAME;
		AmazonS3 s3client = new AmazonS3Client(new BasicAWSCredentials(S3_ACCESS_KEY, S3_SECRET_KEY));

		AccessControlList acl = new AccessControlList();
		acl.grantPermission(GroupGrantee.AllUsers, Permission.Read);

		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(contentType.toString());

		PutObjectResult result = s3client
				.putObject(new PutObjectRequest(bucketName, keyName, inputStream, metadata).withAccessControlList(acl));
		System.out.println(result.toString());
	}

	public static String s3Filepath(String filename) {
		return String.format("https://%s.amazonaws.com/%s/%s", S3_RESION, S3_BUCKET_NAME, filename);
	}

	public static String createAudioAndGetS3FilePath(String text, String filename) {
		InputStream inputStream = AwsUtil.createAudioFile(text);
		AwsUtil.putS3(inputStream, filename, MediaType.parse("audio/mpeg"));
		return AwsUtil.s3Filepath(filename);
	}

	public static InputStream createAudioFile(String text) {
		AWSCredentials cred = new BasicAWSCredentials(POLLY_ACCESS_KEY, POLLY_SECRET_KEY);
		AWSCredentialsProvider cp = new AWSStaticCredentialsProvider(cred);

		AmazonPolly pollyClient = AmazonPollyClientBuilder.standard().withRegion(Regions.US_EAST_1).withCredentials(cp)
				.build();

		SynthesizeSpeechRequest request = new SynthesizeSpeechRequest();
		request.setOutputFormat(OutputFormat.Mp3);
		request.setSampleRate("22050");
		request.setText(text);
		request.setVoiceId(VoiceId.Mizuki);

		SynthesizeSpeechResult result = pollyClient.synthesizeSpeech(request);
		return result.getAudioStream();

	}

}
