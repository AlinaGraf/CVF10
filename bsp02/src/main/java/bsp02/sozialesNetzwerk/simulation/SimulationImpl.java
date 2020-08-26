package bsp02.sozialesNetzwerk.simulation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import bsp02.sozialesNetzwerk.IFs.Member;
import bsp02.sozialesNetzwerk.IFs.MemberStore;
import bsp02.sozialesNetzwerk.Impl.MemberStoreImpl;

/**
 * @author alina
 *
 */
public class SimulationImpl {

	private static final int NR_OF_FRIENDS_GroupA = 10;
	private static final int NR_OF_FRIENDS_GroupB = 20;
	private static final int NR_OF_FRIENDS_GroupC = 40;
	private static final int NR_OF_FRIENDS_GroupD = 80;
	private int nrOfMembersOnDay1;
	private int nrOfMembersGrA;
	private int nrOfMembersGrB;
	private int nrOfMembersGrC;
	private int nrOfMembersGrD;
	private int simRuntimeInDays;

	MemberStore ms = MemberStoreImpl.getInstance();

	public SimulationImpl(int nrOfMembersOnDay1, int nrOfMembersGrA, int nrOfMembersGrB, int nrOfMembersGrC,
			int nrOfMembersGrD, int simRuntimeInDays) {
		this.nrOfMembersOnDay1 = nrOfMembersOnDay1;
		this.nrOfMembersGrA = nrOfMembersGrA;
		this.nrOfMembersGrB = nrOfMembersGrB;
		this.nrOfMembersGrC = nrOfMembersGrC;
		this.nrOfMembersGrD = nrOfMembersGrD;
		this.simRuntimeInDays = simRuntimeInDays;
		System.out.println("Initializing Data for Simulation..");
		prepareSimulation();
	}

	public void startSimulation() {
		System.out.println("Starting Simulation..");
		File logFile = new File("simulationLog.txt");
		System.out.println(logFile.getAbsolutePath().toString());
		FileWriter logger;
		try {
			logger = new FileWriter("simulationLog.txt");
			for (int i = 0; i < simRuntimeInDays; i++) {
				System.out.println("Simulating Day " + i + ":");
				runSimulationDay(i, logger);
			}
			System.out.println("Simulation finished!");
			logger.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param logger
	 * @param i
	 * @throws IOException
	 */
	private void logDay(FileWriter logger, int i) throws IOException {
		logger.write("Tag " + i + " - Anzahl Personen " + ms.getNumberOfMembers() + " \n");
		Member randomMember = ms.getRandomMember().get();
		logger.write("Nachricht Variante 1 hat " + randomMember.getLastMessageV1().get().getNumberOfRecipients()
				+ " Personen erreicht und Nachricht Variante 2 hat "
				+ randomMember.getLastMessageV2().get().getNumberOfRecipients() + " Personen erreicht \n");
	}

	private void runSimulationDay(int i, FileWriter logger) throws IOException {
		long startTime = System.currentTimeMillis();
		System.out.println("\t *making new friends!");
		addNewFriendships();
		System.out.println("\t *sending messages!");
		sendMessages();
		System.out.println("\t *logging daily changes.");
		logDay(logger, i);
		long endTime = System.currentTimeMillis();

		long duration = endTime - startTime;
		long minutes = TimeUnit.MILLISECONDS.toMinutes(duration);
		long seconds = TimeUnit.MILLISECONDS.toSeconds(duration);
		System.err.println("Simulating Day " + i + " took " + minutes + " minutes/" + seconds + " seconds.");

	}

	private void sendMessages() {
		ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		for (Member member : ms.getAllMembers()) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					member.sendMessageV1("Hello Friends!");
					member.sendMessageV2("Do I know you?");
				}
			});
		}
		executor.shutdown();
		try {
			System.out.println("\t *waiting for messages to be delivered...");
			boolean finishedSuccessfully = executor.awaitTermination(10, TimeUnit.MINUTES);
			System.out.println(
					"\t\t *is there a chance some messages got lost? : " + (finishedSuccessfully ? "nope" : "yep, sorry"));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private void addNewFriendships() {
		List<Member> mLessThan25Fs = ms.getAllMembersWithMaxFriends(25);
		for (Member member : mLessThan25Fs) {
			member.addFriend(ms.getRandomMember().get());
			member.addFriend(ms.addNewMember()); // TODO check new member is not randomly selected later
		}
		List<Member> mAtLeast26Fs = ms.getAllMembersWithMinFriends(26);
		for (Member member : mAtLeast26Fs) {
			member.addFriend(ms.getRandomMember().get());
			member.addFriend(ms.addNewMember());
			member.addFriend(ms.addNewMember());
			member.addFriend(ms.addNewMember());
		}
	}

	private void prepareSimulation() {
		createInitialMembersDay0();
		addFriendRelationships();
	}

	private void addFriendRelationships() {
		System.out.println("\t * Making first friends...");
		addGroupDFriends();
		addGroupCFriends();
		addGroupBFriends();
		addGroupAFriends();
	}

	private void addGroupAFriends() {
		while (ms.getAllMembersWithExactlyNFriends(NR_OF_FRIENDS_GroupA).size() < nrOfMembersGrA) {
			Member randomMember = ms.getRandomMember().get();
			while (randomMember.getNumberOfFriends() < NR_OF_FRIENDS_GroupA) {
				randomMember.addFriend(ms.getRandomMember().get());
			}
		}
	}

	private void addGroupBFriends() {
		while (ms.getAllMembersWithExactlyNFriends(NR_OF_FRIENDS_GroupB).size() < nrOfMembersGrB) {
			Member randomMember = ms.getRandomMember().get();
			while (randomMember.getNumberOfFriends() < NR_OF_FRIENDS_GroupB) {
				randomMember.addFriend(ms.getRandomMember().get());
			}
		}

	}

	private void addGroupCFriends() {
		while (ms.getAllMembersWithExactlyNFriends(NR_OF_FRIENDS_GroupC).size() < nrOfMembersGrC) {
			Member randomMember = ms.getRandomMember().get();
			while (randomMember.getNumberOfFriends() < NR_OF_FRIENDS_GroupC) {
				randomMember.addFriend(ms.getRandomMember().get());
			}
		}

	}

	private void addGroupDFriends() {
		while (ms.getAllMembersWithExactlyNFriends(NR_OF_FRIENDS_GroupD).size() < nrOfMembersGrD) {
			Member randomMember = ms.getRandomMember().get();
			while (randomMember.getNumberOfFriends() < NR_OF_FRIENDS_GroupD) {
				randomMember.addFriend(ms.getRandomMember().get());
			}
		}

	}

	private void createInitialMembersDay0() {
		System.out.println("\t * Creating initial members...");
		for (int i = 0; i < nrOfMembersOnDay1; i++) {
			ms.addNewMember();
		}

	}
}
