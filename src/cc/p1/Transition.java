/**
 * File containing the Transition entity definition. 
 */
package cc.p1;

/**
 * Class which represents a transition of a Pushdown Automaton. Each transition
 * is compound by 5 elements which identifies it.
 * 
 * @author Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
 * @version 1.0
 * @since 7 oct. 2018
 */
public class Transition
{
	/** Origin state of the transition */
	State		originState;
	/** Destination state of the transition */
	State		destinationState;
	/** Input symbol required. */
	Symbol		inputSymbol;
	/** Top stack symbol required */
	Symbol		requiredTopStackSymbol;
	/** Stack symbols to introduce */
	Symbol[]	newTopStackSymbols;

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
			Symbol[] newTopStackSymbols)
	{
		this.originState = originState;
		this.destinationState = destinationState;
		this.inputSymbol = inputSymbol;
		this.requiredTopStackSymbol = topStackSymbol;
		this.newTopStackSymbols = newTopStackSymbols;
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
	public Symbol[] getNewTopStackSymbols()
	{
		return newTopStackSymbols;
	}

}
