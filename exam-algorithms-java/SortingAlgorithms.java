import java.util.Arrays;

/**
 * 排序算法复习。
 *
 * 覆盖课件中出现的：
 * 1. Bubble Sort 冒泡排序
 * 2. Optimized Bubble Sort 优化冒泡排序
 * 3. Selection Sort 选择排序
 * 4. Insertion Sort 插入排序
 * 5. Merge Sort 归并排序
 * 6. Quick Sort 快速排序
 */
public class SortingAlgorithms {

    public static void main(String[] args) {
        int[] original = {9, 4, 6, 2, 8, 1, 7, 3, 5};

        runDemo("Bubble sort", original, SortingAlgorithms::bubbleSort);
        runDemo("Optimized bubble sort", original, SortingAlgorithms::optimizedBubbleSort);
        runDemo("Selection sort", original, SortingAlgorithms::selectionSort);
        runDemo("Insertion sort", original, SortingAlgorithms::insertionSort);
        runDemo("Merge sort", original, SortingAlgorithms::mergeSort);
        runDemo("Quick sort", original, SortingAlgorithms::quickSort);
    }

    private interface Sorter {
        void sort(int[] array);
    }

    private static void runDemo(String name, int[] original, Sorter sorter) {
        int[] copy = Arrays.copyOf(original, original.length);
        sorter.sort(copy);
        System.out.println(name + ": " + Arrays.toString(copy));
    }

    /**
     * 冒泡排序 Bubble Sort。
     *
     * 每一轮把相邻元素两两比较，顺序错就交换。
     * 一轮结束后，当前未排序部分的最大值会“冒泡”到尾部。
     * 时间复杂度：O(n^2)。
     */
    public static void bubbleSort(int[] array) {
        for (int pass = 1; pass < array.length; pass++) {
            // pass 轮后，末尾 pass 个元素已经就位，所以内层不用检查那部分。
            for (int i = 0; i < array.length - pass; i++) {
                if (array[i] > array[i + 1]) {
                    swap(array, i, i + 1);
                }
            }
        }
    }

    /**
     * 优化冒泡排序。
     *
     * 如果某一轮完全没有发生交换，说明数组已经有序，可以提前停止。
     * 已排序数组的最好情况可以到 O(n)。
     */
    public static void optimizedBubbleSort(int[] array) {
        for (int pass = 1; pass < array.length; pass++) {
            boolean swapped = false;

            for (int i = 0; i < array.length - pass; i++) {
                if (array[i] > array[i + 1]) {
                    swap(array, i, i + 1);
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }

    /**
     * 选择排序 Selection Sort。
     *
     * 第 i 轮从 i..末尾中找最小值，放到 i 位置。
     * 比较次数是 (n - 1) + (n - 2) + ... + 1，所以是 O(n^2)。
     */
    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int currentMinIndex = i;

            // 找出未排序区间中的最小值下标。
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[currentMinIndex]) {
                    currentMinIndex = j;
                }
            }

            if (currentMinIndex != i) {
                swap(array, i, currentMinIndex);
            }
        }
    }

    /**
     * 插入排序 Insertion Sort。
     *
     * 把数组分成“左侧已排序”和“右侧未排序”。
     * 每次拿出一个新元素，把它插入左侧正确位置。
     * 最坏 O(n^2)，但对接近有序的数据很友好。
     */
    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            int j = i - 1;

            // 把比 current 大的元素向右挪，为 current 腾位置。
            while (j >= 0 && array[j] > current) {
                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = current;
        }
    }

    /** 归并排序外部入口。 */
    public static void mergeSort(int[] array) {
        if (array.length <= 1) {
            return;
        }

        int[] temp = new int[array.length];
        mergeSort(array, temp, 0, array.length - 1);
    }

    /**
     * 归并排序 Merge Sort。
     *
     * 递归地把数组分成两半，分别排好，再合并。
     * 每层合并处理 n 个元素，层数 log n，所以时间复杂度 O(n log n)。
     * 需要临时数组，所以额外空间 O(n)。
     */
    private static void mergeSort(int[] array, int[] temp, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSort(array, temp, left, mid);
        mergeSort(array, temp, mid + 1, right);
        merge(array, temp, left, mid, right);
    }

    /** 把两个已经有序的区间 [left..mid] 和 [mid+1..right] 合并成一个有序区间。 */
    private static void merge(int[] array, int[] temp, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = array[i++];
        }

        while (j <= right) {
            temp[k++] = array[j++];
        }

        for (int p = left; p <= right; p++) {
            array[p] = temp[p];
        }
    }

    /** 快速排序外部入口。 */
    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    /**
     * 快速排序 Quick Sort。
     *
     * 先 partition，把 pivot 放到最终位置；
     * 再递归排序 pivot 左右两边。
     * 平均 O(n log n)，但如果 pivot 总是很差，最坏 O(n^2)。
     */
    private static void quickSort(int[] array, int first, int last) {
        if (last > first) {
            int pivotIndex = partition(array, first, last);
            quickSort(array, first, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, last);
        }
    }

    /**
     * 课件风格 partition：选择第一个元素作为 pivot。
     *
     * low 从左向右找大于 pivot 的元素；
     * high 从右向左找小于等于 pivot 的元素；
     * 两边都找到后交换。
     */
    private static int partition(int[] array, int first, int last) {
        int pivot = array[first];
        int low = first + 1;
        int high = last;

        while (high > low) {
            while (low <= high && array[low] <= pivot) {
                low++;
            }

            while (low <= high && array[high] > pivot) {
                high--;
            }

            if (high > low) {
                swap(array, low, high);
            }
        }

        // 找到 pivot 最终应该放的位置。
        while (high > first && array[high] >= pivot) {
            high--;
        }

        if (pivot > array[high]) {
            array[first] = array[high];
            array[high] = pivot;
            return high;
        }

        return first;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
