import java.util.ArrayList;
import java.util.List;

/**
 * 递归、动态规划与 Tower of Hanoi。
 *
 * 覆盖 Week 8 中：
 * 1. 递归 Fibonacci 的重复子问题
 * 2. 动态规划 Fibonacci
 * 3. Tower of Hanoi 的指数级递归
 */
public class RecursionDynamicProgrammingAlgorithms {

    public static void main(String[] args) {
        System.out.println("fibRecursive(8) = " + fibonacciRecursive(8));
        System.out.println("fibDP(8) = " + fibonacciDP(8));

        List<String> moves = towerOfHanoi(3, "A", "B", "C");
        System.out.println("Tower of Hanoi moves for 3 disks:");
        for (String move : moves) {
            System.out.println(move);
        }
    }

    /**
     * 朴素递归 Fibonacci。
     *
     * 问题：fib(n - 1) 和 fib(n - 2) 会继续重复计算很多相同子问题。
     * 例如 fib(6) 里面 fib(4) 会被算多次。
     * 时间复杂度接近 O(2^n)。
     */
    public static long fibonacciRecursive(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must not be negative");
        }

        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    /**
     * 动态规划 Fibonacci。
     *
     * 思想：每个子问题只算一次，把前两个结果保存下来。
     * 时间复杂度 O(n)，额外空间 O(1)。
     */
    public static long fibonacciDP(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must not be negative");
        }

        if (n == 0) {
            return 0;
        }

        long previous = 0;
        long current = 1;

        for (int i = 2; i <= n; i++) {
            long next = previous + current;
            previous = current;
            current = next;
        }

        return current;
    }

    /**
     * Tower of Hanoi 汉诺塔。
     *
     * 要把 n 个盘子从 from 移到 to，借助 aux：
     * 1. 先把 n-1 个盘子从 from 移到 aux
     * 2. 把最大盘子从 from 移到 to
     * 3. 再把 n-1 个盘子从 aux 移到 to
     *
     * 递推式 T(n) = 2T(n - 1) + 1，时间复杂度 O(2^n)。
     */
    public static List<String> towerOfHanoi(int n, String from, String to, String aux) {
        if (n < 0) {
            throw new IllegalArgumentException("disk count must not be negative");
        }

        List<String> moves = new ArrayList<>();
        towerOfHanoi(n, from, to, aux, moves);
        return moves;
    }

    private static void towerOfHanoi(int n, String from, String to, String aux, List<String> moves) {
        if (n == 0) {
            return;
        }

        towerOfHanoi(n - 1, from, aux, to, moves);
        moves.add("Move disk " + n + " from " + from + " to " + to);
        towerOfHanoi(n - 1, aux, to, from, moves);
    }
}
