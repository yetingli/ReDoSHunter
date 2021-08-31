/*
 * dk.brics.automaton
 * 
 * Copyright (c) 2001-2017 Anders Moeller
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package dk.brics.automaton;

import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Finite-state automaton with regular expression operations.
 * <p>
 * Class invariants:
 * <ul>
 * <li> An automaton is either represented explicitly (with {@link State} and {@link Transition} objects)
 *      or with a singleton string (see {@link #getSingleton()} and {@link #expandSingleton()}) in case the automaton is known to accept exactly one string.
 *      (Implicitly, all states and transitions of an automaton are reachable from its initial state.)
 * <li> Automata are always reduced (see {@link #reduce()}) 
 *      and have no transitions to dead states (see {@link #removeDeadTransitions()}).
 * <li> If an automaton is nondeterministic, then {@link #isDeterministic()} returns false (but
 *      the converse is not required).
 * <li> Automata provided as input to operations are generally assumed to be disjoint.
 * </ul>
 * <p>
 * If the states or transitions are manipulated manually, the {@link #restoreInvariant()}
 * and {@link #setDeterministic(boolean)} methods should be used afterwards to restore 
 * representation invariants that are assumed by the built-in automata operations.
 * 
 * @author Anders M&oslash;ller &lt;<a href="mailto:amoeller@cs.au.dk">amoeller@cs.au.dk</a>&gt;
 */
public class Automaton implements Serializable, Cloneable {
	
	static final long serialVersionUID = 10001;
	
	/**
	 * Minimize using Huffman's O(n<sup>2</sup>) algorithm. 
	 * This is the standard text-book algorithm.
	 * @see #setMinimization(int)
	 */
	public static final int MINIMIZE_HUFFMAN = 0;
	
	/**
	 * Minimize using Brzozowski's O(2<sup>n</sup>) algorithm. 
	 * This algorithm uses the reverse-determinize-reverse-determinize trick, which has a bad
	 * worst-case behavior but often works very well in practice 
	 * (even better than Hopcroft's!).
	 * @see #setMinimization(int)
	 */
	public static final int MINIMIZE_BRZOZOWSKI = 1;
	
	/**
	 * Minimize using Hopcroft's O(n log n) algorithm.
	 * This is regarded as one of the most generally efficient algorithms that exist.
	 * @see #setMinimization(int)
	 */
	public static final int MINIMIZE_HOPCROFT = 2;

	/**
	 * Minimize using Valmari's O(n + m log m) algorithm.
	 * @see #setMinimization(int)
	 */
	public static final int MINIMIZE_VALMARI = 3;
	
	/** Selects minimization algorithm (default: <code>MINIMIZE_HOPCROFT</code>). */
	static int minimization = MINIMIZE_HOPCROFT;
	
	/** Initial state of this automaton. */
	State initial;
	
	/** If true, then this automaton is definitely deterministic 
	 (i.e., there are no choices for any run, but a run may crash). */
	boolean deterministic;
	
	/** Extra data associated with this automaton. */
	transient Object info;
	
	/** Hash code. Recomputed by {@link #minimize()}. */
	int hash_code;
	
	/** Singleton string. Null if not applicable. */
	String singleton;
	
	/** Minimize always flag. */
	static boolean minimize_always = false;
	
	/** Selects whether operations may modify the input automata (default: <code>false</code>). */
	static boolean allow_mutation = false;
	
	/** Caches the <code>isDebug</code> state. */
	static Boolean is_debug = null;
	
	/** 
	 * Constructs a new automaton that accepts the empty language.
	 * Using this constructor, automata can be constructed manually from
	 * {@link State} and {@link Transition} objects.
	 * @see #setInitialState(State)
	 * @see State
	 * @see Transition
	 */
	public Automaton() throws InterruptedException {
		initial = new State();
		deterministic = true;
		singleton = null;
	}
	
	boolean isDebug() {
		if (is_debug == null)
			is_debug = System.getProperty("dk.brics.automaton.debug") != null;
		return is_debug;
	}
	
	/** 
	 * Selects minimization algorithm (default: <code>MINIMIZE_HOPCROFT</code>). 
	 * @param algorithm minimization algorithm
	 */
	static public void setMinimization(int algorithm) {
		minimization = algorithm;
	}
	
