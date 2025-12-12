import model.SystemConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            int physicalMemory = scanner.nextInt(); // M
            int virtualMemory = scanner.nextInt();  // V
            String architecture = scanner.next();   // A
            int totalPages = scanner.nextInt();     // P

            SystemConfig config = new SystemConfig(physicalMemory, virtualMemory, architecture, totalPages);
            int numSequences = scanner.nextInt();

            for (int i = 0; i < numSequences; i++) {
                int sequenceSize = scanner.nextInt();

                List<Integer> pages = new ArrayList<>();
                for (int j = 0; j < sequenceSize; j++) {
                    pages.add(scanner.nextInt());
                }

                System.out.println("--- Debug: Leitura da Sequencia " + (i + 1) + " ---");
                System.out.println("Config: " + config);
                System.out.println("Paginas: " + pages);
                System.out.println("-------------------------------------------");
            }
        } catch (Exception e) {
            System.err.println("Erro ao ler entrada: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
