# CPT204 Exam-Focused Java Keywords Guide / CPT204 考试重点 Java 关键字中英双语讲解

这份文件**不是 Java 全关键字大全**。它只整理 CPT204 这门课中更可能考到、课件/TTL/算法代码里实际使用过或高度相关的 Java 关键字。

This file is **not a complete list of all Java keywords**. It focuses only on Java keywords that are likely to appear in CPT204 exams, lecture notes, TTL exercises, and algorithm code.

## 0. Scope / 范围说明

### Included / 本文件重点包含

这些是 CPT204 高概率相关关键字：

```text
public private protected
class interface extends implements abstract final
new this super instanceof static
boolean byte short int long float double char void
if else switch case default
for while do break continue return
try catch finally throw throws assert
package import enum
true false null
```

这些不是关键字，但考试和代码里经常一起出现，也会在解释里提到：

```text
String Object ArrayList LinkedList Stack Queue Deque ArrayDeque PriorityQueue
HashSet HashMap Comparable Comparator Iterable Iterator Collection List Set Map
```

### Not The Main Focus / 不作为本课程关键复习对象

下面这些是 Java 关键字或现代 Java 特殊词，但 CPT204 课件和 TTL 中基本不是核心考点，所以本文件不展开：

```text
const goto native strictfp transient volatile synchronized
var record yield sealed permits non-sealed
module requires exports opens open uses provides with to transitive
_
```

如果英文考试只是问 “Which one is a Java keyword?”，你知道它们存在即可；但 CPT204 更可能考的是 OOP、collections、generics、algorithm code 中真正用到的关键字。

If an exam only asks whether a word is a Java keyword, it may mention rare keywords. However, CPT204 is much more likely to test keywords used in object-oriented programming, collections, generics, and algorithm implementations.

## 1. Quick Map / 快速对照表

| Keyword | 中文含义 | English meaning | CPT204 常见场景 |
|---|---|---|---|
| `public` | 公开访问 | accessible from anywhere | `public class`, `public static void main` |
| `private` | 私有访问 | accessible only inside the same class | 封装字段 |
| `protected` | 受保护访问 | accessible in same package and subclasses | 继承题、父类字段 |
| `class` | 类 | defines a class | 所有 OOP 题 |
| `interface` | 接口 | defines a contract | `Comparable`, `Comparator` |
| `extends` | 继承 | inherits from a superclass or interface | `Student extends Person` |
| `implements` | 实现接口 | implements an interface | `implements Comparable<T>` |
| `abstract` | 抽象 | incomplete class or method | 抽象类、抽象方法 |
| `final` | 最终/不可再改 | prevents reassignment, overriding, or inheritance | 常量、不可重写方法 |
| `new` | 创建对象 | creates an object or array | `new ArrayList<>()` |
| `this` | 当前对象 | current object reference | 构造器赋值 |
| `super` | 父类部分 | superclass reference | 调父类构造器/方法 |
| `instanceof` | 类型判断 | runtime type test | 安全向下转型 |
| `static` | 类级别 | belongs to the class | `main`, 工具方法 |
| `boolean` | 布尔类型 | true/false primitive type | 条件判断 |
| `byte` | 8 位整数 | 8-bit integer | 认识即可 |
| `short` | 16 位整数 | 16-bit integer | 认识即可 |
| `int` | 整数 | 32-bit integer | 下标、循环、计数 |
| `long` | 长整数 | 64-bit integer | 大整数但非任意大 |
| `float` | 单精度浮点 | 32-bit floating point | 认识即可 |
| `double` | 双精度浮点 | 64-bit floating point | 距离、面积、计算 |
| `char` | 字符 | single Unicode character | grade, character |
| `void` | 无返回值 | no return value | `main`, mutator method |
| `if` | 如果 | conditional branch | 判断 |
| `else` | 否则 | alternative branch | 多条件判断 |
| `switch` | 多分支 | multi-way selection | 菜单、枚举 |
| `case` | 分支项 | switch branch label | switch 分支 |
| `default` | 默认项/接口默认方法 | fallback branch/default method | switch 或 interface |
| `for` | for 循环 | loop | 数组、集合遍历 |
| `while` | while 循环 | pre-test loop | 不确定次数循环 |
| `do` | do-while 循环 | post-test loop | 至少执行一次 |
| `break` | 跳出 | exits loop/switch | 终止循环或 switch |
| `continue` | 跳过本轮 | skips current iteration | 过滤元素 |
| `return` | 返回 | exits method | 返回结果 |
| `try` | 尝试执行 | risky code block | 异常处理 |
| `catch` | 捕获异常 | handles exception | 异常处理 |
| `finally` | 最终执行 | cleanup block | 清理资源 |
| `throw` | 抛出异常 | throws an exception object | 输入校验 |
| `throws` | 声明异常 | declares possible exceptions | 方法签名 |
| `assert` | 断言 | debugging assumption check | 低频，但可能识别 |
| `package` | 包 | declares package | 项目结构 |
| `import` | 导入 | imports class names | `java.util.*` |
| `enum` | 枚举 | fixed set of constants | `Priority.HIGH` |
| `true` | 真 | boolean literal | 条件值 |
| `false` | 假 | boolean literal | 条件值 |
| `null` | 空引用 | no object reference | 引用类型默认值 |

## 2. Access Modifiers / 访问控制关键字

### `public`

中文解释：`public` 表示公开访问。被 `public` 修饰的类、方法或字段可以从其他类访问。

English explanation: `public` is an access modifier that makes a class, method, or field accessible from anywhere.

Concrete effect / 具体作用：

- 编译器允许其他包中的代码访问该成员。
- `public class` 必须放在同名 `.java` 文件中。
- `public static void main(String[] args)` 中的 `public` 让 JVM 可以从类外调用入口方法。

Exam sentence:

> `public` allows a class, method, or field to be accessed from any other class.

Trap / 坑点：

```java
public class Student {
}
```

如果类名是 `Student`，文件名必须是 `Student.java`。

### `private`

中文解释：`private` 表示只能在当前类内部访问。它是封装 encapsulation 的核心。

English explanation: `private` restricts access to the same class only.

Concrete effect / 具体作用：

- 其他类不能直接访问 private field。
- 子类也不能直接访问父类 private field。
- 常用于隐藏对象内部状态，再通过 getter/setter 暴露受控访问。

Exam sentence:

> `private` supports encapsulation by hiding the internal state of an object.

Trap / 坑点：

```java
class Person {
    private String name;
}

class Student extends Person {
    void printName() {
        // System.out.println(name); // Compile-time error
    }
}
```

子类不能直接访问父类 `private` 字段。

### `protected`

中文解释：`protected` 表示同包可访问，或者不同包中的子类可以访问继承来的成员。

English explanation: `protected` allows access within the same package and from subclasses.

Concrete effect / 具体作用：

- 比 `private` 更开放。
- 比 `public` 更受限制。
- 常用于父类希望子类能访问的字段或方法。

Exam sentence:

> `protected` is accessible within the same package and by subclasses.

Trap / 坑点：

`protected` 不是 “only subclasses”。同一个 package 中的普通类也可以访问。

## 3. Class, Object, Inheritance, Interface / 类、对象、继承、接口

### `class`

中文解释：`class` 定义类。类是对象的蓝图 blueprint。

English explanation: `class` defines a class, which is a blueprint for creating objects.

Concrete effect / 具体作用：

- 创建一种新的引用类型 reference type。
- 定义对象的 fields 和 methods。
- 允许用 `new` 创建该类对象。

Exam sentence:

> A class encapsulates data fields and methods that describe the state and behavior of objects.

### `interface`

中文解释：`interface` 定义接口。接口描述一个类应该具备的能力或行为规范。

English explanation: An `interface` defines a contract that implementing classes must follow.

Concrete effect / 具体作用：

- 接口中的抽象方法必须由实现类实现。
- 接口字段默认是 `public static final`。
- 接口抽象方法默认是 `public abstract`。
- Java 类可以实现多个接口。

Exam sentence:

> An interface defines a contract of methods that implementing classes must provide.

Trap / 坑点：

```java
interface Config {
    int MAX_SIZE = 100; // public static final
}

// Config.MAX_SIZE = 200; // Compile-time error
```

接口字段默认是常量，不能改。

### `extends`

中文解释：`extends` 表示继承。子类继承父类的可访问成员。

English explanation: `extends` creates an inheritance relationship.

Concrete effect / 具体作用：

- 子类获得父类可访问的字段和方法。
- 子类可以 override 父类方法。
- 类只能 `extends` 一个父类。
- 接口可以 `extends` 一个或多个接口。

Exam sentence:

> `extends` allows a subclass to inherit accessible members from a superclass.

Trap / 坑点：

Java 不支持类的多继承：

```java
// class C extends A, B { } // Compile-time error
```

### `implements`

中文解释：`implements` 表示类实现接口。

English explanation: `implements` declares that a class provides implementations for an interface.

Concrete effect / 具体作用：

- 普通类必须实现接口中的所有抽象方法。
- 一个类可以 `implements` 多个接口。
- 常见于 `Comparable<T>`、`Comparator<T>`。

Exam sentence:

> A class uses `implements` to promise that it provides all required methods of an interface.

Example:

```java
class Student implements Comparable<Student> {
    private int mark;

    public int compareTo(Student other) {
        return Integer.compare(mark, other.mark);
    }
}
```

### `abstract`

中文解释：`abstract` 可修饰类或方法。抽象类不能直接创建对象；抽象方法没有方法体。

English explanation: `abstract` marks a class or method as incomplete.

Concrete effect / 具体作用：