	/**
	 * Sets or resets minimize always flag.
	 * If this flag is set, then {@link #minimize()} will automatically
	 * be invoked after all operations that otherwise may produce non-minimal automata.
	 * By default, the flag is not set.
	 * @param flag if true, the flag is set
	 */
	static public void setMinimizeAlways(boolean flag) {
		minimize_always = flag;
	}
	
	/**
	 * Sets or resets allow mutate flag.
	 * If this flag is set, then all automata operations may modify automata given as input;
	 * otherwise, operations will always leave input automata languages unmodified. 
	 * By default, the flag is not set.
	 * @param flag if true, the flag is set
	 * @return previous value of the flag
	 */
	static public boolean setAllowMutate(boolean flag) {
		boolean b = allow_mutation;
		allow_mutation = flag;
		return b;
	}
	
	/**
	 * Returns the state of the allow mutate flag.
	 * If this flag is set, then all automata operations may modify automata given as input;
	 * otherwise, operations will always leave input automata languages unmodified. 
	 * By default, the flag is not set.
	 * @return current value of the flag
	 */
	static boolean getAllowMutate() {
		return allow_mutation;
	}
	
	void checkMinimizeAlways() throws InterruptedException {
		if (minimize_always)
			minimize();
	}
	
	boolean isSingleton() {
		return singleton!=null;
	}

	/**
	 * Returns the singleton string for this automaton.
	 * An automaton that accepts exactly one string <i>may</i> be represented
	 * in singleton mode. In that case, this method may be used to obtain the string.
	 * @return string, null if this automaton is not in singleton mode.
	 */
	public String getSingleton() {
		return singleton;
	}
	
	/** 
	 * Sets initial state. 
	 * @param s state
	 */
	public void setInitialState(State s) {
		initial = s;
		singleton = null;
	}
	
	/** 
	 * Gets initial state. 
	 * @return state
	 */
	public State getInitialState() throws InterruptedException {
		expandSingleton();
		return initial;
	}
	
	/**
	 * Returns deterministic flag for this automaton.
	 * @return true if the automaton is definitely deterministic, false if the automaton
	 *         may be nondeterministic
	 */
	public boolean isDeterministic() {
		return deterministic;
	}
	
	/**
	 * Sets deterministic flag for this automaton.
	 * This method should (only) be used if automata are constructed manually.
	 * @param deterministic true if the automaton is definitely deterministic, false if the automaton
	 *                      may be nondeterministic
	 */
	public void setDeterministic(boolean deterministic) {
		this.deterministic = deterministic;
	}
	
	/**
	 * Associates extra information with this automaton. 
	 * @param info extra information
	 */
	public void setInfo(Object info) {
		this.info = info;
	}
	
	/**
	 * Returns extra information associated with this automaton. 
	 * @return extra information
	 * @see #setInfo(Object)
	 */
	public Object getInfo()	{
		return info;
	}
	
