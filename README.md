# bytecode-manipulators

This project shows how three different bytecode manipulation libraries can be used to accomplish the same task.

These examples take the `MyBankAccount` class, and add the `LogTransaction` annotation to the `adjustAcountBalance(BigDecimal amount)` method.  This annotation allows the library to enhance the bytecode of the class in a way that will log each transaction to standard output.  Each library has a different way of accomplishing this task.  These examples are far from perfect or flexible, but should give you an idea of how each library approaches the task.

Look in the src/test/java directories for the Unit tests.

[Javassist](http://www.csg.ci.i.u-tokyo.ac.jp/~chiba/javassist/)

[CGLIB](https://github.com/cglib/cglib)

[ByteBuddy](https://github.com/raphw/byte-buddy)