- `abstract class` 不能被实例化。
- `abstract method` 必须由具体子类实现。
- 抽象类可以有字段、构造器、普通方法和抽象方法。

Exam sentence:

> An abstract class cannot be instantiated and may contain abstract methods that subclasses must implement.

Trap / 坑点：

抽象类可以有构造器：

```java
abstract class Shape {
    Shape() {
        System.out.println("Shape constructor");
    }
}
```

构造器是给子类构造时调用的，不代表可以 `new Shape()`。

### `final`

中文解释：`final` 根据位置不同有三种效果：变量不能重新赋值，方法不能被重写，类不能被继承。

English explanation: `final` prevents reassignment, overriding, or inheritance depending on where it is used.

Concrete effect / 具体作用：

- `final variable`：只能赋值一次。
- `final method`：子类不能 override。
- `final class`：不能被 extends。

Exam sentence:

> A `final` variable cannot be reassigned, a `final` method cannot be overridden, and a `final` class cannot be extended.

Important trap / 重要坑点：

```java
final ArrayList<String> names = new ArrayList<>();
names.add("Alice");           // OK
// names = new ArrayList<>();  // Compile-time error
```

`final` 引用不能换对象，但对象内部内容仍可能改变。

English trap sentence:

> A final reference cannot be reassigned, but the object it refers to may still be mutable.

### `new`

中文解释：`new` 创建对象或数组。

English explanation: `new` creates a new object or array and invokes a constructor.

Concrete effect / 具体作用：

- 在 heap 中创建对象。
- 调用构造器初始化对象。
- 返回对象引用 reference。

Example:

```java
Student student = new Student();
int[] values = new int[10];
```

Exam sentence:

> The `new` keyword allocates memory for an object or array and calls the appropriate constructor.

### `this`

中文解释：`this` 表示当前对象。

English explanation: `this` refers to the current object.

Concrete effect / 具体作用：

- 区分字段和参数。
- 调用当前对象的方法。
- `this(...)` 调用本类另一个构造器。

Example:

```java
class Book {
    private String title;

    Book(String title) {
        this.title = title;
    }
}
```

Trap / 坑点：

`this(...)` 必须是构造器第一行。

### `super`

中文解释：`super` 表示父类部分。

English explanation: `super` refers to the superclass part of the current object.

Concrete effect / 具体作用：

- `super(...)` 调用父类构造器。
- `super.method()` 调用父类方法。
- 常用于子类构造器和方法重写。

Trap / 坑点：

```java
class Person {
    Person(String name) {
    }
}

class Student extends Person {
    Student(String name) {
        super(name); // required, because Person has no no-arg constructor
    }
}
```

如果父类没有无参构造器，子类必须显式调用父类已有构造器。

### `instanceof`

中文解释：`instanceof` 判断对象运行时类型。

English explanation: `instanceof` tests whether an object is an instance of a class or interface.

Concrete effect / 具体作用：

- 返回 boolean。
- 常用于 downcasting 前检查类型。
- 左边是 `null` 时返回 `false`。

Example:

```java
Object value = "CPT204";

if (value instanceof String) {
    String text = (String) value;
    System.out.println(text.length());
}
```

Trap / 坑点：

```java
String text = null;
System.out.println(text instanceof String); // false
```

不会抛异常，结果是 `false`。

### `static`

中文解释：`static` 表示成员属于类，而不是属于某个对象。

English explanation: `static` means a field or method belongs to the class rather than to individual objects.

Concrete effect / 具体作用：

- `static field` 被所有对象共享。
- `static method` 可以通过类名调用。
- `static method` 没有 `this`，不能直接访问 instance field。
- `main` 是 static，因为 JVM 启动时还没有对象。

Exam sentence:

> A static member belongs to the class rather than to any particular object.

Trap 1 / 坑点 1：

```java
class Counter {
    static int count = 0;

    Counter() {
        count++;
    }
}

new Counter();
new Counter();
System.out.println(Counter.count); // 2
```

`count` 是所有对象共享的一份。

Trap 2 / 坑点 2：

```java
class Student {
    private String name;

    static void printName() {
        // System.out.println(name); // Compile-time error
    }
}
```

static 方法没有 `this`。

## 4. Primitive Types And `void` / 基本类型与 void

### `boolean`

中文解释：`boolean` 只有 `true` 和 `false`。

English explanation: `boolean` stores either `true` or `false`.

Concrete effect / 具体作用：

- 用作 `if`、`while`、`for` 条件。
- Java 中不能用 `1` 表示 true，不能用 `0` 表示 false。

Trap:

```java
// if (1) { } // Compile-time error in Java
```

### `byte`, `short`, `int`, `long`

中文解释：这些是整数类型，常用的是 `int`，更大范围用 `long`。

English explanation: These are integer primitive types. `int` is the most commonly used integer type, while `long` stores larger integers.

Concrete effect / 具体作用：

| Type | Size | Common use |
|---|---:|---|
| `byte` | 8-bit | binary data, recognition |
| `short` | 16-bit | less common |
| `int` | 32-bit | indexes, counters, loops |
| `long` | 64-bit | larger integer values |

Trap / 坑点：

```java
System.out.println(5 / 2);   // 2
System.out.println(5 / 2.0); // 2.5
```

两个整数相除，结果仍然是整数，小数部分被截断。

### `float`, `double`

中文解释：浮点数类型。课程里常用 `double` 做距离、面积、百分比计算。

English explanation: Floating-point primitive types. `double` is commonly used for mathematical calculations.

Concrete effect / 具体作用：

- `float` 是 32-bit。
- `double` 是 64-bit。
- 小数字面量默认是 `double`。

Trap / 坑点：

```java
// float x = 3.14;  // Compile-time error
float y = 3.14f;    // OK
```

### `char`

中文解释：`char` 表示单个 Unicode 字符，用单引号。

English explanation: `char` stores a single Unicode character.

Trap / 坑点：

```java
char grade = 'A';
String text = "A";

System.out.println(grade + 1);        // 66
System.out.println((char)(grade + 1)); // B
```

`char` 参与算术运算时会提升为 `int`。

### `void`

中文解释：`void` 表示方法没有返回值。

English explanation: `void` means a method returns no value.

Concrete effect / 具体作用：

- 方法可以执行操作，但不能返回具体值。
- `void` 方法中可以写 `return;` 提前结束。

Trap:

```java
void print() {
    // return 1; // Compile-time error
    return;
}
```

## 5. Control Flow / 流程控制关键字

### `if` and `else`

中文解释：`if` 根据 boolean 条件选择是否执行代码；`else` 是条件不成立时执行的分支。

English explanation: `if` executes a block when a condition is true, and `else` provides an alternative branch.

Concrete effect / 具体作用：

- 改变程序执行路径。
- 条件必须是 boolean。

Exam sentence:

> The condition inside an `if` statement must evaluate to a boolean value.

Trap / 坑点：

`else` 会匹配最近的没有配对的 `if`，这叫 dangling else。

### `switch`, `case`, `default`

中文解释：`switch` 用于多分支选择；`case` 是具体分支；`default` 是没有匹配时的兜底分支。

English explanation: `switch` selects one branch among multiple `case` labels, and `default` is used when no case matches.

Concrete effect / 具体作用：

- 让代码根据一个表达式选择不同分支。
- 常用于菜单、枚举、状态处理。
- 传统 switch 需要注意 `break`。

Trap 1: fall-through

```java
int day = 1;

switch (day) {
    case 1:
        System.out.println("Monday");
    case 2:
        System.out.println("Tuesday");
        break;
    default:
        System.out.println("Other");
}
```

Output:

```text
Monday
Tuesday
```

因为 `case 1` 没有 `break`。

Trap 2: `default` 不一定必须放最后

```java
int value = 2;

switch (value) {
    default:
        System.out.println("Default");
        break;
    case 2:
        System.out.println("Two");
        break;
}
```

Output:

```text
Two
```

### `for`

中文解释：`for` 循环常用于已知次数的循环和数组下标遍历。

English explanation: `for` repeats a block of code, usually with initialization, condition, and update.

Concrete effect / 具体作用：

- 重复执行代码。
- 普通 `for` 可以控制 index。
- enhanced for 可以遍历数组或集合。

Examples:

```java
for (int i = 0; i < values.length; i++) {
    System.out.println(values[i]);
}

for (int value : values) {
    System.out.println(value);
}
```

Trap / 坑点：

enhanced for 不适合在遍历时直接删除集合元素。

### `while` and `do`

中文解释：`while` 先判断再执行；`do-while` 先执行再判断。

English explanation: A `while` loop checks the condition before execution, while a `do-while` loop checks the condition after execution.

Concrete effect / 具体作用：

- `while` 可能执行 0 次。
- `do-while` 至少执行 1 次。

Trap:

```java
int x = 10;

while (x < 5) {
    System.out.println("while");
}

do {
    System.out.println("do-while");
} while (x < 5);
```

Output:

```text
do-while
```

### `break` and `continue`

中文解释：`break` 跳出循环或 switch；`continue` 跳过当前循环剩余部分，进入下一轮。

English explanation: `break` exits the nearest loop or switch, while `continue` skips the current iteration.

Trap:

```java
for (int i = 1; i <= 5; i++) {
    if (i == 3) {
        continue;
    }
    if (i == 5) {
        break;
    }
    System.out.print(i + " ");
}
```

Output:

```text
1 2 4
```

### `return`

中文解释：`return` 结束方法，并可把值返回给调用者。

English explanation: `return` exits a method and optionally returns a value.

Concrete effect / 具体作用：

- `void` 方法可以 `return;`。
- 非 void 方法必须返回兼容类型的值。
- `return` 后面的同一代码块普通语句不可达。

