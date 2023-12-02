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
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Tables_Test {
    WebDriver driver;
    @BeforeClass
    public static void setupClass(){
        WebDriverManager.chromedriver().setup();
    }
    @Before
    public void setup(){driver = new ChromeDriver();
    }
    @Test
    public void tesTable() {
        driver.get(
                "http://cookbook.seleniumacademy.com/Locators.html");
        WebElement simpleTable = driver.findElement(By.cssSelector("#items"));
        //Get all rows
        //<tr> elements are rows of a table.
        List<WebElement> rows = simpleTable.findElements(By.tagName("tr"));
        assertEquals(3, rows.size());

        //Print data from each row
        for (WebElement row : rows) {
            //Each <tr> element then holds the <td> elements, which are the columns or cells of the table.
            List<WebElement> cols = row.findElements(By.tagName("td"));
            for (WebElement col : cols) {
                System.out.println(col.getText() + "\t");
            }
            System.out.println();
        }
    }
    /*
    A table in HTML is a collection of <tr> and <td> elements for rows and cells, respectively.

     */
    @After
    public void teardown(){
        driver.quit();
    }
}

