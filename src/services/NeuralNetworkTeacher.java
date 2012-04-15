package services;

import structure.NeuralNetwork;
import vectors.ReferenceVectorContainer;

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