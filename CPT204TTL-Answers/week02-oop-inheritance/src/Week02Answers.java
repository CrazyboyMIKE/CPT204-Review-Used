import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;

public class Week02Answers {
    public static void main(String[] args) {
        System.out.println("Week 02 Answers");
        demoMyPoint();
        demoCircle2D();
        demoBigIntegers();
        demoTriangle();
        demoPeople();
        demoMyStack();
        demoTaxTables();
    }

    private static void demoMyPoint() {
        MyPoint origin = new MyPoint();
        MyPoint p1 = new MyPoint(10.25, 20.8);
        MyPoint p2 = new MyPoint(13.25, 24.8);
        System.out.println("Distance from origin to p1: " + origin.distance(p1));
        System.out.println("Distance from p1 to p2: " + MyPoint.distance(p1, p2));
    }

    // MyPoint 封装二维坐标。
    // instance method distance(other) 用当前对象 this 和另一个点计算距离。
    // static method distance(p1, p2) 不依赖某个对象，直接用两个参数计算距离。
    static class MyPoint {
        private final double x;
        private final double y;

        public MyPoint() {
            this(0, 0);
        }

        public MyPoint(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public double distance(MyPoint other) {
            return distance(this, other);
        }

        public static double distance(MyPoint p1, MyPoint p2) {
            double dx = p1.x - p2.x;
            double dy = p1.y - p2.y;
            return Math.sqrt(dx * dx + dy * dy);
        }
    }

    private static void demoCircle2D() {
        Circle2D c1 = new Circle2D(2, 2, 5.5);
        System.out.println("Circle area: " + c1.getArea());
        System.out.println("Circle perimeter: " + c1.getPerimeter());
        System.out.println("Circle contains point (3, 3): " + c1.contains(3, 3));
        System.out.println("Circle contains bigger circle: " + c1.contains(new Circle2D(4, 5, 10.5)));
        System.out.println("Circle overlaps smaller circle: " + c1.overlaps(new Circle2D(3, 5, 2.3)));
    }

    // Circle2D 的核心判断都来自圆心距离。
    // 点在圆内：圆心到点的距离 <= 半径。
    // 一个圆包含另一个圆：两个圆心距离 + 小圆半径 <= 大圆半径。
    // 两个圆重叠：两个圆心距离 <= 两个半径之和。
    static class Circle2D {
        private final double x;
        private final double y;
        private final double radius;

        public Circle2D() {
            this(0, 0, 1);
        }

        public Circle2D(double x, double y, double radius) {
            this.x = x;
            this.y = y;
            this.radius = radius;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public double getRadius() {
            return radius;
        }

        public double getArea() {
            return Math.PI * radius * radius;
        }

        public double getPerimeter() {
            return 2 * Math.PI * radius;
        }

        public boolean contains(double pointX, double pointY) {
            double dx = x - pointX;
            double dy = y - pointY;
            return Math.sqrt(dx * dx + dy * dy) <= radius;
        }

        public boolean contains(Circle2D circle) {
            double centerDistance = Math.sqrt(Math.pow(x - circle.x, 2) + Math.pow(y - circle.y, 2));
            return centerDistance + circle.radius <= radius;
        }

        public boolean overlaps(Circle2D circle) {
            double centerDistance = Math.sqrt(Math.pow(x - circle.x, 2) + Math.pow(y - circle.y, 2));
            return centerDistance <= radius + circle.radius;
        }
    }

    private static void demoBigIntegers() {
        BigInteger current = new BigInteger("10000000000000000000000000000000000000000000000000");
        int printed = 0;
        System.out.print("First ten 50-digit numbers divisible by 2 or 3:");

        while (printed < 10) {
            if (current.mod(BigInteger.TWO).equals(BigInteger.ZERO)
                    || current.mod(BigInteger.valueOf(3)).equals(BigInteger.ZERO)) {
                System.out.print(" " + current);
                printed++;
            }
            current = current.add(BigInteger.ONE);
        }
        System.out.println();
    }

