package thermalAnalysis3D;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int temperature = 10;
        int iterations = 40000;

        int nodes[] = {4, 16};
        int threads[] = {4, 16, 256, 1024};

        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < threads.length; j++) {
                double[][][] initialMatrix = Matrix.generateRandomMatrix(temperature, nodes[i], nodes[i], nodes[i]);
                Solver solver = new Solver(threads[j]);
                System.out.println("Number of nodes: " + nodes[i]);
                System.out.println("Number of threads: " + threads[j]);

                long startTime = System.currentTimeMillis();
                Solver.calculate(initialMatrix, nodes[i], nodes[i], nodes[i], iterations, temperature);
                long endTime = System.currentTimeMillis();
                System.out.printf("Working time = %d milliseconds%n", endTime - startTime);
            }
        }

    }
}