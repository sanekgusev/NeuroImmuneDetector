package policies;

public interface KohonenNeuronsDistributionPolicy {
	int getNumberOfPositiveNeurons(int totalNeurons);
	int getNumberOfNegativeNeurons(int totalNeurons);
}