    private static void demoTriangle() {
        Triangle triangle = new Triangle(3, 4, 5);
        triangle.setColor("blue");
        triangle.setFilled(true);
        System.out.println(triangle);
        System.out.println("Triangle area: " + triangle.getArea());
        System.out.println("Triangle perimeter: " + triangle.getPerimeter());
    }

    // GeometricObject 是 Triangle 的父类，保存所有几何对象都可能有的属性。
    // Triangle 继承它以后，只需要额外处理三条边和面积周长公式。
    static class GeometricObject {
        private String color = "white";
        private boolean filled;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public boolean isFilled() {
            return filled;
        }

        public void setFilled(boolean filled) {
            this.filled = filled;
        }
    }

    static class Triangle extends GeometricObject {
        private final double side1;
        private final double side2;
        private final double side3;

        public Triangle() {
            this(1, 1, 1);
        }

        public Triangle(double side1, double side2, double side3) {
            this.side1 = side1;
            this.side2 = side2;
            this.side3 = side3;
        }

        public double getSide1() {
            return side1;
        }

        public double getSide2() {
            return side2;
        }

        public double getSide3() {
            return side3;
        }

        public double getArea() {
            double s = getPerimeter() / 2.0;
            return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
        }

        public double getPerimeter() {
            return side1 + side2 + side3;
        }

        @Override
        public String toString() {
            return "Triangle: side1 = " + side1 + " side2 = " + side2 + " side3 = " + side3;
        }
    }

    private static void demoPeople() {
        Person[] people = {
                new Person("Alice"),
                new Student("Ben", Student.JUNIOR),
                new Employee("Cathy", "B-12", 48000),
                new Faculty("David", "C-02", 72000, "Tuesday 10:00-12:00", "Lecturer"),
                new Staff("Eva", "Senior Administrator")
        };

        for (Person person : people) {
            System.out.println(person);
        }
    }

    // 这里 name 使用 protected，是为了回答题目中“子类是否能直接访问 name”的问题。
    // 更严格的封装方式是 private name + public getter，但 protected 更贴近本题讨论。
    static class Person {
        protected String name;
        private String address;
        private String phoneNumber;
        private String emailAddress;

        public Person(String name) {
            this.name = name;
        }

        public void setContact(String address, String phoneNumber, String emailAddress) {
            this.address = address;
            this.phoneNumber = phoneNumber;
            this.emailAddress = emailAddress;
        }

        @Override
        public String toString() {
            return "Person: " + name;
        }
    }

    static class Student extends Person {
        public static final String FRESHMAN = "freshman";
        public static final String SOPHOMORE = "sophomore";
        public static final String JUNIOR = "junior";
        public static final String SENIOR = "senior";

        private final String status;

        public Student(String name, String status) {
            super(name);
            this.status = status;
        }

        @Override
        public String toString() {
            return "Student: " + name + ", status=" + status;
        }
    }

    static class Employee extends Person {
        private final String office;
        private final double salary;
        private final LocalDate dateHired;

        public Employee(String name, String office, double salary) {
            super(name);
            this.office = office;
            this.salary = salary;
            this.dateHired = LocalDate.now();
        }

        @Override
        public String toString() {
            return "Employee: " + name + ", office=" + office
                    + ", salary=" + salary + ", dateHired=" + dateHired;
        }
    }

    static class Faculty extends Employee {
        private final String officeHours;
        private final String rank;

        public Faculty(String name, String office, double salary, String officeHours, String rank) {
            super(name, office, salary);
            this.officeHours = officeHours;
            this.rank = rank;
        }

        @Override
        public String toString() {
            return "Faculty: " + name + ", officeHours=" + officeHours + ", rank=" + rank;
        }
    }

    static class Staff extends Employee {
        private final String title;

