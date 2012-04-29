package com.neuro_immune_detector_core.services;

import java.io.File;

import com.neuro_immune_detector_core.repositories.FileRepository;


public interface FileExtractor {
	Iterable<File> getRandomFiles(FileRepository repository, int numberOfFiles);
}
