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
- eg `Entry in Map`
  - Map.Entry<k,v>

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

---

### transient

- `not serialize sensitive info`
- `default value is present at serialization` of transient
- still we want serialize
  - `implement Serializable` [I]
  - override
    - `writeObject`()
    - `readObject`()

### Detailed Spring Boot Configuration Loading Order:

Spring Boot loads properties from various sources in a specific order (from lowest to highest priority):

- `application.yml` (in src/main/resources)
- `application.properties` (in src/main/resources)
- `Command-line` arguments
- `Environment` variables
- `@PropertySource` annotations
- `Profile-specific` files like application-dev.properties
- Code-based `configuration (@Value`, etc.)

---

### Union Vs UnionAll

| Feature            | UNION                          | UNION ALL                                     |
| ------------------ | ------------------------------ | --------------------------------------------- |
| Removes duplicates | ✅ Yes                         | ❌ No                                         |
| Performance        | ⛔ Slower (due to sort)        | ⚡ Faster                                     |
| Use case           | When you want distinct results | When you want everything, duplicates included |

### Deep Copy

| Library             | Method                     | Serializable Required | Notes                            |
| ------------------- | -------------------------- | --------------------- | -------------------------------- |
| Apache Commons      | SerializationUtils.clone() | ✅ Yes                | Easiest if already Serializable  |
| Gson                | toJson() + fromJson()      | ❌ No                 | Works on POJOs, very flexible    |
| Jackson (Alt)       | Similar to Gson            | ❌ No                 | Good for complex JSON structures |
| ModelMapper / Dozer | Object mapping             | ❌ No                 | Good for mapping between models  |

### String vs stringBuilder vs StringBuffer

| Feature     | String                   | StringBuilder               | StringBuffer               |
| ----------- | ------------------------ | --------------------------- | -------------------------- |
| Mutability  | ❌ Immutable             | ✅ Mutable                  | ✅ Mutable                 |
| Thread-safe | ✅ Yes                   | ❌ No                       | ✅ Yes                     |
| Performance | 🐢 Slowest               | ⚡ Fastest                  | 🐢 Slower (sync overhead)  |
| Use case    | Read-only or few changes | Many changes, single-thread | Many changes, multi-thread |

---

### behavior if @Configuration is replaced with @Component

```java
@Configuration
  public class BaseConfig {
  @Bean
  public MyService myService() {
    return new MyService();
  }

  @Bean
  public MyRunner runner() {
    myService();
    myService();
    return new MyRunner();
  }
}
```

- `@Configuration`
  - `uses CGLib Proxies`
  - injected beans using `@Bean behave as SINGLETON`
- `@Component`
  - injected `beans behave as PROTOTYPE`

---

### 🧵 SynchronizedMap vs ConcurrentMap

| Feature                    | SynchronizedMap (Collections.synchronizedMap()) <br/> `Collections.synchronizedMap(new HashMap<>())` | ConcurrentMap (ConcurrentHashMap, etc.) <br/> `new ConcurrentHashMap<>()` |
| -------------------------- | ---------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------- |
| Thread safety              | ✅ Yes (via synchronization wrapper)                                                                 | ✅ Yes (via fine-grained concurrency)                                     |
| Performance in concurrency | 🚫 Slower (locks the entire map)                                                                     | ✅ Faster (locks only parts — bucket-level)                               |
| Null keys/values allowed?  | ✅ Yes (depends on underlying map, e.g., HashMap)                                                    | ❌ No (neither null keys nor null values)                                 |
| Fail-safe behavior         | ❌ No (throws ConcurrentModificationException)                                                       | ✅ Yes (weakly consistent, no exception)                                  |
| Preferred for              | Simple thread-safe use in low-contention scenarios                                                   | High-performance, scalable concurrent apps                                |
| Introduced in              | Java 1.2 (Collections utility)                                                                       | Java 1.5 (java.util.concurrent)                                           |

---

### Enumeration vs Iteration

| Feature              | Enumeration                                 | Iterator                                                                     |
| -------------------- | ------------------------------------------- | ---------------------------------------------------------------------------- |
| Introduced in        | Java 1.0                                    | Java 1.2 (as part of the Collections Framework)                              |
| Interface            | java.util.Enumeration                       | java.util.Iterator                                                           |
| Supports             | Only Vector, Hashtable (legacy classes)     | All modern Collection types (e.g., ArrayList, HashSet, etc.)                 |
| Methods              | hasMoreElements(), nextElement()            | hasNext(), next(), remove()                                                  |
| Can remove elements? | ❌ No                                       | ✅ Yes (remove() method)                                                     |
| Fail-fast?           | ❌ No (Enumeration is not fail-fast)        | ✅ Yes (throws ConcurrentModificationException if modified during iteration) |
| Thread-safety        | ✅ Safer in multi-threaded context (Vector) | ❌ Needs external sync (except Concurrent collections)                       |
| Preferred for        | Legacy code or read-only traversal          | Modern Java collections (preferred overall)                                  |

---

### Permgen vs Metaspace in Java

