package structure;

// TODO: serializable
public class KohonenNeuron {
	private byte[] weights;

	public byte[] getWeights() {
		return weights;
	}
	
	public KohonenNeuron(int numberOfWeights) {
		weights = new byte[numberOfWeights];
	}
	
	public double calculateOutput(byte[] inputVector) {
		if (inputVector.length != weights.length) {
			throw new IllegalArgumentException("Incorrect inputVector dimension");
		}
		double sum = 0;
		for (int i = 0; i < inputVector.length; i++) {
			sum += Math.pow(inputVector[i] - weights[i], 2); 
		}
		return Math.sqrt(sum);
	}
}
