package forest.rice.field.k.linebot.function01.urlshorter;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UrlShorterManagerTest {

	static UrlShorterManager target;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		target = new UrlShorterManager();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		UrlShorter result = target.getUrlShorter("https://www.google.com");
		System.out.println(result.toString());
	}

}
