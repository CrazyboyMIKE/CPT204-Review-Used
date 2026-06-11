# CPT204 Advanced Object-Oriented Programming 复习纲领 / Bilingual Review Outline

> 覆盖课件 / Covered lecture notes: arrays, objects/classes, OOP thinking, inheritance/polymorphism, abstract classes/interfaces, generics, lists/stacks/queues/priority queues, sets/maps, algorithm efficiency, sorting/heap, graphs, BST, AVL trees, hashing.

## 0. 复习路线 / Study Roadmap

### 中文

这门课不是只考 Java 语法，而是考“如何用对象、集合、算法和数据结构解决问题”。可以按五条主线复习：

1. Java 基础容器和对象模型：数组、对象、类、构造器、封装、静态成员、引用与内存。
2. 面向对象抽象能力：继承、多态、抽象类、接口、`Comparable`、`Comparator`、`Cloneable`、泛型。
3. Java Collections Framework：`Collection`、`List`、`Set`、`Queue`、`Map` 以及常见实现类。
4. 算法分析与经典算法：Big O、递归、动态规划、排序、字符串匹配、几何算法。
5. 高级数据结构：图、BST、AVL、Hashing，以及它们的复杂度和应用场景。

复习时不要只背 API，要能回答三个问题：

- What is it? 它是什么抽象？
- How does it work? 它内部怎样运行？
- When should I use it? 什么时候选它，复杂度和代价是什么？

### English

The course is about solving problems with Java objects, collections, algorithms, and data structures. For each topic, revise three layers:

- Definition: what abstraction is being introduced?
- Mechanism: how does it work internally?
- Trade-off: when is it appropriate, and what are the time/space costs?

---

## 1. Arrays / 数组

### 1.1 Definition / 定义

**中文：** 数组是存储同一类型数据的固定长度容器。Java 中数组本身是对象，数组变量保存的是数组对象的引用。

**English:** An array is a fixed-size container for elements of the same type. In Java, an array is an object, and an array variable stores a reference to that object.

```java
int[] a = new int[5];
double[] values = {1.9, 2.9, 3.4, 3.5};
```

关键点 / Key points:

- `array.length` 是字段，不是方法。Use `a.length`, not `a.length()`.
- 下标从 `0` 到 `length - 1`。Index range is `0 ... length - 1`.
- 数组创建后长度不能改变。Array size is fixed after creation.
- 默认值：数值类型为 `0`，`boolean` 为 `false`，引用类型为 `null`。

### 1.2 Common Array Algorithms / 常见数组算法

初始化 / Initialization:

```java
for (int i = 0; i < a.length; i++) {
    a[i] = (int)(Math.random() * 100);
}
```

求和 / Sum:

```java
int sum = 0;
for (int x : a) sum += x;
```

最大值最小值 / Max and min:

```java
int max = a[0];
for (int i = 1; i < a.length; i++) {
    if (a[i] > max) max = a[i];
}
```

复制 / Copying:

- `target = source` 只是复制引用。Both variables point to the same array.
- 真正复制元素可以用循环或 `System.arraycopy`。

```java
System.arraycopy(source, srcPos, target, targetPos, length);
```

左移右移 / Shifting:

- 左移会覆盖前一个位置，常用于删除元素。
- 右移要从后往前移动，常用于插入元素，避免覆盖还没搬走的数据。

### 1.3 Passing Arrays to Methods / 数组传参

**中文：** Java 总是 pass by value。对数组参数来说，传入的是“引用值的副本”。方法内可以通过这个引用修改同一个数组对象，但不能通过重新赋值让调用者的引用指向新数组。

**English:** Java is always pass-by-value. For arrays, the copied value is the reference. A method can mutate the same array object, but reassigning the parameter does not reassign the caller's variable.

```java
void change(int[] a) {
    a[0] = 99;          // caller sees this mutation
    a = new int[10];    // caller does not see this reassignment
}
```

### 1.4 Heap and Call Stack / 堆与调用栈

**中文：** 局部变量和方法调用帧在 call stack 中；数组对象在 heap 中。数组变量本身通常在栈帧里保存引用值。

**English:** Local variables and method frames live on the call stack; array objects live on the heap.

常考理解 / Exam intuition:

- `int x` 直接保存数值。
- `int[] y` 保存引用，数组元素在 heap。
- 方法调用时复制参数值；对象本身不被复制。

### 1.5 Searching and Sorting Arrays / 数组查找与排序

Linear search / 线性查找:

- 无需数组有序。
- 从头到尾检查。
- Worst case: `O(n)`。

Binary search / 二分查找:

- 前提是数组已经有序。
- 每次比较中间元素，排除一半范围。
- Worst case: `O(log n)`。

Selection sort / 选择排序:

- 每轮从未排序部分找最小值放到前面。
- 比较次数约为 `(n - 1) + ... + 1`。
- Time: `O(n^2)`。

Insertion sort / 插入排序:

- 把当前位置元素插入前面已排序部分的正确位置。
- 近乎有序时很快；最坏 `O(n^2)`。

`Arrays.sort` / Java 内置排序:

- 实际开发优先使用标准库。
- 复习时要理解排序要求：基本类型、对象数组、对象自然顺序或比较器。

---

## 2. Objects and Classes / 对象与类

### 2.1 Object-Oriented Concepts / 面向对象基本概念

**Class / 类：** 对象的模板，定义字段和方法。

**Object / 对象：** 类的实例，有自己的状态和行为。

**State / 状态：** data fields，例如 `radius`。

**Behavior / 行为：** methods，例如 `getArea()`。

```java
class Circle {
    private double radius = 1.0;

    Circle() {}
    Circle(double radius) {
        this.radius = radius;
    }

    double getArea() {
        return Math.PI * radius * radius;
    }
}
```

### 2.2 Constructors / 构造器

**中文：** 构造器用于创建并初始化对象。构造器名称必须与类名相同，没有返回类型，不能写 `void`。

**English:** Constructors create and initialize objects. They have the same name as the class and no return type.

易错点 / Pitfall:

```java
void Circle() {} // This is a method, not a constructor.
```

### 2.3 Static vs Instance / 静态成员与实例成员

**Instance fields/methods / 实例字段与实例方法：**

- 属于每个对象。
- 需要对象引用调用。

**Static fields/methods / 静态字段与静态方法：**

- 属于类本身，被所有对象共享。
- 推荐用类名调用，例如 `Math.random()`。

```java
class Circle {
    private double radius;
    static int numberOfObjects;
}
```

考试判断 / How to reason:

- 方法中如果访问对象自己的字段，通常应是 instance method。
- 不依赖任何对象状态的工具方法，通常可为 static method。
- static method 不能直接访问 non-static field，因为它没有隐含的 `this`。

### 2.4 Primitive vs Object References / 基本类型与对象引用

**中文：** 基本类型变量保存值，对象类型变量保存引用。复制基本类型会复制值；复制对象引用会让两个变量指向同一个对象。

**English:** Primitive variables store values; object variables store references. Copying an object variable copies the reference, not the object.

### 2.5 Encapsulation / 封装

**中文：** 封装是把字段设为 `private`，通过 getter/setter 控制访问。它保护对象不被外部随意破坏，也让类的内部实现可以改变而不影响使用者。

**English:** Encapsulation hides fields behind public methods, protecting object state and separating implementation from use.

UML visibility:

- `+` public
- `-` private
- `#` protected
- no symbol / package-private

### 2.6 Garbage Collection / 垃圾回收

**中文：** 当对象不再被任何引用变量指向，它就成为 garbage，JVM 可以回收它。程序员通常不手动释放对象。

**English:** An object becomes garbage when no reference can reach it. The JVM may reclaim its memory automatically.

---

## 3. Thinking in Objects / 面向对象思维

### 3.1 Immutable Objects / 不可变对象

**中文：** 不可变对象创建后状态不能改变。只有 `private` 字段、不提供 setter 还不够，如果字段本身引用可变对象，仍可能被外部修改。

**English:** An immutable object cannot change after creation. Private fields and no setters are not enough if mutable internal objects can leak.

不可变类设计 / How to design immutability:

- fields 使用 `private final`。
- 不提供 mutator/setter。
- 构造器中做防御性复制。
- 返回可变字段时返回副本，不返回内部引用。

### 3.2 Variable Scope / 变量作用域

**中文：** 局部变量从声明处开始，到所在 block 结束。局部变量没有默认值，使用前必须赋值。

**English:** A local variable is visible from its declaration to the end of its block. Local variables have no default values.

字段 / Data fields:

- 有默认值。
- 属于对象或类。

### 3.3 `this` Keyword / `this` 关键字

`this` 是当前对象的引用。

用途 / Uses:

- 区分字段和参数：`this.radius = radius;`
- 调用重载构造器：`this();` 或 `this(args);`

规则 / Rule:

- `this(...)` 必须是构造器第一条语句。

### 3.4 Class Abstraction and Encapsulation / 类抽象与封装

**Abstraction / 抽象：** 把“使用方式 API”和“内部实现 implementation”分离。

**Encapsulation / 封装：** 隐藏内部字段，暴露稳定方法。

典型复习类 / Lecture examples:

- `Loan`: 利率、年限、贷款额、贷款日期，计算月供和总付款。
- `BMI`: name、age、weight、height，计算 BMI 和状态。
- `Course`: 维护课程名和学生数组，注意数组扩容/删除。
- `StackOfIntegers`: 用数组模拟栈，维护 `size`。

### 3.5 String / 字符串

**中文：** `String` 是不可变对象。字符串字面量可能被 intern，共享同一个对象；`new String("...")` 会创建新对象。

**English:** `String` is immutable. String literals may be interned and shared; `new String(...)` creates a new object.

比较 / Comparison:

- `==` 比较引用是否相同。
- `equals` 比较内容。
- `compareTo` 比较字典序，返回负数、0、正数。

常用方法 / Common methods:

- `length()`, `charAt(i)`
- `substring(begin, end)`
- `indexOf`, `lastIndexOf`
- `toLowerCase`, `toUpperCase`, `trim`
- `replace`, `replaceAll`, `split`, `matches`

### 3.6 Regex / 正则表达式

**中文：** `matches`, `replaceAll`, `replaceFirst`, `split` 可以使用 regular expressions。

常见符号 / Common regex symbols:

- `.` any single character
- `\d` digit
- `\w` word character
- `[abc]` one of a, b, c
- `*` zero or more
- `+` one or more

### 3.7 StringBuilder and StringBuffer

**中文：** 大量拼接字符串时，用 `StringBuilder` 避免创建过多中间 `String` 对象。`StringBuffer` 类似但同步，通常更慢。

**English:** Use `StringBuilder` for repeated string modification. `StringBuffer` is synchronized.

---

## 4. Inheritance and Polymorphism / 继承与多态

### 4.1 Inheritance / 继承

**中文：** 继承让子类复用父类字段和方法，并表达 is-a 关系。例如 `Circle extends GeometricObject`。

**English:** Inheritance allows a subclass to reuse and specialize a superclass, representing an is-a relationship.

```java
class Circle extends GeometricObject {
    private double radius;
}
```

### 4.2 Constructor Chaining and `super`

**中文：** 构造器不会被继承。创建子类对象时，会先调用父类构造器，再执行子类构造器。

**English:** Constructors are not inherited. When a subclass object is created, superclass construction happens before subclass construction.

`super` 用途:

- `super(args)` 调用父类构造器，必须在第一行。
- `super.method()` 调用父类被覆盖的方法。

### 4.3 Overloading vs Overriding / 重载与重写

**Overloading / 重载：**

- 同名方法，不同参数列表。
- 编译期根据 declared parameter types 选择方法。

**Overriding / 重写：**

- 子类重新实现父类可访问的实例方法。
- 方法签名相同。
- 运行期根据实际对象类型动态绑定。

易错点 / Pitfalls:

- `private` 方法不能被真正 override，因为子类不可见。
- 子类 override 不能降低访问权限。
- `static` 方法不存在真正动态绑定。

### 4.4 Method Matching vs Dynamic Binding

**中文：** 重载先由编译器按参数类型做 method matching；重写再由 JVM 按实际对象类型做 dynamic binding。

