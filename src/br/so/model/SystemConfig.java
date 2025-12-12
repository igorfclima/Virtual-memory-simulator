package model;

/**
 * Representa a configuração do sistema.
 */
public class SystemConfig {

    // @params
    private int physicalMemorySize; // M
    private int virtualMemorySize;  // V
    private String architecture;    // A
    private int totalVirtualPages;  // P

    private int pageSize;
    private int numFrames;
    private int minSwapSize;

    public SystemConfig(int physicalMemorySize, int virtualMemorySize, String architecture, int totalVirtualPages) {
        if (virtualMemorySize < physicalMemorySize) {
            throw new IllegalArgumentException("Erro: A memória virtual deve ser maior ou igual à física.");
        }

        this.physicalMemorySize = physicalMemorySize;
        this.virtualMemorySize = virtualMemorySize;
        this.architecture = architecture;
        this.totalVirtualPages = totalVirtualPages;

        calculateDerivedParameters();
    }

    private void calculateDerivedParameters() {
        this.pageSize = this.virtualMemorySize / this.totalVirtualPages;

        this.numFrames = this.physicalMemorySize / this.pageSize;

        this.minSwapSize = this.virtualMemorySize - this.physicalMemorySize;

        if (this.minSwapSize < 0) this.minSwapSize = 0;
    }

    public int getPageSize() { return pageSize; }
    public int getNumFrames() { return numFrames; }
    public int getMinSwapSize() { return minSwapSize; }
    public int getTotalVirtualPages() { return totalVirtualPages; }

    @Override
    public String toString() {
        return String.format("Page Size: %d | Frames: %d | Swap: %d", pageSize, numFrames, minSwapSize);
    }
}
