import java.util.Arrays;
import java.util.Random;

public class Week01Answers {
    public static void main(String[] args) {
        System.out.println("Week 01 Answers");
        demoReverseInPlace();
        demoConsecutiveFour();
        demoLongestRun();
        demoStopWatch();
        demoStudentRecords();
        demoBookRecords();
        demoAssignGrades();
        demoStock();
    }

    private static void demoReverseInPlace() {
        double[] values = {1.5, 2.5, 3.5, 4.5, 5.5};
        reverseInPlace(values);
        System.out.println("Reversed array: " + Arrays.toString(values));
    }

    // 本题要求“原地反转”，意思是不创建第二个数组保存结果。
    // 做法是使用双指针：left 从左边开始，right 从右边开始。
    // 每次交换 values[left] 和 values[right]，然后 left 右移、right 左移。
    // 当 left >= right 时，说明所有需要交换的位置都已经完成。
    public static void reverseInPlace(double[] values) {
        int left = 0;
        int right = values.length - 1;

        while (left < right) {
            double temp = values[left];
            values[left] = values[right];
            values[right] = temp;
            left++;
            right--;
        }
    }

    private static void demoConsecutiveFour() {
        int[] values = {1, 2, 2, 2, 2, 5, 6};
        System.out.println("Has four consecutive equal values: " + isConsecutiveFour(values));
    }

    // “four consecutive numbers” 在这道题中指连续四个位置上的值完全相同。
    // 循环只需要走到 length - 4，因为从后面不足四个元素的位置开始不可能形成四连。
    // 只要找到一次 values[i]、values[i+1]、values[i+2]、values[i+3] 相等，就立刻返回 true。
    public static boolean isConsecutiveFour(int[] values) {
        for (int i = 0; i <= values.length - 4; i++) {
            if (values[i] == values[i + 1]
                    && values[i] == values[i + 2]
                    && values[i] == values[i + 3]) {
                return true;
            }
        }
        return false;
    }

    private static void demoLongestRun() {
        int[] values = {4, 4, 1, 1, 1, 2, 3, 3};
        System.out.println("Longest run length: " + longestRun(values));
    }

    // longest run 是“最长连续相同段”的长度。
    // currentRun 记录当前连续段长度，bestRun 记录目前见过的最大长度。
    // 遇到和前一个元素相同的值，当前段长度加一；否则当前段重新从 1 开始。
    // 空数组没有任何连续段，所以返回 0。
    public static int longestRun(int[] values) {
        if (values.length == 0) {
            return 0;
        }

        int currentRun = 1;
        int bestRun = 1;

        for (int i = 1; i < values.length; i++) {
            if (values[i] == values[i - 1]) {
                currentRun++;
            } else {
                currentRun = 1;
            }
            bestRun = Math.max(bestRun, currentRun);
        }

        return bestRun;
    }

    private static void demoStopWatch() {
        int[] values = new int[20_000];
        Random random = new Random(204);

        for (int i = 0; i < values.length; i++) {
            values[i] = random.nextInt(100_000);
        }

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        selectionSort(values);
        stopWatch.stop();
        System.out.println("Selection sort elapsed time: " + stopWatch.getElapsedTime() + " ms");
    }

    // StopWatch 用毫秒时间戳记录开始和结束时间。
    // System.currentTimeMillis() 返回当前时间距离 Unix epoch 的毫秒数。
    // elapsed time = endTime - startTime。
    static class StopWatch {
        private long startTime;
        private long endTime;

        public StopWatch() {
            startTime = System.currentTimeMillis();
            endTime = startTime;
        }

        public long getStartTime() {
            return startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void start() {
            startTime = System.currentTimeMillis();
        }

        public void stop() {
            endTime = System.currentTimeMillis();
        }

        public long getElapsedTime() {
            return endTime - startTime;
        }
    }

