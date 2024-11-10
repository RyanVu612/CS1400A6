import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        // Asking users the questions necessary
        System.out.println("Input how many people you want. This must be a perfect square number of people: (Ex. 1, 4, 9 , 16, 25)");
        int individuals = scanner.nextInt();
        int sqrt = (int) Math.sqrt(individuals);
        while (sqrt * sqrt != individuals)
        {
            System.out.println("Invalid input. Please enter a perfect square number:");
            individuals = scanner.nextInt();
            sqrt = (int) Math.sqrt(individuals);
        }
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

        // Generate and draw grid
        GridGenerator grid = new GridGenerator(individuals, timeSteps, infectionRate, recoveryRate);
        
        for (int time = 0; time < timeSteps; time++) {
            // here is where we check whether we are next to people or whatever
            // make sure to use the .txt file of the previous timeStep. so that changes 
            // during that time don't affect the results of the same time
            // if a "S" is next to an "I", call infect()
            // if they are an "I", then call recover()
            // then print the grid out.

            Person[][] previousGrid = copyGrid(grid.grid);

            for (int i = 0; i < grid.individualsSqrt; i++) { // Loop over each row and columns
                for (int j = 0; j < grid.individualsSqrt; j++) { 
                    Person person = grid.grid[i][j]; // Access the person at i and j 

                    //If person is S and next to I, call infect() 
                    if (person.getStatus().equals("S") && isNextToInfectedPerson(previousGrid, i, j)) {
                        person.infect(); 
                    }

                    // If person is I, call recover()
                    else if (person.getStatus().equals("I")) {
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
            "I".equals(previousGrid[personRow][personColumn].getStatus())){
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