Exam sentence:

> A non-void method must return a value compatible with its declared return type.

## 6. Exception Handling / 异常处理关键字

这部分在 CPT204 中不是最核心，但 Java 代码阅读和英文考试可能涉及。

### `try`, `catch`, `finally`

中文解释：`try` 包住可能出错的代码；`catch` 处理异常；`finally` 通常做清理。

English explanation: `try` encloses risky code, `catch` handles exceptions, and `finally` usually performs cleanup.

Concrete effect / 具体作用：

- 程序发生异常时，不一定直接崩溃，可以进入 catch 处理。
- finally 通常无论是否异常都会执行。

Trap / 坑点：

```java
static int test() {
    try {
        return 1;
    } finally {
        return 2;
    }
}
```

Return value:

```text
2
```

`finally` 中的 return 会覆盖 try 中的 return，不推荐这样写。

### `throw` and `throws`

中文解释：`throw` 在方法体里真正抛出异常对象；`throws` 在方法声明上说明可能抛出异常。

English explanation: `throw` actually throws an exception object, while `throws` declares that a method may throw exceptions.

Concrete effect / 具体作用：

- `throw` 改变运行时控制流。
- `throws` 改变方法签名，把异常处理责任交给调用者。

Example:

```java
static void setAge(int age) throws IllegalArgumentException {
    if (age < 0) {
        throw new IllegalArgumentException("Age cannot be negative");
    }
}
```

Exam sentence:

> `throw` is used inside a method body, while `throws` is used in a method declaration.

### `assert`

中文解释：`assert` 用于调试时检查程序内部假设。默认不开启。

English explanation: `assert` checks an internal assumption during debugging and is disabled by default.

Concrete effect / 具体作用：

- 条件为 false 且断言开启时，抛出 `AssertionError`。
- 不应该用来检查用户输入。

Trap:

```java
int size = -1;
assert size >= 0 : "Size must not be negative";
```

需要运行：

```bash
java -ea ClassName
```

断言才会生效。

## 7. Package, Import, Enum / 包、导入、枚举

### `package`

中文解释：`package` 声明当前类属于哪个包。

English explanation: `package` declares the package of a class.

Concrete effect / 具体作用：

- 组织类文件。
- 影响类的完整限定名 fully qualified name。
- 必须放在第一条非注释语句。

Trap:

```java
package study.notes;

import java.util.ArrayList;

public class Example {
}
```

顺序必须是 `package` -> `import` -> class。

### `import`

中文解释：`import` 导入类名，避免写完整包名。

English explanation: `import` allows a class to use another class without writing its fully qualified name.

Concrete effect / 具体作用：

- 帮助编译器解析类名。
- 不会复制代码。
- 不会让程序运行更慢。

Trap:

```java
import java.util.*;

// ConcurrentHashMap map; // Compile-time error unless java.util.concurrent is imported
```

`import java.util.*` 不会递归导入子包。

### `enum`

中文解释：`enum` 定义固定常量集合。

English explanation: `enum` defines a type with a fixed set of constants.

Concrete effect / 具体作用：

- 编译器限制取值只能是枚举中列出的常量。
- 比字符串更安全。
- 常用于 priority、status、direction。

Example:

```java
enum Priority {
    HIGH, MEDIUM, LOW
}

Priority priority = Priority.HIGH;
```

Trap:

```java
// Priority p = new Priority(); // Compile-time error
```

enum 不能用 `new` 创建。

## 8. Literals / 字面量：`true`, `false`, `null`

### `true` and `false`

中文解释：`true` 和 `false` 是 boolean 字面量。

English explanation: `true` and `false` are boolean literals.

Concrete effect / 具体作用：

- 可以赋给 boolean。
- 可以作为条件表达式结果。
- 不能作为变量名。

Example:

```java
boolean completed = false;

if (!completed) {
    completed = true;
}
```

### `null`

中文解释：`null` 表示引用变量没有指向任何对象。

English explanation: `null` represents no object reference.

Concrete effect / 具体作用：

- 只能赋给引用类型。
- 对 `null` 调实例方法会抛 `NullPointerException`。
- 对象数组默认元素是 `null`。

Trap:

```java
String name = null;
// System.out.println(name.length()); // NullPointerException

// int x = null; // Compile-time error
```

Exam sentence:

> `null` can be assigned only to reference variables, not primitive variables.

## 9. Generics-Related Keyword Usage / 泛型中相关关键字用法

CPT204 的泛型题中，最容易考的是：泛型类、泛型方法、`Comparable<T>`、`Comparator<T>`、通配符 `? extends T` / `? super T`、raw type、diamond operator，以及增强 `for` 背后的 `Iterable` / `Iterator`。

Important note / 重要说明：

泛型语法里的 `<T>`、`<?>`、`<E>`、`<>` **不是 Java keyword**，但它们是 CPT204 里非常重要的 Java syntax。相关真正的关键字主要是：

```text
class interface extends implements for new return
```

English:

> Generic type parameters such as `<T>` and wildcards such as `?` are not keywords, but they are important Java syntax used with classes, interfaces, and methods.

### 9.1 Generic Class / 泛型类

中文解释：泛型类是在类名后写类型参数，让同一个类可以保存不同类型的数据，同时保持类型安全。

English explanation: A generic class uses type parameters so that the same class can work with different types while preserving type safety.

Example:

```java
class Box<T> {
    private T value;

    public Box(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}

Box<String> box = new Box<>("CPT204");
String text = box.getValue();
```

Concrete effect / 具体作用：

- 编译器知道 `Box<String>` 里面放的是 `String`。
- 取出数据时不需要强制类型转换。
- 如果放入错误类型，编译期就会报错。

Exam sentence:

> A generic class provides compile-time type safety by using type parameters.

Trap / 坑点：

```java
Box<String> box = new Box<>("Java");
// Box<int> wrong = new Box<>(10); // Compile-time error
Box<Integer> correct = new Box<>(10);
```

泛型类型参数必须是引用类型 reference type，不能是 primitive type。要用 `Integer`，不能用 `int`。

### 9.2 Generic Method / 泛型方法

中文解释：泛型方法是在返回类型前声明类型参数，例如 `<E>`。它可以独立于类的泛型参数。

English explanation: A generic method declares its own type parameter before the return type.

Example:

```java
public static <E> void printArray(E[] values) {
    for (E value : values) {
        System.out.println(value);
    }
}
```

Concrete effect / 具体作用：

- 同一个方法可以处理 `String[]`、`Integer[]`、`Double[]` 等不同数组。
- 编译器根据调用时传入的参数推断 `E`。

Exam sentence:

> A generic method declares type parameters before the return type and can be used with different argument types.

Trap / 坑点：

```java
public static <E> E first(E[] values) {
    return values[0];
}
```

`<E>` 必须写在返回类型 `E` 前面。如果只写 `public static E first(...)`，编译器不知道 `E` 是什么。

### 9.3 Bounded Type Parameter: `<E extends Comparable<E>>`

中文解释：`extends` 在泛型中可以表示上界 upper bound，意思是 E 必须是某种可以比较的类型。

English explanation: In generics, `extends` can define an upper bound, meaning the type must be a subtype of the bound.

Example:

```java
public static <E extends Comparable<E>> E max(E first, E second) {
    if (first.compareTo(second) >= 0) {
        return first;
    }
    return second;
}
```

Concrete effect / 具体作用：

- 编译器允许在 `E` 上调用 `compareTo`。
- 没有 `extends Comparable<E>`，`first.compareTo(second)` 会编译失败。

Exam sentence:

> `<E extends Comparable<E>>` means that E must implement Comparable, so objects of type E can be compared.

Trap / 坑点：

这里的 `extends` 不一定表示“继承一个类”，也可以表示“实现接口上界”。`Comparable<E>` 是接口，但泛型边界仍然写 `extends`，不是 `implements`。

English trap sentence:

> In a generic bound, Java uses `extends` for both class bounds and interface bounds.

### 9.4 `Comparable<T>` vs `Comparator<T>`

中文解释：`Comparable<T>` 让对象自己知道怎么比较；`Comparator<T>` 把比较规则放在另一个对象里。

English explanation: `Comparable<T>` defines the natural ordering inside a class, while `Comparator<T>` defines an external ordering rule.

Comparable example:

```java
class Student implements Comparable<Student> {
    private int mark;

    public int compareTo(Student other) {
        return Integer.compare(mark, other.mark);
    }
}
```

Comparator example:

```java
Comparator<Student> byName = new Comparator<Student>() {
    public int compare(Student a, Student b) {
        return a.getName().compareTo(b.getName());
    }
};
```

Concrete effect / 具体作用：

- `Comparable`：一个类的默认排序方式 natural ordering。
- `Comparator`：可以为同一个类写多个排序规则。

Exam sentence:

> `Comparable` defines a class's natural ordering, while `Comparator` defines an external comparison strategy.

Trap / 坑点：

`compareTo` 和 `compare` 的返回值含义：

```text
negative number: first object is smaller
zero: equal in ordering
positive number: first object is greater
```

不要误以为必须返回 `-1`、`0`、`1`，只要负数、零、正数即可。

### 9.5 Raw Type Trap / 原始类型陷阱

中文解释：raw type 是不写泛型参数的集合类型，例如 `ArrayList list`。它会绕过泛型类型检查，容易产生运行时错误。

English explanation: A raw type is a generic type used without type arguments, which disables generic type checking.

Bad example:

```java
ArrayList list = new ArrayList();
list.add("Java");
list.add(100);

String text = (String) list.get(1); // Runtime ClassCastException
```

Better example:

```java
ArrayList<String> list = new ArrayList<>();
list.add("Java");
// list.add(100); // Compile-time error
```