    // selection sort 每一轮从未排序部分找最小值，放到当前轮的起点。
    // 它的时间复杂度稳定为 O(n^2)，适合用来展示 StopWatch 的计时效果。
    public static void selectionSort(int[] values) {
        for (int i = 0; i < values.length - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < values.length; j++) {
                if (values[j] < values[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = values[i];
            values[i] = values[minIndex];
            values[minIndex] = temp;
        }
    }

    private static void demoStudentRecords() {
        Student alice = new Student("Alice", "Chen", "alice@example.com", 2);
        Student bob = new Student("Bob", "Smith", "bob@example.com", 4);
        System.out.println(alice);
        System.out.println("Alice has a smaller group number than Bob: " + alice.less(bob));
    }

    // Student 是一个普通数据类，字段保持 private，外部通过方法使用对象。
    // less 方法不是比较姓名，而是按照题目要求比较 groupNumber。
    static class Student {
        private final String firstName;
        private final String lastName;
        private final String email;
        private final int groupNumber;

        public Student(String firstName, String lastName, String email, int groupNumber) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.groupNumber = groupNumber;
        }

        public boolean less(Student other) {
            return groupNumber < other.groupNumber;
        }

        @Override
        public String toString() {
            return "Student{firstName='" + firstName + "', lastName='" + lastName
                    + "', email='" + email + "', groupNumber=" + groupNumber + "}";
        }
    }

    private static void demoBookRecords() {
        Book[] books = {
                new Book("Data Structures", "Liang", 2020, "9780000000001"),
                new Book("Algorithms", "Sedgewick", 2011, "9780000000002"),
                new Book("Clean Code", "Martin", 2008, "9780000000003"),
                new Book("Effective Java", "Bloch", 2018, "9780000000004")
        };

        for (Book book : books) {
            System.out.println(book);
        }

        System.out.println("Clean Code was published earlier than Effective Java: "
                + books[2].less(books[3]));
    }

    // Book 的 less 方法按照出版年份比较。
    // 如果题目没有要求自然排序接口，普通 boolean 方法就足够表达“是否更小”。
    static class Book {
        private final String title;
        private final String author;
        private final int year;
        private final String isbn;

        public Book(String title, String author, int year, String isbn) {
            this.title = title;
            this.author = author;
            this.year = year;
            this.isbn = isbn;
        }

        public boolean less(Book other) {
            return year < other.year;
        }

        @Override
        public String toString() {
            return "Book{title='" + title + "', author='" + author
                    + "', year=" + year + ", isbn='" + isbn + "'}";
        }
    }

    private static void demoAssignGrades() {
        int[] scores = {40, 55, 70, 58};
        char[] grades = assignGrades(scores);
        System.out.println("Assigned grades: " + Arrays.toString(grades));
    }

    // 成绩规则以 best score 为基准。
    // 如果分数 >= best - 10，给 A；>= best - 20，给 B；依次类推。
    // 注意判断顺序必须从高到低，否则高分会先被低等级条件捕获。
    public static char[] assignGrades(int[] scores) {
        int best = 0;
        for (int score : scores) {
            best = Math.max(best, score);
        }

        char[] grades = new char[scores.length];
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] >= best - 10) {
                grades[i] = 'A';
            } else if (scores[i] >= best - 20) {
                grades[i] = 'B';
            } else if (scores[i] >= best - 30) {
                grades[i] = 'C';
            } else if (scores[i] >= best - 40) {
                grades[i] = 'D';
            } else {
                grades[i] = 'F';
            }
        }
        return grades;
    }

    private static void demoStock() {
        Stock stock = new Stock("ORCL", "Oracle Corporation");
        stock.setPreviousClosingPrice(34.5);
        stock.setCurrentPrice(34.35);
        System.out.println("Stock change percent: " + stock.getChangePercent());
    }

    // UML 中的字段会变成 Java 类里的 private instance variables。
    // 方法 getChangePercent 返回涨跌百分比：(current - previous) / previous * 100。
    static class Stock {
        private final String symbol;
        private final String name;
        private double previousClosingPrice;
        private double currentPrice;

        public Stock(String symbol, String name) {
            this.symbol = symbol;
            this.name = name;
        }

        public void setPreviousClosingPrice(double previousClosingPrice) {
            this.previousClosingPrice = previousClosingPrice;
        }

        public void setCurrentPrice(double currentPrice) {
            this.currentPrice = currentPrice;
        }

        public double getChangePercent() {
            return (currentPrice - previousClosingPrice) / previousClosingPrice * 100.0;
        }

        @Override
        public String toString() {
            return "Stock{symbol='" + symbol + "', name='" + name + "'}";
        }
    }
}
