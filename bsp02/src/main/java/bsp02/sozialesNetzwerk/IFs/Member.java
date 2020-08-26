package bsp02.sozialesNetzwerk.IFs;

public interface Member {
	// wegen Aufgabenstellung simplifiziert (keine persönliche Daten, Msgs werden
	// nur "verschickt" und nicht empfangen, etc..

	void addFriend(Member m);

	void sendMessageV1();

	void sendMessageV2();
}
