import java.lang.Math;
import java.util.Random;

public class GridGenerator {

    int individuals;
    int timeSteps;
    double infectionRate;
    double recoverRate;
    int individualsSqrt;
    String[][] grid;

    public GridGenerator(int individuals, int timeSteps, double infectionRate, double recoverRate) {
        // Variable assignments
        this.individuals = individuals;
        this.timeSteps = timeSteps;
        this.infectionRate = infectionRate;
        this.recoverRate = recoverRate;
        individualsSqrt = (int) Math.sqrt(individuals);
        grid = new String[individualsSqrt][individualsSqrt];

        // Creates the grid
        declareGrid();
        printGrid();
    }

    public void declareGrid() {
        // Creates a grid of the perfect square inputted by the user
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = "S";
            }
        }

        // Creates a random slot to put the infected person into
        Random rand = new Random();
        int randNum1 = 1 + rand.nextInt(individualsSqrt - 1);
        int randNum2 = 1 + rand.nextInt(individualsSqrt - 1);
        grid[randNum1][randNum2] = "I";
    }

    public void printGrid() {
        System.out.println("Below is the randomly generated grid based on your input.");
        // Creates the grid in the correct format
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

}
