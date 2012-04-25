package com.neuro_immune_detector_core.services;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import com.neuro_immune_detector_core.domain_objects.NeuralNetwork;
import com.neuro_immune_detector_core.policies.InfectedPolicy;




public class FileCheckerImpl implements FileChecker {
	
	private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	private final InfectedPolicy infectedPolicy;
	private final FileFragmentExtractor extractor = new FileFragmentExtractorImpl();
	
	public FileCheckerImpl(InfectedPolicy infectedPolicy) {
		this.infectedPolicy = infectedPolicy;
	}

	@Override
	public Boolean isFileInfected(File file, NeuralNetwork neuralNetwork) throws IOException {
		neuralNetwork.resetAccumulators();
		
		for (byte[] fragment : extractor.getSequentialOverlappingFragments(file, neuralNetwork.getNumberOfInputs())) {
			neuralNetwork.processVector(fragment);
		}
		
		Boolean isInfected = infectedPolicy.isInfected(neuralNetwork);
		
		logger.info(String.format("check result: %s", isInfected == null ? 
				"undetermined" : isInfected.booleanValue() ? "infected" : "clean"));
		
		return isInfected;
	}

}
