package ch01.locators;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test_Selected_Attribute {
    /*
    Using CSS Selectors:
    When using a CSS selector like By.cssSelector("select option[selected='selected']"),
    the selected="selected" part is used to find an option element
    with the selected attribute having the value 'selected'.
    This is utilized in HTML to specify elements with a specific attribute value.

Validation with getAttribute Method:
When you call getAttribute("selected"), Selenium returns the value of the selected attribute for that element.
In HTML and the DOM, the selected attribute is typically treated
as a boolean: it returns true if the element is selected, and null or false if not.
Therefore, when using getAttribute to check the selected attribute,
it checks for the presence of the attribute,
not whether its value is "selected" (a textual value), but usually true or null/false.

It's important to understand that these two approaches are used for different situations and needs.
While you use attribute='value' in CSS selectors, for validation with getAttribute,
expecting a boolean value is more appropriate.

For instance, to validate whether an option element is selected or not, you would typically use code like:

WebElement selectedOption = driver.findElement(By.cssSelector("select option[selected='selected']"));
assertTrue(selectedOption.isSelected());
or

WebElement selectedOption = driver.findElement(By.cssSelector("select option[selected='selected']"));
assertEquals("true", selectedOption.getAttribute("selected"));

In both cases, the presence or state of the selected attribute is checked, but expressed in different ways.
     */
    WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }
    @Before
    public void setup(){
        // Launch Chrome
        driver = new ChromeDriver();
        // Maximize the browser window
        driver.manage().window().maximize();
    }
    @Test
    public void testSelect3() throws InterruptedException {
        driver.get(
                "https://the-internet.herokuapp.com/dropdown");
        WebElement selectedOption = driver.findElement(By.cssSelector("select option[value='1']"));
        System.out.println(selectedOption.getText());
        selectedOption.click();
        //we can not use -selected- like other attributes, so
        // we can not use like below
        //assertThat(selectedOption.getAttribute("selected")).isEqualTo("selected");
        assertThat(selectedOption.getAttribute("selected")).isEqualTo("true");
        assertTrue(selectedOption.isSelected());
        assertEquals("true", selectedOption.getAttribute("selected"));
    }

    @After
    public void teardown(){
        driver.quit();
    }
}


