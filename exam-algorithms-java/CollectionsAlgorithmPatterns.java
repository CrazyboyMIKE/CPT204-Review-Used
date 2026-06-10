import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;

/**
 * Java Collections Framework 中讲过的典型算法模式。
 *
 * 这些不一定是“手写底层算法”，但很可能在考试或项目解释中出现：
 * 1. Iterator 安全删除
 * 2. Comparator / Lambda 排序
 * 3. HashSet 做 membership testing
 * 4. Map 做词频统计
 * 5. PriorityQueue 做优先级处理
 */
public class CollectionsAlgorithmPatterns {

    public static void main(String[] args) {
        iteratorRemoveDemo();
        comparatorSortDemo();
        keywordCountingDemo();
        wordFrequencyDemo();
        priorityQueueDemo();
    }

    /**
     * 迭代器安全删除。
     *
     * 遍历集合时如果要删除元素，应该使用 Iterator.remove()。
     * 不要在 enhanced for-loop 中直接 list.remove(e)，否则容易触发并发修改异常。
     */
    public static void iteratorRemoveDemo() {
        List<String> cities = new ArrayList<>(Arrays.asList("Xi'an", "Paris", "NY", "London"));
        Iterator<String> iterator = cities.iterator();

        while (iterator.hasNext()) {
            String city = iterator.next();
            if (city.length() <= 2) {
                iterator.remove();
            }
        }

        System.out.println("After iterator removal of short names: " + cities);
    }

    /**
     * Comparator 排序。
     *
     * Comparable 是类内部自然顺序；
     * Comparator 是外部自定义规则，可以灵活切换。
     */
    public static void comparatorSortDemo() {
        List<Loan> loans = new ArrayList<>();
        loans.add(new Loan(5000, 4.5));
        loans.add(new Loan(3000, 5.1));
        loans.add(new Loan(5000, 3.8));

        loans.sort(
                Comparator.comparingDouble(Loan::getAmount)
                        .thenComparingDouble(Loan::getInterestRate)
        );

        System.out.println("Sorted by amount, then rate: " + loans);
    }

    private static class Loan {
        private final double amount;
        private final double interestRate;

        Loan(double amount, double interestRate) {
            this.amount = amount;
            this.interestRate = interestRate;
        }

        double getAmount() {
            return amount;
        }

        double getInterestRate() {
            return interestRate;
        }

        @Override
        public String toString() {
            return "{amount=" + amount + ", rate=" + interestRate + "}";
        }
    }

    /**
     * HashSet 做 Java keyword counting。
     *
     * 课件重点：HashSet 的 contains 平均 O(1)，适合大量 membership tests。
     */
    public static void keywordCountingDemo() {
        Set<String> keywords = new HashSet<>(Arrays.asList(
                "abstract", "assert", "boolean", "break", "byte", "case", "catch",
                "char", "class", "const", "continue", "default", "do", "double",
                "else", "enum", "extends", "final", "finally", "float", "for",
                "goto", "if", "implements", "import", "instanceof", "int",
                "interface", "long", "native", "new", "package", "private",
                "protected", "public", "return", "short", "static", "strictfp",
                "super", "switch", "synchronized", "this", "throw", "throws",
                "transient", "try", "void", "volatile", "while", "true", "false", "null"
        ));

        String source = "public class Test { public static void main(String[] args) { int x = 1; } }";
        int count = 0;

        for (String token : source.split("\\s+|\\p{Punct}+")) {
            if (keywords.contains(token)) {
                count++;
            }
        }

        System.out.println("Java keyword count: " + count);
    }

    /**
     * Map 词频统计。
     *
     * TreeMap 会按 key 排序输出；
     * 如果想按 value 排序，需要把 entrySet 转成 List 再 sort。
     */
    public static void wordFrequencyDemo() {
        String text = "Good morning. Have a good class. Have a good visit.";
        Map<String, Integer> frequency = new TreeMap<>();

        for (String word : text.split("\\s+|\\p{Punct}+")) {
            if (word.length() == 0) {
                continue;
            }

            String key = word.toLowerCase();
            frequency.put(key, frequency.getOrDefault(key, 0) + 1);
        }

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(frequency.entrySet());
        entries.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        System.out.println("Word frequency sorted by value descending: " + entries);
    }

    /**
     * PriorityQueue 优先级队列。
     *
     * Queue 是 FIFO；
     * PriorityQueue 是按优先级出队，默认小的元素先出。
     */
    public static void priorityQueueDemo() {
        PriorityQueue<Task> tasks = new PriorityQueue<>(
                Comparator.comparingInt(Task::getPriority).reversed()
        );

        tasks.offer(new Task("Routine inspection", 1));
        tasks.offer(new Task("Road accident", 5));
        tasks.offer(new Task("Traffic light failure", 3));

        System.out.print("Tasks processed by priority: ");
        while (!tasks.isEmpty()) {
            System.out.print(tasks.poll() + " ");
        }
        System.out.println();
    }

    private static class Task {
        private final String name;
        private final int priority;

        Task(String name, int priority) {
            this.name = name;
            this.priority = priority;
        }

        int getPriority() {
            return priority;
        }

        @Override
        public String toString() {
            return name + "(priority=" + priority + ")";
        }
    }
}
