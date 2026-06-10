/*
 * CPT204 Java Keywords Chinese Guide
 *
 * 这份 .java 文件的作用不是写业务程序，而是用“中文注释”系统复习 Java 关键字。
 * 代码部分只保留一个可以编译的空类，真正的知识点全部写在注释里。
 *
 * 说明：
 * 1. Java keyword 是语言保留字，不能作为变量名、方法名、类名使用。
 * 2. true、false、null 严格说是字面量，不是普通关键字，但同样不能当标识符使用。
 * 3. const 和 goto 是 Java 保留字，但 Java 没有实际使用它们。
 * 4. var、record、yield、sealed、permits、non-sealed 等和 Java 版本有关，常被称为
 *    restricted keyword、contextual keyword 或 reserved type name。课程中一般不会深入，
 *    但阅读现代 Java 代码时可能遇到。
 */
public final class Java_Keywords_Chinese_Guide {
    private Java_Keywords_Chinese_Guide() {
        // 这个私有构造器用于防止创建工具说明类对象。
    }
}

/*
 * =========================
 * 一、访问控制关键字
 * =========================
 *
 * public
 * - 表示公开访问。
 * - 修饰 class 时，文件名必须和 public class 名相同。
 * - 修饰 method 或 field 时，任何包中的代码都可以访问。
 * - 课程常见场景：public class Main、public static void main。
 *
 * private
 * - 表示只能在当前类内部访问。
 * - 是封装 encapsulation 的核心关键字。
 * - 字段通常设为 private，再提供 getter/setter 控制访问。
 * - 课程常见场景：private int size; private String name;
 *
 * protected
 * - 表示同一个包内可访问，或者不同包中的子类可访问。
 * - 常用于继承体系中让子类访问父类成员。
 * - 注意：protected 不是“只有子类可访问”，同包类也可以访问。
 */

/*
 * =========================
 * 二、类、对象、继承、接口
 * =========================
 *
 * class
 * - 用来定义类。
 * - 类是对象的模板，描述对象有哪些字段和方法。
 * - 一个源文件最多只能有一个 public 顶级类。
 *
 * interface
 * - 用来定义接口。
 * - 接口表达“能力”或“规范”，例如 Comparable、Iterable。
 * - 接口中的抽象方法默认是 public abstract。
 * - 接口中的字段默认是 public static final。
 *
 * extends
 * - 表示继承。
 * - class A extends B：A 是 B 的子类。
 * - interface A extends B：接口 A 继承接口 B。
 * - Java 类只能单继承一个父类，但接口可以多继承接口。
 *
 * implements
 * - 表示一个类实现接口。
 * - class Student implements Comparable<Student> 表示 Student 必须实现 compareTo。
 * - 一个类可以 implements 多个接口。
 *
 * abstract
 * - 可修饰类或方法。
 * - abstract class 不能直接 new，只能由子类继承。
 * - abstract method 没有方法体，子类必须实现，除非子类也声明为 abstract。
 * - 抽象类适合保存共同字段和部分共同实现。
 *
 * final
 * - 修饰变量：变量只能赋值一次。
 * - 修饰方法：方法不能被子类重写。
 * - 修饰类：类不能被继承，例如 String。
 * - final 引用变量不能换引用，但引用对象内部状态可能仍可变。
 *
 * new
 * - 创建对象或数组。
 * - new Student() 会在堆内存中创建对象，并调用构造器初始化。
 *
 * this
 * - 表示当前对象。
 * - 常用于区分字段和参数：this.name = name。
 * - this(...) 可以在构造器中调用本类另一个构造器，且必须放第一行。
 *
 * super
 * - 表示父类部分。
 * - super(...) 调用父类构造器，必须放在子类构造器第一行。
 * - super.methodName() 调用父类被重写的方法。
 *
 * instanceof
 * - 判断对象运行时类型。
 * - obj instanceof Student 表示 obj 是否是 Student 或其子类对象。
 * - 常用于向下转型前的安全检查。
 */

