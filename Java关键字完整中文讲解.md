# Java Keywords Bilingual Guide / Java 关键字中英双语完整讲解

这份讲义用于英文考试复习：每个 Java 关键字都配有中文理解、英文解释、考试可用表达和常见易错点。你可以先用中文理解概念，再记住对应英文说法。

This guide is designed for English exams. Each Java keyword includes a Chinese explanation, an English explanation, exam-ready English phrases, and common mistakes.

## 1. How To Use This Guide / 如何使用

建议复习方式：

1. 先看中文解释，确认自己理解关键字作用。
2. 再读英文解释，熟悉考试中可能出现的术语。
3. 背诵 `Exam sentence`，它可以直接用于简答题。
4. 注意 `Common mistake`，这类内容很容易出现在选择题或代码判断题中。

Recommended study method:

1. Understand the keyword in Chinese first.
2. Read the English explanation and remember the technical terms.
3. Memorize the `Exam sentence`.
4. Review the `Common mistake`, because it is often tested in multiple-choice or code-reading questions.

## 2. Keyword Quick Map / 关键字快速对照表

| Keyword | 中文含义 | English meaning |
|---|---|---|
| `abstract` | 抽象的 | Defines an abstract class or abstract method |
| `assert` | 断言 | Checks an assumption during debugging |
| `boolean` | 布尔类型 | A primitive type with `true` or `false` |
| `break` | 跳出 | Exits a loop or a `switch` statement |
| `byte` | 8 位整数 | An 8-bit integer primitive type |
| `case` | 分支情况 | A branch inside a `switch` statement |
| `catch` | 捕获异常 | Handles an exception thrown in `try` |
| `char` | 字符类型 | A primitive type for a single Unicode character |
| `class` | 类 | Defines a class |
| `const` | 保留但不用 | Reserved but not used in Java |
| `continue` | 跳过本轮 | Skips the rest of the current loop iteration |
| `default` | 默认分支/接口默认方法 | Default branch in `switch`, or default method in interface |
| `do` | do-while 循环 | Starts a loop that runs at least once |
| `double` | 双精度浮点数 | A 64-bit floating-point primitive type |
| `else` | 否则 | Alternative branch of an `if` statement |
| `enum` | 枚举 | Defines a type with fixed constants |
| `extends` | 继承 | Indicates inheritance |
| `final` | 最终/不可变引用 | Prevents reassignment, overriding, or inheritance |
| `finally` | 最终执行块 | Executes cleanup code after `try-catch` |
| `float` | 单精度浮点数 | A 32-bit floating-point primitive type |
| `for` | for 循环 | A loop with initialization, condition, and update |
| `goto` | 保留但不用 | Reserved but not used in Java |
| `if` | 如果 | Conditional statement |
| `implements` | 实现接口 | Indicates that a class implements an interface |
| `import` | 导入 | Imports classes or packages |
| `instanceof` | 类型判断 | Tests the runtime type of an object |
| `int` | 整数类型 | A 32-bit integer primitive type |
| `interface` | 接口 | Defines a contract of methods |
| `long` | 长整数 | A 64-bit integer primitive type |
| `native` | 本地方法 | Indicates a method implemented in another language |
| `new` | 创建对象 | Creates an object or array |
| `package` | 包 | Declares the package of a class |
| `private` | 私有 | Accessible only within the same class |
| `protected` | 受保护 | Accessible in the same package and subclasses |
| `public` | 公开 | Accessible from anywhere |
| `return` | 返回 | Returns from a method |
| `short` | 短整数 | A 16-bit integer primitive type |
| `static` | 静态/类级别 | Belongs to the class rather than an object |
| `strictfp` | 严格浮点 | Enforces strict floating-point calculations |
| `super` | 父类引用 | Refers to the superclass part of an object |
| `switch` | 多分支选择 | Selects one branch among many cases |
| `synchronized` | 同步 | Controls thread access to a critical section |
| `this` | 当前对象 | Refers to the current object |
| `throw` | 抛出异常 | Explicitly throws an exception |
| `throws` | 声明异常 | Declares exceptions a method may throw |
| `transient` | 不序列化 | Excludes a field from serialization |
| `try` | 尝试执行 | Contains code that may throw exceptions |
| `void` | 无返回值 | Indicates that a method returns no value |
| `volatile` | 可见性 | Ensures visibility of variable changes across threads |
| `while` | while 循环 | Repeats while a condition is true |

