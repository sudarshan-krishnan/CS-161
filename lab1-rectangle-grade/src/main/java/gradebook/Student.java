/*
 * Name : Sudarshan Krishnan
 * Username : kriss03
 */

package gradebook;


public class Student {

    String name;
    int[] scores = new int[5];

    //Constructor that will set name and scores

    public Student(String name, int[] scores){

        this.name = name;
        this.scores = new int[scores.length];

        for (int i=0 ; i < scores.length ; i++){

            this.scores[i] = scores[i];
        }

    }

    //Calculate and return the average of the five test scores

    public double getAverage(){

        double sum = 0.0;

        for(int i=0 ; i < this.scores.length ; i++){

            sum += this.scores[i];

        }

        double average = sum/this.scores.length;

        return average; 

    }

    //Calculate and return the student’s letter grade using their average.

    public String getGrade(){

        double avg = getAverage();

        String grade = "";
        
                if (avg >= 90 && avg <= 100) {
                    grade = "A";
                } 
                
                else if (avg >= 80 && avg < 90) {
                    grade = "B";
                } 
                
                else if (avg >= 70 && avg < 80) {
                    grade = "C";
                } 
                
                else if (avg >= 60 && avg < 70) {
                    grade = "D";
                } 
                
                else {
                    grade = "E";
                }

            return grade;
    
            }

    // Print out name, test scores, average, and letter grade to the output file

    public String toString(){

       String namescore = "";

       for (int i = 0; i < this.scores.length; i++) {

        namescore += this.scores[i] + "\t\t";
        
       }


        return String.format("%s\t%s",name,namescore);

    }
}

