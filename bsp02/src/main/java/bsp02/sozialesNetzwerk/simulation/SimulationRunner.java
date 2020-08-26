package bsp02.sozialesNetzwerk.simulation;

public class SimulationRunner {

	public static void main(String[] args) {
		int simulationRuntimeInDays = 3;
		int numberOfMembersOnDay1 = 1000;
		SimulationImpl simulationImpl = new SimulationImpl(numberOfMembersOnDay1, 50, 30, 15, 5, simulationRuntimeInDays);
		simulationImpl.startSimulation();
	}
}
