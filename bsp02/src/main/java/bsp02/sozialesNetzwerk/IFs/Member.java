package bsp02.sozialesNetzwerk.IFs;

public interface Member {
	// wegen Aufgabenstellung simplifiziert (keine persönliche Daten, Msgs werden
	// nur "verschickt" und nicht empfangen, etc..
	
	Integer getId();

	void addFriend(Integer mID);

	void sendMessageV1();

	void sendMessageV2();
	
	int getNumberOfFriends();
}
