# bytecode-manipulators

This project shows how three different bytecode manipulation libraries can be used to accomplish the same task.

## MyBankAccount
The example starts with the basic `MyBankAccount` class.  This class simply allows you to adjust the balance of the account, and retrieve the balance of the account.

Using the `LogTransaction` annotation on the `adjustAcountBalance(BigDecimal amount)` method, each library will enchance the class's bytecode to provide logging of each adjustment to System.out.

Each library has a different way of accomplishing this task.  These examples are far from perfect or flexible, but should give you an idea of how each library approaches the task.

Look in the src/test/java directories for the Unit tests.

[Javassist](http://www.csg.ci.i.u-tokyo.ac.jp/~chiba/javassist/)

[CGLIB](https://github.com/cglib/cglib)

[ByteBuddy](https://github.com/raphw/byte-buddy)
