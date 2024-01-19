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

        File data = new File("rectangles.txt");
        try (Scanner keyboard = new Scanner(data)) {
            
            while (keyboard.hasNext()) {

                int row = keyboard.nextInt();
                int col = keyboard.nextInt();
                String solid = keyboard.next();

                boolean b = false;

                if (solid .equals("filled"))
                    b = true;

            
                Rectangle rect = new Rectangle(row,col,b);

                String pat = rect.toString();

                System.out.println(pat);

            }
        }
        

    }
    
}
 