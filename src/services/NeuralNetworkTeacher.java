package services;

import domain_objects.NeuralNetwork;
import domain_objects.ReferenceVectorContainer;

public interface NeuralNetworkTeacher {

	void initializeAndTeach(NeuralNetwork network,
			Iterable<ReferenceVectorContainer> referenceVectors,
			double desiredError, int iterationsLimit,
			double adjustmentCoefficient);

	void teach(NeuralNetwork network,
			Iterable<ReferenceVectorContainer> referenceVectors,
			double desiredError, int iterationsLimit,
			double adjustmentCoefficient);

}