Exam sentence:

> Raw types should be avoided because they remove compile-time type safety.

Trap / 坑点：

泛型的价值是把错误提前到 compile time。raw type 会把错误推迟到 runtime。

### 9.6 Diamond Operator `<>` / 菱形操作符

中文解释：`<>` 叫 diamond operator。它不是关键字，而是让编译器根据左边类型推断右边对象的泛型类型。

English explanation: The diamond operator `<>` allows the compiler to infer generic type arguments.

Example:

```java
ArrayList<String> names = new ArrayList<>();
HashMap<Integer, String> map = new HashMap<>();
```

Concrete effect / 具体作用：

- 减少重复代码。
- 右边不用再写 `new ArrayList<String>()`。
- 编译器仍然知道集合元素类型。

Exam sentence:

> The diamond operator lets the compiler infer the generic type arguments from the context.

Trap / 坑点：

`<>` 不是“任意类型都可以放进去”。类型已经由左边决定：

```java
ArrayList<String> names = new ArrayList<>();
// names.add(123); // Compile-time error
```

### 9.7 `? extends T`

中文解释：表示某种 T 或 T 子类型。适合读数据。

English explanation: `? extends T` means an unknown subtype of T and is mainly used for reading values as T.

Example:

```java
static double sum(List<? extends Number> numbers) {
    double total = 0;
    for (Number number : numbers) {
        total += number.doubleValue();
    }
    // numbers.add(10); // Compile-time error
    return total;
}
```

Exam sentence:

> `? extends T` is mainly used when the structure produces values to be read as T.

### 9.8 `? super T`

中文解释：表示某种 T 或 T 父类型。适合写入 T。

English explanation: `? super T` means an unknown supertype of T and is mainly used for writing T values.

Example:

```java
static void addIntegers(List<? super Integer> values) {
    values.add(10);
    values.add(20);
    Object first = values.get(0);
}
```

Exam sentence:

> `? super T` is mainly used when the structure consumes values of type T.

Memory phrase:

```text
PECS = Producer Extends, Consumer Super
```

### 9.9 Generic Invariance / 泛型不变性

中文解释：即使 `Integer` 是 `Number` 的子类，`ArrayList<Integer>` 也不是 `ArrayList<Number>` 的子类。

English explanation: Java generics are invariant. `List<Integer>` is not a subtype of `List<Number>`, even though `Integer` is a subtype of `Number`.

Trap example:

```java
ArrayList<Integer> integers = new ArrayList<>();
// ArrayList<Number> numbers = integers; // Compile-time error
```

Why / 为什么：

如果允许这样赋值，就可能发生：

```java
// numbers.add(3.14);
```

这样 `integers` 里就会混入 `Double`，破坏 `ArrayList<Integer>` 的类型安全。

Exam sentence:

> Java generics are invariant, so `ArrayList<Integer>` is not a subtype of `ArrayList<Number>`.

### 9.10 Type Erasure / 类型擦除

中文解释：Java 泛型主要在编译期提供类型检查，编译后很多泛型信息会被擦除。

English explanation: Java generics are mostly implemented by type erasure, meaning much generic type information is removed at compile time.

Concrete effect / 具体作用：

- 泛型主要提供 compile-time type safety。
- 运行时通常不知道 `ArrayList<String>` 和 `ArrayList<Integer>` 的具体泛型参数。
- 不能直接 `new T()`。

Trap examples:

```java
class Factory<T> {
    T create() {
        // return new T(); // Compile-time error
        return null;
    }
}
```

```java
ArrayList<String> strings = new ArrayList<>();
ArrayList<Integer> integers = new ArrayList<>();

System.out.println(strings.getClass() == integers.getClass()); // true
```

Exam sentence:

> Due to type erasure, generic type parameters are mainly checked at compile time and are not fully available at runtime.

## 10. Lists, Stacks, Queues, And Priority Queues / 线性表、栈、队列与优先队列

截图中的 `List`、`Stack`、`Queue`、`PriorityQueue` 都是 CPT204 集合与数据结构题里的高频概念。它们**不是 Java keywords**，但它们会和 `class`、`interface`、`implements`、`new`、`for`、`while`、`return`、`import`、泛型 `<E>`、`Comparable`、`Comparator` 一起出现。

Important note:

> `List`, `Stack`, `Queue`, and `PriorityQueue` are not Java keywords. They are collection interfaces/classes or abstract data types used to organize and process data.

### 10.1 Big Picture / 总体区别

中文解释：

- `List<E>`：线性表，按 index 保存元素，允许重复。
- `Stack<E>`：栈，后进先出 LIFO。
- `Queue<E>`：队列，先进先出 FIFO。
- `PriorityQueue<E>`：优先队列，不按插入顺序出队，而按 priority/order 出队。

English explanation:

- `List<E>` is an ordered collection with index-based access and duplicate elements.
- `Stack<E>` follows Last-In First-Out.
- `Queue<E>` follows First-In First-Out.
- `PriorityQueue<E>` removes elements according to priority, not insertion order.

Memory map / 记忆图：

```text
List           -> ordered by index
Stack          -> last in, first out
Queue          -> first in, first out
PriorityQueue  -> highest priority or smallest element first, depending on ordering
```

Exam sentence:

> A list stores elements in an ordered sequence, a stack removes the most recently added element first, a queue removes the earliest added element first, and a priority queue removes the element with the highest priority according to its ordering rule.

### 10.2 Interface vs Implementation / 接口和实现类

中文解释：`List`、`Queue`、`Deque` 通常是接口 interface；`ArrayList`、`LinkedList`、`ArrayDeque`、`PriorityQueue` 是常见实现类 class。

English explanation: `List`, `Queue`, and `Deque` are interfaces, while `ArrayList`, `LinkedList`, `ArrayDeque`, and `PriorityQueue` are concrete implementations.

Correct examples:

```java
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class InterfaceImplementationExample {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        Queue<String> tasks = new ArrayDeque<>();

        names.add("Alice");
        tasks.offer("Revise Java");

        System.out.println(names.get(0));
        System.out.println(tasks.poll());
    }
}
```

Trap / 坑点：

```java
// List<String> names = new List<>();   // Compile-time error
// Queue<String> tasks = new Queue<>(); // Compile-time error
```

接口不能直接 `new`。要 `new` 一个实现类。

Exam sentence:

> An interface such as `List` or `Queue` specifies operations, while a class such as `ArrayList` or `ArrayDeque` provides the actual implementation.

### 10.3 List, ArrayList, And LinkedList / 线性表、数组表与链表

中文解释：`List<E>` 表示有顺序的 collection。它保留插入顺序，可以通过 index 访问，也允许重复元素。

English explanation: A `List<E>` is an ordered collection that preserves element order, supports index-based access, and allows duplicates.

Complete example:

```java
import java.util.ArrayList;
import java.util.List;

public class ListExample {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();

        names.add("Alice");     // 添加到末尾
        names.add("Bob");       // 添加到末尾
        names.add("Alice");     // List 允许重复元素

        String firstName = names.get(0); // 按下标读取，index 从 0 开始
        names.set(1, "Ben");             // 替换 index 1 的元素

        boolean hasAlice = names.contains("Alice"); // 判断是否包含某个元素
        int size = names.size();                    // 返回元素数量

        System.out.println(firstName);
        System.out.println(hasAlice);
        System.out.println(size);

        names.remove("Alice"); // 删除第一个等于 "Alice" 的元素
        names.remove(0);       // 删除 index 0 的元素

        System.out.println(names);
    }
}
```

Concrete effect / 具体作用：

- `add(value)`：把元素加到 list 末尾。
- `add(index, value)`：把元素插入指定位置，后面的元素右移。
- `get(index)`：读取指定位置元素。
- `set(index, value)`：替换指定位置元素。
- `remove(index)`：删除指定位置元素。
- `remove(object)`：删除第一个相等的对象。
- `size()`：返回元素个数。
- `isEmpty()`：判断是否没有元素。

ArrayList vs LinkedList / 常考区别：

```text
ArrayList:
fast index access with get(i)
slow middle insertion/removal because elements may need shifting

LinkedList:
slow index access with get(i)
can be efficient for adding/removing at ends
also implements Deque, so it can act as a queue or deque
```

Exam sentence:

> `ArrayList` provides efficient random access by index, while `LinkedList` is usually better for frequent insertions or removals near the ends.

Important trap: `remove(int)` vs `remove(Object)` / 重要坑点：删除下标 vs 删除对象

```java
import java.util.ArrayList;
import java.util.List;

public class ListRemoveTrapExample {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);

        numbers.remove(1);                  // 删除 index 1 的元素，也就是 2
        numbers.remove(Integer.valueOf(1)); // 删除值为 1 的元素

        System.out.println(numbers);        // 输出 [3]
    }
}
```

为什么容易错：

- `numbers.remove(1)` 中的 `1` 是 primitive `int`，优先匹配 `remove(int index)`。
- 如果想删除元素值 `1`，要写 `Integer.valueOf(1)`。

English trap sentence:

> In a `List<Integer>`, `remove(1)` removes the element at index 1, not necessarily the value 1.

### 10.4 Stack / 栈

中文解释：栈 stack 是 LIFO 结构，最后放进去的元素最先出来。常见操作是 `push`、`pop`、`peek`。

English explanation: A stack follows LIFO, meaning the last element pushed is the first element popped.

Java `Stack` class example:

```java
import java.util.Stack;

public class StackClassExample {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        stack.push(10);              // 入栈
        stack.push(20);              // 20 在栈顶

        int topValue = stack.peek(); // 查看栈顶，但不删除
        int removed = stack.pop();   // 删除并返回栈顶元素

        System.out.println(topValue); // 20
        System.out.println(removed);  // 20
        System.out.println(stack);    // [10]
    }
}
```

