/**
 * File containing the Alphabet entity definition. 
 */
package cc.p1;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * Class which represents an alphabet of a Pushdown Automaton.
 * It is compound by a set of symbols.
 * 
 * @author Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
 * @version 1.0
 * @since 7 oct. 2018
 */
public class Alphabet
{
	/** Set of symbols which compounds the alphabet */
	TreeSet<Symbol> symbols;
	/** Establishes if the alphabet can contain non terminal symbols */
	boolean allowNonTerminalSymbols;

	/**
	 * Constructor
	 * 
	 * @param allowNonTerminalSymbols
	 */
	public Alphabet(boolean allowNonTerminalSymbols)
	{
		this.allowNonTerminalSymbols = allowNonTerminalSymbols;
		Comparator<Symbol> comparator = new Comparator<Symbol>()
		{
			@Override
			public int compare(Symbol firstSymbol, Symbol secondSymbol)
			{
				return firstSymbol.getSymbol().compareTo(secondSymbol.getSymbol());
			}
		};

		symbols = new TreeSet<Symbol>(comparator);
	}

	/**
	 * Adds newSymbol to the alphabet
	 * 
	 * @param newSymbol
	 */
	public void addSymbol(Symbol newSymbol)
	{
		if (!allowNonTerminalSymbols && newSymbol.type == Symbol.SymbolType.NON_TERMINAL)
		{
			throw new IllegalArgumentException(
					"Trying to add a non terminal symbol to an alphabet which doesn't admit them");
		}
		
		symbols.add(newSymbol);
	}

	/**
	 * Removes oldSymbol from the alphabet
	 * 
	 * @param oldSymbol
	 */
	public void removeSymbol(Symbol oldSymbol)
	{
		symbols.remove(oldSymbol);
	}

	/**
	 * Checks if the alphabet contains symbol or not
	 * 
	 * @param symbol
	 * @return Result
	 */
	public boolean contains(Symbol symbol)
	{
		return symbols.contains(symbol);
	}
}
