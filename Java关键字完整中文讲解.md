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

## 15. Keyword Effects Table / 关键字具体作用与效果总表

这一节专门回答考试中常见的 “What does this keyword do?” 和 “What is the effect of this keyword?”。中文叫“它具体改变了什么”；英文答题时可以说 “The keyword affects access control / inheritance / object creation / control flow / exception propagation / type checking.”

This section focuses on the concrete effect of each keyword. In an English exam, do not only translate the keyword; explain what it changes in the program.

| Keyword | 具体作用 / Concrete effect | 影响阶段 / Main stage | 易考点 / Exam trap |
|---|---|---|---|
| `public` | 让类、方法、字段从任何包可访问 / makes a class, method, or field accessible from anywhere | Compile-time access checking | `public` 顶级类名必须和文件名一致 |
| `private` | 限制成员只能在本类访问 / restricts access to the same class | Compile-time access checking | 子类不能直接访问父类 `private` 字段 |
| `protected` | 允许同包访问和子类访问 / allows same-package and subclass access | Compile-time access checking | 不是 only subclasses，同包也能访问 |
| `class` | 定义引用类型和对象模板 / defines a reference type and object blueprint | Compile time and runtime | class 本身不是对象；object 是 class 的 instance |
| `interface` | 定义能力契约 / defines a contract of methods | Compile-time type checking | 字段默认 `public static final`，抽象方法默认 `public abstract` |
| `extends` | 建立继承关系 / creates an inheritance relationship | Compile-time type hierarchy | 类只能 `extends` 一个类，接口可 `extends` 多个接口 |
| `implements` | 声明类实现接口 / declares that a class implements an interface | Compile-time type checking | 普通类必须实现接口中所有抽象方法 |
| `abstract` | 标记类或方法不完整 / marks a class or method as incomplete | Compile-time checking | 抽象类不能 `new`，抽象方法没有方法体 |
| `final` | 禁止重新赋值、重写或继承 / prevents reassignment, overriding, or inheritance | Compile-time checking | `final` 引用不等于对象不可变 |
| `new` | 创建对象或数组并调用构造器 / creates an object or array and invokes a constructor | Runtime | `new` 返回引用；对象在 heap 中 |
| `this` | 指向当前对象 / refers to the current object | Runtime reference | `this(...)` 必须是构造器第一行 |
| `super` | 指向父类部分 / refers to the superclass part | Runtime dispatch and constructor chaining | `super(...)` 必须是子类构造器第一行 |
| `instanceof` | 判断运行时对象类型 / tests runtime object type | Runtime type checking | 左边为 `null` 时结果是 `false`，不会抛异常 |
| `boolean` | 存储真/假 / stores `true` or `false` | Runtime value | Java 条件必须是 boolean，不能用 `1` 当 true |
| `byte` | 8 位有符号整数 / 8-bit signed integer | Runtime value | 算术运算会提升为 `int` |
| `short` | 16 位有符号整数 / 16-bit signed integer | Runtime value | `short + short` 结果通常是 `int` |
| `int` | 32 位有符号整数 / 32-bit signed integer | Runtime value | 整数除法会截断小数部分 |
| `long` | 64 位有符号整数 / 64-bit signed integer | Runtime value | long literal 建议加 `L`，不是小写 `l` |
| `float` | 32 位浮点数 / 32-bit floating-point number | Runtime value | 小数字面量默认是 `double`，float 需要 `f` |
| `double` | 64 位浮点数 / 64-bit floating-point number | Runtime value | 浮点数有精度误差，不能总用 `==` 比较 |
| `char` | 单个 Unicode 字符 / single Unicode character | Runtime value | 单引号是 char，双引号是 String |
| `void` | 表示无返回值 / indicates no return value | Compile-time method signature | `void` 方法不能返回具体值 |
| `if` | 条件为 true 时执行分支 / executes a branch if condition is true | Runtime control flow | 条件表达式必须是 boolean |
| `else` | `if` 条件为 false 时执行 / executes alternative branch | Runtime control flow | dangling else 默认匹配最近的 unmatched `if` |
| `switch` | 多分支选择 / selects among multiple branches | Runtime control flow | 传统 switch 没有 `break` 会 fall-through |
| `case` | 标记 switch 分支 / labels a switch branch | Runtime control flow | case 值通常必须是编译期常量 |
| `default` | switch 兜底分支；接口默认方法 / fallback branch or interface default method | Runtime control flow / type behavior | `default` 在 switch 和 interface 中含义不同 |
| `for` | 循环执行代码 / repeats code in a loop | Runtime control flow | enhanced for 不能安全地直接删除集合元素 |
| `while` | 条件为 true 时重复 / repeats while condition is true | Runtime control flow | 可能执行 0 次 |
| `do` | 先执行再判断 / executes before checking condition | Runtime control flow | 至少执行 1 次 |
| `break` | 跳出循环或 switch / exits loop or switch | Runtime control flow | 只跳出最近一层，除非使用 label |
| `continue` | 跳过当前循环剩余部分 / skips current iteration | Runtime control flow | 不结束整个循环 |
| `return` | 结束方法并返回结果 / exits a method and returns a value | Runtime control flow | `finally` 可能在 return 后、真正返回前执行 |
| `try` | 包住可能抛异常的代码 / encloses code that may throw exceptions | Runtime exception handling | 必须配 `catch` 或 `finally` |
| `catch` | 捕获异常 / handles an exception | Runtime exception handling | 子类异常要写在父类异常前 |
| `finally` | 执行清理代码 / executes cleanup code | Runtime exception handling | `finally` 中 return 会覆盖 try/catch 的 return，不推荐 |
| `throw` | 主动抛出异常对象 / explicitly throws an exception object | Runtime exception handling | 只能 throw `Throwable` 的对象 |
| `throws` | 声明方法可能抛异常 / declares possible exceptions | Compile-time exception checking | `throws` 不处理异常，只把责任交给调用者 |
| `assert` | 检查调试假设 / checks debugging assumptions | Runtime if enabled | 默认关闭，不能代替输入验证 |
| `package` | 声明包名 / declares a package | Compile-time organization | 必须是第一条非注释语句 |
| `import` | 导入类名 / imports class names | Compile-time name resolution | `import` 不会复制代码，也不会影响运行速度 |
| `enum` | 定义固定常量集合 / defines fixed constants | Compile time and runtime | enum 构造器默认 private，不能手动 `new` enum |
| `static` | 让成员属于类 / makes a member belong to the class | Class loading and compile-time access | `static` 不是 constant；constant 通常是 `static final` |
| `synchronized` | 使用锁保护临界区 / protects a critical section with a lock | Runtime threading | 会影响并发性能；不是所有线程问题都能靠它解决 |
| `volatile` | 保证变量可见性 / ensures visibility across threads | Runtime memory visibility | 不保证 `count++` 原子性 |
| `transient` | 阻止字段默认序列化 / excludes a field from default serialization | Serialization runtime | 只影响 Java serialization，不影响普通保存逻辑 |
| `native` | 声明非 Java 实现的方法 / declares a non-Java implemented method | Link/runtime native call | native 方法没有 Java 方法体 |
| `strictfp` | 强制严格浮点规则 / enforces strict floating-point rules | Floating-point computation | 现代 Java 中很少用，认识即可 |
| `const` | 保留不用 / reserved but unused | Compile-time reserved word | Java 常量用 `final`，不是 `const` |
| `goto` | 保留不用 / reserved but unused | Compile-time reserved word | Java 不支持任意跳转 |
| `true` | boolean 真字面量 / boolean true literal | Runtime value | 不是变量名 |
| `false` | boolean 假字面量 / boolean false literal | Runtime value | 不是变量名 |
| `null` | 空引用 / null reference literal | Runtime reference value | 对 null 调方法会 NPE；primitive 不能为 null |
| `var` | 局部变量类型推断 / local variable type inference | Compile-time type inference | 不是动态类型；不能用于字段和返回值 |
| `record` | 简洁数据类 / compact data carrier | Compile-time generated members | record 浅不可变；引用字段指向的对象仍可能可变 |
| `yield` | 从 switch expression 产出值 / yields a value from switch expression | Runtime control expression | 用于 switch expression，不是传统 switch 必需 |
| `sealed` | 限制继承范围 / restricts inheritance | Compile-time type hierarchy | 子类必须是 `final`、`sealed` 或 `non-sealed` |
| `permits` | 列出允许子类 / lists permitted subclasses | Compile-time type hierarchy | 只列直接子类 |
| `non-sealed` | 重新开放继承 / reopens inheritance | Compile-time type hierarchy | 只能出现在 sealed 体系子类上 |
| `module` | 声明模块 / declares a module | Module system | 出现在 `module-info.java` |
| `requires` | 声明模块依赖 / declares module dependency | Module resolution | `requires transitive` 会传递可读性 |
| `exports` | 导出包 / exports a package | Module accessibility | 只影响编译/普通访问，不等于反射开放 |
| `opens` | 开放反射访问 / opens a package for reflection | Runtime reflection | 常用于框架反射 |
| `open` | 开放整个模块 / opens all packages in a module | Runtime reflection | 比逐个 `opens` 更宽 |
| `uses` | 声明使用服务 / declares service usage | Service loading | 和 ServiceLoader 相关 |
| `provides` | 声明提供服务 / declares service provider | Service loading | 通常搭配 `with` |
| `with` | 指定服务实现 / specifies service implementation | Service loading | 不是普通类声明关键字 |
| `to` | 限制目标模块 / limits target modules | Module accessibility | 用在 `exports ... to` 或 `opens ... to` |
| `transitive` | 传递模块依赖 / makes dependency transitive | Module readability | 不是普通继承的 transitive |
| `_` | 单独下划线保留 / single underscore is reserved | Compile-time identifier rule | Java 9 后不能当变量名 |

