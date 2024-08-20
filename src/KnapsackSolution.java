import java.util.ArrayList;
import java.util.List;

public class KnapsackSolution {
    private List<Item> items;
    private int totalSize;
    private int totalValue;
    private int checkedSets;
    private long executionTime;

    public KnapsackSolution(List<Item> items, int totalSize, int totalValue, int checkedSets, long executionTime) {
        this.items = items;
        this.totalSize = totalSize;
        this.totalValue = totalValue;
        this.checkedSets = checkedSets;
        this.executionTime = executionTime;
    }

    public static KnapsackSolution solveBruteForce(List<Item> items, int capacity) {
        long startTime = System.currentTimeMillis();
        int n = items.size();
        int maxValue = 0;
        int bestCombination = 0;
        int checkedSets = 0;

        for (int i = 0; i < (1 << n); i++) {
            int currentValue = 0;
            int currentSize = 0;

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    currentValue += items.get(j).getValue();
                    currentSize += items.get(j).getSize();
                }
            }

            checkedSets++;
            if (currentSize <= capacity && currentValue > maxValue) {
                maxValue = currentValue;
                bestCombination = i;
            }
        }

        List<Item> bestItems = new ArrayList<>();
        int totalSize = 0;
        for (int j = 0; j < n; j++) {
            if ((bestCombination & (1 << j)) != 0) {
                bestItems.add(items.get(j));
                totalSize += items.get(j).getSize();
            }
        }

        long elapsedTime = System.currentTimeMillis() - startTime;
        return new KnapsackSolution(bestItems, totalSize, maxValue, checkedSets, elapsedTime);
    }

    public static KnapsackSolution solveHeuristically(List<Item> items, int capacity) {
        long startTime = System.currentTimeMillis();
        items.sort((a, b) -> Double.compare(b.getValueToSizeRatio(), a.getValueToSizeRatio()));

        int totalValue = 0;
        int totalSize = 0;
        int checkedSets = 0;
        List<Item> selectedItems = new ArrayList<>();

        for (Item item : items) {
            if (totalSize + item.getSize() <= capacity) {
                selectedItems.add(item);
                totalValue += item.getValue();
                totalSize += item.getSize();
            }
            checkedSets++;
        }

        long elapsedTime = System.currentTimeMillis() - startTime;
        return new KnapsackSolution(selectedItems, totalSize, totalValue, checkedSets, elapsedTime);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Items:\n");
        for (Item item : items) {
            result.append(item).append("\n");
        }
        result.append("Total value: ").append(totalValue).append("\n");
        result.append("Total size: ").append(totalSize).append("\n");
        result.append("Checked sets: ").append(checkedSets).append("\n");
        result.append("Execution time: ").append(executionTime).append(" ms\n");
        return result.toString();
    }
}
