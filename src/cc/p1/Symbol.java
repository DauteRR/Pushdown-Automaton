/**
 * File containing the Symbol entity definition. 
 */
package cc.p1;

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
	Character	symbol;
	/** Type of the symbol */
	SymbolType	type;

	/**
	 * Constructor
	 * 
	 * @param symbol
	 * @param type
	 */
	Symbol(Character symbol, SymbolType type)
	{
		this.symbol = symbol;
		this.type = type;
	}

	/**
	 * Getter method for symbol attribute.
	 * 
	 * @return symbol
	 */
	public Character getSymbol()
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

}
