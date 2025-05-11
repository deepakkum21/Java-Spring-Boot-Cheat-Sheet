## List and collection remove method

```java
public static void main(String[] args) {
    Collection<String> numbers = new ArrayList<String>(List.of("a", "b", "c"));

    System.out.println(numbers); //[1, 2, 3]

    numbers.remove(1);

    System.out.println(numbers); //[1, 3]

    //If we change the List<Integer> above to Collection<Integer> above, what will be the output?
  }
```

```java
public class After {
  public static void main(String[] args) {
    //List<Integer> numbers = new ArrayList<Integer>(List.of(1, 2, 3));
    Collection<Integer> numbers = new ArrayList<Integer>(List.of(1, 2, 3));

    System.out.println(numbers); //[1, 2, 3]

    numbers.remove(1);

    System.out.println(numbers); //[2, 3] instead of [1, 3]

    //If we change the List<Integer> above to Collection<Integer> above, what will be the output?
  }
}
```

- `Collection's remove takes an Object as parameter`.
- List overrides and also overloads `that function to take an index`.
- If we access the function using a Collection reference, `the value 1 is boxed as an object`
- If we access the function using a List reference, `the value 1 is used, as is, as an index`.
- So Collection's remove method will remove 1 as a object and List's remove method will remove item at index 1

---

## Var in Java

```java
public class Before {
  public static void main(String[] args) {
    var numbers = new ArrayList<Integer>(List.of(1, 2, 3));

    System.out.println(numbers); //[1, 2, 3]

    numbers.remove(1);

    System.out.println(numbers); //[2, 3]

    //If we change the Collection<Integer> above to var above, what will be the output?
  }
}
```

```java
public class After {
  public static void main(String[] args) {
    //Collection<Integer> numbers = new ArrayList<Integer>(List.of(1, 2, 3));
    var numbers = new ArrayList<Integer>(List.of(1, 2, 3));

    System.out.println(numbers); //[1, 2, 3]

    numbers.remove(1);

    System.out.println(numbers); //[1, 3] instead of [2, 3]

    //If we change the Collection<Integer> above to var above, what will be the output?
  }
}
```

- The type of `numbers is inferred as ArrayList<Integer> instead of Collection<Integer>`
- var type inference is based on what is being returned and not its parent
- Type inference is very nice to use, but use caution when changing existing code.
- Relying on good tests in addition to counting on the compiler is generally a good practice.

---

## Arrays.asList() vs List.of()

1. **List.of()** => `return an immutable list`

- `Immutable`: The resulting list cannot be modified (no add, remove, or set).
- `Does not allow null values`: Throws NullPointerException if any element is null.
- `Fixed size: Cannot change size (add/remove)`.

2. **Arrays.asList()**

- `Fixed-size`, but `mutable elements`: You `can use .set()` to change elements, but `cannot add/remove`.
- `Allows null` values
- Backed by the original array: Changes to the list affect the array, and vice versa.

| Feature          | `List.of()` | `Arrays.asList()`             |
| ---------------- | ----------- | ----------------------------- |
| Java Version     | 9+          | 1.2+                          |
| Mutability       | Immutable   | Fixed-size, partially mutable |
| Null elements    | Not allowed | Allowed                       |
| Backed by array? | No          | Yes                           |
| Can change size? | No          | No                            |

---

## Streams Vs Pure Functions

```java
public class Before {
  public static void main(String[] args) {
    List<String> names = List.of("Pushpa", "Shrivalli", "Kesava", "Srinu", "Konda", "Jakka", "Jaali");

    List<String> inUppercase = new ArrayList<>();

    names.stream()
            .map(String::toUpperCase)
            .forEach(name -> inUppercase.add(name));

    System.out.println(names.size());
    System.out.println(inUppercase.size());
  }
}
// in the above scenario both size will be 7 but
// if parallel stream is used and this is run using for loop and trying to modify external list, answer will be inconsistent 7 or 6

```

`because with parallel stream modifying external list could be chance of 2 threads trying to access same memory location caused due to RACE CONDITION`

```java
public class After {
  public static void main(String[] args) {
    List<String> names = List.of("Pushpa", "Shrivalli", "Kesava", "Srinu", "Konda", "Jakka", "Jaali");
    List<String> inUppercase = new ArrayList<>();
    //for(int i=0;i<100;i++) {

    List<String> list = names.parallelStream()
            .map(String::toUpperCase)
            .toList();

    System.out.println(names.size());
    System.out.println(list.size());
  }
  // }
}
```

`use Collectors.toList() / toList()` to store in new variable rather than modifying externalList

---

## Stream Terminal Ops

```java
public class Before {
  public static void main(String[] args) {
    int[] factor = new int[] { 2 };

    var numbers = List.of(1, 2, 3);

    var stream = numbers.stream()
      .map(number -> number * factor[0]); //2,4,6

    factor[0] = 0;

    stream.forEach(System.out::print);
  }
}

//What is the output of this code?
//000 ✅
//123 ❌
```

- This is an example of code we should avoid. In Java, `lazy evaluation will result in 000`
- `streams are lazy, not executed till terminal operator is executed`

---

## Parallel/ Sequential Stream

```java
public class Before {
  public static void main(String[] args) {
    List.of(1, 2, 3).stream()
      .parallel()  // ignored as sequential gets more precedence as it is mentioned as the last one
      .map(number -> transform(number))
      .sequential() // gets precedence as it is the last one
      .forEach(number -> print(number));
  }

  public static int transform(int number) {
    System.out.println("transform: " + Thread.currentThread());
    return number * 2;
  }

  public static void print(int number) {
    System.out.println("print: " + Thread.currentThread());
    System.out.println(number);
  }
}
```

- code may run in main thread or common pool thread (multithreaded)
  - `main thread as parallel is ignored`

---

## ConcurrentModification

```java
public class Before {
  public static void main(String[] args) {

      List<String> names = new ArrayList<>();
      names.add("Alice");
      names.add("Bob");
      names.add("Charlie");
     // names.add("Chetan"); //ConcurrentModificationException if you uncomment

      for (String name : names) {
          if (name.equals("Bob")) {
              names.remove(name);
          }
      }
      System.out.println(names);
  }
}

```

- this will not throw any exception as removed item is 2nd last of the list, so the iterator, will skip the next item as by the time it moves next position one item is removed which have led to decrease in size, and the list is over
- but when the removed item is not the 2nd last one, `when iterator moves to the next element, it will check the modification count[for every next element this happens], if any modification present as list is not over yet it will throw ConcurrentModification`
