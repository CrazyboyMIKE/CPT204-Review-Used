import java.util.ArrayList;
import java.util.Arrays;

/**
 * 二叉堆 Binary Heap 与堆排序 Heap Sort。
 *
 * 课件使用最大堆 Max Heap：
 * 1. 堆是 complete binary tree
 * 2. 每个父节点 >= 它的子节点
 * 3. 最大值永远在 root
 */
public class HeapAlgorithms {

    public static void main(String[] args) {
        Integer[] values = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
        heapSort(values);
        System.out.println("Heap sort result: " + Arrays.toString(values));

        MaxHeap<Integer> heap = new MaxHeap<>();
        heap.add(42);
        heap.add(59);
        heap.add(32);
        heap.add(62);
        heap.add(9);
        System.out.println("Consecutive root removals: " + heap.remove() + ", " + heap.remove());
    }

    /**
     * 最大堆。
     *
     * 用 ArrayList 存储 complete binary tree：
     * index i 的左孩子是 2i + 1
     * index i 的右孩子是 2i + 2
     * index i 的父节点是 (i - 1) / 2
     */
    public static class MaxHeap<E extends Comparable<E>> {
        private final ArrayList<E> list = new ArrayList<>();

        /** 向堆中加入元素。 */
        public void add(E newObject) {
            // 1. 新元素先放在数组末尾，也就是 complete tree 的最后一个位置。
            list.add(newObject);
            int currentIndex = list.size() - 1;

            // 2. 如果新元素比父节点大，就向上交换，直到堆性质恢复。
            while (currentIndex > 0) {
                int parentIndex = (currentIndex - 1) / 2;

                if (list.get(currentIndex).compareTo(list.get(parentIndex)) > 0) {
                    swap(currentIndex, parentIndex);
                    currentIndex = parentIndex;
                } else {
                    break;
                }
            }
        }

        /**
         * 移除并返回 root。
         *
         * 最大堆的 root 是最大值。
         * remove root 后，要用最后一个节点补到 root，再向下调整。
         */
        public E remove() {
            if (list.isEmpty()) {
                return null;
            }

            E removedObject = list.get(0);

            // 1. 把最后一个元素移动到 root。
            list.set(0, list.get(list.size() - 1));
            list.remove(list.size() - 1);

            int currentIndex = 0;

            // 2. 当前节点比孩子小，就和较大的孩子交换，继续向下。
            while (currentIndex < list.size()) {
                int leftChildIndex = 2 * currentIndex + 1;
                int rightChildIndex = 2 * currentIndex + 2;

                if (leftChildIndex >= list.size()) {
                    break;
                }

                int maxChildIndex = leftChildIndex;
                if (rightChildIndex < list.size()
                        && list.get(rightChildIndex).compareTo(list.get(leftChildIndex)) > 0) {
                    maxChildIndex = rightChildIndex;
                }

                if (list.get(currentIndex).compareTo(list.get(maxChildIndex)) < 0) {
                    swap(currentIndex, maxChildIndex);
                    currentIndex = maxChildIndex;
                } else {
                    break;
                }
            }

            return removedObject;
        }

        public int getSize() {
            return list.size();
        }

        private void swap(int i, int j) {
            E temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }
    }

    /**
     * 堆排序 Heap Sort。
     *
     * 1. 把所有元素加入最大堆。
     * 2. 每次 remove root 得到当前最大值。
     * 3. 从数组末尾往前填，最终得到升序数组。
     *
     * 时间复杂度：n 次 add/remove，每次 O(log n)，总共 O(n log n)。
     */
    public static <E extends Comparable<E>> void heapSort(E[] array) {
        MaxHeap<E> heap = new MaxHeap<>();

        for (E value : array) {
            heap.add(value);
        }

        for (int i = array.length - 1; i >= 0; i--) {
            array[i] = heap.remove();
        }
    }
}
