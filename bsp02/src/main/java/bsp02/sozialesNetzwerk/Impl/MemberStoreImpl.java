package bsp02.sozialesNetzwerk.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import bsp02.sozialesNetzwerk.IFs.Member;
import bsp02.sozialesNetzwerk.IFs.MemberStore;

/**
 * Manages all members of the social network
 * 
 * @author alina
 */
public class MemberStoreImpl implements MemberStore {

	/** the next free Member Id */
	private Integer nextId = 0;

	/** a map of all users, mapped to their unique ID */
	private HashMap<Integer, Member> members = new HashMap<Integer, Member>();

	/** randomizer used for getting a random member from the store */
	private Random randomizer = new Random();

	/** the single instance of this class */
	private static MemberStoreImpl single_instance = null;

	/**
	 * private constructor restricted to this class itself
	 */
	private MemberStoreImpl() {

	}

	public static MemberStoreImpl getInstance() {
		if (single_instance == null) {
			single_instance = new MemberStoreImpl();
		}
		return single_instance;
	}

	/**
	 * Creates a new Member and adds it to the Store
	 * 
	 * @return the newly created member
	 */
	public Member addNewMember() {
		MemberImpl newMember = new MemberImpl(nextId);
		members.put(nextId, newMember);
		nextId++;
		return newMember;
	}

	/**
	 * @return a random member from the store
	 */
	public Member getRandomMember() {
		int rMemId = randomizer.nextInt(members.size());
		return members.get(rMemId);
	}

	/**
	 * Returns a list of members with the given max. number of friends
	 * 
	 * @param the maximum number of friends to filter the members by
	 * @return the list of members filtered by number of friends
	 */
	public ArrayList<Member> getAllMembersWithMaxFriends(int maxNrOfFriends) {
		ArrayList<Member> filteredMemberList = new ArrayList<Member>();
		members.values().forEach(m -> {
			if (m.getNumberOfFriends() <= maxNrOfFriends) {
				filteredMemberList.add(m);
			}
		});
		return filteredMemberList;
	}

	/**
	 * Returns a list of members with the given min. number of friends
	 * 
	 * @param the minimum number of friends to filter the members by
	 * @return the list of members filtered by number of friends
	 */
	public ArrayList<Member> getAllMembersWithMinFriends(int minNrOfFriends) {
		ArrayList<Member> filteredMemberList = new ArrayList<Member>();
		members.values().forEach(m -> {
			if (m.getNumberOfFriends() >= minNrOfFriends) {
				filteredMemberList.add(m);
			}
		});
		return filteredMemberList;
	}

	/**
	 * @return a list of all members in the MemberStore
	 */
	public ArrayList<Member> getAllMembers() {
		ArrayList<Member> allMembers = new ArrayList<Member>();
		allMembers.addAll(members.values());
		return allMembers;
	}

	/**
	 * removes all members from the memberStore
	 */
	public void removeAllMembers() {
		members.clear();
		nextId = 0;

	}

}