/*
 * =========================
 * 三、基本数据类型关键字
 * =========================
 *
 * boolean
 * - 只有 true 和 false 两个值。
 * - 常用于 if、while、for 的条件表达式。
 *
 * byte
 * - 8 位整数，范围 -128 到 127。
 * - 常用于二进制数据、文件、网络传输。
 *
 * short
 * - 16 位整数，范围 -32768 到 32767。
 * - 课程中较少使用。
 *
 * int
 * - 32 位整数，是最常用的整数类型。
 * - 数组下标、循环变量、计数器通常用 int。
 *
 * long
 * - 64 位整数。
 * - 数值后可加 L，例如 10000000000L。
 * - 仍然放不下任意大整数，超大整数要用 BigInteger。
 *
 * float
 * - 32 位浮点数。
 * - 数值后要加 f 或 F，例如 3.14f。
 *
 * double
 * - 64 位浮点数，是 Java 默认小数类型。
 * - 数学计算通常用 double。
 *
 * char
 * - 表示单个 Unicode 字符。
 * - 使用单引号，例如 'A'。
 *
 * void
 * - 表示方法没有返回值。
 * - public static void main 中的 void 表示 main 方法不返回数据。
 */

/*
 * =========================
 * 四、条件、分支、循环
 * =========================
 *
 * if
 * - 条件判断。
 * - 条件必须是 boolean 表达式。
 *
 * else
 * - 和 if 搭配，表示条件不成立时执行的分支。
 * - else if 本质上是 else 后面又接了一个 if。
 *
 * switch
 * - 多分支选择。
 * - 可用于 int、char、String、enum 等类型。
 * - 传统 switch 常和 case、break、default 搭配。
 *
 * case
 * - switch 中的某一个匹配分支。
 * - 传统 switch 如果没有 break，可能继续执行后面的 case，这叫 fall-through。
 *
 * default
 * - switch 中没有任何 case 匹配时执行。
 * - 接口中 default 也可以表示默认方法，这是 Java 8 引入的接口能力。
 *
 * for
 * - 循环关键字。
 * - 普通 for 适合计数循环。
 * - enhanced for 适合遍历数组或集合，例如 for (int value : values)。
 *
 * while
 * - 先判断条件，再执行循环体。
 * - 如果条件一开始就是 false，循环体一次都不会执行。
 *
 * do
 * - 和 while 组成 do-while。
 * - 先执行循环体，再判断条件，因此至少执行一次。
 *
 * break
 * - 立即跳出当前循环或 switch。
 * - 只跳出最内层结构，除非使用 label，但课程一般不推荐 label。
 *
 * continue
 * - 跳过本轮循环剩余部分，进入下一轮循环判断。
 * - 常用于过滤不需要处理的元素。
 *
 * return
 * - 从方法返回。
 * - 有返回值的方法必须 return 对应类型的值。
 * - void 方法可以直接 return; 提前结束。
 */

/*
 * =========================
 * 五、异常处理
 * =========================
 *
 * try
 * - 包住可能抛出异常的代码。
 * - 可以搭配 catch、finally 或 try-with-resources。
 *
 * catch
 * - 捕获并处理异常。
 * - catch 的异常类型应该从具体到宽泛排列。
 *
 * finally
 * - 无论是否发生异常，通常都会执行。
 * - 常用于释放资源。
 * - 如果 JVM 被强制终止，finally 不一定执行。
 *
 * throw
 * - 主动抛出一个异常对象。
 * - 例如 throw new IllegalArgumentException("Invalid input");
 *
 * throws
 * - 写在方法声明上，表示方法可能把异常抛给调用者处理。
 * - checked exception 通常需要 catch 或 throws。
 *
 * assert
 * - 断言，用于调试时检查程序内部假设。
 * - 默认不启用，需要运行时加 -ea。
 * - 不应该用 assert 代替正式输入校验。
 */

/*
 * =========================
 * 六、包、导入与枚举
 * =========================
 *
 * package
 * - 声明当前类属于哪个包。
 * - 必须放在源文件非注释代码的第一行。
 * - 包名通常全小写。
 *
 * import
 * - 导入其他包中的类，避免写完整限定名。
 * - java.lang 包自动导入，不需要 import String 或 Math。
 *
 * enum
 * - 定义枚举类型。
 * - 枚举适合表示有限固定取值，例如 HIGH、MEDIUM、LOW。
 * - enum 本质上是一种特殊类，可以有字段、构造器、方法。
 */

