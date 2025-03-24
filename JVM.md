## JVM

![JVM High Level Architecture](./img/JVM-high%20level-arch.png)

- `Java Code => compiler [javac] => converted .class file [byte code] => JVM reads this .class file`

## JVM Components

1. ## Class Loader

   - ## responsibility
   - #### loading
     - `loads the .class file from disk or network`
   - #### linking
     - 3 tasks
     - `verification`:
     - ensuring correctness of .class class
     - check the format
     - throw java.lang.VerifyError
     - `preparation`
     - Allocates memory for static members
     - initialize memory to default values
     - `resolution`
     - transforms symbolic references into direct references
   - #### initialization
     - static variables are assigned with values.
     - will be done from top to bottom
   - ## types of class loader
   - ### Bootstrap
     - `loads class from bootstrap path`
     - `JAVA_HOME/JRE/lib`
   - ### Extension
     - child class of bootstrap
     - `load class from JAVA_HOME/JRE/lib/ext`
   - ### Application/System
     - child class of Application/System
     - `loads class from class path specified by the -cp or -classpath`
     - `It is the default class loader used to load user-defined classes and libraries[whatever we write]`

2. ## JVM Memory

   - ### Method Area
   - `Class Level Information is stored`
   - `className, parentName, methods, variables info`
   - `Only 1 method area per JVM`
   - `shared resource`
   - ### Heap Area
   - `Info for all objects are stored here`
   - `One heap area per JVM`
   - Shared resource
   - `String constant pool also gets stored here`
   - GC will clean the heap area
   - `JavaOutOfMemoryError when it is full`
   - heapsize customization
     - `java -Xms512m -Xmx2048m YourProgram`
     - Initial Heap Size (-Xms):
     - Maximum Heap Size (-Xmx)
   - ### Stack Area
   - `Every thread have a different stack area`
   - methods are loaded as per invocation and offloaded once finished
   - `not a shared resource`
   - `stackOverFlowException`
   - ### PC Registers
   - `Each thread has its own PC register`
   - `points to the current instruction being executed`
   - ### Native Method Stack
   - Used to `store native method calls (methods written in other languages like C/C++).`

3. ## Execution Engine

   - Executes Bytes Codes
     - `Interpreter`
       - The interpreter reads bytecode instructions and directly executes them, one by one.
       - disadvantage
       - if a method is all multiple times it will convert that method everytime
     - `JIT Compiler`
       - It compiles the bytecode into machine code at runtime for faster execution.
       - it improves the speed
       - it will convert the method which is called multiple times into native code and will give to interpreter so that it doesn't have to compile again
     - `GC`
       - Responsible for memory management.
       - It automatically de-allocates memory used by objects that are no longer referenced, preventing memory leaks

### Class Loading Mechanism (Delegation Model):

- When a class is requested, the class loader `first delegates the request to its parent class loader`.
- If the `parent class loader cannot find the class, the request is passed down the chain until a class loader finds it`.
- If `no class loader can find the class, a ClassNotFoundException` is thrown.

            Bootstrap Class Loader
                    |
            Extension Class Loader
                    |
            System Class Loader (Application Class Loader)
                    |
            Custom Class Loaders (User-defined)

![Class loader delegation](./img/class%20loader%20delegation.png)
