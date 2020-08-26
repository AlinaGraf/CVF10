/**
 * 
 */
package bsp01.zahlenschloss.IFs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import bsp01.zahlenschloss.Impls.CombinationGeneratorImpl;
import bsp01.zahlenschloss.Impls.CombinationImpl;
import bsp01.zahlenschloss.Impls.CombinationProposalImpl;

/**
 * Test for class {@link CombinationProposalImpl}
 * 
 * @author alina
 *
 */
public class CombinationGeneratorTest {

	/** The object under test */
	CombinationGenerator generator = new CombinationGeneratorImpl();

	CombinationProposal result;

	CombinationImpl currentCombination;

	private boolean exceptionOccurred = false;

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
		exceptionOccurred = false;
		currentCombination = null;
		result = null;
	}

	@Test
	public void testCombination_1111() {
		givenCurrentCombination(1, 1, 1, 1);
		whenTheCombinationsAreGenerated();
		thenTheResultShouldBe(new int[][] { { 1, 1, 1, 1 } }, 0);
	}

	@Test
	public void testCombination_1199() {
		givenCurrentCombination(1, 1, 9, 9);
		whenTheCombinationsAreGenerated();
		thenTheResultShouldBe(new int[][] { { 0, 0, 0, 0 }, { 1, 1, 1, 1 }, { 9, 9, 9, 9 } }, 4);

	}

	@Test
	public void testCombination_1234() {
		givenCurrentCombination(1, 2, 3, 4);
		whenTheCombinationsAreGenerated();
		thenTheResultShouldBe(new int[][] { { 2, 2, 2, 2 }, { 3, 3, 3, 3 } }, 4);
	}

	@Test
	public void testCombination_0001() {
		givenCurrentCombination(0, 0, 0, 1);
		whenTheCombinationsAreGenerated();
		thenTheResultShouldBe(new int[][] { { 0, 0, 0, 0 } }, 1);
	}

	@Test
	public void testCombination_12314() {
		givenCurrentCombination(1, 2, 3, 14);
		thenAnErrorShouldOccur();
	}

	/**
	 * sets the current combination to the given digits d1-d4
	 * 
	 * @param d1
	 * @param d2
	 * @param d3
	 * @param d4
	 */
	private void givenCurrentCombination(int d1, int d2, int d3, int d4) {
		try {
			currentCombination = new CombinationImpl(d1, d2, d3, d4);
		} catch (IllegalArgumentException e) {
			exceptionOccurred = true;
		}

	}

	/**
	 * generates the combinations
	 */
	private void whenTheCombinationsAreGenerated() {
		result = generator.generateCombinations(currentCombination);
	}

	/**
	 * @param expCombs
	 * @param expectedTurns
	 */
	private void thenTheResultShouldBe(int[][] expCombs, int expectedTurns) {
		ArrayList<Combination> expectedCombinations = new ArrayList<Combination>();
		for (int[] is : expCombs) {
			expectedCombinations.add(new CombinationImpl(is[0], is[1], is[2], is[3]));
		}

		assertEquals(expectedTurns, result.getNumberOfTurns());
		assertEquals(expectedCombinations.size(), result.getCombinations().size());
		assertEquals(expectedCombinations.toString(), result.getCombinations().toString());

	}

	private void thenAnErrorShouldOccur() {
		assertTrue(exceptionOccurred);
	}
}
