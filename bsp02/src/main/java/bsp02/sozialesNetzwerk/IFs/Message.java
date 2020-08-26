package bsp02.sozialesNetzwerk.IFs;

import bsp02.sozialesNetzwerk.Impl.TypeOfMessage;

/**
 * The Message class represents messages sent between friends. Messages are
 * constant; their values cannot be changed after they are created(Message
 * objects are immutable).
 * 
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