Modern preferred Java style / 现代 Java 更推荐：

```java
import java.util.ArrayDeque;
import java.util.Deque;

public class DequeAsStackExample {
    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<>();

        stack.push(10);           // 等价于 addFirst(10)
        stack.push(20);           // 20 成为栈顶

        int topValue = stack.peek(); // 查看但不删除
        int removed = stack.pop();   // 删除并返回栈顶

        System.out.println(topValue);
        System.out.println(removed);
    }
}
```

Concrete effect / 具体作用：

- `push(x)`：把元素压入栈顶。
- `pop()`：删除并返回栈顶元素。
- `peek()`：只查看栈顶元素，不删除。
- `isEmpty()`：检查栈是否为空。

Trap / 坑点：

```java
Stack<Integer> stack = new Stack<>();
// stack.pop();  // EmptyStackException
// stack.peek(); // EmptyStackException
```

空栈上调用 `pop()` 或 `peek()` 会出错。考试写代码时通常先判断：

```java
if (!stack.isEmpty()) {
    int value = stack.pop();
    System.out.println(value);
}
```

Exam sentence:

> A stack is a LIFO structure; `push` inserts an element, `pop` removes the top element, and `peek` reads the top element without removing it.

### 10.5 Queue / 队列

中文解释：队列 queue 是 FIFO 结构，最早进入队列的元素最先出来。常见操作是 `offer`、`poll`、`peek`。

English explanation: A queue follows FIFO, meaning the first element inserted is the first element removed.

Complete example:

```java
import java.util.ArrayDeque;
import java.util.Queue;

public class QueueExample {
    public static void main(String[] args) {
        Queue<String> queue = new ArrayDeque<>();

        queue.offer("first");       // 入队，放到队尾
        queue.offer("second");      // 入队，放到队尾

        String head = queue.peek(); // 查看队头，不删除
        String removed = queue.poll(); // 删除并返回队头

        System.out.println(head);    // first
        System.out.println(removed); // first
        System.out.println(queue);   // [second]
    }
}
```

Method pairs / 方法对比：

```text
offer(x)  -> add x to tail, returns false if it cannot insert
add(x)    -> add x to tail, may throw exception if it cannot insert

poll()    -> remove head, returns null if empty
remove()  -> remove head, throws exception if empty

peek()    -> read head, returns null if empty
element() -> read head, throws exception if empty
```

中文记忆：

- `offer` / `poll` / `peek` 比较温和，失败时通常返回 `false` 或 `null`。
- `add` / `remove` / `element` 更严格，失败时可能抛异常。
- 普通算法题里更常用 `offer`、`poll`、`peek`。

Exam sentence:

> A queue is a FIFO structure; `offer` inserts at the tail, `poll` removes the head, and `peek` reads the head without removing it.

Trap / 坑点：

```java
Queue<String> queue = new ArrayDeque<>();

String value = queue.poll(); // null, because the queue is empty
System.out.println(value);
```

如果 `poll()` 返回 `null`，可能表示队列为空，所以不要把 `null` 当作普通数据放进队列。`ArrayDeque` 和 `PriorityQueue` 不允许 `null` 元素。

### 10.6 Deque And ArrayDeque / 双端队列与 ArrayDeque

中文解释：`Deque<E>` 表示 double-ended queue，双端都可以添加和删除。它可以当 stack 用，也可以当 queue 用。

English explanation: A `Deque<E>` is a double-ended queue that supports insertion and removal at both ends. It can be used as either a stack or a queue.

Complete example:

```java
import java.util.ArrayDeque;
import java.util.Deque;

public class DequeExample {
    public static void main(String[] args) {
        Deque<String> deque = new ArrayDeque<>();

        deque.addFirst("front"); // 加到队头
        deque.addLast("back");   // 加到队尾

        String first = deque.peekFirst(); // 查看队头
        String last = deque.peekLast();   // 查看队尾

        System.out.println(first);
        System.out.println(last);

        deque.removeFirst(); // 删除队头
        deque.removeLast();  // 删除队尾
    }
}
```

Common methods / 常用方法：

```text
addFirst(x), removeFirst(), peekFirst()
addLast(x),  removeLast(),  peekLast()
push(x), pop(), peek()
offer(x), poll(), peek()
```

Exam sentence:

> `Deque` is more flexible than a simple queue because it allows operations at both the front and the back.

Trap / 坑点：

`ArrayDeque` 通常比旧的 `Stack` 类更适合实现栈，也比 `LinkedList` 更常用来实现普通队列。但如果课程或题目明确使用 `Stack`，你仍然要会 `Stack` 的 `push`、`pop`、`peek`。

### 10.7 PriorityQueue / 优先队列

中文解释：`PriorityQueue<E>` 不是普通 FIFO 队列。它每次 `poll()` 出来的不是最早加入的元素，而是优先级最高的元素。Java 默认情况下，对数字来说通常是最小值先出。

English explanation: A `PriorityQueue<E>` is not a FIFO queue. It removes elements according to their priority. By default, Java's priority queue gives the smallest element first for natural ordering.

Integer example:

```java
import java.util.PriorityQueue;

public class PriorityQueueIntegerExample {
    public static void main(String[] args) {
        PriorityQueue<Integer> numbers = new PriorityQueue<>();

        numbers.offer(30);
        numbers.offer(10);
        numbers.offer(20);

        System.out.println(numbers.poll()); // 10
        System.out.println(numbers.poll()); // 20
        System.out.println(numbers.poll()); // 30
    }
}
```

Max-priority example with `Comparator`:

```java
import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueMaxExample {
    public static void main(String[] args) {
        PriorityQueue<Integer> numbers = new PriorityQueue<>(Comparator.reverseOrder());

        numbers.offer(30);
        numbers.offer(10);
        numbers.offer(20);

        System.out.println(numbers.poll()); // 30
        System.out.println(numbers.poll()); // 20
        System.out.println(numbers.poll()); // 10
    }
}
```

Object example with custom comparator:

```java
import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueTaskExample {
    public static void main(String[] args) {
        PriorityQueue<Task> tasks = new PriorityQueue<>(new TaskPriorityComparator());

        tasks.offer(new Task("Read notes", 2));
        tasks.offer(new Task("Finish TTL", 5));
        tasks.offer(new Task("Review exam traps", 4));

        while (!tasks.isEmpty()) {
            Task task = tasks.poll();
            System.out.println(task.getName() + ":" + task.getPriority());
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

class TaskPriorityComparator implements Comparator<Task> {
    @Override
    public int compare(Task first, Task second) {
        return Integer.compare(second.getPriority(), first.getPriority());
    }
}
```

这段代码中，priority 数字越大越先出队，所以 `compare` 写成 `second - first` 的方向。更安全的写法是 `Integer.compare(...)`，避免整数溢出。

Concrete effect / 具体作用：

- `offer(x)`：加入优先队列。
- `peek()`：查看当前最高优先级元素，但不删除。
- `poll()`：删除并返回当前最高优先级元素。
- 没有 comparator 时，元素需要能 natural ordering，例如 `Integer`、`String` 或实现 `Comparable` 的对象。
- 有 comparator 时，按照 comparator 的规则决定优先级。

Exam sentence:

> A priority queue removes elements according to priority rather than insertion order; in Java, natural ordering makes the smallest element come out first unless a comparator changes the order.

Important trap: iteration is not sorted / 重要坑点：遍历不等于排序

```java
PriorityQueue<Integer> numbers = new PriorityQueue<>();
numbers.offer(30);
numbers.offer(10);
numbers.offer(20);

for (int number : numbers) {
    System.out.println(number); // Not guaranteed to be 10, 20, 30
}
```

如果想按优先级顺序取出元素，要反复 `poll()`，不要依赖 enhanced `for` 的遍历顺序。

English trap sentence:

> Iterating over a `PriorityQueue` does not guarantee sorted order; repeated `poll()` operations are needed to retrieve elements in priority order.

### 10.8 Complexity Summary / 复杂度总结

这些复杂度是考试常见简化版，具体实现细节可能更复杂，但足够应对 CPT204 的常见选择题、解释题和代码题。

```text
ArrayList get/set by index: O(1)
ArrayList add at end: usually O(1) amortized
ArrayList insert/remove in middle: O(n)

LinkedList get by index: O(n)
LinkedList add/remove at known end: O(1)
LinkedList search: O(n)

Stack push/pop/peek: usually O(1)
Queue offer/poll/peek with ArrayDeque: usually O(1)
PriorityQueue offer/poll: O(log n)
PriorityQueue peek: O(1)
```

Exam sentence:

> The choice of collection affects time complexity; for example, `ArrayList` is efficient for index access, while `PriorityQueue` is efficient for repeatedly removing the next highest-priority element.

### 10.9 Exam Traps For These Structures / 这些结构的考试坑点

1. `List` is ordered, but not automatically sorted.  
   `List` 保留顺序，但不会自动排序。

2. `List` allows duplicates.  
   `List` 允许重复元素，和 `Set` 不一样。

3. `Queue` is an interface, so `new Queue<>()` is invalid.  
   `Queue` 是接口，不能直接实例化。

4. `PriorityQueue` is not FIFO.  
   `PriorityQueue` 不是普通先进先出队列。

5. `peek()` reads but does not remove; `poll()` or `pop()` removes.  
   `peek()` 只看不删，`poll()` / `pop()` 会删除。

6. `Stack.pop()` on an empty stack throws an exception.  
   空栈 `pop()` 会出错，先检查 `isEmpty()`。