        public Staff(String name, String title) {
            super(name, "Main Office", 36000);
            this.title = title;
        }

        @Override
        public String toString() {
            return "Staff: " + name + ", title=" + title;
        }
    }

    private static void demoMyStack() {
        MyStack stack = new MyStack();
        stack.push("one");
        stack.push("two");
        stack.push("three");
        stack.push("four");
        stack.push("five");

        while (!stack.isEmpty()) {
            System.out.println("Popped value: " + stack.pop());
        }
    }

    // 题目要求用继承实现 MyStack，因此这里 extends ArrayList<Object>。
    // 栈的核心规则是后进先出：push 添加到末尾，pop 从末尾移除。
    static class MyStack extends ArrayList<Object> {
        public boolean isEmpty() {
            return super.isEmpty();
        }

        public int getSize() {
            return size();
        }

        public Object peek() {
            return get(getSize() - 1);
        }

        public Object pop() {
            Object value = peek();
            remove(getSize() - 1);
            return value;
        }

        public void push(Object value) {
            add(value);
        }

        public int search(Object value) {
            return lastIndexOf(value);
        }

        @Override
        public String toString() {
            return "stack: " + super.toString();
        }
    }

    private static void demoTaxTables() {
        int[][] brackets2001 = {
                {27050, 65550, 136750, 297350},
                {45200, 109250, 166500, 297350},
                {22600, 54625, 83250, 148675},
                {36250, 93650, 151650, 297350}
        };
        double[] rates2001 = {0.15, 0.275, 0.305, 0.355, 0.391};

        int[][] brackets2009 = {
                {8350, 33950, 82250, 171550, 372950},
                {16700, 67900, 137050, 208850, 372950},
                {8350, 33950, 68525, 104425, 186475},
                {11950, 45500, 117450, 190200, 372950}
        };
        double[] rates2009 = {0.10, 0.15, 0.25, 0.28, 0.33, 0.35};

        printTaxTable("2001", brackets2001, rates2001);
        printTaxTable("2009", brackets2009, rates2009);
    }

    private static void printTaxTable(String year, int[][] brackets, double[] rates) {
        System.out.println("Tax table for " + year);
        for (int income = 50_000; income <= 60_000; income += 1_000) {
            System.out.print("Income " + income + ":");
            for (int status = 0; status < 4; status++) {
                Tax tax = new Tax(status, brackets, rates, income);
                System.out.print(" " + Math.round(tax.getTax()));
            }
            System.out.println();
        }
    }

    // Tax 使用“边际税率”：每一段收入按照对应税率纳税，而不是全部收入都用最高档税率。
    // brackets[filingStatus] 取出当前报税身份的分界点数组。
    static class Tax {
        public static final int SINGLE_FILER = 0;
        public static final int MARRIED_JOINTLY_OR_QUALIFYING_WIDOW_ER = 1;
        public static final int MARRIED_SEPARATELY = 2;
        public static final int HEAD_OF_HOUSEHOLD = 3;

        private int filingStatus;
        private int[][] brackets;
        private double[] rates;
        private double taxableIncome;

        public Tax() {
        }

        public Tax(int filingStatus, int[][] brackets, double[] rates, double taxableIncome) {
            this.filingStatus = filingStatus;
            this.brackets = brackets;
            this.rates = rates;
            this.taxableIncome = taxableIncome;
        }

        public double getTax() {
            int[] statusBrackets = brackets[filingStatus];
            double tax = 0;
            double lowerBound = 0;

            for (int i = 0; i < statusBrackets.length; i++) {
                double upperBound = statusBrackets[i];
                if (taxableIncome > upperBound) {
                    tax += (upperBound - lowerBound) * rates[i];
                    lowerBound = upperBound;
                } else {
                    tax += (taxableIncome - lowerBound) * rates[i];
                    return tax;
                }
            }

            tax += (taxableIncome - lowerBound) * rates[rates.length - 1];
            return tax;
        }
    }
}
