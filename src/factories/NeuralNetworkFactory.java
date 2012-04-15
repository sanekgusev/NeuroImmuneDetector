package factories;

import policies.DistributionPolicy;
import structure.NeuralNetwork;
import structure.NeuralNetworkImpl;

public class NeuralNetworkFactory {
	public static NeuralNetwork createNeuralNetwork(int numberOfInputs, 
			int numberOfHiddenNeurons, DistributionPolicy distributionPolicy) {
		int numberOfPositiveNeurons = distributionPolicy.getNumberOfPositiveElements(numberOfHiddenNeurons);
		int numberOfNegativeNeurons = numberOfHiddenNeurons - numberOfPositiveNeurons;
		return new NeuralNetworkImpl(numberOfInputs, numberOfPositiveNeurons, numberOfNegativeNeurons);
	}
}
