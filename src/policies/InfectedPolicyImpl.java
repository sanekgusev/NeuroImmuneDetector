package policies;

import structure.NeuralNetwork;

public class InfectedPolicyImpl implements InfectedPolicy {
	
	private double negativeThreshold;
	private double positiveThreshold;
	
	public InfectedPolicyImpl(double positiveThreshold, double negativeThreshold) {
		if (negativeThreshold < 0 || negativeThreshold > 1.0) {
			throw new IllegalArgumentException("Invalid negativeThreshold, must be within [0; 1] range.");
		}
		if (positiveThreshold < 0 || positiveThreshold > 1.0) {
			throw new IllegalArgumentException("Invalid positiveThreshold, must be within [0; 1] range.");
		}
		this.negativeThreshold = negativeThreshold;
		this.positiveThreshold = positiveThreshold;
	}

	@Override
	public Boolean isInfected(NeuralNetwork network) {
		double positiveAccumulatorValue = network.getPositiveAccumulator();
		double negativeAccumulatorValue = network.getNegativeAccumulator();
		
		if ((double)negativeAccumulatorValue / (positiveAccumulatorValue + negativeAccumulatorValue) > negativeThreshold) {
			return false;
		}
		else if ((double)positiveAccumulatorValue / (positiveAccumulatorValue + negativeAccumulatorValue) >= positiveThreshold) {
			return true;
		}
		else {
			return null;
		}
	}
}
