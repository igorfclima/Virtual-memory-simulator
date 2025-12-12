package algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomStrategy implements PageReplacementStrategy {

    private Random random;

    public RandomStrategy() {
        this.random = new Random();
    }

    @Override
    public String getName() {
        return "RAND";
    }

    @Override
    public void accessPage(int pageNumber) {
    }

    @Override
    public int predictToEvict(Set<Integer> currentPagesInMemory, List<Integer> futureRequests) {
        if (currentPagesInMemory.isEmpty()) {
            throw new IllegalStateException("Memória vazia no Random.");
        }
        List<Integer> pagesList = new ArrayList<>(currentPagesInMemory);
        int randomIndex = random.nextInt(pagesList.size());

        return pagesList.get(randomIndex);
    }

    @Override
    public void reset() {
    }
}
