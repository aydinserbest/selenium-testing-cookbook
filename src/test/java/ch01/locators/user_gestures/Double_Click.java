package ch01.locators.user_gestures;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class Double_Click {

    /*
    For performing double-click on an element,
    doubleClick() method of the Actions class is called.
    For calling this method,
    we need to create an instance of Actions class as follows:
    Actions builder = new Actions(driver);

     */
    WebDriver driver;
    @BeforeClass
    public static void setupClass(){
        WebDriverManager.chromedriver().setup();}
    @Before
    public void setup(){driver = new ChromeDriver();}
    @Test
    public void testDoubleClick(){
        driver.get("http://cookbook.seleniumacademy.com/DoubleClickDemo.html");
        WebElement message = driver.findElement(By.cssSelector("#message"));
        System.out.println(message.getCssValue("background-color"));
        System.out.println(message.getCssValue("background-color").toString());
        assertThat(message.getCssValue("background-color")).isEqualTo("rgba(0, 0, 255, 1)");
        assertThat(message.getAttribute("class")).isEqualTo("");
        //assertEquals("rgba(0, 0, 255, 1)",message.getCssValue("background-color").toString());
        assertEquals("rgba(0, 0, 255, 1)",message.getCssValue("background-color").toString());

        Actions actions = new Actions(driver);
        actions.doubleClick(message).build().perform();
        System.out.println(message.getCssValue("background-color"));
        assertThat(message.getCssValue("background-color")).isEqualTo("rgba(255, 255, 0, 1)");
        assertThat(message.getAttribute("class")).isEqualTo("dbl");
        //assertEquals("rgba(255, 255, 0, 1)",message.getCssValue("background-color").toString());
        assertEquals("rgba(255, 255, 0, 1)",message.getCssValue("background-color").toString());



    }
    @After
    public void teardown(){
        if (driver != null)
            driver.quit();
    }
}
