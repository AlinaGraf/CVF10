package bsp02.sozialesNetzwerk.IFs;

import java.util.List;
import java.util.Optional;

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
	Integer getID();

	/**
	 * Adds the given member as friend unless already friended
	 * 
	 * @param the member to add as a friend
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
	 * @return the member's number of friends
	 */
	int getNumberOfFriends();

	/**
	 * @return the member's list of friends
	 */
	List<Integer> getFriends();

	/**
	 * @return the last sent V1 message
	 */
	Optional<Message> getLastMessageV1();

	/**
	 * @return the last sent V2 message
	 */
	Optional<Message> getLastMessageV2();
}
