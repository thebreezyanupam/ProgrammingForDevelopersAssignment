package Question3;

import java.util.Scanner;

class Question3B {
    public static boolean patternMatch(String inputStr, String pattern) {
        int i = 0;
        int j = 0;
        while (i < inputStr.length() && j < pattern.length()) {
            if (pattern.charAt(j) == '@') {
                return true;
            } else if (pattern.charAt(j) == '#' && i < inputStr.length()) {
                i++;
                j++;
            } else if (inputStr.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                return false;
            }
        }
        return i == inputStr.length() && j == pattern.length();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter input string: ");
        String inputStr = scanner.nextLine();

        System.out.print("Enter pattern string: ");
        String pattern = scanner.nextLine();

        boolean isMatch = patternMatch(inputStr, pattern);
        System.out.println("Pattern match result: " + isMatch);
    }

    //given cases in question
//    Input: String a=“tt”, pattern =”@”
//    Output: true
//    Input: String a=“ta”, pattern =”t”
//    Output: false
//    Input: String a=“ta”, pattern =”t#”
//    Output: true


}

