import java.lang.Math;

public class GridGenerator {

    int individuals;
    int timeSteps;
    double infectionRate;
    double recoverRate;

    public GridGenerator(int individuals, int timeSteps, double infectionRate, double recoverRate) {
        this.individuals = individuals;
        this.timeSteps = timeSteps;
        this.infectionRate = infectionRate;
        this.recoverRate = recoverRate;
    }

    int individualsSqrt = (int) Math.sqrt(individuals);

    String[][] grid = new String[individualsSqrt][individualsSqrt];

}
