import java.io.*;
import java.util.Random;

public class Grid {

    int individuals;
    int timeSteps;
    double infectionRate;
    double recoveryRate;
    int individualsSqrt;
    Person[][] grid;

    public Grid(int individuals, int timeSteps, double infectionRate, double recoveryRate) throws IOException {
        // Variable assignments
        this.individuals = individuals;
        this.timeSteps = timeSteps;
        this.infectionRate = infectionRate;
        this.recoveryRate = recoveryRate;
        individualsSqrt = (int) Math.sqrt(individuals);
        grid = new Person[individualsSqrt][individualsSqrt];

        // Creates the grid
        declareGrid();
        // Time 0 since this is the starting grid
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
        // If there is only one person, set that person to "I"
        Random rand = new Random();
        if (individualsSqrt == 1) {
            grid[0][0].setStatus("I");
        } else {
            int randNum1 = 1 + rand.nextInt(individualsSqrt - 1);
            int randNum2 = 1 + rand.nextInt(individualsSqrt - 1);
            grid[randNum1][randNum2].setStatus("I");
        }
    }

    public void printGrid(int timeStep) throws IOException {
        // Creates the grid in the correct format
        String filename = "TimeStep" + timeStep + ".txt";
        FileWriter fw = new FileWriter(filename);
        PrintWriter outputFile = new PrintWriter(fw);

        int susceptibleCount = 0;
        int infectedCount = 0;
        int recoveredCount = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                
                String status = grid[i][j].getStatus();
                outputFile.print(status + " ");

                // Count each type of individual
                if (status.equals("S")){
                    susceptibleCount++;
                }
                else if (status.equals("I")) {
                    infectedCount++;
                } 
                else if (status.equals("R")) {
                    recoveredCount++;
                }
            }
            outputFile.print("\n");
        }

        // Calculate the infected ratio
        double infectedRatio = (double) infectedCount / individuals;

        // Print the statistics at the end of the file
        outputFile.println("\nStatistics for Time Step " + timeStep + ":");
        outputFile.println("Susceptible Individuals: " + susceptibleCount);
        outputFile.println("Infected Individuals: " + infectedCount);
        outputFile.println("Recovered Individuals: " + recoveredCount);
        outputFile.printf("Infected Ratio: " + infectedCount + "/" + 
                            individuals + " (" + infectedRatio + ")");
        outputFile.println("\nThis is the end of the file.");
        outputFile.close();
    }

    public Person[][] copyGrid(){
        int size = grid.length; //gets size of og grid
        Person[][] newGrid = new Person[size][size]; //creates new grid
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {// goes through all rows and columns
                Person originalPerson = grid[i][j]; // then gets the person and puts them into the newGrid
                newGrid[i][j] = new Person(originalPerson.getStatus(), originalPerson.infectionRate, originalPerson.recoveryRate);
            }
        }
        return newGrid;
    }
}
