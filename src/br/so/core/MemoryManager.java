package core;

import algorithms.PageReplacementStrategy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MemoryManager {

    private int numFrames;
    private Set<Integer> physicalMemory;
    private Set<Integer> swapMemory;
    private int pageFaults;

    public MemoryManager(int numFrames) {
        this.numFrames = numFrames;
        this.physicalMemory = new HashSet<>();
        this.swapMemory = new HashSet<>();
        this.pageFaults = 0;
    }

    public void accessPage(int pageNumber, PageReplacementStrategy strategy, List<Integer> futureRequests) {
        strategy.accessPage(pageNumber);

        if (physicalMemory.contains(pageNumber)) {
            return;
        }

        pageFaults++;

        if (swapMemory.contains(pageNumber)) {
            swapMemory.remove(pageNumber);
        }
        if (physicalMemory.size() >= numFrames) {
            int victimPage = strategy.predictToEvict(physicalMemory, futureRequests);
            physicalMemory.remove(victimPage);

            swapMemory.add(victimPage);
        }
        physicalMemory.add(pageNumber);
    }
    public int getPageFaults() {
        return pageFaults;
    }

    public List<Integer> getSwapState() {
        List<Integer> sortedSwap = new ArrayList<>(swapMemory);
        Collections.sort(sortedSwap);
        return sortedSwap;
    }

    public void reset() {
        physicalMemory.clear();
        swapMemory.clear();
        pageFaults = 0;
    }
}
