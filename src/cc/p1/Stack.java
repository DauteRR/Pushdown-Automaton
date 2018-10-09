/**
 * File containing the Stack entity definition. 
 */
package cc.p1;

/**
 * @author Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
 * @version 1.0
 * @since 7 oct. 2018
 */
public class Stack extends java.util.Stack<Symbol>
{
	/** Default serial version */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 */
	public Stack()
	{
		super();
	}

	/**
	 * Simulates the required feature of popping an element in every single step.
	 * When the symbol which must be removed it's the same than the symbol to introduce,
	 * the operation is trivial. This method represents this trivial operation.
	 */
	public void popAndPushSameSymbol()
	{
		//Do nothing
	}
}
