package ch01.locators;

import io.github.bonigarcia.wdm.WebDriverManager;
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
        List<WebElement> links = driver.findElements(By.tagName("tr"));
        assertEquals(10, links.size());
    }
    @Test
    public void testRows(){
        WebElement table = driver.findElement(By.id("items"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        assertEquals(3, rows.size());
    }

}
