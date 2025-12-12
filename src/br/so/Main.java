import algorithms.FIFOStrategy;
import algorithms.LRUStrategy;
import algorithms.OptimalStrategy;
import algorithms.PageReplacementStrategy;
import algorithms.RandomStrategy;
import core.Simulator;
import model.SystemConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            if (!scanner.hasNextInt()) return;

            int physicalMemory = scanner.nextInt();
            int virtualMemory = scanner.nextInt();
            String architecture = scanner.next();
            int totalPages = scanner.nextInt();
            SystemConfig config = new SystemConfig(physicalMemory, virtualMemory, architecture, totalPages);

            System.out.println(config.getPageSize());
            System.out.println(config.getNumFrames());
            System.out.println(config.getMinSwapSize());
            System.out.println();

            Simulator simulator = new Simulator(config);

            int numSequences = scanner.nextInt();

            for (int i = 0; i < numSequences; i++) {
                int sequenceSize = scanner.nextInt();

                List<Integer> pages = new ArrayList<>();
                for (int j = 0; j < sequenceSize; j++) {
                    pages.add(scanner.nextInt());
                }
                List<PageReplacementStrategy> strategies = Arrays.asList(
                    new FIFOStrategy(),
                    new RandomStrategy(),
                    new LRUStrategy(),
                    new OptimalStrategy()
                );
                simulator.runSimulation(pages, strategies);

                System.out.println();
            }

        } catch (Exception e) {
            System.err.println("Erro durante a execução: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
