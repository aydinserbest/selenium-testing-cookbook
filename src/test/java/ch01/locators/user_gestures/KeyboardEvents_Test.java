package ch01.locators.user_gestures;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.SystemUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class KeyboardEvents_Test {
    WebDriver driver;
    String fileUrl;

    @BeforeClass
    public static void setupClass(){WebDriverManager.chromedriver().setup();}
    @Before
    public void setup(){
        driver = new ChromeDriver();
        fileUrl = getFileUrl("OwnHTML.html");
    }
    private String getFileUrl(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resourceUrl = classLoader.getResource(fileName);
        if (resourceUrl != null) {
            return resourceUrl.toString();
        } else {
            throw new RuntimeException("File not found: " + fileName);
        }
    }
    //test the selection of multiple rows on a web page using the Ctrl key:
    @Test
    public void testUsingControlKey() {
        driver.get(fileUrl);
        List<WebElement> tableRows = driver.findElements(By.xpath("//table[@class='iceDatTbl']/tbody/tr"));

        //Select second and fourth row from table using Control Key.
        //Row Index start at 0
        Actions builder = new Actions(driver);
        Keys modifier = SystemUtils.IS_OS_MAC ? Keys.COMMAND : Keys.CONTROL;
        builder.click(tableRows.get(1))
                .keyDown(modifier)
                .click(tableRows.get(3))
                .keyUp(modifier)
                .build().perform();

        //Verify Selected Row table shows two rows selected
       List<WebElement> rows = driver.findElements(By.xpath("//div[@class='icePnlGrp exampleBox']/table[@class='iceDatTbl']/tbody/tr"));
        assertEquals(2,rows.size());
    }
    /*
    Row Selection Using the Ctrl Key:

    keyDown(Keys.CONTROL):
    This simulates pressing and holding down the Control key. It is used for selecting multiple items.
    click(tableRows.get(1)) and click(tableRows.get(3)):
    These lines select the second and fourth rows (index starts at 0).
    keyUp(Keys.CONTROL):
    This simulates releasing the Control key. This step completes the multiple item selection process.
    If you wanted to select three rows, the code would be modified like this:

    builder.click(tableRows.get(1))
           .keyDown(Keys.CONTROL)
           .click(tableRows.get(2))
           .click(tableRows.get(3))
           .keyUp(Keys.CONTROL)
           .build().perform();
     */
    @After
    public void teardown(){driver.quit();}

}
