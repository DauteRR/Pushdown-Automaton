/**
 * File containing the PushdownAutomaton entity definition. 
 */
package cc.p1;

import java.util.TreeSet;

import cc.p1.AuxiliaryTools.PDADescription;
import cc.p1.AuxiliaryTools.Pair;
import cc.p1.PDAComponents.Alphabet;
import cc.p1.PDAComponents.InputTape;
import cc.p1.PDAComponents.State;
import cc.p1.PDAComponents.Symbol;
import cc.p1.PDAComponents.Transition;

/**
 * Class which represents a Pushdown Automaton. A PDA is formally defined as a
 * 7-tuple: M = (Q, Σ, Γ, δ, q0, Z, F)
 * 
 * @author Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
 * @version 1.0
 * @since 10 oct. 2018
 */
public class PushdownAutomaton
{
	/** Q: Finite set of states */
	TreeSet<State>		states;
	/** Σ: Input alphabet, compound by terminal symbols */
	Alphabet			inputAlphabet;
	/** Γ: Stack alphabet, compound by non terminal symbols */
	Alphabet			stackAlphabet;
	/** δ: Finite set of transitions between states */
	TreeSet<Transition>	transitions;
	/** q0: q0 ∈ Q is the initial state */
	State				initialState;
	/** Z: Z ∈ Γ is the initial stack symbol */
	Symbol				initialStackSymbol;
	/** F: F ⊆ Q is the set of accepting states */
	TreeSet<State>		acceptingStates;

	/** Tape which contains the input string to be evaluated */
	InputTape			inputTape;
	/** Contains the unexplored branches of the simulation */
	java.util.Stack<Pair<Transition, PDADescription>>	unexploredBranches;
}