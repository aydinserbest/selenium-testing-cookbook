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
import org.openqa.selenium.WebDriverException;


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
    public void setup(){
        driver = new ChromeDriver();
        injectjQueryIfNeeded(); // Ensure jQuery is loaded
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
    /*
    If jQuery is not initially loaded on the page, consider dynamically injecting jQuery for your tests.
    However, this method is generally used in testing scenarios.
    These 3 methods provides an effective solution for using jQuery in Selenium WebDriver tests
    when the page itself does not load jQuery. It allows for locating elements using jQuery selectors
    even if jQuery is not initially loaded on the page.
    This method is useful in scenarios where:
        -Your page does not use jQuery, but you want to use jQuery selectors in your Selenium tests.
     */
    @Test
    public void testInjectJQuery() {
        driver.get(
                "http://cookbook.seleniumacademy.com/Locators.html");
        List<String> checked =  Arrays.asList(new String[]{"user128_admin", "user220_browser"});
        JavascriptExecutor js = (JavascriptExecutor) driver;
        List<WebElement> elements = (List<WebElement>) js.executeScript("return jQuery.find(':checked')");
        assertEquals(elements.size(), 2);
        for (WebElement element : elements)
            assertTrue(checked.contains(element.getAttribute("id")));
    }
    private void injectjQueryIfNeeded() {
        if (!jQueryLoaded())
            injectjQuery();
    }

    public Boolean jQueryLoaded() {
        Boolean loaded;
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            loaded = (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery()!=null");
        } catch (WebDriverException e) {
            loaded = false;
        }
        return loaded;
    }

    public void injectjQuery() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        ((JavascriptExecutor) driver).executeScript(" var headID = document.getElementsByTagName('head')[0];"
                + "var newScript = document.createElement('script');"
                + "newScript.type = 'text/javascript';"
                + "newScript.src = 'http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js';"
                + "headID.appendChild(newScript);");
    }
    @After
    public void teardown(){
        driver.quit();
    }
}
