package ch01.locators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class XpathLocatorTest {
    WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }
    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    /*
    XPath will check all the elements and their attributes to see
    if they have this value and return the matching element.
     */
    @Test
    public void testValue(){
        driver.get(
                "http://cookbook.seleniumacademy.com/Locators.html");
        WebElement userName = driver.findElement(By.xpath("//input[@*='username']"));

    }
    @After
    public void teardown(){
        driver.quit();
    }
}
