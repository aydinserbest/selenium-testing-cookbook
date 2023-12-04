package ch01.locators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class Parent_To_Child_Css_and_Xpath {
    WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setup() {
        driver = new ChromeDriver();
    }

    //Here, > is used denote the parent and child relationship.
    @Test
    public void test1() {
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
    public void test2() {
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
        /*
    select indicates the parent element, which is a <select> tag used for drop-down menus.
    option[selected='selected'] identifies the child element.
    It's an <option> tag (which represents an individual choice in the drop-down)
    that has an attribute selected set to 'selected'.
    This signifies the currently selected option in the drop-down menu.
    Hence, the code navigates from the parent <select> element to its child <option> element
    that is currently selected.
         */
    }

    @Test
    public void test3() {
        driver.get(
                "http://cookbook.seleniumacademy.com/Locators.html");
        // Locate the first cell of the second row using CSS selector
        WebElement cell = driver.findElement(By.cssSelector("table#items tbody tr:nth-child(2) td"));
        System.out.println("Data in the first cell of the second row: " + cell.getText());
        /*
        table#items is the parent element, identifying a <table> with the ID items.
        The # symbol is used to specify an ID in CSS selectors.
    tbody represents a child of the <table>, which is the <tbody> tag.
    This is typically used to group the body content in a table.
    tr:nth-child(2) is the next level of child element under <tbody>.
    It represents the second <tr> (table row) element. :nth-child(2) is
    a pseudo-class that selects the second child of its parent.
    Finally, td selects the <td> elements (table data cells) within that second row.
    So, this code effectively navigates from a specific table (table#items) to its body (tbody),
    then to the second row (tr:nth-child(2)) of that body, and finally to all cells (td) in that row.
         */
    }

    @Test
    public void test4() {
        driver.get(
                "http://cookbook.seleniumacademy.com/Locators.html");

        //First, find the td containing Nash's name
        WebElement nashRow = driver.findElement(By.xpath("//td[text()='Nash']"));

        //Find the checkbox in Nash's row that corresponds to 'Admin' access
        WebElement adminCheckbox = nashRow.findElement(By.xpath("following-sibling::td//input[@id='user128_admin']"));
        /*
        What you're trying to do above is
        narrow down the scope to find another WebElement within a WebElement.

        we can re-write it without scope narrowing:
        WebElement adminCheckbox = driver.findElement(By.xpath("//td[text()='Nash']/ancestor::tr//input[@id='user128_admin']"));
        */
        //Perform a checkbox check
        boolean isAdminChecked = adminCheckbox.isSelected();

        //Test verification
        assertTrue("Nash should have admin access", isAdminChecked);
    }

}
