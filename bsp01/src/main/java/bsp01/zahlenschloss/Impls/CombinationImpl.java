package bsp01.zahlenschloss.Impls;

import java.util.ArrayList;

import bsp01.zahlenschloss.IFs.Combination;

/**
 * Lock Combination with 4 digits
 * 
 * @author alina
 *
 */
public class CombinationImpl implements Combination {

	/** first digit of the combination */
	private final int firstDigit;

	/** second digit of the combination */
	private final int secondDigit;

	/** third digit of the combination */
	private final int thirdDigit;

	/** fourth digit of the combination */
	private final int fourthDigit;

	/** the list containing all 4 digits */
	private final ArrayList<Integer> digits;


	/**
	 * Creates a new Combination with the given digits
	 * 
	 * @param firstDigit
	 * @param secondDigit
	 * @param thirdDigit
	 * @param fourthDigit
	 */
	public CombinationImpl(int firstDigit, int secondDigit, int thirdDigit, int fourthDigit) {
		checkInputDigits(firstDigit, secondDigit, thirdDigit, fourthDigit);
		this.firstDigit = firstDigit;
		this.secondDigit = secondDigit;
		this.thirdDigit = thirdDigit;
		this.fourthDigit = fourthDigit;

		digits = new ArrayList<Integer>();
		digits.add(firstDigit);
		digits.add(secondDigit);
		digits.add(thirdDigit);
		digits.add(fourthDigit);

	}

	/**
	 * checks that all 4 digits are between 0 and 9
	 * 
	 * @param firstDigit
	 * @param secondDigit
	 * @param thirdDigit
	 * @param fourthDigit
	 * @throws IllegalArgumentException if a digit is outside the valid range
	 */
	private void checkInputDigits(int firstDigit, int secondDigit, int thirdDigit, int fourthDigit) {
		if (!isValidDigit(firstDigit) || !isValidDigit(secondDigit) || !isValidDigit(thirdDigit)
				|| !isValidDigit(fourthDigit)) {
			throw new IllegalArgumentException("Combination digits must be between 0 and 9.");
		}
	}

	/**
	 * Checks if the given digit is between 0 and 9
	 * 
	 * @param digit the digit to check
	 * @return true if the given digit is within the valid range, false otherwise
	 */
	private boolean isValidDigit(int digit) {
		return digit >= 0 && digit < 10;
	}

	/**
	 * @return a list containing the 4 digits of the combination
	 */
	public ArrayList<Integer> getDigits() {
		return digits;
	}

	@Override
	public String toString() {
		return "[" + firstDigit + " " + secondDigit + " " + thirdDigit + " " + fourthDigit + "]";
	}
}
