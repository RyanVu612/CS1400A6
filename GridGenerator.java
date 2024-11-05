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
        printGrid();
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

    public void printGrid() throws IOException {
        for(int k=1; k<timeSteps+1; k++){
            // Creates the grid in the correct format
            String timeStepString = Integer.toString(k);
            String filename = "TimeStep" + k + ".txt";
            FileWriter fw = new FileWriter(filename);
            PrintWriter outputFile = new PrintWriter(fw);

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    outputFile.print(grid[i][j] + " ");
                }
                outputFile.print("\n");
                
            }
            outputFile.println("This is the end of the file.");
            outputFile.close();

            //edits the infected for now and only sees if I has a chance to recover w/o considering anything around it along w S
            Person person = new Person(grid, infectionRate, recoverRate);
            person.getStatus(grid);
        }
    }

}
