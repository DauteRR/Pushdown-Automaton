/**
 * File containing the PDAParser entity definition. 
 */
package cc.p1.AuxiliaryTools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import cc.p1.PushdownAutomaton;

/**
 * Class which parses a configuration file and creates a PDA object.
 * 
 * @author Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
 * @version 1.0
 * @since 10 oct. 2018
 */
public class PDAParser
{
	/**
	 * Constructs a PDA from a configuration file.
	 * 
	 * @param inputFile
	 * @throws IOException
	 */
	public static PushdownAutomaton constructPDA(String inputFile) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		String line = "";
		while (reader.ready())
		{
			line = reader.readLine();
			String[] tokens = line.split("\\s");
			for (String token: tokens)
				System.out.println(token);
		}
		
		//TODO Parse the configuration file to a PDA
		
		reader.close();
		
		return null;
	}
}
