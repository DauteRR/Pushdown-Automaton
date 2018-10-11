/**
 * File containing the State entity definition. 
 */
package cc.p1.PDAComponents;

/**
 * Class which represents a state of a Pushdown Automaton. The state can be an
 * accepting state. Each state has an unique identifier.
 * 
 * @author Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
 * @version 1.0
 * @since 7 oct. 2018
 */
public class State
{
	/** State identifier */
	String	stateID;
	/** Establishes if the state is an accepting state */
	boolean	isAnAcceptingState;

	/**
	 * Constructor
	 * 
	 * @param id
	 *            state identifier
	 */
	public State(String id)
	{
		this.stateID = id;
	}

	/**
	 * Getter method for stateID attribute.
	 * 
	 * @return stateID
	 */
	public String getStateID()
	{
		return stateID;
	}

	/**
	 * Getter method for isAnAcceptingState attribute.
	 * 
	 * @return isAnAcceptingState
	 */
	public boolean isAnAcceptingState()
	{
		return isAnAcceptingState;
	}

	/**
	 * Setter method for isAnAcceptingState attribute.
	 * 
	 * @param isAnAcceptingState
	 */
	public void setAnAcceptingState(boolean isAnAcceptingState)
	{
		this.isAnAcceptingState = isAnAcceptingState;
	}
}
