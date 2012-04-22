package com.neuro_immune_detector_core.services;

import java.io.IOException;

import com.neuro_immune_detector_core.domain_objects.NeuralNetworkPopulation;
import com.neuro_immune_detector_core.exceptions.NeuroImmuneDetectorException;
import com.neuro_immune_detector_core.policies.DistributionPolicy;
import com.neuro_immune_detector_core.repositories.FileRepository;

public interface NeuralNetworkPopulationVerifier {
	void verifyAndFilter(NeuralNetworkPopulation population, FileRepository infectedFiles, 
			FileRepository cleanFiles, int numberOfVectors, DistributionPolicy distributionPolicy,
			double maximumAllowedError) 
					throws IOException, NeuroImmuneDetectorException;
}
