package services;

import java.io.IOException;
import java.util.Collection;

import exceptions.NeuroImmuneDetectorException;

import policies.DistributionPolicy;

import repositories.FileRepository;

import vectors.ReferenceVectorContainer;

public interface ReferenceVectorsCreator {
	Collection<ReferenceVectorContainer> createReferenceVectors(FileRepository infectedFiles, 
			FileRepository cleanFiles, int numberOfVectors, DistributionPolicy distributionPolicy,
			int vectorLength) throws IOException, NeuroImmuneDetectorException;
}
