package com.neuro_immune_detector_core.helpers;

// thread-safe
public class RandomHelper {
	
	public static long getRandomValue(long limit) {
		return (long)Math.round(Math.random() * limit);
	}
	
	public static int getRandomValue(int limit) {
		return (int)Math.round(Math.random() * limit);
	}
}
