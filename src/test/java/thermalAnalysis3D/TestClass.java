package thermalAnalysis3D;

import org.junit.Assert;
import org.junit.Test;

public class TestClass {
    @Test
    public void Test() throws InterruptedException {
        int temp = 10;
        int iterations = 40000;
        int nodes = 20;
        int parallel = 6;
        Solver solver = new Solver(parallel);
        double[][][] tInit = Matrix.generateRandomMatrix(temp, nodes, nodes, nodes);
        double sum = 0;
        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                for (int k = 0; k < nodes; k++) {
                    sum += tInit[i][j][k];
                }
            }
        }

        solver.calculate(tInit, nodes, nodes, nodes, iterations, temp);
        double sumCalc = 0;
        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                for (int k = 0; k < nodes; k++) {
                    sumCalc += tInit[i][j][k];
                }
            }
        }
        Matrix.print(tInit);
        Assert.assertEquals(1.00, sum / sumCalc, 0.1);
    }
}
