import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Week03Answers {
    public static void main(String[] args) {
        System.out.println("Week 03 Answers");
        demoShuffleNumbers();
        demoComparableCircle();
        demoDeepCopyStack();
        demoEmployees();
        demoDiscountableBook();
        demoAbstractClassAndInterfaceNotes();
        demoComparableStudents();
        demoNumberArray();
    }

    private static void demoShuffleNumbers() {
        ArrayList<Number> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2.5);
        numbers.add(3L);
        numbers.add(4.75f);
        shuffle(numbers);
        System.out.println("Shuffled numbers: " + numbers);
    }

    // Fisher-Yates 洗牌算法从后往前处理数组。
    // 对于位置 i，随机选一个 0 到 i 之间的位置 j，然后交换 i 和 j。
    // 这样每个排列出现的概率相同，时间复杂度是 O(n)。
    public static void shuffle(ArrayList<Number> list) {
        Random random = new Random(204);
        for (int i = list.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Number temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }
    }

    private static void demoComparableCircle() {
        ComparableCircle c1 = new ComparableCircle(3);
        ComparableCircle c2 = new ComparableCircle(5);
        ComparableCircle larger = max(c1, c2);
        System.out.println("Larger circle radius: " + larger.getRadius());
    }

    static class Circle {
        private final double radius;

        public Circle(double radius) {
            this.radius = radius;
        }

        public double getRadius() {
            return radius;
        }

        public double getArea() {
            return Math.PI * radius * radius;
        }
    }

    // ComparableCircle 实现 Comparable 后，就可以被通用 max 方法比较。
    // compareTo 返回正数表示当前对象更大，负数表示更小，0 表示相等。
    static class ComparableCircle extends Circle implements Comparable<ComparableCircle> {
        public ComparableCircle(double radius) {
            super(radius);
        }

        @Override
        public int compareTo(ComparableCircle other) {
            return Double.compare(getRadius(), other.getRadius());
        }
    }

    public static <T extends Comparable<T>> T max(T first, T second) {
        return first.compareTo(second) >= 0 ? first : second;
    }

    private static void demoDeepCopyStack() {
        MyStack<MutableBox> original = new MyStack<>();
        original.push(new MutableBox("alpha"));
        original.push(new MutableBox("beta"));

        MyStack<MutableBox> copy = original.clone();
        original.peek().setValue("changed");

        System.out.println("Original stack: " + original);
        System.out.println("Copied stack: " + copy);
    }

    interface Copyable<T> {
        T copy();
    }

    // 深拷贝的重点有两层：
    // 第一层：新的栈对象要拥有新的 ArrayList，不能和原栈共享同一个 list。
    // 第二层：list 里面的可变元素也要复制，否则两个栈仍然会共享同一个元素对象。
    // Java 无法安全地对任意 Object 自动深拷贝，所以这里要求元素实现 Copyable。
    static class MyStack<T extends Copyable<T>> implements Cloneable {
        private ArrayList<T> list = new ArrayList<>();

        public boolean isEmpty() {
            return list.isEmpty();
        }

        public int getSize() {
            return list.size();
        }

        public T peek() {
            return list.get(list.size() - 1);
        }

        public T pop() {
            return list.remove(list.size() - 1);
        }

        public void push(T value) {
            list.add(value);
        }

        @SuppressWarnings("unchecked")
        @Override
        public MyStack<T> clone() {
            try {
                MyStack<T> cloned = (MyStack<T>) super.clone();
                cloned.list = new ArrayList<>();
                for (T item : list) {
                    cloned.list.add(item.copy());
                }
                return cloned;
            } catch (CloneNotSupportedException ex) {
                throw new AssertionError(ex);
            }
        }

        @Override
        public String toString() {
            return list.toString();
        }
    }

    static class MutableBox implements Copyable<MutableBox> {
        private String value;

        public MutableBox(String value) {
            this.value = value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public MutableBox copy() {
            return new MutableBox(value);
        }

        @Override
        public String toString() {
            return value;
        }
    }

    private static void demoEmployees() {
        Employee[] employees = {
                new FullTimeEmployee("Alice", "F001", 4500),
                new PartTimeEmployee("Ben", "P001", 80, 18)
        };

        for (Employee employee : employees) {
            System.out.println(employee + ", salary=" + employee.calculateSalary());
        }
    }

    // 抽象类可以保存共同字段和共同方法，也可以声明必须由子类实现的抽象方法。
    // Employee 不知道具体薪资公式，所以 calculateSalary 留给 FullTimeEmployee 和 PartTimeEmployee。
    abstract static class Employee {
        private final String name;
        private final String id;

        public Employee(String name, String id) {
            this.name = name;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }

        public abstract double calculateSalary();

        @Override
        public String toString() {
            return getClass().getSimpleName() + "{name='" + name + "', id='" + id + "'}";
        }
    }

    static class FullTimeEmployee extends Employee {
        private final double monthlySalary;

        public FullTimeEmployee(String name, String id, double monthlySalary) {
            super(name, id);
            this.monthlySalary = monthlySalary;
        }

        @Override
        public double calculateSalary() {
            return monthlySalary;
        }
    }

    static class PartTimeEmployee extends Employee {
        private final int hoursWorked;
        private final double hourlyRate;

        public PartTimeEmployee(String name, String id, int hoursWorked, double hourlyRate) {
            super(name, id);
            this.hoursWorked = hoursWorked;
            this.hourlyRate = hourlyRate;
        }

        @Override
        public double calculateSalary() {
            return hoursWorked * hourlyRate;
        }
    }

    private static void demoDiscountableBook() {
        Book book = new Book("Algorithms", 60.0, "Sedgewick");
        System.out.println(book);
        System.out.println("Discounted price: " + book.getDiscountedPrice(20));
    }

    interface Discountable {
        double getDiscountedPrice(double percent);
    }

    static class Product {
        private final String name;
        private final double price;

        public Product(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }
    }

    // Book 继承 Product，说明 Book 是一种 Product。
    // Book 实现 Discountable，说明 Book 承诺提供折扣计算能力。
    static class Book extends Product implements Discountable {
        private final String author;

        public Book(String name, double price, String author) {
            super(name, price);
            this.author = author;
        }

        @Override
        public double getDiscountedPrice(double percent) {
            return getPrice() * (1.0 - percent / 100.0);
        }

        @Override
        public String toString() {
            return "Book{name='" + getName() + "', author='" + author + "', price=" + getPrice() + "}";
        }
    }

    private static void demoAbstractClassAndInterfaceNotes() {
        System.out.println("Abstract class and interface notes are explained in comments.");
    }

    // 抽象类和接口常见考点：
    // 1. 抽象类不能直接 new，因为它可能有未实现的抽象方法。
    // 2. 类继承抽象类使用 extends，实现接口使用 implements。
    // 3. 普通子类必须实现继承到的所有抽象方法，否则子类也必须声明为 abstract。
    // 4. 接口字段默认是 public static final，也就是常量。
    // 5. 重写方法时不能降低访问权限，例如 public 方法不能改成 protected。
    abstract static class AbstractShape {
        public abstract double area();
    }

    interface Named {
        String getName();
    }

    static class NamedCircle extends AbstractShape implements Named {
        private final String name;
        private final double radius;

        public NamedCircle(String name, double radius) {
            this.name = name;
            this.radius = radius;
        }

        @Override
        public double area() {
            return Math.PI * radius * radius;
        }

        @Override
        public String getName() {
            return name;
        }
    }

    private static void demoComparableStudents() {
        StudentMark[] students = {
                new StudentMark("Ben", 72),
                new StudentMark("Alice", 72),
                new StudentMark("Cathy", 88)
        };
        Arrays.sort(students);
        System.out.println("Sorted students: " + Arrays.toString(students));
    }

    // compareTo 先按 mark 排序；mark 相同时再按 name 字母顺序排序。
    // 这样排序结果是稳定且可解释的，不会因为同分而顺序混乱。
    static class StudentMark implements Comparable<StudentMark> {
        private final String name;
        private final int mark;

        public StudentMark(String name, int mark) {
            this.name = name;
            this.mark = mark;
        }

        @Override
        public int compareTo(StudentMark other) {
            int markComparison = Integer.compare(mark, other.mark);
            if (markComparison != 0) {
                return markComparison;
            }
            return name.compareTo(other.name);
        }

        @Override
        public String toString() {
            return name + ":" + mark;
        }
    }

    private static void demoNumberArray() {
        Number[] values = {1, 2.5, 3L, 4.5f};
        double sum = 0;

        for (Number value : values) {
            sum += value.doubleValue();
            System.out.println("Value " + value + " has type " + value.getClass().getSimpleName());
        }

        System.out.println("Number sum: " + sum);
    }
}
