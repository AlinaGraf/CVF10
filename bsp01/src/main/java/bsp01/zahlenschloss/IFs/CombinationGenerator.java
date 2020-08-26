package bsp01.zahlenschloss.IFs;

/**
 * Generates a list of combinations which can be reached with the least amount of turns
 * 
 * @author alina
 *
 */
public interface CombinationGenerator {
	
	/**
	 * Returns the Combination(s) which can be achieved with the minimal amount of turns
	 * 
	 * @param currentCombination the current combination
	 * @return a proposal containing one or more combinations and the number of turns needed to reach them
	 */
	CombinationProposal generateCombinations(Combination currentCombination);
	
	/**
	 * Returns the Combination(s) which can be achieved with the minimal amount of turns
	 * 
	 * @param d1..d4 the 4 digits of the current combination
	 * 
	 * @return a proposal containing one or more combinations and the number of turns needed to reach them
	 */
	CombinationProposal generateCombinations(int d1, int d2, int d3, int d4);
}
