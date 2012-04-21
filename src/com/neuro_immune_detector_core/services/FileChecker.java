package com.neuro_immune_detector_core.services;

import java.io.File;
import java.io.IOException;

import com.neuro_immune_detector_core.domain_objects.NeuralNetwork;



public interface FileChecker {
	public Boolean isFileInfected(File file, NeuralNetwork neuralNetwork) throws IOException;
}
