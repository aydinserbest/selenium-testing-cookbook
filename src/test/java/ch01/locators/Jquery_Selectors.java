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
        //Expected list of selected Checkbox
        List<String> checked =  Arrays.asList(new String[]{"my-check-1", "my-radio-1"});
        //Create an instance of JavaScript Executor from driver
        JavascriptExecutor js = (JavascriptExecutor) driver;
        List<WebElement> elements = (List<WebElement>) js.executeScript("return jQuery.find(':checked')");
        //Verify two Checkbox are selected
        assertEquals(elements.size(), 3);
        //Verify correct Checkbox are selected
        for (WebElement element : elements)
        assertTrue(checked.contains(element.getAttribute("id")));
    }
    @After
    public void teardown(){
        driver.quit();
    }
}