## 16. Deep Exam Traps / 更容易挖坑的考试点

这一节比前面的 `Common mistake` 更细，适合考前专门刷“坑题”。英文考试里，很多题不是问你 keyword 的中文意思，而是问它的 exact effect、legal position、compile-time error、runtime behavior。

### 16.1 Access Control Traps / 访问控制陷阱

`private`、`protected`、`public` 都是 compile-time access control。也就是说，很多访问错误在编译期就会被发现。

English exam phrase:

> Access modifiers are checked at compile time and determine where a class member can be accessed.

坑点：

- `private` 字段不可以被子类直接访问，即使子类对象内部确实包含父类那部分状态。
- `protected` 不是“只有子类可访问”。同一个 package 中的非子类也可以访问。
- 顶级类只能是 `public` 或 package-private，不能写 `private class` 或 `protected class`。
- 一个 `.java` 文件中只能有一个 `public` top-level class。

### 16.2 `static` vs Instance / 静态成员与实例成员陷阱

`static` 成员属于 class；instance 成员属于 object。

English exam phrase:

> A static member is shared by the class, while an instance member belongs to each object.

坑点：

- `static` 方法不能直接访问 instance field，因为它没有 `this`。
- instance 方法可以访问 static 成员，因为对象知道自己属于哪个类。
- `static` field 被所有对象共享，一个对象改了，其他对象看到的也是同一份值。
- `static final` 常用于常量，但只有 `static` 不代表不能修改。

