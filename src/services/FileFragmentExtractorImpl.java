package services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class FileFragmentExtractorImpl implements FileFragmentExtractor {
	
	private static interface InnerExtractor {
		void getFragments(FileChannel channel, ByteBuffer buffer, 
				Collection<byte[]> retCollection) throws IOException;
	}
	
	private Collection<byte[]> commonGetFragments(File file, int fragmentLength, 
			InnerExtractor extractor) throws IOException {
		if (fragmentLength <= 0) {
			throw new IllegalArgumentException("Invalid fragmentLength, must be > 0");
		}
		
		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream(file);
		} 
		catch (FileNotFoundException e) {
			throw new IllegalArgumentException("File doesn't exist", e);
		}
		try {
			FileChannel inputChannel = fileInputStream.getChannel();
			
			ByteBuffer buf = ByteBuffer.allocateDirect(fragmentLength);
			
			Collection<byte[]> retCollection = new ArrayList<byte[]>();
			
			extractor.getFragments(inputChannel, buf, retCollection);
			
			return Collections.unmodifiableCollection(retCollection);
		}
		finally {
			fileInputStream.close();
		}
	}
	
	private long getFileSize(File file) throws IOException {
		URL fileUrl = file.toURI().toURL();
		InputStream stream = null;
        try {
            stream = fileUrl.openStream();
            return stream.available();
        } finally {
            stream.close();
        }
	}
	
	private long getRandomValue(long limit) {
		return (long)Math.round(Math.random() * limit);
	}

	@Override
	public Collection<byte[]> getRandomFragments(final File file,
			final int numberOfFragments, final int fragmentLength) throws IOException {
		return commonGetFragments(file, fragmentLength, new InnerExtractor() {
			
			@Override
			public void getFragments(FileChannel channel, ByteBuffer buffer,
					Collection<byte[]> retCollection) throws IOException {
				long fileSize = getFileSize(file);
				
				if (fileSize < fragmentLength) {
					// TODO: throw some kind of (own?) exception
				}
				
				for (int i = 0; i < numberOfFragments; i++) {
					channel.position(getRandomValue(fileSize - fragmentLength));
					assert (channel.read(buffer) == fragmentLength);
					byte[] bytes = new byte[fragmentLength];
					buffer.get(bytes);
					retCollection.add(bytes);
					buffer.clear();
				}
			}
		});
	}

	@Override
	public Iterable<byte[]> getSequentialOverlappingFragments(File file, final int fragmentLength) 
			throws IOException  {
		
		return commonGetFragments(file, fragmentLength, new InnerExtractor() {
			
			@Override
			public void getFragments(FileChannel channel, ByteBuffer buffer,
					Collection<byte[]> retCollection) throws IOException {
				long length = 0;
		        while((length = channel.read(buffer)) == fragmentLength) {
		        	byte[] bytes = new byte[fragmentLength];
		        	buffer.get(bytes);
		        	retCollection.add(bytes);
		        	channel.position(channel.position() - length + 1);
		        	buffer.clear();
		        }
		        
		        if (length < fragmentLength && length > 0) {
		        	byte[] bytes = new byte[fragmentLength];
		        	buffer.get(bytes, 0, (int)length);
		        	retCollection.add(bytes);
		        }
			}
			
		});
	}
}
