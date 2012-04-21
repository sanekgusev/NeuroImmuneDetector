package com.neuro_immune_detector_core.services;

import java.io.File;
import java.util.Collection;

import com.neuro_immune_detector_core.repositories.FileRepository;


public interface FileExtractor {
	// TODO: maybe return Iterable<File> ?
	Collection<File> getRandomFiles(FileRepository repository, int numberOfFiles);
}
