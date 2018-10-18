/**
 * File containing the PushdownAutomaton entity definition. 
 */
package cc.p1;

import java.util.ArrayList;
import java.util.TreeSet;

import cc.p1.AuxiliaryTools.PDADescription;
import cc.p1.AuxiliaryTools.Pair;
import cc.p1.PDAComponents.Alphabet;
import cc.p1.PDAComponents.InputTape;
import cc.p1.PDAComponents.Stack;
import cc.p1.PDAComponents.State;
import cc.p1.PDAComponents.Symbol;
import cc.p1.PDAComponents.Transition;

/**
 * Class which represents a Pushdown Automaton. A PDA is formally defined as a
 * 7-tuple: TM = (Q, Σ, Γ, δ, s, Z, F)
 * 
 * @author Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
 * @version 1.0
 * @since 10 oct. 2018
 */
public class PushdownAutomaton
{
	/** Q: Finite set of states */
	TreeSet<State>										states;
	/** Σ: Input alphabet, compound by terminal symbols */
	Alphabet											inputAlphabet;
	/** Γ: Stack alphabet, compound by non terminal symbols */
	Alphabet											stackAlphabet;
	/** δ: Finite set of transitions between states */
	TreeSet<Transition>									transitions;
	/** s: s ∈ Q is the initial state */
	State												initialState;
	/** Z: Z ∈ Γ is the initial stack symbol */
	Symbol												initialStackSymbol;
	/** F: F ⊆ Q is the set of accepting states */
	TreeSet<State>										acceptingStates;

	/** Tape which contains the input string to be evaluated */
	InputTape											inputTape;
	/** Contains the unexplored branches of the simulation */
	java.util.Stack<Pair<Transition, PDADescription>>	unexploredBranches;
	/** Stack of the Pushdown Automaton */
	Stack												stack;
	/** Epsilon symbol. */
	public static final Symbol							EPSILON_SYMBOL	= new Symbol(
			".", Symbol.SymbolType.NON_TERMINAL);

	/**
	 * @param states
	 * @param inputAlphabet
	 * @param stackAlphabet
	 * @param transitions
	 * @param initialState
	 * @param initialStackSymbol
	 * @param acceptingStates
	 */
	public PushdownAutomaton(TreeSet<State> states, Alphabet inputAlphabet,
			Alphabet stackAlphabet, TreeSet<Transition> transitions,
			State initialState, Symbol initialStackSymbol,
			TreeSet<State> acceptingStates)
	{
		this.states = states;
		this.inputAlphabet = inputAlphabet;
		this.stackAlphabet = stackAlphabet;
		this.transitions = transitions;
		this.initialState = initialState;
		this.initialStackSymbol = initialStackSymbol;
		this.acceptingStates = acceptingStates;

		this.inputTape = new InputTape();
		this.unexploredBranches = new java.util.Stack<Pair<Transition, PDADescription>>();
		this.stack = new Stack(initialStackSymbol);
	}

	public void startSimulation(Symbol[] inputString)
	{

		System.out.println(inputTape.getInputStringRepresentation() + " ∈ L ?");
		if (simulate(inputString))
			System.out.println("w ∈ L");
		else
			System.out.println("w ∉ L");
	}

	public boolean simulate(Symbol[] inputString)
	{
		// Reset PDA components
		this.inputTape.updateInputString(inputString);
		this.unexploredBranches.clear();
		this.stack.clear();
		this.stack.push(initialStackSymbol);
		
		// The initial unexplored branches are introduced in the unexplored branches stack
		for(Transition transition: getPossibleTransitions(initialState, inputTape.getCurrentSymbol(), initialStackSymbol))
			unexploredBranches.push(new Pair<>(transition, new PDADescription(initialState, new InputTape(inputTape), new Stack(stack))));
		
		// While exists at least one unexplored branch the PDA continues its simulation,
		// if the language accepts the input string, the simulations stops
		while (!unexploredBranches.isEmpty())
		{
			// The local variables which defines the current state of the PDA are updated
			PDADescription pdaDescription = unexploredBranches.peek().getRight();
			Transition currentTransition = unexploredBranches.peek().getLeft();
			unexploredBranches.pop();
			
			System.out.println(pdaDescription.getCurrentState() + "    " + pdaDescription.getInputTape() + "    " + pdaDescription.getStack() + "    " + currentTransition.getTransitionID() + " " + currentTransition);
			
			// Stack update
			if (currentTransition.getNewTopStackSymbols().size() == 1 &&
				currentTransition.getNewTopStackSymbols().get(0).equals(pdaDescription.getStack().peek()))
			{
				pdaDescription.getStack().popAndPushSameSymbol();
			} else 
			{
				pdaDescription.getStack().pop();
				for (int i = currentTransition.getNewTopStackSymbols().size() - 1; i >= 0; --i)
					pdaDescription.getStack().push(currentTransition.getNewTopStackSymbols().get(i));
			}
			
			// If the input string has been consumed and the current state is an accepting state
			if (pdaDescription.getInputTape().hasInputStringBeenConsumed() && 
				acceptingStates.contains(currentTransition.getDestinationState())) 
			{
				// The language accepts the input string
				return true;
			} else
			{
				// Consume input symbol
				if (!currentTransition.getInputSymbol().equals(EPSILON_SYMBOL))
					pdaDescription.getInputTape().consumeSymbol();
				// Apply the transition
				Symbol newInputSymbol = pdaDescription.getInputTape().getCurrentSymbol();
				State newState = currentTransition.getDestinationState();
				Symbol newTopStackSymbol = pdaDescription.getStack().peek();
				
				// Introduce the new unexplored branches in the unexplored branches stack
				for(Transition transition: getPossibleTransitions(newState, newInputSymbol, newTopStackSymbol))
					unexploredBranches.push(new Pair<>(transition, new PDADescription(newState, new InputTape(pdaDescription.getInputTape()), new Stack(pdaDescription.getStack()))));
			}
				
		}
		
		return false;
	}