Example trap:

```java
class Counter {
    static int count;
    int id;

    static void printId() {
        // System.out.println(id); // 编译错误：static context 没有 this
    }
}
```

### 16.3 `final` Is Not Always Immutable / final 不等于完全不可变

`final` 的直接效果是“不能重新赋值”，不是“对象内容永远不变”。

English exam phrase:

> A final reference cannot be reassigned, but the object it refers to may still be mutable.

坑点：

```java
final ArrayList<String> names = new ArrayList<>();
names.add("Alice");       // 合法：修改对象内容
// names = new ArrayList<>(); // 非法：重新赋值引用
```

真正 immutable object 需要对象本身不暴露可变状态，例如 `String`。

### 16.4 Abstract Class vs Interface / 抽象类与接口陷阱

English exam phrase:

> An abstract class is used for shared state and partial implementation, while an interface defines a contract or capability.

坑点：

- 抽象类可以有构造器，但不能直接 `new` 抽象类。
- 接口也可以有 `default` 和 `static` 方法。
- 一个类只能继承一个 abstract class，但可以实现多个 interfaces。
- 接口字段默认是 `public static final`，所以必须初始化。
- 如果普通类没有实现所有抽象方法，就必须声明为 `abstract`。

### 16.5 Constructor Traps: `this(...)` and `super(...)`

构造器链非常容易考。

English exam phrase:

> `this(...)` calls another constructor in the same class, while `super(...)` calls a constructor in the superclass.

坑点：

- `this(...)` 和 `super(...)` 都必须是构造器第一行。
- 同一个构造器不能同时第一行写 `this(...)` 和 `super(...)`。
- 如果没有显式写 `super(...)`，Java 会尝试自动插入 `super()`。
- 如果父类没有 no-arg constructor，子类必须显式调用某个父类构造器。

### 16.6 Overriding Traps / 方法重写陷阱

虽然 `override` 不是 Java keyword，但 `extends`、`implements`、`abstract`、`final` 经常和重写一起考。

English exam phrase:

> Method overriding occurs when a subclass provides a new implementation for an inherited method with the same signature.

坑点：

- `final` 方法不能被 override。
- `private` 方法不能真正被 override，因为子类看不到它；子类写同名方法只是新方法。
- `static` 方法不能被真正 override，只能 method hiding。
- 重写时不能降低访问权限，例如父类 `public`，子类不能改成 `protected`。
- 返回类型可以是 covariant return type，也就是更具体的子类型。

### 16.7 Primitive Type Traps / 基本类型陷阱

English exam phrase:

> Primitive variables store actual values, while reference variables store references to objects.

坑点：

- `int / int` 得到 `int`，小数部分被截断：`5 / 2 == 2`。
- `byte`、`short`、`char` 做算术时通常会提升为 `int`。
- `float` 字面量通常要写 `3.14f`，否则 `3.14` 是 `double`。
- 浮点数有精度误差，不推荐用 `==` 比较计算结果。
- primitive 不能赋值为 `null`。

Example:

```java
double result = 5 / 2;   // 结果是 2.0，不是 2.5
double fixed = 5 / 2.0;  // 结果是 2.5
```

### 16.8 `null` and Reference Traps / null 与引用陷阱

English exam phrase:

> `null` means that a reference variable does not refer to any object.

坑点：

- `obj instanceof SomeClass` 在 `obj == null` 时返回 `false`，不会抛异常。
- 对 `null` 调用实例方法会抛 `NullPointerException`。
- 数组是引用类型，所以数组变量可以是 `null`。
- 数组元素有默认值：对象数组元素默认是 `null`，`int[]` 默认是 0，`boolean[]` 默认是 false。

### 16.9 `switch`, `case`, `default`, `break` Traps

English exam phrase:

> In a traditional switch statement, execution falls through to the next case unless a `break` or another terminating statement is used.

坑点：

- 传统 switch 中 `case` 后没有 `break` 会继续执行后面的 case。
- `default` 不一定必须放最后；放中间也合法，但执行流仍可能 fall-through。
- `case` 标签通常必须是 compile-time constant。
- `switch` 支持 `String` 和 `enum`，但不能直接 switch `double` 或 `float`。
- 新版 switch expression 可以用 `->`，通常不需要 `break`。

