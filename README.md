# CPT204 Final Study Project

This repository contains CPT204 Java and data-structures study materials, bilingual revision notes, and runnable Java answer projects generated from the TTL/Lab files.

## Contents

- `CPT204_中英双语复习纲领.md`  
  Full Chinese-English revision outline for the course.

- `Java关键字完整中文讲解.md`  
  Bilingual Java keyword guide with Chinese explanations and English exam-ready sentences.

- `Java_Keywords_Chinese_Guide.java`  
  Java keyword guide written as detailed Chinese comments in a compilable Java file.

- `exam-algorithms-java/`  
  Standalone Java algorithm review files for common exam algorithms.

- `CPT204TTL/`  
  Original TTL/Lab source files.

- `CPT204TTL-Answers/`  
  Weekly independent Java answer projects, each with source code and a Chinese Markdown study guide.

## Run Java Files

Example:

```bash
cd CPT204TTL-Answers/week09-sorting
javac -encoding UTF-8 src/Week09Answers.java
java -cp src Week09Answers
```

For the algorithm review files:

```bash
cd exam-algorithms-java
javac -encoding UTF-8 SortingAlgorithms.java
java SortingAlgorithms
```

## GitHub Note

`Week5-HD.pdf` and `Week6-HD.pdf` are not committed because each file is larger than GitHub's 100 MB file limit. Keep them locally, or use Git LFS if you need to upload them.
