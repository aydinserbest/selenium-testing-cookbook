package ch01.locators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
/*
On the page, there are multiple checkboxes or radio buttons.
Our goal is to assert which elements are selected by default when the page is first loaded.
 */

public class Jquery_Selectors {
    WebDriver driver;
    @BeforeClass
    public static void setupClass(){
        WebDriverManager.chromedriver().setup();
    }
    @Before
    public void setup(){driver = new ChromeDriver();
    }
    @Test
    public void testDefaultSelectedCheckbox() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        /*
        to see which elements are selected by default when the page is first loaded
        we store the IDs of the elements that are supposed to be selected in a list.
        Later, we will use this list in our assertions.
        Expected list of selected Checkbox
        Defines which checkboxes should be selected at the end of the test.
         */
        List<String> checked =  Arrays.asList(new String[]{"my-check-1", "my-radio-1"});
        //Create an instance of JavaScript Executor from driver
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //Finds all the selected (:checked) checkboxes on the page.
        List<WebElement> elements = (List<WebElement>) js.executeScript("return jQuery.find(':checked')");
        //Verify two Checkbox are selected
        assertEquals(elements.size(), 3);
        //Verify correct Checkbox are selected
        //In the for loop, checks whether the ID of each found element is in the initially defined checked list.
        for (WebElement element : elements)
        assertTrue(checked.contains(element.getAttribute("id")));
    }
    @After
    public void teardown(){
        driver.quit();
    }
}
