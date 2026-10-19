/**
 * Manacher's Algorithm - Longest Palindromic Substring in O(n) time
 * Modular implementation
 */
public class Manacher {

    /**
     * Finds the longest palindromic substring in the given string.
     *
     * @param s input string
     * @return the longest palindromic substring
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        if (s.length() == 1) {
            return s;
        }

        // Step 1: Preprocess the string
        String processed = preprocess(s);

        // Step 2: Compute the radius array (P)
        int[] P = computePalindromeRadii(processed);

        // Step 3: Find the center with maximum radius
        int maxLen = 0;
        int centerIndex = 0;
        for (int i = 1; i < processed.length() - 1; i++) {
            if (P[i] > maxLen) {
                maxLen = P[i];
                centerIndex = i;
            }
        }

        // Step 4: Extract the original substring
        int start = (centerIndex - maxLen) / 2;
        return s.substring(start, start + maxLen);
    }

    /**
     * Preprocesses the string by inserting '#' between characters
     * and adding sentinels '^' and '$' to avoid bounds checking.
     * Example: "aba" → "^#a#b#a#$"
     */
    private static String preprocess(String s) {
        StringBuilder sb = new StringBuilder("^");
        for (char c : s.toCharArray()) {
            sb.append('#').append(c);
        }
        sb.append("#$");
        return sb.toString();
    }

    /**
     * Core of Manacher's Algorithm.
     * Computes the array P where P[i] = radius of the longest
     * palindrome centered at position i in the processed string.
     */
    private static int[] computePalindromeRadii(String t) {
        int n = t.length();
        int[] P = new int[n];
        int center = 0;   // center of the rightmost palindrome
        int right = 0;    // right boundary of the rightmost palindrome

        for (int i = 1; i < n - 1; i++) {
            int mirror = 2 * center - i;  // mirror of i with respect to center

            // If i is within the right boundary, we can use previously computed value
            if (i < right) {
                P[i] = Math.min(right - i, P[mirror]);
            }

            // Attempt to expand palindrome centered at i
            while (t.charAt(i + 1 + P[i]) == t.charAt(i - 1 - P[i])) {
                P[i]++;
            }

            // If the expanded palindrome goes beyond the current right boundary,
            // update center and right
            if (i + P[i] > right) {
                center = i;
                right = i + P[i];
            }
        }
        return P;
    }

    // ==================== Utility / Demo methods ====================

    /**
     * Returns the length of the longest palindromic substring.
     */
    public static int longestPalindromeLength(String s) {
        return longestPalindrome(s).length();
    }

    /**
     * Returns all longest palindromic substrings
     * (in case there are multiple of the same maximum length).
     */
    public static java.util.List<String> allLongestPalindromes(String s) {
        java.util.List<String> result = new java.util.ArrayList<>();
        if (s == null || s.isEmpty()) return result;

        String processed = preprocess(s);
        int[] P = computePalindromeRadii(processed);

        int maxLen = 0;
        for (int radius : P) {
            maxLen = Math.max(maxLen, radius);
        }

        for (int i = 1; i < processed.length() - 1; i++) {
            if (P[i] == maxLen) {
                int start = (i - maxLen) / 2;
                result.add(s.substring(start, start + maxLen));
            }
        }
        return result;
    }

    // ==================== Example usage ====================
    public static void main(String[] args) {
        String[] tests = {
            "babad",
            "cbbd",
            "a",
            "ac",
            "racecar",
            "forgeeksskeegfor",
            "bananas",
            "aaaa"
        };

        for (String test : tests) {
            System.out.println("Input  : " + test);
            System.out.println("LPS    : " + longestPalindrome(test));
            System.out.println("Length : " + longestPalindromeLength(test));
            System.out.println("All LPS: " + allLongestPalindromes(test));
            System.out.println("-------------------------");
        }
    }
}