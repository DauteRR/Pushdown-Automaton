# Pushdown Automaton
A **pushdown automaton (PDA)** is a type of automaton that employs a stack. Pushdown automata are used in theories about what can be computed by machines. They are more capable than finite-state machines but less capable than Turing machines. Deterministic pushdown automata can recognize all deterministic context-free languages while nondeterministic ones can recognize all context-free languages, with the former often used in parser design.

A **PDA is formally defined as a 7-tuple**:
*TM = (Q, Σ, Γ, δ, s, Z, F)*
* Q: Finite set of states
* Σ: Input alphabet, compound by terminal symbols
* Γ: Stack alphabet, compound by non terminal symbols
* δ: Finite set of transitions between states
* s: s ∈ Q is the initial state
* Z: Z ∈ Γ is the initial stack symbol
* F: F ⊆ Q is the set of accepting states

## Program description
The program creates a PDA from a configuration files (see the examples in Samples folder). Then the programs simulates the behaviour of the PDA with some input string (given in a file or via command line). Lastly, the program says if the language that the PDA represents accepts the input string or no. In addition, the program can be executed in trace mode.
* **The input string must be specified with each symbol separated with a `whitespace`**.
* **In trace mode, the character `>` points the next symbol to be read from the input tape**.
* **The `topmost stack symbol` is at the right side of the stack representation in trace mode**.
## Execution

For execute the program try the command below:
```
    java -jar PDASimulator.jar configFile traceMode [inputFile]
```
Where:
* **configFile** is the file containing the configuration of the PDA (e.g Samples/APf2.txt)
* **traceMode** if you want to enable it type 1, otherwise type 0
* **inputFile** is the file containing the input string (optional)
## Trace mode execution example
```
    java -jar PDASimulator.jar Samples/APf2.txt 1 input.txt

    Input string: 0 1 0 1 0 1 1 0 1 0 1 0

    States              Input                    Stack                    Transitions              
    _________________________________________________________________________________________________
    p                   >010101101010.           [S]                      (p, ., S, q, S) 7

    q                   >010101101010.           [S]                      (q, ., S, r, S) 12

    r                   >010101101010.           [S]                      w ∉ L
    -----------------------------------------------------------------------------------------------

    p                   >010101101010.           [S]                      (p, 0, S, p, 0 S) 1

    p                   0>10101101010.           [S, 0]                   (p, ., 0, q, 0) 8

    q                   0>10101101010.           [S, 0]                   w ∉ L
    -----------------------------------------------------------------------------------------------

    p                   0>10101101010.           [S, 0]                   (p, 1, 0, p, 1 0) 5

    p                   01>0101101010.           [S, 0, 1]                (p, ., 1, q, 1) 9

    q                   01>0101101010.           [S, 0, 1]                w ∉ L
    -----------------------------------------------------------------------------------------------

    p                   01>0101101010.           [S, 0, 1]                (p, 0, 1, p, 0 1) 4

    p                   010>101101010.           [S, 0, 1, 0]             (p, ., 0, q, 0) 8

    q                   010>101101010.           [S, 0, 1, 0]             w ∉ L
    -----------------------------------------------------------------------------------------------

    p                   010>101101010.           [S, 0, 1, 0]             (p, 1, 0, p, 1 0) 5

    p                   0101>01101010.           [S, 0, 1, 0, 1]          (p, ., 1, q, 1) 9

    q                   0101>01101010.           [S, 0, 1, 0, 1]          w ∉ L
    -----------------------------------------------------------------------------------------------

    p                   0101>01101010.           [S, 0, 1, 0, 1]          (p, 0, 1, p, 0 1) 4

    p                   01010>1101010.           [S, 0, 1, 0, 1, 0]       (p, ., 0, q, 0) 8

    q                   01010>1101010.           [S, 0, 1, 0, 1, 0]       w ∉ L
    -----------------------------------------------------------------------------------------------

    p                   01010>1101010.           [S, 0, 1, 0, 1, 0]       (p, 1, 0, p, 1 0) 5

    p                   010101>101010.           [S, 0, 1, 0, 1, 0, 1]    (p, ., 1, q, 1) 9

    q                   010101>101010.           [S, 0, 1, 0, 1, 0, 1]    (q, 1, 1, q, .) 11

    q                   0101011>01010.           [S, 0, 1, 0, 1, 0]       (q, 0, 0, q, .) 10

    q                   01010110>1010.           [S, 0, 1, 0, 1]          (q, 1, 1, q, .) 11

    q                   010101101>010.           [S, 0, 1, 0]             (q, 0, 0, q, .) 10

    q                   0101011010>10.           [S, 0, 1]                (q, 1, 1, q, .) 11

    q                   01010110101>0.           [S, 0]                   (q, 0, 0, q, .) 10

    q                   010101101010>.           [S]                      (q, ., S, r, S) 12

    r                   010101101010>.           [S]                      w ∈ L
```
