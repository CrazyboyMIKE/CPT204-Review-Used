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

---

## 7. Java Collections Framework / Java 集合框架

### 7.1 Big Picture / 总图

**Collection** 家族存单个元素：

- `List`: ordered, allows duplicates.
- `Set`: no duplicates.
- `Queue`: processing order, usually FIFO.
- `PriorityQueue`: priority order, not arrival order.

**Map** 家族存 key-value pairs，不属于 `Collection` 接口。

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

### 7.5 ArrayList vs LinkedList

| Feature / 特性 | ArrayList | LinkedList |
|---|---|---|
| Structure | resizable array | doubly linked nodes |
| Random access `get/set` | `O(1)` | `O(n)` |
| Insert/remove at beginning | slow, shifting | fast pointer changes |
| Memory overhead | lower | higher, node links |
| Best use | frequent reading/access | frequent head/tail operations |

**复习结论：** 不确定时优先 `ArrayList`；只有明确需要高频头部/尾部插删或队列/双端队列操作时考虑 `LinkedList`。

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

### 7.9 Queue and PriorityQueue / 队列与优先队列

`Queue`:

- FIFO: first in, first out.
- 安全方法：`offer`, `poll`, `peek`，空队列时返回 `false/null`。
- 风险方法：`add`, `remove`, `element`，失败时可能抛异常。

`PriorityQueue`:

- 按优先级出队，不按插入顺序。
- 默认使用 natural ordering，较小元素优先。
- 可传入 `Comparator` 改变优先级。
- 不允许 `null`。
- 元素必须 comparable 或提供 comparator。

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

### 9.6 Recurrence Patterns / 递推式模式

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

- `O(|V| + |E|)` with adjacency lists.

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

- `O(|V| + |E|)`。

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
| no duplicates, fastest lookup | `HashSet` | hashing average `O(1)` |
| no duplicates, insertion order | `LinkedHashSet` | linked order |
| no duplicates, sorted order | `TreeSet` | balanced tree ordering |
| FIFO processing | `Queue` with `LinkedList` | arrival order |
| priority processing | `PriorityQueue` | priority order |
| key-value lookup | `HashMap` | fast by key |
| sorted key-value lookup | `TreeMap` | key order and navigation |

### 17.2 Algorithm Complexity Summary

| Algorithm / Operation | Time |
|---|---|
| Linear search | `O(n)` |
| Binary search | `O(log n)` |
| Selection sort | `O(n^2)` |
| Insertion sort | worst `O(n^2)`, good for nearly sorted |
| Bubble sort | `O(n^2)`, optimized best `O(n)` |
| Merge sort | `O(n log n)` |
| Quick sort | average `O(n log n)`, worst `O(n^2)` |
| Heap add/remove | `O(log n)` |
| Heap sort | `O(n log n)` |
| DFS/BFS | `O(|V| + |E|)` |
| BST search/insert/delete | `O(h)`, worst `O(n)` |
| AVL search/insert/delete | `O(log n)` |
| Hash table search/insert/delete | average `O(1)`, worst depends on collisions |
| Brute-force string match | `O(nm)` |
| KMP | `O(n + m)` |
| Euclid GCD | `O(log n)` |
| Tower of Hanoi | `O(2^n)` |

### 17.3 Interface vs Abstract Class vs Concrete Class

| Type | Meaning | Can instantiate? | Contains implementation? |
|---|---|---|---|
| Interface | capability/contract | no | modern Java can have defaults, course focus is abstract methods/constants |
| Abstract class | partial implementation/base type | no | yes |
| Concrete class | usable implementation | yes | yes |

### 17.4 Comparable vs Comparator

| Item | Comparable | Comparator |
|---|---|---|
| Where defined | inside class | outside class |
| Method | `compareTo` | `compare` |
| Meaning | natural order | custom order |
| Number of orderings | usually one | many |
| Example | `String`, `Integer` | sort by length, by area, by amount |

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
- iterator: 迭代器
- comparator: 比较器
- natural ordering: 自然顺序
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

English:

1. Memorize data-structure selection rules.
2. Practice deriving Big O from loops, recursion, height, vertices, and edges.
3. Trace code carefully for references, equality, dynamic binding, iterator behavior, BST deletion, and AVL rotations.

