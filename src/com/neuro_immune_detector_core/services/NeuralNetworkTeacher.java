package com.neuro_immune_detector_core.services;

import com.neuro_immune_detector_core.domain_objects.NeuralNetwork;
import com.neuro_immune_detector_core.domain_objects.ReferenceVectorContainer;

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