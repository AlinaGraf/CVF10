package bsp02.sozialesNetzwerk.IFs;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author alina
 *
 */
public interface Member {
	// wegen Aufgabenstellung simplifiziert (keine persönliche Daten, Msgs werden
	// nur "verschickt" und nicht empfangen, etc..

	/**
	 * @return the unique ID of the member
	 */
	Integer getId();

	/**
	 * Adds the given member to the friends list
	 * 
	 * @param m the member to add to the friends list
	 */
	void addFriend(Member m);

	/**
	 * Send the given message to all friends and their friends
	 * 
	 * @param message the message to send
	 */
	void sendMessageV1(String message);

	/**
	 * Send the given message to all members reachable via friends and friends'
	 * friend connections
	 * 
	 * @param message the message to send
	 */
	void sendMessageV2(String message);

	/**
	 * @return the number of friends
	 */
	int getNumberOfFriends();

	/**
	 * @return the list of friends
	 */
	List<Integer> getFriends();

	/**
	 * @return the list of sent messages
	 */
	List<Message> getMessages();
}