**English:** Overloaded method selection happens at compile time; overridden method dispatch happens at runtime.

```java
GeometricObject g = new Circle();
System.out.println(g.toString()); // runtime chooses Circle's override if present
```

### 4.5 Polymorphism / 多态

**中文：** 父类变量可以引用子类对象，程序可以以统一接口处理不同具体类型。

**English:** A superclass reference can refer to a subclass object, enabling uniform processing of different concrete types.

### 4.6 Casting and `instanceof`

向上转型 / Upcasting:

- 子类对象赋给父类变量，自动安全。

向下转型 / Downcasting:

- 父类变量转回子类，需要显式 cast。
- 运行时可能失败，先用 `instanceof` 检查。

```java
if (obj instanceof Circle) {
    Circle c = (Circle)obj;
}
```

### 4.7 `Object`, `toString`, `equals`

所有 Java 类最终继承自 `Object`。

`toString()`:

- 默认输出类名和哈希信息。
- 通常 override 成对象可读描述。

`equals(Object obj)`:

- 默认比较引用。
- 内容相等需要 override。
- 正确签名必须是 `public boolean equals(Object obj)`。

`equals` 合约 / Contract:

- reflexive: `x.equals(x)` true
- symmetric: `x.equals(y)` 与 `y.equals(x)` 一致
- transitive: 传递性
- consistent: 多次调用结果一致
- non-null: `x.equals(null)` false

若对象用于 HashSet/HashMap，override `equals` 时也要 override `hashCode`。

### 4.8 `final` Modifier / final 修饰符

- final variable: 常量或不可重新赋值。
- final method: 不能被 override。
- final class: 不能被继承。

---

## 5. Abstract Classes and Interfaces / 抽象类与接口

### 5.1 Abstract Class / 抽象类

**中文：** 抽象类不能实例化，可以包含字段、构造器、普通方法和抽象方法。

**English:** An abstract class cannot be instantiated. It can contain fields, constructors, concrete methods, and abstract methods.

规则 / Rules:

- 抽象方法只能出现在抽象类或接口中。
- 非抽象子类必须实现所有继承来的抽象方法。
- 抽象类可以没有抽象方法。
- 抽象类可作为变量类型：`GeometricObject g = new Circle();`

### 5.2 Interface / 接口

**中文：** 接口定义能力或契约。类用 `implements` 实现接口；接口之间用 `extends` 继承。

**English:** An interface defines a capability or contract. A class implements an interface; an interface may extend other interfaces.

接口成员默认修饰 / Implicit modifiers:

- fields: `public static final`
- methods: traditionally `public abstract`

### 5.3 Abstract Class vs Interface

| 维度 / Aspect | Abstract class / 抽象类 | Interface / 接口 |
|---|---|---|
| 关系 | strong is-a | capability / can-do |
| 字段 | 可有实例字段 | 常量为主 |
| 构造器 | 可以有 | 没有构造器 |
| 多继承 | 类只能 extends 一个类 | 类可 implements 多个接口 |
| 用法 | 共享代码和状态 | 统一行为契约 |

### 5.4 `Comparable` / 自然顺序

**中文：** `Comparable<T>` 定义对象的自然顺序，方法是 `compareTo(T other)`。

**English:** `Comparable<T>` defines natural ordering through `compareTo`.

返回值 / Return:

- `< 0`: this before other
- `0`: equal in ordering
- `> 0`: this after other

```java
class Student implements Comparable<Student> {
    public int compareTo(Student other) {
        return this.id - other.id;
    }
}
```

更安全写法:

```java
return Integer.compare(this.id, other.id);
```

### 5.5 `Comparator` / 外部比较器

**中文：** 当类没有自然顺序，或需要多个排序规则时，用 `Comparator<T>`。

**English:** Use `Comparator<T>` for external or custom ordering.

```java
Comparator<Loan> byAmount =
    Comparator.comparing(Loan::getLoanAmount);
```

常用链式方法:

- `Comparator.comparing(Class::property)`
- `.thenComparing(...)`
- `.reversed()`
- lambda: `(a, b) -> ...`
- method reference: `String::compareToIgnoreCase`

### 5.6 `Cloneable`, Shallow Copy, Deep Copy

**Marker interface / 标记接口：** `Cloneable` 没有方法，只表示对象允许被 clone。

Shallow copy / 浅拷贝:

- 基本类型字段复制值。
- 引用字段复制引用，内部对象共享。

Deep copy / 深拷贝:

- 引用字段指向的新对象也复制一份。
- 用于避免两个对象共享可变内部状态。

### 5.7 Wrapper Classes / 包装类

**中文：** 集合只能存对象，不能直接存 primitive；因此需要 `Integer`, `Double`, `Character` 等包装类。

**English:** Collections store objects, so primitives are boxed into wrapper classes.

Autoboxing / unboxing:

```java
ArrayList<Integer> list = new ArrayList<>();
list.add(5);      // int -> Integer
int x = list.get(0); // Integer -> int
```

### 5.8 BigInteger and BigDecimal

**BigInteger:** 任意精度整数。

**BigDecimal:** 高精度十进制，适合财务计算。

注意 / Note:

- 它们是对象，不用 `+ - * /`。
- 使用 `add`, `subtract`, `multiply`, `divide`。

---

## 6. Generics / 泛型

### 6.1 Why Generics / 为什么用泛型

**中文：** 泛型把类型作为参数，使错误尽量在编译期暴露，减少强制类型转换，提高可读性和安全性。

**English:** Generics parameterize types, moving type errors from runtime to compile time and reducing casts.

Without generics:

```java
ArrayList list = new ArrayList();
list.add("1");
Integer x = (Integer) list.get(0); // runtime error
```

With generics:

```java
ArrayList<Integer> list = new ArrayList<>();
list.add("1"); // compile-time error
```

### 6.2 Generic Classes / 泛型类

```java
class GenericStack<E> {
    private ArrayList<E> list = new ArrayList<>();
    public void push(E e) { list.add(e); }
    public E pop() { return list.remove(list.size() - 1); }
}
```

`E` 是 formal generic type，创建对象时替换成 concrete type。

### 6.3 Generic Methods / 泛型方法

泛型方法把类型参数写在返回类型前面：

```java
public static <E> void print(E[] array) {
    for (E e : array) System.out.println(e);
}
```

### 6.4 Bounded Generic Types / 有界泛型

**中文：** 如果方法需要调用某类特有方法，就要给类型参数加上边界。

**English:** Use bounds when the generic code needs capabilities from a superclass or interface.

```java
public static <E extends Comparable<E>> E max(E a, E b) {
    return a.compareTo(b) > 0 ? a : b;
}
```

注意：即使是接口，也使用 `extends`，不是 `implements`。

### 6.5 Raw Types / 原始类型

**中文：** 不写类型参数的泛型类是 raw type，用于兼容老代码，但不安全。

**English:** Raw types exist for backward compatibility but are unsafe.

```java
ArrayList raw = new ArrayList(); // avoid in new code
```

### 6.6 Wildcards / 通配符

核心问题：`GenericStack<Integer>` 不是 `GenericStack<Number>` 的子类型，即使 `Integer extends Number`。

通配符类型 / Wildcard forms:

- `<?>`: unknown type
- `<? extends T>`: T 或 T 的子类，适合读。producer extends
- `<? super T>`: T 或 T 的父类，适合写。consumer super

PECS rule:

- Producer Extends: 如果结构主要产出 `T`，用 `? extends T`。
- Consumer Super: 如果结构主要接收 `T`，用 `? super T`。

### 6.7 Type Erasure / 类型擦除

**中文：** Java 泛型主要在编译期检查；编译后类型参数会被擦除，运行时多个不同泛型实例共享同一个 class。

**English:** Java generics use type erasure. Type parameters are checked at compile time and erased at runtime.

限制 / Restrictions:

- 不能 `new E()`。
- 不能创建 `new E[]`。
- 不能用 primitive type 作为泛型参数，如 `ArrayList<int>` 错。
- 不能 `instanceof GenericStack<String>`。
- 泛型类不能 extends `Throwable`。
- static 字段不能依赖类的类型参数。

### 6.8 Generics With Collections: Exam Keywords / 集合泛型里的高频关键字

很多 CPT204 题不是直接问 “what is `extends`?”，而是给你一段集合、泛型、排序代码，让你解释为什么能比较、为什么能遍历、为什么不能 `new List<>()`。

Many CPT204 questions do not ask keywords in isolation. They place keywords inside collection and generic code, then ask you to reason about type safety, ordering, traversal, or object creation.

Key patterns / 关键模式:

```java
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class GenericCollectionKeywordExample {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Alice", 88));
        students.add(new Student("Bob", 75));

        students.sort(new MarkComparator());

        PriorityQueue<Student> queue = new PriorityQueue<>(new MarkComparator());
        queue.offer(new Student("Cathy", 92));
        queue.offer(new Student("David", 70));

        while (!queue.isEmpty()) {
            Student student = queue.poll();
            System.out.println(student.getName());
        }
    }
}

class Student {
    private String name;
    private int mark;

    public Student(String name, int mark) {
        this.name = name;
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public int getMark() {
        return mark;
    }
}

class MarkComparator implements Comparator<Student> {
    @Override
    public int compare(Student first, Student second) {
        return Integer.compare(second.getMark(), first.getMark());
    }
}
```

Keyword explanation / 关键字解释:

- `import`: brings collection classes/interfaces into the file.  
  `import` 把 `List`、`ArrayList`、`PriorityQueue`、`Comparator` 等名字导入当前文件。
- `class`: defines a blueprint for objects.  
  `class` 定义对象模板，例如 `Student`。
- `interface`: `Comparator` is an interface, meaning it defines a contract.  
  `Comparator` 是接口，规定实现类必须提供 `compare` 方法。
- `implements`: `MarkComparator implements Comparator<Student>` means the class promises to provide comparison logic.  
  `implements` 表示类实现接口承诺。
- `new`: creates concrete objects such as `new ArrayList<>()`; interfaces cannot be directly instantiated.  
  `new` 创建具体对象；不能 `new List<>()` 或 `new Comparator<>()`。
- `while`: repeatedly processes the priority queue until it is empty.  
  `while` 用于反复处理优先队列直到为空。
- `return`: sends comparison result back to the caller.  
  `return` 返回比较结果。

Exam wording / 英文答题句:

> `List<Student>` gives compile-time type safety, while `new ArrayList<>()` creates the concrete resizable-list object.

> `Comparator<Student>` defines an external ordering rule, which can be used by sorting methods and priority queues.

---

## 7. Java Collections Framework / Java 集合框架

### 7.1 Big Picture / 总图

**Collection** 家族存单个元素：

- `List`: ordered, allows duplicates.
- `Set`: no duplicates.
- `Queue`: processing order, usually FIFO.
- `PriorityQueue`: priority order, not arrival order.

**Map** 家族存 key-value pairs，不属于 `Collection` 接口。

接口 vs 实现 / Interface vs implementation:

| Interface / 接口 | Common implementation / 常见实现 | Main idea / 核心用途 |
|---|---|---|
| `List<E>` | `ArrayList<E>`, `LinkedList<E>` | ordered sequence with duplicates |
| `Set<E>` | `HashSet<E>`, `LinkedHashSet<E>`, `TreeSet<E>` | unique elements |
| `Queue<E>` | `ArrayDeque<E>`, `LinkedList<E>`, `PriorityQueue<E>` | process elements by a rule |
| `Deque<E>` | `ArrayDeque<E>`, `LinkedList<E>` | double-ended queue, also usable as stack |
| `Map<K,V>` | `HashMap<K,V>`, `LinkedHashMap<K,V>`, `TreeMap<K,V>` | key-value lookup |

关键考试句 / Exam sentence:

> An interface specifies what operations are available, while an implementation class decides how those operations are stored and how efficient they are.

常见错误 / Common trap:

```java
// List<String> names = new List<>();   // wrong: List is an interface
// Queue<String> queue = new Queue<>(); // wrong: Queue is an interface

List<String> names = new ArrayList<>();     // correct
Queue<String> queue = new ArrayDeque<>();   // correct
```

### 7.2 `Collection<E>` Interface

常用操作 / Common operations:

