package com.neuro_immune_detector_core.services;

import java.util.Random;

import com.neuro_immune_detector_core.domain_objects.KohonenNeuron;
import com.neuro_immune_detector_core.domain_objects.NeuralNetwork;
import com.neuro_immune_detector_core.domain_objects.ReferenceVectorContainer;



public class NeuralNetworkTeacherImpl implements NeuralNetworkTeacher {

	/* (non-Javadoc)
	 * @see services.NeuralNetworkTeacher#initializeAndTeach(structure.NeuralNetwork, java.lang.Iterable, double, int, double)
	 */
	@Override
	public void initializeAndTeach(NeuralNetwork network, 
			Iterable<ReferenceVectorContainer> referenceVectors,
			double desiredError, 
			int iterationsLimit,
			double adjustmentCoefficient) {
		initialize(network);
		teach(network, referenceVectors, desiredError, iterationsLimit, adjustmentCoefficient);
	}
	
	/* (non-Javadoc)
	 * @see services.NeuralNetworkTeacher#teach(structure.NeuralNetwork, java.lang.Iterable, double, int, double)
	 */
	@Override
	public void teach(NeuralNetwork network, 
			Iterable<ReferenceVectorContainer> referenceVectors,
			double desiredError, 
			int iterationsLimit,
			double adjustmentCoefficient) {
		if (adjustmentCoefficient <= 0 || adjustmentCoefficient > 1) {
			throw new IllegalArgumentException("adjustmentCoefficient is invalid, should be within (0; 1] range.");
		}
		for (int i = 0; i < iterationsLimit; i++) {
			int misses = 0;
			for (ReferenceVectorContainer container : referenceVectors) {
				byte[] referenceVector = container.getReferenceVector();
				KohonenNeuron winner = network.findWinnerNeuron(referenceVector);
				int sign = 0;
				if (network.getPositiveNeurons().contains(winner) == container.isInfected()) {
					sign = 1;
				}
				else {
					sign = -1;
					misses++;
				}
				// NOTE: online correction, might want to consider offline instead
				byte[] winnerWeights = winner.getWeights();
				for (int j = 0; j < winnerWeights.length; j++) {
					winnerWeights[j] += sign * adjustmentCoefficient * (referenceVector[j] - winnerWeights[j]);
				}
			}
			// stop if desired precision reached
			// non-obviously, misses actually holds error function value
			if (misses < desiredError) {
				break;
			}
		}
	}
	
	private void initialize(NeuralNetwork network) {
		Random random = new Random();
		for (KohonenNeuron neuron : network.getPositiveNeurons()) {
			byte[] weights = neuron.getWeights();
			random.nextBytes(weights);
		}
		for (KohonenNeuron neuron : network.getNegativeNeurons()) {
			byte[] weights = neuron.getWeights();
			random.nextBytes(weights);
		}
	}
}
