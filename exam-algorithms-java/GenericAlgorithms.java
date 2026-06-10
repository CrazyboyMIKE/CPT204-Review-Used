import java.util.Arrays;
import java.lang.reflect.Array;

/**
 * 泛型算法复习。
 *
 * 覆盖 Generics 课件中可能考到的算法模板：
 * 1. generic max
 * 2. generic selection sort
 * 3. generic matrix addition and multiplication
 */
public class GenericAlgorithms {

    public static void main(String[] args) {
        Integer[] numbers = {5, 1, 9, 3, 7};
        genericSelectionSort(numbers);
        System.out.println("Generic selection sort: " + Arrays.toString(numbers));

        System.out.println("max(\"Java\", \"HTML\") = " + max("Java", "HTML"));

        IntegerMatrix matrix = new IntegerMatrix();
        Integer[][] a = {
                {1, 2},
                {3, 4}
        };
        Integer[][] b = {
                {5, 6},
                {7, 8}
        };

        System.out.println("Matrix addition:");
        printMatrix(matrix.addMatrix(a, b));

        System.out.println("Matrix multiplication:");
        printMatrix(matrix.multiplyMatrix(a, b));
    }

    /**
     * 泛型 max 方法。
     *
     * E 必须 extends Comparable<E>，否则无法调用 compareTo 比较大小。
     */
    public static <E extends Comparable<E>> E max(E first, E second) {
        if (first.compareTo(second) >= 0) {
            return first;
        }
        return second;
    }

    /**
     * 泛型选择排序。
     *
     * 和 int[] 的 selection sort 思想完全一样；
     * 区别是比较时不能用 < 或 >，而要用 compareTo。
     */
    public static <E extends Comparable<E>> void genericSelectionSort(E[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            int currentMinIndex = i;

            for (int j = i + 1; j < list.length; j++) {
                if (list[j].compareTo(list[currentMinIndex]) < 0) {
                    currentMinIndex = j;
                }
            }

            if (currentMinIndex != i) {
                E temp = list[i];
                list[i] = list[currentMinIndex];
                list[currentMinIndex] = temp;
            }
        }
    }

    /**
     * 泛型矩阵算法模板。
     *
     * E extends Number 表示矩阵元素必须是数字类型；
     * 但 Number 本身不知道怎么做加法和乘法，所以 add/multiply/zero 交给子类实现。
     */
    public abstract static class GenericMatrix<E extends Number> {

        protected abstract E add(E first, E second);

        protected abstract E multiply(E first, E second);

        protected abstract E zero();

        /**
         * 矩阵加法。
         *
         * 两个矩阵同一位置的元素相加。
         */
        public E[][] addMatrix(E[][] first, E[][] second) {
            checkSameShape(first, second);

            @SuppressWarnings("unchecked")
            E[][] result = (E[][]) Array.newInstance(
                    first[0][0].getClass(), first.length, first[0].length);

            for (int row = 0; row < first.length; row++) {
                for (int column = 0; column < first[row].length; column++) {
                    result[row][column] = add(first[row][column], second[row][column]);
                }
            }

            return result;
        }

        /**
         * 矩阵乘法。
         *
         * result[i][j] = first 第 i 行 与 second 第 j 列 的点积。
         */
        public E[][] multiplyMatrix(E[][] first, E[][] second) {
            if (first[0].length != second.length) {
                throw new IllegalArgumentException("first matrix columns must equal second matrix rows");
            }

            @SuppressWarnings("unchecked")
            E[][] result = (E[][]) Array.newInstance(
                    first[0][0].getClass(), first.length, second[0].length);

            for (int row = 0; row < result.length; row++) {
                for (int column = 0; column < result[row].length; column++) {
                    E total = zero();

                    for (int k = 0; k < first[row].length; k++) {
                        total = add(total, multiply(first[row][k], second[k][column]));
                    }

                    result[row][column] = total;
                }
            }

            return result;
        }

        private void checkSameShape(E[][] first, E[][] second) {
            if (first.length != second.length || first[0].length != second[0].length) {
                throw new IllegalArgumentException("matrices must have the same shape");
            }
        }
    }

    /** Integer 矩阵：具体定义 Integer 如何相加、相乘，以及零元素是什么。 */
    public static class IntegerMatrix extends GenericMatrix<Integer> {
        @Override
        protected Integer add(Integer first, Integer second) {
            return first + second;
        }

        @Override
        protected Integer multiply(Integer first, Integer second) {
            return first * second;
        }

        @Override
        protected Integer zero() {
            return 0;
        }
    }

    private static void printMatrix(Number[][] matrix) {
        for (Number[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
