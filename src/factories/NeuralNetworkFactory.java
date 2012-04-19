package factories;

import domain_objects.NeuralNetwork;
import domain_objects.NeuralNetworkImpl;
import policies.DistributionPolicy;

public class NeuralNetworkFactory {
	public static NeuralNetwork createNeuralNetwork(int numberOfInputs, 
			int numberOfHiddenNeurons, DistributionPolicy distributionPolicy) {
		int numberOfPositiveNeurons = distributionPolicy.getNumberOfPositiveElements(numberOfHiddenNeurons);
		int numberOfNegativeNeurons = numberOfHiddenNeurons - numberOfPositiveNeurons;
		return new NeuralNetworkImpl(numberOfInputs, numberOfPositiveNeurons, numberOfNegativeNeurons);
	}
}