特殊但重要：

| Word | 中文含义 | English meaning |
|---|---|---|
| `true` | 真 | Boolean literal for truth |
| `false` | 假 | Boolean literal for falsehood |
| `null` | 空引用 | Null reference literal |
| `var` | 局部变量类型推断 | Local variable type inference |
| `record` | 数据记录类 | Compact immutable data carrier |
| `yield` | switch 表达式返回值 | Produces a value in a switch expression |
| `sealed` | 限制继承 | Restricts which classes may extend or implement a type |
| `permits` | 允许的子类列表 | Lists permitted subclasses of a sealed type |
| `non-sealed` | 重新开放继承 | Reopens inheritance in a sealed hierarchy |
| `module` | 模块 | Declares a Java module |
| `requires` | 模块依赖 | Declares a module dependency |
| `exports` | 导出包 | Exports a package from a module |
| `opens` | 开放反射访问 | Opens a package for reflection |
| `open` | 开放模块 | Declares an open module |
| `uses` | 使用服务 | Declares service usage |
| `provides` | 提供服务 | Declares a service provider |
| `with` | 使用实现类 | Specifies the implementation class for `provides` |
| `to` | 指定目标模块 | Limits `exports` or `opens` to target modules |
| `transitive` | 传递依赖 | Makes a dependency readable by dependent modules |

## 3. Access Modifiers / 访问控制关键字

### `public`

中文解释：`public` 表示公开访问。被 `public` 修饰的类、方法或字段可以被其他包中的代码访问。

English explanation: `public` means the member or class is accessible from anywhere.

Exam sentence: `public` is an access modifier that allows a class, method, or field to be accessed from any other class.

Example:

```java
public class Student {
    public String getName() {
        return "Alice";
    }
}
```

Common mistake: A public top-level class must be declared in a file with the same name as the class.

常见易错点：如果顶级类是 `public`，文件名必须和类名一致。例如 `public class Student` 必须放在 `Student.java`。

### `private`

中文解释：`private` 表示只能在当前类内部访问。它是封装的核心。

English explanation: `private` means the member is accessible only inside the same class.

Exam sentence: `private` supports encapsulation by hiding the internal state of an object from outside classes.

Example:

```java
class Student {
    private String name;
}
```

Common mistake: Subclasses cannot directly access private fields of the superclass.

常见易错点：子类不能直接访问父类的 `private` 字段，需要通过 getter/setter 或 protected 方法访问。

### `protected`

中文解释：`protected` 表示同一个包内可访问，或者不同包中的子类可访问继承来的成员。

English explanation: `protected` allows access within the same package and from subclasses, even if they are in different packages.

Exam sentence: `protected` is less restrictive than `private` but more restrictive than `public`.

Common mistake: `protected` does not mean “only subclasses”; classes in the same package can also access it.

常见易错点：`protected` 不是“只有子类能访问”，同包类也可以访问。

## 4. Class, Object, Inheritance, Interface / 类、对象、继承、接口

### `class`

中文解释：`class` 用来定义类。类是对象的模板，描述对象有哪些字段和方法。

English explanation: `class` defines a class, which is a blueprint for creating objects.

Exam sentence: A class encapsulates data fields and methods that describe the state and behavior of objects.

Example:

```java
class Book {
    private String title;
}
```

### `new`

中文解释：`new` 用来创建对象或数组，并调用构造器初始化对象。

English explanation: `new` creates a new object or array in memory and invokes a constructor.

Exam sentence: The `new` keyword allocates memory for an object and calls its constructor.

Example:

```java
Book book = new Book();
int[] values = new int[10];
```

### `this`

中文解释：`this` 表示当前对象。常用于区分字段和参数。

English explanation: `this` refers to the current object.

Exam sentence: `this` is used to refer to the current object's fields, methods, or constructors.

Example:

```java
class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }
}
```

Common mistake: `this(...)` can call another constructor in the same class, but it must be the first statement in the constructor.

常见易错点：`this(...)` 调用本类其他构造器时，必须写在构造器第一行。

### `super`

中文解释：`super` 表示父类部分，可以调用父类构造器、字段或方法。

English explanation: `super` refers to the superclass part of the current object.

Exam sentence: `super` is used to call a superclass constructor or access overridden superclass methods.

Example:

```java
class Student extends Person {
    public Student(String name) {
        super(name);
    }
}
```

