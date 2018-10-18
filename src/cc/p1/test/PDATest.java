/**
 * File containing the PDATest entity definition. 
 */
package cc.p1.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import cc.p1.PushdownAutomaton;
import cc.p1.AuxiliaryTools.PDAParser;
import cc.p1.PDAComponents.Symbol;

/**
 * @author Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
 * @version 1.0
 * @since 18 oct. 2018
 */
class PDATest
{
	/** PDAs for testing */
	static public PushdownAutomaton	pda1;
	static public PushdownAutomaton	pda2;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception
	{
		pda1 = PDAParser.constructPDA("Samples/APf.txt");
		pda2 = PDAParser.constructPDA("Samples/APf2.txt");
	}

	@Test
	void pd1SimulationTest()
	{
		Symbol[] string1 = {pda1.getInputAlphabet().getSymbol("a"), pda1.getInputAlphabet().getSymbol("a"),
							pda1.getInputAlphabet().getSymbol("a"), pda1.getInputAlphabet().getSymbol("b"),
							pda1.getInputAlphabet().getSymbol("b"), pda1.getInputAlphabet().getSymbol("b")};
		Symbol[] string2 = {};
		Symbol[] string3 = {pda1.getInputAlphabet().getSymbol("a"), pda1.getInputAlphabet().getSymbol("a"),
							pda1.getInputAlphabet().getSymbol("b"), pda1.getInputAlphabet().getSymbol("b"),
							pda1.getInputAlphabet().getSymbol("b"), pda1.getInputAlphabet().getSymbol("b")};
		Symbol[] string4 = {pda1.getInputAlphabet().getSymbol("b"), pda1.getInputAlphabet().getSymbol("b"),
							pda1.getInputAlphabet().getSymbol("a"), pda1.getInputAlphabet().getSymbol("a")};
		Symbol[] string5 = {pda1.getInputAlphabet().getSymbol("a"), pda1.getInputAlphabet().getSymbol("a"),
							pda1.getInputAlphabet().getSymbol("b"), pda1.getInputAlphabet().getSymbol("b")};
		Symbol[] string6 = {pda1.getInputAlphabet().getSymbol("a"), pda1.getInputAlphabet().getSymbol("a"),
							pda1.getInputAlphabet().getSymbol("a"), pda1.getInputAlphabet().getSymbol("a")};
		
		assertTrue(pda1.simulate(string1));
		assertFalse(pda1.simulate(string2));
		assertFalse(pda1.simulate(string3));
		assertFalse(pda1.simulate(string4));
		assertTrue(pda1.simulate(string5));
		assertFalse(pda1.simulate(string6));
	}
	
	@Test
	void pd2SimulationTest()
	{
		Symbol[] string1 = {pda2.getInputAlphabet().getSymbol("0"), pda2.getInputAlphabet().getSymbol("1"),
							pda2.getInputAlphabet().getSymbol("0"), pda2.getInputAlphabet().getSymbol("0"),
							pda2.getInputAlphabet().getSymbol("1"), pda2.getInputAlphabet().getSymbol("0")};
		Symbol[] string2 = {};
		Symbol[] string3 = {pda2.getInputAlphabet().getSymbol("0"), pda2.getInputAlphabet().getSymbol("1"),
							pda2.getInputAlphabet().getSymbol("0"), pda2.getInputAlphabet().getSymbol("1"),
							pda2.getInputAlphabet().getSymbol("0"),};
		Symbol[] string4 = {pda2.getInputAlphabet().getSymbol("0"), pda2.getInputAlphabet().getSymbol("1"),
							pda2.getInputAlphabet().getSymbol("1"), pda2.getInputAlphabet().getSymbol("1")};
		Symbol[] string5 = {pda2.getInputAlphabet().getSymbol("0"), pda2.getInputAlphabet().getSymbol("1"),
							pda2.getInputAlphabet().getSymbol("1"), pda2.getInputAlphabet().getSymbol("1"),
							pda2.getInputAlphabet().getSymbol("1")};
		
		assertTrue(pda2.simulate(string1));
		assertTrue(pda2.simulate(string2));
		assertFalse(pda2.simulate(string3));
		assertFalse(pda2.simulate(string4));
		assertFalse(pda2.simulate(string5));
	}

}
