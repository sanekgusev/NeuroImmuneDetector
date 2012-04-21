package com.neuro_immune_detector_core.policies;

import com.neuro_immune_detector_core.domain_objects.NeuralNetwork;

public interface InfectedPolicy {
	Boolean isInfected(NeuralNetwork network);
}
