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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
    @Test
    public void test_child_2() {
        driver.get(
                "https://the-internet.herokuapp.com/dropdown");
        WebElement selectedOption = driver.findElement(By.cssSelector("select option[selected='selected']"));
        System.out.println(selectedOption.getText());
    }
    @Test
    public void test_child_3() {
        driver.get(
                "http://cookbook.seleniumacademy.com/Locators.html");
        // Locate the first cell of the second row using CSS selector
        WebElement cell = driver.findElement(By.cssSelector("table#items tbody tr:nth-child(2) td"));
        System.out.println("Data in the first cell of the second row: " + cell.getText());
    }
    /*
    In both examples you provided,(test_child_2 and test_child_3) CSS selectors are used to navigate
    from a parent element to a child element within the HTML document.
    This approach allows for precise and targeted access to specific HTML elements.

In the first example (with select and option elements):
Here, the selected option element within the select element is being located.
The CSS selector "select option[selected='selected']" is written to access
the <option> child element
that has the selected attribute, within the parent <select> tag.

In the second example (with table, tr, and td elements):
Here, the first cell (td) in the second row (tr:nth-child(2)) of a table with the ID table#items
is being located.
The CSS selector "table#items tbody tr:nth-child(2) td" is used to reach a specific <tr> (row)
within the parent <table> element and a <td> (cell) within that row.

In both cases, CSS selectors are employed to reach specific elements within a given structure.
This is a common and effective method for accessing particular elements on web pages
and manipulating them using Selenium.
     */
    @After
    public void teardown(){
        driver.quit();
    }
}
    /*
    https://www.w3schools.com/cssref/css_selectors.php for more cssSelector ways
     */
