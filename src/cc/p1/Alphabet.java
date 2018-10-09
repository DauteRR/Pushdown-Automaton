/**
 * File containing the Alphabet entity definition. 
 */
package cc.p1;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
 * @version 1.0
 * @since 7 oct. 2018
 */
public class Alphabet
{
	/** Set of symbols which compounds the alphabet */
	TreeSet<Symbol> symbols;

	/**
	 * Constructor
	 */
	public Alphabet()
	{
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
