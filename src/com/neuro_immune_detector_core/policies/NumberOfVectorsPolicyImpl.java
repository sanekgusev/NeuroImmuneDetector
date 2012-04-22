package com.neuro_immune_detector_core.policies;

import com.neuro_immune_detector_core.domain_objects.NeuralNetwork;

public class NumberOfVectorsPolicyImpl implements NumberOfVectorsPolicy {

	@Override
	public int getRequiredNumberOfVectors(NeuralNetwork network) {
		return 2 * (network.getNegativeNeurons().size() + network.getPositiveNeurons().size()); 
	}

}
