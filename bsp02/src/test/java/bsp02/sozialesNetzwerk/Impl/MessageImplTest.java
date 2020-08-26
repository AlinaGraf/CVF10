package bsp02.sozialesNetzwerk.Impl;

import static org.junit.Assert.fail;

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

	private int numberOfRecipients1;

	private int numberOfRecipients2;

	private TypeOfMessage messageType1;

	private TypeOfMessage messageType2;

	private long actualTime1;

	private long actualTime2;

	private String actMessageContent1;

	private String actMessageContent2;

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
		message1 = null;
		message2 = null;
		numberOfRecipients1 = 0;
		numberOfRecipients2 = 0;
	}

	@Test
	public void testGetNumberOfRecipients() throws ParseException {
		given2Messages();
		whenNrOfRecChecked();
		thenTheyShouldBe(4, 6);
	}

	private void thenTheyShouldBe(int n1, int n2) {
		Assert.assertEquals(n1, numberOfRecipients1);
		Assert.assertEquals(n2, numberOfRecipients2);
	}

	private void whenNrOfRecChecked() {
		numberOfRecipients1 = message1.getNumberOfRecipients();
		numberOfRecipients2 = message2.getNumberOfRecipients();

	}

	@Test
	public void testGetMessageType() throws ParseException {
		given2Messages();
		whenMsgTypeChecked();
		thenTheTypesShouldBe(TypeOfMessage.V1, TypeOfMessage.V2);
	}

	private void thenTheTypesShouldBe(TypeOfMessage t1, TypeOfMessage t2) {
		Assert.assertEquals(t1, messageType1);
		Assert.assertEquals(t2, messageType2);

	}

	private void whenMsgTypeChecked() {
		messageType1 = message1.getMessageType();
		messageType2 = message2.getMessageType();

	}

	@Test
	public void testGetMessageSentTime() throws ParseException {

		long t1 = dateTimeFormatter.parse(dateTime1).getTime();
		long t2 = dateTimeFormatter.parse(dateTime2).getTime();
		given2Messages();
		whenMsgTimeChecked();
		thenTheTimesShouldBe(t1, t2);
	}

	private void thenTheTimesShouldBe(long t1, long t2) {
		Assert.assertEquals(t1, actualTime1);
		Assert.assertEquals(t2, actualTime2);
		
	}

	private void whenMsgTimeChecked() {
		actualTime1=message1.getMessageSentTime();
		actualTime2=message2.getMessageSentTime();
		
	}

	@Test
	public void testGetMessageContent() throws ParseException {
		given2Messages();
		whenMsgContentChecked();
		thenTheContentShouldBe(msg1_txt, msg2_txt);
	}

	private void thenTheContentShouldBe(String msg1Txt, String msg2Txt) {
		Assert.assertEquals(msg1Txt, actMessageContent1);
		Assert.assertEquals(msg2Txt, actMessageContent2);
		
	}

	private void whenMsgContentChecked() {
		actMessageContent1 = message1.getMessageContent();
		actMessageContent2 = message2.getMessageContent();
		
	}

	private void given2Messages() throws ParseException {
		message1 = new MessageImpl(msg1_txt, Arrays.asList(1, 32, 4, 5),
				dateTimeFormatter.parse(dateTime1).getTime(), TypeOfMessage.V1);
		message2 = new MessageImpl(msg2_txt, Arrays.asList(1, 32, 4, 5, 78, 9),
				dateTimeFormatter.parse(dateTime2).getTime(), TypeOfMessage.V2);
	}

}
