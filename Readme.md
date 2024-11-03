# Calculator

Demo project of a simple calculator REPL.

Supports
* integer literals : `1`
* addition (requires to wrap in parentheses) : `(1 + 2)`
* multiplication (requires to wrap in parentheses) : `(1 * 2)`
* division (requires to wrap in parentheses) : `(12 / 2)`
* variable definitions : `const variableName = (1 + 5)`
* variable usage : `(variableName + 2)`
* to exit : `:quit`

# References

Inspired by https://github.com/madjar/talk-scala-parser-combinators

Relies on [scala parser combinators](https://github.com/scala/scala-parser-combinators) 


# Requirements

* [sbt](https://www.scala-sbt.org/download/) (tested with sbt 1.10.4)
* a JDK (tested with JDK 21) (can be installed through [SDKMAN](https://sdkman.io/usage/))

# Usage

```
sbt run
```

# Example

```
input> 1 + 2
Parsing error : [1.1] failure: 'const ' expected but '1' found

1 + 2
^
input> 5
Evaluation result: 5.0
input> const b = 4
Memory state :
==============
b: 4.0
input> const a = 7
Memory state :
==============
b: 4.0
a: 7.0
input> a + b
Parsing error : [1.1] failure: 'const ' expected but 'a' found

a + b
^
input> const c = a + b
Parsing error : [1.11] failure: string matching regex '\d+' expected but 'a' found

const c = a + b
          ^
input> (a + 2)
Evaluation result: 9.0
input> const c = ((a + a) * 2) 
Memory state :
==============
b: 4.0
a: 7.0
c: 28.0
input> c
Evaluation result: 28.0
input> a
Evaluation result: 7.0
input> a
Evaluation result: 7.0
input> b
Evaluation result: 4.0
input> const b = 1
Memory state :
==============
b: 1.0
a: 7.0
c: 28.0
input> :quit
```
