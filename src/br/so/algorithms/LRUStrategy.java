package algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LRUStrategy implements PageReplacementStrategy {
    private List<Integer> accessHistory;

    public LRUStrategy() {
        this.accessHistory = new ArrayList<>();
    }

    @Override
    public String getName() {
        return "LRU";
    }

    @Override
    public void accessPage(int pageNumber) {
        accessHistory.remove((Integer) pageNumber);
        accessHistory.add(pageNumber);
    }

    @Override
    public int predictToEvict(Set<Integer> currentPagesInMemory, List<Integer> futureRequests) {
        for (Integer page : accessHistory) {
            if (currentPagesInMemory.contains(page)) {
                return page;
            }
        }
        throw new IllegalStateException("Erro: LRU não encontrou página para remover.");
    }
    @Override
    public void reset() {
        this.accessHistory.clear();
    }
}