Common mistake: `super(...)` must be the first statement in a subclass constructor.

### `extends`

中文解释：`extends` 表示继承。子类继承父类的可访问成员，并可以扩展或重写行为。

English explanation: `extends` indicates inheritance between classes or interfaces.

Exam sentence: In Java, a class can extend only one superclass, but an interface can extend multiple interfaces.

Example:

```java
class Triangle extends GeometricObject {
}
```

Common mistake: Java does not support multiple inheritance of classes.

常见易错点：Java 类只能继承一个父类，但可以实现多个接口。

### `implements`

中文解释：`implements` 表示类实现接口。实现接口的类必须实现接口要求的方法。

English explanation: `implements` declares that a class provides implementations for an interface.

Exam sentence: A class uses `implements` to promise that it provides all required methods of an interface.

Example:

```java
class Student implements Comparable<Student> {
    public int compareTo(Student other) {
        return 0;
    }
}
```

### `interface`

中文解释：`interface` 用来定义接口。接口表达一种能力或规范，例如 `Comparable` 表示可比较。

English explanation: An `interface` defines a contract of methods that implementing classes must provide.

Exam sentence: An interface specifies what a class can do, while an abstract class can also store shared state and implementation.

Example:

```java
interface Discountable {
    double getDiscountedPrice(double percent);
}
```

Common mistake: Interface fields are implicitly `public static final`, and interface abstract methods are implicitly `public abstract`.

### `abstract`

中文解释：`abstract` 可以修饰类或方法。抽象类不能直接实例化；抽象方法没有方法体，必须由具体子类实现。

English explanation: `abstract` defines an incomplete class or method that must be completed by subclasses.

Exam sentence: An abstract class cannot be instantiated and may contain abstract methods that subclasses must implement.

Example:

```java
abstract class Employee {
    public abstract double calculateSalary();
}
```

Common mistake: If a class does not implement all inherited abstract methods, it must also be declared abstract.

### `final`

中文解释：`final` 可以修饰变量、方法和类。修饰变量表示只能赋值一次；修饰方法表示不能被重写；修饰类表示不能被继承。

English explanation: `final` prevents reassignment, method overriding, or class inheritance depending on where it is used.

Exam sentence: A `final` variable cannot be reassigned, a `final` method cannot be overridden, and a `final` class cannot be extended.

Example:

```java
final int maxSize = 100;
```

Common mistake: A final reference cannot point to another object, but the object it refers to may still be mutable.

常见易错点：`final List<String> list` 不能换成另一个 list，但原 list 里面仍然可以添加元素。

### `instanceof`

中文解释：`instanceof` 用来判断对象的运行时类型，常用于向下转型前的安全检查。

English explanation: `instanceof` tests whether an object is an instance of a class, subclass, or implementation of an interface.

Exam sentence: `instanceof` is used to avoid unsafe casting by checking the runtime type before a downcast.

Example:

```java
if (person instanceof Student) {
    Student student = (Student) person;
}
```

## 5. Primitive Types / 基本数据类型

### `boolean`

中文解释：`boolean` 表示布尔类型，只有 `true` 和 `false` 两个值。

English explanation: `boolean` is a primitive type that stores either `true` or `false`.

Exam sentence: A `boolean` expression is required in conditional statements such as `if` and `while`.

### `byte`

中文解释：`byte` 是 8 位整数，范围是 -128 到 127。

English explanation: `byte` is an 8-bit signed integer primitive type.

Exam sentence: `byte` is useful for saving memory when storing large amounts of small integer data.

### `short`

中文解释：`short` 是 16 位整数，范围是 -32768 到 32767。

English explanation: `short` is a 16-bit signed integer primitive type.

Exam sentence: `short` uses less memory than `int`, but `int` is more commonly used in normal arithmetic.

### `int`

中文解释：`int` 是 32 位整数，是 Java 中最常用的整数类型。

English explanation: `int` is a 32-bit signed integer primitive type.

Exam sentence: `int` is commonly used for counters, indexes, and general integer calculations.

### `long`

中文解释：`long` 是 64 位整数。字面量通常加 `L`。

English explanation: `long` is a 64-bit signed integer primitive type.

Exam sentence: `long` can store larger integers than `int`, but arbitrary-size integers require `BigInteger`.

Example:

```java
long value = 10000000000L;
```

### `float`