7. `Queue.poll()` on an empty queue returns `null`.  
   空队列 `poll()` 通常返回 `null`，所以队列中不要存 `null`。

8. `PriorityQueue` needs comparable elements or a comparator.  
   优先队列里的对象要么能自然比较，要么提供 comparator。

9. `ArrayList<Integer>.remove(1)` removes by index.  
   `ArrayList<Integer>.remove(1)` 删除 index 1，不一定是值 1。

10. Big-O depends on the implementation, not only the interface name.  
    复杂度取决于实现类，不只取决于接口名。

Short English answers to memorize / 可背英文短句：

```text
A List is an ordered collection that allows duplicates and supports index-based access.
A Stack is a LIFO structure.
A Queue is a FIFO structure.
A PriorityQueue removes elements according to priority, not insertion order.
ArrayDeque can be used as an efficient stack or queue implementation.
```

## 11. Iterator And Iterable / 迭代器与 Iterable

重要说明：`Iterator`、`Iterable`、`hasNext()`、`next()`、`remove()` **都不是 Java keywords**。它们是接口或方法名。但 CPT204 的集合、增强 `for`、遍历题中经常出现，所以必须会。

Important note:

> `Iterator` and `Iterable` are not Java keywords. They are interfaces used by the Java Collections Framework.

### 11.1 Enhanced `for` and `Iterable`

中文解释：增强 `for` 可以遍历数组，也可以遍历实现了 `Iterable` 的对象。

English explanation: The enhanced `for` loop can iterate over arrays and objects that implement `Iterable`.

Example:

```java
ArrayList<String> names = new ArrayList<>();
names.add("Alice");
names.add("Bob");

for (String name : names) {
    System.out.println(name);
}
```

Concrete effect / 具体作用：

- 语法更简洁。
- 不需要手动管理 index。
- 对集合来说，底层通常使用 iterator。

Exam sentence:

> The enhanced `for` loop works with arrays and objects that implement `Iterable`.

Trap / 坑点：

增强 `for` 适合读取遍历，不适合一边遍历一边删除集合元素。

```java
for (String name : names) {
    // names.remove(name); // May cause ConcurrentModificationException
}
```

### 11.2 Manual Iterator / 手动使用 Iterator

中文解释：`Iterator<E>` 是一个遍历集合的对象。常用方法是 `hasNext()` 和 `next()`。

English explanation: `Iterator<E>` is an object used to traverse a collection. Its common methods are `hasNext()` and `next()`.

Example:

```java
Iterator<String> iterator = names.iterator();

while (iterator.hasNext()) {
    String name = iterator.next();
    System.out.println(name);
}
```

Concrete effect / 具体作用：

- `hasNext()` 判断是否还有元素。
- `next()` 返回下一个元素并移动迭代位置。
- 手动 iterator 可以在遍历时安全删除当前元素。

Exam sentence:

> An iterator provides a standard way to traverse a collection without exposing its internal structure.

Trap / 坑点：

不要在没有检查 `hasNext()` 时盲目调用 `next()`：

```java
Iterator<String> iterator = names.iterator();
// String value = iterator.next(); // If empty, NoSuchElementException
```

### 11.3 Safe Removal With Iterator / 用 Iterator 安全删除

中文解释：遍历集合时，如果需要删除当前元素，应该使用 iterator 的 `remove()`，而不是集合自己的 `remove()`。

English explanation: When removing elements during iteration, use the iterator's `remove()` method instead of the collection's `remove()` method.

Example:

```java
Iterator<String> iterator = names.iterator();

while (iterator.hasNext()) {
    String name = iterator.next();
    if (name.startsWith("A")) {
        iterator.remove();
    }
}
```

Concrete effect / 具体作用：

- `iterator.remove()` 删除的是最近一次 `next()` 返回的元素。
- 可以避免很多 fail-fast 集合中的 `ConcurrentModificationException`。

Exam sentence:

> To remove elements safely while iterating, use `Iterator.remove()` after calling `next()`.

Trap / 坑点：

```java
Iterator<String> iterator = names.iterator();
// iterator.remove(); // IllegalStateException, because next() has not been called
```

必须先调用 `next()`，再调用 `remove()`。

### 11.4 Implementing Iterable / 自定义类实现 Iterable

中文解释：如果一个自定义类实现 `Iterable<E>`，它就可以被增强 `for` 遍历。

English explanation: If a custom class implements `Iterable<E>`, it can be used in an enhanced `for` loop.

Example:

```java
class NameBag implements Iterable<String> {
    private ArrayList<String> names = new ArrayList<>();

    public void add(String name) {
        names.add(name);
    }

    public Iterator<String> iterator() {
        return names.iterator();
    }
}

NameBag bag = new NameBag();
bag.add("Alice");
bag.add("Bob");

for (String name : bag) {
    System.out.println(name);
}
```

Concrete effect / 具体作用：

- `implements Iterable<String>` 表示这个类承诺提供 `iterator()`。
- 增强 `for` 会调用 `iterator()` 来遍历元素。

Exam sentence:

> A class that implements `Iterable` must provide an `iterator()` method, allowing it to be used in an enhanced `for` loop.

Trap / 坑点：

`Iterator` 和 `Iterable` 不一样：

```text
Iterable: something that can be iterated over
Iterator: the object that performs the iteration
```

中文：

```text
Iterable：可被遍历的对象
Iterator：真正执行遍历的迭代器对象
```

### 11.5 Iterator vs Index Loop / 迭代器 vs 下标循环

中文解释：数组和 `ArrayList` 适合下标循环；`LinkedList` 用下标访问可能很慢，更适合 iterator 或 enhanced for。

English explanation: Arrays and `ArrayList` are efficient for index-based loops, while `LinkedList` is usually better traversed with an iterator or enhanced `for`.

Example:

```java
for (int i = 0; i < list.size(); i++) {
    System.out.println(list.get(i));
}
```

对于 `ArrayList` 通常没问题；但对 `LinkedList`，`get(i)` 每次可能都要从头走，整体可能变成 `O(n^2)`。

Exam sentence:

> Index-based access is efficient for arrays and `ArrayList`, but iterator-based traversal is often better for `LinkedList`.

### 11.6 Iterator Exam Traps / 迭代器考试坑点

1. `Iterator` is not a keyword.  
   `Iterator` 不是关键字，是接口。

2. Enhanced `for` uses `Iterable` for collections.  
   集合能被增强 `for` 遍历，是因为它实现了 `Iterable`。

3. Calling `next()` without an available element may throw `NoSuchElementException`.  
   没有下一个元素还调用 `next()` 会出错。

4. Calling `remove()` before `next()` may throw `IllegalStateException`.  
   没有先 `next()` 就 `remove()` 会出错。

5. Removing directly from the collection while using enhanced `for` may cause `ConcurrentModificationException`.  
   增强 `for` 中直接用集合 remove 可能导致并发修改异常。

6. `Iterable` produces an `Iterator`; `Iterator` performs the traversal.  
   `Iterable` 产生迭代器；`Iterator` 真正遍历。

## 12. Complete Java Example Bank / 完整 Java 示例库

这一节把前面零散讲到的关键字组合成完整 Java 文件形式。每个代码块都可以复制到对应文件名中运行。这样你能看到关键字在真实 Java 程序里的位置，而不是只看到孤立片段。

This section rewrites the important examples as complete Java files. Each code block can be copied into a `.java` file with the same public class name.

### 12.1 Access Modifier Complete Example

File name:

```text
AccessModifierExample.java
```

```java
public class AccessModifierExample {
    public static void main(String[] args) {
        Student student = new Student("Alice", 88);
        System.out.println(student.getName());
        System.out.println(student.getMark());

        GraduateStudent graduate = new GraduateStudent("Bob", 92, "Graphs");
        graduate.printResearchTopic();
    }
}

class Student {
    private String name;
    protected int mark;

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

class GraduateStudent extends Student {
    private String researchTopic;

    public GraduateStudent(String name, int mark, String researchTopic) {
        super(name, mark);
        this.researchTopic = researchTopic;
    }

    public void printResearchTopic() {
        System.out.println("Mark: " + mark);
        System.out.println("Topic: " + researchTopic);
        // System.out.println(name); // Compile-time error: name is private in Student.
    }
}
```

这个例子同时展示：

- `public`：`main` 和 getter 可以从外部访问。
- `private`：`name` 只能在 `Student` 内部直接访问。
- `protected`：`mark` 可以在子类 `GraduateStudent` 中直接访问。
- `extends` / `super`：子类调用父类构造器。

English exam sentence:

> `private` hides data inside the class, while `protected` allows subclass access and same-package access.

### 12.2 OOP Keywords Complete Example

File name:

```text
OopKeywordExample.java
```

```java
public class OopKeywordExample {
    public static void main(String[] args) {
        Shape circle = new Circle("blue", 3.0);
        System.out.println(circle.describe());
        System.out.println(circle.area());

        if (circle instanceof Circle) {
            Circle realCircle = (Circle) circle;
            System.out.println(realCircle.getRadius());
        }

        System.out.println(MathTool.square(5));
    }
}

interface Printable {
    String describe();
}

abstract class Shape implements Printable {
    private String color;

    public Shape(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public abstract double area();

    public final String category() {
        return "shape";
    }
}

class Circle extends Shape {
    private double radius;

    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public String describe() {
        return "Circle color: " + getColor();
    }
}

final class MathTool {
    private MathTool() {
    }

    public static int square(int value) {
        return value * value;
    }
}
```

这个例子同时展示：

