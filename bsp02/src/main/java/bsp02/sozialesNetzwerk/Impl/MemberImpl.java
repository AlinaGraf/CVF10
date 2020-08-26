package bsp02.sozialesNetzwerk.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bsp02.sozialesNetzwerk.IFs.Member;
import bsp02.sozialesNetzwerk.IFs.Message;

/**
 * @author alina
 *
 */
public class MemberImpl implements Member {

	MemberStoreImpl mStore = MemberStoreImpl.getInstance();

	/** List of Friends */
	private List<Integer> friends = new ArrayList<Integer>();

	/** The Id of the current member */
	private Integer memberId;

	/** messages mapped to the time they were sent */
	Map<Long, Message> messages = new HashMap<Long, Message>();

	/**
	 * 
	 * @param memberId the unique ID of the member
	 */
	public MemberImpl(Integer memberId) {
		this.memberId = memberId;
	}

	/**
	 * @return the member's ID
	 */
	public Integer getId() {
		return memberId;
	}

	/**
	 * Adds the given member to the friend list unless it's already contained
	 * 
	 * @param the member to add as a friend
	 */
	public void addFriend(Member newFriend) {
		Integer newFriendID = newFriend.getId();
		if (!newFriendID.equals(this.memberId) && !friends.contains(newFriendID)) {
			friends.add(newFriendID);
		}
	}

	/**
	 * @return the number of members in the friend list
	 */
	public int getNumberOfFriends() {
		return getFriends().size();
	}

	/**
	 * Send a message to all friends and their friends
	 */
	public void sendMessageV1(String msgContent) {
		ArrayList<Integer> recipients = new ArrayList<Integer>();
		for (Integer friend : friends) {
			addFriendsAsRecipient(recipients, friend);
		}
		sendMessage(msgContent, recipients, TypeOfMessage.V1);
	}

	private void addFriendsAsRecipient(ArrayList<Integer> recipients, Integer friendID) {
		addAsRecipient(recipients, friendID);
		Member member = mStore.getMember(friendID);
		for (Integer fID : member.getFriends()) {
			addAsRecipient(recipients, fID);
		}

	}

	/**
	 * Creates and sends the message
	 * 
	 * @param msgContent the message content
	 * @param recipients the list of recipients
	 * @param msgType    the type of message
	 */
	private void sendMessage(String msgContent, List<Integer> recipients, TypeOfMessage msgType) {
		Message msg = new MessageImpl(msgContent, recipients, System.currentTimeMillis(), msgType);
		// send msg to rec.
		messages.put(msg.getMessageSentTime(), msg);
	}

	/**
	 * send message to all members that can be reached via friend connections
	 */
	public void sendMessageV2(String msgContent) {
		ArrayList<Integer> recipients = new ArrayList<Integer>();
		for (Integer friend : getFriends()) {
			addAllFriendsAsRecipient(recipients, friend);
		}

		sendMessage(msgContent, recipients, TypeOfMessage.V2);
	}

	/**
	 * Adds the given member's ID to the list if not already present
	 * 
	 * @param recipients the list to add the given member's ID to
	 * @param friend     the member to add to the list
	 */
	private void addAsRecipient(ArrayList<Integer> recipients, Integer friendId) {
		if (!recipients.contains(friendId)) {
			recipients.add(friendId);
		}
	}

	/**
	 * Recursively adds all friends of the given member and their friends to the
	 * given list
	 * 
	 * @param recipients the list to add all friend's friends to
	 * @param friend     the starting member
	 */
	private void addAllFriendsAsRecipient(ArrayList<Integer> recipients, Integer friendID) {
		addAsRecipient(recipients, friendID);
		Member member = mStore.getMember(friendID);
		for (Integer fID : member.getFriends()) {
			addAllFriendsAsRecipient(recipients, fID);
		}
	}

	/**
	 * @return a list of member IDs
	 */
	public List<Integer> getFriends() {
		return friends;
	}

	/**
	 * @return a list of all sent messages
	 */
	public List<Message> getMessages() {
		List<Message> msgs = new ArrayList<>();
		msgs.addAll(messages.values());
		return msgs;
	}

}