中文解释：`float` 是 32 位浮点数，字面量要加 `f` 或 `F`。

English explanation: `float` is a 32-bit floating-point primitive type.

Exam sentence: A float literal must usually end with `f` or `F`, because decimal literals are `double` by default.

### `double`

中文解释：`double` 是 64 位浮点数，是 Java 默认小数类型。

English explanation: `double` is a 64-bit floating-point primitive type.

Exam sentence: `double` is commonly used for mathematical calculations because it has higher precision than `float`.

### `char`

中文解释：`char` 表示单个 Unicode 字符，用单引号。

English explanation: `char` is a primitive type that stores a single Unicode character.

Exam sentence: A `char` literal uses single quotes, while a `String` literal uses double quotes.

Example:

```java
char grade = 'A';
String text = "A";
```

### `void`

中文解释：`void` 表示方法没有返回值。

English explanation: `void` indicates that a method does not return a value.

Exam sentence: A `void` method may use `return;` to exit early, but it cannot return a value.

## 6. Conditional Statements And Loops / 条件、分支与循环

### `if`

中文解释：`if` 用于条件判断。条件表达式必须是 `boolean`。

English explanation: `if` executes a block of code only when a condition is true.

Exam sentence: The condition inside an `if` statement must evaluate to a boolean value.

### `else`

中文解释：`else` 表示 `if` 条件不成立时执行的分支。

English explanation: `else` provides an alternative branch when the `if` condition is false.

Exam sentence: `else if` is not a separate keyword; it is an `else` followed by another `if`.

### `switch`

中文解释：`switch` 用于多分支选择，常和 `case`、`default`、`break` 搭配。

English explanation: `switch` selects one branch from multiple possible cases.

Exam sentence: In a traditional `switch`, missing `break` may cause fall-through to the next case.

### `case`

中文解释：`case` 表示 `switch` 中的某个匹配分支。

English explanation: `case` labels a branch inside a `switch` statement.

Exam sentence: Each `case` represents one possible value that the switch expression may match.

### `default`

中文解释：`default` 在 `switch` 中表示没有任何 `case` 匹配时执行的分支；在接口中表示默认方法。

English explanation: `default` provides a fallback branch in `switch` or a default method implementation in an interface.

Exam sentence: A `default` method in an interface has a method body and can be inherited by implementing classes.

### `for`

中文解释：`for` 用于循环。普通 `for` 适合计数循环，增强 `for` 适合遍历数组或集合。

English explanation: `for` repeats a block of code, usually with initialization, condition, and update expressions.

Exam sentence: An enhanced `for` loop is used to iterate over arrays or iterable collections without manually using an index.

### `while`

中文解释：`while` 先判断条件，再执行循环体。如果条件一开始为 false，循环一次也不执行。

English explanation: `while` repeats a block as long as the condition remains true.

Exam sentence: A `while` loop may execute zero times if its condition is initially false.

### `do`

中文解释：`do` 和 `while` 组成 `do-while` 循环，循环体至少执行一次。

English explanation: `do` starts a do-while loop, which checks the condition after executing the loop body.

Exam sentence: A `do-while` loop always executes its body at least once.

### `break`

中文解释：`break` 立即跳出当前循环或传统 `switch`。

English explanation: `break` exits the nearest enclosing loop or switch statement.

Exam sentence: `break` is often used in a switch statement to prevent fall-through.

### `continue`

中文解释：`continue` 跳过当前循环剩余语句，进入下一轮循环。

English explanation: `continue` skips the rest of the current iteration and proceeds to the next iteration.

Exam sentence: `continue` does not terminate the whole loop; it only skips the current iteration.

### `return`

中文解释：`return` 从方法返回。有返回值的方法必须返回对应类型的值。

English explanation: `return` exits a method and optionally returns a value to the caller.

Exam sentence: A non-void method must return a value compatible with its declared return type.

## 7. Exception Handling / 异常处理

### `try`

中文解释：`try` 包住可能抛出异常的代码。

English explanation: `try` encloses code that may throw an exception.

Exam sentence: A `try` block must be followed by at least one `catch` block or a `finally` block.

### `catch`

中文解释：`catch` 捕获并处理异常。

English explanation: `catch` handles an exception thrown by the corresponding `try` block.

Exam sentence: More specific exception types should be caught before more general exception types.

### `finally`

中文解释：`finally` 通常无论是否发生异常都会执行，常用于释放资源。

