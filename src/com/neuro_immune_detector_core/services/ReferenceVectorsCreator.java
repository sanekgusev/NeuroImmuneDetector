package com.neuro_immune_detector_core.services;

import java.io.IOException;
import java.util.Collection;

import com.neuro_immune_detector_core.domain_objects.ReferenceVectorContainer;
import com.neuro_immune_detector_core.exceptions.NeuroImmuneDetectorException;
import com.neuro_immune_detector_core.policies.DistributionPolicy;
import com.neuro_immune_detector_core.repositories.FileRepository;






public interface ReferenceVectorsCreator {
	Collection<ReferenceVectorContainer> createReferenceVectors(FileRepository infectedFiles, 
			FileRepository cleanFiles, int numberOfVectors, DistributionPolicy distributionPolicy,
			int vectorLength) throws IOException, NeuroImmuneDetectorException;
}
