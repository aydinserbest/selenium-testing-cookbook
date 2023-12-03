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
import org.openqa.selenium.support.locators.RelativeLocator;

import static org.junit.Assert.assertTrue;

public class Narrow_Scope {
    WebDriver driver;
    @BeforeClass
    public static void setupClass(){
        WebDriverManager.chromedriver().setup();
    }
    @Before
    public void setup(){driver = new ChromeDriver();
    }

    @Test
    public void testDate() {
        driver.get(
                "https://rahulshettyacademy.com/AutomationPractice/");

        WebElement sql = driver.findElement(By.xpath("//td[contains(text(),'Learn SQL in Practical + Database Testing from Scratch')]/.."));

        WebElement childSql=sql.findElement(By.xpath(".//td[2]"));

        System.out.println(childSql.getText());

//        WebElement sqlCourseElement = sql.findElement(By.xpath("./following-sibling::td"));

        WebElement instructor=driver.findElement(
                RelativeLocator.with(By.tagName("tr")).above(sql));
        System.out.println(instructor.findElement(By.xpath(".//td[2]")).getText());

//        WebElement appium = instructor.findElement(By.xpath("//td[2]"));
        /*
        What you're trying to do here is
        narrow down the scope to find another WebElement within a WebElement.
        However, there is a mistake in the expression sql.findElement(By.xpath("//td[2]")).
        This searches from the root of the document, not within the sql element,
        because it starts with //. This will return the first second td element
        it finds from the root of the document, which may not be the second td child of the tr element you are expecting.

        Therefore,
        you need to put a dot at the beginning of your XPath expression
        to search among the elements under the current element (in this case, sql).
        In XPath expressions,
        a statement that starts with // is used to search for a specified node anywhere from the root of the document.
        A statement starting with . (dot) is used to search within the subnodes of the current node or context.
         */
    }
    @Test
    public void testDate2() {
        driver.get(
                "https://rahulshettyacademy.com/AutomationPractice/");

        System.out.println(driver.findElements(By.tagName("a")).size());
        WebElement footerDriver = driver.findElement(By.id("gf-BIG"));
        System.out.println(footerDriver.findElements(By.tagName("a")).size());
        WebElement columnDriver=footerDriver.findElement(By.xpath("//td[1]/ul"));
        System.out.println("column links count "+columnDriver.findElements(By.tagName("a")).size());
    }
    @Test
    public void tesTable_Better_Approach() {
        driver.get(
                "http://cookbook.seleniumacademy.com/Locators.html");

        //First, find the td containing Nash's name
        WebElement nashRow = driver.findElement(By.xpath("//td[text()='Nash']"));

        //Find the checkbox in Nash's row that corresponds to 'Admin' access
        WebElement adminCheckbox = nashRow.findElement(By.xpath("following-sibling::td//input[@id='user128_admin']"));
       /*
    This line of code operates on a previously located WebElement named nashRow.
    nashRow represents a <td> element containing the text "Nash".
    The method nashRow.findElement is used to find another element
    within the context of this specific WebElement.

    following-sibling::td:
    This XPath expression begins a search among the siblings of nashRow.
    By using the following-sibling axis, it finds <td> elements within the same <tr> element as nashRow,
    but following it.
    //input[@id='user128_admin']:
    Within these identified <td> elements, it looks for an input element with the id attribute
    set to "user128_admin".
    This expression is used to locate the input element with the ID "user128_admin"
    within the same row (<tr>) as nashRow, specifically in the <td> elements that follow nashRow.
    As nashRow itself is a <td>, the following-sibling::td part
    navigates to subsequent <td> elements in the same row (<tr>).

    This approach confines the search to the row where nashRow is located,
    avoiding a broader search scope. It's a more precise and targeted strategy for locating the desired element.
        */

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
    @After
    public void teardown(){
        driver.quit();
    }
}
