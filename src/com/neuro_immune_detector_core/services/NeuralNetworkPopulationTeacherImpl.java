package com.neuro_immune_detector_core.services;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.neuro_immune_detector_core.domain_objects.NeuralNetwork;
import com.neuro_immune_detector_core.domain_objects.NeuralNetworkPopulation;
import com.neuro_immune_detector_core.domain_objects.ReferenceVectorContainer;
import com.neuro_immune_detector_core.exceptions.NeuroImmuneDetectorException;
import com.neuro_immune_detector_core.policies.DistributionPolicy;
import com.neuro_immune_detector_core.policies.NumberOfVectorsPolicy;
import com.neuro_immune_detector_core.policies.NumberOfVectorsPolicyImpl;
import com.neuro_immune_detector_core.repositories.FileRepository;

public class NeuralNetworkPopulationTeacherImpl implements
		NeuralNetworkPopulationTeacher {
	
//	private static final int numberOfProcessors = Runtime.getRuntime().availableProcessors();
	
	private ReferenceVectorsCreator vectorsCreator;
	private NeuralNetworkTeacher teacher;
	private NumberOfVectorsPolicy numberOfVectorsPolicy;
	
	public NeuralNetworkPopulationTeacherImpl() {
		vectorsCreator = new ReferenceVectorsCreatorImpl();
		teacher = new NeuralNetworkTeacherImpl();
		numberOfVectorsPolicy = new NumberOfVectorsPolicyImpl();
	}

	@Override
	public void initializeAndTeachAll(NeuralNetworkPopulation population,
			final FileRepository infectedFiles, final FileRepository cleanFiles,
			final DistributionPolicy distributionPolicy, final double desiredError,
			final int iterationsLimit, final double adjustmentCoefficient) 
					throws IOException, NeuroImmuneDetectorException {
		
		ExecutorService executor = Executors.newCachedThreadPool();
    	List<Future<?>> futures = new LinkedList<Future<?>>();
		for (final NeuralNetwork network : population) {
			futures.add(executor.submit(new Runnable() {
				@Override
				public void run() {
					Collection<ReferenceVectorContainer> referenceVectors;
					try {
						referenceVectors = vectorsCreator.createReferenceVectors(infectedFiles, 
								cleanFiles, numberOfVectorsPolicy.getRequiredNumberOfVectors(network),
								distributionPolicy, network.getNumberOfInputs());
					} catch (IOException e) {
						throw new RuntimeException(e);
					} catch (NeuroImmuneDetectorException e) {
						throw new RuntimeException(e);
					}
					teacher.initializeAndTeach(network, referenceVectors, desiredError, 
							iterationsLimit, adjustmentCoefficient);
				}
			}));
		}
		for (Future<?> f : futures) {
            try {
                f.get();
            } catch (InterruptedException e) {
            } catch (ExecutionException e) {
            	Throwable cause = e.getCause();
            	if (cause instanceof RuntimeException) {
            		Throwable innerCause = cause.getCause();
            		if (innerCause instanceof IOException) {
            			throw (IOException)innerCause;
            		}
            		else if (innerCause instanceof NeuroImmuneDetectorException) {
            			throw (NeuroImmuneDetectorException)innerCause;
            		}
            	}
            }
        }
        executor.shutdown();
	}
}