	/** 
	 * Returns the set of states that are reachable from the initial state.
	 * @return set of {@link State} objects
	 */
	public Set<State> getStates() throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		expandSingleton();
		Set<State> visited;
		if (isDebug())
			visited = new LinkedHashSet<State>();
		else
			visited = new HashSet<State>();
		LinkedList<State> worklist = new LinkedList<State>();
		worklist.add(initial);
		visited.add(initial);
		while (worklist.size() > 0) {
			if (Thread.currentThread().isInterrupted()) {
				throw new InterruptedException();
			}

			State s = worklist.removeFirst();
			Collection<Transition> tr;
			if (isDebug())
				tr = s.getSortedTransitions(false);
			else
				tr = s.transitions;
			for (Transition t : tr) {
				if (Thread.currentThread().isInterrupted()) {
					throw new InterruptedException();
				}

				if (!visited.contains(t.to)) {
					visited.add(t.to);
					worklist.add(t.to);
				}
			}
		}
		return visited;
	}
	
	/** 
	 * Returns the set of reachable accept states. 
	 * @return set of {@link State} objects
	 */
	public Set<State> getAcceptStates() throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		expandSingleton();
		HashSet<State> accepts = new HashSet<State>();
		HashSet<State> visited = new HashSet<State>();
		LinkedList<State> worklist = new LinkedList<State>();
		worklist.add(initial);
		visited.add(initial);
		while (worklist.size() > 0) {
			if (Thread.currentThread().isInterrupted()) {
				throw new InterruptedException();
			}

			State s = worklist.removeFirst();
			if (s.accept)
				accepts.add(s);
			for (Transition t : s.transitions) {
				if (Thread.currentThread().isInterrupted()) {
					throw new InterruptedException();
				}

				if (!visited.contains(t.to)) {
					visited.add(t.to);
					worklist.add(t.to);
				}
			}
		}
		return accepts;
	}
	
	/** 
	 * Assigns consecutive numbers to the given states. 
	 */
	static void setStateNumbers(Set<State> states) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		if (states.size() == Integer.MAX_VALUE)
			throw new IllegalArgumentException("number of states exceeded Integer.MAX_VALUE");
		int number = 0;
		for (State s : states) {
			if (Thread.currentThread().isInterrupted()) {
				throw new InterruptedException();
			}

			s.number = number++;
		}
	}
	
	/** 
	 * Adds transitions to explicit crash state to ensure that transition function is total. 
	 */
	void totalize() throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		State s = new State();
		s.transitions.add(new Transition(Character.MIN_VALUE, Character.MAX_VALUE, s));
		for (State p : getStates()) {
			if (Thread.currentThread().isInterrupted()) {
				throw new InterruptedException();
			}

			int maxi = Character.MIN_VALUE;
			for (Transition t : p.getSortedTransitions(false)) {
				if (Thread.currentThread().isInterrupted()) {
					throw new InterruptedException();
				}

				if (t.min > maxi)
					p.transitions.add(new Transition((char)maxi, (char)(t.min - 1), s));
				if (t.max + 1 > maxi)
					maxi = t.max + 1;
			}
			if (maxi <= Character.MAX_VALUE)
				p.transitions.add(new Transition((char)maxi, Character.MAX_VALUE, s));
		}
	}
	
	/**
	 * Restores representation invariant.
	 * This method must be invoked before any built-in automata operation is performed 
	 * if automaton states or transitions are manipulated manually.
	 * @see #setDeterministic(boolean)
	 */
	public void restoreInvariant() throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		removeDeadTransitions();
	}
	
	/** 
	 * Reduces this automaton.
	 * An automaton is "reduced" by combining overlapping and adjacent edge intervals with same destination. 
	 */
	public void reduce() throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		if (isSingleton())
			return;
		Set<State> states = getStates();
		setStateNumbers(states);
		for (State s : states) {
			if (Thread.currentThread().isInterrupted()) {
				throw new InterruptedException();
			}

			List<Transition> st = s.getSortedTransitions(true);
			s.resetTransitions();
			State p = null;
			int min = -1, max = -1;
			for (Transition t : st) {
				if (Thread.currentThread().isInterrupted()) {
					throw new InterruptedException();
				}

				if (p == t.to) {
					if (t.min <= max + 1) {
						if (t.max > max)
							max = t.max;
					} else {
						if (p != null)
							s.transitions.add(new Transition((char)min, (char)max, p));
						min = t.min;
						max = t.max;
					}
				} else {
					if (p != null)
						s.transitions.add(new Transition((char)min, (char)max, p));
					p = t.to;
					min = t.min;
					max = t.max;
				}
			}
			if (p != null)
				s.transitions.add(new Transition((char)min, (char)max, p));
		}
		clearHashCode();
	}
	
	/** 
	 * Returns sorted array of all interval start points. 
	 */
	char[] getStartPoints() throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		Set<Character> pointset = new HashSet<Character>();
		pointset.add(Character.MIN_VALUE);
		for (State s : getStates()) {
			if (Thread.currentThread().isInterrupted()) {
				throw new InterruptedException();
			}

			for (Transition t : s.transitions) {
				if (Thread.currentThread().isInterrupted()) {
					throw new InterruptedException();
				}

				pointset.add(t.min);
				if (t.max < Character.MAX_VALUE)
					pointset.add((char)(t.max + 1));
			}
		}
		char[] points = new char[pointset.size()];
		int n = 0;
		for (Character m : pointset) {
			if (Thread.currentThread().isInterrupted()) {
				throw new InterruptedException();
			}

			points[n++] = m;
		}
		Arrays.sort(points);
		return points;
	}
	
	/** 
	 * Returns the set of live states. A state is "live" if an accept state is reachable from it. 
	 * @return set of {@link State} objects
	 */
	public Set<State> getLiveStates() throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		expandSingleton();
		return getLiveStates(getStates());
	}
	
	private Set<State> getLiveStates(Set<State> states) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		HashMap<State, Set<State>> map = new HashMap<State, Set<State>>();
		for (State s : states) {
			if (Thread.currentThread().isInterrupted()) {
				throw new InterruptedException();
			}

			map.put(s, new HashSet<State>());
		}
		for (State s : states) {
			if (Thread.currentThread().isInterrupted()) {
				throw new InterruptedException();
			}

			for (Transition t : s.transitions) {
				if (Thread.currentThread().isInterrupted()) {
					throw new InterruptedException();
				}

				map.get(t.to).add(s);
			}
		}
		Set<State> live = new HashSet<State>(getAcceptStates());
		LinkedList<State> worklist = new LinkedList<State>(live);
		while (worklist.size() > 0) {
			if (Thread.currentThread().isInterrupted()) {
				throw new InterruptedException();
			}

			State s = worklist.removeFirst();
			for (State p : map.get(s)) {
				if (Thread.currentThread().isInterrupted()) {
					throw new InterruptedException();
				}

				if (!live.contains(p)) {
					live.add(p);
					worklist.add(p);
				}
			}
		}
		return live;
	}
	
	/** 
	 * Removes transitions to dead states and calls {@link #reduce()} and {@link #clearHashCode()}.
	 * (A state is "dead" if no accept state is reachable from it.)
	 */
	public void removeDeadTransitions() throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		clearHashCode();
		if (isSingleton())
			return;
		Set<State> states = getStates();
		Set<State> live = getLiveStates(states);
		for (State s : states) {
			if (Thread.currentThread().isInterrupted()) {
				throw new InterruptedException();
			}

			Set<Transition> st = s.transitions;
			s.resetTransitions();
			for (Transition t : st) {
				if (Thread.currentThread().isInterrupted()) {
					throw new InterruptedException();
				}

				if (live.contains(t.to))
					s.transitions.add(t);
			}
		}
		reduce();
	}
	
	/** 
	 * Returns a sorted array of transitions for each state (and sets state numbers). 
	 */
	static Transition[][] getSortedTransitions(Set<State> states) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		setStateNumbers(states);
		Transition[][] transitions = new Transition[states.size()][];
		for (State s : states) {
			if (Thread.currentThread().isInterrupted()) {
				throw new InterruptedException();
			}

			transitions[s.number] = s.getSortedTransitionArray(false);
		}
		return transitions;
	}
	
	/** 
	 * Expands singleton representation to normal representation.
	 * Does nothing if not in singleton representation. 
	 */
	public void expandSingleton() throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		if (isSingleton()) {
			State p = new State();
			initial = p;
			for (int i = 0; i < singleton.length(); i++) {
				if (Thread.currentThread().isInterrupted()) {
					throw new InterruptedException();
				}

				State q = new State();
				q.number = i;
				p.transitions.add(new Transition(singleton.charAt(i), q));
				p = q;
			}
			p.accept = true;
			deterministic = true;
			singleton = null;
		}
	}
	
	/**
	 * Returns the number of states in this automaton.
	 */
	public int getNumberOfStates() throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		if (isSingleton())
			return singleton.length() + 1;
		return getStates().size();
	}
	
	/**
	 * Returns the number of transitions in this automaton. This number is counted
	 * as the total number of edges, where one edge may be a character interval.
	 */
	public int getNumberOfTransitions() throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		if (isSingleton())
			return singleton.length();
		int c = 0;
		for (State s : getStates()) {
			if (Thread.currentThread().isInterrupted()) {
				throw new InterruptedException();
			}

			c += s.transitions.size();
		}
		return c;
	}
	
	/**
	 * Returns true if the language of this automaton is equal to the language
	 * of the given automaton. Implemented using <code>hashCode</code> and
	 * <code>subsetOf</code>.
	 */
	@Override
	public boolean equals(Object obj) {

		if (obj == this)
			return true;
		if (!(obj instanceof Automaton))
			return false;
		Automaton a = (Automaton)obj;
		if (isSingleton() && a.isSingleton())
			return singleton.equals(a.singleton);
		try {
			return hashCode() == a.hashCode() && subsetOf(a) && a.subsetOf(this);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Returns hash code for this automaton. The hash code is based on the
	 * number of states and transitions in the minimized automaton.
	 * Invoking this method may involve minimizing the automaton.
	 */
	@Override
	public int hashCode() {
		if (hash_code == 0) {
			try {
				minimize();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return hash_code;
	}
	
	/**
	 * Recomputes the hash code.
	 * The automaton must be minimal when this operation is performed.
	 */
	void recomputeHashCode() throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		hash_code = getNumberOfStates() * 3 + getNumberOfTransitions() * 2;
		if (hash_code == 0)
			hash_code = 1;
	}
	
	/**
	 * Must be invoked when the stored hash code may no longer be valid.
	 */
	void clearHashCode() {
		hash_code = 0;
	}
	
	/**
	 * Returns a string representation of this automaton.
	 */
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		if (isSingleton()) {
			b.append("singleton: ");
			for (char c : singleton.toCharArray()) {
				try {
					Transition.appendCharString(c, b);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			b.append("\n");
		} else {
			try {
				Set<State> states = getStates();
				setStateNumbers(states);
				b.append("initial state: ").append(initial.number).append("\n");
				for (State s : states) {
					b.append(s.toString());
				}
			} catch (InterruptedException e) {

			}
		}
		return b.toString();
	}

	/**
	 * Returns <a href="http://www.research.att.com/sw/tools/graphviz/" target="_top">Graphviz Dot</a> 
	 * representation of this automaton.
	 */
	public String toDot() throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		StringBuilder b = new StringBuilder("digraph Automaton {\n");
		b.append("  rankdir = LR;\n");
		Set<State> states = getStates();
		setStateNumbers(states);
		for (State s : states) {
			if (Thread.currentThread().isInterrupted()) {
				throw new InterruptedException();
			}

			b.append("  ").append(s.number);
			if (s.accept)
				b.append(" [shape=doublecircle,label=\"\"];\n");
			else
				b.append(" [shape=circle,label=\"\"];\n");
			if (s == initial) {
				b.append("  initial [shape=plaintext,label=\"\"];\n");
				b.append("  initial -> ").append(s.number).append("\n");
			}
			for (Transition t : s.transitions) {
				if (Thread.currentThread().isInterrupted()) {
					throw new InterruptedException();
				}

				b.append("  ").append(s.number);
				t.appendDot(b);
			}
		}
		return b.append("}\n").toString();
	}
	
	/**
	 * Returns a clone of this automaton, expands if singleton.
	 */
	Automaton cloneExpanded() throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		Automaton a = clone();
		a.expandSingleton();
		return a;
	}

	/**
	 * Returns a clone of this automaton unless <code>allow_mutation</code> is set, expands if singleton.
	 */
	Automaton cloneExpandedIfRequired() throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		if (allow_mutation) {
			expandSingleton();
			return this;
		} else
			return cloneExpanded();
	}

	/**
	 * Returns a clone of this automaton.
	 */
	@Override
	public Automaton clone() {
		try {
			Automaton a = (Automaton)super.clone();
			if (!isSingleton()) {
				HashMap<State, State> m = new HashMap<State, State>();
				Set<State> states = getStates();
				for (State s : states) {
					if (Thread.currentThread().isInterrupted()) {
						throw new InterruptedException();
					}

					m.put(s, new State());
				}
				for (State s : states) {
					if (Thread.currentThread().isInterrupted()) {
						throw new InterruptedException();
					}

					State p = m.get(s);
					p.accept = s.accept;
					if (s == initial)
						a.initial = p;
					for (Transition t : s.transitions) {
						if (Thread.currentThread().isInterrupted()) {
							throw new InterruptedException();
						}

						p.transitions.add(new Transition(t.min, t.max, m.get(t.to)));
					}
				}
			}
			return a;
		} catch (CloneNotSupportedException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Returns a clone of this automaton, or this automaton itself if <code>allow_mutation</code> flag is set. 
	 */
	Automaton cloneIfRequired() throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		if (allow_mutation)
			return this;
		else
			return clone();
	}
	
	/** 
	 * Retrieves a serialized <code>Automaton</code> located by a URL.
	 * @param url URL of serialized automaton
	 * @exception IOException if input/output related exception occurs
	 * @exception ClassCastException if the data is not a serialized <code>Automaton</code>
	 * @exception ClassNotFoundException if the class of the serialized object cannot be found
	 */
	public static Automaton load(URL url) throws IOException, ClassCastException, ClassNotFoundException, InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return load(url.openStream());
	}
	
	/**
	 * Retrieves a serialized <code>Automaton</code> from a stream.
	 * @param stream input stream with serialized automaton
	 * @exception IOException if input/output related exception occurs
	 * @exception ClassCastException if the data is not a serialized <code>Automaton</code>
	 * @exception ClassNotFoundException if the class of the serialized object cannot be found
	 */
	public static Automaton load(InputStream stream) throws IOException, ClassCastException, ClassNotFoundException, InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		ObjectInputStream s = new ObjectInputStream(stream);
		return (Automaton)s.readObject();
	}
	
	/**
	 * Writes this <code>Automaton</code> to the given stream.
	 * @param stream output stream for serialized automaton
	 * @exception IOException if input/output related exception occurs
	 */
	public void store(OutputStream stream) throws IOException, InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		ObjectOutputStream s = new ObjectOutputStream(stream);
		s.writeObject(this);
		s.flush();
	}

	/** 
	 * See {@link BasicAutomata#makeEmpty()}.
	 */
	public static Automaton makeEmpty() throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return BasicAutomata.makeEmpty();
	}

	/** 
	 * See {@link BasicAutomata#makeEmptyString()}.
	 */
	public static Automaton makeEmptyString() throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return BasicAutomata.makeEmptyString();
	}
	
	/** 
	 * See {@link BasicAutomata#makeAnyString()}.
	 */
	public static Automaton makeAnyString() throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return BasicAutomata.makeAnyString();
	}
	
	/** 
	 * See {@link BasicAutomata#makeAnyChar()}.
	 */
	public static Automaton makeAnyChar() throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return BasicAutomata.makeAnyChar();
	}
	
	/** 
	 * See {@link BasicAutomata#makeChar(char)}.
	 */
	public static Automaton makeChar(char c) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return BasicAutomata.makeChar(c);
	}
	
	/** 
	 * See {@link BasicAutomata#makeCharRange(char, char)}.
	 */
	public static Automaton makeCharRange(char min, char max) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return BasicAutomata.makeCharRange(min, max);
	}
	
	/** 
	 * See {@link BasicAutomata#makeCharSet(String)}.
	 */
	public static Automaton makeCharSet(String set) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return BasicAutomata.makeCharSet(set);
	}
	
	/** 
	 * See {@link BasicAutomata#makeInterval(int, int, int)}.
	 */
	public static Automaton makeInterval(int min, int max, int digits) throws IllegalArgumentException, InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return BasicAutomata.makeInterval(min, max, digits);
	}
	
	/** 
	 * See {@link BasicAutomata#makeString(String)}.
	 */
	public static Automaton makeString(String s) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return BasicAutomata.makeString(s);
	}
	
    /** 
     * See {@link BasicAutomata#makeStringUnion(CharSequence...)}.
     */
    public static Automaton makeStringUnion(CharSequence... strings) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

        return BasicAutomata.makeStringUnion(strings);
    }

	/**
	 * See {@link BasicAutomata#makeMaxInteger(String)}.
	 */
	public static Automaton makeMaxInteger(String n) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return BasicAutomata.makeMaxInteger(n);
	}
	
	/**
	 * See {@link BasicAutomata#makeMinInteger(String)}.
	 */
	public static Automaton makeMinInteger(String n) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return BasicAutomata.makeMinInteger(n);
	}

	/**
	 * See {@link BasicAutomata#makeTotalDigits(int)}.
	 */
	public static Automaton makeTotalDigits(int i) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return BasicAutomata.makeTotalDigits(i);
	}

	/**
	 * See {@link BasicAutomata#makeFractionDigits(int)}.
	 */
	public static Automaton makeFractionDigits(int i) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return BasicAutomata.makeFractionDigits(i);
	}
	
	/**
	 * See {@link BasicAutomata#makeIntegerValue(String)}.
	 */
	public static Automaton makeIntegerValue(String value) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return BasicAutomata.makeIntegerValue(value);
	}
	
	/**
	 * See {@link BasicAutomata#makeDecimalValue(String)}.
	 */
	public static Automaton makeDecimalValue(String value) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return BasicAutomata.makeDecimalValue(value);
	}
	
	/**
	 * See {@link BasicAutomata#makeStringMatcher(String)}.
	 */
	public static Automaton makeStringMatcher(String s) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return BasicAutomata.makeStringMatcher(s);
	}
	
	/** 
	 * See {@link BasicOperations#concatenate(Automaton, Automaton)}.
	 */
	public Automaton concatenate(Automaton a) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return BasicOperations.concatenate(this, a);
	}
	
	/**
	 * See {@link BasicOperations#concatenate(List)}.
	 */
	static public Automaton concatenate(List<Automaton> l) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return BasicOperations.concatenate(l);
	}

	/**
	 * See {@link BasicOperations#optional(Automaton)}.
	 */
	public Automaton optional() throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return BasicOperations.optional(this);
	}
	
	/**
	 * See {@link BasicOperations#repeat(Automaton)}.
	 */
	public Automaton repeat() throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return BasicOperations.repeat(this);
	}

	/**
	 * See {@link BasicOperations#repeat(Automaton, int)}.
	 */
	public Automaton repeat(int min) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return BasicOperations.repeat(this, min);
	}
	
	/**
	 * See {@link BasicOperations#repeat(Automaton, int, int)}.
	 */
	public Automaton repeat(int min, int max) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return BasicOperations.repeat(this, min, max);
	}

	/**
	 * See {@link BasicOperations#complement(Automaton)}.
	 */
	public Automaton complement() throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return BasicOperations.complement(this);
	}

	/**
	 * See {@link BasicOperations#minus(Automaton, Automaton)}.
	 */
	public Automaton minus(Automaton a) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return BasicOperations.minus(this, a);
	}

	/**
	 * See {@link BasicOperations#intersection(Automaton, Automaton)}.
	 */
	public Automaton intersection(Automaton a) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return BasicOperations.intersection(this, a);
	}
	
	/**
	 * See {@link BasicOperations#subsetOf(Automaton, Automaton)}.
	 */
	public boolean subsetOf(Automaton a) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return BasicOperations.subsetOf(this, a);
	}
	
	/**
	 * See {@link BasicOperations#union(Automaton, Automaton)}.
	 */
	public Automaton union(Automaton a) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return BasicOperations.union(this, a);
	}
	
	/**
	 * See {@link BasicOperations#union(Collection)}.
	 */
	static public Automaton union(Collection<Automaton> l) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return BasicOperations.union(l);
	}

	/**
	 * See {@link BasicOperations#determinize(Automaton)}.
	 */
	public void determinize() throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		BasicOperations.determinize(this);
	}

	/** 
	 * See {@link BasicOperations#addEpsilons(Automaton, Collection)}.
	 */
	public void addEpsilons(Collection<StatePair> pairs) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		BasicOperations.addEpsilons(this, pairs);
	}
	
	/**
	 * See {@link BasicOperations#isEmptyString(Automaton)}.
	 */
	public boolean isEmptyString() throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return BasicOperations.isEmptyString(this);
	}

	/**
	 * See {@link BasicOperations#isEmpty(Automaton)}.
	 */
	public boolean isEmpty() throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return BasicOperations.isEmpty(this);
	}
	
	/**
	 * See {@link BasicOperations#isTotal(Automaton)}.
	 */
	public boolean isTotal() throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return BasicOperations.isTotal(this);
	}

	// 新增 返回不一定是最短路径
	public String getExample(boolean accepted) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return BasicOperations.getExample(this, accepted);
	}

	/**
	 * See {@link BasicOperations#getShortestExample(Automaton, boolean)}.
	 */
	public String getShortestExample(boolean accepted) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return BasicOperations.getShortestExample(this, accepted);
	}
	
	/**
	 * See {@link BasicOperations#run(Automaton, String)}.
	 */
	public boolean run(String s) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return BasicOperations.run(this, s);
	}
	
	/**
	 * See {@link MinimizationOperations#minimize(Automaton)}.
	 */
	public void minimize() throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		MinimizationOperations.minimize(this);
	}
	
	/**
	 * See {@link MinimizationOperations#minimize(Automaton)}.
	 * Returns the automaton being given as argument.
	 */
	public static Automaton minimize(Automaton a) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		a.minimize();
		return a;
	}
	
	/**
	 * See {@link SpecialOperations#overlap(Automaton, Automaton)}.
	 */
	public Automaton overlap(Automaton a) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return SpecialOperations.overlap(this, a);
	}
	
	/** 
	 * See {@link SpecialOperations#singleChars(Automaton)}.
	 */
	public Automaton singleChars() throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return SpecialOperations.singleChars(this);
	}
	
	/**
	 * See {@link SpecialOperations#trim(Automaton, String, char)}.
	 */
	public Automaton trim(String set, char c) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return SpecialOperations.trim(this, set, c);
	}
	
	/**
	 * See {@link SpecialOperations#compress(Automaton, String, char)}.
	 */
	public Automaton compress(String set, char c) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return SpecialOperations.compress(this, set, c);
	}
	
	/**
	 * See {@link SpecialOperations#subst(Automaton, Map)}.
	 */
	public Automaton subst(Map<Character,Set<Character>> map) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return SpecialOperations.subst(this, map);
	}

	/**
	 * See {@link SpecialOperations#subst(Automaton, char, String)}.
	 */
	public Automaton subst(char c, String s) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return SpecialOperations.subst(this, c, s);
	}
	
	/**
	 * See {@link SpecialOperations#homomorph(Automaton, char[], char[])}.
	 */
	public Automaton homomorph(char[] source, char[] dest) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return SpecialOperations.homomorph(this, source, dest);
	}
	
	/**
	 * See {@link SpecialOperations#projectChars(Automaton, Set)}.
	 */
	public Automaton projectChars(Set<Character> chars) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return SpecialOperations.projectChars(this, chars);
	}
	
	/**
	 * See {@link SpecialOperations#isFinite(Automaton)}.
	 */
	public boolean isFinite() throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return SpecialOperations.isFinite(this);
	}

	// 新增 返回指定最小长度的串
	@Deprecated
	public String getExample(int minLength) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return SpecialOperations.getExample(this, minLength);
	}


	/**
	 * See {@link SpecialOperations#getStrings(Automaton, int)}.
	 */
	public Set<String> getStrings(int length) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return SpecialOperations.getStrings(this, length);
	}

	/**
	 * See {@link SpecialOperations#getFiniteStrings(Automaton)}.
	 */
	public Set<String> getFiniteStrings() throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return SpecialOperations.getFiniteStrings(this);
	}
	
	/**
	 * See {@link SpecialOperations#getFiniteStrings(Automaton, int)}.
	 */
	public Set<String> getFiniteStrings(int limit) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return SpecialOperations.getFiniteStrings(this, limit);
	}

	/**
	 * See {@link SpecialOperations#getCommonPrefix(Automaton)}.
	 */
	public String getCommonPrefix() throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return SpecialOperations.getCommonPrefix(this);
	}
	
	/**
	 * See {@link SpecialOperations#prefixClose(Automaton)}.
	 */
	public void prefixClose() throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		SpecialOperations.prefixClose(this);
	}

	/**
	 * See {@link SpecialOperations#hexCases(Automaton)}.
	 */
	public static Automaton hexCases(Automaton a) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return SpecialOperations.hexCases(a);
	}
	
	/**
	 * See {@link SpecialOperations#replaceWhitespace(Automaton)}.
	 */
	public static Automaton replaceWhitespace(Automaton a) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return SpecialOperations.replaceWhitespace(a);
	}
	
	/**
	 * See {@link ShuffleOperations#shuffleSubsetOf(Collection, Automaton, Character, Character)}.
	 */ 
	public static String shuffleSubsetOf(Collection<Automaton> ca, Automaton a, Character suspend_shuffle, Character resume_shuffle) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return ShuffleOperations.shuffleSubsetOf(ca, a, suspend_shuffle, resume_shuffle);
	}

	/** 
	 * See {@link ShuffleOperations#shuffle(Automaton, Automaton)}.
	 */
	public Automaton shuffle(Automaton a) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}

		return ShuffleOperations.shuffle(this, a);
	}
}
