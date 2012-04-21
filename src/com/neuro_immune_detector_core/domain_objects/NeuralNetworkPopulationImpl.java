package com.neuro_immune_detector_core.domain_objects;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Iterator;

public class NeuralNetworkPopulationImpl extends AbstractCollection<NeuralNetwork>
	implements NeuralNetworkPopulation, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -531484990825174789L;
	
	private ArrayList<NeuralNetwork> list = new ArrayList<NeuralNetwork>();

	@Override
	public Iterator<NeuralNetwork> iterator() {
		return list.iterator();
	}

	@Override
	public int size() {
		return list.size();
	}
	
	@Override
	public boolean add(NeuralNetwork network) {
		return list.add(network);
	}

}
