package com.neuro_immune_detector_core.policies;

import com.neuro_immune_detector_core.domain_objects.NeuralNetwork;

public interface NumberOfVectorsPolicy {
	int getRequiredNumberOfVectors(NeuralNetwork network);
}
