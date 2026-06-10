import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 字符串匹配算法。
 *
 * 覆盖 Week 8：
 * 1. Brute Force 暴力匹配，O(nm)
 * 2. Boyer-Moore bad-character 思想
 * 3. KMP failure function，O(n + m)
 */
public class StringMatchingAlgorithms {

    public static void main(String[] args) {
        String text = "ABCABCXABCABCDA";
        String pattern = "ABCDA";

        System.out.println("Brute Force: " + bruteForceMatch(text, pattern));
        System.out.println("Boyer-Moore: " + boyerMooreBadCharacter(text, pattern));
        System.out.println("KMP: " + kmpMatch(text, pattern));
        System.out.println("KMP failure: " + Arrays.toString(buildFailure(pattern)));
    }

    /**
     * 暴力字符串匹配。
     *
     * 让 pattern 从 text 的每个可能起点开始尝试；
     * 每次逐字符比较。
     * 最坏情况有 n-m+1 个起点，每次比较 m 个字符，所以 O(nm)。
     */
    public static int bruteForceMatch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        if (m == 0) {
            return 0;
        }

        for (int start = 0; start <= n - m; start++) {
            int k = 0;

            while (k < m && text.charAt(start + k) == pattern.charAt(k)) {
                k++;
            }

            if (k == m) {
                return start;
            }
        }

        return -1;
    }

    /**
     * Boyer-Moore 的 bad-character 简化版。
     *
     * 从 pattern 的右端开始比较。
     * 一旦 mismatch，查看 text 中这个坏字符在 pattern 中最后一次出现的位置，
     * 然后把 pattern 尽量向右跳。
     */
    public static int boyerMooreBadCharacter(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        if (m == 0) {
            return 0;
        }

        Map<Character, Integer> last = buildLastOccurrence(pattern);
        int start = 0;

        while (start <= n - m) {
            int j = m - 1;

            // 从右向左比较。
            while (j >= 0 && pattern.charAt(j) == text.charAt(start + j)) {
                j--;
            }

            if (j < 0) {
                return start;
            }

            char badChar = text.charAt(start + j);
            int lastIndex = last.getOrDefault(badChar, -1);

            // 至少向右移动一位，避免死循环。
            start += Math.max(1, j - lastIndex);
        }

        return -1;
    }

    private static Map<Character, Integer> buildLastOccurrence(String pattern) {
        Map<Character, Integer> last = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            last.put(pattern.charAt(i), i);
        }

        return last;
    }

    /**
     * KMP 字符串匹配。
     *
     * KMP 的关键是不让 text 指针回退。
     * mismatch 时，用 failure array 告诉 pattern 指针应该跳到哪里。
     */
    public static int kmpMatch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        if (m == 0) {
            return 0;
        }

        int[] fail = buildFailure(pattern);
        int i = 0; // text 指针
        int k = 0; // pattern 指针

        while (i < n) {
            if (text.charAt(i) == pattern.charAt(k)) {
                if (k == m - 1) {
                    return i - m + 1;
                }
                i++;
                k++;
            } else if (k > 0) {
                // 复用已经匹配过的前缀信息。
                k = fail[k - 1];
            } else {
                // pattern 第一个字符都不匹配，只能 text 前进。
                i++;
            }
        }

        return -1;
    }

    /**
     * failure[i] 表示 pattern[0..i] 中：
     * 最长的“真前缀 == 后缀”的长度。
     */
    public static int[] buildFailure(String pattern) {
        int[] fail = new int[pattern.length()];
        int i = 1;
        int k = 0;

        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(k)) {
                fail[i] = k + 1;
                i++;
                k++;
            } else if (k > 0) {
                k = fail[k - 1];
            } else {
                fail[i] = 0;
                i++;
            }
        }

        return fail;
    }
}
