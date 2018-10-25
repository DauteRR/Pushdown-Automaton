/**
 * File containing the InputTape entity definition. 
 */
package cc.p1.pda.PDAComponents;

import cc.p1.pda.PushdownAutomaton;

/**
 * Class which represents the input tape of a Pushdown Automata.
 * 
 * @author Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
 * @version 1.0
 * @since 10 oct. 2018
 */
public class InputTape
{
	/** String that must be evaluated by the automaton */
	Symbol[]	inputString;
	/** Integer that specifies the position of the next symbol to be read.
	 *  The value -1 specifies if the input string has been consumed or if
	 *  the input string is empty */
	int			inputTapeHeader	= -1;

	/**
	 * Constructor
	 */
	public InputTape()
	{

	}

	/**
	 * Constructor
	 * 
	 * @param inputString
	 */
	public InputTape(Symbol[] inputString)
	{
		if (inputString.length > 0) 
		{
			this.inputString = inputString;
			this.inputTapeHeader = 0;
		}
	}

	public InputTape(InputTape inputTape)
	{
		this.inputString = new Symbol[inputTape.getInputString().length];
		for (int i = 0; i < inputTape.getInputString().length; ++i)
			this.inputString[i] = inputTape.getInputString()[i];
		this.inputTapeHeader = inputTape.getInputTapeHeaderPosition();
	}

	/**
	 * Returns the position of the input tape header.
	 * 
	 * @return Position of the input tape header.
	 */
	public int getInputTapeHeaderPosition()
	{
		return inputTapeHeader;
	}
	
	/**
	 * Getter method for inputString attribute.
	 * @return inputString
	 */
	public String getInputStringRepresentation()
	{
		String inputStringRepresentation = "w = \"";
		for (Symbol symbol: inputString)
			inputStringRepresentation += symbol;
		return inputStringRepresentation + "\"";
	}

	/**
	 * Returns the symbol pointed by the input tap header
	 * 
	 * @return Symbol
	 */
	public Symbol getCurrentSymbol()
	{
		if (inputTapeHeader == -1)
		{
			return PushdownAutomaton.EPSILON_SYMBOL;
		} else
		{
			return inputString[inputTapeHeader];
		}
	}

	/**
	 * Setter method for inputString attribute
	 * 
	 * @param inputString Array of symbols
	 */
	public void updateInputString(Symbol[] inputString)
	{
		this.inputString = inputString;
		inputTapeHeader = (this.inputString.length > 0) ? 0 : -1;
	}
	
	/**
	 * Checks if the entire input string has been consumed.
	 * 
	 * @return Result of the operation.
	 */
	public boolean hasInputStringBeenConsumed()
	{
		if (inputTapeHeader == -1)
		{
			// We consider the empty string
			return true;
		} else
		{
			return false;
		}
	}

	/**
	 * Represents the movement of the input tape header.
	 */
	public void consumeSymbol()
	{
		if (inputTapeHeader == -1)
			return;
		inputTapeHeader++;
		if  (inputTapeHeader >= inputString.length)
			inputTapeHeader = -1;
	}

	@Override
	public String toString()
	{
		String inputTape = "";
		for (int i = 0; i < inputString.length; ++i)
		{
			if (i == inputTapeHeader)
				inputTape += ">";
			inputTape += inputString[i];
		}
		if (inputTapeHeader == -1)
			inputTape += ">";
		inputTape += PushdownAutomaton.EPSILON_SYMBOL;

		return inputTape;
	}

	/**
	 * Setter method for inputString attribute.
	 * @param inputString 
	 */
	public void setInputString(Symbol[] inputString)
	{
		this.inputString = inputString;
	}

	/**
	 * Getter method for inputString attribute.
	 * @return inputString
	 */
	public Symbol[] getInputString()
	{
		return inputString;
	}
}
