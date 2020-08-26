/**
 * 
 */
package bsp02.sozialesNetzwerk.Impl;

import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bsp02.sozialesNetzwerk.IFs.Member;
import bsp02.sozialesNetzwerk.IFs.MemberStore;

/**
 * @author alina
 *
 */
public class MemberStoreImplTest {

	private final MemberStore ms = MemberStoreImpl.getInstance();
	private List<Member> filteredMemberList;
	private Optional<Member> member;

	/**
	 * 
	 */
	@Before
	public void setUp() throws Exception {

	}

	/**
	 * 
	 */
	@After
	public void tearDown() throws Exception {
		ms.removeAllMembers();
		filteredMemberList = null;
		member = null;
	}

	/**
	 * Test method for
	 * {@link bsp02.sozialesNetzwerk.Impl.MemberStoreImpl#addNewMember()}.
	 */
	@Test
	public void testAddNewMember() {
		Member m1 = ms.addNewMember();
		Assert.assertSame(0, m1.getId());
		Member m2 = ms.addNewMember();
		Assert.assertSame(1, m2.getId());
		Member m3 = ms.addNewMember();
		Assert.assertSame(2, m3.getId());
	}

	/**
	 * Test method for
	 * {@link bsp02.sozialesNetzwerk.Impl.MemberStoreImpl#getMember()}.
	 */
	@Test
	public void testGetMember() {
		Member m1 = ms.addNewMember();
		Member m2 = ms.addNewMember();
		Member m3 = ms.addNewMember();
		Assert.assertSame(m1, ms.getMember(m1.getId()).get());
		Assert.assertSame(m2, ms.getMember(m2.getId()).get());
		Assert.assertSame(m3, ms.getMember(m3.getId()).get());

	}

	/**
	 * Test method for
	 * {@link bsp02.sozialesNetzwerk.Impl.MemberStoreImpl#getMember()}.
	 */
	@Test
	public void testGetMemberWithEmptyStore() {
		givenStoreIsEmpty();
		whenGetMemberIsCalled();
		thenAnEmptyValueIsReturned();
	}

	/**
	 * Test method for
	 * {@link bsp02.sozialesNetzwerk.Impl.MemberStoreImpl#getRandomMember()}.
	 */
	@Test
	public void testGetRandomMemberWithEmptyStore() {
		givenStoreIsEmpty();
		whenGetRandomMemberIsCalled();
		thenAnEmptyValueIsReturned();
	}

	private void whenGetRandomMemberIsCalled() {
		member = ms.getRandomMember();
	}

	private void thenAnEmptyValueIsReturned() {
		Assert.assertTrue(member.isEmpty());

	}

	private void whenGetMemberIsCalled() {
		member = ms.getMember(0);

	}

	private void givenStoreIsEmpty() {
		ms.removeAllMembers();

	}

	/**
	 * Test method for
	 * {@link bsp02.sozialesNetzwerk.Impl.MemberStoreImpl#getRandomMember()}.
	 */
	@Test
	public void testGetRandomMember() {
		givenNumberOfMembersInMSIs(20);
		Member m1 = ms.getRandomMember().get();
		Member m2 = ms.getRandomMember().get();
		Member m3 = ms.getRandomMember().get();
		Member m4 = ms.getRandomMember().get();
		Assert.assertFalse(m1.equals(m2) && m3.equals(m4) && m4.equals(m1));
	}

	/**
	 * Test method for
	 * {@link bsp02.sozialesNetzwerk.Impl.MemberStoreImpl#getAllMembers()}.
	 */
	@Test
	public void testGetAllMembers() {
		int totalNrMembers = 20;
		givenNumberOfMembersInMSIs(totalNrMembers);
		whenGetAllMembersIsCalled();
		thenTheTotalNumberOfMembersIs(totalNrMembers);
	}

