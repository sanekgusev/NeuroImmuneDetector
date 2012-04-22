package com.neuro_immune_detector_core.services;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import com.neuro_immune_detector_core.domain_objects.NeuralNetwork;
import com.neuro_immune_detector_core.domain_objects.NeuralNetworkPopulation;
import com.neuro_immune_detector_core.domain_objects.ReferenceVectorContainer;
import com.neuro_immune_detector_core.exceptions.NeuroImmuneDetectorException;
import com.neuro_immune_detector_core.policies.DistributionPolicy;
import com.neuro_immune_detector_core.repositories.FileRepository;

public class NeuralNetworkPopulationVerifierImpl implements
		NeuralNetworkPopulationVerifier {
	
	private NeuralNetworkVerifier networkVerifier;
	private ReferenceVectorsCreator vectorsCreator;
	
	public NeuralNetworkPopulationVerifierImpl() {
		networkVerifier = new NeuralNetworkVerifierImpl();
		vectorsCreator = new ReferenceVectorsCreatorImpl();
	}

	@Override
	public void verifyAndFilter(NeuralNetworkPopulation population,
			FileRepository infectedFiles, FileRepository cleanFiles,
			int numberOfVectors, DistributionPolicy distributionPolicy,
			double maximumAllowedError) throws IOException, NeuroImmuneDetectorException {
		Iterator<NeuralNetwork> iterator = population.iterator();
		while (iterator.hasNext()) {
			NeuralNetwork network = iterator.next();
			Collection<ReferenceVectorContainer> testVectors = 
					vectorsCreator.createReferenceVectors(infectedFiles, cleanFiles, 
							numberOfVectors, distributionPolicy, network.getNumberOfInputs());
			if (networkVerifier.test(network, testVectors) > maximumAllowedError) {
				iterator.remove();
			}
		}
	}

}
