package core;

import algorithms.PageReplacementStrategy;
import model.SystemConfig;
import java.util.List;

public class Simulator {

    private MemoryManager memoryManager;
    private SystemConfig config;

    public Simulator(SystemConfig config) {
        this.config = config;
        this.memoryManager = new MemoryManager(config.getNumFrames());
    }

    public void runSimulation(List<Integer> requestSequence, List<PageReplacementStrategy> strategies) {
        System.out.println("Sequencia de Requisições: " + requestSequence);
        for (PageReplacementStrategy strategy : strategies) {
            memoryManager.reset();
            strategy.reset();
            long startTime = System.nanoTime();

            for (int i = 0; i < requestSequence.size(); i++) {
                int pageId = requestSequence.get(i);

                List<Integer> futureRequests = (i < requestSequence.size() - 1)
                                             ? requestSequence.subList(i + 1, requestSequence.size())
                                             : List.of();

                memoryManager.accessPage(pageId, strategy, futureRequests);
            }

            long endTime = System.nanoTime();
            long durationSeconds = Math.round((endTime - startTime) / 1_000_000_000.0);

            printPolicyResult(strategy.getName(), durationSeconds, memoryManager.getPageFaults(), memoryManager.getSwapState());
        }
    }

    private void printPolicyResult(String name, long time, int faults, List<Integer> swapState) {
        StringBuilder swapStr = new StringBuilder();
        for (Integer p : swapState) {
            swapStr.append(p).append(" ");
        }
        System.out.printf("%s: %d s, %d page faults, Swap: %s%n",
            name, time, faults, swapStr.toString().trim());
    }
}
