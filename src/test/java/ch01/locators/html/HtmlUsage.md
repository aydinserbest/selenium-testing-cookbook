In the RowSelection class, instead of a web page, an HTML prepared by ChatGPT was used.
1-"Example of working with a local HTML file."
"You can open a locally saved HTML file and perform operations on it with Selenium WebDriver. 
With Selenium WebDriver, you can interact with the local HTML file, locate elements, and engage with them. 
Selenium operates on this file as if it were a normal web page. 
There are two different methods to use the local file:


1-  
driver.get("file:///C:/Users/YourName/Documents/example.html");

2-
// Get the full path of the HTML file
String localFilePath = Paths.get("/Users/gebruiker/Desktop/table.html").toUri().toString();
// Load the HTML file with WebDriver
driver.get(localFilePath);
The small difference between these two is:


In the expression Paths.get("C:\\path\\to\\test_table.html").toUri().toString();, the toUri().toString() part is used to convert the file path into a URI (Uniform Resource Identifier) format. This is especially helpful for consistently handling file paths across different operating systems (Windows, macOS, Linux, etc.).

The Paths.get(...) method converts the given file path into a Path object.
The toUri() method converts this Path object into a URI. This makes the file paths suitable for the URI format, for example, file:///C:/path/to/test_table.html.
The toString() method then converts this URI into a string format, so it can be used in the driver.get() method.
This approach ensures the file path is correctly processed in a platform-independent way and is generally a more robust solution.
In the first example, I directly used the file:/// protocol to specify the file path. This approach is used to directly provide the full path of the file to the browser, for example, driver.get("file:///C:/path/to/file.html");.

The main difference between using toUri().toString() and directly using the file:/// protocol is that the toUri().toString() method automatically converts the file path into a proper URI format. This is important for codes that run on different operating systems, as each platform may handle file paths differently.

Which one to use depends entirely on your needs:

If you want your code to work smoothly across different operating systems, 
using toUri().toString() might be more appropriate.
However, if you are only running it on a specific system and are sure that your file path is in the correct format, 
directly using the file:/// protocol might be simpler."

note: in the code of driver.get("file:///path/to/your/html/file.html");, there are 3 ///
The expression driver.get("file:///path/to/your/html/file.html"); includes three forward slashes (/) in the file:/// part. This represents the standard file: URI scheme used to access a local file through a browser.

The file:/// URI scheme is used to load a file from your computer's local file system. Here:

The first two slashes (//) are a standard part of all URIs.
The third slash (/) points to the root directory of the local file system.
Therefore, when accessing a local HTML file, you need to use the full file path starting with file:///.

### 2-"Example where the HTML file is included in the project."
There are two methods to include an HTML file in the project instead of working with it locally: 
1- Using ClassLoader 
2- Using the File class.

### 1- Using ClassLoader: 
In this method, the getFileUrl method finds the URL of the file using ClassLoader. 
This approach is typically used to find resource files 
(for example, files in the resources directory). 
The ClassLoader system is a mechanism used by Java applications to load classes and resources. 

private String getFileUrl(String fileName) {
ClassLoader classLoader = getClass().getClassLoader();
URL resourceUrl = classLoader.getResource(fileName);
if (resourceUrl != null) {
return resourceUrl.toString();
} else {
throw new RuntimeException("File not found: " + fileName);
}
}


This method searches for the project's compiled resources (those found on the classpath). 
Therefore, the file must be in a special directory like resources, 
and this directory must be added to the classpath. 
If the file is directly in the project root directory 
and not added to the classpath, ClassLoader cannot find the file.

### 2- Using the File class: 
In the second method, you obtain the full path of the file through the File class. This means the file can be in the project root directory or any subdirectory. The File class provides direct access to files in the file system. 

private String getFileUrl(String fileName) {
ClassLoader classLoader = getClass().getClassLoader();
URL resourceUrl = classLoader.getResource(fileName);
if (resourceUrl != null) {
return resourceUrl.toString();
} else {
throw new RuntimeException("File not found: " + fileName);
}
}


This method allows direct access to the file through its path in the local file system. 
If the file is in the project root directory and not added to the classpath, 
this method can find the file.

### In summary, 
which method to use depends on where the file is placed. 
If the file is in a directory like resources and added to the classpath, 
the first method (ClassLoader) is used. 
However, if the file is in the project root directory and not added to the classpath, 
the second method (File class) is more suitable for obtaining the file's full path.

In this case, since your file is in the project root directory 
and cannot be found by ClassLoader, 
it is more appropriate to use the File class to obtain the full path of the file. 
This method enables independent and direct access to the file from the local file system.








If the HTML file is stored as a local file on the desktop of your MacBook and is not included in your project, then if you push the project to GitHub, other people will not be able to access this HTML file. In this case, your code will only work on your computer, as the path to the HTML file is specific to your local system.

If you want to share the project and allow others to run this test, you should include the HTML file as part of your project. By doing this, anyone who downloads the project from GitHub will have access to this HTML file and will be able to run your tests. You can achieve this by placing the HTML file within your project's directory and adjusting the file path according to the project's directory structure.

For example, you could create a folder named resources in the root directory of your project and place the HTML file there. Then, when loading this file with Selenium WebDriver, use this path within your project. This will enhance the portability of your project and ensure it works on other systems as well.


