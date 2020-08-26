package bsp02.sozialesNetzwerk.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import bsp02.sozialesNetzwerk.IFs.Member;
import bsp02.sozialesNetzwerk.IFs.MemberStore;

/**
 * Manages all members of the social network
 * 
 * @author alina
 */
public class MemberStoreImpl implements MemberStore {

	/** the next free Member ID */
	private Integer nextMemberID = 0;

	/** a map of all users, mapped to their unique ID */
	private Map<Integer, Member> members = new HashMap<Integer, Member>();

	/** randomizer used for getting a random member from the store */
	private Random randomizer = new Random();

	/** the single instance of this class */
	private static MemberStoreImpl single_instance = null;

	/**
	 * private constructor restricted to this class itself
	 */
	private MemberStoreImpl() {

	}

	/**
	 * @return the single instance of the {@link MemberStore}
	 */
	public static MemberStoreImpl getInstance() {
		if (single_instance == null) {
			single_instance = new MemberStoreImpl();
		}
		return single_instance;
	}


	/**
	 * {@inheritDoc}
	 */
	public Member addNewMember() {
		MemberImpl newMember = new MemberImpl(nextMemberID);
		members.put(nextMemberID, newMember);
		nextMemberID++;
		return newMember;
	}

	/**
	 * {@inheritDoc}
	 */
	public Optional<Member> getMember(Integer memberID) {
		if (!members.isEmpty() && members.containsKey(memberID)) {

			return Optional.ofNullable(members.get(memberID));
		}
		return Optional.empty();
	}

	/**
	 * {@inheritDoc}
	 */
	public Optional<Member> getRandomMember() {
		if (members.size() > 0) {
			int randomMemberID = randomizer.nextInt(members.size());
			return Optional.ofNullable(members.get(randomMemberID));
		}
		return Optional.empty();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Member> getAllMembersWithMaxFriends(int maxNrOfFriends) {
		ArrayList<Member> filteredMemberList = new ArrayList<Member>();
		members.values().forEach(m -> {
			if (m.getNumberOfFriends() <= maxNrOfFriends) {
				filteredMemberList.add(m);
			}
		});
		return filteredMemberList;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Member> getAllMembersWithMinFriends(int minNrOfFriends) {
		ArrayList<Member> filteredMemberList = new ArrayList<Member>();
		members.values().forEach(m -> {
			if (m.getNumberOfFriends() >= minNrOfFriends) {
				filteredMemberList.add(m);
			}
		});
		return filteredMemberList;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Member> getAllMembers() {
		ArrayList<Member> allMembers = new ArrayList<Member>();
		allMembers.addAll(members.values());
		return allMembers;
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeAllMembers() {
		members.clear();
		nextMemberID = 0;
	}

}
