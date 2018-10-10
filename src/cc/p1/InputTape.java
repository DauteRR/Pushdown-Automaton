/**
 * File containing the InputTape entity definition. 
 */
package cc.p1;

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
	/** Integer that specifies the position of the next symbol to be read */
	int			inputTapeHeader	= -1;

	/**
	 * Constructor
	 * 
	 * @param inputString
	 *            String that must be evaluated by the automaton
	 */
	public InputTape(Symbol[] inputString)
	{
		this.inputString = inputString;

		if (this.inputString.length > 0)
		{
			inputTapeHeader = 0;
		}
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
	 * Returns the symbol pointed by the input tap header
	 * 
	 * @return Symbol
	 */
	public Symbol getCurrentSymbol()
	{
		if (inputTapeHeader == -1)
		{
			throw new IllegalArgumentException("Empty input string");
		} else if (inputTapeHeader >= inputString.length)
		{
			throw new IllegalArgumentException(
					"Input string has been consumed");
		} else
		{
			return inputString[inputTapeHeader];
		}
	}

	/**
	 * Setter method for inputString attribute
	 * 
	 * @param inputString
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
			throw new IllegalArgumentException("Empty input string");
		} else if (inputTapeHeader >= inputString.length)
		{
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
		{
			throw new IllegalArgumentException("Empty input string");
		} else if (inputTapeHeader >= inputString.length)
		{
			throw new IllegalArgumentException(
					"Input string has been consumed");
		}
		inputTapeHeader++;
	}
}
