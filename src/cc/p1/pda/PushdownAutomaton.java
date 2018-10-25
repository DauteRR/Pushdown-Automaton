/**
 * File containing the PushdownAutomaton entity definition. 
 */
package cc.p1.pda;

import java.util.ArrayList;
import java.util.TreeSet;

import cc.p1.pda.AuxiliaryTools.PDADescription;
import cc.p1.pda.AuxiliaryTools.Pair;
import cc.p1.pda.PDAComponents.Alphabet;
import cc.p1.pda.PDAComponents.InputTape;
import cc.p1.pda.PDAComponents.Stack;
import cc.p1.pda.PDAComponents.State;
import cc.p1.pda.PDAComponents.Symbol;
import cc.p1.pda.PDAComponents.Transition;

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
	
	/** Needed for the trace mode */
	final int STATES_COLUMN_MAX_SPACES = 20;
	/** Needed for the trace mode */
	final int INPUT_COLUMN_MAX_SPACES = 25;
	/** Needed for the trace mode */
	final int STACK_COLUMN_MAX_SPACES = 25;
	/** Needed for the trace mode */
	final int TRANSITION_COLUMN_MAX_SPACES = 25;
	/** Needed for the trace mode */
	ArrayList<Transition> possibleTransitions = new ArrayList<>();

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

	/**
	 * Interface for the simulate method
	 * 
	 * @param traceMode Specifies if the program must show the simulation steps
	 */
	public void startSimulation(boolean traceMode)
	{
		
		if (!traceMode)
			System.out.println("\n" + inputTape.getInputStringRepresentation() + " ∈ L ?\n");
		
		if (traceMode)
		{
			String header = "\nStates";
			for (int i = 0; i < STATES_COLUMN_MAX_SPACES - 6; ++i)
				header += " ";
			header += "Input";
			for (int i = 0; i < INPUT_COLUMN_MAX_SPACES - 5; ++i)
				header += " ";
			header += "Stack";
			for (int i = 0; i < STACK_COLUMN_MAX_SPACES - 5; ++i)
				header += " ";
			header += "Transitions";
			for (int i = 0; i < TRANSITION_COLUMN_MAX_SPACES - 11; ++i)
				header += " ";
			
			header += "\n";
			int size = header.length();
			for (int i = 0; i < size; ++i)
				header += "_";
			
			System.out.println(header);
		}
		
		boolean accepted = simulate(inputTape.getInputString(), traceMode);
		
		if (!traceMode && accepted)
			System.out.println("w ∈ L");
		else if (!traceMode)
			System.out.println("w ∉ L");
	}
	
	/**
	 * Method needed by the trace mode simulation mode.
	 * 
	 * @param pdaDescription
	 * @param currentTransition
	 */
	private void traceMode(String state, String input, String stack, String transition)
	{
		String line = state;
		for (int i = 0; i < STATES_COLUMN_MAX_SPACES - state.length(); ++i)
			line += " ";
		
		line += input;
		for (int i = 0; i < INPUT_COLUMN_MAX_SPACES - input.length(); ++i)
			line += " ";
		line += stack;
		
		for (int i = 0; i < STACK_COLUMN_MAX_SPACES - stack.length(); ++i)
			line += " ";
		line += transition + "\n";
		
		System.out.println(line);
	}

	/**
	 * Simulates the behavior of the PDA with the input string given.
	 * 
	 * @param inputString
	 * @param traceMode Specifies if the program must show the simulation steps
	 * @return True if the language accepts the string, false otherwise.
	 */
	public boolean simulate(Symbol[] inputString, boolean traceMode)
	{
		// Reset PDA components
		this.inputTape.updateInputString(inputString);
		this.unexploredBranches.clear();
		this.stack.clear();
		this.stack.push(initialStackSymbol);
		
		possibleTransitions = getPossibleTransitions(initialState, inputTape.getCurrentSymbol(), initialStackSymbol);
		
		// The initial unexplored branches are introduced in the unexplored branches stack
		for(Transition transition: possibleTransitions)
			unexploredBranches.push(new Pair<>(transition, new PDADescription(initialState, new InputTape(inputTape), new Stack(stack))));
		
		// While exists at least one unexplored branch the PDA continues its simulation,
		// if the language accepts the input string, the simulations stops
		while (!unexploredBranches.isEmpty())
		{
			
			// The local variables which defines the current state of the PDA are updated
			PDADescription pdaDescription = unexploredBranches.peek().getRight();
			Transition currentTransition = unexploredBranches.peek().getLeft();
			unexploredBranches.pop();
			
			if (traceMode)
			{
				String transitions = "";
				for (Transition transition: possibleTransitions)
				{
					transitions += transition.toString() + " ";
				}
				if (possibleTransitions.size() == 0)
				{
					transitions = currentTransition.toString() + " ";
				}
				traceMode(currentTransition.getOriginState() + "", pdaDescription.getInputTape() + "", pdaDescription.getStack() + "", transitions + "*");
			}
				
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
				if (traceMode)
				{
					traceMode(currentTransition.getDestinationState() + "", pdaDescription.getInputTape() + "", pdaDescription.getStack() + "", "w ∈ L");
				}
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
				possibleTransitions = getPossibleTransitions(newState, newInputSymbol, newTopStackSymbol);
				
				// Introduce the new unexplored branches in the unexplored branches stack
				for(Transition transition: possibleTransitions)
					unexploredBranches.push(new Pair<>(transition, new PDADescription(newState, new InputTape(pdaDescription.getInputTape()), new Stack(pdaDescription.getStack()))));
				
				if (traceMode && getPossibleTransitions(newState, newInputSymbol, newTopStackSymbol).size() == 0)
				{
					String separator = "\n";
					for (int i = 0; i < STATES_COLUMN_MAX_SPACES + 
							STACK_COLUMN_MAX_SPACES + INPUT_COLUMN_MAX_SPACES + 
							TRANSITION_COLUMN_MAX_SPACES; ++i)
						separator += "-";
					
					traceMode(currentTransition.getDestinationState() + "", pdaDescription.getInputTape() + "", pdaDescription.getStack() + "", "w ∉ L" + separator);
				}
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