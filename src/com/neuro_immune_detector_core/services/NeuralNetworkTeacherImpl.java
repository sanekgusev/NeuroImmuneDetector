package com.neuro_immune_detector_core.services;

import java.util.Random;
import java.util.logging.Logger;

import com.neuro_immune_detector_core.domain_objects.KohonenNeuron;
import com.neuro_immune_detector_core.domain_objects.NeuralNetwork;
import com.neuro_immune_detector_core.domain_objects.ReferenceVectorContainer;



public class NeuralNetworkTeacherImpl implements NeuralNetworkTeacher {
	
	private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

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
		int misses = 0;
		for (int i = 0; i < iterationsLimit; i++) {
			misses = 0;
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
				// NOTE: offline correction, way to go
				for (int j = 0; j < winner.getNumberOfWeights(); j++) {
					winner.queueAdjustment(j, sign * adjustmentCoefficient * (referenceVector[j] - winner.getWeight(j)));
				}
			}
			network.commitQueuedAdjustments();
			
			// stop if desired precision reached
			// non-obviously, misses actually holds error function value
			if (misses < desiredError) {
				break;
			}
		}
		logger.info(String.format("Misses on last iteration: %d", misses));
	}
	
	// FIXME: hardcoded random offsets
	private void initialize(NeuralNetwork network) {
		Random random = new Random();
		for (KohonenNeuron neuron : network.getPositiveNeurons()) {
			for (int i = 0; i < neuron.getNumberOfWeights(); i++) {
				int offset = random.nextInt(10);
				neuron.setWeight(i, random.nextBoolean() ? (byte)offset : (byte)-offset);
			}
		}
		for (KohonenNeuron neuron : network.getNegativeNeurons()) {
			for (int i = 0; i < neuron.getNumberOfWeights(); i++) {
				int offset = random.nextInt(10);
				neuron.setWeight(i, random.nextBoolean() ? (byte)offset : (byte)-offset);
			}
		}
	}
}
