/**
 * File containing the Symbol entity definition. 
 */
package cc.p1.PDAComponents;

/**
 * Class which represents a symbol of a Pushdown Automaton alphabet. The symbol
 * could be terminal or non terminal. It's nature conditions the alphabet to
 * which it may belong.
 * 
 * @author Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
 * @version 1.0
 * @since 7 oct. 2018
 */
public class Symbol
{
	/** Enumeration containing the types of symbols */
	static public enum SymbolType
	{
		TERMINAL, NON_TERMINAL
	};

	/** Symbol */
	String	symbol;
	/** Type of the symbol */
	SymbolType	type;

	/**
	 * Constructor
	 * 
	 * @param symbol
	 * @param type
	 */
	public Symbol(String symbol, SymbolType type)
	{
		this.symbol = symbol;
		this.type = type;
	}

	/**
	 * Getter method for symbol attribute.
	 * 
	 * @return symbol
	 */
	public String getSymbolRepresentation()
	{
		return symbol;
	}

	/**
	 * Getter method for type attribute.
	 * 
	 * @return type
	 */
	public SymbolType getType()
	{
		return type;
	}
	
	@Override
	public String toString()
	{
		return symbol;
	}

}
