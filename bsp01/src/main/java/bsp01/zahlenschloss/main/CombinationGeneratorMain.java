package bsp01.zahlenschloss.main;

import java.util.Scanner;

import bsp01.zahlenschloss.IFs.Combination;
import bsp01.zahlenschloss.IFs.CombinationGenerator;
import bsp01.zahlenschloss.IFs.CombinationProposal;
import bsp01.zahlenschloss.Impls.CombinationGeneratorImpl;
import bsp01.zahlenschloss.Impls.CombinationImpl;

public class CombinationGeneratorMain {
	static Scanner consoleInput = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Starting CombinationGenerator:");
		System.out.println("Input the 4 digits of the current combination separated by commas: ");
		Combination currentCombination = getInputCombinationFromConsole();
		consoleInput.close();

		generateAndOutputCombination(currentCombination);
	}

	private static void generateAndOutputCombination(Combination currentCombination) {
		CombinationGenerator generator = new CombinationGeneratorImpl();
		CombinationProposal combinationProp = generator.generateCombinations(currentCombination);
		System.out.println(
				"The following combination(s) were generated: " + combinationProp.getCombinations().toString());
		System.out.println("The number of turns needed to reach the generated combination(s) is: "
				+ combinationProp.getNumberOfTurns());
	}

	private static CombinationImpl getInputCombinationFromConsole() {
		String[] digits = consoleInput.nextLine().split(",");
		
		if (digits.length != 4) {
			System.out.println("You entered an incorrect number of digits (" + digits.length + "), please try again!");
			return getInputCombinationFromConsole();
		}
		try {
			return new CombinationImpl(getDigit(digits[0]), getDigit(digits[1]), getDigit(digits[2]), getDigit(digits[3]));
		} catch (NumberFormatException e) {
			System.out.println("Only numbers are permitted, please try again!");
			return getInputCombinationFromConsole();
		} catch (IllegalArgumentException e) {
			System.out.println("Only numbers between 0 and 9 are permitted, please try again!");
			return getInputCombinationFromConsole();
		}
	}

	private static int getDigit(String s) {
		return Integer.valueOf(s.trim()).intValue();
	}
}
