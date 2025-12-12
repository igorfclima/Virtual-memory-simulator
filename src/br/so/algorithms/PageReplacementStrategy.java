package algorithms;

import java.util.List;
import java.util.Set;

public interface PageReplacementStrategy {
    String getName();
    void accessPage(int pageNumber);

    int predictToEvict(Set<Integer> currentPagesInMemory, List<Integer> futureRequests);

    void reset();
}