### 16.10 Loop Traps: `for`, `while`, `do`, `break`, `continue`

English exam phrase:

> `break` terminates the nearest loop, whereas `continue` skips the current iteration and continues with the next iteration.

坑点：

- `while` 可能执行 0 次；`do-while` 至少执行 1 次。
- enhanced for 不能方便地修改集合结构，否则可能 `ConcurrentModificationException`。
- `break` 默认只跳出最近一层循环。
- `continue` 在 `for` 中会先执行 update expression，再检查 condition。
- 无限循环常见写法：`while (true)`，需要靠 `break` 或 `return` 退出。

### 16.11 `return` and `finally` Traps

`finally` 的执行时间很容易被问。

English exam phrase:

> A `finally` block usually executes after the `try` or `catch` block, even if a return statement is reached.

坑点：

- `try` 中执行 `return` 前，`finally` 通常仍会执行。
- 如果 `finally` 中也有 `return`，它会覆盖 `try` 或 `catch` 的 return，不推荐这样写。
- 如果 JVM 直接退出，例如 `System.exit(0)`，`finally` 可能不执行。

Example trap:

```java
static int test() {
    try {
        return 1;
    } finally {
        return 2; // 最终返回 2，但这是坏写法
    }
}
```

### 16.12 `throw` vs `throws` vs `try-catch`

English exam phrase:

> `throw` creates and throws an exception object, while `throws` declares that a method may pass exceptions to its caller.

坑点：

- `throw` 后面必须是异常对象，例如 `new IllegalArgumentException()`。
- `throws` 写在方法签名上，不会真正处理异常。
- checked exception 必须被 catch 或在方法上 throws。
- unchecked exception，例如 `RuntimeException`，不强制 catch 或 throws。
- catch 顺序必须从具体异常到一般异常。

### 16.13 `assert` Traps

English exam phrase:

> Assertions are mainly used for debugging internal assumptions and are disabled by default.

坑点：

- 默认运行 Java 程序时，`assert` 不生效。
- 需要 `java -ea ClassName` 开启。
- 不要用 assert 检查用户输入，因为正式运行环境可能关闭断言。

### 16.14 `package` and `import` Traps

English exam phrase:

> `import` only helps the compiler resolve class names; it does not include or copy code into the file.

坑点：

- `package` 必须放在第一条非注释语句。
- `import` 必须放在 `package` 后、类定义前。
- `java.lang` 自动导入。
- `import java.util.*` 不会导入 `java.util.concurrent.*`，通配符不递归。
- 同名类冲突时，需要使用 fully qualified name。

### 16.15 `enum` Traps

English exam phrase:

> An enum defines a fixed set of constants and each constant is an instance of the enum type.

坑点：

- enum 不能用 `new` 创建。
- enum 构造器默认 private。
- enum 可以有字段、构造器和方法。
- `switch` enum 时 case 写常量名，不写 enum 类型名前缀。

### 16.16 Generics With `extends` and `super` / 泛型里的 extends 和 super

这里的 `extends` 和 `super` 不只是继承语法，也可用于泛型通配符边界。

English exam phrase:

> In generics, `? extends T` is mainly for reading values as T, while `? super T` is mainly for writing T values.

坑点：

- `List<Integer>` 不是 `List<Number>` 的子类型。
- `? extends T` 可以安全读取为 T，但通常不能安全添加 T。
- `? super T` 可以安全写入 T，但读取出来通常只能当 Object。
- 口诀：PECS, Producer Extends, Consumer Super。

### 16.17 `volatile` and `synchronized` Traps

English exam phrase:

> `volatile` guarantees visibility, while `synchronized` provides mutual exclusion and also visibility.

坑点：

- `volatile` 不保证 `count++` 原子性。
- `synchronized` 能保证同一把锁同一时间只被一个线程进入。
- 如果两个 synchronized block 锁的不是同一个对象，就不能互斥。
- 单线程课程中通常只需认识这些关键字，不需要深入并发实现。

### 16.18 `transient`, `native`, `strictfp` Recognition Traps

这些低频关键字常出现在“which is a Java keyword?” 类型题目中。

English exam phrase:

> `transient`, `native`, and `strictfp` are Java keywords, although they are less common in ordinary coursework.

坑点：

- `transient` 只影响 Java 默认序列化。
- `native` 方法没有 Java 方法体。
- `strictfp` 和浮点计算一致性有关，现代代码少见。

### 16.19 `var` Traps

English exam phrase:

> `var` performs local variable type inference at compile time; it does not make Java dynamically typed.

坑点：

- `var` 只能用于局部变量。
- 不能用于字段、方法参数、返回类型。
- 必须能从初始化表达式推断类型，所以不能写 `var x;`。
- `var x = null;` 不合法，因为无法推断具体类型。

### 16.20 `record` Traps

English exam phrase:

> A record is a compact data carrier that automatically provides a constructor, accessors, `equals`, `hashCode`, and `toString`.

坑点：

- record 字段默认是 private final。
- record 是 shallow immutable，不是 deep immutable。
- 如果 record 字段是 `ArrayList`，list 内容仍然可能被修改。
- record 访问器是 `name()`，不是传统 `getName()`。

### 16.21 `sealed`, `permits`, `non-sealed` Traps

