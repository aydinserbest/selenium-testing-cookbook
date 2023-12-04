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
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertEquals;

public class Test_Select {
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
    /*
    Clicking on option Elements
    Direct Interaction:
    In HTML, option elements typically cannot be directly clicked on.
    Instead, a user first clicks on the select element and then chooses an option from the opened menu.

    Interaction with option in Selenium:
    Selenium WebDriver provides a special Select class for interacting with select elements.
    This class offers more suitable methods for managing select elements and their contents.

    Errors and Behavior:
    When an option element is directly clicked on,
    this interaction may not work as expected in some browsers.

    In some browsers and under certain conditions,
    it may be possible to directly click on option elements to select them.
    However, this behavior can vary from browser to browser and depending on how the web page is configured.
    Typically, using the Select class of Selenium WebDriver for interacting with select elements yields
    more consistent results. The Select class is designed to manage select elements and their contents,
    and it is recommended to use this class instead of directly clicking on option elements.
    However, in the specific combination of the web page and browser you are testing,
    it might be possible to directly click on an option element to make a selection.

    Recommendation
    For more consistent interactions and better compatibility across browsers,
    it is generally a better practice to use the Select class to select option elements.
     */

    @Test
    public void testSelect(){
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");

        // find select element
        WebElement selectElement = driver.findElement(By.cssSelector("select[name='my-select']"));
        Select select = new Select(selectElement);

        // choose second option
        select.selectByIndex(1);  // Index starts from 0, hence 1 corresponds to the 'One' option."

        // Get and check the value of the selected option."
        String selectedOption = select.getFirstSelectedOption().getText();
        System.out.println(selectedOption);
        assertEquals("One", selectedOption);
    }

    @After
    public void teardown(){
        driver.quit();
    }
}

