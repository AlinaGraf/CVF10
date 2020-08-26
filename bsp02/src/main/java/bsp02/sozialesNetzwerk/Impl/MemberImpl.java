package bsp02.sozialesNetzwerk.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import bsp02.sozialesNetzwerk.IFs.Member;
import bsp02.sozialesNetzwerk.IFs.Message;

/**
 * @author alina
 *
 */
public class MemberImpl implements Member {

	/** the member store */
	MemberStoreImpl mStore = MemberStoreImpl.getInstance();

	/** List of Friends */
	private List<Integer> friends = new ArrayList<Integer>();

	/** The Id of the current member */
	private Integer memberId;

	/** messages mapped to the time they were sent */
	List<Message> messagesV1 = new ArrayList<Message>();
	List<Message> messagesV2 = new ArrayList<Message>();

	/**
	 * 
	 * @param memberId the unique ID of the member
	 */
	public MemberImpl(Integer memberId) {
		this.memberId = memberId;
	}

	/**
	 * {@inheritDoc}
	 */
	public Integer getID() {
		return memberId;
	}

	/**
	 * {@inheritDoc}
	 */
	public void addFriend(Member newFriend) {
		Integer newFriendID = newFriend.getID();
		if (!newFriendID.equals(this.memberId) && !friends.contains(newFriendID)) {
			friends.add(newFriendID);
			newFriend.addFriend(this);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public int getNumberOfFriends() {
		return getFriends().size();
	}

	/**
	 * {@inheritDoc}
	 */
	public void sendMessageV1(String msgContent) {
		sendMessage(msgContent, compileRecipientsListV1(), TypeOfMessage.V1);
	}

	/**
	 * @return the recipients list for a V1 message
	 */
	private ArrayList<Integer> compileRecipientsListV1() {
		ArrayList<Integer> recipients = new ArrayList<Integer>();
		for (Integer friendID : friends) {
			addFriendAndFriendsAsRecipient(recipients, friendID);
		}
		return recipients;
	}

	/**
	 * Adds the given friend and the friend's friends as recipients
	 * 
	 * @param recipients
	 * @param friendID
	 */
	private void addFriendAndFriendsAsRecipient(List<Integer> recipients, Integer friendID) {
		if (memberIsValidRecipient(recipients, friendID)) {
			recipients.add(friendID);
			addFriendsOfFriend(recipients, friendID);
		}
	}

	/**
	 * Adds the given friend's friends as recipients
	 * 
	 * @param recipients
	 * @param friendID
	 */
	private void addFriendsOfFriend(List<Integer> recipients, Integer friendID) {
		Member member = mStore.getMember(friendID).get();
		for (Integer fID : member.getFriends()) {
			if (memberIsValidRecipient(recipients, fID)) {
				recipients.add(fID);
			}
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
		if (msgType.equals(TypeOfMessage.V1)) {
			messagesV1.add(msg);
		} else {
			messagesV2.add(msg);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void sendMessageV2(String msgContent) {
		sendMessage(msgContent, compileRecipientsListV2(), TypeOfMessage.V2);
	}

	/**
	 * @return the recipients list for a V2 message
	 */
	private ArrayList<Integer> compileRecipientsListV2() {
		ArrayList<Integer> recipients = new ArrayList<Integer>();
		for (Integer friend : friends) {
			addAllFriendsAsRecipient(recipients, friend);
		}
		return recipients;
	}

	/**
	 * Recursively adds all friends of the given member and their friends to the
	 * given list
	 * 
	 * @param recipients the list to add all friend's friends to
	 * @param friend     the starting member
	 */
	private void addAllFriendsAsRecipient(ArrayList<Integer> recipients, Integer friendID) {
		if (memberIsValidRecipient(recipients, friendID)) {
			recipients.add(friendID);
			Member member = mStore.getMember(friendID).get();
			for (Integer fID : member.getFriends()) {
				addAllFriendsAsRecipient(recipients, fID);
			}
		}
	}

	/**
	 * A member is a valid recipient if it's a valid member in the member store and
	 * it's neither the current member nor already in the recipient list
	 * 
	 * @param recipients the list of recipients
	 * @param friendID   the id of the member to check
	 * @return true if the given member is a valid recipient
	 */
	private boolean memberIsValidRecipient(List<Integer> recipients, Integer friendID) {
		return !mStore.getMember(friendID).isEmpty() && !friendID.equals(memberId) && !recipients.contains(friendID);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Integer> getFriends() {
		return friends;
	}

	/**
	 * {@inheritDoc}
	 */
	public Optional<Message> getLastMessageV1() {
		if (messagesV1.size() > 0) {
			return Optional.ofNullable(messagesV1.get(messagesV1.size() - 1));
		}
		return Optional.empty();
	}

	/**
	 * {@inheritDoc}
	 */
	public Optional<Message> getLastMessageV2() {
		if (messagesV2.size() > 0) {
			return Optional.ofNullable(messagesV2.get(messagesV2.size() - 1));
		}
		return Optional.empty();
	}

}
