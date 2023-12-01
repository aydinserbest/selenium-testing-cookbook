You can use different methods other than 
Arrays.asList() 
to assign the expression new String[]{"my-check-1", "my-radio-1"} 
to a list in Java. 
An array can be directly converted to a list, 
or a new list can be created using the array elements. 
### Here are some alternatives:

### Manually Creating a List: 
You can manually add array elements to a list one by one. This is especially preferable when the number of array elements is small.

List<String> checked = new ArrayList<>();
checked.add("my-check-1");
checked.add("my-radio-1");

### Using Streams for Java 8 and Above: 
In Java 8 and later versions, it's possible to create a list from an array using the Stream API. This can be more effective for larger arrays.

List<String> checked = Arrays.stream(new String[]{"my-check-1", "my-radio-1"})
.collect(Collectors.toList());

### Using the Collections.addAll() Method: 
The Collections.addAll() method can be used to add all elements of an array to an existing list.

List<String> checked = new ArrayList<>();
Collections.addAll(checked, "my-check-1", "my-radio-1");

### Using an Anonymous Inner Class: 
This method can be suitable for one-time use. Here, the list is initialized using an anonymous inner class.

List<String> checked = new ArrayList<String>() {{
add("my-check-1");
add("my-radio-1");
}};
Each method can be suitable for different situations and needs. While Arrays.asList() is simple and readable, the size of the list it returns is unmodifiable. If you plan to expand or shrink the list later, using a resizable list type like ArrayList would be more appropriate."