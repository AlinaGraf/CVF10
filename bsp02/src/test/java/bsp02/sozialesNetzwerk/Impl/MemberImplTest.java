/**
 * 
 */
package bsp02.sozialesNetzwerk.Impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bsp02.sozialesNetzwerk.IFs.Member;
import bsp02.sozialesNetzwerk.IFs.MemberStore;
import bsp02.sozialesNetzwerk.IFs.Message;

/**
 * @author alina
 *
 */
public class MemberImplTest {

	private final MemberStore ms = MemberStoreImpl.getInstance();
	Member m1;
	Member m2;
	Member m3;
	Member m4;
	private List<Integer> expFriends;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		expFriends = new ArrayList<>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		ms.removeAllMembers();
		m1 = null;
		m2 = null;
		m3 = null;
		m4 = null;
		expFriends = null;
	}

	/**
	 * Test method for {@link bsp02.sozialesNetzwerk.Impl.MemberImpl#getId()}.
	 */
	@Test
	public void testGetId() {
		givenMembersInMS();
		Assert.assertSame(0, m1.getId());
		Assert.assertSame(1, m2.getId());
		Assert.assertSame(2, m3.getId());
		Assert.assertSame(3, m4.getId());
	}

	/**
	 * Test method for
	 * {@link bsp02.sozialesNetzwerk.Impl.MemberImpl#addFriend(bsp02.sozialesNetzwerk.IFs.Member)}.
	 */
	@Test
	public void testAddFriend1() {
		givenMembersInMS();
		whenFriendAdded(m1, m4);
		thenTheAddedFriendsAreInTheFriendList(m1);
	}

	/**
	 * Test method for
	 * {@link bsp02.sozialesNetzwerk.Impl.MemberImpl#addFriend(bsp02.sozialesNetzwerk.IFs.Member)}.
	 */
	@Test
	public void testAddFriend2() {
		givenMembersInMS();
		whenFriendsAdded();
		thenTheAddedFriendsAreInTheFriendList(m1);
	}

	private void whenFriendsAdded() {
		addFriend(m1, m2);
		addFriend(m1, m3);
		addFriend(m1, m4);
	}

	private void thenTheAddedFriendsAreInTheFriendList(Member m1) {
		Assert.assertEquals(expFriends, m1.getFriends());
	}

	private void whenFriendAdded(Member m1, Member f1) {
		addFriend(m1, f1);
	}

	private void addFriend(Member m1, Member f1) {
		m1.addFriend(f1);
		expFriends.add(f1.getId());
	}

	/**
	 * Test method for
	 * {@link bsp02.sozialesNetzwerk.Impl.MemberImpl#getNumberOfFriends()}.
	 */
	@Test
	public void testGetNumberOfFriends1() {
		givenMembersInMS();
		whenFriendAdded(m1, m2);
		thenTheNumberOfFriendsIs(m1, 1);
	}

	/**
	 * Test method for
	 * {@link bsp02.sozialesNetzwerk.Impl.MemberImpl#getNumberOfFriends()}.
	 */
	@Test
	public void testGetNumberOfFriends2() {
		givenMembersInMS();
		whenFriendsAdded();
		thenTheNumberOfFriendsIs(m1, 3);
	}

	private void thenTheNumberOfFriendsIs(Member m1, int i) {
		Assert.assertEquals(i, m1.getNumberOfFriends());

	}

	/**
	 * Test method for
	 * {@link bsp02.sozialesNetzwerk.Impl.MemberImpl#sendMessageV1(java.lang.String)}.
	 */
	@Test
	public void testSendMessageV1() {
		givenMembersInMS();
		givenFriendsAdded();
		whenV1MsgIsSent();
		thenTheMessageWasSentToRecs(2);
	}

	private void thenTheMessageWasSentToRecs(int i) {
		List<Message> messages = m1.getMessages();
		Assert.assertEquals(1, messages.size());

		Message message = messages.get(0);
		Assert.assertEquals(i, message.getNumberOfRecipients());
	}

	private void whenV1MsgIsSent() {
		m1.sendMessageV1("Hello Friends!");

	}

	private void givenFriendsAdded() {
		addFriend(m1, m2);
		addFriend(m2, m1);
		addFriend(m2, m3);
		addFriend(m3, m1);
		addFriend(m3, m2);
		addFriend(m3, m4);
	}

	/**
	 * Test method for
	 * {@link bsp02.sozialesNetzwerk.Impl.MemberImpl#sendMessageV2(java.lang.String)}.
	 */
	@Test
	public void testSendMessageV2() {
		givenMembersInMS();
		givenFriendsAdded();
		whenV2MsgIsSent();
		thenTheMessageWasSentToRecs(3);
	}

	private void whenV2MsgIsSent() {
		m1.sendMessageV2("Hello Friends!");

	}

	/**
	 * Test method for {@link bsp02.sozialesNetzwerk.Impl.MemberImpl#getFriends()}.
	 */
	@Test
	public void testGetFriends() {
		givenMembersInMS();
		List<Integer> expFriends = new ArrayList<>();
		m4.addFriend(m1);
		m4.addFriend(m2);
		m4.addFriend(m3);

		expFriends.add(m1.getId());
		expFriends.add(m2.getId());
		expFriends.add(m3.getId());

		Assert.assertEquals(expFriends, m4.getFriends());
	}

	private void givenMembersInMS() {
		m1 = ms.addNewMember();
		m2 = ms.addNewMember();
		m3 = ms.addNewMember();
		m4 = ms.addNewMember();
	}

}
