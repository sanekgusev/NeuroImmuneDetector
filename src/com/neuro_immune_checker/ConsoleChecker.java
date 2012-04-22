package com.neuro_immune_checker;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;

import com.neuro_immune_detector_core.domain_objects.*;
import com.neuro_immune_detector_core.exceptions.*;
import com.neuro_immune_detector_core.io.*;
import com.neuro_immune_detector_core.policies.*;
import com.neuro_immune_detector_core.services.*;

public class ConsoleChecker {
	
	private static final double INFECTED_THRESHOLD = 0.2;
	private static final double CLEAN_THRESOLD = 0.8;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Usage: neuro_immune_checker path/to/population_file path/to/file_to_check");
			return;
		}

		File populationFile = new File(args[0]);
		
		NeuralNetworkPopulation population = null;
		
		try {
			population = NeuralNetworkPopulationPersister.loadPopulation(populationFile);
		} catch (NeuroImmuneDetectorException e) {
			System.err.format("ERROR: %s.%n", e.getMessage());
			return;
		} catch (IOException e) {
			System.err.format("ERROR: %s.%n", e.getMessage());
			return;
		}
		
		File fileToCheck = new File(args[1]);
		
		InfectedPolicy infectedPolicy = new InfectedPolicyImpl(INFECTED_THRESHOLD, 
				CLEAN_THRESOLD);
		FileChecker checker = new FileCheckerImpl(infectedPolicy);
		
		int infectedCount = 0;
		int cleanCount = 0;
		
		try {
			for (NeuralNetwork network : population) {
				Boolean infected = checker.isFileInfected(fileToCheck, network);
				if (infected != null) {
					if (infected.booleanValue()) {
						infectedCount++;
					}
					else {
						cleanCount++;
					}
				}
			}
		} catch (IOException e) {
			System.err.format("ERROR: %s.%n", e.getMessage());
			e.printStackTrace();
		}
		
		if (infectedCount == 0 && cleanCount == 0) {
			System.out.println("Cannot determine whether this file is infected or not.");
			return;
		}
		
		if (infectedCount == 0) {
			System.out.println("File is clean.");
			return;
		}
		
		if (cleanCount == 0) {
			System.out.println("File is infected.");
			return;
		}
		
		NumberFormat percentFormatter = NumberFormat.getPercentInstance();
		System.out.format("Check results for file: %s infected, %s clean.%n", 
				percentFormatter.format((double)infectedCount / population.size()),
				percentFormatter.format((double)cleanCount / population.size()));
	}

}