- `interface`：`Printable` 定义能力。
- `abstract`：`Shape` 不能直接 `new`，但能保存共同状态。
- `implements`：`Shape` 实现接口。
- `extends`：`Circle` 继承 `Shape`。
- `super`：调用父类构造器。
- `this`：区分当前对象字段和参数。
- `final`：`category()` 不能被重写，`MathTool` 不能被继承。
- `static`：`MathTool.square(5)` 不需要创建对象。
- `instanceof`：向下转型前判断真实类型。

English exam sentence:

> `extends` creates an inheritance relationship, while `implements` connects a class to an interface contract.

### 12.3 Primitive Types And Control Flow Complete Example

File name:

```text
PrimitiveControlFlowExample.java
```

```java
public class PrimitiveControlFlowExample {
    public static void main(String[] args) {
        int score = 86;
        char grade = calculateGrade(score);
        boolean passed = score >= 40;

        System.out.println("Grade: " + grade);
        System.out.println("Passed: " + passed);
        printCountdown(3);
        printMenuResult(2);
        printLoopTrap();
    }

    public static char calculateGrade(int score) {
        if (score >= 70) {
            return 'A';
        } else if (score >= 60) {
            return 'B';
        } else if (score >= 50) {
            return 'C';
        }
        return 'F';
    }

    public static void printCountdown(int start) {
        while (start > 0) {
            System.out.println(start);
            start--;
        }
    }

    public static void printMenuResult(int choice) {
        switch (choice) {
            case 1:
                System.out.println("Add");
                break;
            case 2:
                System.out.println("View");
                break;
            default:
                System.out.println("Unknown");
                break;
        }
    }

    public static void printLoopTrap() {
        for (int i = 1; i <= 5; i++) {
            if (i == 3) {
                continue;
            }
            if (i == 5) {
                break;
            }
            System.out.print(i + " ");
        }
        System.out.println();

        double result = 5 / 2;
        double fixed = 5 / 2.0;
        System.out.println(result);
        System.out.println(fixed);
    }
}
```

这个例子同时展示：

- `int`、`char`、`boolean`、`double`、`void`
- `if` / `else`
- `switch` / `case` / `default` / `break`
- `for` / `while`
- `continue` / `return`
- 整数除法 `5 / 2` 的坑

English exam sentence:

> `break` terminates the loop or switch, while `continue` skips only the current loop iteration.

### 12.4 Exception Handling Complete Example

File name:

```text
ExceptionKeywordExample.java
```

```java
public class ExceptionKeywordExample {
    public static void main(String[] args) {
        try {
            setAge(-1);
        } catch (IllegalArgumentException ex) {
            System.out.println("Caught: " + ex.getMessage());
        } finally {
            System.out.println("Cleanup finished");
        }

        int size = 3;
        assert size >= 0 : "Size must not be negative";
    }

    public static void setAge(int age) throws IllegalArgumentException {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        System.out.println("Age: " + age);
    }
}
```

运行断言需要：

```bash
java -ea ExceptionKeywordExample
```

这个例子同时展示：

- `try`：包住可能出错的代码。
- `catch`：捕获异常。
- `finally`：通常执行清理。
- `throw`：真正抛出异常对象。
- `throws`：在方法签名中声明可能抛出异常。
- `assert`：调试时检查内部假设，默认关闭。

English exam sentence:

> `throw` actually throws an exception object, while `throws` declares that a method may throw exceptions.

### 12.5 Enum And Import Complete Example

File name:

```text
EnumCollectionExample.java
```

```java
import java.util.ArrayList;

public class EnumCollectionExample {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Revise keywords", Priority.HIGH));
        tasks.add(new Task("Read notes", Priority.MEDIUM));

        for (Task task : tasks) {
            printTask(task);
        }
    }

    public static void printTask(Task task) {
        switch (task.getPriority()) {
            case HIGH:
                System.out.println("Urgent: " + task.getTitle());
                break;
            case MEDIUM:
                System.out.println("Normal: " + task.getTitle());
                break;
            case LOW:
                System.out.println("Later: " + task.getTitle());
                break;
            default:
                System.out.println("Unknown priority");
                break;
        }
    }
}

enum Priority {
    HIGH, MEDIUM, LOW
}

class Task {
    private String title;
    private Priority priority;

    public Task(String title, Priority priority) {
        this.title = title;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public Priority getPriority() {
        return priority;
    }
}
```

这个例子同时展示：

- `import`：导入 `ArrayList`。
- `enum`：定义固定优先级。
- enhanced `for`：遍历集合。
- `switch` enum 时，`case HIGH` 不写 `Priority.HIGH`。

English exam sentence:

> An enum defines a fixed set of constants and is safer than using strings for fixed categories.

### 12.6 Generics Complete Example

File name:

```text
GenericsExample.java
```

```java
import java.util.ArrayList;
import java.util.Comparator;

public class GenericsExample {
    public static void main(String[] args) {
        Box<String> textBox = new Box<>("Java");
        System.out.println(textBox.getValue());

        Integer[] values = {3, 8, 2};
        printArray(values);
        System.out.println(max(10, 20));

        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("Alice", 88));
        students.add(new Student("Bob", 75));

        students.sort(Comparator.naturalOrder());
        System.out.println(students);

        students.sort(new StudentNameComparator());
        System.out.println(students);

        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(10);
        integers.add(20);
        System.out.println(sum(integers));

        ArrayList<Number> numbers = new ArrayList<>();
        addIntegers(numbers);
        System.out.println(numbers);
    }

    public static <E> void printArray(E[] values) {
        for (E value : values) {
            System.out.println(value);
        }
    }

    public static <E extends Comparable<E>> E max(E first, E second) {
        if (first.compareTo(second) >= 0) {
            return first;
        }
        return second;
    }

    public static double sum(ArrayList<? extends Number> values) {
        double total = 0;
        for (Number value : values) {
            total += value.doubleValue();
        }
        return total;
    }

    public static void addIntegers(ArrayList<? super Integer> values) {
        values.add(1);
        values.add(2);
    }
}

class Box<T> {
    private T value;

    public Box(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}

class Student implements Comparable<Student> {
    private String name;
    private int mark;

    public Student(String name, int mark) {
        this.name = name;
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Student other) {
        return Integer.compare(mark, other.mark);
    }

    @Override
    public String toString() {
        return name + ":" + mark;
    }
}

class StudentNameComparator implements Comparator<Student> {
    @Override
    public int compare(Student first, Student second) {
        return first.getName().compareTo(second.getName());
    }
}
```

这个例子同时展示：

- 泛型类 `Box<T>`
- 泛型方法 `<E>`
- 上界 `<E extends Comparable<E>>`
- `Comparable<Student>`
- `Comparator<Student>`
- `? extends Number`
- `? super Integer`
- diamond operator `new ArrayList<>()`

English exam sentence:

> Generics provide compile-time type safety and reduce the need for explicit casts.

### 12.7 Iterator And Iterable Complete Example

File name:

```text
IteratorIterableExample.java
```

```java
import java.util.ArrayList;
import java.util.Iterator;

public class IteratorIterableExample {
    public static void main(String[] args) {
        NameBag bag = new NameBag();
        bag.add("Alice");
        bag.add("Bob");
        bag.add("Amy");

        for (String name : bag) {
            System.out.println(name);
        }

        Iterator<String> iterator = bag.iterator();
        while (iterator.hasNext()) {
            String name = iterator.next();
            if (name.startsWith("A")) {
                iterator.remove();
            }
        }

        System.out.println("After removal:");
        for (String name : bag) {
            System.out.println(name);
        }
    }
}

class NameBag implements Iterable<String> {
    private ArrayList<String> names = new ArrayList<>();

    public void add(String name) {
        names.add(name);
    }

    @Override
    public Iterator<String> iterator() {
        return names.iterator();
    }
}
```

这个例子同时展示：

- `Iterable<String>`：对象可以被 enhanced `for` 遍历。
- `Iterator<String>`：真正执行遍历。
- `hasNext()`：判断是否还有元素。
- `next()`：取出下一个元素。
- `iterator.remove()`：安全删除最近一次 `next()` 返回的元素。

English exam sentence:

> `Iterable` represents something that can be iterated over, while `Iterator` is the object that performs the traversal.

### 12.8 List Stack Queue PriorityQueue Complete Example

File name:

```text
CollectionAdtExample.java
```

```java
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class CollectionAdtExample {
    public static void main(String[] args) {
        demonstrateList();
        demonstrateStack();
        demonstrateQueue();
        demonstratePriorityQueue();
    }

    public static void demonstrateList() {
        List<String> names = new ArrayList<>();

        names.add("Alice");     // 添加到末尾
        names.add("Bob");       // 添加到末尾
        names.add("Alice");     // List 允许重复元素

        System.out.println("List first: " + names.get(0));
        System.out.println("List size: " + names.size());

        names.set(1, "Ben");    // 替换 index 1 的元素
        names.remove("Alice");  // 删除第一个匹配的对象

        System.out.println("List after changes: " + names);
    }

    public static void demonstrateStack() {
        Deque<Integer> stack = new ArrayDeque<>();

        stack.push(10);          // 入栈
        stack.push(20);          // 20 成为栈顶

        System.out.println("Stack peek: " + stack.peek()); // 查看但不删除
        System.out.println("Stack pop: " + stack.pop());   // 删除并返回栈顶
        System.out.println("Stack after pop: " + stack);
    }

    public static void demonstrateQueue() {
        Queue<String> queue = new ArrayDeque<>();

        queue.offer("first");    // 入队，放到队尾
        queue.offer("second");   // 入队，放到队尾

        System.out.println("Queue peek: " + queue.peek()); // 查看队头
        System.out.println("Queue poll: " + queue.poll()); // 删除并返回队头
        System.out.println("Queue after poll: " + queue);
    }

    public static void demonstratePriorityQueue() {
        PriorityQueue<Task> tasks = new PriorityQueue<>(new TaskPriorityComparator());

        tasks.offer(new Task("Read notes", 2));
        tasks.offer(new Task("Finish TTL", 5));
        tasks.offer(new Task("Review exam traps", 4));

        while (!tasks.isEmpty()) {
            Task task = tasks.poll(); // 每次取出当前最高优先级任务
            System.out.println("Priority task: " + task.getName());
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

class TaskPriorityComparator implements Comparator<Task> {
    @Override
    public int compare(Task first, Task second) {
        return Integer.compare(second.getPriority(), first.getPriority());
    }
}
```

