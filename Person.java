import java.util.*;

public class Person {
    String status;
    double infectionRate;
    double recoveryRate;
    Random rand = new Random();

    //Constructor
    public Person(String status, double infectionRate, double recoveryRate){
        this.status = status;
        this.recoveryRate = recoveryRate;
        this.infectionRate = infectionRate;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void recover() {
        double randomProb = rand.nextDouble();
        if (status.equals("I") && randomProb <= recoveryRate) {
            status = "R";
        }
    }

    public void infect() {
        double randomProb = rand.nextDouble();
        if (status.equals("S") && randomProb <= infectionRate) {
            status = "I";
        }
    }
}
