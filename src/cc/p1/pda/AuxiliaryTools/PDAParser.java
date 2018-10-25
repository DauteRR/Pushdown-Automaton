/**
 * File containing the PDAParser entity definition. 
 */
package cc.p1.pda.AuxiliaryTools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

import cc.p1.pda.PushdownAutomaton;
import cc.p1.pda.PDAComponents.Alphabet;
import cc.p1.pda.PDAComponents.State;
import cc.p1.pda.PDAComponents.Symbol;
import cc.p1.pda.PDAComponents.Transition;

/**
 * Class which parses a configuration file and creates a PDA object.
 * 
 * @author Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
 * @version 1.0
 * @since 10 oct. 2018
 */
public class PDAParser
{
	/**
	 * Constructs a PDA from a configuration file.
	 * 
	 * @param inputFile
	 * @throws IOException
	 */
	public static PushdownAutomaton constructPDA(String inputFile) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		String line = "";
		ArrayList<String> statesIdentifiers = new ArrayList<String>();
		
		//Skip comments and empty lines
		//States
		while (reader.ready())
		{
			line = reader.readLine();
			if (line.length() == 0 || line.charAt(0) == '#')
				continue;
			else
			{
				String[] tokens = line.split("\\s");
				for(String token: tokens)
				{
					if (token.charAt(0) == '#')
						break;
					statesIdentifiers.add(token);
				}
				break;
			}
		}
		
		// Input alphabet
		line = reader.readLine();
		String[] inputAlphabetSymbolsTokens = line.split("\\s");
		ArrayList<String> inputAlphabetSymbols = new ArrayList<String>();
		for (String symbol: inputAlphabetSymbolsTokens)
		{
			if (symbol.charAt(0) == '#')
				break;
			else
				inputAlphabetSymbols.add(symbol);
		}
		
		// Stack alphabet
		line = reader.readLine();
		String[] stackAlphabetSymbolsTokens = line.split("\\s");
		ArrayList<String> stackAlphabetSymbols = new ArrayList<String>();
		for (String symbol: stackAlphabetSymbolsTokens)
		{
			if (symbol.charAt(0) == '#')
				break;
			else
				stackAlphabetSymbols.add(symbol);
		}
		
		// Initial state
		line = reader.readLine();
		String[] initialStateLineTokens = line.split("\\s");
		String initialStateIdentifier = initialStateLineTokens[0];
		
		if (!statesIdentifiers.contains(initialStateIdentifier))
		{
			reader.close();
			throw new IllegalArgumentException("The initial state must be included in Q");
		}
		
		// Initial stack symbol
		line = reader.readLine();
		String[] initialStackSymbolLineTokens = line.split("\\s");
		String initialStackSymbolRepresentation = initialStackSymbolLineTokens[0];
		
		if (!stackAlphabetSymbols.contains(initialStackSymbolRepresentation))
		{
			reader.close();
			throw new IllegalArgumentException("The initial stack symbol must be included in Γ");
		}
		
		// Accepting states
		line = reader.readLine();
		String[] acceptingStatesTokens = line.split("\\s");
		ArrayList<String> acceptingStatesIdentifiers = new ArrayList<String>();
		for (String acceptingStateIdentifier: acceptingStatesTokens)
		{
			if (acceptingStateIdentifier.charAt(0) == '#')
				break;
			else
				acceptingStatesIdentifiers.add(acceptingStateIdentifier);
		}
		
		if (!statesIdentifiers.containsAll(acceptingStatesIdentifiers))
		{
			reader.close();
			throw new IllegalArgumentException("Some state of F is not included in Q");
		}
		
		// Creation of some of the components of the PDA
		TreeSet<State> states = new TreeSet<State>();
		for (String state: statesIdentifiers)
			states.add(new State(state));
		
		Alphabet inputAlphabet = new Alphabet(false);
		for (String symbol: inputAlphabetSymbols)
			inputAlphabet.addSymbol(new Symbol(symbol, Symbol.SymbolType.TERMINAL));
		
		Alphabet stackAlphabet = new Alphabet(true);
		for (String symbol: stackAlphabetSymbols)
			stackAlphabet.addSymbol(new Symbol(symbol, Symbol.SymbolType.NON_TERMINAL));
		
		State initialState = null;
		for (State state: states)
			if (state.getStateID().equals(initialStateIdentifier))
			{
				initialState = state;
				break;
			}
		
		Symbol initialStackSymbol = null;
		initialStackSymbol = stackAlphabet.getSymbol(initialStackSymbolRepresentation);
			
		TreeSet<State> acceptingStates = new TreeSet<State>();
		for (String stateID: acceptingStatesIdentifiers)
			for (State state: states)
				if (state.getStateID().equals(stateID))
				{
					acceptingStates.add(state);
					break;
				}
		
		// Transitions
		TreeSet<Transition>	transitions = new TreeSet<Transition>();
		Transition auxiliarTransition = null;
		String originStateID = "", inputSymbolRepresentation = "", requiredTopStackSymbolRepresentation = "", destinationStateID = "";
		ArrayList<Symbol> newTopStackSymbols = null;
		
		State transitionOriginState = null, transitionDestinationState = null;
		
		while (reader.ready())
		{
			line = reader.readLine();
			String[] transitionTokens = line.split("\\s");
			
			if (transitionTokens.length < 5)
			{
				reader.close();
				throw new IllegalArgumentException("Strange transition: " + line);
			}
			
			originStateID = transitionTokens[0];
			inputSymbolRepresentation = transitionTokens[1];
			Symbol inputSymbol = (inputSymbolRepresentation.equals(PushdownAutomaton.EPSILON_SYMBOL.getSymbolRepresentation())) ?
					PushdownAutomaton.EPSILON_SYMBOL :
					inputAlphabet.getSymbol(inputSymbolRepresentation);
			requiredTopStackSymbolRepresentation = transitionTokens[2];
			destinationStateID = transitionTokens[3];
			
			newTopStackSymbols = new ArrayList<Symbol>();
			for (int i = 4; i < transitionTokens.length; ++i)
			{
				if (transitionTokens[i].charAt(0) == '#')
					break;
				else if (!transitionTokens[i].equals(PushdownAutomaton.EPSILON_SYMBOL.getSymbolRepresentation()))
					newTopStackSymbols.add(stackAlphabet.getSymbol(transitionTokens[i]));
				else
					newTopStackSymbols.add(PushdownAutomaton.EPSILON_SYMBOL);
			}
			
			for (State state: states)
			{
				if (state.getStateID().equals(originStateID))
					transitionOriginState = state;
				if (state.getStateID().equals(destinationStateID))
					transitionDestinationState = state;
			}
			
			auxiliarTransition = new Transition(transitionOriginState, transitionDestinationState, inputSymbol, 
					stackAlphabet.getSymbol(requiredTopStackSymbolRepresentation), newTopStackSymbols, transitions.size() + 1);
			
			transitions.add(auxiliarTransition);
		}
		reader.close();
		
		return new PushdownAutomaton(states, inputAlphabet, stackAlphabet, transitions, initialState, initialStackSymbol, acceptingStates);
	}
}
