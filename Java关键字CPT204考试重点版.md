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
String Object ArrayList LinkedList Stack Queue PriorityQueue HashSet HashMap
Comparable Comparator Iterable Iterator Collection List Set Map
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

CPT204 的泛型题中，`extends` 和 `super` 会以 wildcard bound 的形式出现。

### `? extends T`

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

### `? super T`

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

## 10. Exam Trap Bank / CPT204 高频坑点库

### 10.1 `static` is not constant

`static` 表示属于类，不代表不能修改。常量通常写成：

```java
public static final int MAX_SIZE = 100;
```

English:

> `static` does not mean constant; constants are usually declared with `static final`.

### 10.2 `final` reference is not deep immutable

```java
final ArrayList<String> list = new ArrayList<>();
list.add("A"); // OK
```

English:

> `final` prevents reassignment of the reference, not mutation of the object.

### 10.3 `private` is not directly visible to subclasses

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

### 10.4 `throw` vs `throws`

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

### 10.5 `break` vs `continue`

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

### 10.6 `switch` fall-through

没有 `break` 会继续往下执行。

English:

> Missing `break` in a traditional switch statement may cause fall-through.

### 10.7 integer division

```java
double result = 5 / 2;
System.out.println(result); // 2.0
```

因为 `5 / 2` 先做整数除法，结果是 2，再转成 double。

正确写法：

```java
double result = 5 / 2.0;
```

### 10.8 `char` plus int

```java
char c = 'A';
System.out.println(c + 1); // 66
```

`char` 算术运算时提升为 int。

### 10.9 `String` is not a keyword

`String` 很常用，但它不是 Java keyword，它是 `java.lang.String` 类。

English:

> `String` is a class, not a Java keyword.

### 10.10 Collection names are not keywords

`ArrayList`、`HashMap`、`Queue`、`Stack`、`PriorityQueue` 都不是关键字，它们是类或接口。

English:

> Collection names such as `ArrayList` and `HashMap` are classes or interfaces, not keywords.

## 11. Exam Answer Templates / 英文考试答题模板

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

## 12. Final Priority List / 最后优先背诵清单

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
String Object ArrayList HashMap HashSet Queue Stack PriorityQueue
Comparable Comparator Collection List Set Map
```

这些不是关键字，但 CPT204 里非常常见。
