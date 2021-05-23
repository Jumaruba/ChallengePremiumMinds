package com.premiumminds.internship.motionblur;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Created by aamado on 05-05-2021.
 */
@RunWith(JUnit4.class)
public class MotionBlurTest {

  /**
   * The corresponding implementations to test.
   *
   * If you want, you can make others :)
   *
   */
  public MotionBlurTest() {
  };

  private int M1result[][] = { { 3, 3, 4 }, { 4, 5, 6 }, { 6, 7, 8 } };
  private int Edge1result[][] = {{}, {}};
  private int Edge2result[][] = null;

  @Test
  public void MotionBlurSingleThreadE1() throws ExecutionException, InterruptedException, TimeoutException {
    Future<int[][]> step1 = new MotionBlurSingleThread().run(MatrixData.Edge1, 1);
    int[][] result = step1.get(10, TimeUnit.SECONDS);
    assertTrue(Arrays.deepEquals(result, Edge1result));
    printHelper(result);
  }

  @Test
  public void MotionBlurSingleThreadE2() {
    Future<int[][]> step1 = new MotionBlurSingleThread().run(MatrixData.Edge2, 1);
    assertNull(step1);
  }

  @Test
  public void MotionBlurSingleThreadM1() throws InterruptedException, ExecutionException, TimeoutException {
    Future<int[][]> step1 = new MotionBlurSingleThread().run(MatrixData.M1, 1);
    int[][] result = step1.get(10, TimeUnit.SECONDS);
    assertTrue(Arrays.deepEquals(result, M1result));
    printHelper(result);
  }



/*
  @Test
  public void MotionBlurSingleThreadM1With5WorkersTest()
      throws InterruptedException, ExecutionException, TimeoutException {
    Future<int[][]> step1 = new MotionBlurMultiThread().run(MatrixData.M1, 5);
    int[][] result = step1.get(10, TimeUnit.SECONDS);
    assertTrue(Arrays.deepEquals(result, M1result));
  }*/

  @Test
  public void MotionBlurMultiThreadM1With5WorkersTest()
      throws InterruptedException, ExecutionException, TimeoutException {
    Future<int[][]> step1 = new MotionBlurMultiThread().run(MatrixData.M1, 5);
    int[][] result = step1.get(10, TimeUnit.SECONDS);
    assertTrue(Arrays.deepEquals(result, M1result));
    printHelper(result);
  }

  @Test
  public void MotionBlurMultiThreadM1With6WorkersTest()
          throws InterruptedException, ExecutionException, TimeoutException {
    Future<int[][]> step1 = new MotionBlurMultiThread().run(MatrixData.M1, 6);
    int[][] result = step1.get(10, TimeUnit.SECONDS);
    assertTrue(Arrays.deepEquals(result, M1result));
    printHelper(result);
  }

  @Test
  public void MotionBlurMultiThreadM1With100WorkersTest()
          throws InterruptedException, ExecutionException, TimeoutException {
    Future<int[][]> step1 = new MotionBlurMultiThread().run(MatrixData.M1, 100);
    int[][] result = step1.get(10, TimeUnit.SECONDS);
    assertTrue(Arrays.deepEquals(result, M1result));
    printHelper(result);
  }

  @Test
  public void MotionBlurMultiThreadM3With4WorkersTest()
          throws InterruptedException, ExecutionException, TimeoutException {
    Future<int[][]> step1 = new MotionBlurMultiThread().run(MatrixData.M3, 4);
    int[][] result = step1.get(10, TimeUnit.SECONDS);
    printHelper(result);
  }
  

  private void printHelper(int[][] matrix) {
    for (int l = 0; l < matrix.length; l++) {
      for (int c = 0; c < matrix[0].length; c++) {
        System.out.print(matrix[l][c] + " ");
      }
      System.out.println(" ");
    }
  }

}