package thermalAnalysis3D;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int temperature = 10;
        int iterations = 40000;
        int nodes = 10;
        int parallel = 3;

        double[][][] initialMatrix = Matrix.generateRandomMatrix(temperature, nodes, nodes, nodes);
        Solver solver = new Solver(parallel);
        System.out.println("Number of nodes: " + nodes);
        System.out.println("Number of threads: " + parallel);

        long startTime = System.currentTimeMillis();
        Solver.calculate(initialMatrix, nodes, nodes, nodes, iterations, temperature);
        long endTime = System.currentTimeMillis();
        System.out.printf("Working time = %d milliseconds%n", endTime - startTime);
        Matrix.print(initialMatrix);
    }
}