| Feature           | PermGen (Permanent Generation)              | Metaspace                                                                                                                           |
| ----------------- | ------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------- |
| Introduced in     | Java 1.2                                    | Java 8 (replaces PermGen)                                                                                                           |
| Removed in        | ❌ Removed in Java 8                        | ✅ Present from Java 8 onward                                                                                                       |
| Location          | Part of the JVM heap                        | Outside the heap (native memory)                                                                                                    |
| Size              | Fixed size (default or via -XX:MaxPermSize) | Dynamically expands (unless limited)                                                                                                |
| Configurable with | -XX:PermSize, -XX:MaxPermSize               | -XX:MetaspaceSize, -XX:MaxMetaspaceSize <br/> If you don’t set MaxMetaspaceSize, `it will expand until system memory is exhausted`. |
| Stores            | Class metadata, method info, static vars    | Same (class metadata, etc.)                                                                                                         |
| OOM Risk          | OutOfMemoryError: PermGen space             | OutOfMemoryError: Metaspace (if maxed)                                                                                              |
| GC Behavior       | Collected with Full GC (and slower)         | More efficient management                                                                                                           |

---

### Ways to Exclude Auto-Configuration in Spring Boot

1. `Using exclude in @SpringBootApplication` => Best for excluding known auto-config classes directly

```java
@SpringBootApplication(
    exclude = {DataSourceAutoConfiguration.class}
)
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
```

2. `Using excludeName in @SpringBootApplication` => Useful when dealing with optional dependencies (e.g., you don’t want to import the actual class).

```java
@SpringBootApplication(
    excludeName = {"org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"}
)
public class MyApplication { ... }
```

3. `Using spring.autoconfigure.exclude in application.properties or application.yml`

```yml
spring:
  autoconfigure:
    exclude:
      - org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
```

---

### handling checked & unchecked exception in lambda expression

1. `Unchecked Exceptions (RuntimeException)`

```java
list.forEach(s -> {
    try {
        System.out.println(s.toUpperCase());
    } catch (NullPointerException e) {
        System.out.println("Null value encountered!");
    }
});
```

2. `Checked Exception`

```java
files.forEach(file -> {
    Files.readAllLines(Path.of(file)); // ❌ Compilation error (IOException is checked)
});
```

- 1. Wrap in try-catch block inside lambda

```java
files.forEach(file -> {
    try {
        List<String> lines = Files.readAllLines(Path.of(file));
        lines.forEach(System.out::println);
    } catch (IOException e) {
        System.err.println("Error reading file: " + file);
    }
});
```

- 2. Create a Custom Functional Interface `That Allows Exceptions`

```java
@FunctionalInterface
interface CheckedConsumer<T> {
    void accept(T t) throws Exception;
}

static <T> Consumer<T> handleCheckedException(CheckedConsumer<T> consumer) {
    return t -> {
        try {
            consumer.accept(t);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    };
}


files.forEach(handleCheckedException(file -> {
    List<String> lines = Files.readAllLines(Path.of(file));
    lines.forEach(System.out::println);
}));
```

| Exception Type | Can Use in Lambda Directly? | Solution                                                                                           |
| -------------- | --------------------------- | -------------------------------------------------------------------------------------------------- |
| Unchecked      | ✅ Yes                      | Handle with try/catch or ignore                                                                    |
| Checked        | ❌ No                       | Wrap with try/catch OR use helper `method/custom interface where abstract method throws Exception` |

---

### param we should check before deciding any data structure

1. `Type of Data / Nature of Data`

- Is it primitive, object-based, hierarchical, relational
- Are elements unique or can they repeat?

2. `Operations You Need to Perform`
3. Time Complexity Constraints
4. Memory Usage
5. `Concurrency / Thread-Safety`

- Single-threaded environment? ➝ Use non-synchronized collections (ArrayList, HashMap)
- Multi-threaded environment? ➝ Use concurrent structures:
  - ConcurrentHashMap, CopyOnWriteArrayList, BlockingQueue
- Or wrap with Collections.synchronizedList(...)

6. `Mutability / Immutability`

- Use Collections.unmodifiableList(...) or Java 9+ factory methods (List.of(...))

---

### Configure Multiple DataSource

1. `define each datasource separately with its own configuration, entity manager, and transaction manager` (in case of JPA)
2. Add dependencies (Maven)
3. Add properties in application.yml or application.properties

```yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/primarydb
    username: root
    password: pass
    driver-class-name: com.mysql.cj.jdbc.Driver

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

secondary:
  datasource:
    url: jdbc:postgresql://localhost:5432/secondarydb
    username: postgres
    password: pass
    driver-class-name: org.postgresql.Driver

  jpa:
    show-sql: true
    hibernate:
      ddl-auto: update
```

4. Create Config for Primary Datasource (Default)

```java
@Configuration
@EnableJpaRepositories(
    basePackages = "com.example.primary.repository", // your package
    entityManagerFactoryRef = "primaryEntityManager",
    transactionManagerRef = "primaryTransactionManager"
)
public class PrimaryDataSourceConfig {

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties primaryDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    public DataSource primaryDataSource() {
        return primaryDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean primaryEntityManager(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(primaryDataSource())
                .packages("com.example.primary.model")
                .persistenceUnit("primary")
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager primaryTransactionManager(
            @Qualifier("primaryEntityManager") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
```