English explanation: `finally` contains cleanup code that usually runs whether or not an exception occurs.

Exam sentence: `finally` is commonly used to release resources such as files, streams, or database connections.

### `throw`

中文解释：`throw` 主动抛出一个异常对象。

English explanation: `throw` explicitly throws an exception object.

Exam sentence: `throw` is used inside a method body to create and throw a specific exception.

Example:

```java
throw new IllegalArgumentException("Invalid input");
```

### `throws`

中文解释：`throws` 写在方法声明上，表示方法可能把异常交给调用者处理。

English explanation: `throws` declares the exception types that a method may pass to its caller.

Exam sentence: `throw` actually throws an exception, while `throws` declares that a method may throw exceptions.

### `assert`

中文解释：`assert` 是断言，用于调试时检查程序内部假设。

English explanation: `assert` checks an assumption during debugging and may throw an `AssertionError` if the condition is false.

Exam sentence: Assertions are disabled by default and should not be used for normal input validation.

## 8. Packages, Imports, And Enum / 包、导入与枚举

### `package`

中文解释：`package` 声明当前类属于哪个包，必须放在源文件非注释代码的第一行。

English explanation: `package` declares the package to which a class belongs.

Exam sentence: The package declaration, if present, must be the first non-comment statement in a Java source file.

### `import`

中文解释：`import` 导入其他包中的类，避免写完整限定名。

English explanation: `import` allows a class to use another class without writing its fully qualified name.

Exam sentence: Classes in `java.lang`, such as `String` and `Math`, are imported automatically.

### `enum`

中文解释：`enum` 定义枚举类型，适合表示有限固定取值。

English explanation: `enum` defines a type with a fixed set of constants.

Exam sentence: An enum is safer than using strings for fixed categories because invalid values are rejected at compile time.

Example:

```java
enum Priority {
    HIGH, MEDIUM, LOW
}
```

## 9. Static And Other Modifiers / 静态与其他修饰符

### `static`

中文解释：`static` 表示属于类，而不是属于某个对象。静态字段被所有对象共享。

English explanation: `static` means that a field or method belongs to the class rather than to individual objects.

Exam sentence: A static method can be called using the class name, but it cannot directly access instance fields without an object.

Common mistake: `static` does not mean “constant”; constants are usually written as `static final`.

### `synchronized`

中文解释：`synchronized` 用于多线程同步，保证同一时间只有一个线程进入被同一把锁保护的代码。

English explanation: `synchronized` controls concurrent access to a critical section by using a lock.

Exam sentence: `synchronized` helps prevent race conditions when multiple threads access shared mutable data.

### `volatile`

中文解释：`volatile` 用于多线程可见性。一个线程修改后，其他线程能看到最新值。

English explanation: `volatile` ensures visibility of changes to a variable across threads.

Exam sentence: `volatile` guarantees visibility, but it does not make compound operations such as `count++` atomic.

### `transient`

中文解释：`transient` 和序列化有关，被它修饰的字段不会被默认序列化。

English explanation: `transient` prevents a field from being serialized by Java's default serialization mechanism.

Exam sentence: A transient field is skipped during serialization, often for sensitive or temporary data.

### `native`

中文解释：`native` 表示方法由非 Java 语言实现，例如 C 或 C++。

English explanation: `native` indicates that a method is implemented in platform-dependent code outside Java.

Exam sentence: A native method has no Java method body because its implementation is provided by another language.

### `strictfp`

中文解释：`strictfp` 用于要求浮点计算遵守严格标准。现代 Java 中很少手写。

English explanation: `strictfp` enforces strict floating-point calculation rules for portability.

Exam sentence: `strictfp` is rarely used in normal coursework but may appear in keyword lists.

## 10. Reserved But Unused Keywords / 保留但不用的关键字

### `const`

中文解释：Java 保留了 `const`，但不使用它定义常量。

English explanation: `const` is reserved in Java but has no practical use.

Exam sentence: Java uses `final` or `static final`, not `const`, to define constants.

### `goto`

中文解释：Java 保留了 `goto`，但不支持任意跳转。

English explanation: `goto` is reserved but not used in Java.

Exam sentence: Java avoids arbitrary jumps and uses structured control flow such as loops, `break`, `continue`, and `return`.

## 11. Literals / 字面量

### `true`

中文解释：`true` 是布尔真值字面量。

English explanation: `true` is the boolean literal representing truth.

