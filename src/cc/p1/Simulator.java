/**
 * File containing the Simulator entity definition.
 */
package cc.p1;

import java.io.IOException;

import cc.p1.AuxiliaryTools.PDAParser;

/**
 * Class which constructs and starts the simulation of the PDA.
 * It contains a main method to start the program aswell.
 *
 * @author Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
 * @version 1.0
 * @since 10 oct. 2018
 */
public class Simulator
{
	/**
	 * Main method
	 *
	 * @param args Arguments given to the program
	 */
	public static void main(String [ ] args) throws IOException
	{
	      PushdownAutomaton PDA = PDAParser.constructPDA("Samples/APf2.txt");
	}
}
