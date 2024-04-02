package mathproblem;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

interface MathProblem {
	String showProblem(); 
    int getAnswer(); 
    boolean checkAnswer(int answer);
}
