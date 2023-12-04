package ch01.locators.html;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.net.URL;

public class UsingClassLoader {
    WebDriver driver;
    String fileUrl;
    @BeforeClass
    public static void setupClass(){
        WebDriverManager.chromedriver().setup();
    }
    @Before
    public void setup(){
        driver = new ChromeDriver();
        fileUrl = getFileUrl("yourFileName.html"); // Replace with your actual file name
    }


    private String getFileUrl(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resourceUrl = classLoader.getResource(fileName);
        if (resourceUrl != null) {
            return resourceUrl.toString();
        } else {
            throw new RuntimeException("File not found: " + fileName);
        }
    }
    /*
    The getResource method of the ClassLoader returns a URL representing the specified file or resource name.
    This URL contains the complete path and location of the file, which can be used to open or read the file.

    For instance, if you have a file named html.html in the src/main/resources folder of your project
    and you want to open this file with Selenium WebDriver,
    you can obtain the URL of this file using the ClassLoader.
    In the given code snippet,
    the call to getClass().getClassLoader() returns the class loader of the running Java class.
    The call to classLoader.getResource("html.html") then returns the URL of the html.html file.
    This URL includes the physical location of the file, which can be used later to open the file.
     */

    @Test
    public void utilizeNearby() {
        driver.get(fileUrl);
        // ... Test codes
    }

    @Test
    public void useavaScript() {
        driver.get(fileUrl);
        // ... Test codes
    }
}
