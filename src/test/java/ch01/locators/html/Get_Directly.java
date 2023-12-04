package ch01.locators.html;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Get_Directly {
    WebDriver driver;
    @BeforeClass
    public static void setupClass(){
        WebDriverManager.chromedriver().setup();
    }
    @Before
    public void setup(){
        driver = new ChromeDriver();
    }
    @Test
    public void withoutToString(){
        //Get the full path of the HTML file
        //Load the HTML file with WebDriver
        driver.get("/Users/gebruiker/Desktop/table.html");
    }
}
