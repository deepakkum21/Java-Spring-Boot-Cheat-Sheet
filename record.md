# Record

- A record is a `special kind of class designed to act as a transparent carrier for immutable data`

### 1.Immutability

- Once an object of a record is created, `the components (fields) cannot be changed. They are implicitly final`.

### 2.Accessors:

- Records automatically generate accessor methods for each component of the record. For instance, in the example above, name() and age() are the accessor methods.
- `no setter methods`

### 3.Automatic equals(), hashCode(), and toString():

- These methods are generated automatically based on the components of the record.
- if `needed can override` equals(), hashCode(), and toString()

### 4.Cannot Extend Other Classes:

- A record `implicitly extends java.lang.Record, so it cannot extend other classes`. However, it `can implement interfaces`.

```java
        public record Person(String name, int age) {
            // You can add additional methods or logic if needed
            public String greeting() {
                return "Hello, my name is " + name + " and I am " + age + " years old.";
            }

        }
```


```java
        public record CustomerRequest(
            String id,
            @NotNull(message = "Customer firstname is required")
            String firstname,
            @NotNull(message = "Customer firstname is required")
            String lastname,
            @NotNull(message = "Customer Email is required")
            @Email(message = "Customer Email is not a valid email address")
            String email,
            Address address
        ) {

        }



```
