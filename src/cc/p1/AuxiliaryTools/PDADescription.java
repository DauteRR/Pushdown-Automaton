/**
 * File containing the PushdownAutomatonDescription entity definition. 
 */
package cc.p1.AuxiliaryTools;

import cc.p1.PDAComponents.Stack;
import cc.p1.PDAComponents.State;

/**
 * Class which represents the configuration of a Pushdown Automaton
 * at a specific given time.
 * 
 * @author Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
 * @version 1.0
 * @since 10 oct. 2018
 */
public class PDADescription
{
	/** Represents the current state */
	State currentState;
	/** Position of the input tape header */
	int inputTapeHeaderPosition;
	/** Stack of the automaton */
	Stack stack;
	
	/**
	 * Constructor.
	 * 
	 * @param currentState
	 * @param inputTapeHeaderPosition
	 * @param stack
	 */
	public PDADescription(State currentState,
			int inputTapeHeaderPosition, Stack stack)
	{
		super();
		this.currentState = currentState;
		this.inputTapeHeaderPosition = inputTapeHeaderPosition;
		this.stack = stack;
	}

	/**
	 * Getter method for currentState attribute.
	 * @return currentState
	 */
	public State getCurrentState()
	{
		return currentState;
	}

	/**
	 * Getter method for inputTapeHeaderPosition attribute.
	 * @return inputTapeHeaderPosition
	 */
	public int getInputTapeHeaderPosition()
	{
		return inputTapeHeaderPosition;
	}

	/**
	 * Getter method for stack attribute.
	 * @return stack
	 */
	public Stack getStack()
	{
		return stack;
	}
}
