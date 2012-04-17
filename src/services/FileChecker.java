package services;

import java.io.File;
import java.io.IOException;

import structure.NeuralNetwork;

public interface FileChecker {
	public Boolean isFileInfected(File file, NeuralNetwork neuralNetwork) throws IOException;
}
