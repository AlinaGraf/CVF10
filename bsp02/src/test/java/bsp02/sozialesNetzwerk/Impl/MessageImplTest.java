package bsp02.sozialesNetzwerk.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bsp02.sozialesNetzwerk.IFs.Message;

public class MessageImplTest {

	private static final String msg2_txt = "Hello All Friends!";

	private static final String msg1_txt = "Hello Friends!";

	private static final String dateTime1 = "2020-08-26 10:20:30";

	private static final String dateTime2 = "2020-08-08 14:05:27";

	SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	Message message1;

	Message message2;

	@Before
	public void setUp() throws Exception {
		message1 = new MessageImpl(msg1_txt, Arrays.asList(1, 32, 4, 5), dateTimeFormatter.parse(dateTime1).getTime(),
				TypeOfMessage.V1);
		message2 = new MessageImpl(msg2_txt, Arrays.asList(1, 32, 4, 5, 78, 9),
				dateTimeFormatter.parse(dateTime2).getTime(), TypeOfMessage.V2);
	}

	@After
	public void tearDown() throws Exception {
		message1 = null;
		message2 = null;
	}

	@Test
	public void testMessageFields() throws ParseException {
		Assert.assertEquals(4, message1.getNumberOfRecipients());
		Assert.assertEquals(6, message2.getNumberOfRecipients());
		Assert.assertEquals(message1.getMessageType(), TypeOfMessage.V1);
		Assert.assertEquals(message2.getMessageType(), TypeOfMessage.V2);
		Assert.assertEquals(dateTimeFormatter.parse(dateTime1).getTime(), message1.getMessageSentTime());
		Assert.assertEquals(dateTimeFormatter.parse(dateTime2).getTime(), message2.getMessageSentTime());
		Assert.assertEquals(msg1_txt, message1.getMessageContent());
		Assert.assertEquals(msg2_txt, message2.getMessageContent());

	}

}
