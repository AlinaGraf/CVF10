package bsp01.zahlenschloss.Impls;

import java.util.ArrayList;

import bsp01.zahlenschloss.IFs.Combination;
import bsp01.zahlenschloss.IFs.CombinationProposal;

/**
 * Contains the combination(s) with the least amount of turns as well as the
 * number of turns needed to get to them.
 * 
 * @author alina
 *
 */
public class CombinationProposalImpl implements CombinationProposal {

	/** a list of one or more combinations */
	private ArrayList<Combination> combinations;

	/** the minimum number of turns to reach the combinations */
	private int numberOfTurns;

	/**
	 * Creates a new combinationProposal containing a list of one or more
	 * combinations and the number of turns needed to reach them
	 * 
	 * @param combinations  list of Combinations
	 * @param numberOfTurns the min. number of turns needed to reach the
	 *                      combinations
	 */
	public CombinationProposalImpl(ArrayList<Combination> combinations, int numberOfTurns) {
		this.combinations = combinations;
		this.numberOfTurns = numberOfTurns;
	}

	/**
	 * Returns the list of combinations in the proposal
	 * 
	 * @return a list of combinations
	 */
	public ArrayList<Combination> getCombinations() {
		return combinations;
	}

	/**
	 * @return the min. number of turns
	 */
	public int getNumberOfTurns() {
		return numberOfTurns;
	}
}
