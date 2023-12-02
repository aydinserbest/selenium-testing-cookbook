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
import static org.junit.Assert.assertTrue;

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
    @Test
    public void tesTable_cssSelector() {
        driver.get(
                "http://cookbook.seleniumacademy.com/Locators.html");
        // Locate the first cell of the second row using CSS selector
        WebElement cell = driver.findElement(By.cssSelector("table#items tbody tr:nth-child(2) td"));
        System.out.println("Data in the first cell of the second row: " + cell.getText());

    }@Test
    public void tesTable_xpath() {
        driver.get(
                "http://cookbook.seleniumacademy.com/Locators.html");
        // Locate the first cell of the second row using xpath
        WebElement cell = driver.findElement(By.xpath("//table[@id='items']/tbody/tr[2]/td"));
        System.out.println("Data in the first cell of the second row: " + cell.getText());
    }
    @Test
    public void tesTable_Better_Approach() {
        driver.get(
                "http://cookbook.seleniumacademy.com/Locators.html");

        //First, find the td containing Nash's name
        WebElement nashRow = driver.findElement(By.xpath("//td[text()='Nash']"));

        //Find the checkbox in Nash's row that corresponds to 'Admin' access
        WebElement adminCheckbox = nashRow.findElement(By.xpath("following-sibling::td//input[@id='user128_admin']"));

        //Perform a checkbox check
        boolean isAdminChecked = adminCheckbox.isSelected();

        //Test verification
        assertTrue("Nash should have admin access", isAdminChecked);
    /*
    This code finds the "Admin" checkbox for Nash
    by first locating the td element containing Nash's name
    and then accessing the checkbox with the ID user128_admin in the same row.
    This method ensures the correct checkbox is identified, even in situations
    where the checkbox ID is not directly correlated with the user's name.
         */
    }
    @After
    public void teardown(){
        driver.quit();
    }
}

