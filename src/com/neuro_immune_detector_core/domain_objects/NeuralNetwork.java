package com.neuro_immune_detector_core.domain_objects;

import java.util.Collection;

public interface NeuralNetwork {
	
	int getNumberOfInputs();

	int getPositiveAccumulator();

	int getNegativeAccumulator();
	
	void resetAccumulators();
	
	Collection<KohonenNeuron> getPositiveNeurons();
	
	Collection<KohonenNeuron> getNegativeNeurons();

	KohonenNeuron findWinnerNeuron(byte[] inputVector);

	void processVector(byte[] inputVector);
	
	void commitQueuedAdjustments();

}