package Question2;

import java.util.Arrays;

public class Question2A {

    // Method to find greatest common divisor (GCD) of two numbers using Euclid's algorithm
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    // Method to find nearest ancestor with relative prime value
    public static int nearestAncestor(int[] values, int[][] edges, int node) {
        // Base case: If the current node is the root node (i.e., has no parent)
        if (node == 0) {
            return -1;
        }

        int parent = -1;
        int gcdValue = 0;

        // Traverse the path from the current node to the root node
        while (node != 0 && gcdValue != 1) {
            // Find the parent of the current node
            for (int i = 0; i < edges.length; i++) {
                if (edges[i][1] == node) {
                    parent = edges[i][0];
                    break;
                }
            }
            gcdValue = gcd(values[node], values[parent]);
            node = parent;
        }

        if (gcdValue == 1) {
            return parent;
        } else {
            return -1;
        }
    }

    // Main method to find nearest ancestors for all nodes
    public static int[] nearestAncestors(int[] values, int[][] edges) {
        int n = values.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);

        // Iterate over all nodes and find their nearest ancestor with relative prime value
        for (int i = 0; i < n; i++) {
            result[i] = nearestAncestor(values, edges, i);
        }

        return result;
    }

    // Main method to test the solution
    public static void main(String[] args) {
        int[] values = {3, 2, 6, 6, 4, 7, 12};
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}};
        int[] result = nearestAncestors(values, edges);

        // Print the result
        System.out.println(Arrays.toString(result));
    }
}