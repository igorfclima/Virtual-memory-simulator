package algorithms;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class FIFOStrategy implements PageReplacementStrategy {
    private Queue<Integer> queue;

    public FIFOStrategy() {
        this.queue = new LinkedList<>();
    }
    @Override
    public String getName() {
        return "FIFO";
    }
    @Override
    public void accessPage(int pageNumber) {
        if (!queue.contains(pageNumber)) {
            queue.add(pageNumber);
        }
    }
    @Override
    public int predictToEvict(Set<Integer> currentPagesInMemory, List<Integer> futureRequests) {
        if (queue.isEmpty()) {
            throw new IllegalStateException("Tentando remover de memória vazia.");
        }
        return queue.poll();
    }

    @Override
    public void reset() {
        this.queue.clear();
    }
}
