/**
 * File containing the PushdownAutomatonDescription entity definition. 
 */
package cc.p1.AuxiliaryTools;

import cc.p1.PDAComponents.InputTape;
import cc.p1.PDAComponents.Stack;
import cc.p1.PDAComponents.State;

/**
 * Class which represents the configuration of a Pushdown Automaton at a
 * specific given time.
 * 
 * @author Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
 * @version 1.0
 * @since 10 oct. 2018
 */
public class PDADescription
{
	/** Represents the current state */
	State		currentState;
	/** Input tape */
	InputTape	inputTape;
	/** Stack of the automaton */
	Stack		stack;

	/**
	 * Constructor.
	 * 
	 * @param currentState
	 * @param inputTape
	 * @param stack
	 */
	public PDADescription(State currentState, InputTape inputTape,
			Stack stack)
	{
		super();
		this.currentState = currentState;
		this.inputTape = inputTape;
		this.stack = stack;
	}

	/**
	 * Getter method for currentState attribute.
	 * 
	 * @return currentState
	 */
	public State getCurrentState()
	{
		return currentState;
	}

	/**
	 * Getter method for inputTape attribute.
	 * 
	 * @return inputTape
	 */
	public InputTape getInputTape()
	{
		return inputTape;
	}

	/**
	 * Getter method for stack attribute.
	 * 
	 * @return stack
	 */
	public Stack getStack()
	{
		return stack;
	}

	@Override
	public String toString()
	{
		return "( " + currentState + ", " + inputTape + ", " + stack + ")";
	}

	/**
	 * Setter method for currentState attribute.
	 * @param currentState 
	 */
	public void setCurrentState(State currentState)
	{
		this.currentState = currentState;
	}
}
