package factories;

import policies.KohonenNeuronsDistributionPolicy;
import structure.NeuralNetwork;
import structure.NeuralNetworkImpl;

public class NeuralNetworkFactory {
	public static NeuralNetwork createNeuralNetwork(int numberOfInputs, 
			int numberOfHiddenNeurons, KohonenNeuronsDistributionPolicy distributionPolicy) {
		int numberOfPositiveNeurons = distributionPolicy.getNumberOfPositiveNeurons(numberOfHiddenNeurons);
		int numberOfNegativeNeurons = distributionPolicy.getNumberOfNegativeNeurons(numberOfHiddenNeurons);
		return new NeuralNetworkImpl(numberOfInputs, numberOfPositiveNeurons, numberOfNegativeNeurons);
	}
}
