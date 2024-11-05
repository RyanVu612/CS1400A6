import java.io.*;
import java.util.Random;

public class GridGenerator {

    int individuals;
    int timeSteps;
    double infectionRate;
    double recoveryRate;
    int individualsSqrt;
    Person[][] grid;

    public GridGenerator(int individuals, int timeSteps, double infectionRate, double recoveryRate) throws IOException {
        // Variable assignments
        this.individuals = individuals;
        this.timeSteps = timeSteps;
        this.infectionRate = infectionRate;
        this.recoveryRate = recoveryRate;
        individualsSqrt = (int) Math.sqrt(individuals);
        grid = new Person[individualsSqrt][individualsSqrt];

        // Creates the grid
        declareGrid();
        printGrid(0);
    }

    public void declareGrid() {
        // Creates a grid of the perfect square inputted by the user
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = new Person("S", infectionRate, recoveryRate);
            }
        }

        // Creates a random slot to put the infected person into
        Random rand = new Random();
        int randNum1 = 1 + rand.nextInt(individualsSqrt - 1);
        int randNum2 = 1 + rand.nextInt(individualsSqrt - 1);
        grid[randNum1][randNum2].setStatus("R");
    }

    public void printGrid(int timeStep) throws IOException {
        // Creates the grid in the correct format
        String filename = "TimeStep" + timeStep + ".txt";
        FileWriter fw = new FileWriter(filename);
        PrintWriter outputFile = new PrintWriter(fw);

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                outputFile.print(grid[i][j].getStatus() + " ");
            }
            outputFile.print("\n");
        }

        outputFile.println("This is the end of the file.");
        outputFile.close();
    }

}
