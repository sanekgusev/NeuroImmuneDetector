package com.neuro_immune_detector_core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.neuro_immune_detector_core.domain_objects.NeuralNetworkPopulation;
import com.neuro_immune_detector_core.domain_objects.NeuralNetworkPopulationImpl;
import com.neuro_immune_detector_core.exceptions.NeuroImmuneDetectorException;


public class NeuralNetworkPopulationPersister {
	public static NeuralNetworkPopulation loadPopulation(File file)
		throws NeuroImmuneDetectorException, IOException {
		ObjectInputStream inputStream = null;
		try {
			inputStream = new ObjectInputStream(new FileInputStream(file));
			Object object = null;
			if ((object = inputStream.readObject()) != null) {
				if (object instanceof NeuralNetworkPopulationImpl) {
					return (NeuralNetworkPopulation)object;
				}
				throw new NeuroImmuneDetectorException("File doesn't contain neural network populations");
			}
			throw new NeuroImmuneDetectorException("File has no neural network populations");
		} catch (ClassNotFoundException e) {
			throw new NeuroImmuneDetectorException("File doesn't contain neural network populations", e);
		}
		finally {
			try {
				inputStream.close();
			}
			catch (IOException e) {
				e.printStackTrace();
				// so what?
			}
		}
	}
	
	public static void savePopulation(NeuralNetworkPopulation population, File file) 
		throws IOException {
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(file));
			outputStream.writeObject(population);
		}
		finally {
			try {
				outputStream.close();
			}
			catch (Exception e) {
				e.printStackTrace();
				// this is hopeless
			}
		}
	}
}
