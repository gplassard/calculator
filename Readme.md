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
```
