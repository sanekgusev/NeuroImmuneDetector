package com.neuro_immune_detector_core.repositories;

import java.io.File;

public interface FileRepository extends Iterable<File> {
	File get(int index);
	int size();
}
