package bsp02.sozialesNetzwerk.Impl;

import java.util.Date;

import bsp02.sozialesNetzwerk.IFs.Message;

public class MessageImpl implements Message {

	/** # recipients this message was sent to */
	private int nrOfRecipients;
	
	/** message type (V1 or V2) */
	private TypeOfMessage msgType;
	
	/** the time when the message was sent */
	private Date time;

	public MessageImpl(int nrOfRecipients, Date time, TypeOfMessage msgType) {
		this.nrOfRecipients = nrOfRecipients;
		this.time = time;
		this.msgType = msgType;
	}

	public int getNumberOfRecipients() {
		return nrOfRecipients;
	}

}