- Modification: `add`, `remove`, `clear`
- Query: `size`, `isEmpty`, `contains`
- Bulk: `addAll`, `removeAll`, `retainAll`
- Traversal: `iterator`

集合批量操作对应集合论:

- `addAll`: union-like merge
- `removeAll`: difference
- `retainAll`: intersection

### 7.3 Iterator and Traversal / 迭代器与遍历

三种遍历 / Three traversal styles:

```java
Iterator<E> it = collection.iterator();
while (it.hasNext()) {
    E e = it.next();
}

for (E e : collection) {}

collection.forEach(e -> System.out.println(e));
```

重要规则 / Critical rule:

- 遍历时要删除元素，使用 explicit iterator 的 `remove()`。
- enhanced for 和 `forEach` 适合只读遍历，不适合边遍历边结构性删除。

### 7.4 `List` / 列表

**中文：** List 有顺序，允许重复，支持按 index 访问。

**English:** A list is ordered, allows duplicates, and supports positional access.

这里的 ordered 是“有位置、有下标、保留插入后的排列”，不是 automatically sorted。`List` 不会自动从小到大排序。

Here, ordered means elements have positions and indexes. It does not mean automatically sorted.

核心方法:

- `add(index, element)`
- `get(index)`
- `set(index, element)`
- `remove(index)`
- `indexOf`
- `subList`

`ListIterator`:

- 可以双向移动：`hasPrevious`, `previous`
- 可以在迭代中 `add`, `set`

完整例子 / Complete example:

```java
import java.util.ArrayList;
import java.util.List;

public class ListReviewExample {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();

        numbers.add(30);
        numbers.add(10);
        numbers.add(20);

        System.out.println(numbers.get(0)); // 30
        System.out.println(numbers);        // [30, 10, 20], not sorted

        numbers.set(1, 15);                 // replace index 1
        numbers.remove(0);                  // remove index 0

        System.out.println(numbers);        // [15, 20]
    }
}
```

`List<Integer>` 的 `remove` 重载坑 / `remove` overload trap:

```java
List<Integer> values = new ArrayList<>();
values.add(1);
values.add(2);
values.add(3);

values.remove(1);                  // removes index 1, so value 2 is removed
values.remove(Integer.valueOf(1)); // removes the object/value 1
```

英文考试句 / Exam sentence:

> A `List` is ordered by index but not necessarily sorted by value.

### 7.5 ArrayList vs LinkedList

| Feature / 特性 | ArrayList | LinkedList |
|---|---|---|
| Structure | resizable array | doubly linked nodes |
| Random access `get/set` | `O(1)` | `O(n)` |
| Insert/remove at beginning | slow, shifting | fast pointer changes |
| Memory overhead | lower | higher, node links |
| Best use | frequent reading/access | frequent head/tail operations |

**复习结论：** 不确定时优先 `ArrayList`；只有明确需要高频头部/尾部插删或队列/双端队列操作时考虑 `LinkedList`。

更精确地说 / More precisely:

- `ArrayList.get(i)` 是 `O(1)`，因为底层像数组一样可以直接定位。
- `LinkedList.get(i)` 是 `O(n)`，因为要沿节点走过去。
- `ArrayList` 在中间插入/删除通常是 `O(n)`，因为后面的元素要整体移动。
- `LinkedList` 如果已经有节点位置，改链接可以是 `O(1)`；但如果先按 index 找节点，查找本身仍然是 `O(n)`。
- `LinkedList` implements `Deque`，所以可以做 queue/deque，但实际写普通 stack/queue 时 `ArrayDeque` 更常见。

考试坑 / Exam trap:

> Do not simply say "`LinkedList` insertion is always O(1)." It is O(1) only when the node position is already known; finding the position may still cost O(n).

### 7.6 `Arrays.asList` and `List.of`

`Arrays.asList`:

- 返回由数组支持的 fixed-size list。
- 可以 `set`，但不能改变大小。

`List.of`:

- JDK 9+，返回 immutable list。
- 不能 `add/remove/set`。

### 7.7 `Collections` Utility Class

常用工具方法:

- Sorting: `sort`, `reverseOrder`
- Searching: `binarySearch`
- Modifying: `reverse`, `shuffle`, `copy`, `fill`
- Creating: `nCopies`
- Analyzing: `max`, `min`, `frequency`, `disjoint`

重点 / Important:

- `Collections.binarySearch` 要求 list 已排序。
- `Collections.copy(dest, src)` 要求 `dest.size() >= src.size()`。
- `shuffle(list, new Random(seed))` 可复现实验。

### 7.8 Vector and Stack / 旧式容器

`Vector`:

- 类似 `ArrayList`，但方法同步。
- 线程安全但慢；不需要同步时用 `ArrayList`。

`Stack`:

- extends `Vector`。
- LIFO: last in, first out.
- 方法：`push`, `pop`, `peek`, `empty`, `search`。
- `search` 从栈顶开始数，返回 1-based position，不是数组下标。

现代 Java 中更常推荐 / More modern Java style:

```java
import java.util.ArrayDeque;
import java.util.Deque;

public class StackWithDequeExample {
    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<>();

        stack.push(10);             // push to top
        stack.push(20);             // 20 is now the top

        System.out.println(stack.peek()); // read top, do not remove
        System.out.println(stack.pop());  // remove top
    }
}
```

关键区别 / Key distinction:

- `peek()` reads the top element without removing it.  
  `peek()` 只看栈顶，不删除。
- `pop()` removes and returns the top element.  
  `pop()` 删除并返回栈顶。
- Calling `pop()` on an empty stack may throw an exception.  
  空栈 `pop()` 可能抛异常，代码题里先检查 `isEmpty()`。

### 7.9 Queue and PriorityQueue / 队列与优先队列

`Queue`:

- FIFO: first in, first out.
- 安全方法：`offer`, `poll`, `peek`，空队列时返回 `false/null`。
- 风险方法：`add`, `remove`, `element`，失败时可能抛异常。

Queue method pairs / 队列方法对:

| Purpose / 目的 | Safer method / 温和方法 | Exception method / 异常方法 |
|---|---|---|
| insert at tail | `offer(e)` | `add(e)` |
| remove head | `poll()` | `remove()` |
| read head | `peek()` | `element()` |

完整例子 / Complete example:

```java
import java.util.ArrayDeque;
import java.util.Queue;

public class QueueReviewExample {
    public static void main(String[] args) {
        Queue<String> queue = new ArrayDeque<>();

        queue.offer("first");
        queue.offer("second");

        System.out.println(queue.peek()); // first, not removed
        System.out.println(queue.poll()); // first, removed
        System.out.println(queue.poll()); // second, removed
        System.out.println(queue.poll()); // null, because empty
    }
}
```

英文考试句 / Exam sentence:

> A queue is a FIFO structure: `offer` inserts at the tail, `poll` removes the head, and `peek` reads the head without removing it.

`PriorityQueue`:

- 按优先级出队，不按插入顺序。
- 默认使用 natural ordering，较小元素优先。
- 可传入 `Comparator` 改变优先级。
- 不允许 `null`。
- 元素必须 comparable 或提供 comparator。

完整例子 / Complete example:

```java
import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueReviewExample {
    public static void main(String[] args) {
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        minQueue.offer(30);
        minQueue.offer(10);
        minQueue.offer(20);

        System.out.println(minQueue.poll()); // 10

        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Comparator.reverseOrder());
        maxQueue.offer(30);
        maxQueue.offer(10);
        maxQueue.offer(20);

        System.out.println(maxQueue.poll()); // 30
    }
}
```

对象优先级 / Object priority:

```java
import java.util.Comparator;
import java.util.PriorityQueue;

public class TaskPriorityExample {
    public static void main(String[] args) {
        PriorityQueue<Task> tasks = new PriorityQueue<>(new TaskComparator());

        tasks.offer(new Task("Read notes", 2));
        tasks.offer(new Task("Finish TTL", 5));
        tasks.offer(new Task("Review traps", 4));

        while (!tasks.isEmpty()) {
            System.out.println(tasks.poll().getName());
        }
    }
}

class Task {
    private String name;
    private int priority;

    public Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }
}

class TaskComparator implements Comparator<Task> {
    @Override
    public int compare(Task first, Task second) {
        return Integer.compare(second.getPriority(), first.getPriority());
    }
}
```

为什么 `second` 在前 / Why `second` comes first:

- Java `PriorityQueue` 默认让“比较结果更小”的对象先出来。
- `Integer.compare(second.getPriority(), first.getPriority())` 会让 priority 较大的任务排在前面。
- 用 `Integer.compare` 比直接写 `second.getPriority() - first.getPriority()` 更安全，因为避免整数溢出。

PriorityQueue 坑点 / PriorityQueue traps:

- It is not FIFO.  
  它不是普通先进先出队列。
- Iteration is not sorted. Use repeated `poll()` to get priority order.  
  enhanced `for` 遍历 `PriorityQueue` 不保证有序；要按优先级顺序取出，反复 `poll()`。
- `offer` and `poll` are usually `O(log n)`, while `peek` is `O(1)`.  
  加入和删除通常 `O(log n)`，查看队头 `O(1)`。

英文考试句 / Exam sentence:

> A priority queue removes elements according to priority rather than insertion order; in Java, natural ordering usually gives the smallest element first unless a comparator changes the order.

### 7.10 Deque and ArrayDeque / 双端队列与 ArrayDeque

**中文：** `Deque<E>` 是 double-ended queue，队头和队尾都可以插入、删除、查看。`ArrayDeque<E>` 是常见实现，可以当 stack 用，也可以当 queue 用。

**English:** A `Deque<E>` is a double-ended queue. It supports insertion, removal, and inspection at both ends. `ArrayDeque<E>` is a common implementation and can be used as a stack or queue.

常用方法 / Common methods:

| Front / 队头 | Back / 队尾 |
|---|---|
| `addFirst(e)` | `addLast(e)` |
| `removeFirst()` | `removeLast()` |
| `peekFirst()` | `peekLast()` |

完整例子 / Complete example:

```java
import java.util.ArrayDeque;
import java.util.Deque;

public class DequeReviewExample {
    public static void main(String[] args) {
        Deque<String> deque = new ArrayDeque<>();

        deque.addFirst("front");
        deque.addLast("back");

        System.out.println(deque.peekFirst()); // front
        System.out.println(deque.peekLast());  // back

        deque.removeFirst();
        deque.removeLast();
    }
}
```

复习结论 / Revision conclusion:

> `ArrayDeque` is usually a good implementation for stack and FIFO queue behavior, while `PriorityQueue` is for priority-based processing.

---

## 8. Sets and Maps / 集合 Set 与映射 Map

### 8.1 Set / 集合

**中文：** Set 不允许重复元素，适合 membership testing、去重、唯一性约束。

**English:** A set stores unique elements and is ideal for membership tests and duplicate removal.

常用实现:

| Implementation | Order | Best for |
|---|---|---|
| `HashSet` | unpredictable | fastest general lookup |
| `LinkedHashSet` | insertion order | uniqueness plus insertion order |
| `TreeSet` | sorted order | sorted retrieval and range search |

### 8.2 HashSet Internals / HashSet 内部思想

**中文：** HashSet 依赖 `hashCode` 快速定位 bucket，再用 `equals` 判断真正相等。

**English:** HashSet uses `hashCode` to locate a bucket and `equals` to confirm equality.

重要合约:

- 如果 `a.equals(b)` 为 true，那么 `a.hashCode() == b.hashCode()` 必须为 true。
- hashCode 相同不一定 equals。
- 用作 set/map key 的对象最好不可变，否则修改字段会破坏定位。

Capacity and load factor:

- 默认初始容量常见为 16。
- 默认 load factor 常见为 0.75。
- 元素多到超过阈值时扩容/rehash。

### 8.3 Set Operations / 集合运算

```java
set1.addAll(set2);     // union
set1.removeAll(set2);  // difference
set1.retainAll(set2);  // intersection
```

### 8.4 SortedSet and NavigableSet

`TreeSet` implements `SortedSet` and `NavigableSet`。

常用方法:

- `first`, `last`
- `headSet(toElement)`
- `tailSet(fromElement)`
- `lower`, `floor`, `ceiling`, `higher`
- `pollFirst`, `pollLast`

