## equals()

- Whenever you override `equals(), it is also essential to override the hashCode() method to ensure that equal objects have the same hash code`.
- This is especially important if your objects are stored in collections like HashMap or HashSet.
- The `default implementation of hashCode() in the Object class returns a unique integer based on the memory address`, which is inconsistent with the default equals() method.
- The hashCode() method should return a consistent hash code for equal objects, and it should be based on the fields that determine equality in the equals() method.

        Objects.hash(name, age);

##
