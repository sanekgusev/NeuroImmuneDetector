package com.neuro_immune_detector_core.domain_objects;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class KohonenNeuron implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8165721364181741043L;
	
	private byte[] weights;
	private transient double[] adjustments;
	
	public int getNumberOfWeights() {
		return weights.length;
	}
	
	public KohonenNeuron(int numberOfWeights) {
		weights = new byte[numberOfWeights];
		adjustments = new double[numberOfWeights];
	}
	
	public byte getWeight(int index) {
		return weights[index];
	}
	
	public void setWeight(int index, byte weight) {
		weights[index] = weight;
	}
	
	public void queueAdjustment(int index, double adjustment) {
		adjustments[index] += adjustment;
	}
	
	public void commitQueuedAdjustments() {
		assert(weights.length == adjustments.length);
		for (int i = 0; i < weights.length; i++) {
			weights[i] += adjustments[i];
			adjustments[i] = 0;
		}
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
	
	private void readObject(ObjectInputStream aInputStream) 
			throws ClassNotFoundException, IOException {
		aInputStream.defaultReadObject();
		adjustments = new double[weights.length];
	}
}
