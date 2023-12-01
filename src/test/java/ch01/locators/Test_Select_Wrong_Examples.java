package ch01.locators;

import static org.assertj.core.api.Assertions.assertThat;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class Test_Select_Wrong_Examples {
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
    In such cases, Selenium does not throw an error, but the selection of the option does not occur.
 */

    //for below test, default --Open this select menu-- is selected
    // Selenium does not throw an error,
    // instead , it chooses One (as expected),
    // it should not select anything,
    // website can have a failure
    @Test
    public void testSelect() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        //default --Open this select menu-- is selected
        WebElement selectedOption = driver.findElement(By.cssSelector("select :nth-child(2)"));
        System.out.println(selectedOption.getText());
        selectedOption.click();
        assertThat(selectedOption.getText()).isEqualTo("One");
        //assertEquals(selectedOption.getText(), "One");
    }
    @Test
    public void testSelect2(){
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement selectedOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("select :nth-child(2)")));
        System.out.println(selectedOption.getText());
        selectedOption.click();

        //select :nth-child(1)
        assertThat(selectedOption.getText()).isEqualTo("One");
        //assertEquals(selectedOption.getText(), "One");
    }

    //for below test,
    // Selenium does not throw an error, but the selection of the option does not occur.
    @Test
    public void testSelect3(){
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

        //assertEquals(selectedOption.getText(), "Option 1");
    }
    @Test
    public void testSelect4() {
        driver.get(
                "https://selectorshub.com/xpath-practice-page/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement selectedOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("select option[value='Opel']")));
    // ERROR: TimeoutException: Expected condition failed: waiting for visibility of element located by
        System.out.println(selectedOption.getText());
        selectedOption.click();
    }
    @Test
    public void testSelect5() {
        driver.get(
                "https://selectorshub.com/xpath-practice-page/");
        WebElement selectedOption = driver.findElement(By.cssSelector("select option[value='Opel']"));
        System.out.println(selectedOption.getText());
        selectedOption.click();
        /*
        NoSuchElementException: no such element: Unable to locate element:
         */
    }

    @After
    public void teardown(){
        driver.quit();
    }
}
