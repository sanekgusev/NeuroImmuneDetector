package repositories;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class FileRepositoryImpl implements FileRepository {
	
	private List<File> files;
	
	public FileRepositoryImpl(Collection<File> files) {
		this.files = new ArrayList<File>(files);
	}
	
	public FileRepositoryImpl(String directoryPath) {
		File[] files = new File(directoryPath).listFiles();
		if (files == null) {
			throw new IllegalArgumentException("Invalid directoryPath");
		}
		this.files = Arrays.asList(files);
	}

	@Override
	public Iterator<File> iterator() {
		return files.iterator();
	}

	@Override
	public File get(int index) {
		return files.get(index);
	}

	@Override
	public int size() {
		return files.size();
	}

}
