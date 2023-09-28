package thermalAnalysis3D;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int temperature = 22;
        int iterations = 40000;
        int nodes = 10;
        int maxParallelism = 3;

        double[][][] initialMatrix = Matrix.generateRandomMatrix(temperature, nodes, nodes, nodes);

        for (int i = 1; i < maxParallelism; i++) {
            Solver solver = new Solver(i);
            Matrix.print(initialMatrix);
            System.out.println("Number of nodes: " + nodes);
            System.out.println("Number of threads: " + i);

            long startTime = System.currentTimeMillis();
            Solver.calculate(initialMatrix, nodes, nodes, nodes, iterations, temperature);
            long endTime = System.currentTimeMillis();

            System.out.printf("Working time = %d milliseconds%n", endTime - startTime);
        }
    }
}