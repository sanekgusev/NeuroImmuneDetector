package services;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public interface FileFragmentExtractor {
	// TODO: maybe return Iterable<byte[]>?
	// NOTE: random fragments may overlap
	Collection<byte[]> getRandomFragments(File file, int numberOfFragments, 
			int fragmentLength)
			throws IOException;
	
	Iterable<byte[]> getSequentialOverlappingFragments(File file, int fragmentLength) 
			throws IOException;
}
