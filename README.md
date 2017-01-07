# Code Challenge: String Conversion

Parse the given string of words, whitespace, and parentheses. Output each word on a separate line,
with hyphen indentation corresponding to the parenthesis nesting level.

Bonus problem:
Output the lines in word-sorted order.

Author: Tom Cooper
Date: 2017-01-06

## Prerequisites

* Java 8 JDK

## Building

Compile all the java files, placing all class files in the ``out`` folder, which is also the classpath.

```find src -name "*.java" -exec javac -d out -cp out {} \;```

Create an executable jar out of the compiled class file folder, specifying the main entry point.

```jar cvfe convert-java.jar com.frontlineed.challenge.Main -C out com```

## Running the solution

Run the executable jar file. The ``-ea`` option turns on assertion checking.
If you leave out that option the assertions will be ignored.

```java -ea -jar conver-java.jar```