注意 / Pitfall:

- `TreeSet` 的“重复”由排序比较决定。如果 comparator 认为两个对象相等，set 会把它们当作重复，即使 `equals` 不同。

### 8.5 Choosing Set vs List

选择规则 / Decision rule:

- 需要按 index 精确访问或允许重复：`List`。
- 需要快速查重、检查是否存在、去重：`HashSet`。
- 需要去重并保留插入顺序：`LinkedHashSet`。
- 需要去重并排序/范围查询：`TreeSet`。

课程案例 / Lecture case:

- Java keyword counting uses `HashSet<String>` for 53 reserved words.
- `contains(word)` gives average `O(1)` membership checking.

### 8.6 Map / 映射

**中文：** Map 保存 key-value pair。key 唯一，value 可重复。Map 不继承 Collection。

**English:** A map stores key-value pairs. Keys are unique; values may repeat. Map is not a Collection.

核心方法:

- Update: `put`, `putAll`, `remove`, `clear`
- Query: `get`, `containsKey`, `containsValue`, `isEmpty`, `size`
- Views: `keySet`, `values`, `entrySet`

`Map.Entry<K,V>`:

- `getKey`
- `getValue`
- `setValue`

### 8.7 HashMap, LinkedHashMap, TreeMap

| Implementation | Order | Complexity intuition | Best for |
|---|---|---|---|
| `HashMap` | random/unpredictable | average `O(1)` | fastest general key lookup |
| `LinkedHashMap` | insertion/access order | average `O(1)` plus links | history, LRU-like order |
| `TreeMap` | sorted by key | `O(log n)` | sorted keys, range navigation |

`LinkedHashMap(accessOrder=true)`:

- 被访问的 entry 移到末尾。
- 可用于 LRU 缓存思想。

`TreeMap`:

- key 必须 comparable 或提供 comparator。
- 可用 `firstKey`, `lastKey`, `headMap`, `tailMap`, `lowerKey`, `floorKey`, `ceilingKey`, `higherKey`。

### 8.8 Word Frequency Pattern / 词频统计模式

```java
Map<String, Integer> map = new TreeMap<>();
for (String word : words) {
    if (word.length() == 0) continue;
    word = word.toLowerCase();
    map.put(word, map.getOrDefault(word, 0) + 1);
}
```

按 value 排序 / Sort entries by value:

```java
List<Map.Entry<String, Integer>> entries =
    new ArrayList<>(map.entrySet());

entries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
```

关键理解:

- Map 天然按 key 管理，不天然按 value 排序。
- 要按 value 排序，取出 `entrySet` 转成 list，再用 comparator。

### 8.9 Immutable and Unmodifiable Collections

Empty constants:

- `Collections.EMPTY_SET`
- `Collections.EMPTY_LIST`
- `Collections.EMPTY_MAP`

Singletons:

- `Collections.singleton(o)`
- `Collections.singletonList(o)`
- `Collections.singletonMap(k, v)`

Unmodifiable wrappers:

- `Collections.unmodifiableList(list)`
- `Collections.unmodifiableSet(set)`
- `Collections.unmodifiableMap(map)`

JDK 9 factory methods:

- `List.of(...)`
- `Set.of(...)`
- `Map.of(...)`

区别 / Important distinction:

- unmodifiable view 是只读视图，底层集合若被内部代码修改，视图会反映变化。
- immutable factory method 创建的集合本身不可变。

---

## 9. Big O and Algorithm Analysis / Big O 与算法分析

### 9.1 Why Big O / 为什么不用运行时间直接比较

**中文：** 实测时间受机器、系统负载、输入分布影响。Big O 用输入规模增长率比较算法，关注当 `n` 变大时成本如何增长。

**English:** Actual runtime depends on hardware, system load, and input. Big O compares growth rate as input size increases.

### 9.2 Cases / 最好、平均、最坏情况

- Best case: shortest time for input size n.
- Average case: expected typical time, but input probabilities hard to model.
- Worst case: safe upper bound, most commonly used.

### 9.3 Simplification Rules / 化简规则

- Ignore constants: `O(100n) = O(n)`.
- Ignore lower-order terms: `O(n^2 + n + 1) = O(n^2)`.
- Constant time: independent of n, `O(1)`.

### 9.4 Common Loop Patterns

| Pattern | Example | Complexity |
|---|---|---|
| Single loop | `for i = 1..n` | `O(n)` |
| Full nested loop | `for i = 1..n`, `for j = 1..n` | `O(n^2)` |
| Triangular nested loop | `for j = 1..i` | `O(n^2)` |
| Constant inner loop | inner loop fixed 20 times | `O(n)` |
| Halving | `n = n / 2` repeatedly | `O(log n)` |
| Branches | take most expensive branch | max branch cost |

### 9.5 Growth Ladder / 增长阶梯

从慢到快:

```text
O(1) < O(log n) < O(n) < O(n log n) < O(n^2) < O(n^3) < O(2^n)
```

### 9.6 Collection Operation Complexity / 集合操作复杂度

这张表是 CPT204 里判断集合选择和代码效率的高频内容。复杂度通常讲 average/common case；具体还会受实现、碰撞、扩容、树高影响。

This table is high-yield for CPT204 collection-selection and code-efficiency questions. Complexities are typical/common cases unless stated otherwise.

| Operation / 操作 | Common structure / 常见结构 | Time / 时间 |
|---|---|---|
| access by index | `ArrayList.get(i)` | `O(1)` |
| access by index | `LinkedList.get(i)` | `O(n)` |
| append at end | `ArrayList.add(e)` | amortized `O(1)` |
| insert/remove middle | `ArrayList` | `O(n)` |
| push/pop/peek | stack with `ArrayDeque` | `O(1)` |
| offer/poll/peek | FIFO queue with `ArrayDeque` | `O(1)` |
| offer/poll | `PriorityQueue` | `O(log n)` |
| peek | `PriorityQueue` | `O(1)` |
| contains/search | unsorted `List` | `O(n)` |
| contains/add/remove | `HashSet` / `HashMap` | average `O(1)`, worst can degrade |
| search/insert/delete | `TreeSet` / `TreeMap` | `O(log n)` |

关键解释 / Key explanation:

- Amortized `O(1)` means occasional resizing is expensive, but the average cost over many insertions is constant.  
  摊还 `O(1)` 表示某一次扩容可能很贵，但大量插入平均下来是常数级。
- Big-O depends on implementation, not only interface.  
  复杂度取决于实现类，不只看接口名。
- If a structure is sorted or priority-based, updates often cost `O(log n)`.  
  如果结构维护排序或优先级，插入/删除通常不是 `O(1)`，常见是 `O(log n)`。

Exam sentence:

> The same interface can have different performance depending on its implementation; for example, `ArrayList` has fast index access, while `LinkedList` has slow index access.

### 9.7 Recurrence Patterns / 递推式模式

| Recurrence | Typical complexity | Example |
|---|---|---|
| `T(n) = T(n/2) + O(1)` | `O(log n)` | binary search |
| `T(n) = T(n - 1) + O(1)` | `O(n)` | simple recursion |
| `T(n) = 2T(n/2) + O(n)` | `O(n log n)` | merge sort |
| `T(n) = T(n - 1) + O(n)` | `O(n^2)` | selection-style recursion |
| `T(n) = 2T(n - 1) + O(1)` | `O(2^n)` | Tower of Hanoi |

---

## 10. Algorithm Design Examples / 典型算法设计

### 10.1 Fibonacci and Dynamic Programming

Naive recursive Fibonacci:

- 重复计算大量子问题。
- Time: `O(2^n)`。

Dynamic programming / 动态规划:

- 每个子问题只算一次。
- 保存中间结果，复用。
- Fibonacci iterative version: `O(n)` time, `O(1)` extra space if only保存前两个值。

### 10.2 GCD and Euclid's Algorithm

Brute force:

- 逐个检查可能 divisor。
- Worst case: `O(n)`。

Euclid:

```java
gcd(m, n) = gcd(n, m % n)
```

- Base case: `m % n == 0`, answer is `n`。
- Worst case happens for consecutive Fibonacci numbers.
- Time: `O(log n)`。

### 10.3 Prime Numbers and Sieve

单个数是否 prime:

- 检查到 `n / 2` 太慢。
- 只需检查到 `sqrt(n)`：若有因子，必有一个因子不超过平方根。

生成范围内所有质数:

- 先用已找到 primes 去除不必要 divisor。
- Sieve of Eratosthenes 用 boolean array 标记合数。
- 标准理论中，筛法对生成 `1..n` 的所有质数非常高效，常见复杂度记为 `O(n log log n)`；课件重点是它通过批量标记避免重复试除。

### 10.4 Closest Pair of Points

Brute force:

- 检查每对点。
- Time: `O(n^2)`。

Divide-and-conquer:

- 按 x 排序，分成左右两半。
- 递归求左右最近距离。
- 只检查中线附近 strip 中可能跨区的点。
- Time: `O(n log n)`。

### 10.5 Eight Queens and Backtracking

**Backtracking / 回溯：** 一行一行放 queen；一旦当前 partial solution 不可能成功，立即撤销并尝试下一个选择。

状态表示:

```java
queens[row] = column;
```

合法性检查:

- 同列冲突：`queens[i] == queens[row]`
- 对角线冲突：`Math.abs(queens[i] - queens[row]) == row - i`

核心思想:

- build incrementally
- reject invalid partial states early
- return to previous decision point

### 10.6 Convex Hull

**Convex hull / 凸包：** 包含所有点的最小凸多边形。

Gift-wrapping:

- 从一个 hull 点开始。
- 每次扫描所有点找下一个外侧点。
- Time: `O(hn)`, h is number of hull vertices.

Graham scan:

- 选 anchor。
- 按 polar angle 排序。
- 用 stack 保留左转，遇到右转 pop。
- Time: `O(n log n)`。

### 10.7 String Matching

Brute force:

- 尝试每个 alignment。
- 每次最多比较 pattern length `m`。
- Time: `O(nm)`。

Boyer-Moore:

- 从右向左比较。
- mismatch 后用 bad-character rule 跳过多位。
- 平均通常快，课程简化版本 worst case 仍可 `O(nm)`。

KMP:

- 预处理 pattern 的 failure function。
- mismatch 时复用已匹配前缀，不回退 text pointer。
- Time: `O(n + m)`。

---

## 11. Sorting and Heaps / 排序与堆

### 11.1 Bubble Sort / 冒泡排序

机制 / Mechanism:

- 多轮 pass。
- 每轮比较相邻元素，顺序错误则交换。
- 每轮结束后最大未排序元素到达尾部。

Complexity:

- Typical/worst: `O(n^2)`。
- 优化版如果某轮无 swap，可提前停止；已排序 best case `O(n)`。

### 11.2 Merge Sort / 归并排序

机制:

1. Divide array into two halves.
2. Recursively sort each half.
3. Merge two sorted halves.

复杂度:

- 每层 merge 总共处理 n 个元素。
- 层数约 `log n`。
- Time: `O(n log n)`。
- Space: `O(n)` temporary arrays.

### 11.3 Quick Sort / 快速排序

机制:

1. Choose pivot.
2. Partition: smaller/equal on left, greater on right.
3. Recursively sort left and right partitions.

复杂度:

- Best/average: balanced partitions, `O(n log n)`。
- Worst: pivot repeatedly creates empty + size n-1 subarray, `O(n^2)`。

课件实现选择 first element as pivot，因此已排序数组可能触发 worst case。

### 11.4 Binary Heap / 二叉堆

**Binary heap / 二叉堆：**

- Complete binary tree.
- Max heap: parent >= children.

Array representation:

- left child: `2i + 1`
- right child: `2i + 2`
- parent: `(i - 1) / 2`

Add / insertion:

- 新元素放末尾。
- while greater than parent, swap upward。
- Cost: `O(log n)`。

Remove root:

- 根是最大值。
- 用最后一个元素替换 root。
- 与较大 child 交换向下。
- Cost: `O(log n)`。

### 11.5 Heap Sort / 堆排序

过程:

