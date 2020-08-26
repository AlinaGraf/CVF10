package bsp01.zahlenschloss.Impls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import bsp01.zahlenschloss.IFs.Combination;
import bsp01.zahlenschloss.IFs.CombinationGenerator;
import bsp01.zahlenschloss.IFs.CombinationProposal;

/**
 * Generates a list of combinations which can be reached with the least amount
 * of turns
 * 
 * @author alina
 *
 */
public class CombinationGeneratorImpl implements CombinationGenerator {

	/** all valid digits of a combination lock */
	private final static int[] allValidDigits = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

	/**
	 * Returns the Combination(s) which can be achieved with the minimal amount of
	 * turns
	 * 
	 * @param d1..d4 the 4 digits of the current combination
	 * 
	 * @return a proposal containing one or more combinations and the number of
	 *         turns needed to reach them
	 */
	public CombinationProposal generateCombinations(int d1, int d2, int d3, int d4) {
		return generateCombinations(new CombinationImpl(d1, d2, d3, d4));
	}

	/**
	 * Returns a {@link CombinationProposal} containing all combinations with the
	 * minumum amount of turns as well as the amount of turns needed
	 * 
	 * @param currentCombination the starting point for the search
	 * @return the {@link CombinationProposal} containing the combinations and the
	 *         #turns
	 */
	public CombinationProposal generateCombinations(Combination currentCombination) {
		HashMap<Integer, ArrayList<Combination>> hm = getAllCombinations(currentCombination);
		Integer minTurns = getMinimum(hm.keySet());
		return new CombinationProposalImpl(hm.get(minTurns), minTurns);
	}

	/**
	 * @param currentCombination the current combination
	 * @return all possible combinations mapped to the number of turns needed to get
	 *         to them
	 */
	private HashMap<Integer, ArrayList<Combination>> getAllCombinations(Combination currentCombination) {
		HashMap<Integer, ArrayList<Combination>> combinations = new HashMap<Integer, ArrayList<Combination>>();

		if (checkAllDigitsAreSame(currentCombination)) {
			addCurrentCombinationToResult(currentCombination, combinations);
		} else {
			generateAllPossibleCombinations(currentCombination, combinations);
		}
		return combinations;
	}

	/**
	 * Adds the current combination with key 0 to the map
	 * 
	 * @param currentCombination the current combination
	 * @param combinations       the map of #turns to combinations
	 */
	private void addCurrentCombinationToResult(Combination currentCombination,
			HashMap<Integer, ArrayList<Combination>> combinations) {
		ArrayList<Combination> combs = new ArrayList<Combination>();
		combs.add(currentCombination);
		combinations.put(0, combs);
	}

	/**
	 * Computes the #turns for each possible combination and adds them to the map
	 * 
	 * @param currentCombination the current combination
	 * @param combinations       the map of #turns to combinations
	 */
	private void generateAllPossibleCombinations(Combination currentCombination,
			HashMap<Integer, ArrayList<Combination>> combinations) {
		for (Integer digit : allValidDigits) {
			int nrOfTurns = getNrOfTurns(digit, currentCombination.getDigits());
			if (!combinations.containsKey(nrOfTurns)) {
				combinations.put(nrOfTurns, new ArrayList<Combination>());
			}
			combinations.get(nrOfTurns).add(new CombinationImpl(digit, digit, digit, digit));
		}
	}

	/**
	 * Calculates how many turns are necessary to get from the current combination
	 * to the target one (targetDigit,targetDigit,targetDigit,targetDigit)
	 * 
	 * @param targetDigit              the digit to turn to
	 * @param currentCombinationDigits the current values of the lock
	 */
	private int getNrOfTurns(Integer targetDigit, ArrayList<Integer> currentCombinationDigits) {
		Integer nrOT = 0;
		for (Integer d : currentCombinationDigits) {
			nrOT += getMinTurns(targetDigit, d);
		}
		return nrOT;
	}

	/**
	 * Calculates the min. amount of turns needed to get from the starting value to
	 * the target value
	 * 
	 * @param targetDigit   the digit to turn to
	 * @param startingPoint the current value
	 * @return the number of turns
	 */
	private int getMinTurns(Integer targetDigit, Integer startingPoint) {
		int min = 0;
		if (targetDigit >= startingPoint) {
			min = getMinValue(getBackwardTurns(targetDigit, startingPoint),
					getForwardTurns(targetDigit, startingPoint));
		} else {
			min = getMinTurns(startingPoint, targetDigit);
		}
		return min;
	}

	/**
	 * Returns the smaller of the two given values
	 * 
	 * @param val1 first value
	 * @param val2 second value
	 * @return the smaller of the two given int values
	 */
	private int getMinValue(int val1, int val2) {
		if (val1 >= val2) {
			return val2;
		}
		return val1;
	}

	/**
	 * Calculates the number of turns when turning forward
	 * 
	 * @param biggerVal  bigger value
	 * @param smallerVal smaller value
	 * @return the amount of turns when going forward
	 */
	private int getForwardTurns(Integer biggerVal, Integer smallerVal) {
		return 9 - biggerVal + smallerVal + 1;
	}

	/**
	 * Calculates the number of turns when turning backwards
	 * 
	 * @param biggerVal  bigger value
	 * @param smallerVal smaller value
	 * @return the amount of turns when going backwards
	 */
	private int getBackwardTurns(Integer biggerVal, Integer smallerVal) {
		return biggerVal - smallerVal;
	}

	/**
	 * @param currentCombination the combination to check
	 * @return true if all digits of the combination are the same, false otherwise
	 */
	private boolean checkAllDigitsAreSame(Combination currentCombination) {
		ArrayList<Integer> digits = currentCombination.getDigits();
		return digits.get(0).equals(digits.get(1)) && digits.get(2).equals(digits.get(3))
				&& digits.get(0).equals(digits.get(3));
	}

	/**
	 * Returns the lowest value from a given set of integers
	 * 
	 * @param values the set of values
	 * @return the lowest value
	 */
	private Integer getMinimum(Set<Integer> values) {
		int minTurns = values.iterator().next();
		for (Integer val : values) {
			if (minTurns > val) {
				minTurns = val;
			}
		}
		return minTurns;
	}

}
