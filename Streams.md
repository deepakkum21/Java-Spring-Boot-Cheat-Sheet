## Ways to create Stream

- `Arrays.stream(array)`
- `anyCollection.stream()`
- `Stream.of(item1, item2,...)`
- `stringName.chars().mapToObj(x -> (char)x)`
- `Stream.generate(Math::random).limit(5)`
- `Stream.iterate(seed, function)`

## Handling Null values while filter,reduce,map in streams

- ### Filter
  - use `filter(Objects::nonNull)`
- ### Map

  - use `map(p -> Optional.ofNullable(p.getName()).orElse("Unknown"))`

        List<String> names = people.stream()
            .filter(Objects::nonNull)  // Remove null values
            .map(p -> Optional.ofNullable(p.getName()).orElse("Unknown"))  // Safely map null name to "Unknown"
            .collect(Collectors.toList());

## Sample beginner level Stream program
```java
        String s = "deepak";
        System.out.println(s.chars().mapToObj(a-> (char)a).collect(Collectors.groupingBy(a -> a, Collectors.counting())));

        // Reverse words of string
        String strArr = "Shiv Kumar Prasad";
        List<String> strList = Arrays.stream(strArr.split(" ")).collect(Collectors.toList());
        Collections.reverse(strList);
        System.out.println(strList);

        // Sum of all numbers in a list
        List<Integer> intList = Arrays.asList(1,3,54,6,3,2,5,6,4,7);
        System.out.println(intList.stream().reduce(0, (x,b)-> x+b));

        // Filter even numbers
        System.out.println(intList.stream().filter(x->x%2==0).toList());

        // Filter even and odd numbers
        System.out.println(intList.stream().collect(Collectors.partitioningBy(x->x%2==0)));

        //Convert a list of strings to uppercase
        List<String> strList2 = Arrays.asList("Deepak","Kumar","DBS Tech India","Apple");
        System.out.println(strList2.stream().peek(System.out::println).map(String::toUpperCase).toList());

        // Find the longest string
        System.out.println(strList2.stream().sorted((x,b)-> {
            if(b.length()>=x.length()) return 1;
            return -1;
        }).findFirst().orElse("No String found"));

        // Count occurrences of a specific number
        System.out.println(intList.stream().collect(Collectors.groupingBy(x->x, Collectors.counting())));

        // Check if all numbers are positive
        List<Integer> intList1 = Arrays.asList(1,-3,54,6,3,2,5,6,4,7);
        System.out.println(intList1.stream().filter(x->x<0).findAny().orElseGet(() -> 0));

        // Find the first string starting with a specific letter
        String particularLetter = "K";
        System.out.println(strList2.stream().filter(x-> x.contains(particularLetter)).findFirst().orElse("No String found"));

        // Sort a list of strings alphabetically
        System.out.println(strList2.stream().sorted().toList());

        // Remove duplicate elements from a list
        System.out.println(intList.stream().distinct().toList());

        // Find the average of numbers
        System.out.println(intList.stream().collect(Collectors.averagingDouble(x->x)));

        // Create a list of squared values
        System.out.println(intList.stream().map(x->x*x).toList());

        // Concatenate two lists of strings
        System.out.println(Stream.concat(strList.stream(),strList2.stream()).toList());
        System.out.println(Stream.of(strList, strList2).flatMap(List::stream).toList());

        // Find the sum of squares of even numbers
        System.out.println(intList1.stream().filter(x->x%2==0).map(x->x*x).reduce(0,(x,b) ->x+b));

        // Group words by length
        System.out.println(strList2.stream().collect(Collectors.groupingBy(String::length,Collectors.counting())));

        // Flatten a list of lists
        List<List<String>> listOfLists = new ArrayList<>();
        List<String> list1 = new ArrayList<>(Arrays.asList("Apple", "Banana", "Cherry"));
        List<String> list2 = new ArrayList<>(Arrays.asList("Date", "Elderberry", "Fig"));
        List<String> list3 = new ArrayList<>(Arrays.asList("Grape", "Honeydew", "Iced apple"));
        listOfLists.add(list1);
        listOfLists.add(list2);
        listOfLists.add(list3);
        System.out.println(listOfLists.stream().flatMap(List::stream).toList());

        // Find the maximum value in a list
        System.out.println(intList1.stream().max((a,b)->{
            if(a>=b) return 1;
            return -1;
        }).get());

        // Find strings that contain a particular substring
        String particularString = "Apple1";
        System.out.println(strList2.stream().filter(x->x.contains(particularString)).findAny().orElse("No String found"));

        // Convert a list of integers to a list of their string representations
        System.out.println(intList.stream().map(String::valueOf).toList());

        // Find the second-highest number in a list
        System.out.println(intList1.stream().sorted((a,b)->{
            if(a>=b) return -1;
            return 1;
        }).skip(1).findFirst().get());

        // Remove nulls and duplicates from string list
        List<String> strList3 = Arrays.asList("Apple", "Banana","Apple", "Banana", "Cherry",null);
        System.out.println(strList3.stream().filter(Objects::nonNull).distinct().toList());

        // Group words by their starting letter
        System.out.println(strList2.stream().collect(Collectors.groupingBy(x->x.substring(0,1),Collectors.counting())));

        // Find the longest word in a list
        System.out.println(strList2.stream().min((a, b) -> {
            if (a.length() >= b.length()) return -1;
            return 1;
        }).get());

        // Merge two lists of integers and remove odd numbers
        System.out.println(Stream.concat(intList1.stream(),intList.stream()).filter(x->x%2==0).toList());

        // Check if two lists have any common elements
        System.out.println(intList.stream().anyMatch(intList1::contains));

        // find common elements from 2 list
        System.out.println(intList.stream().filter(intList1::contains).toList());

        // Find the average length of words in a list
        System.out.println(strList2.stream().map(String::length).collect(Collectors.averagingDouble(x->x)));

        // Concatenate multiple lists of strings into one sorted list
        System.out.println(Stream.of(list1, list3, list2).flatMap(List::stream).sorted().toList());

        // Create a map from a list of strings (length of string as key)
        System.out.println(strList2.stream().collect(Collectors.groupingBy(String::length,Collectors.counting())));

        // Find the frequency of each number in a list
        System.out.println(intList.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting())));

        // Sort a list of custom objects based on a field
        List<Person> p = Arrays.asList(new Person(21,"Deepak"), new Person(18,"Anjani"), new Person(33,"rakesh"));
        System.out.println(p.stream().sorted((p1,p2) -> {
            if(p1.getAge() > p2.getAge()) return 1;
            return -1;
        }).map(Person::getName).toList());

        // Find the first non-repeating character in a string
        String str3 = "DEEPAK";
        str3.chars().mapToObj(c-> (char)c)
                .collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting()))
                .entrySet()
                .stream().filter(entry -> entry.getValue()==1)
                .map(Map.Entry::getKey)
                .findFirst().ifPresentOrElse(System.out::println,() -> System.out.println("No Data FOund"));

        // Find the element that occurs most frequently in a list
        intList1.stream().max((a,b) -> {
            if(a>=b) return 1;
            return -1;
        }).ifPresentOrElse(System.out::println,() -> System.out.println("No Data FOund"));

        // Transform a list of numbers into a list of their binary representations
        System.out.println(intList1.stream().map(Integer::toBinaryString)  // Convert each number to its binary string
                .collect(Collectors.toList()));

        // Using Optional with Stream Operations: How would you safely find the first element in a list that is divisible by both 3 and 5,
        // and if no such element exists, return an appropriate default value using Optional?
        System.out.println(intList1.stream().filter(x->x%5==0 && x%3==0).findFirst().orElse(100));

## Advance Stream

        // Implement a custom Collector that groups strings by their first letter, but instead of creating a Map<Character, List<String>>,
        // create a Map<Character, Set<String>>, ensuring that there are no duplicates for any character.
        List<String> strList4 = Arrays.asList("Deepak","Kumar","DBS Tech India","Apple","Deepak","Kumar");
        System.out.println(strList4.stream().collect(Collectors.groupingBy(x->x.substring(0,1), Collectors.toSet())));

        // Stream Grouping by Multiple Fields: Given a list of employees with fields name, department, and salary,
        // how would you use the Stream API to group the employees first by department and then by salary range
        Map<String, Map<String, List<Employee>>> groupedEmployees = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.groupingBy(employee -> getSalaryRange(employee.getSalary()))));
        private static String getSalaryRange(double salary) {
            if (salary < 60000) {
                return "low";
            } else if (salary <= 100000) {
                return "medium";
            } else {
                return "high";
            }
        }

        // Distinct Based on Multiple Fields: Given a list of objects, implement a stream operation to find distinct objects based on multiple fields
        // (e.g., name and age for a Person object) without overriding equals and hashCode.

        List<Person> personList = Arrays.asList(new Person(21,"Deepak"), new Person(18,"Anjani"), new Person(33,"rakesh"));
        System.out.println(personList.stream().collect(Collectors.toMap(
                person->new AbstractMap.SimpleEntry<>(person.getName(), person.getAge()), // composite key (name, age)
                person->person, // value is the person itself
                (existing, replacement) -> existing)) // resolve conflicts by keeping the existing entry
                .values()
                .stream().toList());

        // Combining Streams in Parallel Processing: How can you process multiple streams concurrently (in parallel)
        // using Stream API and merge the results into a single list? Explain the potential risks of parallel stream usage and how to mitigate them.
        List<Integer> list11 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list12 = Arrays.asList(6, 7, 8, 9, 10);
        List<Integer> list13 = Arrays.asList(11, 12, 13, 14, 15);

        System.out.println(Stream.of(list12.parallelStream(),list11.parallelStream(),list13.parallelStream())
                .flatMap(Function.identity()).toList());

        // Complex Mapping with Transformation: You have a list of orders where each order contains a list of items.
        // Each item has a price. Write a stream operation that maps the orders to the total price of items in each order,
        // and then sorts them by the total price in descending order.        List<Order> orderlist = new ArrayList<>();
        List<Order> orderList = new ArrayList<>();
        List<Item> itemList1 = Arrays.asList(new Item(22, "Apple"), new Item(22, "Banana"));
        List<Item> itemList2 = Arrays.asList(new Item(212, "Pencil"), new Item(32, "Pen"));
        List<Item> itemList3 = Arrays.asList(new Item(122, "Shoe"), new Item(52, "Bag"));
        orderList.add(new Order(itemList1));
        orderList.add(new Order(itemList2));
        orderList.add(new Order(itemList3));

        orderList.stream()
                .sorted((o1, o2) ->
                        Double.compare(o2.getItemList().stream()
                        .mapToDouble(Item::getPrice).sum(), o1.getItemList().stream().mapToDouble(Item::getPrice).sum())) // Sort by total price in descending order
                .toList().forEach(System.out::println);


        // Reducing Complex Data Structures: Given a list of Employee objects, each with a salary, implement a reduce operation that finds the employee with the highest salary.
        // Then, modify it to return both the employee and their salary in the form of a custom result object.
        List<Employee> empList = Arrays.asList(new Employee(2200, "Deepak"), new Employee(2200, "Shiv"), new Employee(2300, "Rakesh"));
        empList.stream()
                .reduce((e1, e2) -> e1.getSalary() > e2.getSalary() ? e1 : e2)
                .map(employee -> new Result(employee, employee.getSalary()));

        // Custom Sorting with Multiple Comparators: You have a list of Person objects, each with age, name, and height attributes.
        // Write a stream operation that sorts the list first by age (ascending), then by name (alphabetically), and finally by height (in descending order).
        List<Person> personList1 = Arrays.asList(new Person(21,"Deepak",22), new Person(18,"Anjani",56), new Person(33,"rakesh",43));
        personList1.stream()
                .sorted(
                        Comparator.comparingInt(Person::getAge)
                        .thenComparing(Person::getName)
                        .thenComparing(Comparator.comparingDouble(Person::getHeight).reversed())
                );

        // Advanced FlatMap Usage for Nested Lists: Given a nested structure like List<List<Integer>>,
        // write a Stream operation to find the sum of all numbers within the nested lists that are greater than a given threshold (e.g., 100).
        List<List<Integer>> nestedIntList = Arrays.asList(
                Arrays.asList(1,4,9,100,200,300,400),
                Arrays.asList(100,200,300,400));
        int threshold=100;
        System.out.println(nestedIntList.stream().flatMap(List::stream).filter(x->x>threshold).mapToInt(Integer::intValue).sum());

        // Collecting with Multiple Operations: Implement a Collector that collects all elements in a stream into a Map<String, Integer>,
        // where the key is the string and the value is the length of the string, but only for strings that are longer than 3 characters.
        System.out.println(strList4.stream().filter(Objects::nonNull)
                .filter(x-> x.length()>3).collect(Collectors.toSet())  // to set to avoid duplicate keys
                .stream().collect(Collectors.toMap(x->x, String::length)));

        // Stream of Random Numbers and Statistical Operations: Generate a stream of 1000 random integers,
        // and then use the Stream API to calculate the mean, max, and min of the numbers.
        // Use Collectors.teeing() to combine two statistical operations (e.g., average and count) into one result.
        var result= Stream.generate(Math::random).limit(1000).collect(Collectors.teeing(
                Collectors.averagingInt(Integer::intValue),
                Collectors.counting(),
                (avg,count)-> Map.of("average",avg,"count",count)
        ));

        // Complex Filter and Map Operations: Given a list of Product objects, where each product has a category, name, and price,
        // write a stream operation that filters out products in the "electronics" category,
        // then maps them to a string format like "Product [name] costs [price]", and finally collects them into a List<String>.
        products.stream()
            .filter(product -> "electronics".equals(product.getCategory()))  // Filter out electronics products
            .map(product -> "Product " + product.getName() + " costs " + product.getPrice())  // Map to formatted string
            .collect(Collectors.toList());

## for finding top [k] from big list keeping performance

- `sorted() + limit()` has time complexity of `O(n log n)`

        employees.stream().sorted(
            Comparator.comparingDouble(Employee::getSalary).reversed() // Sort by salary in descending order
        ).limit(10).toList();

- use `priority queue` has time complexity of `O(n log k)`

        PriorityQueue<Employee> topEmployees = new PriorityQueue<>(Comparator.comparingDouble(Employee::getSalary));

        employees.stream()
            .forEach(employee -> {
                topEmployees.offer(employee);  // inserts into priority queue
                if (topEmployees.size() > 10) {
                    topEmployees.poll();  // Remove the smallest element from top of queue when the queue exceeds 10
                }
            });

        List<Employee> top10Employees = new ArrayList<>(topEmployees);
```
