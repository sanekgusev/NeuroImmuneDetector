package services;

import java.util.Collection;

import policies.DistributionPolicy;
import repositories.FileRepository;
import vectors.ReferenceVectorContainer;

// TODO: implement
public class ReferenceVectorsCreatorImpl implements ReferenceVectorsCreator {

	@Override
	public Collection<ReferenceVectorContainer> createReferenceVectors(
			FileRepository infectedFiles, FileRepository cleanFiles,
			int numberOfVectors, DistributionPolicy distributionPolicy,
			int vectorLength) {
		// TODO Auto-generated method stub
		return null;
	}

}
