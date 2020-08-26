package bsp02.sozialesNetzwerk.IFs;

import java.util.List;
import java.util.Optional;

/**
 * Manages all Members in the social network
 * 
 * @author alina
 *
 */
public interface MemberStore {
	// wegen Aufgabenstellung simplifiziert (z.B. Mitglieder können nicht
	// ausstiegen, keine DB, keine Syncronisierung ..)
	
	/**
	 * Creates & adds a new member to the store
	 * @return the new member
	 */
	Member addNewMember();

	/**
	 * Returns a random member from the store
	 * @return a random member from the store
	 */
	Optional<Member> getMember(Integer memberID);
	
	/**
	 * Returns a random member from the store
	 * @return a random member from the store
	 */
	Optional<Member> getRandomMember();
	
	/**
	 * Returns a list of members filtered by the number of friends they have
	 * @param maxNrOfFriends the number of friends to filter by
	 * @return all members with at most the given number of friends
	 */
	List<Member> getAllMembersWithMaxFriends(int maxNrOfFriends);

	/**
	 * Returns a list of members filtered by the number of friends they have
	 * @param minNrOfFriends the number of friends to filter by
	 * @return all members with at least the given number of friends
	 */
	List<Member> getAllMembersWithMinFriends(int minNrOfFriends);

	/**
	 * @return all members in the store
	 */
	List<Member> getAllMembers();

	/**
	 * Clears out the store
	 */
	void removeAllMembers();
}
