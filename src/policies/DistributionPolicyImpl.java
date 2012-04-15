package policies;

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
		return (int)Math.round(totalElements * positiveElementsPercentage);
	}

}
