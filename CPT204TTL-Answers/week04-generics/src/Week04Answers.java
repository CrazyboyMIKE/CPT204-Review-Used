import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Week04Answers {
    public static void main(String[] args) {
        System.out.println("Week 04 Answers");
        demoReverse();
        demoShuffle();
        demoMax();
        demoPair();
        demoWildcardPrint();
        demoWildcardCopy();
        demoGenericNotes();
    }

    private static void demoReverse() {
        ArrayList<String> words = new ArrayList<>(Arrays.asList("one", "two", "three", "four"));
        reverse(words);
        System.out.println("Reversed words: " + words);
    }

    // <E> 表示这个方法可以处理任意引用类型元素。
    // 算法本身不关心元素类型，只需要 get 和 set，所以可以写成泛型方法。
    public static <E> void reverse(ArrayList<E> list) {
        int left = 0;
        int right = list.size() - 1;

        while (left < right) {
            E temp = list.get(left);
            list.set(left, list.get(right));
            list.set(right, temp);
            left++;
            right--;
        }
    }

    private static void demoShuffle() {
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        shuffle(values);
        System.out.println("Shuffled integers: " + values);
    }

    // 泛型洗牌和 Week 03 的 Number 洗牌相同，只是这里不限制元素必须是 Number。
    // 因为交换元素不需要数学运算，任何对象类型都可以。
    public static <E> void shuffle(ArrayList<E> list) {
        Random random = new Random(204);
        for (int i = list.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            E temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }
    }

    private static void demoMax() {
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(8, 3, 12, 7));
        System.out.println("Max integer: " + max(values));
    }

    // <E extends Comparable<E>> 是泛型上界。
    // 它的意思是：E 必须实现 Comparable<E>，否则无法调用 compareTo。
    public static <E extends Comparable<E>> E max(ArrayList<E> list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("List must not be empty.");
        }

        E best = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).compareTo(best) > 0) {
                best = list.get(i);
            }
        }
        return best;
    }

    private static void demoPair() {
        Pair<String, Integer> pair = new Pair<>("age", 21);
        System.out.println("Pair first: " + pair.getFirst() + ", second: " + pair.getSecond());
    }

    // 原来的 Pair<E> 只能让两个字段使用同一种类型。
    // 改成 Pair<F, S> 后，first 和 second 可以是不同类型。
    static class Pair<F, S> {
        private final F first;
        private final S second;

        public Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }

        public F getFirst() {
            return first;
        }

        public S getSecond() {
            return second;
        }
    }

    private static void demoWildcardPrint() {
        ArrayList<Integer> integers = new ArrayList<>(Arrays.asList(1, 2, 3));
        print(integers);
    }

    // ArrayList<Integer> 不是 ArrayList<Object> 的子类。
    // 如果只是读取并打印，不需要知道具体元素类型，用 ArrayList<?> 最合适。
    public static void print(ArrayList<?> list) {
        System.out.println("Wildcard print: " + list);
    }

    private static void demoWildcardCopy() {
        ArrayList<Integer> source = new ArrayList<>(Arrays.asList(10, 20, 30));
        ArrayList<Number> target = new ArrayList<>();
        copy(source, target);
        System.out.println("Copied values: " + target);
    }

    // source 使用 ? extends T：它是 T 或 T 的子类列表，安全地“读出 T”。
    // target 使用 ? super T：它是 T 或 T 的父类列表，安全地“写入 T”。
    // 口诀是 PECS：Producer Extends, Consumer Super。
    public static <T> void copy(ArrayList<? extends T> source, ArrayList<? super T> target) {
        for (T item : source) {
            target.add(item);
        }
    }

    private static void demoGenericNotes() {
        GenericStack<Integer> stack = new GenericStack<>();
        stack.push(10);
        stack.push(20);
        System.out.println("Generic stack pop: " + stack.pop());
        System.out.println("Generic notes are explained in comments.");
    }

    static class GenericStack<E> {
        private final ArrayList<E> values = new ArrayList<>();

        public void push(E value) {
            values.add(value);
        }

        public E pop() {
            return values.remove(values.size() - 1);
        }
    }

    // 泛型常考概念：
    // 1. 泛型提供编译期类型安全，减少强制类型转换。
    // 2. 泛型类型参数只能使用引用类型，不能写 ArrayList<int>，要写 ArrayList<Integer>。
    // 3. GenericStack<Integer> 不是 GenericStack<Number> 的子类，这叫泛型不变性。
    // 4. 类型擦除会在编译后移除大部分泛型类型信息，所以运行期不能直接 new E()。
    // 5. ? extends Number 适合读取 Number，? super Integer 适合写入 Integer。
}
