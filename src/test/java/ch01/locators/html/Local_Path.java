package ch01.locators.html;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.nio.file.Paths;

public class Local_Path {
    WebDriver driver;
    @BeforeClass
    public static void setupClass(){
        WebDriverManager.chromedriver().setup();
    }
    @Before
    public void setup(){
        driver = new ChromeDriver();
        //Get the full path of the HTML file
        String localFilePath = Paths.get("/Users/gebruiker/Desktop/table.html").toUri().toString();
        //Load the HTML file with WebDriver
        driver.get(localFilePath);
        // other codes
    }
}
