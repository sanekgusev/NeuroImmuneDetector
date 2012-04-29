package com.neuro_immune_detector_core.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import java.io.File;
import java.io.IOException;

import com.neuro_immune_detector_core.domain_objects.ReferenceVectorContainer;
import com.neuro_immune_detector_core.exceptions.NeuroImmuneDetectorException;
import com.neuro_immune_detector_core.policies.DistributionPolicy;
import com.neuro_immune_detector_core.repositories.FileRepository;




public class ReferenceVectorsCreatorImpl implements ReferenceVectorsCreator {

	@Override
	public Iterable<ReferenceVectorContainer> createReferenceVectors(
			FileRepository infectedFiles, FileRepository cleanFiles,
			int numberOfVectors, DistributionPolicy distributionPolicy,
			int vectorLength) throws IOException, NeuroImmuneDetectorException {
		int numberOfPositiveVectors = distributionPolicy.getNumberOfPositiveElements(numberOfVectors);
		int numberOfNegativeVectors = numberOfVectors - numberOfPositiveVectors;
		
		int numberOfInfectedFiles = infectedFiles.size();
		int numberOfCleanFiles = cleanFiles.size();
		
		int minFragmentsPerInfectedFile = (int)Math.ceil((double)numberOfPositiveVectors / numberOfInfectedFiles);
		int minFragmentsPerCleanFile = (int)Math.ceil((double)numberOfNegativeVectors / numberOfCleanFiles);
		
		int fragmentsPerFile = Math.max(minFragmentsPerCleanFile, minFragmentsPerInfectedFile);
		
		int infectedFilesToSelect = (int)Math.ceil((double)numberOfPositiveVectors / fragmentsPerFile);
		int cleanFilesToSelect = (int)Math.ceil((double)numberOfNegativeVectors / fragmentsPerFile);
		
		Collection<ReferenceVectorContainer> referenceVectors = new ArrayList<ReferenceVectorContainer>();
		
		FileExtractor fileExtractor = new FileExtractorImpl();
		FileFragmentExtractor fileFragmentExtractor = new FileFragmentExtractorImpl();
		
		int addedFragments = 0;
		for (File file : fileExtractor.getRandomFiles(infectedFiles, infectedFilesToSelect)) {
			for (byte[] fragment : fileFragmentExtractor.getRandomFragments(file, fragmentsPerFile, vectorLength)) {
				referenceVectors.add(new ReferenceVectorContainer(fragment, true));
				if (++addedFragments == numberOfPositiveVectors) {
					break;
				}
			}
			if (addedFragments == numberOfPositiveVectors) {
				break;
			}
		}
		
		addedFragments = 0;
		
		for (File file : fileExtractor.getRandomFiles(cleanFiles, cleanFilesToSelect)) {
			for (byte[] fragment : fileFragmentExtractor.getRandomFragments(file, fragmentsPerFile, vectorLength)) {
				referenceVectors.add(new ReferenceVectorContainer(fragment, false));
				if (++addedFragments == numberOfNegativeVectors) {
					break;
				}
			}
			if (addedFragments == numberOfNegativeVectors) {
				break;
			}
		}
		
		return Collections.unmodifiableCollection(referenceVectors);
	}

}
