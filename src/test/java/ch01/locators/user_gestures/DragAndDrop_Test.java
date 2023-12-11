package ch01.locators.user_gestures;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.network.model.DataReceived;
import org.openqa.selenium.interactions.Actions;
import static org.junit.Assert.assertEquals;


public class DragAndDrop_Test {
    WebDriver driver;
    private StringBuilder verificationErrors = new StringBuilder();

    @BeforeClass
    public static void setupClass(){
        WebDriverManager.chromedriver().create();
    }
    @After
    public void teardown(){
        if (driver != null){
            driver.quit();
        }
    }
    @Test
    public void testDragDrop(){
        driver.get("http://cookbook.seleniumacademy.com/DragDropDemo.html");
        WebElement source = driver.findElement(By.xpath("//p[.='Drag me to my target']"));
        WebElement target = driver.findElement(By.xpath("//p[text()='Drop here']"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).perform();
        try
        {
            assertEquals("Dropped!", target.getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }
}
    /*
    For dragging an element on to another element and dropping it,
    we need to locate these elements and pass them to the dragAndDrop() method of the Actions class.
    For calling this method,
    we need to create an instance of the Actions class in the following way:
    Actions builder = new Actions(driver);
    The dragAndDrop() method needs the source element and target element
    where the source element will be dragged-and-dropped.
     */
