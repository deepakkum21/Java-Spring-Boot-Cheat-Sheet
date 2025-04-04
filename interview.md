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
