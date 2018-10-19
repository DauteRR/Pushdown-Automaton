/**
 * File containing the Simulator entity definition.
 */
package cc.p1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import cc.p1.AuxiliaryTools.PDAParser;
import cc.p1.PDAComponents.Symbol;

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
		String inputString = "";
		if (args.length < 2)
		{
			System.err.println("You have to specify the configuration file of the PDA and the flag for trace mode at least:");
			System.err.println("java -jar PDASimulator.jar Samples/Apf2.txt {0|1} [input.txt]");
			return;
		} else if (args.length == 2)
		{
			System.out.print("Enter input string(separe symbols with whitespaces): ");
			
			Scanner scanner = new Scanner(System.in);
		    inputString = scanner.nextLine();
		    scanner.close();
		} else
		{
			BufferedReader reader = new BufferedReader(new FileReader(args[2]));
			inputString = reader.readLine();
			reader.close();
			System.out.println("Input string: " + inputString);
		}
		
		PushdownAutomaton PDA = PDAParser.constructPDA(args[0]);
		
		Symbol[] inputStringSymbols = new Symbol[inputString.split("\\s").length];
		for (int i = 0; i < inputStringSymbols.length; ++i)
			inputStringSymbols[i] = PDA.getInputAlphabet().getSymbol(inputString.split("\\s")[i]);
		
		
		PDA.getInputTape().updateInputString(inputStringSymbols);
		PDA.startSimulation(Integer.parseInt(args[1]) == 1);
	}
}
