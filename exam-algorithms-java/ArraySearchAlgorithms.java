import java.util.Arrays;

/**
 * 数组与查找算法复习。
 *
 * 本文件覆盖课件 Week 1.1 中最容易考的内容：
 * 1. 数组遍历、求和、最大值、最小值
 * 2. 数组复制、反转
 * 3. 线性查找 Linear Search
 * 4. 二分查找 Binary Search
 */
public class ArraySearchAlgorithms {

    public static void main(String[] args) {
        int[] data = {6, 4, 1, 9, 7, 3, 2, 8};
        int[] sorted = {1, 2, 3, 4, 6, 7, 8, 9};

        System.out.println("Original array: " + Arrays.toString(data));
        System.out.println("Sum: " + sum(data));
        System.out.println("Maximum: " + max(data));
        System.out.println("Minimum: " + min(data));
        System.out.println("Copied array: " + Arrays.toString(copyByLoop(data)));
        System.out.println("Reversed new array: " + Arrays.toString(reverseToNewArray(data)));

        reverseInPlace(data);
        System.out.println("After in-place reverse: " + Arrays.toString(data));

        System.out.println("Linear search index for 7: " + linearSearch(data, 7));
        System.out.println("Binary search index for 7: " + binarySearch(sorted, 7));
        System.out.println("Recursive binary search index for 7: " + binarySearchRecursive(sorted, 7));
    }

    /** 求数组所有元素之和，遍历每一个元素一次，所以时间复杂度是 O(n)。 */
    public static int sum(int[] array) {
        int total = 0;

        // enhanced for loop 会从左到右依次取出数组元素。
        for (int value : array) {
            total += value;
        }

        return total;
    }

    /** 找最大值：先假设第一个元素最大，再用后面的元素逐个挑战它。 */
    public static int max(int[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("array must not be empty");
        }

        int currentMax = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > currentMax) {
                currentMax = array[i];
            }
        }
        return currentMax;
    }

    /** 找最小值：逻辑和找最大值完全对称。 */
    public static int min(int[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("array must not be empty");
        }

        int currentMin = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < currentMin) {
                currentMin = array[i];
            }
        }
        return currentMin;
    }

    /**
     * 用循环复制数组。
     *
     * 注意：target = source 只会复制引用，不会复制数组对象。
     * 这里创建了新数组，并逐个复制元素，所以 source 和 target 是两个不同数组。
     */
    public static int[] copyByLoop(int[] source) {
        int[] target = new int[source.length];

        for (int i = 0; i < source.length; i++) {
            target[i] = source[i];
        }

        return target;
    }

    /**
     * 生成一个反转后的新数组。
     *
     * 原数组不变；result 是新对象。
     */
    public static int[] reverseToNewArray(int[] source) {
        int[] result = new int[source.length];

        // i 从原数组头部走，j 从新数组尾部走。
        for (int i = 0, j = source.length - 1; i < source.length; i++, j--) {
            result[j] = source[i];
        }

        return result;
    }

    /**
     * 原地反转数组。
     *
     * 原地 in-place 的意思是不创建同样大小的新数组，只交换原数组内部元素。
     */
    public static void reverseInPlace(int[] array) {
        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;

            left++;
            right--;
        }
    }

    /**
     * 线性查找 Linear Search。
     *
     * 从左到右逐个比较。
     * 优点：数组不需要有序。
     * 缺点：最坏情况要看完所有元素，时间复杂度 O(n)。
     */
    public static int linearSearch(int[] array, int key) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == key) {
                return i;
            }
        }

        // Java API 常用 -1 表示没有找到。
        return -1;
    }

    /**
     * 二分查找 Binary Search，迭代版。
     *
     * 前提：数组必须已经按升序排好。
     * 思想：每次比较中间元素，然后丢掉一半搜索范围。
     * 时间复杂度：O(log n)。
     */
    public static int binarySearch(int[] sortedArray, int key) {
        int low = 0;
        int high = sortedArray.length - 1;

        while (low <= high) {
            // 避免 (low + high) 在极大数组中整数溢出。
            int mid = low + (high - low) / 2;

            if (key == sortedArray[mid]) {
                return mid;
            } else if (key < sortedArray[mid]) {
                // key 更小，只可能在左半边。
                high = mid - 1;
            } else {
                // key 更大，只可能在右半边。
                low = mid + 1;
            }
        }

        return -1;
    }

    /** 二分查找递归版，外部调用更方便。 */
    public static int binarySearchRecursive(int[] sortedArray, int key) {
        return binarySearchRecursive(sortedArray, key, 0, sortedArray.length - 1);
    }

    /** 二分查找递归版的真正工作方法。 */
    private static int binarySearchRecursive(int[] sortedArray, int key, int low, int high) {
        if (low > high) {
            return -1;
        }

        int mid = low + (high - low) / 2;

        if (key == sortedArray[mid]) {
            return mid;
        } else if (key < sortedArray[mid]) {
            return binarySearchRecursive(sortedArray, key, low, mid - 1);
        } else {
            return binarySearchRecursive(sortedArray, key, mid + 1, high);
        }
    }
}
