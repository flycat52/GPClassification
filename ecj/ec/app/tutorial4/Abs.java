/*
  Copyright 2006 by Sean Luke
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
*/

package ec.app.tutorial4;

import ec.*;
import ec.gp.*;
import ec.util.*;

public class Abs extends GPNode {
	public String toString() {
		return "abs";
	}

	/*
	 * public void checkConstraints(final EvolutionState state, final int tree,
	 * final GPIndividual typicalIndividual, final Parameter individualBase) {
	 * super.checkConstraints(state,tree,typicalIndividual,individualBase); if
	 * (children.length!=2)
	 * state.output.error("Incorrect number of children for node " +
	 * toStringForError() + " at " + individualBase); }
	 */
	public int expectedChildren() {
		return 1;
	}

	public void eval(final EvolutionState state, final int thread, final GPData input, final ADFStack stack,
			final GPIndividual individual, final Problem problem) {

		DoubleData rd = ((DoubleData) (input));

		children[0].eval(state, thread, input, stack, individual, problem);
		rd.x = /* Strict */Math.abs(rd.x);
	}
}
