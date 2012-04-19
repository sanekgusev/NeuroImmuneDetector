package domain_objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NeuralNetworkImpl implements NeuralNetwork, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9105246747994707636L;
	
	private List<KohonenNeuron> positiveNeurons;
	private List<KohonenNeuron> negativeNeurons;
	
	private int numberOfInputs;
	
	private transient int positiveAccumulator;
	private transient int negativeAccumulator;
	
	public NeuralNetworkImpl(int numberOfInputs, int numberOfPositiveNeurons, int numberOfNegativeNeurons) {
		if (numberOfInputs < 1) {
			throw new IllegalArgumentException("Invalid numberOfInputs, must be 1 or more");
		}
		if (numberOfPositiveNeurons < 1) {
			throw new IllegalArgumentException("Invalid numberOfPositiveNeurons, must be 1 or more");
		}
		if (numberOfNegativeNeurons < 1) {
			throw new IllegalArgumentException("Invalid numberOfNegativeNeurons, must be 1 or more");
		}
		
		this.numberOfInputs = numberOfInputs;
		positiveNeurons = new ArrayList<KohonenNeuron>();
		for (int i = 0; i < numberOfPositiveNeurons; i++) {
			positiveNeurons.add(new KohonenNeuron(this.getNumberOfInputs()));
		}
		negativeNeurons = new ArrayList<KohonenNeuron>();
		for (int i = 0; i < numberOfNegativeNeurons; i++) {
			negativeNeurons.add(new KohonenNeuron(this.getNumberOfInputs()));
		}
	}

	public int getNumberOfInputs() {
		return numberOfInputs;
	}

	/* (non-Javadoc)
	 * @see structure.NeuralNetwork#getPositiveAccumulator()
	 */
	@Override
	public int getPositiveAccumulator() {
		return positiveAccumulator;
	}

	/* (non-Javadoc)
	 * @see structure.NeuralNetwork#getNegativeAccumulator()
	 */
	@Override
	public int getNegativeAccumulator() {
		return negativeAccumulator;
	}
	
	@Override
	public Collection<KohonenNeuron> getPositiveNeurons() {
		return Collections.unmodifiableCollection(positiveNeurons);
	}

	@Override
	public Collection<KohonenNeuron> getNegativeNeurons() {
		return Collections.unmodifiableCollection(negativeNeurons);
	}

	
	/* (non-Javadoc)
	 * @see structure.NeuralNetwork#getKohonenNeurons()
	 */
	private List<KohonenNeuron> getKohonenNeurons() {
		List<KohonenNeuron> list = new ArrayList<KohonenNeuron>(positiveNeurons);
		list.addAll(negativeNeurons);
		return Collections.unmodifiableList(list);
	}
	
	/* (non-Javadoc)
	 * @see structure.NeuralNetwork#resetAccumulators()
	 */
	@Override
	public void resetAccumulators() {
		positiveAccumulator = negativeAccumulator = 0;
	}
	
	@Override
	public KohonenNeuron findWinnerNeuron(final byte[] inputVector) {
		if (inputVector.length != getNumberOfInputs()) {
			throw new IllegalArgumentException("Wrong dimension of inputVector");
		}
		return Collections.min(getKohonenNeurons(), new Comparator<KohonenNeuron>() {

			@Override
			public int compare(KohonenNeuron o1, KohonenNeuron o2) {
				double output1 = o1.calculateOutput(inputVector);
				double output2 = o2.calculateOutput(inputVector);
				if (output1 < output2) {
					return -1;
				}
				else if (output1 > output2) {
					return 1;
				}
				else {
					return 0;
				}
			}
			
		});
	}
	
	/* (non-Javadoc)
	 * @see structure.NeuralNetwork#processVector(byte[])
	 */
	@Override
	public void processVector(byte[] inputVector) {
		
		KohonenNeuron winner = findWinnerNeuron(inputVector);
		
		if (positiveNeurons.contains(winner)) {
			positiveAccumulator++;
		}
		else {
			negativeAccumulator++;
		}
	}

}
