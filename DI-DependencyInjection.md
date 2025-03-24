## Dependency Injection

### 1. Field Injection

- set `fields of the class`
- `using reflection`
- easy to use
- disadvantage

  - `cannot be used with immutable fields [final]`
  - `chances of NPE`

        class B {
            @Autowired
            private Order order;
        }

### 2. Setter Injection

- `@Autowired in setter method`

            @Autowired
            void setOrder(Order order) {
                this order = order
            }

- Advantages
  - dependency `can be changed wrt to implemented classes`, what is passed after object creation
  - can pass mock object for testing
- Disadvantage
  - `cannot make the property or field as immutable by using final`
  - less readable

### 3. Constructor Injection

- can `create immutable using final`
- `dependency is resolved at the time of initialization of object`
- [spring 4.3]when only `one constructor no need to use @Autowired on constructor`.
- ` Only 1 constructor can @Autowired [in case of multiple constructors]`
- fail fast exception caught at compile time

- Advantages :
  - All mandatory dependencies will be injected at the time of initialization itself.
  - `Makes 100% sure that our object will be initialized with all the required dependencies`.
  - `Avoids NPE`
  - We can create immutable object using constructor injection
  - `Fail Fast - Fail at compilation only in case of missing dependencies`

## ISSUES with DI

### 1. Circular Dependency

- use `@Lazy on field/setter/constructor injection`
- `@PostConstruct & remove all @Autowired`

### 2. Unsatisfied Dependency

-` @Primary when there are multiple implementation` classes of DI object

- `@Qualifier("name") with @Autowired`
