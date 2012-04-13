package policies;

import structure.NeuralNetwork;

public interface InfectedPolicy {
	Boolean isInfected(NeuralNetwork network);
}
