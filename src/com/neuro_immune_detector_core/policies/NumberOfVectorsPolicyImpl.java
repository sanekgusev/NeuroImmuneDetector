package com.neuro_immune_detector_core.policies;

import com.neuro_immune_detector_core.domain_objects.NeuralNetwork;

public class NumberOfVectorsPolicyImpl implements NumberOfVectorsPolicy {
	
	private double coefficient;
	
	public NumberOfVectorsPolicyImpl(double coefficient) {
		this.coefficient = coefficient;
	}

	@Override
	public int getRequiredNumberOfVectors(NeuralNetwork network) {
		return (int)Math.round(coefficient * 
				(network.getNegativeNeurons().size() + network.getPositiveNeurons().size())); 
	}

}