1. 把所有元素 add 到 heap。
2. 重复 remove root，把最大值从数组末尾往前放。

复杂度:

- 每次 add/remove `O(log n)`。
- n 个元素，总体 `O(n log n)`。
- 比 merge sort 更省额外数组空间。

---

## 12. Graphs / 图

### 12.1 Graph Terminology / 图术语

Graph: `G = (V, E)`

- `V`: vertices/nodes.
- `E`: edges connecting vertices.

Directed graph:

- edge has direction.

Undirected graph:

- edge has no direction; connection is symmetric.

Weighted graph:

- edge has weight/cost/distance.

Unweighted graph:

- edge only means connected or not.

Other terms:

- adjacent vertices: connected by edge.
- incident edge: edge touching vertex.
- degree: number of incident edges.
- connected graph: path exists between every pair.
- cycle: closed path.
- tree: connected graph with no cycles.
- subgraph: subset of vertices and edges.
- spanning tree: includes all vertices, connected, no cycles.

### 12.2 Representing Graphs in Java

Vertices:

- array: `String[] vertices`
- list: `List<String> vertices`
- objects: `City` class storing richer data

Edges:

- edge array: `int[][] edges`
- edge objects: `class Edge { int u, v; }`
- adjacency matrix: `int[][] matrix`
- adjacency vertex list: `List<Integer>[] neighbors`
- adjacency edge list: `List<Edge>[] neighbors`

Weighted edge:

```java
class WeightedEdge extends Edge {
    double weight;
}
```

选择 / Trade-off:

- adjacency matrix: easy lookup, space `O(V^2)`。
- adjacency list: efficient for sparse graphs, traversal natural。

### 12.3 DFS / 深度优先搜索

**中文：** DFS 沿一条路径尽可能深入，走不通再回溯。

**English:** DFS explores as deeply as possible before backtracking.

Pseudo:

```text
DFS(v):
  mark v visited
  for each neighbor w:
    if w not visited:
      parent[w] = v
      DFS(w)
```

Complexity:

- `O(V + E)` with adjacency lists.

### 12.4 BFS / 广度优先搜索

**中文：** BFS 用 queue，按层访问。先访问起点，再访问距离 1 的邻居，再距离 2 的节点。

**English:** BFS uses a queue and visits vertices level by level.

Pseudo:

```text
BFS(start):
  enqueue start, mark visited
  while queue not empty:
    u = dequeue
    for each neighbor w:
      if w not visited:
        parent[w] = u
        mark visited
        enqueue w
```

Complexity:

- `O(V + E)`。

### 12.5 MST and Prim's Algorithm / 最小生成树与 Prim

Minimum Spanning Tree:

- includes all vertices.
- connected.
- no cycles.
- has minimum total edge weight.

Prim's algorithm:

- 从一个 vertex 开始。
- 维护集合 `T`：已经加入树的 vertices。
- 每次选择从 `T` 到 `V - T` 的最小权重边，把新 vertex 加入。

`cost[v]` meaning in Prim:

- cheapest edge weight connecting v to current tree T.

课程代码复杂度:

- 使用 `ArrayList` 存 `T`，`T.contains(i)` 是线性，课件实现给出 `O(n^3)`。
- 若用更合适结构/优先队列，可优化。

### 12.6 Dijkstra's Algorithm / Dijkstra 最短路径

Goal:

- from one source vertex to all other vertices shortest paths.

Process:

- `cost[source] = 0`, others infinity.
- `T` stores vertices whose shortest distance is finalized.
- each step choose outside-T vertex with smallest current cost.
- relax edges: if `cost[v] > cost[u] + weight(u, v)`, update.

`cost[v]` meaning in Dijkstra:

- current shortest known distance from source to v.

Prim vs Dijkstra:

| Aspect | Prim | Dijkstra |
|---|---|---|
| Goal | connect all vertices with minimum total weight | shortest paths from one source |
| `cost[v]` | cheapest edge to connect v to tree | shortest known source-to-v distance |
| Output | MST | shortest-path tree |
| Use case | building low-cost network | route/path planning |

---

## 13. Binary Search Trees / 二叉搜索树 BST

### 13.1 Binary Tree and BST

Binary tree:

- empty, or root + left subtree + right subtree.

BST property:

- all values in left subtree < node.
- all values in right subtree > node.
- duplicates are not inserted by default unless custom rule is defined.

Node representation:

```java
class TreeNode<E> {
    E element;
    TreeNode<E> left;
    TreeNode<E> right;
}
```

### 13.2 Search / 查找

Mechanism:

- 从 root 开始。
- key < current: go left.
- key > current: go right.
- equal: found.
- null: not found.

Time:

- `O(h)`, h is tree height.
- balanced: `O(log n)`.
- worst skewed: `O(n)`.

### 13.3 Insert / 插入

Mechanism:

- 用 `current` 找位置，用 `parent` 记录父节点。
- 找到 null 后接到 parent.left 或 parent.right。
- 若遇到相等值，默认返回 false，不插入。

### 13.4 Traversal / 遍历

Preorder:

- Node -> Left -> Right
- useful for copying tree structure.

Inorder:

- Left -> Node -> Right
- for BST, produces sorted order.

Postorder:

- Left -> Right -> Node
- useful for deleting/freeing subtrees.

Breadth-first:

- level by level, usually queue.

所有遍历 / all traversals:

- Time `O(n)`, because each node visited once.

### 13.5 Iterator for BST

**中文：** `inorder()`, `preorder()`, `postorder()` 通常直接打印；如果要让用户自定义处理每个元素，就实现 iterator，使 BST 支持 enhanced for loop。

**English:** Traversal methods often print directly; an iterator allows flexible user-defined processing through `for-each`.

Why for-each works:

- `Tree<E>` extends `Collection<E>`.
- `Collection<E>` extends `Iterable<E>`.
- Therefore BST can provide `iterator()`.

### 13.6 Delete / 删除

Step 1:

- locate `current` node and `parent`.

Case 1: `current.left == null`

- connect parent directly to `current.right`.
- if deleting root, root becomes `current.right`.

Case 2: `current.left != null`

- find rightmost node in current's left subtree.
- copy that rightmost value into current.
- remove the original rightmost node.
- rightmost may have left child; reconnect it.

Complexity:

- search/insert/delete follow one root-to-leaf path: `O(h)`.
- worst skewed BST: `O(n)`.

---

## 14. AVL Trees / AVL 树

### 14.1 Why Balanced Trees / 为什么需要平衡树

BST operations depend on height.

- Balanced height: `O(log n)`.
- Skewed height: `O(n)`.

AVL tree keeps BST property plus balance condition.

### 14.2 Balance Factor / 平衡因子

课程定义:

```text
BF = height(right subtree) - height(left subtree)
```

AVL condition:

```text
|BF| <= 1 for every node
```

Meaning:

- `BF = 0`: perfectly balanced.
- `BF = -1`: left subtree one level taller.
- `BF = +1`: right subtree one level taller.
- `BF = -2` or `+2`: rebalance required.

### 14.3 Rotations / 旋转

LL imbalance:

- A has `BF = -2`, A.left has `BF <= 0`。
- Perform LL rotation: right rotation around A.

RR imbalance:

- A has `BF = +2`, A.right has `BF >= 0`。
- Perform RR rotation: left rotation around A.

LR imbalance:

- A has `BF = -2`, A.left has `BF > 0`。
- Double rotation: first left rotation on left child, then right rotation on A.

RL imbalance:

- A has `BF = +2`, A.right has `BF < 0`。
- Double rotation: first right rotation on right child, then left rotation on A.

### 14.4 AVL Implementation Pattern

`AVLTree<E extends Comparable<E>> extends BST<E>`。

`AVLTreeNode` extends `TreeNode` and adds:

```java
protected int height = 0;
```

Insert workflow:

1. call `super.insert(e)`。
2. if inserted, call `balancePath(e)`。
3. from inserted node back to root:
   - update height.
   - compute balance factor.
   - rotate if needed.

Delete workflow:

- perform BST deletion.
- call `balancePath(...)` from affected parent/rightmost parent.

### 14.5 AVL Complexity

Because AVL height is `O(log n)`:

- search: `O(log n)`
- insert: `O(log n)`
- delete: `O(log n)`

At each node on the path:

- `updateHeight`: `O(1)`
- `balanceFactor`: `O(1)`
- rotation: `O(1)`

Total: path length `O(log n)` times constant work.

---

## 15. Hashing / 哈希

### 15.1 Why Hashing / 为什么用哈希

Without hashing:

- find element by scanning: often `O(n)`.

With hashing:

- hash function converts key/element to table index.
- average search/insert/delete: `O(1)` if load factor controlled.

### 15.2 Collisions / 冲突

**Collision / 冲突：** two different keys map to same index.

Two major strategies:

- Open addressing: find another empty slot in the same table.
- Separate chaining: store collided entries in a bucket/list at that index.

### 15.3 Linear Probing / 线性探测

Formula:

```text
index = (start + j) % N, j = 1, 2, 3...
```

Problem:

- primary clustering: long consecutive occupied blocks.
- new insertions may scan through cluster.

### 15.4 Quadratic Probing / 二次探测

Formula:

```text
index = (start + j^2) % N
```

Advantage:

- reduces primary clustering.

Problem:

- secondary clustering: keys with same start follow same probe sequence.

### 15.5 Double Hashing / 双重哈希

Formula:

```text
index = (h(key) + j * h2(key)) % N
```

Rules:

- `h2(key)` must not return 0.
- step size should be relatively prime to table size N.

Course example:

```text
h2(element) = q - element % q
```

where q is prime and smaller than N.

### 15.6 Separate Chaining / 分离链表

Each table index is a bucket.

- bucket can be array/list/linked list.
- all entries with same hash index stored in same bucket.
- load factor can exceed 1.

### 15.7 Load Factor and Rehashing / 负载因子与再哈希

```text
lambda = n / N
```

- n: number of elements.
- N: table size.

Open addressing:

- lambda between 0 and 1.
- lecture recommends keeping under 0.5.

Separate chaining:

- lambda can be greater than 1.
- lecture recommends keeping under 0.9.

Rehashing:

- when load factor exceeds threshold, create larger table.
- recompute indexes because N changed.
- usually at least double size to avoid repeated rehashing.

---

## 16. Project and Applied Design / 项目与综合应用

### 16.1 Group Project Topics

Scenario:

- city infrastructure inspection, such as roads, traffic lights, 5G stations.

Task A:

- use sorting algorithms such as bubble sort, quick sort, merge sort to identify highest-priority locations.
- evaluate algorithm performance.

Task B:

- plan paths/routes using graph algorithms.
- justify and evaluate algorithm choice.

Task C:

- describe the program and how OOP principles are applied.

Task D:

- reflect on project management tools such as JIRA/KANBAN.

Task E:

- 8-minute video presentation with PPT.

### 16.2 How to Explain OOP in Project

Use these ideas:

- Encapsulation: classes hide fields and expose methods.
- Abstraction: public APIs separate use from implementation.
- Inheritance: shared behavior in superclass when strong is-a exists.
- Polymorphism: process different subclasses through common superclass/interface.
- Interfaces: define capabilities, such as sortable/comparable, measurable, routable.
- Generics: reusable data structures and algorithms with type safety.

### 16.3 How to Justify Algorithms

Sorting:

- Bubble sort is simple but `O(n^2)`, mainly baseline.
- Merge sort is stable and `O(n log n)`, but needs extra memory.
- Quick sort is usually fast `O(n log n)`, but pivot choice can cause `O(n^2)`.
- Heap sort is `O(n log n)` and space efficient.

Graph routes:

- BFS for shortest number of edges in unweighted graph.
- Dijkstra for minimum total weight from one source.
- Prim/MST for connecting all locations with minimum total connection cost, not for shortest route from source.

Data structures:

- `HashSet` for duplicate removal/membership.
- `TreeSet`/`TreeMap` for sorted output/range.
- `HashMap` for fast key-value lookup.
- `PriorityQueue` for repeatedly selecting highest/lowest priority item.

---

## 17. High-Yield Comparison Tables / 高频对比表

### 17.1 Core Data Structure Choice

