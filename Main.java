import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int individuals;
        int timeSteps;
        double infectionRate;
        double recoveryRate;

        int sqrt;

        // Asking users the questions necessary
        System.out.println("Input how many people you want. This must be a perfect square number of people: (Ex. 1, 4, 9 , 16, 25)");
        individuals = scanner.nextInt();
        sqrt = (int) Math.sqrt(individuals);
        while (sqrt * sqrt != individuals)
        {
            System.out.println("Invalid input. Please enter a perfect square number:");
            individuals = scanner.nextInt();
            sqrt = (int) Math.sqrt(individuals);
        }

        System.out.println("Input the number of time cycles you want:");
        timeSteps = scanner.nextInt();
        if(timeSteps > 20){
            timeSteps = 20;
            System.out.println("Too many timesteps. Reduced to limit cap 20");
        }

        System.out.println("Input the rate of infection. This must be as a decimal between 0 and 1 (ex. 0.6, 0.8):");
        infectionRate = scanner.nextDouble();
        while (infectionRate >= 1 || infectionRate < 0) {
            System.out.println("Invalid input. Please enter a decimal between 0 and 1");
            infectionRate = scanner.nextDouble();
        }

        System.out.println("Input the rate of recovery. This must be as a decimal between 0 and 1 (ex. 0.6, 0.8):");
        recoveryRate = scanner.nextDouble();
        while (recoveryRate >= 1 || recoveryRate < 0) {
            System.out.println("Invalid input. Please enter a decimal between 0 and 1");
            recoveryRate = scanner.nextDouble();
        }

        // Generate and draw grid
        GridGenerator grid = new GridGenerator(individuals, timeSteps, infectionRate, recoveryRate);
        
        for (int time = 0; time < timeSteps; time++) {
            Person[][] previousGrid = copyGrid(grid.grid);

            // Check and change status of each individual
            // If S and next to I, chance to infect
            // If I, chance to recover
            for (int i = 0; i < grid.individualsSqrt; i++) {
                for (int j = 0; j < grid.individualsSqrt; j++) { 
                    Person person = grid.grid[i][j]; // Access the person at i and j 

                    //If person is S and next to I, call infect() 
                    if (person.getStatus().equals("S") && isNextToInfectedPerson(previousGrid, i, j)) {
                        person.infect(); 
                    } else if (person.getStatus().equals("I")) {    // If person is I, call recover()

                        person.recover();
                    }
                }
            }
            grid.printGrid(time + 1);
        }
    }

    //Method to check if a Person is next to an infected person
    public static boolean isNextToInfectedPerson (Person[][] previousGrid, int i, int j) {
        // directions in arrays: up, down, left, and right, idea is to 
        int[][] directions = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };

        for (int [] direction : directions) { 
            int personRow = i + direction[0]; // checks the rows (x-axis)
            int personColumn = j + direction[1]; // checks the columns (y-axis)

            // check if the person is next to someone whos infected
            if (personRow >= 0 && personRow < previousGrid.length && personColumn >= 0 && personColumn < previousGrid.length &&
            previousGrid[personRow][personColumn].getStatus().equals("I")){
                return true;
            }
        }
        return false;
    }

    // copies previous grid into a new grid
    public static Person[][] copyGrid(Person[][] originalGrid){
        int size = originalGrid.length; //gets size of og grid
        Person[][] newGrid = new Person[size][size]; //creates new grid
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {// goes through all rows and columns
                Person originalPerson = originalGrid[i][j]; // then gets the person and puts them into the newGrid
                newGrid[i][j] = new Person(originalPerson.getStatus(), originalPerson.infectionRate, originalPerson.recoveryRate);
            }
        }
        return newGrid;
    }
}
