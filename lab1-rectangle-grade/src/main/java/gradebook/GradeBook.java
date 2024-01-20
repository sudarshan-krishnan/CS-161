/*
 * Name : Sudarshan Krishnan
 * Username : kriss03
 */

package gradebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class GradeBook {

    public static void main(String[] args) throws FileNotFoundException {   
        
        File first = new File("grade_data.txt");        // File Reader 
        try (Scanner reader = new Scanner(first)) {              // File Writer
            int size = reader.nextInt();

            Student[] obj = new Student[size];                    

            for (int i = 0; i < size ; i++) {                    // Getting all 15 names and their scores into a array

                String name = reader.next();

                int[] scores = new int[5];

                for (int j = 0; j < 5; j++) {

                    scores[j] = reader.nextInt(); 
                    
                }

                obj[i] = new Student(name, scores);

            }

            PrintWriter writer = new PrintWriter("gradebook.txt");

            // Header
            writer.println("Student\tTest1\tTest2\tTest3\tTest4\tTest5\tAverage\tGrade\n");

            double summ = 0.0;
            
            for (int i = 0; i < obj.length ; i++) {

                Student obj2 = obj[i];      // Writes student data to the file

                writer.printf("%s%.1f\t%s%n",obj2.toString(),obj2.getAverage(),obj2.getGrade());

                summ += obj2.getAverage();

            }

            double avgg = summ/size;        // Calculates class average
            
            writer.printf("%nClass Average = %.2f%n", avgg);

            writer.close();
        }

    }
    
}