Exam sentence: `true` is not a variable; it is a boolean literal.

### `false`

中文解释：`false` 是布尔假值字面量。

English explanation: `false` is the boolean literal representing falsehood.

Exam sentence: `false` can be assigned only to a boolean variable or used in boolean expressions.

### `null`

中文解释：`null` 是空引用，表示引用变量没有指向任何对象。

English explanation: `null` is the literal representing no object reference.

Exam sentence: Calling a method on `null` causes a `NullPointerException`.

Common mistake: Primitive variables such as `int` and `double` cannot be `null`; only reference variables can be `null`.

## 12. Modern Java Contextual Keywords / 现代 Java 上下文关键字

这些词和 Java 版本有关，课程考试不一定深入，但英文题目或代码阅读中可能出现。

These words depend on Java versions. They may not be central in CPT204, but they can appear in English explanations or modern Java code.

### `var`

中文解释：`var` 用于局部变量类型推断。编译器根据右侧表达式推断静态类型。

English explanation: `var` enables local variable type inference.

Exam sentence: `var` does not make Java dynamically typed; the variable still has a fixed compile-time type.

Common mistake: `var` cannot be used for fields, method parameters, or method return types.

### `record`

中文解释：`record` 用于定义主要保存数据的不可变数据载体。

English explanation: `record` declares a compact immutable data carrier class.

Exam sentence: A record automatically provides a constructor, accessor methods, `equals`, `hashCode`, and `toString`.

### `yield`

中文解释：`yield` 用于 `switch expression` 中从代码块返回一个值。

English explanation: `yield` produces a value from a block in a switch expression.

Exam sentence: `yield` is used in switch expressions, not in traditional switch statements.

### `sealed`

中文解释：`sealed` 限制哪些类可以继承当前类或实现当前接口。

English explanation: `sealed` restricts which classes may extend a class or implement an interface.

Exam sentence: A sealed class controls its inheritance hierarchy by listing permitted subclasses.

### `permits`

中文解释：`permits` 和 `sealed` 搭配，列出允许的直接子类。

English explanation: `permits` lists the permitted direct subclasses of a sealed type.

Exam sentence: `permits` is used after a sealed class or interface declaration to specify allowed subclasses.

### `non-sealed`

中文解释：`non-sealed` 用在 sealed 体系的子类上，表示重新开放继承。

English explanation: `non-sealed` reopens inheritance for a subclass in a sealed hierarchy.

Exam sentence: A `non-sealed` class can be extended by other classes without the restrictions of its sealed parent.

## 13. Java Module System Words / Java 模块系统相关词

模块系统通常出现在 `module-info.java`，基础数据结构课程一般只需认识。

The module system words usually appear in `module-info.java`. In a data structures course, you usually only need to recognize them.

### `module`

中文解释：声明一个模块。

English explanation: `module` declares a Java module.

Exam sentence: A module groups related packages and declares its dependencies and exported packages.

### `requires`

中文解释：声明当前模块依赖另一个模块。

English explanation: `requires` declares a dependency on another module.

### `exports`

中文解释：声明某个包对其他模块可见。

English explanation: `exports` makes a package accessible to other modules.

### `opens`

中文解释：允许反射访问某个包。

English explanation: `opens` allows reflective access to a package.

### `open`

中文解释：声明开放模块，使模块内包更容易被反射访问。

English explanation: `open` declares an open module for reflection.

### `uses`

中文解释：声明当前模块使用某个服务。

English explanation: `uses` declares that a module consumes a service.

### `provides`

中文解释：声明当前模块提供某个服务实现。

English explanation: `provides` declares that a module provides an implementation of a service.

### `with`

中文解释：和 `provides` 搭配，指定服务实现类。

English explanation: `with` specifies the implementation class for a provided service.

### `to`

中文解释：限制 `exports` 或 `opens` 的目标模块。

English explanation: `to` restricts an export or open directive to specific target modules.

### `transitive`

中文解释：用于 `requires transitive`，表示依赖可以传递。

English explanation: `transitive` makes a required module readable by modules that depend on the current module.

## 14. Special Identifier `_` / 特殊标识符下划线

中文解释：单独的 `_` 从 Java 9 开始不能作为变量名使用。

English explanation: A single underscore `_` cannot be used as an identifier since Java 9.

Exam sentence: `_` is reserved and cannot be used as a variable name in modern Java.

