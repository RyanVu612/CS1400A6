import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        // Asking users the questions necessary
        System.out.println("Input how many people you want. This must be a perfect square number of people: (Ex. 1, 4, 9 , 16, 25)");
        int individuals = scanner.nextInt();
        System.out.println("Input the number of time cycles you want:");
        int timeSteps = scanner.nextInt();
        if(timeSteps > 20){
            timeSteps = 20;
            System.out.println("Too many timesteps. Reduced to limit cap 20");
        }
        System.out.println("Input the rate of infection. This must be as a decimal (ex. 0.6, 0.8):");
        double infectionRate = scanner.nextDouble();
        System.out.println("Input the rate of recovery. This must be as a decimal (ex. 0.6, 0.8):");
        double recoveryRate = scanner.nextDouble();

        int individualsSqrt =  (int)Math.sqrt(individuals);


        // Creates a grid of the perfect square inputted by the user
        Person[][] grid = new Person[individualsSqrt][individualsSqrt];
        for (int i = 0; i < individualsSqrt; i++) {
            for (int j = 0; j < individualsSqrt; j++) {
                grid[i][j] = new Person("S", "S", infectionRate, recoveryRate, false);
            }
        }

        // Creates a random slot to put the infected person into
        int randNum1 = 1 + rand.nextInt(individualsSqrt - 1);
        int randNum2 = 1 + rand.nextInt(individualsSqrt - 1);
        grid[randNum1][randNum2].setStatus("I");

        int time = 0;

        printGrid(grid, time);

        String filePath = "";

        while (time < timeSteps) {
            // here is where we check whether we are next to people or whatever
            // make sure to use the .txt file of the previous timeStep. so that changes 
            // during that time don't affect the results of the same time
            // if a "S" is next to an "I", call infect()
            // if they are an "I", then call recover()
            // then print the grid out.
            
            filePath = "TimeStep" + time + ".txt";

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid.length; j++) {
                    if (grid[i][j].getStatus().equals("I")) {
                        // Aleady infected
                        grid[i][j].recover();
                    } else {
                        // Susceptible
                        
                        if ((i != 0 && grid[i-1][j].getPreviousStatus().equals("I")) ||
                            (j != 0 && grid[i][j-1].getPreviousStatus().equals("I")) ||
                            (i != grid.length - 1 && grid[i+1][j].getPreviousStatus().equals("I")) ||
                            (j != grid.length - 1 && grid[i][j+1].getPreviousStatus().equals("I"))) {
                            grid[i][j].nextToInfectedPerson();
                            grid[i][j].infect();
                        }
                    }
                }
            }

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid.length; j++) {
                    if (grid[i][j].getStatus() != grid[i][j].getPreviousStatus()) {
                        grid[i][j].setPreviousStatus(grid[i][j].getStatus());
                    }
                }
            }

            time++;
            printGrid(grid, time);
        }
    }

    public static void printGrid(Person[][] grid, int timeStep) throws IOException {
        // Creates the grid in the correct format
        String filename = "TimeStep" + timeStep + ".txt";
        FileWriter fw = new FileWriter(filename);
        PrintWriter outputFile = new PrintWriter(fw);

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                outputFile.print(grid[i][j].getStatus());
            }
            outputFile.print("\n");
        }

        outputFile.println("This is the end of the file.");
        outputFile.close();
    }
}
