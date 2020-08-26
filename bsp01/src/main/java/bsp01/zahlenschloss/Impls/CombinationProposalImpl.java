package bsp01.zahlenschloss.Impls;

import java.util.List;

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
	private final List<Combination> combinations;

	/** the minimum number of turns to reach the combinations */
	private final int numberOfTurns;

	/**
	 * Creates a new combinationProposal containing a list of one or more
	 * combinations and the number of turns needed to reach them
	 * 
	 * @param list          list of Combinations
	 * @param numberOfTurns the min. number of turns needed to reach the
	 *                      combinations
	 */
	public CombinationProposalImpl(List<Combination> list, int numberOfTurns) {
		this.combinations = list;
		this.numberOfTurns = numberOfTurns;
	}

	/**
	 * Returns the list of combinations in the proposal
	 * 
	 * @return a list of combinations
	 */
	public List<Combination> getCombinations() {
		return combinations;
	}

	/**
	 * @return the min. number of turns
	 */
	public int getNumberOfTurns() {
		return numberOfTurns;
	}
}
