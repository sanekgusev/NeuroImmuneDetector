package com.neuro_immune_detector_core.services;

import java.io.File;
import java.io.IOException;

import com.neuro_immune_detector_core.exceptions.NeuroImmuneDetectorException;


public interface FileFragmentExtractor {
	// NOTE: random fragments may overlap
	Iterable<byte[]> getRandomFragments(File file, int numberOfFragments, 
			int fragmentLength)
			throws IOException, NeuroImmuneDetectorException;
	
	Iterable<byte[]> getSequentialOverlappingFragments(File file, int fragmentLength) 
			throws IOException;
}
