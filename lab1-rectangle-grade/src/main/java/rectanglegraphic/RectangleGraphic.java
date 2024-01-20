/*
 * Name : Sudarshan Krishnan
 * Username : kriss03
 */

package rectanglegraphic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;     //imported entire util package instead of scanner class

public class RectangleGraphic {
    
    public static void main(String[] args) throws FileNotFoundException {

        File data = new File("rectangles.txt");     //creating file class object
        try (Scanner keyboard = new Scanner(data)) {         // creating an object for scanner
            
            while (keyboard.hasNext()) {                //keyboard reads only if there is something to read

                int row = keyboard.nextInt();           //reading the first int for rows
                int col = keyboard.nextInt();           //reading the second int for columns
                String solid = keyboard.next();         //reading for unfilled or filled

                boolean b = false;

                if (solid .equals("filled"))   
                    b = true;

            
                Rectangle rect = new Rectangle(row,col,b);

                String pat = rect.toString();

                System.out.println(pat);               //printing the pattern

            }
        }
        

    }
    
}
 