package services;

import domain_objects.NeuralNetwork;
import domain_objects.ReferenceVectorContainer;

public interface NeuralNetworkVerifier {
	double test(NeuralNetwork network, Iterable<ReferenceVectorContainer> testVectors);
}
