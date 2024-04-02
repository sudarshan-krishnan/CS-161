package mathproblem;

public class SubtractionProblem implements MathProblem {

    private int num1, num2; // Values of operands

    public SubtractionProblem(int n1, int n2) {
        num1 = n1;
        num2 = n2;
    }

    // Solve the subtraction problem
    @Override
    public int solve() {
        return num1 - num2;
    }

    // Return the problem statement
    @Override
    public String getProblemStatement() {
        return "What is " + num1 + " - " + num2 + "?";
    }

}
