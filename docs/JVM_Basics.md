
# JVM Basics – LearnTrack

## 1. What is Java?

Java is a high-level, object-oriented programming language.

Java follows the principle:

**Write Once, Run Anywhere (WORA).**

Java source code is compiled into bytecode, which can run on any system that has a compatible Java Virtual Machine (JVM).

## 2. JDK, JRE, and JVM

### JDK – Java Development Kit

The JDK provides the tools required to develop Java applications.

It includes:
- Java compiler (`javac`)
- Java runtime components
- Development tools

### JRE – Java Runtime Environment

The JRE provides the environment required to run Java applications.

It includes the JVM and supporting libraries.

### JVM – Java Virtual Machine

The JVM executes Java bytecode.

Its responsibilities include:
- Loading class files
- Verifying bytecode
- Executing code
- Managing memory
- Garbage collection

## 3. How Java Code Executes

Java execution happens in the following steps:

1. Write Java source code in a `.java` file.
2. Compile the source code using the Java compiler (`javac`).
3. The compiler generates bytecode in a `.class` file.
4. The JVM loads and executes the bytecode.

Example:

```bash
javac HelloWorld.java
java HelloWorld
```

## 4. Java Architecture

The Java execution process can be represented as:

```text
Java Source Code (.java)
          |
          v
    Java Compiler
       (javac)
          |
          v
   Bytecode (.class)
          |
          v
         JVM
          |
          v
    Machine Execution
```

## 5. JVM Memory Areas

The JVM uses different memory areas while executing a Java application.

### Heap Memory
- Stores objects and arrays.
- Shared across threads.
- Managed by the garbage collector.

### Stack Memory
- Stores method calls and local variables.
- Each thread has its own stack.
- Memory is released as method calls complete.

### Method Area
- Stores class-level information, such as class metadata and runtime constant-pool information.

### Program Counter (PC) Register
- Keeps track of the current JVM instruction being executed by a thread.

### Native Method Stack
- Supports execution of native methods written in languages such as C or C++.

## 6. Garbage Collection

Garbage Collection (GC) is the JVM process of identifying and reclaiming heap memory occupied by objects that are no longer reachable.

Example:

```java
Student student = new Student(101, "John", "Doe", "Batch A");

student = null;
```

After assigning `null`, the object may become eligible for garbage collection if no other references point to it.

Garbage collection timing is managed by the JVM.

## 7. JVM in LearnTrack

In the LearnTrack application:

- Java source files such as `Student.java` and `Course.java` are compiled into bytecode.
- The JVM executes the compiled classes.
- Student, Course, and Enrollment objects are created and stored in heap memory.
- Local variables and method calls use stack memory.
- Objects that are no longer reachable may eventually be reclaimed by garbage collection.
