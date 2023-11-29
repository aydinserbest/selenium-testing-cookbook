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

import static org.assertj.core.api.Assertions.assertThat;

public class ByCssSelector {
    WebDriver driver;
    @BeforeClass
    public static void setupClass(){
        WebDriverManager.chromedriver().setup();
    }
    @Before
    public void setup(){driver = new ChromeDriver();
    }
    @Test
    public void testByCssSelectorAttribute(){
        /*
        In the following example, the Name attribute is used to locate an <input> element.
         */
        driver.get(
                "http://cookbook.seleniumacademy.com/Locators.html");
        WebElement userName = driver.findElement(By.cssSelector("input[name=username]"));
        assertThat(userName.getAttribute("value")).isEqualTo("login");
        /*
        Using the name attribute to locate an element is
        similar to the name() locator method of the By class.
         */
    }@Test
    public void testByCssSelectorAttribute2(){
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        WebElement hidden = driver.findElement(By.cssSelector("input[type=hidden]"));
        assertThat(hidden.isDisplayed()).isFalse();
    }
    /*
    Let's use some other attribute to locate an element.
    In the following example, the <img> element is located by using its alt attribute.
     */
    @Test
    public void testByCssSelectorAttribute3(){
        driver.get(
                "http://cookbook.seleniumacademy.com/Locators.html");
        WebElement previousButton = driver.findElement(By.cssSelector("img[alt='Previous']"));
        assertThat(previousButton.getTagName()).isEqualTo("img");
    }
    /*
    You might come across situations where one attribute may not be sufficient
    to locate an element and you need to combine additional attributes for a precise match.
    In the following example,
    multiple attributes are used to locate the Login button's <input> element:
    WebElement previousButton = driver.findElement(By.cssSelector("input[type='submit'][value='Login']"));
     */
    @Test
    public void testByCssSelectorAttribute4(){
        driver.get(
                "http://cookbook.seleniumacademy.com/Locators.html");
        WebElement previousButton = driver.findElement(By.cssSelector("input[type='submit'][value='Login']"));
        assertThat(previousButton.getTagName()).isEqualTo("input");
    }
    @Test
    public void testByCssSelectorBasic(){
        /*
        While finding elements using the CSS selector,
        we can use the Class attribute to locate an element.
        This can be done by specifying the type of HTML tag,
        then adding a dot followed by the value of the class attribute in the following way:
         */
        driver.get(
                "http://cookbook.seleniumacademy.com/Locators.html");
        WebElement login = driver.findElement(By.cssSelector("input.login"));
        /*
        This will find the Login button's <input> tag whose Class attribute is login.
         */
        assertThat(login.getAttribute("name")).isEqualTo("login");
        /*
        There is also a shortcut where you can put a . and class attribute value and ignore the HTML tag.
        However, this will return all the elements with class as login
        and the test may not return the correct element.
        This method is similar to the className() locator method.

        WebElement loginButton = driver.findElement(By.cssSelector(".login"));
         */
    }
    @Test
    public void testId(){
        driver.get(
                "http://cookbook.seleniumacademy.com/Locators.html");
        /*
        This can be done by specifying the type of HTML tag,
        then entering a hash followed by the value of the Class attribute, as shown:
         */

        WebElement login = driver.findElement(By.cssSelector("input#username"));
        //This will return the username <input> element using its id attribute.

        /*
        There is also a shortcut where you can enter # and a class attribute value
        and ignore the HTML tag.
        However, this will return all the elements with the id set as username
        and the test may not return the correct element.
        This has to be used very carefully.

        WebElement userName = driver.findElement(By.cssSelector("#username"));

        This method is similar to the id locator strategy.
         */

    }
    /*
    we want to locate elements based on only the specific attribute defined for them
    but not attribute values.
    For example, we want to lookup all the <img> elements which have alt attribute specified.
     */
    @Test
    public void testAttributeNameOnly(){
        driver.get(
                "http://cookbook.seleniumacademy.com/Locators.html");
        List<WebElement> imagesWithAlt = driver.findElements(By.cssSelector("img[alt]"));
        /*
        A Boolean not()pseudo-class can also be used to locate elements
        not matching the specified criteria.
        For example, to locate all the <img> elements that do not have the alt attribute,
        the following method can be used:
         */
        List<WebElement> imagesWithoutAlt = driver.findElements(By.cssSelector("img:not([alt])"));


    }
    @Test
    public void testByCssSelectorAdvanced(){
        driver.get(
                "https://the-internet.herokuapp.com/checkboxes");
        WebElement checkbox2 = driver.findElement(By.cssSelector("[type=checkbox]:checked"));
        assertThat(checkbox2.isSelected()).isTrue();

        WebElement checkbox1 = driver.findElement(By.cssSelector("[type=checkbox]:not(:checked)"));
        assertThat(checkbox1.isSelected()).isFalse();
    }
    @After
    public void teardown(){
        driver.quit();
    }
}