English exam phrase:

> A sealed class restricts which classes can directly extend it, and permitted subclasses must explicitly choose `final`, `sealed`, or `non-sealed`.

坑点：

- `permits` 列出的是直接子类，不是所有后代类。
- sealed 子类必须明确写 `final`、`sealed` 或 `non-sealed`。
- `non-sealed` 只能用于 sealed inheritance hierarchy。
- sealed 主要用于控制类型层次，不是控制对象内容不可变。

### 16.22 Module Keyword Traps

模块系统相关词通常不是普通 Java 类文件里的业务代码。

English exam phrase:

> Module declarations are written in `module-info.java` and control module dependencies and exported packages.

坑点：

- `exports` 让其他模块普通访问某个包。
- `opens` 主要允许 reflection。
- `requires transitive` 会把依赖暴露给依赖当前模块的其他模块。
- 对 CPT204 这类课程，通常认识即可，不需要深入写模块系统代码。

### 16.23 Reserved Word Traps: `const`, `goto`, `_`

English exam phrase:

> `const` and `goto` are reserved but unused in Java, and a single underscore cannot be used as an identifier in modern Java.

坑点：

- Java 常量不是 `const`，而是 `final` 或 `static final`。
- Java 没有真正的 `goto`。
- 单独 `_` 不能作为变量名。

### 16.24 Keyword Position Traps / 关键字位置陷阱

有些关键字只能出现在特定位置。

| Keyword | 合法位置 / Legal position | 常见错误 / Common illegal use |
|---|---|---|
| `package` | 文件第一条非注释语句 | 放在 import 后面 |
| `import` | package 后、class 前 | 放在 class 里面 |
| `public` top-level class | 顶级类声明 | 文件名不匹配 |
| `this(...)` | 构造器第一行 | 放在普通方法里或构造器第二行 |
| `super(...)` | 子类构造器第一行 | 和 `this(...)` 同时出现 |
| `return value` | 非 void 方法或 lambda | 在 `void` 方法返回具体值 |
| `break` | loop 或 switch 内 | 写在没有循环/switch 的普通代码块中 |
| `continue` | loop 内 | 写在 switch 中但不在循环中 |
| `case` | switch 内 | 写在 if 或普通 block 里 |
| `catch` | try 后 | 没有 try 单独出现 |
| `finally` | try/catch 后 | 单独出现 |
| `throws` | 方法/构造器声明 | 写在方法体里面 |
| `throw` | 方法体语句 | 写在方法声明处代替 throws |

## 17. Exam Answer Templates For Effects / “作用和效果”英文答题模板

如果题目问 “Explain the effect of keyword X”，可以按下面模板回答。

### Access Modifier Template

> `X` is an access modifier. It controls where a class, method, or field can be accessed. This restriction is checked at compile time.

中文：`X` 是访问控制修饰符，决定类、方法或字段能在哪里被访问，这种限制通常在编译期检查。

### Inheritance Template

> `extends` creates an inheritance relationship, allowing a subclass to reuse and override accessible members of its superclass.

中文：`extends` 建立继承关系，让子类复用和重写父类可访问成员。

### Interface Template

> `implements` means that a class agrees to provide implementations for the methods required by an interface.

中文：`implements` 表示一个类承诺实现接口要求的方法。

### Object Creation Template

> `new` allocates memory for an object or array and calls the appropriate constructor.

中文：`new` 为对象或数组分配内存，并调用合适的构造器。

### Control Flow Template

> This keyword changes the normal flow of execution by selecting a branch, repeating a block, skipping an iteration, or exiting a method.

中文：这个关键字通过选择分支、重复代码块、跳过循环轮次或退出方法来改变正常执行流。

### Exception Template

> This keyword is part of Java's exception handling mechanism. It either encloses risky code, handles an exception, throws an exception, or declares that an exception may be thrown.

中文：这个关键字属于 Java 异常处理机制，可能用于包住风险代码、处理异常、抛出异常或声明可能抛出异常。

### Type Template

> This keyword defines or refers to a type. It affects what values can be stored and what operations are allowed by the compiler.

中文：这个关键字定义或引用一种类型，影响变量能存什么值，以及编译器允许哪些操作。

## 18. Special Keyword Example Bank / 特殊关键字避坑例子库

这一节专门用例子讲“看起来懂，其实很容易错”的关键字。英文考试中，题目经常给一段小代码，让你判断 compile-time error、runtime error 或输出结果。

This section gives short code examples for tricky keywords. In English exams, these often appear as code-reading questions asking for compile-time errors, runtime errors, or output.

### 18.1 `final` Examples / final 例子

#### Example 1: final primitive variable

```java
final int score = 90;
// score = 95; // Compile-time error
```

中文解释：`final` 修饰基本类型变量时，变量值只能赋一次。

English exam sentence:

> A final primitive variable cannot be reassigned after initialization.

#### Example 2: final reference is not deep immutability

```java
final ArrayList<String> names = new ArrayList<>();
names.add("Alice");          // OK
// names = new ArrayList<>(); // Compile-time error
```

中文解释：`names` 这个引用不能指向新对象，但它指向的 `ArrayList` 内容仍然可以变。

English exam sentence:

