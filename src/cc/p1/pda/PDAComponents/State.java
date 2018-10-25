/**
 * File containing the State entity definition. 
 */
package cc.p1.pda.PDAComponents;

/**
 * Class which represents a state of a Pushdown Automaton. The state can be an
 * accepting state. Each state has an unique identifier.
 * 
 * @author Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
 * @version 1.0
 * @since 7 oct. 2018
 */
public class State implements Comparable<State>
{
	/** State identifier */
	String	stateID;

	/**
	 * Constructor
	 * 
	 * @param id
	 *            state identifier
	 * 
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

	@Override
	public int compareTo(State otherState)
	{
		return stateID.compareTo(otherState.stateID);
	}
	
	@Override
	public String toString()
	{
		return stateID;
	}
}