| Need / 需求 | Choose / 选择 | Why / 原因 |
|---|---|---|
| fixed-size same-type sequence | array | simple, indexed, fixed length |
| resizable indexed sequence | `ArrayList` | fast random access |
| frequent head/tail insert/remove | `LinkedList` | pointer updates |
| stack LIFO processing | `ArrayDeque` as `Deque` | efficient `push/pop/peek` |
| double-ended operations | `ArrayDeque` as `Deque` | operations at both ends |
| no duplicates, fastest lookup | `HashSet` | hashing average `O(1)` |
| no duplicates, insertion order | `LinkedHashSet` | linked order |
| no duplicates, sorted order | `TreeSet` | balanced tree ordering |
| FIFO processing | `Queue` with `ArrayDeque` | arrival order |
| priority processing | `PriorityQueue` | priority order |
| key-value lookup | `HashMap` | fast by key |
| sorted key-value lookup | `TreeMap` | key order and navigation |

### 17.2 Algorithm Complexity Summary

| Algorithm / Operation | Best / 最佳 | Average / 平均 | Worst / 最坏/最慢 |
|---|---|---|---|
| Linear search | `O(1)` | `O(n)` | `O(n)` |
| Binary search | `O(1)` | `O(log n)` | `O(log n)` |
| Selection sort | `O(n^2)` | `O(n^2)` | `O(n^2)` |
| Insertion sort | `O(n)` | `O(n^2)` | `O(n^2)` |
| Bubble sort optimized | `O(n)` | `O(n^2)` | `O(n^2)` |
| Merge sort | `O(n log n)` | `O(n log n)` | `O(n log n)` |
| Quick sort | `O(n log n)` | `O(n log n)` | `O(n^2)` |
| Heap add/remove | `O(1)` best add/remove case | `O(log n)` | `O(log n)` |
| Heap sort | `O(n log n)` | `O(n log n)` | `O(n log n)` |
| DFS/BFS full traversal | `O(V + E)` | `O(V + E)` | `O(V + E)` |
| BST search/insert/delete | `O(1)` | `O(log n)` balanced | `O(n)` skewed |
| AVL search/insert/delete | `O(1)` search at root | `O(log n)` | `O(log n)` |
| Hash table search/insert/delete | `O(1)` | `O(1)` | `O(n)` |
| Brute-force string match | `O(n)` | `O(nm)` course bound | `O(nm)` |
| KMP | `O(n + m)` | `O(n + m)` | `O(n + m)` |
| Euclid GCD | `O(1)` | `O(log n)` | `O(log n)` |
| Tower of Hanoi | `O(2^n)` | `O(2^n)` | `O(2^n)` |

### 17.3 All Learned Algorithms Big-O And Use Cases / 所学算法 Big-O 与用途总表

这一张表把课程里出现过的主要算法和数据结构操作统一放在一起。考试里如果问 “Which algorithm should be used?” 或 “What is the Big-O notation?”，先从这里找。

This table collects the main algorithms and data-structure operations covered in the course. Use it when an exam asks which algorithm to choose or what the Big-O notation is.

说明 / Note: 对某些算法，课程通常只要求 full run 或 worst case；这时 Best/Average/Worst 可能相同。For some algorithms, the course usually analyzes the full run or worst case, so the best, average, and worst cases may be the same.

| Algorithm / 算法 | Best / 最佳 | Average / 平均 | Worst / 最坏/最慢 | Use case / 用处 |
|---|---|---|---|---|
| Array traversal / 数组遍历 | `O(n)` | `O(n)` | `O(n)` | Visit every element; sum/count/max/min. / 访问每个元素；求和、计数、最大最小。 |
| Array copy / 数组复制 | `O(n)` | `O(n)` | `O(n)` | Copy all elements. / 复制所有元素。 |
| Linear search / 线性查找 | `O(1)` | `O(n)` | `O(n)` | Search unsorted data. / 查找未排序数据。 |
| Binary search / 二分查找 | `O(1)` | `O(log n)` | `O(log n)` | Search sorted data. / 查找已排序数据。 |
| Selection sort / 选择排序 | `O(n^2)` | `O(n^2)` | `O(n^2)` | Simple repeated minimum selection. / 反复选择最小值。 |
| Insertion sort / 插入排序 | `O(n)` | `O(n^2)` | `O(n^2)` | Good for nearly sorted data. / 适合几乎有序数据。 |
| Bubble sort / 冒泡排序 | `O(n)` optimized | `O(n^2)` | `O(n^2)` | Teaching swap-based sorting. / 理解相邻交换排序。 |
| Merge sort / 归并排序 | `O(n log n)` | `O(n log n)` | `O(n log n)` | Stable divide-and-conquer sorting. / 稳定分治排序。 |
| Quick sort / 快速排序 | `O(n log n)` | `O(n log n)` | `O(n^2)` | Fast general sorting; pivot matters. / 常用快速排序；pivot 很关键。 |
| Heap insertion / 堆插入 | `O(1)` | `O(log n)` | `O(log n)` | Add while keeping heap property. / 插入并保持堆性质。 |
| Heap removal / 堆删除根 | `O(1)` | `O(log n)` | `O(log n)` | Remove max/min root. / 删除堆顶最大或最小元素。 |
| Heap sort / 堆排序 | `O(n log n)` | `O(n log n)` | `O(n log n)` | Space-efficient comparison sorting. / 较省空间的比较排序。 |
| PriorityQueue `offer` / 优先队列入队 | `O(1)` | `O(log n)` | `O(log n)` | Add item by priority. / 按优先级加入元素。 |
| PriorityQueue `poll` / 优先队列出队 | `O(1)` | `O(log n)` | `O(log n)` | Remove next priority item. / 取出下一个优先级元素。 |
| PriorityQueue `peek` / 查看队头 | `O(1)` | `O(1)` | `O(1)` | Inspect without removing. / 查看但不删除。 |
| Stack push/pop/peek / 栈操作 | `O(1)` | `O(1)` | `O(1)` | LIFO processing. / 后进先出。 |
| Queue offer/poll/peek / 队列操作 | `O(1)` amortized | `O(1)` amortized | `O(n)` during resize | FIFO processing and BFS. / 先进先出和 BFS。 |
| DFS / 深度优先搜索 | `O(V + E)` | `O(V + E)` | `O(V + E)` | Components, cycles, path exploration. / 连通分量、环、路径探索。 |
| BFS / 广度优先搜索 | `O(V + E)` | `O(V + E)` | `O(V + E)` | Shortest edge count in unweighted graphs. / 无权图最少边路径。 |
| Prim's algorithm / Prim 最小生成树 | `O(n^3)` course version | `O(n^3)` course version | `O(n^3)` course version | Minimum spanning tree. / 最小生成树。 |
| Prim optimized / Prim 优化版 | `O(E log V)` | `O(E log V)` | `O(E log V)` | MST with priority queue. / 用优先队列求 MST。 |
| Dijkstra simple / Dijkstra 简单版 | `O(V^2)` | `O(V^2)` | `O(V^2)` | Single-source shortest paths. / 单源最短路径。 |
| Dijkstra with priority queue / Dijkstra 优先队列版 | `O((V+E) log V)` | `O((V+E) log V)` | `O((V+E) log V)` | Faster sparse-graph shortest paths. / 稀疏图更快最短路。 |
| BST search / BST 查找 | `O(1)` | `O(log n)` | `O(n)` | Search by BST ordering. / 利用左小右大查找。 |
| BST insertion / BST 插入 | `O(1)` | `O(log n)` | `O(n)` | Insert while preserving BST rule. / 插入并保持 BST 性质。 |
| BST deletion / BST 删除 | `O(1)` | `O(log n)` | `O(n)` | Remove leaf/one-child/two-child node. / 删除不同情况节点。 |
| Tree inorder traversal / 中序遍历 | `O(n)` | `O(n)` | `O(n)` | Sorted output for BST. / BST 输出有序结果。 |
| Tree preorder traversal / 前序遍历 | `O(n)` | `O(n)` | `O(n)` | Copy. / 复制。 |
| Tree postorder traversal / 后序遍历 | `O(n)` | `O(n)` | `O(n)` | Delete/free subtrees. / 删除或释放子树。 |
| AVL search / AVL 查找 | `O(1)` | `O(log n)` | `O(log n)` | Balanced-tree lookup. / 平衡树查找。 |
| AVL insertion / AVL 插入 | `O(1)` | `O(log n)` | `O(log n)` | Insert and rebalance. / 插入并重新平衡。 |
| AVL deletion / AVL 删除 | `O(1)` | `O(log n)` | `O(log n)` | Delete and rebalance. / 删除并重新平衡。 |
| AVL rotation / AVL 旋转 | `O(1)` | `O(1)` | `O(1)` | Restore balance locally. / 局部恢复平衡。 |
| Hash table search/insert/delete / 哈希表操作 | `O(1)` | `O(1)` | `O(n)` | Fast membership and key-value lookup. / 快速成员测试和键值查找。 |
| Linear probing / 线性探测 | `O(1)` | `O(1)` if controlled | `O(n)` | Open-address collision handling. / 开放地址冲突处理。 |
| Quadratic probing / 二次探测 | `O(1)` | `O(1)` if controlled | `O(n)` | Reduce primary clustering. / 减少主聚集。 |
| Double hashing / 双重哈希 | `O(1)` | `O(1)` if controlled | `O(n)` | Second hash step for collisions. / 第二哈希步长处理冲突。 |
| Separate chaining / 分离链表 | `O(1)` | `O(1)` | `O(n)` | Store collisions in bucket lists. / 冲突元素放入桶链表。 |
| Rehashing / 再哈希 | `O(n)` | `O(n)` | `O(n)` | Rebuild table. / 重建表。 |
| Euclid GCD / 欧几里得算法 | `O(1)` | `O(log n)` | `O(log n)` | Greatest common divisor. / 最大公因数。 |
| Prime test by trial division / 试除法判质数 | `O(1)` | `O(sqrt n)` | `O(sqrt n)` | Test one number for primality. / 判断单个数是否质数。 |
| Sieve of Eratosthenes / 埃拉托色尼筛 | `O(n log log n)` | `O(n log log n)` | `O(n log log n)` | Generate primes up to n. / 生成 n 以内质数。 |
| Fibonacci naive recursion / 斐波那契朴素递归 | `O(1)` base case | `O(2^n)` | `O(2^n)` | Show repeated subproblems. / 展示重复子问题。 |
| Fibonacci dynamic programming / 斐波那契动态规划 | `O(1)` base case | `O(n)` | `O(n)` | Store results to avoid repetition. / 存结果避免重复计算。 |
| Closest pair brute force / 最近点对暴力法 | `O(n^2)` | `O(n^2)` | `O(n^2)` | Check all point pairs. / 检查所有点对。 |
| Closest pair divide and conquer / 最近点对分治法 | `O(n log n)` | `O(n log n)` | `O(n log n)` | Efficient closest pair search. / 高效找最近点对。 |
| Eight queens backtracking / 八皇后回溯 | Case-dependent | Exponential | Exponential | Search valid placements. / 搜索合法摆法。 |
| Convex hull gift-wrapping / 凸包 Gift Wrapping | `O(n)` when h small | `O(nh)` | `O(n^2)` | Build hull by boundary points. / 通过边界点构造凸包。 |
| Convex hull Graham scan / 凸包 Graham Scan | `O(n log n)` | `O(n log n)` | `O(n log n)` | Hull after angle sorting. / 角度排序后构造凸包。 |
| Brute-force string matching / 暴力字符串匹配 | `O(n)` | `O(nm)` course bound | `O(nm)` | Simple substring search. / 简单子串查找。 |
| KMP string matching / KMP 字符串匹配 | `O(n + m)` | `O(n + m)` | `O(n + m)` | Prefix-table substring search. / 前缀表子串查找。 |
| Boyer-Moore string matching / Boyer-Moore 字符串匹配 | Often sublinear | Often sublinear | `O(nm)` | Fast practical mismatch shifts. / 利用失配跳跃。 |
| Tower of Hanoi / 汉诺塔 | `O(2^n)` | `O(2^n)` | `O(2^n)` | Classic exponential recursion. / 指数级经典递归。 |

Quick exam reminders / 考试速记:

