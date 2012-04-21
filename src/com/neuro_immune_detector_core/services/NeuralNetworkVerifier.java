package com.neuro_immune_detector_core.services;

import com.neuro_immune_detector_core.domain_objects.NeuralNetwork;
import com.neuro_immune_detector_core.domain_objects.ReferenceVectorContainer;

public interface NeuralNetworkVerifier {
	double test(NeuralNetwork network, Iterable<ReferenceVectorContainer> testVectors);
}
