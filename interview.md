## equals()

- Whenever you override `equals(), it is also essential to override the hashCode() method to ensure that equal objects have the same hash code`.
- This is especially important if your objects are stored in collections like HashMap or HashSet.
- The `default implementation of hashCode() in the Object class returns a unique integer based on the memory address`, which is inconsistent with the default equals() method.
- The hashCode() method should return a consistent hash code for equal objects, and it should be based on the fields that determine equality in the equals() method.

        Objects.hash(name, age);

## Make Immutable

- class as final [so can't be extended]
- fields as private final
- initialize in constructor
  - can initialize with deepCopy [Collections.unmodifiableList(hobbies)]
- no setter
- return a deep copy from getter
  - [Collections.unmodifiableList(hobbies)]

## how to break Singleton

1. reflection
2. clone [class should implement Cloneable Interface]
3. Serialization [class should implement Serializable Interface]

## Marker Interface

- interface with no methods
  - Cloneable
  - Serializable
  - Repository
  - TransactionManager

## for Singleton

1. Eager
   - private constructor
   - field as private static final and initialize with new
   - no setter
   - in getter always return the filed which is already initialized
2. Lazy
   - private constructor
   - field as private static
   - synchronized getter
     - check is field is null then initialize
     - if not null means already initialized
3. Enum [ simple, thread-safe singleton that is immune to serialization and reflection attacks.]

## Optional

- a developer an forget to check for null, to avoid this Optional[means it can have null]
- `creation`
  - `Optional.of()`: if null is sent will throw error
  - `Optional.ofNullable()`: can assign null
  - `Optional.empty()`: if null can assign empty
- `access`
  - `optionalObject.isPresent()`:- check if value is not null
    - optionalObject.get() to get the value
  - `optionalObject.ifPresent(consumer)`: value passed to consumer is value present in optional
  - `optionalObject.orElse("defaultValue")`: if value is null or empty, defaultValue will
  - `optionalObject.orElseGet(supplier)`
  - `optionalObject.orElseThrow(supplier)`
  - `optionalObject.ifPresentOrElse(consumer, ()->  )`
- can use intermediate operation like map with optionalObject before getting the value and latter can get the value

## JOINS

| **Join Type**          | **Description**                                                                                          | **Return Rows**                                                                                                         | **Example**                                                                                                                      |
| ---------------------- | -------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------- |
| **INNER JOIN**         | Returns only rows where there is a match in both tables.                                                 | Rows with matching values in both the left and right tables.                                                            | `SELECT * FROM employees INNER JOIN departments ON employees.department_id = departments.department_id;`                         |
| **LEFT JOIN (OUTER)**  | Returns all rows from the left table and matched rows from the right table. If no match, returns `NULL`. | All rows from the left table and matching rows from the right table. If no match, `NULL` in columns of the right table. | `SELECT * FROM employees LEFT JOIN departments ON employees.department_id = departments.department_id;`                          |
| **RIGHT JOIN (OUTER)** | Returns all rows from the right table and matched rows from the left table. If no match, returns `NULL`. | All rows from the right table and matching rows from the left table. If no match, `NULL` in columns of the left table.  | `SELECT * FROM employees RIGHT JOIN departments ON employees.department_id = departments.department_id;`                         |
| **FULL JOIN (OUTER)**  | Returns all rows from both tables. If no match, returns `NULL` for the non-matching side.                | All rows from both tables. If no match, `NULL` in columns of the table without a match.                                 | `SELECT * FROM employees FULL JOIN departments ON employees.department_id = departments.department_id;`                          |
| **CROSS JOIN**         | Returns the Cartesian product of both tables (all combinations of rows).                                 | All combinations of rows from both tables, regardless of whether they match.                                            | `SELECT * FROM employees CROSS JOIN departments;`                                                                                |
| **SELF JOIN**          | A join where a table is joined with itself. Used for hierarchical relationships.                         | Joins the table with itself based on a relationship (like a manager-employee relationship).                             | `SELECT a.name AS employee_name, b.name AS manager_name FROM employees a LEFT JOIN employees b ON a.manager_id = b.employee_id;` |

---

### dividing by zero

```java
        int result = 10 / 0;          // This will throw ArithmeticException
        double result1 = 10.0 / 0;    // Positive infinity
        double result2 = -10.0 / 0;   // Negative infinity
        double result3 = 0.0 / 0;     // NaN
```

### this keyword in lambda

- `this in lambda expressions refers to the outer class` instance where the lambda is defined
- You `cannot` use this to refer to the lambda instance itself because a `lambda expression does not have its own instance` of the enclosing class

### Save() vs saveAndFlush()

| Feature / Aspect               | `save()`                                                 | `saveAndFlush()`                                                                        |
| ------------------------------ | -------------------------------------------------------- | --------------------------------------------------------------------------------------- |
| **Immediate DB Write?**        | ❌ No (deferred until transaction commits or flush call) | ✅ Yes (flushes changes immediately to the database)                                    |
| **Flush Persistence Context?** | ❌ No                                                    | ✅ Yes                                                                                  |
| **Within Transaction?**        | ✅ Yes (typically used inside `@Transactional` method)   | ✅ Yes                                                                                  |
| **Returns Saved Entity?**      | ✅ Yes                                                   | ✅ Yes                                                                                  |
| **Performance**                | Better for bulk/batch operations                         | Slightly heavier (immediate DB interaction)                                             |
| **Use Case**                   | Standard save inside a transaction                       | When you need the entity persisted _immediately_ eg need of saved record id immediately |

### API versioning

- /v1/api
- using headers
  - `Accept: application/vnd.myapp.v1+json`
  - `X-API-VERSION: 1`

```java
@GetMapping(headers = "X-API-VERSION=1")
    public String getUserV1() {
        return "User V1";
    }
```

### Access modifiers

| Modifier  | Class | Package | Subclass | World (anywhere) |
| --------- | ----- | ------- | -------- | ---------------- |
| public    | ✅    | ✅      | ✅       | ✅               |
| protected | ✅    | ✅      | ✅       | ❌               |
| (default) | ✅    | ✅      | ❌       | ❌               |
| private   | ✅    | ❌      | ❌       | ❌               |

### Class can be private?

- `no, classes can't be private`
- only i`nnerclass can be private`
- inner class
  - static
  - non-static
  - localinner
  - anonymous

---

### JVM vs JDK vs JRE

| Term | Full Form                | Purpose                                                              | Contains                                                             |
| ---- | ------------------------ | -------------------------------------------------------------------- | -------------------------------------------------------------------- |
| JVM  | Java Virtual Machine     | Runs Java bytecode on any platform (WORA - Write Once, Run Anywhere) | Part of JRE; contains class loader, memory manager, execution engine |
| JRE  | Java Runtime Environment | Provides the runtime environment to run Java applications            | JVM + libraries + other runtime components                           |
| JDK  | Java Development Kit     | Full package for Java development                                    | JRE + development tools like javac, javadoc, jar, etc.               |

### Comparable Vs Comparator

| Feature / Aspect         | Comparable                                  | Comparator                                                       |
| ------------------------ | ------------------------------------------- | ---------------------------------------------------------------- |
| Package                  | java.lang                                   | java.util                                                        |
| Purpose                  | Defines natural ordering                    | Defines custom ordering                                          |
| Method to implement      | int compareTo(T o)                          | int compare(T o1, T o2)                                          |
| Modifies original class? | ✅ Yes (implements in the class itself)     | ❌ No (used outside the class)                                   |
| Used with                | Collections.sort(list)                      | Collections.sort(list, comparator)                               |
| Flexibility              | Less flexible (only one sort logic)         | More flexible (can have multiple strategies)                     |
| When to use              | When the object has a single, natural order | When you want different sorting logic or cannot modify the class |

###

```java
x = 127
y =127
x==y  // true
x.equals(y)  // true
```

java caches till -127 to 128

### Why do we need Wrapper classes

- int is primitive
- so to have object of int we need wrapper
- `Wrapper takes more memory`
  - wrapper obj => 16bytes + 4bytes [stack memory ref]
  - primitive int 4 bytes

### concurrentModificationException

list.remove throws concurrentModificationException

- use iterator remove()
- CopyOnWriteArrayList
- list.removeIf
- concurrentHashMap

### get() vs load()

| Aspect               | get()                                   | load()                                      |
| -------------------- | --------------------------------------- | ------------------------------------------- |
| Eager or Lazy?       | ❗ Eager (immediate hit to DB)          | 💤 Lazy (returns a proxy, loads later)      |
| Return if not found? | null                                    | ❌ Throws ObjectNotFoundException on access |
| Return type          | Actual entity                           | Proxy of the entity (Hibernate-specific)    |
| DB Hit               | Happens immediately                     | Happens only when proxy is accessed         |
| Performance          | Slightly slower (immediate fetch)       | Can be more performant (if not accessed)    |
| Use Case             | When you need the entity data right now | When you might not use the data immediately |
| JPA Standard?        | ✅ Yes (EntityManager.find())           | ❌ Hibernate-specific (Session.load())      |

### Hibernate caching

- L1
  - `Default cache (always enabled)`
  - Scope: `Session` (Hibernate Session object)
  - Lifecycle: Exists until the session is closed.
  - Usage: Automatically caches entities that are read/loaded during a session.
  - Second-level cache is most useful for read-heavy apps
  - No extra configuration needed

```java
Session session = sessionFactory.openSession();
Employee emp1 = session.get(Employee.class, 1); // hits DB
Employee emp2 = session.get(Employee.class, 1); // fetched from L1 cache
session.close();
```

- L2
  - `Optional and needs to be explicitly configured`
  - Scope: `SessionFactory` level
  - Lifecycle: Lives as long as the SessionFactory lives (shared across sessions)
  - Purpose: `Reduces DB hits across sessions`
  - Used with: Entities, collections, queries

```java
hibernate.cache.use_second_level_cache=true

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)   // mention
public class Employee {
    @Id
    private int id;
    private String name;
    // other fields
}
```

---

### What to Do When OutOfMemoryError Occurs

- `Understand the Error Message`
  - java.lang.OutOfMemoryError: Java heap space → Heap memory full (common).
  - java.lang.OutOfMemoryError: GC overhead limit exceeded → JVM is spending too much time on GC.
  - java.lang.OutOfMemoryError: Metaspace (Java 8+) → Class metadata space is full.
  - java.lang.OutOfMemoryError: Direct buffer memory → Direct (off-heap) memory exhausted.
  - java.lang.OutOfMemoryError: unable to create new native thread → Too many threads.
- `Increase Memory Limits`

        # Example for heap space
        java -Xms512m -Xmx2048m YourApp

        # For Metaspace (Java 8+)
        java -XX:MaxMetaspaceSize=256m YourApp

- `Analyze Memory Usage` []
  - VisualVM (comes with JDK)
  - JProfiler
- `Check for Memory Leaks`
  - Unclosed resources (like DB connections, file streams)
  - Static collections holding references too long
  - Large caches that never get cleared
  - Listeners or threads not removed
  - 🔍 Use heap dump analysis to find objects that should’ve been garbage collected but weren’t.
    - -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./heapdump.hprof // Generate heap dump on OOM
- `Tune Garbage Collection (GC)`
  - -XX:+UseConcMarkSweepGC, -XX:+UseParallelGC, -XX:+UseG1GC
- `Fix Thread Issues (If Thread-related OOM)`
  - Use thread pools (Executors) instead of creating new threads manually.
- `Optimize Your Code`
  - Use streams, pagination, batch processing.
  - Avoid loading huge data into memory all at once (e.g., millions of records from DB).
  - Clean up unnecessary object references.
  - Reduce memory footprint of data structures (e.g., use ArrayList instead of LinkedList if insertion order isn’t critical).

---

### Types of Microservices Testing

| Test Type                   | Purpose                                         | Tools                            |
| --------------------------- | ----------------------------------------------- | -------------------------------- |
| ✅ Unit Testing             | Test small units like methods or classes        | JUnit, Mockito                   |
| ✅ Integration Testing      | Test how components work together (DB, REST)    | Spring Boot Test, Testcontainers |
| ✅ Contract Testing         | Ensure service agreements between microservices | Spring Cloud Contract, Pact      |
| ✅ End-to-End (E2E) Testing | Simulate real scenarios across services         | RestAssured, Selenium            |
| ✅ Component Testing        | Test a single microservice in isolation         | MockServer, WireMock             |
| ✅ Performance Testing      | Load testing, stress testing                    | JMeter, Gatling                  |

### Java Example of the Diamond Problem (with Interfaces)

```java
interface A {
    default void sayHello() {
        System.out.println("Hello from A");
    }
}

interface B {
    default void sayHello() {
        System.out.println("Hello from B");
    }
}

class C implements A, B {
    // Compilation error unless we override sayHello()
}
```

- `solution`
  - override method `InterfaceName.super.methodName() `

```java
class C implements A, B {
    @Override
    public void sayHello() {
        A.super.sayHello(); // or B.super.sayHello();
        // Or your own implementation
    }
}
```

### Why private in Interface [java 9]

- To help other default or static methods
- code reusability in interface
- types
  - `private` => Instance-level helper methods
  - `private static` => Static helper methods

### Interface Static & Default methods

| Feature | When to Use                                                                                                                                                                  |     |
| ------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --- |
| default | - You want to add new methods without breaking existing classes <br/> - You want to give optional, overridable behavior                                                      |
| static  | - You need utility/helper methods tied to the interface <br/> - The method doesn't need access to instance or implementing class data <br/> - `InterfaceName.staticMethod()` |

---

### Create a Custom Marker Interface

1. `Define the Interface`

```java
public interface Auditable {
    // No methods, it's just a marker
}
```

2. `Implement the Interface in Your Class`

```java
public class User implements Auditable {
    private String name;
    private String email;
    // ...getters, setters, etc.
}
```

3. `Use instanceof` or Reflection to Check the Marker

```java
public class AuditProcessor {

    public void process(Object obj) {
        if (obj instanceof Auditable) {
            System.out.println("Auditing: " + obj.getClass().getSimpleName());
            // Perform auditing logic
        } else {
            System.out.println("Not auditable: " + obj.getClass().getSimpleName());
        }
    }

    public static void main(String[] args) {
        User user = new User();
        AuditProcessor auditProcessor = new AuditProcessor();
        auditProcessor.process(user);
    }
}
```

---

### Functional Interface?

- `only one abstract method`
- M`ay have any number of default, static, or private` methods

```java
@FunctionalInterface
interface MyFunction {
    void execute();              // ✔ abstract method (required)

    default void log() {
        System.out.println("Logging from default method");
    }

    static void info() {
        System.out.println("Static method in interface");
    }
}
```
