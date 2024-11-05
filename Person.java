import java.util.*;

public class Person {

    /*Since the person object is going to be necessary to do anything else, want to create that and if you have time, 
    the method that will include the random number generator saying if they will get infected/recover? this method 
    should probably be inside the person class. After that, we can split up the tasks of  */
    double infectionRate;
    double recoveryRate;

    //Constructor
    public Person(String[][] status, double infectionRate, double recoveryRate){
        this.recoveryRate=recoveryRate;
        this.infectionRate=infectionRate;
    }

    //Analyzes status and determines if it will change into another status, then changes it
    public void getStatus(String[][] status){

        Random rand = new Random();

        for (int i = 0; i < status.length; i++) {
            for (int j = 0; j < status[0].length; j++) {
                double randomProb = rand.nextDouble();
                if(status[i][j].equals("I") && randomProb <= recoveryRate){
                    status[i][j] = "R";
                }
                else if(status[i][j].equals("S") && randomProb <= infectionRate){
                    status[i][j] = "I";
                }
            }
        }
    }
    
}
