package com.neuro_immune_detector_core.services;

import java.io.IOException;
import java.util.Collection;

import com.neuro_immune_detector_core.domain_objects.NeuralNetwork;
import com.neuro_immune_detector_core.domain_objects.NeuralNetworkPopulation;
import com.neuro_immune_detector_core.domain_objects.ReferenceVectorContainer;
import com.neuro_immune_detector_core.exceptions.NeuroImmuneDetectorException;
import com.neuro_immune_detector_core.policies.DistributionPolicy;
import com.neuro_immune_detector_core.policies.NumberOfVectorsPolicy;
import com.neuro_immune_detector_core.policies.NumberOfVectorsPolicyImpl;
import com.neuro_immune_detector_core.repositories.FileRepository;

public class NeuralNetworkPopulationTeacherImpl implements
		NeuralNetworkPopulationTeacher {
	
	private ReferenceVectorsCreator vectorsCreator;
	private NeuralNetworkTeacher teacher;
	private NumberOfVectorsPolicy numberOfVectorsPolicy;
	
	public NeuralNetworkPopulationTeacherImpl() {
		vectorsCreator = new ReferenceVectorsCreatorImpl();
		teacher = new NeuralNetworkTeacherImpl();
		numberOfVectorsPolicy = new NumberOfVectorsPolicyImpl();
	}

	@Override
	public void initializeAndTeachAll(NeuralNetworkPopulation population,
			FileRepository infectedFiles, FileRepository cleanFiles,
			DistributionPolicy distributionPolicy, double desiredError,
			int iterationsLimit, double adjustmentCoefficient) 
					throws IOException, NeuroImmuneDetectorException {
		
		for (NeuralNetwork network : population) {
			Collection<ReferenceVectorContainer> referenceVectors = 
					vectorsCreator.createReferenceVectors(infectedFiles, 
							cleanFiles, numberOfVectorsPolicy.getRequiredNumberOfVectors(network),
							distributionPolicy, network.getNumberOfInputs());
			teacher.teach(network, referenceVectors, desiredError, 
					iterationsLimit, adjustmentCoefficient);
		}
	}
	
}
