package ch01.locators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TagnameTest {
    static WebDriver driver;
    @BeforeClass
    public static void setupClass(){
        driver = WebDriverManager.chromedriver().create();
        driver.get("http://cookbook.seleniumacademy.com/Locators.html");
    }
    @Test
    public void testTagname(){
        //Get all the links displayed on Page
        List<WebElement> links = driver.findElements(By.tagName("a"));

        //Verify there are four links displayed on the page
        assertEquals(10, links.size());

        //Iterate though the list of links and print
        //target for each link
        for(WebElement link : links)
            System.out.println(link.getAttribute("href"));
    }
    @Test
    public void testTagname2(){
        List<WebElement> links = driver.findElements(By.tagName("tr"));
        assertEquals(10, links.size());
    }
    @Test
    public void testRows(){
        WebElement table = driver.findElement(By.id("items"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        assertEquals(3, rows.size());
    }
    @After
    public void teardown(){
        driver.quit();
    }

}