> A final reference cannot be reassigned, but the object it refers to may still be mutable.

#### Example 3: final method cannot be overridden

```java
class Parent {
    final void print() {
        System.out.println("Parent");
    }
}

class Child extends Parent {
    // void print() {} // Compile-time error
}
```

坑点：`final` 方法禁止 overriding，但不禁止 overloading。

Trap: A final method cannot be overridden, but it can still be overloaded with different parameters.

### 18.2 `static` Examples / static 例子

#### Example 1: shared static field

```java
class Counter {
    static int count = 0;

    Counter() {
        count++;
    }
}

Counter a = new Counter();
Counter b = new Counter();
System.out.println(Counter.count); // 2
```

中文解释：`count` 属于类，不属于单个对象。两个对象共享同一个 `count`。

English exam sentence:

> A static field is shared by all instances of the class.

#### Example 2: static method has no `this`

```java
class Student {
    private String name;

    static void printName() {
        // System.out.println(name); // Compile-time error
    }
}
```

中文解释：`static` 方法属于类，没有当前对象，所以不能直接访问实例字段。

English exam sentence:

> A static method has no `this` reference, so it cannot directly access instance fields.

#### Example 3: static method hiding, not overriding

```java
class A {
    static void show() {
        System.out.println("A");
    }
}

class B extends A {
    static void show() {
        System.out.println("B");
    }
}

A obj = new B();
obj.show(); // A
```

中文解释：`static` 方法根据引用类型绑定，不按对象运行时类型动态派发。这叫 method hiding，不是 overriding。

English exam sentence:

> Static methods are hidden, not overridden, and are resolved using the reference type.

### 18.3 `this` and `super` Examples / this 和 super 例子

#### Example 1: constructor chaining with `this(...)`

```java
class Book {
    private String title;
    private int year;

    Book(String title) {
        this(title, 2026);
    }

    Book(String title, int year) {
        this.title = title;
        this.year = year;
    }
}
```

中文解释：`this(...)` 调用本类另一个构造器，必须放在构造器第一行。

English exam sentence:

> `this(...)` calls another constructor in the same class and must be the first statement.

#### Example 2: explicit superclass constructor call

```java
class Person {
    Person(String name) {
    }
}

class Student extends Person {
    Student(String name) {
        super(name);
    }
}
```

中文解释：如果父类没有无参构造器，子类必须显式调用父类已有构造器。

English exam sentence:

> If the superclass has no no-argument constructor, the subclass constructor must explicitly call an existing superclass constructor.

#### Example 3: illegal constructor order

```java
class Example {
    Example() {
        // int x = 1;
        // this(10); // Compile-time error if not first statement
    }

    Example(int value) {
    }
}
```

坑点：`this(...)` 和 `super(...)` 都要求第一行，而且不能同时出现。

Trap: A constructor cannot call both `this(...)` and `super(...)` directly, because both must be first.

### 18.4 `abstract`, `interface`, and `default` Examples

#### Example 1: abstract class can have constructor

```java
abstract class Shape {
    private String color;

    Shape(String color) {
        this.color = color;
    }

    abstract double area();
}
```

中文解释：抽象类不能直接 `new`，但它可以有构造器，用于初始化子类继承来的状态。

English exam sentence:

> An abstract class cannot be instantiated, but it can have constructors used by subclasses.

#### Example 2: interface default method

```java
interface Named {
    default String getName() {
        return "Unknown";
    }
}

class Task implements Named {
}

System.out.println(new Task().getName()); // Unknown
```

中文解释：接口中的 `default` 方法有方法体，实现类可以继承，也可以重写。

English exam sentence:

> A default method in an interface provides an implementation that can be inherited or overridden.

#### Example 3: interface field is constant

```java
interface Config {
    int MAX_SIZE = 100;
}

// Config.MAX_SIZE = 200; // Compile-time error
```

中文解释：接口字段默认是 `public static final`，所以是常量。

English exam sentence:

> Fields declared in an interface are implicitly public, static, and final.

### 18.5 `instanceof` and `null` Examples

#### Example 1: null with instanceof

```java
String text = null;
System.out.println(text instanceof String); // false
```

中文解释：`null instanceof SomeType` 返回 `false`，不会抛异常。

English exam sentence:

> If the left operand of `instanceof` is null, the result is false.

#### Example 2: safe downcasting

```java
Object value = "CPT204";

if (value instanceof String) {
    String text = (String) value;
    System.out.println(text.length());
}
```

中文解释：先用 `instanceof` 判断，再强制类型转换，可以避免 `ClassCastException`。

English exam sentence:

> `instanceof` is commonly used before downcasting to avoid a `ClassCastException`.

### 18.6 `switch`, `case`, `default`, `break`, and `yield` Examples

#### Example 1: fall-through trap

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

中文解释：`case 1` 后没有 `break`，所以继续执行 `case 2`，这叫 fall-through。

English exam sentence:

> In a traditional switch statement, execution falls through to the next case unless a break is used.

#### Example 2: default does not have to be last

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

中文解释：`default` 可以不放最后。switch 会先寻找匹配的 `case`。

English exam sentence:

> The default label does not have to be the last label in a switch statement.

#### Example 3: switch expression with `yield`

