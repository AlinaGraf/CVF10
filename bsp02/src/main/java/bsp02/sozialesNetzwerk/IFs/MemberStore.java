package bsp02.sozialesNetzwerk.IFs;

import java.util.ArrayList;

/**
 * Stores all Members in the social network
 * 
 * @author alina
 *
 */
public interface MemberStore {
	// wegen Aufgabenstellung simplifiziert (z.B. Mitglieder können nicht
	// ausstiegen, keine DB, ..)
	
	Member addNewMember();

	Member getRandomMember();

	ArrayList<Member> getAllMembersWithMaxFriends(int maxNrOfFriends);

	ArrayList<Member> getAllMembersWithMinFriends(int minNrOfFriends);

	ArrayList<Member> getAllMembers();

}
