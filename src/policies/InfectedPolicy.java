package policies;

import domain_objects.NeuralNetwork;

public interface InfectedPolicy {
	Boolean isInfected(NeuralNetwork network);
}
