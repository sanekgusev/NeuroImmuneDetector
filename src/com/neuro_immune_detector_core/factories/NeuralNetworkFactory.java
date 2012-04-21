package com.neuro_immune_detector_core.factories;

import com.neuro_immune_detector_core.domain_objects.NeuralNetwork;
import com.neuro_immune_detector_core.domain_objects.NeuralNetworkImpl;
import com.neuro_immune_detector_core.policies.DistributionPolicy;


public class NeuralNetworkFactory {
	public static NeuralNetwork createNeuralNetwork(int numberOfInputs, 
			int numberOfHiddenNeurons, DistributionPolicy distributionPolicy) {
		int numberOfPositiveNeurons = distributionPolicy.getNumberOfPositiveElements(numberOfHiddenNeurons);
		int numberOfNegativeNeurons = numberOfHiddenNeurons - numberOfPositiveNeurons;
		return new NeuralNetworkImpl(numberOfInputs, numberOfPositiveNeurons, numberOfNegativeNeurons);
	}
}
