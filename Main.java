import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        // Asking users the questions necessary
        System.out.println("Input how many people you want. This must be a perfect square number of people:");
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

        // Generate and draw grid
        GridGenerator grid = new GridGenerator(individuals, timeSteps, infectionRate, recoveryRate);
    }
}
