import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List<List<Item>> dataSets = DataReader.readData("plecak.txt");

        Random random = new Random();
        int selectedDataSetIndex = random.nextInt(15);
        List<Item> selectedItems = dataSets.get(selectedDataSetIndex);

        int knapsackCapacity = 50;

        KnapsackSolution heuristicSolution = KnapsackSolution.solveHeuristically(selectedItems, knapsackCapacity);
        System.out.println("Heuristic:\n" + heuristicSolution);

        KnapsackSolution bruteForceSolution = KnapsackSolution.solveBruteForce(selectedItems, knapsackCapacity);
        System.out.println("Bruteforce: \n" + bruteForceSolution);
    }
}