	private void thenTheTotalNumberOfMembersIs(int totalNrMembers) {
		Assert.assertSame(totalNrMembers, ms.getAllMembers().size());
	}

	private void whenGetAllMembersIsCalled() {
		filteredMemberList = ms.getAllMembers();
	}

	private void givenNumberOfMembersInMSIs(int nrMembers) {
		for (int i = 0; i < nrMembers; i++) {
			Member member = ms.addNewMember();
			Assert.assertSame(i, member.getId());
		}
	}

	/**
	 * Test method for
	 * {@link bsp02.sozialesNetzwerk.Impl.MemberStoreImpl#getAllMembersWithMaxFriends(int)}.
	 */
	@Test
	public void testGetAllMembersWithMaxFriends() {
		int totalNrMembers = 20;
		int nrMem = 3;
		int nrFriends = 12;
		givenNumberOfMembersInMSIs(totalNrMembers);
		givenXMembersHaveYFriends(nrMem, nrFriends);
		whenGetAllMembersWithMaxFriendsCalled(nrFriends - 1);
		thenTheNumberOfMembersIs(totalNrMembers - nrMem);
	}

	/**
	 * Test method for
	 * {@link bsp02.sozialesNetzwerk.Impl.MemberStoreImpl#getAllMembersWithMinFriends(int)}.
	 */
	@Test
	public void testGetAllMembersWithMinFriends() {
		int totalNrMembers = 20;
		int nrMem = 3;
		int nrFriends = 12;
		givenNumberOfMembersInMSIs(totalNrMembers);
		givenXMembersHaveYFriends(nrMem, nrFriends);
		whenGetAllMembersWithMinFriendsCalled(nrFriends);
		thenTheNumberOfMembersIs(nrMem);
	}

	/**
	 * Test method for
	 * {@link bsp02.sozialesNetzwerk.Impl.MemberStoreImpl#getAllMembersWithMinFriends(int)}.
	 */
	@Test
	public void testGetAllMembersWithMinFriendsWithEmptyStore() {
		int nrFriends = 12;
		givenStoreIsEmpty();
		whenGetAllMembersWithMinFriendsCalled(nrFriends);
		thenTheNumberOfMembersIs(0);
	}
	
	/**
	 * Test method for
	 * {@link bsp02.sozialesNetzwerk.Impl.MemberStoreImpl#getAllMembers()}.
	 */
	@Test
	public void testRemoveAllMembers() {
		int totalNrMembers = 20;
		givenNumberOfMembersInMSIs(totalNrMembers);
		whenGetAllMembersIsCalled();
		thenTheTotalNumberOfMembersIs(totalNrMembers);
		whenRemoveAllMembersIsCalled();
		thenTheTotalNumberOfMembersIs(0);
		givenNumberOfMembersInMSIs(2);
		whenGetAllMembersIsCalled();
		thenTheTotalNumberOfMembersIs(2);
	}

	private void whenRemoveAllMembersIsCalled() {
		ms.removeAllMembers();

	}

	private void whenGetAllMembersWithMinFriendsCalled(int nrFriends) {
		filteredMemberList = ms.getAllMembersWithMinFriends(nrFriends);
	}

	private void thenTheNumberOfMembersIs(int nrMem) {
		Assert.assertEquals(nrMem, filteredMemberList.size());

	}

	private void whenGetAllMembersWithMaxFriendsCalled(int nrFriends) {
		filteredMemberList = ms.getAllMembersWithMaxFriends(nrFriends);

	}

	private void givenXMembersHaveYFriends(int nrMem, int nrFriends) {
		List<Member> allMembers = ms.getAllMembers();
		for (int i = 0; i < nrMem; i++) {
			Member m = allMembers.get(i);

			for (int j = allMembers.size() - 1; j >= 0; j--) {
				if (m.getNumberOfFriends() >= nrFriends) {
					break;
				}
				m.addFriend(allMembers.get(j));
			}
		}
	}

}
