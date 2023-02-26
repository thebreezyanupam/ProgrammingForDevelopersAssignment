package Question6;

public class Question6B {
    static int charToNum(char c) {
        if (c == 'S') return 6;
        if (c == 'I') return 5;
        if (c == 'X') return 0;
        if (c == 'E') return 8;
        if (c == 'V') return 7;
        if (c == 'N') return 2;
        if (c == 'T') return 1;
        if (c == 'W') return 3;
        if (c == 'Y') return 4;
        return -1;
    }

    static int[] wordToNum(String word) {
        int[] nums = new int[word.length()];
        for (int i = 0; i < word.length(); i++) {
            nums[i] = charToNum(word.charAt(i));
        }
        return nums;
    }

    static int toNum(int[] nums) {
        int num = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == -1) {
                return -1;
            }
            num = num * 10 + nums[i];
        }
        return num;
    }

    public static boolean isValid(String[] words, String result) {
        int cnt = 0;
        for (int j = 0; j < words.length; j++) {
            String word = words[j];
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (charToNum(c) == -1) {
                    cnt++;
                }
            }
        }
        System.out.println("cnt: " + cnt);

        int[] wordNums = wordToNum(result);
        int target = toNum(wordNums);
        System.out.println("target: " + target);

        int sum = 0;
        for (int j = 0; j < words.length; j++) {
            String word = words[j];
            int[] nums = wordToNum(word);
            int wordSum = toNum(nums);
            if (wordSum == -1) {
                return false;
            }
            sum += wordSum;
        }
        System.out.println("sum: " + sum);

        return sum == target;
    }

    public static void main(String[] args) {
        String[] words = {"SIX", "SEVEN", "SEVEN"};
        String result = "TWENTY";
        System.out.println("isValid: " + isValid(words, result));
    }
}
