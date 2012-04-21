package com.neuro_immune_detector_core.policies;

public class DistributionPolicyImpl implements
		DistributionPolicy {
	
	private double positiveElementsPercentage;
	
	public DistributionPolicyImpl(double positiveElementsPercentage) {
		if (positiveElementsPercentage < 0 || positiveElementsPercentage > 1.0) {
			throw new IllegalArgumentException("positiveNeuronsPercentage is invalid, should be within [0; 1] range.");
		}
		this.positiveElementsPercentage = positiveElementsPercentage;
	}

	@Override
	public int getNumberOfPositiveElements(int totalElements) {
		if (totalElements < 0) {
			throw new IllegalArgumentException("totalElements is invalid, should be >= 0");
		}
		return (int)Math.round(totalElements * positiveElementsPercentage);
	}

}
