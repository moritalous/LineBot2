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
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.CompareFacesRequest;
import com.amazonaws.services.rekognition.model.CompareFacesResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.S3Object;
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

	private static final String REKOGNITION_ACCESS_KEY = System.getenv("REKOGNITION_ACCESS_KEY");
	private static final String REKOGNITION_SECRET_KEY = System.getenv("REKOGNITION_SECRET_KEY");

	private static final String REKOGNITION_S3_BUCKET = System.getenv("REKOGNITION_S3_BUCKET");
	private static final String REKOGNITION_S3_NAME = System.getenv("REKOGNITION_S3_NAME");

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

	private static S3Object createS3Object(String bucket, String name) {
		return new S3Object().withBucket(bucket).withName(name);
	}

	public static S3Object createS3Object(String name) {
		return new S3Object().withBucket(S3_BUCKET_NAME).withName(name);
	}

	private static S3Object createRekognitionSource() {
		return createS3Object(REKOGNITION_S3_BUCKET, REKOGNITION_S3_NAME);
	}

	public static float compareFaces(S3Object target) {
		return compareFaces(createRekognitionSource(), target);
	}

	private static float compareFaces(S3Object source, S3Object target) {
		AWSCredentials rekognitionCred = new BasicAWSCredentials(REKOGNITION_ACCESS_KEY, REKOGNITION_SECRET_KEY);
		AWSCredentialsProvider cp = new AWSStaticCredentialsProvider(rekognitionCred);

		CompareFacesRequest request = new CompareFacesRequest();

		request.withSourceImage(new Image().withS3Object(source)).withTargetImage(new Image().withS3Object(target))
				.withSimilarityThreshold(0.0f);

		AmazonRekognition client = AmazonRekognitionClientBuilder.standard().withRegion(Regions.US_EAST_1)
				.withCredentials(cp).build();
		CompareFacesResult result = client.compareFaces(request);

		if (result.getFaceMatches().size() == 0) {
			return 0.0f;
		} else {
			return result.getFaceMatches().get(0).getSimilarity();
		}

	}

}
