package algorithms;

import java.util.List;
import java.util.Set;

public class OptimalStrategy implements PageReplacementStrategy {

    @Override
    public String getName() {
        return "MIN";
    }

    @Override
    public void accessPage(int pageNumber) {
    }

    @Override
    public int predictToEvict(Set<Integer> currentPagesInMemory, List<Integer> futureRequests) {
        int victim = -1;
        int farthestDistance = -1;

        for (Integer page : currentPagesInMemory) {
            int distance;

            if (futureRequests.contains(page)) {
                distance = futureRequests.indexOf(page);
            } else {
                distance = Integer.MAX_VALUE;
            }
            if (distance > farthestDistance) {
                farthestDistance = distance;
                victim = page;
            }
        }

        if (victim == -1 && !currentPagesInMemory.isEmpty()) {
            return currentPagesInMemory.iterator().next();
        }

        return victim;
    }

    @Override
    public void reset() {
    }
}
