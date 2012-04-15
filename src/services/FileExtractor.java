package services;

import java.io.File;
import java.util.Collection;

import repositories.FileRepository;

public interface FileExtractor {
	// TODO: maybe return Iterable<File> ?
	Collection<File> getRandomFiles(FileRepository repository, int numberOfFiles);
}