	/**
	 * Returns the possible transitions given the current state, the current
	 * input tape symbol and the current topmost stack symbol.
	 * 
	 * @param currentState
	 *            Current state
	 * @param inputSymbol
	 *            Current input tape symbol
	 * @param topStackSymbol
	 *            Current topmost stack symbol
	 * @return Possible transitions.
	 */
	public ArrayList<Transition> getPossibleTransitions(State currentState,
			Symbol inputSymbol, Symbol topStackSymbol)
	{
		ArrayList<Transition> possibleTransitions = new ArrayList<Transition>();
		for (Transition transition : transitions)
			if (transition.getOriginState().equals(currentState)
					&& (transition.getInputSymbol().equals(inputSymbol)
							|| (transition.getInputSymbol()
									.equals(EPSILON_SYMBOL)))
					&& transition.getRequiredTopStackSymbol()
							.equals(topStackSymbol))
				possibleTransitions.add(transition);
		return possibleTransitions;
	}

	@Override
	public String toString()
	{
		String PDA = "TM=(Q, Σ, Γ, δ, s, Z, F)\n\n  Q = { ";
		for (State state : states)
			PDA += state + ", ";
		PDA = PDA.substring(0, PDA.length() - 2) + "}\n  Σ = " + inputAlphabet
				+ "\n  Γ = " + stackAlphabet + "\n  s = " + initialState
				+ "\n  Z = " + initialStackSymbol + "\n  F = { ";

		for (State state : acceptingStates)
			PDA += state + ", ";
		PDA = PDA.substring(0, PDA.length() - 2) + "}\n  δ = {\n    ";

		for (Transition transition : transitions)
			PDA += transition + ",\n    ";
		PDA = PDA.substring(0, PDA.length() - 6) + "\n  }";

		return PDA;
	}

	/**
	 * Getter method for unexploredBranches attribute.
	 * 
	 * @return unexploredBranches
	 */
	public java.util.Stack<Pair<Transition, PDADescription>> getUnexploredBranches()
	{
		return unexploredBranches;
	}

	/**
	 * Setter method for unexploredBranches attribute.
	 * 
	 * @param unexploredBranches
	 */
	public void setUnexploredBranches(
			java.util.Stack<Pair<Transition, PDADescription>> unexploredBranches)
	{
		this.unexploredBranches = unexploredBranches;
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

	/**
	 * Setter method for stack attribute.
	 * 
	 * @param stack
	 */
	public void setStack(Stack stack)
	{
		this.stack = stack;
	}

	/**
	 * Getter method for states attribute.
	 * 
	 * @return states
	 */
	public TreeSet<State> getStates()
	{
		return states;
	}

	/**
	 * Getter method for inputAlphabet attribute.
	 * 
	 * @return inputAlphabet
	 */
	public Alphabet getInputAlphabet()
	{
		return inputAlphabet;
	}

	/**
	 * Getter method for stackAlphabet attribute.
	 * 
	 * @return stackAlphabet
	 */
	public Alphabet getStackAlphabet()
	{
		return stackAlphabet;
	}

	/**
	 * Getter method for transitions attribute.
	 * 
	 * @return transitions
	 */
	public TreeSet<Transition> getTransitions()
	{
		return transitions;
	}

	/**
	 * Getter method for initialState attribute.
	 * 
	 * @return initialState
	 */
	public State getInitialState()
	{
		return initialState;
	}

	/**
	 * Getter method for initialStackSymbol attribute.
	 * 
	 * @return initialStackSymbol
	 */
	public Symbol getInitialStackSymbol()
	{
		return initialStackSymbol;
	}

	/**
	 * Getter method for acceptingStates attribute.
	 * 
	 * @return acceptingStates
	 */
	public TreeSet<State> getAcceptingStates()
	{
		return acceptingStates;
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
	 * Getter method for epsilonSymbol attribute.
	 * 
	 * @return epsilonSymbol
	 */
	public static Symbol getEpsilonSymbol()
	{
		return EPSILON_SYMBOL;
	}

}