```java
int score = 85;

String grade = switch (score / 10) {
    case 10, 9 -> "A";
    case 8 -> {
        yield "B";
    }
    default -> "C";
};
```

中文解释：新版 `switch expression` 可以产生一个值。代码块分支里用 `yield` 返回该分支的值。

English exam sentence:

> `yield` is used to produce a value from a block in a switch expression.

### 18.7 Loop Control Examples / 循环控制例子

#### Example 1: `break` vs `continue`

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

中文解释：`continue` 跳过 i=3 这一轮；`break` 在 i=5 时结束整个循环。

English exam sentence:

> `continue` skips the current iteration, while `break` terminates the loop.

#### Example 2: while may run zero times, do-while runs once

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

中文解释：`while` 先判断，所以可能 0 次；`do-while` 先执行，所以至少 1 次。

English exam sentence:

> A while loop may execute zero times, but a do-while loop executes at least once.

### 18.8 `return`, `try`, `catch`, `finally`, `throw`, `throws` Examples

#### Example 1: finally runs before method actually returns

```java
static int test() {
    try {
        return 1;
    } finally {
        System.out.println("finally");
    }
}
```

Output:

```text
finally
```

Return value:

```text
1
```

中文解释：`try` 中已经准备 return，但真正返回前通常还会执行 `finally`。

English exam sentence:

> A finally block usually executes before the method actually returns.

#### Example 2: finally return overrides try return

```java
static int badExample() {
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

中文解释：`finally` 里的 return 会覆盖 `try` 里的 return。这是合法但非常不推荐的写法。

English exam sentence:

> A return statement in a finally block can override a return value from the try block, but this is bad practice.

#### Example 3: `throw` vs `throws`

```java
static void setAge(int age) throws IllegalArgumentException {
    if (age < 0) {
        throw new IllegalArgumentException("Age cannot be negative");
    }
}
```

中文解释：`throws` 在方法签名上声明可能抛异常；`throw` 在方法体内真正抛出异常对象。

English exam sentence:

> `throw` actually throws an exception object, while `throws` declares that a method may throw an exception.

### 18.9 `assert` Examples

```java
int size = -1;
assert size >= 0 : "Size must not be negative";
```

中文解释：默认运行时断言不启用，这行可能什么都不做。需要用 `java -ea ClassName` 开启。

English exam sentence:

> Assertions are disabled by default and should be used for debugging internal assumptions, not for normal input validation.

坑点：不要用 `assert` 检查用户输入，例如登录密码、菜单选择、文件路径等。

Trap: Do not rely on `assert` for user input validation, because assertions may be disabled.

### 18.10 `enum` Examples

#### Example 1: enum in switch

```java
enum Priority {
    HIGH, MEDIUM, LOW
}

Priority priority = Priority.HIGH;

switch (priority) {
    case HIGH:
        System.out.println("Do it now");
        break;
    case MEDIUM:
        System.out.println("Do it soon");
        break;
    case LOW:
        System.out.println("Do it later");
        break;
}
```

中文解释：`case` 中写 `HIGH`，不是 `Priority.HIGH`。

English exam sentence:

> In a switch over an enum, case labels use the enum constants without the enum type name.

#### Example 2: enum cannot be created with new

```java
// Priority p = new Priority(); // Compile-time error
```

中文解释：枚举常量是固定的，不能用 `new` 创建新的枚举值。

English exam sentence:

> Enum constants are fixed, and enum objects cannot be created with `new`.

### 18.11 `var` Examples

#### Example 1: inferred static type

```java
var number = 10;
// number = "ten"; // Compile-time error
```

中文解释：`var` 不是动态类型。编译器已经把 `number` 推断为 `int`。

English exam sentence:

> `var` performs compile-time type inference; it does not make Java dynamically typed.

#### Example 2: illegal uses of var

```java
// var value;        // Compile-time error: no initializer
// var text = null;  // Compile-time error: cannot infer type

class Example {
    // var field = 10; // Compile-time error: var cannot be used for fields
}
```

中文解释：`var` 必须用于有初始化表达式的局部变量。

English exam sentence:

> `var` can only be used for local variables with an initializer.

### 18.12 `record` Examples

#### Example 1: generated accessors

```java
record Point(int x, int y) {
}

Point p = new Point(3, 4);
System.out.println(p.x()); // 3
```

中文解释：record 的访问器是 `x()`，不是 `getX()`。

English exam sentence:

> A record automatically generates accessor methods with the same names as its components.

#### Example 2: shallow immutability

```java
record StudentNames(ArrayList<String> names) {
}

ArrayList<String> list = new ArrayList<>();
StudentNames data = new StudentNames(list);
list.add("Alice"); // data.names() can observe the change
```

中文解释：record 的字段引用是 final，但如果引用对象本身可变，内容仍可能改变。这叫 shallow immutability。

English exam sentence:

> Records are shallowly immutable; mutable objects stored inside a record may still be modified.

### 18.13 `sealed`, `permits`, and `non-sealed` Examples

```java
sealed class Shape permits Circle, Rectangle {
}

final class Circle extends Shape {
}

non-sealed class Rectangle extends Shape {
}

