package services;

import java.io.File;
import java.io.IOException;

import domain_objects.NeuralNetwork;

import policies.InfectedPolicy;


public class FileCheckerImpl implements FileChecker {
	
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
		
		return infectedPolicy.isInfected(neuralNetwork);
	}

}