- `O(log n)` usually means the search space is repeatedly halved.  
  `O(log n)` 通常说明搜索空间不断减半。
- `O(n log n)` is common for efficient comparison sorting.  
  `O(n log n)` 常见于高效比较排序。
- `O(V + E)` means graph traversal visits vertices and edges.  
  `O(V + E)` 表示图遍历会访问顶点和边。
- Hashing is average `O(1)`, but poor hashing or too many collisions can degrade performance.  
  哈希平均 `O(1)`，但哈希差或冲突多会变慢。

### 17.4 Collection Method Quick Table / 集合方法速查

| Structure / 结构 | Main methods / 主要方法 | Meaning / 含义 |
|---|---|---|
| `List` | `add`, `get`, `set`, `remove`, `contains`, `size` | indexed sequence |
| `Iterator` | `hasNext`, `next`, `remove` | safe traversal and deletion |
| `Stack` / `Deque` stack | `push`, `pop`, `peek`, `isEmpty` | LIFO |
| `Queue` | `offer`, `poll`, `peek` | FIFO safe methods |
| `Deque` | `addFirst`, `addLast`, `removeFirst`, `removeLast` | both-end operations |
| `PriorityQueue` | `offer`, `poll`, `peek` | priority order |
| `Map` | `put`, `get`, `containsKey`, `remove`, `keySet`, `entrySet` | key-value lookup |
| `Set` | `add`, `contains`, `remove` | uniqueness |

考试提醒 / Exam reminders:

- `peek` means read without removing.  
  `peek` 只看不删。
- `poll` removes queue head; `pop` removes stack top.  
  `poll` 删除队头；`pop` 删除栈顶。
- `PriorityQueue` iteration is not sorted.  
  遍历优先队列不保证有序。
- `List.remove(1)` in `List<Integer>` removes by index.  
  `List<Integer>` 中 `remove(1)` 默认删 index 1。

### 17.4.1 Java Method Patterns And Replaceable Parts / Java 常用方法模板与可替换位置

考试里很多代码题不会直接问“这个方法是什么意思”，而是把变量名、集合类型、条件、比较规则或返回值换掉，让你补全代码、判断输出或解释复杂度。本节用 `<...>` 标出最容易被替换的位置。

In many exam questions, the method itself stays the same, but variable names, collection types, conditions, comparison rules, or returned values are replaced. This section marks replaceable parts with `<...>`.

重要约定 / Important convention:

- `<>` 在真实 Java 里可以表示 generics，例如 `ArrayList<String>`。  
  In real Java, `<>` can mean generics, such as `ArrayList<String>`.
- 本节模板中的 `<collection>`, `<condition>`, `<value>` 是“占位符”，考试或你自己写代码时要替换成真实代码。  
  In the templates below, `<collection>`, `<condition>`, and `<value>` are placeholders. Replace them with real Java code.
- 不要把 `<condition>` 这种占位符原样写进 Java 程序；它只是告诉你“这里可能被题目替换”。  
  Do not literally type placeholder names such as `<condition>` into Java code.

#### A. Iterator / 迭代器方法

| Method / 方法 | Effect / 作用 | Replaceable parts / 可能替换位置 | Exam trap / 考试坑点 |
|---|---|---|---|
| `<collection>.iterator()` | creates an iterator over a collection / 创建迭代器 | `<collection>` may become a `List`, `Set`, custom `BST`, etc. / 集合对象可能被替换 | Object must implement `Iterable`. / 对象需要实现 `Iterable` |
| `it.hasNext()` | checks whether another element exists / 判断是否还有下一个元素 | loop condition may be changed / 循环条件可能被换 | Calling `next()` without checking may throw exception. / 不检查就 `next()` 可能异常 |
| `it.next()` | returns current next element and moves forward / 取出下一个元素并前进 | element type `<E>` may change / 元素类型可能变 | Two `next()` calls in one loop move two steps. / 一轮循环调用两次会跳过元素 |
| `it.remove()` | removes the last element returned by `next()` / 删除刚刚由 `next()` 返回的元素 | remove condition may change / 删除条件可能被换 | Must be after `next()` and only once per `next()`. / 必须在 `next()` 后，且一次 `next()` 最多一次 `remove()` |

标准删除模板 / Standard safe-removal template:

```java
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorRemovalTemplate {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(3);
        numbers.add(10);
        numbers.add(15);
        numbers.add(22);

        Iterator<Integer> iterator = numbers.iterator();
        while (iterator.hasNext()) {
            Integer value = iterator.next();

            // 可替换位置 1: <conditionToRemove>
            // Replaceable part 1: change this condition in exam questions.
            if (value % 2 == 0) {
                iterator.remove();
            }
        }

        System.out.println(numbers); // [3, 15]
    }
}
```

可替换位置总结 / Replaceable parts:

- `<ElementType>`: `Integer`, `String`, `Student`, `Edge`, `TreeNode<E>` 等。  
  Element type may be replaced by `Integer`, `String`, `Student`, `Edge`, `TreeNode<E>`, etc.
- `<collection>`: `numbers`, `names`, `students`, `tree`, `set` 等。  
  Collection variable may change.
- `<conditionToRemove>`: `value % 2 == 0`, `value.compareTo(target) < 0`, `student.getMark() < 40` 等。  
  Removal condition is often replaced.
- `<action>`: 打印、计数、求和、删除、插入到另一个集合。  
  The loop body may print, count, sum, remove, or copy elements.

#### B. Enhanced For Loop vs Iterator / 增强 for 与迭代器

只读遍历模板 / Read-only traversal:

```java
for (Integer value : numbers) {
    System.out.println(value);
}
```

边遍历边删除时不要这样写 / Do not structurally remove like this:

```java
for (Integer value : numbers) {
    if (value % 2 == 0) {
        numbers.remove(value); // risky: may cause ConcurrentModificationException
    }
}
```

考试英文句 / Exam English sentence:

> Use an explicit iterator when removing elements during traversal because `Iterator.remove()` updates the iterator state consistently.

中文理解：边遍历边结构性删除时，用 `Iterator.remove()`，因为它知道自己刚访问了哪个元素，也能让迭代器状态保持一致。

#### C. ListIterator / 双向列表迭代器

| Method / 方法 | Effect / 作用 | Replaceable parts / 可替换位置 |
|---|---|---|
| `list.listIterator()` | starts before the first element / 从第一个元素前开始 | `<list>` may change |
| `list.listIterator(index)` | starts at a given position / 从指定下标开始 | `<index>` may change |
| `hasPrevious()` / `previous()` | moves backward / 向前一个方向移动 | direction may change |
| `add(element)` | inserts during iteration / 迭代时插入 | `<element>` may change |
| `set(element)` | replaces last returned element / 替换刚返回的元素 | replacement value may change |

完整例子 / Complete example:

```java
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ListIteratorTemplate {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("java");
        words.add("exam");
        words.add("list");

        ListIterator<String> iterator = words.listIterator();
        while (iterator.hasNext()) {
            String word = iterator.next();

            // 可替换位置: <targetValue> and <replacementValue>
            if (word.equals("exam")) {
                iterator.set("CPT204");
                iterator.add("review");
            }
        }

        System.out.println(words); // [java, CPT204, review, list]
    }
}
```

#### D. Collection and List Methods / 集合与列表方法

| Method / 方法 | Effect / 作用 | Return / 返回值 | Replaceable parts / 可替换位置 |
|---|---|---|---|
| `add(e)` | appends or inserts element / 添加元素 | usually `boolean` for `Collection`, `void` for `List.add(index,e)` | `<e>`, `<index>` |
| `get(index)` | reads element at index / 读取下标元素 | element | `<index>` |
| `set(index, e)` | replaces element at index / 替换下标元素 | old element / 旧元素 | `<index>`, `<e>` |
| `remove(index)` | removes by position / 按下标删除 | removed element | `<index>` |
| `remove(object)` | removes by value / 按对象值删除 | `boolean` | `<object>` |
| `contains(e)` | membership test / 成员测试 | `boolean` | `<e>` |
| `size()` | number of elements / 元素个数 | `int` | none |
| `isEmpty()` | checks empty / 是否为空 | `boolean` | none |
| `clear()` | removes all elements / 清空 | `void` | none |
| `indexOf(e)` | first index of value / 第一次出现位置 | `int`, or `-1` if absent | `<e>` |
| `subList(from, to)` | view from `from` inclusive to `to` exclusive / 左闭右开子列表视图 | `List<E>` | `<from>`, `<to>` |

`List<Integer>` 删除重载完整例子 / Complete overload example:

```java
import java.util.ArrayList;
import java.util.List;

public class ListRemoveTemplate {
    public static void main(String[] args) {
        List<Integer> values = new ArrayList<>();
        values.add(1);
        values.add(2);
        values.add(3);

        values.remove(1);                  // removes index 1, value 2
        values.remove(Integer.valueOf(1)); // removes object/value 1

        System.out.println(values);        // [3]
    }
}
```

可能被替换 / What may be replaced:

- `1` 可能是 index，也可能要包成 `Integer.valueOf(1)` 表示对象值。  
  `1` may mean an index, or it may need `Integer.valueOf(1)` to mean a value object.
- `subList(from, to)` 的 `to` 不包含。  
  `to` in `subList(from, to)` is exclusive.

#### E. Stack, Queue, Deque, PriorityQueue Methods / 栈、队列、双端队列、优先队列方法

| Structure / 结构 | Safer/common methods / 常用方法 | Effect / 作用 | Replaceable parts / 可替换位置 |
|---|---|---|---|
| Stack via `Deque` | `push(e)`, `pop()`, `peek()` | LIFO / 后进先出 | `<e>`, stack variable |
| Queue | `offer(e)`, `poll()`, `peek()` | FIFO / 先进先出 | `<e>`, queue variable |
| Queue exception pair | `add(e)`, `remove()`, `element()` | may throw exception / 可能抛异常 | method choice |
| Deque front | `addFirst(e)`, `removeFirst()`, `peekFirst()` | front-end operation / 前端操作 | `<e>` |
| Deque back | `addLast(e)`, `removeLast()`, `peekLast()` | back-end operation / 后端操作 | `<e>` |
| PriorityQueue | `offer(e)`, `poll()`, `peek()` | smallest or highest-priority element first / 最小或最高优先级先出 | comparator |

PriorityQueue 比较器模板 / Comparator template:

```java
import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueMethodTemplate {
    public static void main(String[] args) {
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        minQueue.offer(30);
        minQueue.offer(10);
        minQueue.offer(20);
        System.out.println(minQueue.poll()); // 10

        // 可替换位置: <comparisonRule>
        // This comparator reverses the natural order, so larger numbers leave first.
        PriorityQueue<Integer> maxQueue =
                new PriorityQueue<>(Comparator.reverseOrder());
        maxQueue.offer(30);
        maxQueue.offer(10);
        maxQueue.offer(20);
        System.out.println(maxQueue.poll()); // 30
    }
}
```

考试坑点 / Exam traps:

- `peek()` 不删除，`poll()` 删除。  
  `peek()` inspects; `poll()` removes.
- `PriorityQueue` 的 enhanced for 遍历不保证 priority order。  
  Enhanced for over a `PriorityQueue` does not guarantee priority order.
- 如果题目换了 comparator，出队顺序也会变。  
  If the comparator changes, the removal order changes.

#### F. Map and Map.Entry Methods / 映射与键值对方法

| Method / 方法 | Effect / 作用 | Return / 返回值 | Replaceable parts / 可替换位置 |
|---|---|---|---|
| `put(key, value)` | inserts or replaces mapping / 插入或替换键值对 | old value or `null` | `<key>`, `<value>` |
| `get(key)` | finds value by key / 根据 key 找 value | value or `null` | `<key>` |
| `getOrDefault(key, defaultValue)` | gets value or default / 找不到时给默认值 | value/default | `<key>`, `<defaultValue>` |
| `containsKey(key)` | tests key existence / 判断 key 是否存在 | `boolean` | `<key>` |
| `remove(key)` | removes mapping by key / 按 key 删除 | old value or `null` | `<key>` |
| `keySet()` | returns keys / 返回所有 key | `Set<K>` | loop variable |
| `values()` | returns values / 返回所有 value | `Collection<V>` | loop variable |
| `entrySet()` | returns key-value entries / 返回键值对集合 | `Set<Map.Entry<K,V>>` | entry variable |
| `entry.getKey()` | reads key from entry / 读 key | key | none |
| `entry.getValue()` | reads value from entry / 读 value | value | none |

