package services;

import structure.NeuralNetwork;
import vectors.ReferenceVectorContainer;

public interface NeuralNetworkTester {
	double test(NeuralNetwork network, Iterable<ReferenceVectorContainer> testVectors);
}