class Square extends Rectangle {
}
```

中文解释：`Shape` 只允许 `Circle` 和 `Rectangle` 直接继承。`Circle` 是 final，不能再被继承；`Rectangle` 是 non-sealed，所以 `Square` 可以继承它。

English exam sentence:

> A sealed class restricts its direct subclasses, and each permitted subclass must be final, sealed, or non-sealed.

坑点：`permits` 列的是直接子类，不是所有后代类。

Trap: `permits` lists direct subclasses only, not every descendant class.

### 18.14 `transient` Examples

```java
class User implements java.io.Serializable {
    private String username;
    private transient String password;
}
```

中文解释：使用 Java 默认序列化时，`password` 字段不会被保存。

English exam sentence:

> A transient field is skipped by Java's default serialization mechanism.

坑点：`transient` 不会让字段在普通程序运行中消失，它只影响序列化。

Trap: `transient` does not remove the field from the object during normal execution; it only affects serialization.

### 18.15 `volatile` and `synchronized` Examples

#### Example 1: volatile visibility

```java
class Worker {
    private volatile boolean running = true;

    void stop() {
        running = false;
    }
}
```

中文解释：`volatile` 让其他线程更可靠地看到 `running` 的最新值。

English exam sentence:

> `volatile` guarantees visibility of changes across threads.

#### Example 2: volatile is not atomic

```java
class Counter {
    volatile int count = 0;

    void increment() {
        count++; // Not atomic
    }
}
```

中文解释：`count++` 包含读、加、写多个步骤，`volatile` 不能保证它整体不可分割。

English exam sentence:

> `volatile` does not make compound operations atomic.

#### Example 3: synchronized mutual exclusion

```java
class SafeCounter {
    private int count = 0;

    synchronized void increment() {
        count++;
    }
}
```

中文解释：`synchronized` 保证同一时间只有一个线程执行这个方法。

English exam sentence:

> `synchronized` provides mutual exclusion by allowing only one thread to enter the synchronized method for the same object.

### 18.16 `package` and `import` Examples

#### Example 1: correct order

```java
package study.notes;

import java.util.ArrayList;

public class Example {
}
```

中文解释：顺序必须是 `package`，然后 `import`，然后类定义。

English exam sentence:

> The package declaration must appear before import declarations and class declarations.

#### Example 2: wildcard import is not recursive

```java
import java.util.*;

// ConcurrentHashMap map; // Compile-time error unless java.util.concurrent is imported
```

中文解释：`import java.util.*` 不会导入 `java.util.concurrent.*`。

English exam sentence:

> A wildcard import does not import classes from subpackages.

### 18.17 `const`, `goto`, and `_` Examples

```java
// int const = 1; // Compile-time error
// int goto = 2;  // Compile-time error
// int _ = 3;     // Compile-time error in modern Java
```

中文解释：`const` 和 `goto` 是 Java 保留但不用的关键字；单独 `_` 在现代 Java 中不能当变量名。

English exam sentence:

> `const` and `goto` are reserved but unused in Java, and `_` cannot be used as a single identifier in modern Java.

### 18.18 Generics Boundary Examples Using `extends` and `super`

这里的 `extends` 和 `super` 不是普通类继承，而是泛型通配符边界。

#### Example 1: `? extends Number` is mainly for reading

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

中文解释：`? extends Number` 表示某种 Number 子类型列表，可以安全读出 Number，但不能安全添加 Integer。

English exam sentence:

> `? extends T` is mainly used when the structure produces values to be read as T.

#### Example 2: `? super Integer` is mainly for writing

```java
static void addIntegers(List<? super Integer> values) {
    values.add(10);
    values.add(20);
    Object first = values.get(0);
}
```

中文解释：`? super Integer` 可以安全写入 Integer，但读出来通常只能当 Object。

English exam sentence:

> `? super T` is mainly used when the structure consumes values of type T.

Memory phrase:

```text
PECS = Producer Extends, Consumer Super
```

### 18.19 `native` and `strictfp` Recognition Examples

```java
class NativeExample {
    native void runNativeCode();
}

strictfp class FloatingPointExample {
    double calculate(double a, double b) {
        return a / b;
    }
}
```

中文解释：这两个关键字课程中通常不写代码实现，但要认得它们是 Java keywords。

English exam sentence:

> `native` and `strictfp` are valid Java keywords, although they are uncommon in typical data-structure programs.

### 18.20 Mini Output Traps / 小型输出题陷阱

#### Trap 1: integer division

```java
System.out.println(5 / 2);   // 2
System.out.println(5 / 2.0); // 2.5
```

Reason:

> If both operands are integers, integer division is performed.

#### Trap 2: char arithmetic

```java
char c = 'A';
System.out.println(c + 1);       // 66
System.out.println((char)(c + 1)); // B
```

Reason:

> A char is promoted to int during arithmetic operations.

#### Trap 3: string concatenation

```java
System.out.println("A" + 1 + 2); // A12
System.out.println(1 + 2 + "A"); // 3A
```

Reason:

> Evaluation is left to right, and `+` performs string concatenation if one operand is a String.

## 19. High-Frequency Exam Phrases / 英文考试高频表达

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

## 20. Common Exam Traps / 常见考试陷阱

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

## 21. One-Page Priority List / 考前优先背诵清单

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
