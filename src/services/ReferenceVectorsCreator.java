package services;

import java.io.IOException;
import java.util.Collection;

import domain_objects.ReferenceVectorContainer;

import exceptions.NeuroImmuneDetectorException;

import policies.DistributionPolicy;

import repositories.FileRepository;


public interface ReferenceVectorsCreator {
	Collection<ReferenceVectorContainer> createReferenceVectors(FileRepository infectedFiles, 
			FileRepository cleanFiles, int numberOfVectors, DistributionPolicy distributionPolicy,
			int vectorLength) throws IOException, NeuroImmuneDetectorException;
}
