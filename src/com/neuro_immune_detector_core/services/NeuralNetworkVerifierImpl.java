package com.neuro_immune_detector_core.services;

import java.util.logging.Logger;

import com.neuro_immune_detector_core.domain_objects.KohonenNeuron;
import com.neuro_immune_detector_core.domain_objects.NeuralNetwork;
import com.neuro_immune_detector_core.domain_objects.ReferenceVectorContainer;

public class NeuralNetworkVerifierImpl implements NeuralNetworkVerifier {

	private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
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
		
		logger.info(String.format("Misses when verifying: %d", misses));
		
		// non-obviously, misses actually holds error function value
		return misses;
	}

}
