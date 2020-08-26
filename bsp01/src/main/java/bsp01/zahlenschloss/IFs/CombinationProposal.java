package bsp01.zahlenschloss.IFs;

import java.util.List;

/**
 * Contains the combination(s) with the least amount of turns as well as the
 * number of turns needed to get to them.
 * 
 * @author alina
 *
 */
public interface CombinationProposal {
	/**
	 * @return a list of one or more possible combinations
	 */
	List<Combination> getCombinations();

	/**
	 * @return the number of turns needed for the combinations
	 */
	int getNumberOfTurns();
}
