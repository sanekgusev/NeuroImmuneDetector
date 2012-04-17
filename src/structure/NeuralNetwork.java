package structure;

import java.util.Collection;

public interface NeuralNetwork {
	
	int getNumberOfInputs();

	int getPositiveAccumulator();

	int getNegativeAccumulator();
	
	void resetAccumulators();
	
	Collection<KohonenNeuron> getPositiveNeurons();
	
	Collection<KohonenNeuron> getNegativeNeurons();

	KohonenNeuron findWinnerNeuron(byte[] inputVector);

	void processVector(byte[] inputVector);

}