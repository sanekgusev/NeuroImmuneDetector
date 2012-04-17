package services;

import structure.NeuralNetwork;
import vectors.ReferenceVectorContainer;

public interface NeuralNetworkVerifier {
	double test(NeuralNetwork network, Iterable<ReferenceVectorContainer> testVectors);
}