5. Create Config for Secondary Datasource

```java
@Configuration
@EnableJpaRepositories(
    basePackages = "com.example.secondary.repository",  // base package for secondary datasource
    entityManagerFactoryRef = "secondaryEntityManager",
    transactionManagerRef = "secondaryTransactionManager"
)
public class SecondaryDataSourceConfig {

    @Bean
    @ConfigurationProperties("secondary.datasource")
    public DataSourceProperties secondaryDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource secondaryDataSource() {
        return secondaryDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean secondaryEntityManager(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(secondaryDataSource())
                .packages("com.example.secondary.model")
                .persistenceUnit("secondary")
                .build();
    }

    @Bean
    public PlatformTransactionManager secondaryTransactionManager(
            @Qualifier("secondaryEntityManager") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
```

6.  Define Separate Model and Repository Packages

```java
com.example.primary.model
com.example.primary.repository
com.example.secondary.model
com.example.secondary.repository
```

---

### Communication b/w Microservices

- `Synchronous`
  - rest
  - grpc
- `Asynchronous`
  - message brokers [kafka]

---

### @Bean vs @Component

| Feature                     | @Bean                                  | @Component                          |
| --------------------------- | -------------------------------------- | ----------------------------------- |
| Declared in                 | @Configuration class                   | Directly on the class               |
| When to use                 | 3rd-party classes / custom logic       | Your own classes / simple injection |
| Method-based or class-based | Method that returns a bean             | Annotates a class                   |
| Auto-detected?              | ❌ No (must be manually defined)       | ✅ Yes (via component scanning)     |
| More control over bean      | ✅ Yes (init params, conditions, etc.) | ❌ Limited                          |

---

### to run some logic as soon application starts

1. Create a class `implementing CommandLineRunner`
2. Annotate with @Component
3. `override run()`

---

### ❌ Non-Idempotent Method

| Method | Idempotent? | Description                                                                                                                                |
| ------ | ----------- | ------------------------------------------------------------------------------------------------------------------------------------------ |
| POST   | ❌ No       | Used to create resources or trigger operations. Calling it multiple times usually creates multiple resources or changes things repeatedly. |

### Idempotent HTTP Methods

| Method  | Idempotent?    | Description                                                                                                      |
| ------- | -------------- | ---------------------------------------------------------------------------------------------------------------- |
| GET     | ✅ Yes         | Retrieves data. Doesn't change anything.                                                                         |
| PUT     | ✅ Yes         | Replaces a resource. Multiple PUTs with the same data = same result.                                             |
| DELETE  | ✅ Yes         | Deletes a resource. Deleting it again doesn’t change the state (resource stays gone).                            |
| HEAD    | ✅ Yes         | Like GET but only returns headers. Doesn’t affect server state.                                                  |
| OPTIONS | ✅ Yes         | Returns allowed methods or server capabilities. No side effects.                                                 |
| PATCH   | ⚠️ No (mostly) | Not guaranteed to be idempotent. Depends on how it's implemented. E.g., incrementing a counter = not idempotent. |

---

### ❌ When Not to Use parallelStream()

- Small collections
- I/O-heavy operations
- Order-sensitive operations (unless using .forEachOrdered())
- Operations involving shared mutable state
- In environments with limited cores (e.g., containers with CPU limits)

---

### @component vs @configuration

| Feature              | `@Component`                 | `@Configuration`                                                                                                                                                                                       |
| -------------------- | ---------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| Purpose              | General-purpose component    | Bean factory/configuration class                                                                                                                                                                       |
| Bean creation method | Class is itself a bean       | Defines beans via `@Bean` methods                                                                                                                                                                      |
| Proxying behavior    | Not proxied by default       | `Uses CGLIB proxy to ensure singleton beans`                                                                                                                                                           |
| Typical use          | Services, repositories, etc. | Centralized configuration or complex bean setup                                                                                                                                                        |
| Internally           |                              | - `@Component` <br/> - `proxyBeanMethods()` [true] [uses CGLIB proxy to return singleton beans] <br/> - `enforceUniqueMethods()` [true] [to ensure there is no conflicting bean inside @configuration] |

---

### Arrays.asList() vs List.of()

| Feature           | `Arrays.asList()`                                                                                | `List.of()`                                                                       |
| ----------------- | ------------------------------------------------------------------------------------------------ | --------------------------------------------------------------------------------- |
| Java Version      | Java 1.2+                                                                                        | Java 9+                                                                           |
| Mutability        | - `Modifiable elements, but fixed size` <br/> - `can update but cannot add new item to the list` | - `Completely immutable` <br/> - can't add also can't update to the existing list |
| Backing Structure | Backed by array                                                                                  | Not backed by array                                                               |
| Supports `null`?  | Yes                                                                                              | No (`NullPointerException` if `null` is used)                                     |
| Performance       | Slightly more overhead due to backing array                                                      | Often faster and safer due to immutability                                        |
