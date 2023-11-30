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

public class Child_Sibling_Elements {
    WebDriver driver;
    @BeforeClass
    public static void setupClass(){
        WebDriverManager.chromedriver().setup();
    }
    @Before
    public void setup(){driver = new ChromeDriver();
    }
    //Here, > is used denote the parent and child relationship.
    @Test
    public void testChild() {
        driver.get(
                "http://cookbook.seleniumacademy.com/Locators.html");
        //below line finds 3 elements, so maybe the next one is better
        WebElement userName = driver.findElement(By.cssSelector("form#loginForm > input"));
        //  or
       // WebElement userName2 = driver.findElement(By.cssSelector("form#loginForm :nth-child(2)"));

        // after m of loginForm , there is an space before :,   form#loginForm :nth-child(2)

        /*
        other usage:
        form#loginForm :first-child
        form#loginForm :last-child
         */
    }
    @Test
    public void testSibling(){
        driver.get(
                "");
        WebElement productDescription = driver.findElement(By.cssSelector("div#top5 > p + p"));
        /*
        div#top5  , then +p is , child of it,
        then, second +p is sibling of it, the first seibling with tagname p,
        to go second sibling,
        div#top5 > p +*+ p
        we put * between + and +
         */
    }
    @After
    public void teardown(){
        driver.quit();
    }
}
    /*
    https://www.w3schools.com/cssref/css_selectors.php for more cssSelector ways
     */
