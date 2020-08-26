package bsp02.sozialesNetzwerk.Impl;

import java.util.List;

import bsp02.sozialesNetzwerk.IFs.Message;

public class MessageImpl implements Message {

	/** # recipients this message was sent to */
	private final List<Integer> recipients;

	/** message type (V1 or V2) */
	private final TypeOfMessage msgType;

	/** the time when the message was sent */
	private final long time;

	private final String msgContent;

	public MessageImpl(String msgContent, List<Integer> recipients, long time, TypeOfMessage msgType) {
		this.msgContent = msgContent;
		this.recipients = recipients;
		this.time = time;
		this.msgType = msgType;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getNumberOfRecipients() {
		return recipients.size();
	}

	/**
	 * {@inheritDoc}
	 */
	public TypeOfMessage getMessageType() {
		return msgType;
	}

	/**
	 * {@inheritDoc}
	 */
	public long getMessageSentTime() {
		return time;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getMessageContent() {
		return msgContent;
	}

}
