import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 数论类算法复习。
 *
 * 覆盖 Week 8：
 * 1. GCD 暴力法
 * 2. Euclid's Algorithm 欧几里得算法
 * 3. 判断质数
 * 4. 使用已发现质数加速
 * 5. Sieve of Eratosthenes 埃拉托色尼筛法
 */
public class NumberTheoryAlgorithms {

    public static void main(String[] args) {
        System.out.println("gcdBruteForce(252, 105) = " + gcdBruteForce(252, 105));
        System.out.println("gcdEuclid(252, 105) = " + gcdEuclid(252, 105));
        System.out.println("Is 29 prime: " + isPrimeSqrt(29));
        System.out.println("Primes up to 30: " + primesBySieve(30));
        System.out.println("Prime flags up to 30: " + Arrays.toString(sieveFlags(30)));
    }

    /**
     * 最大公约数暴力法。
     *
     * 从 1 到 min(m,n) 逐个试，能同时整除就更新 gcd。
     * 时间复杂度 O(n)，其中 n 是较小的数。
     */
    public static int gcdBruteForce(int m, int n) {
        m = Math.abs(m);
        n = Math.abs(n);

        int limit = Math.min(m, n);
        int gcd = 1;

        for (int k = 1; k <= limit; k++) {
            if (m % k == 0 && n % k == 0) {
                gcd = k;
            }
        }

        return gcd;
    }

    /**
     * Euclid's Algorithm 欧几里得算法。
     *
     * 核心公式：gcd(m, n) = gcd(n, m % n)。
     * 每次用余数把问题变小，最坏情况也是 O(log n)。
     */
    public static int gcdEuclid(int m, int n) {
        m = Math.abs(m);
        n = Math.abs(n);

        if (n == 0) {
            return m;
        }

        return gcdEuclid(n, m % n);
    }

    /**
     * 判断质数的直接方法：检查 2 到 n/2。
     *
     * 正确但慢，因为如果 n 有因子，一定有一个因子不超过 sqrt(n)，没必要查到 n/2。
     */
    public static boolean isPrimeByHalf(int n) {
        if (n <= 1) {
            return false;
        }

        for (int divisor = 2; divisor <= n / 2; divisor++) {
            if (n % divisor == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * 判断质数的改进方法：只检查到 sqrt(n)。
     *
     * 如果 n = a * b，且 a 和 b 都大于 sqrt(n)，那么 a*b 会大于 n，矛盾。
     */
    public static boolean isPrimeSqrt(int n) {
        if (n <= 1) {
            return false;
        }

        for (int divisor = 2; divisor * divisor <= n; divisor++) {
            if (n % divisor == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * 使用已经发现的质数作为 divisor。
     *
     * 对每个 candidate，只用之前保存的质数去试除，并且只试到 sqrt(candidate)。
     */
    public static List<Integer> primesUsingStoredPrimes(int limit) {
        List<Integer> primes = new ArrayList<>();

        for (int candidate = 2; candidate <= limit; candidate++) {
            boolean isPrime = true;

            for (int prime : primes) {
                if (prime * prime > candidate) {
                    break;
                }

                if (candidate % prime == 0) {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) {
                primes.add(candidate);
            }
        }

        return primes;
    }

    /**
     * Sieve of Eratosthenes 埃拉托色尼筛法。
     *
     * 思想：
     * 1. 先假设 2..limit 都是质数。
     * 2. 从 2 开始，每找到一个仍为 true 的数 p，就把 p 的倍数标记为 false。
     * 3. 最后仍为 true 的位置就是质数。
     */
    public static boolean[] sieveFlags(int limit) {
        boolean[] isPrime = new boolean[limit + 1];

        if (limit >= 2) {
            Arrays.fill(isPrime, true);
            isPrime[0] = false;
            isPrime[1] = false;
        }

        for (int p = 2; p * p <= limit; p++) {
            if (isPrime[p]) {
                // p*p 之前的 p 的倍数已经被更小的质数处理过。
                for (int multiple = p * p; multiple <= limit; multiple += p) {
                    isPrime[multiple] = false;
                }
            }
        }

        return isPrime;
    }

    /** 把筛法的 boolean 标记转换成质数列表，方便查看。 */
    public static List<Integer> primesBySieve(int limit) {
        boolean[] isPrime = sieveFlags(limit);
        List<Integer> primes = new ArrayList<>();

        for (int i = 2; i <= limit; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }

        return primes;
    }
}
