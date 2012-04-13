package policies;

public class KohonenNeuronsDistributionPolicyImpl implements
		KohonenNeuronsDistributionPolicy {
	
	private double positiveNeuronsPercentage;
	
	public KohonenNeuronsDistributionPolicyImpl(double positiveNeuronsPercentage) {
		if (positiveNeuronsPercentage < 0 || positiveNeuronsPercentage > 1.0) {
			throw new IllegalArgumentException("positiveNeuronsPercentage is invalid, should be within [0; 1] range.");
		}
		this.positiveNeuronsPercentage = positiveNeuronsPercentage;
	}

	@Override
	public int getNumberOfPositiveNeurons(int totalNeurons) {
		return (int)Math.round(totalNeurons * positiveNeuronsPercentage);
	}

	@Override
	public int getNumberOfNegativeNeurons(int totalNeurons) {
		return totalNeurons - getNumberOfPositiveNeurons(totalNeurons);
	}

}
