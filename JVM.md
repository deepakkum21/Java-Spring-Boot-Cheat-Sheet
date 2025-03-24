## JVM

![JVM High Level Architecture](./img/JVM-high%20level-arch.png)

- `Java Code => compiler [javac] => converted .class file [byte code] => JVM reads this .class file`

## JVM Components

- ## Class Loader
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
