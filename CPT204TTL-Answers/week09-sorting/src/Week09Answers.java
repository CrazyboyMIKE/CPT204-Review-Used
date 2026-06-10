import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Week09Answers {
    public static void main(String[] args) {
        System.out.println("Week 09 Answers");

        int[] bubbleValues = {2, 9, 5, 4, 8, 1};
        bubbleSortPasses(bubbleValues, 3);
        System.out.println("After three bubble passes: " + Arrays.toString(bubbleValues));

        int[] sortedValues = {1, 2, 3, 4, 5};
        optimizedBubbleSort(sortedValues);
        System.out.println("Optimized bubble result: " + Arrays.toString(sortedValues));

        System.out.println("Possible heap values: " + possibleUnknownHeapValues());

        int[] quickValues = {5, 1, 8, 3, 7, 2};
        quickSortDescending(quickValues);
        System.out.println("Quick sort descending: " + Arrays.toString(quickValues));

        Integer[] mergeValues = {9, 4, 6, 2, 8, 1};
        mergeSort(mergeValues);
        System.out.println("Generic merge sort: " + Arrays.toString(mergeValues));

        String[] words = {"pear", "apple", "banana"};
        insertionSort(words, Comparator.naturalOrder());
        System.out.println("Generic insertion sort: " + Arrays.toString(words));
    }

    // Bubble sort 每一轮都会把当前未排序部分的最大值“冒泡”到右端。
    // passes 参数让我们只执行指定轮数，正好回答题目“第三轮之后数组是什么”。
    public static void bubbleSortPasses(int[] values, int passes) {
        for (int pass = 1; pass <= passes; pass++) {
            for (int i = 0; i < values.length - pass; i++) {
                if (values[i] > values[i + 1]) {
                    int temp = values[i];
                    values[i] = values[i + 1];
                    values[i + 1] = temp;
                }
            }
        }
    }

    // 优化版 bubble sort 使用 swapped 标记。
    // 如果某一轮没有任何交换，说明数组已经有序，可以提前结束。
    // 对已经有序的数组，最好情况时间复杂度从 O(n^2) 降到 O(n)。
    public static void optimizedBubbleSort(int[] values) {
        boolean swapped = true;
        for (int pass = 1; pass < values.length && swapped; pass++) {
            swapped = false;
            for (int i = 0; i < values.length - pass; i++) {
                if (values[i] > values[i + 1]) {
                    int temp = values[i];
                    values[i] = values[i + 1];
                    values[i + 1] = temp;
                    swapped = true;
                }
            }
        }
    }

    // 最大堆要求父节点大于等于子节点。
    // 未知节点的父节点是 30，子节点是 26 和 27。
    // 如果只考虑整数，未知值必须满足 30 >= x >= 27，所以可能是 27、28、29、30。
    public static List<Integer> possibleUnknownHeapValues() {
        ArrayList<Integer> values = new ArrayList<>();
        for (int value = 27; value <= 30; value++) {
            values.add(value);
        }
        return values;
    }

    public static void quickSortDescending(int[] values) {
        quickSortDescending(values, 0, values.length - 1);
    }

    private static void quickSortDescending(int[] values, int first, int last) {
        if (first < last) {
            int pivotIndex = partitionDescending(values, first, last);
            quickSortDescending(values, first, pivotIndex - 1);
            quickSortDescending(values, pivotIndex + 1, last);
        }
    }

    // 降序 quick sort 的分区目标是：pivot 左边都 >= pivot，右边都 <= pivot。
    // 因此 low 要跳过已经大于等于 pivot 的元素，high 要跳过已经小于 pivot 的元素。
    private static int partitionDescending(int[] values, int first, int last) {
        int pivot = values[first];
        int low = first + 1;
        int high = last;

        while (high > low) {
            while (low <= high && values[low] >= pivot) {
                low++;
            }
            while (low <= high && values[high] < pivot) {
                high--;
            }
            if (high > low) {
                int temp = values[high];
                values[high] = values[low];
                values[low] = temp;
            }
        }

        while (high > first && values[high] <= pivot) {
            high--;
        }

        if (pivot < values[high]) {
            values[first] = values[high];
            values[high] = pivot;
            return high;
        }
        return first;
    }

    public static <E extends Comparable<E>> void mergeSort(E[] values) {
        E[] temp = values.clone();
        mergeSort(values, temp, 0, values.length - 1, Comparable::compareTo);
    }

    public static <E> void mergeSort(E[] values, Comparator<? super E> comparator) {
        E[] temp = values.clone();
        mergeSort(values, temp, 0, values.length - 1, comparator);
    }

    // Merge sort 先递归拆分，再把两个已经有序的部分合并。
    // 它的时间复杂度是 O(n log n)，但需要 O(n) 额外数组空间。
    private static <E> void mergeSort(E[] values, E[] temp, int left, int right,
                                      Comparator<? super E> comparator) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSort(values, temp, left, mid, comparator);
        mergeSort(values, temp, mid + 1, right, comparator);
        merge(values, temp, left, mid, right, comparator);
    }

    private static <E> void merge(E[] values, E[] temp, int left, int mid, int right,
                                  Comparator<? super E> comparator) {
        for (int i = left; i <= right; i++) {
            temp[i] = values[i];
        }

        int leftIndex = left;
        int rightIndex = mid + 1;
        int target = left;

        while (leftIndex <= mid && rightIndex <= right) {
            if (comparator.compare(temp[leftIndex], temp[rightIndex]) <= 0) {
                values[target++] = temp[leftIndex++];
            } else {
                values[target++] = temp[rightIndex++];
            }
        }

        while (leftIndex <= mid) {
            values[target++] = temp[leftIndex++];
        }
    }

    public static <E extends Comparable<E>> void insertionSort(E[] values) {
        insertionSort(values, Comparable::compareTo);
    }

    public static <E> void insertionSort(E[] values, Comparator<? super E> comparator) {
        for (int i = 1; i < values.length; i++) {
            E current = values[i];
            int j = i - 1;

            while (j >= 0 && comparator.compare(values[j], current) > 0) {
                values[j + 1] = values[j];
                j--;
            }

            values[j + 1] = current;
        }
    }
}
