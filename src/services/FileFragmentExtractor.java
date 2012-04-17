package services;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import exceptions.NeuroImmuneDetectorException;

public interface FileFragmentExtractor {
	// TODO: maybe return Iterable<byte[]>?
	// NOTE: random fragments may overlap
	Collection<byte[]> getRandomFragments(File file, int numberOfFragments, 
			int fragmentLength)
			throws IOException, NeuroImmuneDetectorException;
	
	Iterable<byte[]> getSequentialOverlappingFragments(File file, int fragmentLength) 
			throws IOException;
}
