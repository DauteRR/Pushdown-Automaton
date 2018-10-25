/**
 * File containing the Transition entity definition. 
 */
package cc.p1.pda.PDAComponents;

import java.util.ArrayList;

import cc.p1.pda.PushdownAutomaton;

/**
 * Class which represents a transition of a Pushdown Automaton. Each transition
 * is compound by 5 elements which identifies it.
 * 
 * @author Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
 * @version 1.0
 * @since 7 oct. 2018
 */
public class Transition implements Comparable<Transition>
{
	/** Origin state of the transition */
	State				originState;
	/** Destination state of the transition */
	State				destinationState;
	/** Input symbol required. */
	Symbol				inputSymbol;
	/** Top stack symbol required */
	Symbol				requiredTopStackSymbol;
	/** Stack symbols to introduce */
	ArrayList<Symbol>	newTopStackSymbols;
	/** Identifies each transition */
	int					transitionID;

	/**
	 * Constructor
	 * 
	 * @param originState
	 * @param destinationState
	 * @param inputSymbol
	 * @param topStackSymbol
	 * @param newTopStackSymbols
	 */
	public Transition(State originState, State destinationState,
			Symbol inputSymbol, Symbol topStackSymbol,
			ArrayList<Symbol> newTopStackSymbols, int transitionID)
	{

		if (inputSymbol.getType() != Symbol.SymbolType.TERMINAL
				&& !inputSymbol.equals(PushdownAutomaton.EPSILON_SYMBOL))
		{
			throw new IllegalArgumentException(
					"The input symbol must be terminal");
		} else if (topStackSymbol.getType() != Symbol.SymbolType.NON_TERMINAL)
		{
			throw new IllegalArgumentException(
					"The required top stack symbol must be non terminal");
		}

		for (Symbol symbol : newTopStackSymbols)
		{
			if (symbol.getType() != Symbol.SymbolType.NON_TERMINAL)
			{
				throw new IllegalArgumentException(
						"The new top stack symbols must be non terminal");
			}
		}

		this.originState = originState;
		this.destinationState = destinationState;
		this.inputSymbol = inputSymbol;
		this.requiredTopStackSymbol = topStackSymbol;
		this.newTopStackSymbols = newTopStackSymbols;
		this.transitionID = transitionID;
	}

	/**
	 * Getter method for transitionID attribute.
	 * 
	 * @return transitionID
	 */
	public int getTransitionID()
	{
		return transitionID;
	}

	/**
	 * Getter method for originState attribute.
	 * 
	 * @return originState
	 */
	public State getOriginState()
	{
		return originState;
	}

	/**
	 * Getter method for destinationState attribute.
	 * 
	 * @return destinationState
	 */
	public State getDestinationState()
	{
		return destinationState;
	}

	/**
	 * Getter method for inputSymbol attribute.
	 * 
	 * @return inputSymbol
	 */
	public Symbol getInputSymbol()
	{
		return inputSymbol;
	}

	/**
	 * Getter method for requiredTopStackSymbol attribute.
	 * 
	 * @return requiredTopStackSymbol
	 */
	public Symbol getRequiredTopStackSymbol()
	{
		return requiredTopStackSymbol;
	}

	/**
	 * Getter method for newTopStackSymbols attribute.
	 * 
	 * @return newTopStackSymbols
	 */
	public ArrayList<Symbol> getNewTopStackSymbols()
	{
		return newTopStackSymbols;
	}

	@Override
	public int compareTo(Transition otherTransition)
	{
		Integer firstID = Integer.valueOf(transitionID);
		Integer secondID = Integer.valueOf(otherTransition.getTransitionID());

		return firstID.compareTo(secondID);
	}
	
	@Override
	public String toString()
	{
		String transition = "(" + originState + ", " + inputSymbol + ", " + requiredTopStackSymbol + ", " + destinationState + ", ";
		for(Symbol symbol: newTopStackSymbols)
		{
			transition += symbol + " ";
		}
		return transition.substring(0, transition.length() - 1) + ")";
	}

}
