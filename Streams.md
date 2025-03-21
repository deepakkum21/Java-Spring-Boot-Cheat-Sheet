## Ways to create Stream

- `Arrays.stream(array)`
- `anyCollection.stream()`
- `Stream.of(item1, item2,...)`
- `stringName.chars().mapToObj(x -> (char)x)`
- `Stream.generate(Math::random).limit(5)`
- `Stream.iterate(seed, function)`

## Sample beginner level Stream program

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
