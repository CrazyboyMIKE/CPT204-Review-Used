# CPT204 TTL Answers 中文总目录

这个文件夹把 `CPT204TTL` 中每周 TTL/Lab 题目整理成独立 Java 学习项目。每个项目都有：

- `src/WeekXXAnswers.java`：本周题目的 Java 答案代码。
- `学习说明.md`：中文版学习说明，解释题目思路、核心知识点和常考点。

Java 文件中，类名、方法名、变量名和输出文本都使用英文；中文解释只写在注释中。

## 项目列表

| 周次 | 项目目录 | 内容 |
|---|---|---|
| Week 01 | `week01-oop-review` | 数组、连续段、StopWatch、Student、Book、Stock |
| Week 02 | `week02-oop-inheritance` | MyPoint、Circle2D、BigInteger、继承、多态、Tax |
| Week 03 | `week03-abstract-interface` | 抽象类、接口、Comparable、深拷贝、Number |
| Week 04 | `week04-generics` | 泛型方法、通配符、Pair、copy、GenericStack |
| Week 05 | `week05-list-queue-stack-priority` | Todo 系统：List、Queue、Stack、PriorityQueue |
| Week 06 | `week06-set-map-tags` | Todo 标签：Set、Map、频率统计、排序视图 |
| Week 08 | `week08-efficient-todo` | Todo 效率优化、索引结构、Big O 分析 |
| Week 09 | `week09-sorting` | Bubble、Heap、Quick Sort、Merge Sort、Insertion Sort |
| Week 10 | `week10-graphs` | 图概念、邻接矩阵、Prim、Dijkstra、路径恢复 |
| Week 11 | `week11-bst` | BST 合法性、搜索、遍历、计数、删除 |
| Week 12 | `week12-avl-hashing` | AVL 旋转、balancePath、线性/二次/双重哈希 |

## 单独运行某一周

以 Week 09 为例：

```bash
cd "week09-sorting"
javac -encoding UTF-8 src/Week09Answers.java
java -cp src Week09Answers
```

其他周只需要把文件名和类名替换为对应的 `WeekXXAnswers`。