计数模板 / Counting template:

```java
import java.util.HashMap;
import java.util.Map;

public class MapCountingTemplate {
    public static void main(String[] args) {
        String[] words = {"java", "tree", "java", "map"};
        Map<String, Integer> counts = new HashMap<>();

        for (String word : words) {
            // 可替换位置: <key>, <defaultValue>, <newValue>
            int oldCount = counts.getOrDefault(word, 0);
            counts.put(word, oldCount + 1);
        }

        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
```

#### G. Arrays, Collections, and String Utility Methods / 数组、集合工具类与字符串方法

| Area / 类别 | Method / 方法 | Effect / 作用 | Replaceable parts / 可替换位置 |
|---|---|---|---|
| `Arrays` | `Arrays.sort(array)` | sorts array / 排序数组 | `<array>` |
| `Arrays` | `Arrays.binarySearch(array, key)` | binary search in sorted array / 已排序数组二分查找 | `<array>`, `<key>` |
| `Arrays` | `Arrays.copyOf(array, newLength)` | copies and resizes / 复制并改变长度 | `<newLength>` |
| `Arrays` | `Arrays.asList(a, b, c)` | fixed-size list view / 固定大小列表视图 | elements |
| `Collections` | `Collections.sort(list)` | sorts list / 排序列表 | `<list>` |
| `Collections` | `Collections.binarySearch(list, key)` | binary search in sorted list / 已排序列表二分查找 | `<key>` |
| `Collections` | `Collections.reverse(list)` | reverses order / 反转顺序 | `<list>` |
| `Collections` | `Collections.max(list)` / `min(list)` | max/min by natural order / 自然顺序最大最小 | `<list>` |
| `String` | `length()` | string length / 字符串长度 | none |
| `String` | `charAt(index)` | character at index / 取指定字符 | `<index>` |
| `String` | `substring(start, end)` | substring, end exclusive / 子串，右边不包含 | `<start>`, `<end>` |
| `String` | `indexOf(target)` | first occurrence / 第一次出现位置 | `<target>` |
| `String` | `equals(other)` | content equality / 内容相等 | `<other>` |
| `String` | `compareTo(other)` | lexicographic comparison / 字典序比较 | `<other>` |

字符串模板 / String template:

```java
public class StringMethodTemplate {
    public static void main(String[] args) {
        String text = "CPT204 Java";

        // 可替换位置: <index>, <start>, <end>, <target>
        System.out.println(text.length());        // 11
        System.out.println(text.charAt(0));       // C
        System.out.println(text.substring(0, 6)); // CPT204
        System.out.println(text.indexOf("Java")); // 7
        System.out.println(text.equals("Java"));  // false
    }
}
```

#### H. Comparable and Comparator Methods / 自然顺序与外部比较器方法

| Method / 方法 | Where / 位置 | Meaning / 含义 | Replaceable parts / 可替换位置 |
|---|---|---|---|
| `compareTo(other)` | inside `Comparable<T>` class | natural order / 自然顺序 | compared field |
| `compare(a, b)` | inside `Comparator<T>` class | custom order / 自定义顺序 | comparison rule |
| `Comparator.reverseOrder()` | utility comparator | reverse natural order / 反转自然顺序 | none |

完整比较器例子 / Complete comparator example:

```java
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Student {
    private String name;
    private int mark;

    public Student(String name, int mark) {
        this.name = name;
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public int getMark() {
        return mark;
    }

    public String toString() {
        return name + ":" + mark;
    }
}

class MarkDescendingComparator implements Comparator<Student> {
    public int compare(Student first, Student second) {
        // 可替换位置: <comparisonField> and <orderDirection>
        return second.getMark() - first.getMark();
    }
}

public class ComparatorMethodTemplate {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Amy", 80));
        students.add(new Student("Ben", 95));
        students.add(new Student("Cara", 70));

        students.sort(new MarkDescendingComparator());
        System.out.println(students); // [Ben:95, Amy:80, Cara:70]
    }
}
```

英文答题句 / English answer:

> `compare(a, b)` returns a negative value when `a` should come before `b`, zero when they are equal in ordering, and a positive value when `a` should come after `b`.

额外坑点 / Extra trap: `return second.getMark() - first.getMark();` 对小范围成绩很直观；如果比较的是可能很大的 `int`，更安全的写法是 `Integer.compare(second.getMark(), first.getMark())`，避免整数溢出。

For small marks, subtraction is easy to read. For large integers, `Integer.compare(second.getMark(), first.getMark())` is safer because it avoids integer overflow.

#### I. Custom Tree/BST Iterator Method Pattern / 自定义树迭代器方法模板

课程 BST 代码里常见方法名可能是自定义的，例如 `insert`, `delete`, `search`, `inorder`, `preorder`, `postorder`, `iterator`。考试可能把元素类型、遍历顺序、删除目标或比较条件替换掉。

In course BST code, method names may be custom, such as `insert`, `delete`, `search`, `inorder`, `preorder`, `postorder`, and `iterator`. Exam questions may replace element type, traversal order, deletion target, or comparison condition.

```java
// Pseudocode-style Java template for a BST iterator idea.
// 这里是思想模板：真实课程代码可能用 inner class 或 ArrayList 保存遍历结果。
Iterator<E> iterator() {
    // 可替换位置: <traversalOrder>
    // inorder gives sorted order for a BST.
    return inorderList.iterator();
}
```

BST 相关可替换点 / Replaceable BST parts:

- `<traversalOrder>`: `inorder`, `preorder`, `postorder`, `breadth-first`。  
  Traversal order may change.
- `<target>`: 要搜索、插入或删除的元素。  
  Target value may change.
- `<comparison>`: `element.compareTo(current.element) < 0` 等比较方向。  
  Comparison direction may change.
- `<case>`: 删除节点时的 leaf、one child、two children。  
  Deletion case may change.

### 17.5 Interface vs Abstract Class vs Concrete Class

| Type | Meaning | Can instantiate? | Contains implementation? |
|---|---|---|---|
| Interface | capability/contract | no | modern Java can have defaults, course focus is abstract methods/constants |
| Abstract class | partial implementation/base type | no | yes |
| Concrete class | usable implementation | yes | yes |

### 17.6 Comparable vs Comparator

| Item | Comparable | Comparator |
|---|---|---|
| Where defined | inside class | outside class |
| Method | `compareTo` | `compare` |
| Meaning | natural order | custom order |
| Number of orderings | usually one | many |
| Example | `String`, `Integer` | sort by length, by area, by amount |

### 17.7 High-Yield Java Keywords In Course Code / 课程代码高频关键字

| Keyword / 关键字 | Exam meaning / 英文考点 | Common CPT204 use / 常见位置 |
|---|---|---|
| `class` | defines a blueprint for objects | `Student`, `BST`, `AVLTree` |
| `interface` | defines a contract | `Comparable`, `Comparator`, `Iterable` |
| `implements` | promises to provide interface methods | `implements Comparable<E>` |
| `extends` | inherits from superclass or sets generic upper bound | `AVLTree extends BST`, `<E extends Comparable<E>>` |
| `new` | creates a concrete object or array | `new ArrayList<>()`, `new int[n]` |
| `static` | belongs to the class, not one object | `main`, helper methods |
| `final` | prevents reassignment/overriding/inheritance depending on target | constants, fixed references |
| `this` | current object reference | constructors, instance methods |
| `super` | superclass part of current object | constructor chaining, overridden methods |
| `instanceof` | runtime type check | safe downcasting |
| `for` / `while` | repeated execution | traversal, algorithm loops |
| `return` | exits method and optionally gives a value | recursive and search methods |

短答模板 / Short answer templates:

> `implements` connects a class to an interface contract.

> In a generic bound, `extends` means the type must be a subtype of the bound, even if the bound is an interface.

> `new` requires a concrete class; interfaces and abstract classes cannot be directly instantiated.

---

## 18. Exam-Style Reasoning Checklist / 考前推理清单

When seeing code:

1. Identify data structure: array, list, set, map, tree, graph?
2. Identify operation: search, insert, delete, traverse, sort, compare?
3. Ask whether order matters: insertion order, sorted order, priority order?
4. Ask whether duplicates are allowed.
5. For complexity, count the dominant loop or recursion.
6. For generics, check whether type safety is compile-time or runtime.
7. For inheritance, separate declared type from actual object type.
8. For equals/hashCode, check whether both are consistent.
9. For BST/AVL, draw the path from root; operations depend on height.
10. For graph algorithms, distinguish traversal, MST, and shortest path.
11. For `List`, check whether code uses index, value, or iterator.
12. For stack/queue, identify whether removal is LIFO, FIFO, or priority-based.
13. For `PriorityQueue`, check comparator direction and remember iteration is not sorted.
14. For interfaces, check the concrete class after `new`.
15. For keywords, explain the concrete effect in this code, not only the dictionary meaning.
16. For method-template questions, identify which parts are replaceable: type, variable, index, condition, comparator, return value, and traversal order.
17. For iterator questions, check whether the code only reads elements or structurally removes elements during traversal.

常见英文作答方式 / Common English answer shapes:

```text
This code uses a List because order and duplicates matter.
This code uses a Queue because elements must be processed in FIFO order.
This code uses a PriorityQueue because the next element is chosen by priority.
The time complexity is O(n) because the loop may inspect every element once.
The time complexity is O(log n) because each step halves the search space.
```

---

## 19. English Mini Glossary / 英文术语速查

- abstraction: 抽象
- encapsulation: 封装
- inheritance: 继承
- polymorphism: 多态
- dynamic binding: 动态绑定
- overloading: 重载
- overriding: 重写
- interface: 接口
- abstract class: 抽象类
- generic type: 泛型类型
- type erasure: 类型擦除
- wildcard: 通配符
- collection: 集合
- list: 列表，有序可重复集合
- stack: 栈，后进先出
- queue: 队列，先进先出
- deque: 双端队列
- priority queue: 优先队列
- iterator: 迭代器
- iterable: 可迭代对象
- comparator: 比较器
- comparable: 可比较的，自然顺序
- natural ordering: 自然顺序
- insertion order: 插入顺序
- sorted order: 排序顺序
- priority order: 优先级顺序
- random access: 随机访问/按下标直接访问
- amortized time: 摊还时间
- hash code: 哈希码
- collision: 哈希冲突
- load factor: 负载因子
- rehashing: 再哈希
- graph traversal: 图遍历
- spanning tree: 生成树
- minimum spanning tree: 最小生成树
- shortest path: 最短路径
- binary search tree: 二叉搜索树
- balance factor: 平衡因子
- rotation: 旋转
- time complexity: 时间复杂度
- space complexity: 空间复杂度
- divide and conquer: 分治
- dynamic programming: 动态规划
- backtracking: 回溯

---

## 20. 最后复习建议 / Final Revision Advice

中文：

1. 先背选择规则：什么时候用 `ArrayList`、`HashSet`、`TreeMap`、`PriorityQueue`、BST、AVL、HashMap。
2. 再练复杂度：看到循环、递归、树高、图边点数量，立刻写出主导项。
3. 最后练代码追踪：引用传递、`equals` vs `==`、动态绑定、迭代器删除、BST 删除、AVL 旋转。
4. 集合题先问三个问题：是否需要重复、是否需要顺序、取出顺序是 FIFO/LIFO/priority 还是 sorted。
5. 关键字题不要只翻译，要说效果：`new` 创建对象，`implements` 承诺接口方法，`extends` 建立继承或泛型上界。

English:

1. Memorize data-structure selection rules.
2. Practice deriving Big O from loops, recursion, height, vertices, and edges.
3. Trace code carefully for references, equality, dynamic binding, iterator behavior, BST deletion, and AVL rotations.
4. For collection questions, ask whether duplicates, order, and removal policy matter.
5. For keyword questions, explain the effect in code, not only the literal meaning.
