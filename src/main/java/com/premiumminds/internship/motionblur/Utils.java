package com.premiumminds.internship.motionblur;

public class Utils {

    public static int[][] deepCopyMatrix(int[][] matrix){
        if (matrix == null){
            return null;
        }

        int[][] result = matrix.clone();
        for (int i = 0 ; i  < matrix.length; i++){
            result[i] = matrix[i].clone();
        }
        return result;
    }
}
