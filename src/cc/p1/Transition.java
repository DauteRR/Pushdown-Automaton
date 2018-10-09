/**
 * File containing the Transition entity definition. 
 */
package cc.p1;

/**
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
	Symbol	inputSymbol;
	/** Top stack symbol required */
	Symbol	topStackSymbol;
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
			Symbol inputSymbol, Symbol topStackSymbol, Symbol[] newTopStackSymbols)
	{
		this.originState = originState;
		this.destinationState = destinationState;
		this.inputSymbol = inputSymbol;
		this.topStackSymbol = topStackSymbol;
		this.newTopStackSymbols = newTopStackSymbols;
	}
	
}
