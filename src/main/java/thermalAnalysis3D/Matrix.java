package thermalAnalysis3D;

import java.util.Random;

public class Matrix {
    public static double[][][] generateRandomMatrix(int temp, int nX, int nY, int nZ) {
        double[][][] tInit = new double[nX][nY][nZ];
        for (int i = 0; i < nX; i++) {
            for (int j = 0; j < nY; j++) {
                for (int k = 0; k < nZ; k++) {
                    if (i == 0 || i == nX || j == 0 || j == nY || k == 0 || k == nZ) {
                        tInit[i][j][k] = temp;
                    } else {
                        tInit[i][j][k] = new Random().nextInt(10);
                    }
                }

            }
        }
        return tInit;
    }

    public static void print(double[][][] matrix) {
        for (int i = 0; i < matrix.length; ++i) {
            System.out.println("Слой №" + (i + 1));
            for (int j = 0; j < matrix[i].length; ++j) {
                for (int k = 0; k < matrix[i][j].length; ++k) {
                    if (k != matrix.length - 1) {
                        System.out.print("\t" + matrix[i][j][k]);
                    } else {
                        System.out.print("\t" + matrix[i][j][k]);
                        System.out.println();
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
