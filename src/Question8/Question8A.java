package Question8;

import java.util.Stack;

public class Question8A {

    public static int getMaxSquareArea(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int maxArea = 0;
        int[] histogram = new int[n];

        // Step 1: Calculate the histogram for each row
        for (int i = 0; i < m; i++) {
            // calculate histogram for the current row
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    histogram[j] = 0;
                } else {
                    histogram[j]++;
                }
            }
            // calculate the maximum area of the square for the current row
            int area = largestSquareArea(histogram);
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }

    // Step 2: Implement the largest square in a histogram algorithm using a stack
    private static int largestSquareArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int i = 0;
        while (i <= n) {
            int h = (i == n) ? 0 : heights[i];
            if (stack.isEmpty() || h >= heights[stack.peek()]) {
                stack.push(i);
                i++;
            } else {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                int size = Math.min(height, width);
                int area = size * size;
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 0, 1, 0, 0},
                {0, 1, 1, 1, 1},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 1, 1},
                {0, 1, 0, 1, 1}};

        // Find the maximum area of square made by 0s in the matrix
        int maxArea = Question8A.getMaxSquareArea(matrix);
        System.out.println("Maximum area of square made by 0s: " + maxArea);
    }
}
