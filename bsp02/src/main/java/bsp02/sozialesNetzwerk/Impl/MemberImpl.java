package bsp02.sozialesNetzwerk.Impl;

import java.util.ArrayList;

import bsp02.sozialesNetzwerk.IFs.Member;

/**
 * @author alina
 *
 */
public class MemberImpl implements Member {

	/** List of Friends */
	private ArrayList<Integer> friends = new ArrayList<Integer>();

	/** The Id of the current member */
	private Integer memberId;

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
	 * @param the member to add as a friend
	 */
	public void addFriend(Integer newFriendId) {
		if (newFriendId != memberId && !friends.contains(newFriendId)) {
			friends.add(newFriendId);
		}

	}

	public int getNumberOfFriends() {
		return friends.size();
	}

	public void sendMessageV1() {
		// TODO Auto-generated method stub

	}

	public void sendMessageV2() {
		// TODO Auto-generated method stub

	}

}