/*
 * =========================
 * 七、修饰符与并发相关关键字
 * =========================
 *
 * static
 * - 表示属于类，而不是属于某个对象。
 * - static field 被所有对象共享。
 * - static method 可以通过类名调用，不能直接访问非 static 成员。
 * - main 方法必须是 static，因为 JVM 启动时还没有创建对象。
 *
 * synchronized
 * - 用于多线程同步。
 * - 保证同一时间只有一个线程进入被同一把锁保护的代码。
 * - 课程如果不涉及并发，一般只需知道它和线程安全有关。
 *
 * volatile
 * - 用于多线程可见性。
 * - 一个线程修改 volatile 变量，其他线程能更快看到最新值。
 * - volatile 不等于原子性，count++ 仍然不是线程安全操作。
 *
 * transient
 * - 和对象序列化有关。
 * - 被 transient 修饰的字段不会被默认序列化。
 *
 * native
 * - 表示方法由非 Java 语言实现，例如 C/C++。
 * - 方法只有声明，没有 Java 方法体。
 *
 * strictfp
 * - 用于限制浮点计算遵守严格标准。
 * - 现代 Java 中很少手写，课程一般只需认识。
 */

/*
 * =========================
 * 八、保留但不用的关键字
 * =========================
 *
 * const
 * - Java 保留了 const，但不使用。
 * - Java 中定义常量使用 static final。
 *
 * goto
 * - Java 保留了 goto，但不使用。
 * - Java 用 break、continue、return 控制流程，不鼓励任意跳转。
 */

/*
 * =========================
 * 九、字面量与特殊保留词
 * =========================
 *
 * true
 * - boolean 真值字面量。
 *
 * false
 * - boolean 假值字面量。
 *
 * null
 * - 空引用字面量，表示引用变量没有指向任何对象。
 * - 对 null 调用方法会产生 NullPointerException。
 */

/*
 * =========================
 * 十、现代 Java 中容易混淆的词
 * =========================
 *
 * var
 * - Java 10 引入的局部变量类型推断。
 * - var 不是普通关键字，而是 reserved type name。
 * - 只能用于局部变量，不能用于字段、方法参数、返回值。
 * - 编译器仍然会推断出静态类型，不是 JavaScript 那种动态类型。
 *
 * record
 * - Java 16 正式引入。
 * - 用于声明主要保存数据的不可变数据载体。
 * - 编译器自动生成构造器、访问器、equals、hashCode、toString。
 *
 * yield
 * - 用于 switch expression 中返回某个分支的值。
 * - 它是 restricted identifier，只有特定语境下有特殊含义。
 *
 * sealed
 * - 用于限制哪些类可以继承当前类或实现当前接口。
 * - 通常和 permits 搭配。
 *
 * permits
 * - 在 sealed class 或 sealed interface 后面列出允许的直接子类。
 *
 * non-sealed
 * - 用在 sealed 体系的子类上，表示重新开放继承。
 *
 * module
 * - Java 9 模块系统使用。
 * - 通常出现在 module-info.java 文件中。
 *
 * requires
 * - 模块系统中声明当前模块依赖另一个模块。
 *
 * exports
 * - 模块系统中声明哪些包对外可见。
 *
 * opens
 * - 模块系统中允许反射访问某个包。
 *
 * open
 * - 声明开放模块，允许反射访问模块内所有包。
 *
 * uses
 * - 模块系统中声明当前模块使用某个服务。
 *
 * provides
 * - 模块系统中声明当前模块提供某个服务实现。
 *
 * with
 * - 和 provides 搭配，指出服务实现类。
 *
 * to
 * - 在 exports ... to 或 opens ... to 中限制目标模块。
 *
 * transitive
 * - 在 requires transitive 中使用，表示依赖可以传递给依赖当前模块的其他模块。
 *
 * _
 * - 单独下划线从 Java 9 开始不能作为标识符使用。
 * - 现代 Java 中下划线常与未命名变量或模式匹配语法相关。
 */

/*
 * =========================
 * 十一、课程高频组合
 * =========================
 *
 * public static void main(String[] args)
 * - public：JVM 可以从类外访问。
 * - static：不创建对象也能调用。
 * - void：main 不返回值。
 * - String[] args：命令行参数数组。
 *
 * private final field
 * - private 保证封装。
 * - final 保证字段引用初始化后不可再改。
 * - 常用于不可变对象设计。
 *
 * class SubClass extends SuperClass implements InterfaceName
 * - extends 表示继承父类。
 * - implements 表示实现接口。
 * - Java 类只能 extends 一个类，但可以 implements 多个接口。
 *
 * try-catch-finally
 * - try 放可能出错的代码。
 * - catch 处理异常。
 * - finally 做清理。
 *
 * switch-case-default-break
 * - switch 选择变量。
 * - case 写分支。
 * - default 兜底。
 * - break 防止传统 switch 继续向下执行。
 */
