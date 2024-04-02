package mathproblem;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

abstract class MathProblem {
    protected double operand1;
    protected double operand2;
    protected double answer;

    public MathProblem(double operand1, double operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public abstract String getProblem();

    public abstract boolean checkAnswer(double response);

    public double getAnswer() {
        return answer;
    }
}

class MultiplicationProblem extends MathProblem {
    public MultiplicationProblem(double operand1, double operand2) {
        super(operand1, operand2);
        answer = operand1 * operand2;
    }

    public String getProblem() {
        return operand1 + " * " + operand2;
    }

    public boolean checkAnswer(double response) {
        return response == answer;
    }
}

class DivisionProblem extends MathProblem {
    public DivisionProblem(double operand1, double operand2) {
        super(operand1, operand2);
        answer = operand1 / operand2;
    }

    public String getProblem() {
        return operand1 + " / " + operand2;
    }

    public boolean checkAnswer(double response) {
        return Math.abs(response - answer) < 0.0001; // Allow for small floating-point differences
    }
}

public class MathQuiz {

    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("math_data.bin", "r");
        ArrayList<MathProblem> problems = new ArrayList<>();

        try {
            while (true) {
                char operator = file.readChar();
                double operand1 = file.readDouble();
                double operand2 = file.readDouble();

                MathProblem problem;
                if (operator == '*') {
                    problem = new MultiplicationProblem(operand1, operand2);
                } else if (operator == '/') {
                    problem = new DivisionProblem(operand1, operand2);
                } else {
                    throw new IllegalArgumentException("Unknown operator: " + operator);
                }

                problems.add(problem);
            }
        } catch (EOFException e) {
            // End of file reached
        } finally {
            file.close();
        }

        Scanner kb = new Scanner(System.in);

        for (MathProblem problem : problems) {
            System.out.println("What is " + problem.getProblem() + "?");
            double response = kb.nextDouble();

            if (problem.checkAnswer(response)) {
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect, the answer is " + problem.getAnswer() + ".");
            }
        }

        kb.close();
    }
}