package com.neuro_immune_detector_core.exceptions;

public class NeuroImmuneDetectorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -630318308992779590L;
	
	public NeuroImmuneDetectorException() {
		
	}
	
	public NeuroImmuneDetectorException(String message) {
		super(message);
	}
	
	public NeuroImmuneDetectorException(Throwable cause) {
		super(cause);
	}
	
	public NeuroImmuneDetectorException(String message, Throwable cause) {
		super(message, cause);
	}

}