这个例子同时展示：

- `List<String>`：接口类型保存 `ArrayList` 对象。
- `ArrayList`：支持 index-based access。
- `Deque<Integer>`：用 `ArrayDeque` 实现 stack。
- `Queue<String>`：用 `ArrayDeque` 实现 FIFO queue。
- `PriorityQueue<Task>`：用 comparator 决定出队优先级。
- `Comparator<Task>`：外部比较规则。
- `while (!tasks.isEmpty())`：持续取出优先级最高的元素。

English exam sentence:

> `ArrayDeque` can implement both stack and queue behavior, while `PriorityQueue` removes elements according to a comparator or natural ordering.

### 12.9 Null And Reference Complete Example

File name:

```text
NullReferenceExample.java
```

```java
public class NullReferenceExample {
    public static void main(String[] args) {
        String text = null;

        System.out.println(text instanceof String);

        if (text != null) {
            System.out.println(text.length());
        } else {
            System.out.println("Text is null");
        }

        String[] names = new String[2];
        System.out.println(names[0]);

        int[] values = new int[2];
        System.out.println(values[0]);
    }
}
```

这个例子同时展示：

- `null instanceof String` 是 `false`。
- 引用类型数组默认元素是 `null`。
- `int[]` 默认元素是 `0`。
- 使用对象前先判断 `null` 可以避免 `NullPointerException`。

English exam sentence:

> `null` means that a reference variable does not refer to any object.

## 13. Exam Trap Bank / CPT204 高频坑点库

### 13.1 `static` is not constant

`static` 表示属于类，不代表不能修改。常量通常写成：

```java
public static final int MAX_SIZE = 100;
```

English:

> `static` does not mean constant; constants are usually declared with `static final`.

### 13.2 `final` reference is not deep immutable

```java
final ArrayList<String> list = new ArrayList<>();
list.add("A"); // OK
```

English:

> `final` prevents reassignment of the reference, not mutation of the object.

### 13.3 `private` is not directly visible to subclasses

```java
class A {
    private int value;
}

class B extends A {
    void test() {
        // value = 10; // Compile-time error
    }
}
```

English:

> Private members are not directly accessible in subclasses.

### 13.4 `throw` vs `throws`

```java
throw new IllegalArgumentException();
```

is an action.

```java
void read() throws IOException
```

is a declaration.

English:

> `throw` performs the throwing action, while `throws` declares possible exceptions.

### 13.5 `break` vs `continue`

```java
for (int i = 1; i <= 3; i++) {
    if (i == 2) {
        continue;
    }
    System.out.print(i);
}
```

Output:

```text
13
```

`continue` 只跳过当前一轮，不结束整个循环。

### 13.6 `switch` fall-through

没有 `break` 会继续往下执行。

English:

> Missing `break` in a traditional switch statement may cause fall-through.

### 13.7 integer division

```java
double result = 5 / 2;
System.out.println(result); // 2.0
```

因为 `5 / 2` 先做整数除法，结果是 2，再转成 double。

正确写法：

```java
double result = 5 / 2.0;
```

### 13.8 `char` plus int

```java
char c = 'A';
System.out.println(c + 1); // 66
```

`char` 算术运算时提升为 int。

### 13.9 `String` is not a keyword

`String` 很常用，但它不是 Java keyword，它是 `java.lang.String` 类。

English:

> `String` is a class, not a Java keyword.

### 13.10 Collection names are not keywords

`ArrayList`、`LinkedList`、`Queue`、`Deque`、`ArrayDeque`、`Stack`、`PriorityQueue`、`HashMap` 都不是关键字，它们是类或接口。

English:

> Collection names such as `ArrayList` and `HashMap` are classes or interfaces, not keywords.

### 13.11 `List` order is not the same as sorted order

`List` 是有顺序 ordered，意思是它保留元素的排列位置和 index，不代表它会自动按大小排序。

```java
List<Integer> numbers = new ArrayList<>();
numbers.add(30);
numbers.add(10);
numbers.add(20);

System.out.println(numbers); // [30, 10, 20]
```

English:

> A `List` is ordered, but it is not automatically sorted.

### 13.12 `remove(1)` in `List<Integer>` may remove by index

```java
List<Integer> numbers = new ArrayList<>();
numbers.add(1);
numbers.add(2);
numbers.add(3);

numbers.remove(1); // removes index 1, so value 2 is removed
```

如果想删除值为 1 的对象：

```java
numbers.remove(Integer.valueOf(1));
```

English:

> In `List<Integer>`, `remove(1)` calls `remove(int index)`, so it removes the element at index 1.

### 13.13 `peek`, `pop`, and `poll` are different

```java
Deque<Integer> stack = new ArrayDeque<>();
stack.push(10);

System.out.println(stack.peek()); // reads 10, stack still has 10
System.out.println(stack.pop());  // removes 10
```

```java
Queue<Integer> queue = new ArrayDeque<>();
queue.offer(10);

System.out.println(queue.peek()); // reads 10, queue still has 10
System.out.println(queue.poll()); // removes 10
```

English:

> `peek` reads without removing, while `pop` and `poll` remove an element.

### 13.14 `PriorityQueue` is not FIFO

```java
PriorityQueue<Integer> numbers = new PriorityQueue<>();
numbers.offer(30);
numbers.offer(10);
numbers.offer(20);

System.out.println(numbers.poll()); // 10, not 30
```

`PriorityQueue` 默认按照 natural ordering，`Integer` 会让最小值先出来。

English:

> A `PriorityQueue` removes elements by priority rather than insertion order.

### 13.15 Iterating a `PriorityQueue` is not sorted

```java
PriorityQueue<Integer> numbers = new PriorityQueue<>();
numbers.offer(30);
numbers.offer(10);
numbers.offer(20);

for (int number : numbers) {
    System.out.println(number); // order is not guaranteed to be sorted
}
```

如果题目要求按优先级顺序处理，要用：

```java
while (!numbers.isEmpty()) {
    System.out.println(numbers.poll());
}
```

English:

> Repeated `poll()` operations retrieve priority-queue elements in priority order; iteration does not guarantee sorted order.

## 14. Exam Answer Templates / 英文考试答题模板

### Access modifier

> `X` is an access modifier. It controls where a class, method, or field can be accessed.

### Class

> A class is a blueprint for creating objects. It defines fields and methods.

### Object creation

> `new` creates an object or array and calls the appropriate constructor.

### Inheritance

> `extends` creates an inheritance relationship between a subclass and a superclass.

### Interface

> An interface defines a contract that implementing classes must follow.

### Static

> A static member belongs to the class rather than to any particular object.

### Final

> `final` prevents reassignment for variables, overriding for methods, and inheritance for classes.

### Exception handling

> `try` contains code that may throw an exception, `catch` handles the exception, and `finally` usually performs cleanup.

### Throw and throws

> `throw` actually throws an exception object, while `throws` declares that a method may throw exceptions.

### List

> A `List` is an ordered collection that allows duplicate elements and supports index-based access.

### ArrayList

> `ArrayList` is a resizable-array implementation of `List`; it provides efficient index access but may be slow for insertions or removals in the middle.

### LinkedList

> `LinkedList` stores elements as linked nodes; index access is usually slower than `ArrayList`, but it can support efficient operations at the ends.

### Stack

> A stack is a LIFO structure, where the last element pushed is the first element popped.

### Queue

> A queue is a FIFO structure, where the first element inserted is the first element removed.

### Deque

> A `Deque` is a double-ended queue that supports insertion and removal at both the front and the back.

### PriorityQueue

> A `PriorityQueue` removes elements according to priority rather than insertion order.

### Comparator with PriorityQueue

> A comparator can define the priority order for objects stored in a `PriorityQueue`.

## 15. Final Priority List / 最后优先背诵清单

### Must know / 必须会

```text
public private protected
class interface extends implements abstract final
new this super instanceof static
if else for while switch case default break continue return
int double boolean char void
import package
true false null
```

### Should know / 应该会

```text
try catch finally throw throws
enum
byte short long float
assert
```

### Know as related concepts / 作为相关概念认识

```text
String Object ArrayList LinkedList HashMap HashSet
Queue Deque ArrayDeque Stack PriorityQueue
Comparable Comparator Collection List Set Map
Iterable Iterator
```

这些不是关键字，但 CPT204 里非常常见。

### Collection method names to remember / 集合方法名速记

```text
List: add get set remove contains size isEmpty
Stack: push pop peek isEmpty
Queue: offer poll peek add remove element
Deque: addFirst addLast removeFirst removeLast peekFirst peekLast
PriorityQueue: offer poll peek
Ordering: compare compareTo
```

这些也不是关键字，但它们经常出现在集合、栈、队列、优先队列和排序代码里。

### Generic syntax to remember / 泛型语法记忆

```text
<T>
<E>
<>
?
? extends T
? super T
<E extends Comparable<E>>
```

这些也不是关键字，但 CPT204 的泛型、排序、集合题里很容易考。
