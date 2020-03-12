# NYU CS9053 - Fall 2019 - Section I

## Introduction to Java

### Instructor

Brian Langel


### Course Description
* An introduction to the Java programming language. See [Lectures](#lectures) for topics covered.
* This is *not* an introduction to programming, data structures, algorithms or other computer science topics. It is expected that the student have experience in at least one programming language prior to taking this course. This course will cover Java specific solutions to common algorithms, data structures, concurrency problems and other computer science related topics. Although not a strict prerequisite, it is assumed that the student have taken undergraduate level courses in data structures and algorithms.   
* The Java ecosystem is large and many topics will not be covered; including, _JDBC_, _EJB_, _Swing_, _JSF_, _JNI_, and _Java EE_ topics

### Textbook
* Core Java, volume one, 10th ed.; Cay Horstmann. __ISBN-13 978-0134177304__

### Recommended Textbooks
Although these two books are not required they will be referenced extensively throughout the course. If you plan on programming in Java I highly recommend purchasing these books.

* Java Concurrency in Practice; Brian Goetz et al. __ISBN-13 978-0321349606__
* Effective Java, 2nd ed.; Joshua Bloch __ISBN-13 978-0321356680__

### Java Version ###
* All homework and exams will be graded according to Java 8, i.e. [JLS v8](https://docs.oracle.com/javase/specs/jls/se8/html/index.html)

### Purpose
The goal of this course is to teach you a pragmatic understanding of the Java programming language. It will avoid the esoteric, the rarely used and the vestigial aspects of the language and the Java ecosystem at large (e.g., _Java EE_)     


### Lectures

| Lecture | Date | Topic | Reading (chapters) |
| :-----: | :--: | :-----: | :------------------: |
| 1 | 9/3 | Introduction / Basics | 1 & 2 & 13.1 |
| 2 | 9/10 | Procedural Java | 3 |
| 3 | 9/17 | Objects | 4 |
| 4 | 9/24 | Inheritance | 5 (*not 5.3 or 5.7*) |
| 5 | 10/1 | Interfaces / Nested & Inner Classes | 6 (*not 6.3 or 6.5*) |
| 6 | 10/8 | Exceptions / Debugging / Annotations & Regular Expressions | 7 & [supplemental] |
| - | 10/15 | Fall Break | - |
| - | 10/22 | __Midterm__ (see [Exams](#exams)) | - |
| 7 | 10/29 | Generics | 8 (*not 8.9*) |
| 8 | 11/5 | Collections | 9 & 5.3 |
| 9 | 11/12 | Concurrency | 14 & Goetz (*not 14.11*) |
| 10 | 11/19 | Concurrency | 14 & Goetz (*not 14.11*) |
| 11 | 11/26 | IO/NIO | [supplemental] |
| 12 | 12/3 | Functional Java (Lambdas / Streams / etc) | 6.3 & [supplemental] |
| 13 | 12/10 | Libraries (Guava / Jackson) / Testing (Junit / Mockito) / IDEs / Patterns (Builder, Dependency Injection, etc) | |
| - | 12/17 | __Final__ (see [Exams](#exams)) | - |


### Exams

There will be __2__ exams in total.  
 
* __Midterm__ - this is an in-class exam. It will be computer usage.
* __Final__ - this is an in-class exam. It will be slightly longer than the midterm and will also be computer usage.


### Homework Grading Policy

Every homework will be evaluated under the following policy:

* Style (1 - 5) - 10%
    - This is related to how your name classes, variables and how well you follow the [Java Code Conventions](http://www.oracle.com/technetwork/java/javase/documentation/codeconvtoc-136057.html)
* Immutability (0 or 5) - 10%
    - This is whether you program with immutable data.  If all data is immutable you get a 5 if one or more portions of your code uses mutable data you get a 0 for this portion of the grade.
* Repeating Past Mistakes (0 or 5) - 10%
    - This is whether or not you fix past mistakes going forward.  E.g. if you used mutable data in the past homework and I commented about this and you continue to use mutable data you will get a 0 for this portion of the grade.
* Git Usage (0 or 5) - 10%
    - This is whether you properly use Git/GitHub.  You should not have merge conflicts submitted in your homework.  You should not submit `.class` files or IDE files.
    - NOTE, this is separate from submitting code on time.  Only code prior to _3pm_ of the deadline (with a commit id in the Google Spreadsheet) will be reviewed and if you do not push your code for review within _24hr_ of the deadline you will get a 0 for the entire homework.
* Organization (1 - 5) - 20%
    - This is how well you organize your code.  Is it readable and maintainable?
* Correctness (1 - 5) - 40%
    - This is whether your code fulfills the specifications of the homework.  E.g. does it compile? Does it work?  Does it pass test cases if present? Etc.

### Supplemental Reading

In addition to the [Textbook](#textbook) and the [Recommended Textbooks](#recommended-textbooks) I'd also suggest you read the following:

* Core Java, volume two, Advanced Features, 10th ed.; Cay Horstmann and Gary Cornell. __ISBN-13 978-0134177298__
    - We will be referencing the second volume in lectures 6 and 12
    - Many of the features (like _JDBC_, etc) we will not get into but this is still a good book to own and reference
* The Java Programming Language, 4th ed; Ken Arnold, James Gosling & David Holmes; __ISBN-13 978-0321349804__
    - Great introduction to the language and surprisingly approachable 
* Java Puzzlers; Joshua Bloch & Neal Gafter __ISBN-13 978-0321336781__
    - Fun read and the first lecture will include a couple examples of which for illustrative purposes
    - Many of the puzzles deal with very esoteric aspects of the language but many of them are also _gotchyas_ of which Java programmers should be aware
* The Well-Grounded Java Developer: Vital techniques of Java 7 and polyglot programming; Benjamin J Evans & Martijn Verburg __ISBN-13 978-1617290060__
    - Geared toward Java 7 in particular. I'd recommend you read this after taking this class. It will reinforce a lot of what you should learn in this class.
* Java In A Nutshell, 5th ed; David Flanagan __ISBN-13 978-0596007737__
    - Decent overview of the language. Not as good as _Core Java_ but still worth reading

Online Resources

* Java 8 Documentation - http://docs.oracle.com/javase/8/docs/api/
    - Mostly will be referenced by you via _Google_ searches but still good to browse through proactively to understand how __Javadoc__ structures documentation in general
* Java Tutorials - http://docs.oracle.com/javase/tutorial
    - Fairly good tutorials that are practical and pointed. I would recommend doing most of these.
* The Java Language Specification - http://docs.oracle.com/javase/specs
    - For a rainy day...
* Angelika Langer's FAQ on Java Generics - http://www.angelikalanger.com/GenericsFAQ/JavaGenericsFAQ.html
    - Extremely great FAQ about generics added in Java 5. This is my go-to when I'm twisting my head around edge cases with generics.
* John Sterling's [Java for C++ Programmers](http://cse.poly.edu/jsterling/cs9053/Notes/JavaForC++Programmer.html)
    - A great reference point for those coming to Java from C++. It's written by an NYU professor who has taught CS9053 for years.
* StackOverflow - http://stackoverflow.com
    - The best place to find and post questions to Java problems. I'm guessing you'll be spending a lot of time on this site while learning Java. 