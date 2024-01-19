/*
 * Name : Sudarshan Krishnan
 * Username : kriss03
 */

package rectanglegraphic;

public class Rectangle {

    int rows;
    int cols;
    boolean filled;
    
    public Rectangle (int a,int b,boolean bool){

        rows = a;
        cols = b;
        filled = bool;

    }

    public String toString() {

        String pattern = "";
        
             for (int i = 0; i < rows; i++) {
                
                for (int j = 0; j < cols; j++) {
                    
                    if (i == 0 || j == 0 || i == (rows-1) || j == (cols-1) || filled)
                        pattern += "#";
                    
                    else 
                    pattern += " ";
                }
                
                pattern += "\n";
            }
        
        return pattern;


            
    }

}
