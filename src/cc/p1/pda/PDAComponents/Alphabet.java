/**
 * File containing the Alphabet entity definition. 
 */
package cc.p1.pda.PDAComponents;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Class which represents an alphabet of a Pushdown Automaton.
 * It is compound by a set of symbols.
 * 
 * @author Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
 * @version 1.0
 * @since 7 oct. 2018
 */
public class Alphabet implements Iterable<Symbol>
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
				return firstSymbol.getSymbolRepresentation().compareTo(secondSymbol.getSymbolRepresentation());
			}
		};

		symbols = new TreeSet<Symbol>(comparator);
	}
	
	/**
	 * Returns an specific symbol object
	 * @param symbol Searched symbol
	 * @return Symbol
	 */
	public Symbol getSymbol(String requiredSymbol)
	{
		for (Symbol symbol: symbols)
			if (symbol.getSymbolRepresentation().equals(requiredSymbol))
				return symbol;
		throw new IllegalArgumentException("The alphabet doesn't contain the required symbol: " + requiredSymbol);
	}

	/**
	 * Adds newSymbol to the alphabet
	 * 
	 * @param newSymbol
	 */
	public void addSymbol(Symbol newSymbol)
	{
		if (!allowNonTerminalSymbols && newSymbol.getType() == Symbol.SymbolType.NON_TERMINAL)
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
	
	@Override
	public String toString()
	{
		String alphabet = "{ ";
		for (Symbol symbol: symbols)
		{
			alphabet += symbol + ", ";
		}
		return alphabet.substring(0, alphabet.length() - 2) + "}";
	}

	@Override
	public Iterator<Symbol> iterator()
	{
        return symbols.iterator();
	}
}

