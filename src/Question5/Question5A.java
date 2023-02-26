package Question5;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;


//You are given a 2D array containing coordinates and height of rectangle such that height[i]=[xi,yi,hi], where xi the
//        x coordinate of left edge, yi represents x coordinate of right edge of rectangle and hi represents the height of the
//        peaks of each rectangle. If you want to construct a border line over the peaks of rectangle represented in bar chart,
//        return the key coordinates required to build a border line that contacts the peaks of the given chart.
//        Note: key points are the left coordinates of shape representing peaks where you need to draw boarder line.
//        Input: height={{1,4,10},{2,5,15},{5,8,12},{9,11,1},{11,13,15}}
//        Output: {{1,10},{2,15},{5,12},{8,0},{9,1},{11,15},{13,0}}

//Output: {{1,10},{2,15},{5,12},{8,0},{9,1},{11,15},{13,0}}
public class Question5A {
    public int[][] getKeyCoordinates(int[][] height) {
        // Store the start and end points of each rectangle in a TreeMap
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int[] rect : height) {
            map.put(rect[0], Math.max(map.getOrDefault(rect[0], 0), rect[2]));
            map.put(rect[1], 0);
        }

        // Keep track of the current height while iterating through the map
        int currHeight = 0;
        int[][] res = new int[map.size()][2];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int x = entry.getKey();
            int h = entry.getValue();
            if (h != currHeight) {
                res[i][0] = x;
                res[i][1] = currHeight = h;
                i++;
            }
        }

        // Return the key coordinates
        return Arrays.copyOfRange(res, 0, i);
    }

    public static void main(String[] args) {
        Question5A obj = new Question5A() ;
        int[][] height = {{1,4,10},{2,5,15},{5,8,12},{9,11,1},{11,13,15}};
        int[][] res = obj.getKeyCoordinates(height);
        System.out.println(Arrays.deepToString(res));
    }
}
