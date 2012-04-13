package vectors;

public class ReferenceVectorContainer {
	private byte[] referenceVector;
	private boolean infected;
	
	public ReferenceVectorContainer(byte[] referenceVector, boolean infected) {
		this.referenceVector = referenceVector;
		this.infected = infected;
	}
	
	public byte[] getReferenceVector() {
		return referenceVector;
	}
	
	public boolean isInfected() {
		return infected;
	}
}
