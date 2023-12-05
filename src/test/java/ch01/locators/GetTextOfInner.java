package ch01.locators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class GetTextOfInner {
    WebDriver driver;
    String fileUrl;
    @BeforeClass
    public static void setupClass(){
        WebDriverManager.chromedriver().setup();
    }
    @Before
    public void setup(){
        driver = new ChromeDriver();
        fileUrl = getFileUrl("getText.html"); // Replace with your actual file name

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
    @Test
    public void getTextAll(){
        driver.get(fileUrl);
        //Get the area Element
        WebElement area = driver.findElement(By.id("area"));

        //Verify element's text displays "Div's Text\nSpan's Text"
        assertEquals("Div's Text\nSpan's Text",area.getText());
    }
}
