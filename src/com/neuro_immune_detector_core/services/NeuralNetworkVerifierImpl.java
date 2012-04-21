package com.neuro_immune_detector_core.services;

import com.neuro_immune_detector_core.domain_objects.KohonenNeuron;
import com.neuro_immune_detector_core.domain_objects.NeuralNetwork;
import com.neuro_immune_detector_core.domain_objects.ReferenceVectorContainer;

public class NeuralNetworkVerifierImpl implements NeuralNetworkVerifier {

	@Override
	public double test(NeuralNetwork network,
			Iterable<ReferenceVectorContainer> testVectors) {
		int misses = 0;
		for (ReferenceVectorContainer container : testVectors) {
			byte[] testVector = container.getReferenceVector();
			KohonenNeuron winner = network.findWinnerNeuron(testVector);
			if (network.getPositiveNeurons().contains(winner) != container.isInfected()) {
				misses++;
			}
		}
		// non-obviously, misses actually holds error function value
		return misses;
	}

}
