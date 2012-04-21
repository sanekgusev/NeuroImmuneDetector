package com.neuro_immune_detector_core.factories;

import com.neuro_immune_detector_core.domain_objects.NeuralNetworkPopulation;
import com.neuro_immune_detector_core.domain_objects.NeuralNetworkPopulationImpl;
import com.neuro_immune_detector_core.policies.DistributionPolicy;


public class NeuralNetworkPopulationFactory {
	public static NeuralNetworkPopulation createNeuralNetworkPopulation(int numberOfNetworks,
			int numberOfInputs, int numberOfHiddenNeurons, DistributionPolicy distributionPolicy) {
		if (numberOfNetworks < 0) {
			throw new IllegalArgumentException("numberOfNetworks is invalid, should be >= 0");
		}
		NeuralNetworkPopulation population = new NeuralNetworkPopulationImpl();
		for (int i = 0; i < numberOfNetworks; i++) {
			population.add(NeuralNetworkFactory.createNeuralNetwork(numberOfInputs, 
					numberOfHiddenNeurons, distributionPolicy));
		}
		return population;
	}
}
