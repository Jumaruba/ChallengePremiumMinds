package com.premiumminds.internship.motionblur;

import java.util.concurrent.Callable;

/**
 * This class is responsible to calculate the Motion Blur matrix of a bi-dimensional array.
 * It can be a thread of multiple others.
 */
public class MotionBlurProcess implements Callable<int[][]> {

    int[][] matrix;
    int workerId = 0;
    int numOfWorkers = 1;
    int[][] MotionBlurMatrix;

    public MotionBlurProcess(int[][] matrix, int[][] frameBuffer, int workerId, int numOfWorkers) {
        this.matrix = matrix;
        this.workerId = workerId;
        this.numOfWorkers = numOfWorkers;
        this.MotionBlurMatrix = frameBuffer;
    }

    public MotionBlurProcess(int[][] matrix, int[][] frameBuffer) {
        this.matrix = matrix;
        this.MotionBlurMatrix = frameBuffer;
    }

    /**
     * Given the number of workers and the work ID, calculates some position of the Motion Blur matrix.
     */
    public int[][] call() {

        int matrixWidth = matrix[0].length;
        int matrixSize = matrix.length * matrixWidth;
        int numElementsToCompute = (int) Math.ceil((float) matrixSize / numOfWorkers);
        int startPosition = numElementsToCompute * workerId;
        int endPosition = Math.min(startPosition + numElementsToCompute, matrixSize);

        for (int i = startPosition; i < endPosition; i++) {
            int x = getX(i, matrixWidth);
            int y = getY(i, matrixWidth);
            MotionBlurMatrix[y][x] = getMotionResult(matrix, x, y);
        }

        return MotionBlurMatrix;
    }


    /**
     * Gets the element position in the Y axis given it's linear position.
     *
     * @param linearPos Linear position of the element.
     * @param width     Matrix width.
     * @return Position in the X axis.
     */
    private int getX(int linearPos, int width) {
        return linearPos % width;
    }

    /**
     * Gets the element position in the Y axis given it's linear position.
     *
     * @param linearPos Linear position of the element.
     * @param width     Matrix width.
     * @return Position in the Y axis.
     */
    private int getY(int linearPos, int width) {
        return linearPos / width;
    }

    /**
     * This method applies the Motion Blur algorithm for a cell in the matrix.
     *
     * @param matrix
     * @param x      Cell position in the X axis.
     * @param y      Cell position in the Y axis.
     * @return Value of the cell in the Motion Blur matrix.
     */
    public int getMotionResult(int[][] matrix, int x, int y) {
        int result = matrix[y][x];
        int numElements = 1;

        if (x > 0) {
            result += matrix[y][x - 1];
            numElements++;
        }
        if (y > 0) {
            result += matrix[y - 1][x];
            numElements++;
        }
        if (y < matrix.length - 1) {
            result += matrix[y + 1][x];
            numElements++;
        }

        return Math.round((float) result / numElements);
    }


}
