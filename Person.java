import java.util.*;

public class Person {
    String status;
    double infectionRate;
    double recoveryRate;
    boolean isNextToInfectedPerson;
    Random rand = new Random();

    //Constructor
    public Person(String status, double infectionRate, double recoveryRate, boolean isNextToInfectedPerson){
        this.status = status;
        this.recoveryRate = recoveryRate;
        this.infectionRate = infectionRate;
        this.isNextToInfectedPerson = isNextToInfectedPerson;
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

    public void nextToInfectedPerson() {
        isNextToInfectedPerson = true;
    }

    public boolean isNextToInfectedPerson() {
        return isNextToInfectedPerson;
    }
}
