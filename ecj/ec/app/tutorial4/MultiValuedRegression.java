/*
  Copyright 2006 by Sean Luke
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
*/

package ec.app.tutorial4;

import ec.util.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ec.*;
import ec.gp.*;
import ec.gp.koza.*;
import ec.simple.*;

public class MultiValuedRegression extends GPProblem implements SimpleProblemForm {
	private static final long serialVersionUID = 1;

	public double[] currentValue;
	final static double PROBABLY_ZERO = 1.11E-15;
	final static double BIG_NUMBER = 1.0e15;

	public void setup(final EvolutionState state, final Parameter base) {
		super.setup(state, base);

		// verify our input is the right class (or subclasses from it)
		if (!(input instanceof DoubleData))
			state.output.fatal("GPData class must subclass from " + DoubleData.class, base.push(P_DATA), null);
	}

	public void evaluate(final EvolutionState state, final Individual ind, final int subpopulation,
			final int threadnum) {
		if (!ind.evaluated) // don't bother reevaluating
		{
			try {
				this.currentValue = new double[9]; // initialize 9 attributes

				String fp = System.getProperty("user.dir") + "/ec/app/tutorial4/training.txt";
				//String fp = "training.txt"; //for deployment
				DoubleData input = (DoubleData) (this.input);

				int hits = 0;
				double accuracy = 0.0;
				double expectedResult;

				Stream<String> stream = Files.lines(Paths.get(fp));
				ArrayList<String> list = (ArrayList<String>) stream.collect(Collectors.toList());
				stream.close();

				for (int i = 0; i < list.size(); i++) {
					String[] line = list.get(i).split(",");
					for (int j = 0; j < line.length - 1; j++) {
						this.currentValue[j] = Double.parseDouble(line[j]);
					}
					expectedResult = Double.parseDouble(line[line.length - 1]);

					((GPIndividual) ind).trees[0].child.eval(state, threadnum, input, stack, ((GPIndividual) ind),
							this);

					if ((input.x > 0 && expectedResult == 4.0) || (input.x < 0 && expectedResult == 2.0)) {
						hits++;
					}
				}

				accuracy = (double) hits / list.size();
				SimpleFitness f = ((SimpleFitness) ind.fitness);
				f.setFitness(state, accuracy, false);

				ind.evaluated = true;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	public void describe(EvolutionState state, Individual ind, int subpopulation, int threadnum, int log) {
		try {
			this.currentValue = new double[9]; // initialize 9 attributes

			// we do the testing set here
			String fp = System.getProperty("user.dir") + "/ec/app/tutorial4/test.txt";
			//String fp = "test.txt"; //for deployment

			DoubleData input = (DoubleData) (this.input);

			state.output.println("\nPerformance of Best Individual on Testing Set: ", log);

			int hits = 0;
			double accuracy = 0.0;
			double expectedResult;


			Stream<String> stream = Files.lines(Paths.get(fp));
			ArrayList<String> list = (ArrayList<String>) stream.collect(Collectors.toList());
			stream.close();

			for (int i = 0; i < list.size(); i++) {
				String[] line = list.get(i).split(",");
				for (int j = 0; j < line.length - 1; j++) {
					this.currentValue[j] = Double.parseDouble(line[j]);
				}
				expectedResult = Double.parseDouble(line[line.length - 1]);

				((GPIndividual) ind).trees[0].child.eval(state, threadnum, input, stack, ((GPIndividual) ind), this);


				if ((input.x > 0 && expectedResult == 4.0) || (input.x < 0 && expectedResult == 2.0)) {
					hits++;
				}
			}

			accuracy = (double) hits / list.size();
			SimpleFitness f = ((SimpleFitness) ind.fitness);
			f.setFitness(state, accuracy, false);

			f.printFitnessForHumans(state, log);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
