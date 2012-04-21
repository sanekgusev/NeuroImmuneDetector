package com.neuro_immune_detector_core.domain_objects;

import java.io.Serializable;

public class KohonenNeuron implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8165721364181741043L;
	
	private byte[] weights;

	public byte[] getWeights() {
		return weights;
	}
	
	public KohonenNeuron(int numberOfWeights) {
		weights = new byte[numberOfWeights];
	}
	
	public double calculateOutput(byte[] inputVector) {
		if (inputVector.length != weights.length) {
			throw new IllegalArgumentException("Incorrect inputVector dimension");
		}
		double sum = 0;
		for (int i = 0; i < inputVector.length; i++) {
			sum += Math.pow(inputVector[i] - weights[i], 2); 
		}
		return Math.sqrt(sum);
	}
}
