package com.neuro_immune_teacher;

import java.io.File;
import java.io.IOException;

import com.neuro_immune_detector_core.domain_objects.NeuralNetworkPopulation;
import com.neuro_immune_detector_core.exceptions.NeuroImmuneDetectorException;
import com.neuro_immune_detector_core.factories.NeuralNetworkPopulationFactory;
import com.neuro_immune_detector_core.io.NeuralNetworkPopulationPersister;
import com.neuro_immune_detector_core.policies.DistributionPolicy;
import com.neuro_immune_detector_core.policies.DistributionPolicyImpl;
import com.neuro_immune_detector_core.repositories.FileRepository;
import com.neuro_immune_detector_core.repositories.FileRepositoryImpl;
import com.neuro_immune_detector_core.services.NeuralNetworkPopulationTeacher;
import com.neuro_immune_detector_core.services.NeuralNetworkPopulationTeacherImpl;
import com.neuro_immune_detector_core.services.NeuralNetworkPopulationVerifier;
import com.neuro_immune_detector_core.services.NeuralNetworkPopulationVerifierImpl;

public class ConsoleTeacher {
	
	private static final String LEARN_CLEAN_PATH = "files/learn/clean";
	private static final String LEARN_INFECTED_PATH = "files/learn/infected";
	private static final String TEST_CLEAN_PATH = "files/test/clean";
	private static final String TEST_INFECTED_PATH = "files/test/infected";
	
	private static final int NUMBER_OF_NETWORKS = 10;
	private static final int NUMBER_OF_INPUTS = 512;
	private static final int NUMBER_OF_HIDDEN_NEURONS = 200;
	private static final double POSITIVE_ELEMENTS_THRESHOLD = 0.5;
	private static final double DESIRED_TEACH_ERROR = 0;
	private static final int ITERATIONS_LIMIT = 100;
	private static final double ADJUSTMENT_COEFFICIENT = 0.01;
	private static final double DESIRED_VERIFICATION_ERROR = 15;
	private static final int NUMBER_OF_VERIFICATION_VECTORS = 50;
	
	private FileRepository learnCleanRepository;
	private FileRepository learnInfectedRepository;
	private FileRepository testCleanRepository;
	private FileRepository testInfectedRepository;
	
	private NeuralNetworkPopulation population;
	private DistributionPolicy distributionPolicy;
	
	private NeuralNetworkPopulationTeacher populationTeacher;
	private NeuralNetworkPopulationVerifier populationVerifier;
	
	private ConsoleTeacher() {
		learnCleanRepository = new FileRepositoryImpl(LEARN_CLEAN_PATH);
		learnInfectedRepository = new FileRepositoryImpl(LEARN_INFECTED_PATH);
		testCleanRepository = new FileRepositoryImpl(TEST_CLEAN_PATH);
		testInfectedRepository = new FileRepositoryImpl(TEST_INFECTED_PATH);
		distributionPolicy = new DistributionPolicyImpl(POSITIVE_ELEMENTS_THRESHOLD);
		populationTeacher = new NeuralNetworkPopulationTeacherImpl();
		populationVerifier = new NeuralNetworkPopulationVerifierImpl();
	}
	
	private void recreatePopulation() {
		population = 
				NeuralNetworkPopulationFactory.createNeuralNetworkPopulation(NUMBER_OF_NETWORKS, 
						NUMBER_OF_INPUTS, NUMBER_OF_HIDDEN_NEURONS, distributionPolicy);
	}
	
	private void initializeAndTeachPopulation() 
			throws IOException, NeuroImmuneDetectorException {
		populationTeacher.initializeAndTeachAll(population,
				learnInfectedRepository, learnCleanRepository, 
				distributionPolicy, DESIRED_TEACH_ERROR, ITERATIONS_LIMIT,
				ADJUSTMENT_COEFFICIENT);
	}
	
	private void verifyPopulation() 
			throws IOException, NeuroImmuneDetectorException {
		populationVerifier.verifyAndFilter(population, 
				testInfectedRepository, testCleanRepository, 
				NUMBER_OF_VERIFICATION_VECTORS, distributionPolicy, 
				DESIRED_VERIFICATION_ERROR);
	}
	
	private void savePopulation(String filename) 
			throws IOException, NeuroImmuneDetectorException {
		File file = new File(filename);
		if (!file.createNewFile()) {
			throw new NeuroImmuneDetectorException("The specified file already exists");
		}
		NeuralNetworkPopulationPersister.savePopulation(population, file);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		if (args.length != 1) {
			System.out.println("USAGE: ConsoleTeacher.jar path/to/population_file");
			return;
		}
		
		ConsoleTeacher teacher = new ConsoleTeacher();
		teacher.recreatePopulation();
		try {
			teacher.initializeAndTeachPopulation();
			teacher.verifyPopulation();
			teacher.savePopulation(args[0]);
		} catch (IOException e) {
			System.err.format("ERROR: %s.%n", e.getMessage());
			e.printStackTrace();
		} catch (NeuroImmuneDetectorException e) {
			System.err.format("ERROR: %s.%n", e.getMessage());
			e.printStackTrace();
		}
	}

}
