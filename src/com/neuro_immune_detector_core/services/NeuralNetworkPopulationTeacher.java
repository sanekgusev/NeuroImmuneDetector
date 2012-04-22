package com.neuro_immune_detector_core.services;

import java.io.IOException;

import com.neuro_immune_detector_core.domain_objects.NeuralNetworkPopulation;
import com.neuro_immune_detector_core.exceptions.NeuroImmuneDetectorException;
import com.neuro_immune_detector_core.policies.DistributionPolicy;
import com.neuro_immune_detector_core.repositories.FileRepository;

public interface NeuralNetworkPopulationTeacher {
	void initializeAndTeachAll(NeuralNetworkPopulation population, FileRepository infectedFiles, 
			FileRepository cleanFiles, DistributionPolicy distributionPolicy, double desiredError, 
			int iterationsLimit, double adjustmentCoefficient) 
					throws IOException, NeuroImmuneDetectorException;
}
