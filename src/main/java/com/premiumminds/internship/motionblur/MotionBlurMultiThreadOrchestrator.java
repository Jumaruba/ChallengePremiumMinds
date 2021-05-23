package com.premiumminds.internship.motionblur;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * For a MultiThread approach, this class is responsible for calling other threads
 * and notify the requester that the process has ended.
 */
public class MotionBlurMultiThreadOrchestrator implements Callable {

    int numOfWorkers;
    int[][] matrix;

    public MotionBlurMultiThreadOrchestrator(int[][] matrix, int numOfWorkers) {
        int matrixSize = matrix.length * matrix[0].length;
        this.numOfWorkers = Math.min(numOfWorkers, matrixSize);
        this.matrix = matrix;
    }

    @Override
    public int[][] call() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(numOfWorkers);
        int[][] frameBuffer = Utils.deepCopyMatrix(matrix);

        for (int i = 0; i < numOfWorkers; i++) {
            executorService.submit(new MotionBlurProcess(matrix, frameBuffer, i, numOfWorkers));
        }

        executorService.awaitTermination(2, TimeUnit.SECONDS);
        return frameBuffer;
    }
}
