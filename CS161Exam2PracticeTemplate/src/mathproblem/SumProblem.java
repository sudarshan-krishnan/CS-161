package mathproblem;

import java.util.ArrayList;

public class SumProblem implements MathProblem {

    private ArrayList<Integer> nums; 

    public SumProblem(ArrayList<Integer> nums) {
        this.nums = nums;
    }

    // Returns a string representation of the sum problem
    @Override
    public String showProblem() {
        StringBuilder problem = new StringBuilder();
        for (int i = 0; i < nums.size(); i++) {
            problem.append(nums.get(i));
            if (i < nums.size() - 1) {
                problem.append(" + ");
            }
        }
        return problem.toString();
    }

    // Calculates and returns the sum of the numbers
    @Override
    public int getAnswer() {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }

    // Checks if the provided answer is correct
    @Override
    public boolean checkAnswer(int answer) {
        return answer == getAnswer();
    }

}
