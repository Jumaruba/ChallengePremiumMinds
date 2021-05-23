package com.premiumminds.internship.motionblur;

import java.util.concurrent.Future;

/**
  * Created by aamado on 05-05-2021.
  */
interface MotionBlurFactory {
  /**
    * Method to start processing the data
    * @param data matrix of integers
    * @param numberOfWorkers number of threads that should work in parallel
    * @return matrix of integers
    */
  public Future<int[][]> run(int[][] data,int numberOfWorkers);
}