## 15. High-Frequency Exam Phrases / 英文考试高频表达

### About Encapsulation / 关于封装

English answer:

> Encapsulation hides the internal state of an object and exposes controlled access through public methods.

中文理解：

封装隐藏对象内部状态，通过 public 方法提供受控访问。

### About Inheritance / 关于继承

English answer:

> Inheritance allows a subclass to reuse and extend the fields and methods of a superclass.

中文理解：

继承让子类复用并扩展父类的字段和方法。

### About Interface / 关于接口

English answer:

> An interface defines a contract that implementing classes must follow.

中文理解：

接口定义一种规范，实现接口的类必须遵守。

### About Abstract Class / 关于抽象类

English answer:

> An abstract class cannot be instantiated and may contain abstract methods that must be implemented by subclasses.

中文理解：

抽象类不能直接创建对象，可以包含必须由子类实现的抽象方法。

### About `static` / 关于 static

English answer:

> A static member belongs to the class rather than to any particular object.

中文理解：

静态成员属于类，而不是属于某个具体对象。

### About `final` / 关于 final

English answer:

> `final` prevents reassignment for variables, overriding for methods, and inheritance for classes.

中文理解：

`final` 修饰变量表示不能重新赋值，修饰方法表示不能重写，修饰类表示不能继承。

### About `throw` vs `throws` / 关于 throw 和 throws

English answer:

> `throw` is used to actually throw an exception, while `throws` declares that a method may throw exceptions.

中文理解：

`throw` 是真正抛出异常，`throws` 是在方法声明中说明可能抛出异常。

### About `break` vs `continue` / 关于 break 和 continue

English answer:

> `break` terminates the nearest loop or switch, while `continue` skips only the current loop iteration.

中文理解：

`break` 结束整个循环或 switch，`continue` 只跳过当前这一轮循环。

### About `private` vs `protected` vs `public`

English answer:

> `private` allows access only within the same class, `protected` allows access within the same package and subclasses, and `public` allows access from anywhere.

中文理解：

`private` 只允许本类访问，`protected` 允许同包和子类访问，`public` 允许任何地方访问。

## 16. Common Exam Traps / 常见考试陷阱

1. `static` does not mean constant.  
   `static` 不等于常量；常量通常是 `static final`。

2. `final` reference does not always mean immutable object.  
   `final` 引用不能换对象，但对象内部可能还能改。

3. `private` members are not directly inherited for access by subclasses.  
   `private` 成员虽然存在于对象中，但子类不能直接访问。

4. `throw` and `throws` are different.  
   `throw` 是抛出，`throws` 是声明。

5. `interface` fields are implicitly `public static final`.  
   接口字段默认就是公开静态常量。

6. `default` has two meanings.  
   `default` 既可以是 switch 默认分支，也可以是接口默认方法。

7. A `while` loop may run zero times, but a `do-while` loop runs at least once.  
   `while` 可能一次不执行，`do-while` 至少执行一次。

8. `ArrayList<Integer>` is not a subtype of `ArrayList<Number>`.  
   泛型是不变的，虽然 `Integer` 是 `Number` 的子类，但 `ArrayList<Integer>` 不是 `ArrayList<Number>` 的子类。

9. `null` can only be assigned to reference types, not primitive types.  
   `null` 只能赋给引用类型，不能赋给 `int`、`double` 等基本类型。

10. `const` and `goto` are reserved but unused in Java.  
    `const` 和 `goto` 被保留，但 Java 不使用它们。

## 17. One-Page Priority List / 考前优先背诵清单

最优先掌握：

```text
public private protected class static void main new this super
extends implements interface abstract final
if else switch case default
for while do break continue return
try catch finally throw throws
int double boolean char
import package enum instanceof
```

英文考试最常用表达：

```text
access modifier
encapsulation
inheritance
subclass
superclass
interface contract
abstract method
method overriding
method overloading
runtime type
exception handling
primitive type
reference type
static member
compile-time type
runtime object
```

建议最后能用英文解释这些句子：

- A class is a blueprint for creating objects.
- An object is an instance of a class.
- A private field supports encapsulation.
- A subclass inherits accessible members from its superclass.
- An interface defines a contract for implementing classes.
- A static method belongs to the class, not to an object.
- A final method cannot be overridden.
- A try block contains code that may throw an exception.
- A catch block handles an exception.
- A finally block is usually used for cleanup code.
