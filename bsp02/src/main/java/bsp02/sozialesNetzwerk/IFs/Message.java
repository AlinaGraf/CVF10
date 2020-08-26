package bsp02.sozialesNetzwerk.IFs;

import bsp02.sozialesNetzwerk.Impl.TypeOfMessage;

/**
 * @author alina
 *
 */
public interface Message {
	
	/**
	 * @return the number of recipients
	 */
	int getNumberOfRecipients();

	/**
	 * @return the time the message was sent
	 */
	long getMessageSentTime();

	/**
	 * @return the message type
	 */
	TypeOfMessage getMessageType();

	/**
	 * @return the mesage content
	 */
	String getMessageContent();
}
