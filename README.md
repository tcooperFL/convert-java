# Code Challenge: String Conversion

Parse the given string of words, whitespace, and parentheses. Output each word on a separate line,
with hyphen indentation corresponding to the parenthesis nesting level.

Bonus problem:
Output the lines in word-sorted order.

Author: Tom Cooper
Date: 2017-01-06

## Prerequisites

* Java 8 JDK - on Windows be sure that %JDK_HOME%\bin is on your path.

## Building

For this solution, I tried to minimize any dependencies on OS or IDE.

### 1. Compile all the java files, placing all class files in the ``out`` folder, which is also the classpath.

OS | Command
---|---
Mac | ```javac -d out -cp out $(find src -name "*.java")```
Windows | ```dir /s/B *.java > sources_list.txt``` <br> ```javac -d out -cp out @sources_list.txt```

### 2. Create an executable jar out of the compiled class file folder, specifying the main entry point.

#### Mac and Windows
```
jar cvfe convert-java.jar com.frontlineed.challenge.Main -C out com
```

## Running the solution

Run the executable jar file. The ``-ea`` option turns on assertion checking.
If you leave out that option the assertions will be ignored.

#### Mac and Windows
```
java -ea -jar convert-java.jar
```

This demonstrates the given example. Test it on your own strings by giving it
a string argument.

```
java -ea -jar convert-java.jar "(a,b(foo(((deep stuff)))), c)"
```

Output:

```
Original order:
a
b
- foo
---- deep
---- stuff
c

Sorted order:
a
b
c
---- deep
- foo
---- stuff
```
