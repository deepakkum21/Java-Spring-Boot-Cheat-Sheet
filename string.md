# String

- String `are immutable`
- String s1="Deepak"
- String s2 = new String("Deepak") //not interned / not sent to string pool
- String s3 = `new String("Deepak").intern()` // checks the string pool if present return the address otherwise saves to string pool

Immutable ? - use cache of string pool - threadsafe - use cached hashcode

- `'+' operator gets implemented using StringBuilder.append internally`

## Why String are immutable?

- **`Thread Safety`**:
  - Since Strings `cannot be modified, they are inherently thread-safe`.
  - `Multiple threads can use the same String` without worrying about data corruption.
- **`HashCode Consistency`**:
  - Strings are widely used as keys in `HashMap Immutability ensures the hashcode of a String doesn't change after it's created`.
- **`Security`**:
  - Strings are used in sensitive operations like class loading or database connection URLs. Immutability ensures they cannot be modified maliciously.

## Interview Question

```java
    String s1 = new String("Deepak");
    String s2 = new String("Deepak");
    s1==s2; // false as with new always new object is created

    String s3 = new String("Deepak").intern();
    String s4 = "Deepak";
    s3==s4; // true as with intern if object is present in String pool ref to that will be returned otherwise new object in SCP will be created.

    String s5 = "Deepak"+"Kumar";
    String s6 = "DeepakKumar";
    s5==s6; // true
```
