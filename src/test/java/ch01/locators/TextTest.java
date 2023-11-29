package ch01.locators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TextTest {
    /*
    locating elements based on their text contents.
    This approach comes in handy when elements don't have enough attributes
    or when no other strategies work when attempting to locate these elements.
     */
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
    Here we are using the contains function along with the text() function.
    The text() function returns the complete text from the element
    and the contains() function checks for the specific value that we have mentioned.
     */
    @Test
    public void testValue() {
        driver.get(
                "http://cookbook.seleniumacademy.com/Locators.html");
        WebElement cell = driver.findElement(By.xpath("//td[contains(text(),'Item 1')]"));
        System.out.println(cell.getText());
    }
    /*

    //td[text()='Item 1']
      we can use  . instead of  text()
    //td[.='Item 1']

    contains requires (string, string)
    //td[contains(.,'Item 1')]
     */

}
